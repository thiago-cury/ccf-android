package thiagocury.eti.br.ccf;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.util.ArrayList;

public class NewBuscaCliente extends AppCompatActivity {

    private ClienteDAO cDAO;

    private ArrayList<Cliente> cliAux;
    private ClienteAdapter adapter;
    private ClienteAdapterFiltro adapterfiltro;

    //componentes visuais
    private ListView lvClientes;
    private Spinner cbPesquisa;
    private EditText etPesquisa;
    private Button btnPesquisar;
    private Button btnExportarCSV;
    private Button btnExportarPDF;

    //criando um array para a pesquisa
    private String comboPesquisa[];

    //Variavel pra guardar a posicao selecionada
    private int posSelecionada = -1;

    //Menu de contexto
    private static final int ALTERAR = 0;
    private static final int DELETAR = 1;

    //String pra montar a query
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_busca_cliente);

        //Componentes visuais
        btnPesquisar = (Button)findViewById(R.id.btnPesquisar);
        btnExportarCSV = (Button)findViewById(R.id.btnExportarCsv);
        btnExportarPDF = (Button)findViewById(R.id.btnExportarPdf);

        lvClientes = (ListView) findViewById(R.id.lvClientes);

        cDAO = new ClienteDAO(this);
        cDAO.abrirBanco();
        cliAux = cDAO.consultar();

        cliAux = new ArrayList<Cliente>();
        cliAux = cDAO.consultar();

        if(cliAux.isEmpty()){
            AlertDialog.Builder msg = new AlertDialog.Builder(NewBuscaCliente.this);
            msg.setTitle("Alerta");
            msg.setMessage("Banco de dados vazio!");
            msg.setNeutralButton("OK", null);
            msg.show();
        }
        adapter = new ClienteAdapter(this,cliAux);

        isStoragePermissionGranted();

        //preenchendo a comboPesquisa
        comboPesquisa = new String[15];

        comboPesquisa[0] = "Selecione";
        comboPesquisa[1] = "Código";
        comboPesquisa[2] = "Nome";
        comboPesquisa[3] = "Rg";
        comboPesquisa[4] = "Cpf";
        comboPesquisa[5] = "Endereço";
        comboPesquisa[6] = "Número";
        comboPesquisa[7] = "Bairro";
        comboPesquisa[8] = "Cidade";
        comboPesquisa[9] = "Uf";
        comboPesquisa[10] = "Cep";
        comboPesquisa[11] = "Telefone Fixo";
        comboPesquisa[12] = "Telefone Celular";
        comboPesquisa[13] = "Email";
        comboPesquisa[14] = "Obs";

        cbPesquisa = (Spinner)findViewById(R.id.cbPesquisa);

        //Marcando Selecione
        //cbPesquisa.setSelection(12);

        final ArrayAdapter ad = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, comboPesquisa);

        cbPesquisa.setAdapter(ad);

        //referencia do EditText pesquisa
        etPesquisa = (EditText) findViewById(R.id.etPesquisa);

        //monta a tela
        lvClientes.setAdapter(adapter);

        //Notificando o adapter para a atualizacao dos dados na tela
        adapter.notifyDataSetChanged();

        lvClientes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                //recebendo e guardando a posicao
                posSelecionada = position;

                return false;
            }//fecha onItemLongClick
        });//fecha setOnItemLongClickListener

        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cbPesquisa.getSelectedItemPosition() == 0 || etPesquisa.getText().toString().isEmpty()) {
                    AlertDialog.Builder msg = new AlertDialog.Builder(NewBuscaCliente.this);
                    msg.setTitle("Alerta");
                    msg.setMessage("Digite e escolha um item para a pesquisa!");
                    msg.setNeutralButton("OK", null);
                    msg.show();
                }

                if (cbPesquisa.getSelectedItem().toString().equals("Código")) {
                    if (!etPesquisa.getText().toString().isEmpty()) {
                        query = " idcliente = " + etPesquisa.getText().toString();
                        cliAux = cDAO.filtrar(query);
                        adapterfiltro = new ClienteAdapterFiltro(getBaseContext(), cliAux);
                        lvClientes.setAdapter(adapterfiltro);
                        adapter.notifyDataSetChanged();
                        adapterfiltro.notifyDataSetChanged();

                    } else {

                        AlertDialog.Builder msg = new AlertDialog.Builder(NewBuscaCliente.this);
                        msg.setTitle("Alerta");
                        msg.setMessage("Preencha o campo de pesquisa !");
                        msg.setNeutralButton("Ok", null);
                        msg.show();

                        etPesquisa.setFocusable(true);

                    }//fecha if else

                } else if (cbPesquisa.getSelectedItem().toString().equals("Nome")) {

                    if (!etPesquisa.getText().toString().isEmpty()) {

                        query = " nomecompleto like '%" + etPesquisa.getText().toString() + "%'";

                        cliAux = cDAO.filtrar(query);

                        adapterfiltro = new ClienteAdapterFiltro(getBaseContext(), cliAux);

                        lvClientes.setAdapter(adapterfiltro);

                        adapter.notifyDataSetChanged();
                        adapterfiltro.notifyDataSetChanged();

                    } else {

                        AlertDialog.Builder msg = new AlertDialog.Builder(NewBuscaCliente.this  );
                        msg.setTitle("Alerta");
                        msg.setMessage("Preencha o campo de pesquisa !");
                        msg.setNeutralButton("Ok", null);
                        msg.show();

                        etPesquisa.setFocusable(true);

                    }//fecha if else

                } else if (cbPesquisa.getSelectedItem().toString().equals("Cpf")) {

                    if (!etPesquisa.getText().toString().isEmpty()) {

                        query = " cpf like '%" + etPesquisa.getText().toString() + "%'";

                        cliAux = cDAO.filtrar(query);

                        adapterfiltro = new ClienteAdapterFiltro(getBaseContext(), cliAux);

                        lvClientes.setAdapter(adapterfiltro);

                        adapter.notifyDataSetChanged();
                        adapterfiltro.notifyDataSetChanged();

                    } else {

                        AlertDialog.Builder msg = new AlertDialog.Builder(NewBuscaCliente.this);
                        msg.setTitle("Alerta");
                        msg.setMessage("Preencha o campo de pesquisa !");
                        msg.setNeutralButton("Ok", null);
                        msg.show();

                        etPesquisa.setFocusable(true);

                    }//fecha if else

                } else if (cbPesquisa.getSelectedItem().toString().equals("Endereço")) {

                    if (!etPesquisa.getText().toString().isEmpty()) {

                        query = " endereco like '%" + etPesquisa.getText().toString() + "%'";

                        cliAux = cDAO.filtrar(query);

                        adapterfiltro = new ClienteAdapterFiltro(getBaseContext(), cliAux);

                        lvClientes.setAdapter(adapterfiltro);

                        adapter.notifyDataSetChanged();
                        adapterfiltro.notifyDataSetChanged();

                    } else {

                        AlertDialog.Builder msg = new AlertDialog.Builder(NewBuscaCliente.this);
                        msg.setTitle("Alerta");
                        msg.setMessage("Preencha o campo de pesquisa !");
                        msg.setNeutralButton("Ok", null);
                        msg.show();

                        etPesquisa.setFocusable(true);

                    }//fecha if else

                } else if (cbPesquisa.getSelectedItem().toString().equals("Número")) {

                    if (!etPesquisa.getText().toString().isEmpty()) {

                        query = " numero = " + etPesquisa.getText().toString();

                        cliAux = cDAO.filtrar(query);

                        adapterfiltro = new ClienteAdapterFiltro(getBaseContext(), cliAux);

                        lvClientes.setAdapter(adapterfiltro);

                        adapter.notifyDataSetChanged();
                        adapterfiltro.notifyDataSetChanged();

                    } else {

                        AlertDialog.Builder msg = new AlertDialog.Builder(NewBuscaCliente.this);
                        msg.setTitle("Alerta");
                        msg.setMessage("Preencha o campo de pesquisa !");
                        msg.setNeutralButton("Ok", null);
                        msg.show();

                        etPesquisa.setFocusable(true);

                    }//fecha if else

                } else if (cbPesquisa.getSelectedItem().toString().equals("Bairro")) {

                    if (!etPesquisa.getText().toString().isEmpty()) {

                        query = " bairro like '%" + etPesquisa.getText().toString() + "%'";

                        cliAux = cDAO.filtrar(query);

                        adapterfiltro = new ClienteAdapterFiltro(getBaseContext(), cliAux);

                        lvClientes.setAdapter(adapterfiltro);

                        adapter.notifyDataSetChanged();
                        adapterfiltro.notifyDataSetChanged();

                    } else {

                        AlertDialog.Builder msg = new AlertDialog.Builder(NewBuscaCliente.this);
                        msg.setTitle("Alerta");
                        msg.setMessage("Preencha o campo de pesquisa !");
                        msg.setNeutralButton("Ok", null);
                        msg.show();

                        etPesquisa.setFocusable(true);

                    }//fecha if else

                } else if (cbPesquisa.getSelectedItem().toString().equals("Cidade")) {

                    if (!etPesquisa.getText().toString().isEmpty()) {

                        query = " cidade like '%" + etPesquisa.getText().toString() + "%'";

                        cliAux = cDAO.filtrar(query);

                        adapterfiltro = new ClienteAdapterFiltro(getBaseContext(), cliAux);

                        lvClientes.setAdapter(adapterfiltro);

                        adapter.notifyDataSetChanged();
                        adapterfiltro.notifyDataSetChanged();

                    } else {

                        AlertDialog.Builder msg = new AlertDialog.Builder(NewBuscaCliente.this);
                        msg.setTitle("Alerta");
                        msg.setMessage("Preencha o campo de pesquisa !");
                        msg.setNeutralButton("Ok", null);
                        msg.show();

                        etPesquisa.setFocusable(true);

                    }//fecha if else

                } else if (cbPesquisa.getSelectedItem().toString().equals("Uf")) {

                    if (!etPesquisa.getText().toString().isEmpty()) {

                        query = " uf like '%" + etPesquisa.getText().toString() + "%'";

                        cliAux = cDAO.filtrar(query);

                        adapterfiltro = new ClienteAdapterFiltro(getBaseContext(), cliAux);

                        lvClientes.setAdapter(adapterfiltro);

                        adapter.notifyDataSetChanged();
                        adapterfiltro.notifyDataSetChanged();

                    } else {

                        AlertDialog.Builder msg = new AlertDialog.Builder(NewBuscaCliente.this);
                        msg.setTitle("Alerta");
                        msg.setMessage("Preencha o campo de pesquisa !");
                        msg.setNeutralButton("Ok", null);
                        msg.show();

                        etPesquisa.setFocusable(true);

                    }//fecha if else

                } else if (cbPesquisa.getSelectedItem().toString().equals("Cep")) {

                    if (!etPesquisa.getText().toString().isEmpty()) {

                        query = " cep like '%" + etPesquisa.getText().toString() + "%'";

                        cliAux = cDAO.filtrar(query);

                        adapterfiltro = new ClienteAdapterFiltro(getBaseContext(), cliAux);

                        lvClientes.setAdapter(adapterfiltro);

                        adapter.notifyDataSetChanged();
                        adapterfiltro.notifyDataSetChanged();

                    } else {

                        AlertDialog.Builder msg = new AlertDialog.Builder(NewBuscaCliente.this);
                        msg.setTitle("Alerta");
                        msg.setMessage("Preencha o campo de pesquisa !");
                        msg.setNeutralButton("Ok", null);
                        msg.show();

                        etPesquisa.setFocusable(true);

                    }//fecha if else

                } else if (cbPesquisa.getSelectedItem().toString().equals("Telefone Fixo")) {

                    if (!etPesquisa.getText().toString().isEmpty()) {

                        query = " telefonefixo = " + etPesquisa.getText().toString();

                        cliAux = cDAO.filtrar(query);

                        adapterfiltro = new ClienteAdapterFiltro(getBaseContext(), cliAux);

                        lvClientes.setAdapter(adapterfiltro);

                        adapter.notifyDataSetChanged();
                        adapterfiltro.notifyDataSetChanged();

                    } else {

                        AlertDialog.Builder msg = new AlertDialog.Builder(NewBuscaCliente.this);
                        msg.setTitle("Alerta");
                        msg.setMessage("Preencha o campo de pesquisa !");
                        msg.setNeutralButton("Ok", null);
                        msg.show();

                        etPesquisa.setFocusable(true);

                    }//fecha if else

                } else if (cbPesquisa.getSelectedItem().toString().equals("Telefone Celular")) {

                    if (!etPesquisa.getText().toString().isEmpty()) {

                        query = " telefonecelular = " + etPesquisa.getText().toString();

                        cliAux = cDAO.filtrar(query);

                        adapterfiltro = new ClienteAdapterFiltro(getBaseContext(), cliAux);

                        lvClientes.setAdapter(adapterfiltro);

                        adapter.notifyDataSetChanged();
                        adapterfiltro.notifyDataSetChanged();

                    } else {

                        AlertDialog.Builder msg = new AlertDialog.Builder(NewBuscaCliente.this);
                        msg.setTitle("Alerta");
                        msg.setMessage("Preencha o campo de pesquisa !");
                        msg.setNeutralButton("Ok", null);
                        msg.show();

                        etPesquisa.setFocusable(true);

                    }//fecha if else

                } else {

                    if (!etPesquisa.getText().toString().isEmpty()) {

                        query = " email like '%" + etPesquisa.getText().toString() + "%'";

                        cliAux = cDAO.filtrar(query);

                        adapterfiltro = new ClienteAdapterFiltro(getBaseContext(), cliAux);

                        lvClientes.setAdapter(adapterfiltro);

                        adapter.notifyDataSetChanged();
                        adapterfiltro.notifyDataSetChanged();

                    } else {

                        /*AlertDialog.Builder msg = new AlertDialog.Builder(New);
                        msg.setTitle("Alerta");
                        msg.setMessage("Preencha o campo de pesquisa !");
                        msg.setNeutralButton("Ok", null);
                        msg.show();

                        etPesquisa.setFocusable(true);*/
                    }//fecha if else
                }//fecha of if
                //etPesquisa.setFocusable(true);
            }//fecha onClick
        });

        btnExportarPDF.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder msg = new AlertDialog.Builder(NewBuscaCliente.this);
                msg.setTitle("Nomeie o arquivo.");
                msg.setMessage("Digite o nome do arquivo.");

                final EditText input = new EditText(getApplicationContext());

                input.setInputType(InputType.TYPE_CLASS_TEXT);
                input.setTextColor(Color.BLACK);

                msg.setView(input);

                msg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String nome = input.getText().toString();

                        ExportarPDF ePDF = new ExportarPDF();

                        try {

                            ePDF.exportarPDF(getApplicationContext(), cliAux, nome);

                            AlertDialog.Builder msg = new AlertDialog.Builder(NewBuscaCliente.this);
                            msg.setTitle("Aviso");
                            msg.setMessage("Arquivo "+nome+".pdf exportado com sucesso!");
                            msg.setNeutralButton("OK", null);
                            msg.show();

                        } catch (IOException | DocumentException ioe) {

                            AlertDialog.Builder msg = new AlertDialog.Builder(NewBuscaCliente.this);
                            msg.setTitle("Alerta");
                            msg.setMessage("Erro: "+ioe.getMessage());
                            msg.setNeutralButton("OK", null);
                            msg.show();

                        }//fecha try-catch

                    }//fecha onClick
                });//fecha setPositiveButton

                msg.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();

                    }//fecha onClick
                });//fecha setNegativeButton

                msg.show();


            }//fecha onClick

        });//fecha setOnClickListener exportar

        btnExportarCSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder msg = new AlertDialog.Builder(NewBuscaCliente.this);
                msg.setTitle("Nomeie o arquivo.");
                msg.setMessage("Digite o nome do arquivo.");

                final EditText input = new EditText(getApplicationContext());

                input.setInputType(InputType.TYPE_CLASS_TEXT);
                input.setTextColor(Color.BLACK);

                msg.setView(input);

                msg.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nome = input.getText().toString();
                        ExportarCSV eCSV = new ExportarCSV();
                        try {
                            eCSV.exportarCSV(getApplicationContext(), cliAux, nome);

                            AlertDialog.Builder msg = new AlertDialog.Builder(NewBuscaCliente.this);
                            msg.setTitle("Aviso");
                            msg.setMessage("Arquivo "+nome+".csv exportado com sucesso!");
                            msg.setNeutralButton("OK", null);
                            msg.show();

                        } catch (IOException ioe) {

                            AlertDialog.Builder msg = new AlertDialog.Builder(NewBuscaCliente.this);
                            msg.setTitle("Alerta");
                            msg.setMessage("Erro!"+ioe.getMessage());
                            msg.setNeutralButton("OK", null);
                            msg.show();
                        }//fecha try-catch
                    }//fecha onClick
                });//fecha setPositiveButton

                msg.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }//fecha onClick
                });//fecha setNegativeButton
                msg.show();
            }//fecha onClick
        });//fecha setOnClickListener
        registerForContextMenu(lvClientes);
    }//fecha onCreate

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v("LOG PERMISSOES","TESTE: Permission is granted");
                return true;
            } else {
                Log.v("LOG PERMISSOES","TESTE: Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("LOG PERMISSOES","TESTE: Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Log.v("LOG PERMISSOES","TESTE CALLBACK Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Menu");

        menu.addSubMenu(DELETAR, DELETAR, 100, "Deletar");
        menu.addSubMenu(ALTERAR , ALTERAR, 200, "Alterar");

    }//fecha onCreateContextMenu

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case DELETAR:
                AlertDialog.Builder msg = new AlertDialog.Builder(NewBuscaCliente.this);
                msg.setTitle("Alerta");
                msg.setMessage("Você tem certeza que deseja excluir?");
                msg.setIcon(R.mipmap.ic_launcher);
                msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //RESGATANDO O CLIENTE SELECIONADO PELO USUaRIO
                        Cliente c = cliAux.get(posSelecionada);

                        //removendo do banco
                        cDAO.excluir(c);

                        //removendo do arrayList
                        cliAux.remove(c);

                        //Mensagem pro usu�rio
                        AlertDialog.Builder msg = new AlertDialog.Builder(NewBuscaCliente.this);
                        msg.setTitle("Aviso");
                        msg.setMessage("Exclusão efetuada com sucesso!");
                        msg.setNeutralButton("Ok", null);
                        msg.show();

                        adapter.notifyDataSetChanged();
                        adapter.notifyDataSetChanged();

                    }//fecha onClick
                });//fecha PositiveButton

                msg.setNeutralButton("Nao", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Mensagem pro usu�rio
                        AlertDialog.Builder msg = new AlertDialog.Builder(NewBuscaCliente.this);
                        msg.setTitle("Alerta");
                        msg.setMessage("Erro ao excluir!");
                        msg.setNeutralButton("Ok", null);
                        msg.show();

                    }//fecha OnClick
                });//fechaNeutralButton

                msg.show();
                break;

            case ALTERAR:

                //Resgatando o Cliente Selecionado pelo usuario
                Cliente c = cliAux.get(posSelecionada);

                //Enviando para a tela de cadastro(alterando)
                Intent it = new Intent(NewBuscaCliente.this, NewCadastroCliente.class);

                it.putExtra("c",c); //N�o esquecer de implementar a classe base
                it.putExtra("acao", "alterar");
                startActivity(it);
                finish();
                break;
        }//fecha switch
        return super.onContextItemSelected(item);

    }//fecha onContextItemSelected

    @Override
    protected void onResume() {
        super.onResume();
        //toda vez que a activity receber o foco, ativamos a conex�o com o banco de dados
        cDAO.abrirBanco();
    }//fecha onResume

    @Override
    protected void onPause() {
        super.onPause();
        //toda vez que a activity perder o foco, fechamos a conex�o com o banco de dados
        cDAO.fecharBanco();
    }//fecha onPause

    private void limparPesquisa(){
        etPesquisa.setText(null);
    }//fecha limparPesquisa
}//fecha classe