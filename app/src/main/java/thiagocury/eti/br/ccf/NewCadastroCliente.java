package thiagocury.eti.br.ccf;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class NewCadastroCliente extends AppCompatActivity {

    //Alert Dialog Personalizado
    final Context context = this;
    private Button button;

    //tretas do xml
    private EditText etIdCliente;
    private EditText etNome;
    private EditText etRg;
    private EditText etCpf;
    private EditText etEndereco;
    private EditText etNumero;
    private EditText etBairro;
    private EditText etCidade;
    private EditText etUf;
    private EditText etCep;
    private EditText etTelefoneFixo;
    private EditText etTelefoneCelular;
    private EditText etEmail;
    private EditText etObs;
    private Button btnCadastrar;
    private Button btnAlterar;

    private Cliente c;

    //banco
    private ClienteDAO cDAO;

    //valicação

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cadastro_cliente);

        etIdCliente = (EditText) findViewById(R.id.etIdCliente);
        etNome = (EditText)findViewById(R.id.etNomeCompleto);
        etRg = (EditText)findViewById(R.id.etRg);
        etCpf = (EditText)findViewById(R.id.etCpf);
        etEndereco = (EditText)findViewById(R.id.etEndereco);
        etNumero = (EditText)findViewById(R.id.etNumero);
        etBairro = (EditText)findViewById(R.id.etBairro);
        etCidade = (EditText)findViewById(R.id.etCidade);
        etUf = (EditText)findViewById(R.id.etUf);
        etCep = (EditText)findViewById(R.id.etCep);
        etTelefoneFixo = (EditText)findViewById(R.id.etTelefoneFixo);
        etTelefoneCelular = (EditText)findViewById(R.id.etTelefoneCelular);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etObs = (EditText)findViewById(R.id.etObs);
        btnCadastrar = (Button)findViewById(R.id.btnCadastrar);
        btnAlterar = (Button)findViewById(R.id.btnAlterar);

        btnAlterar.setVisibility(View.INVISIBLE);
        etIdCliente.setVisibility(View.INVISIBLE);

        //Propaganda
        AdView mAdView = (AdView) findViewById(R.id.adViewTelaCadastro);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //abrindo o banco
        cDAO = new ClienteDAO(this);
        cDAO.abrirBanco();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                    c = new Cliente();

                    if(Validacao.verificarNome(etNome.getText().toString())){
                        c.setNome(etNome.getText().toString());
                    }else{
                        throw new Exception("\nNome inválido !");
                    }//fecha if else

                    c.setRg(etRg.getText().toString());

                    if(Validacao.verificarCpf(etCpf.getText().toString())){
                        c.setCpf(etCpf.getText().toString());
                    }else{
                        throw new Exception("\nCpf inválido !");
                    }//fecha if else

                    c.setEndereco(etEndereco.getText().toString());

                    if(Validacao.verificarNumero(etNumero.getText().toString())){
                        c.setNumeroendereco(etNumero.getText().toString());
                    }else{
                        throw new Exception("\nNúmero inválido !");
                    }//fecha if else

                    c.setBairro(etBairro.getText().toString());

                    c.setCidade(etCidade.getText().toString());

                    if(Validacao.verificarEstado(Validacao.converterMaiuscula(etUf.getText().toString()))){
                        c.setUf(etUf.getText().toString());
                    }else{
                        throw new Exception("\nEstado inválido !");
                    }//fecha if else

                    c.setCep(etCep.getText().toString());

                    if(Validacao.verificarTelefone(etTelefoneFixo.getText().toString())){
                        c.setTelefonefixo(etTelefoneFixo.getText().toString());
                    }else{
                        throw new Exception("\nTelefone Fixo inválido !");
                    }//fecha if else

                    if(Validacao.verificarTelefone(etTelefoneCelular.getText().toString())){
                        c.setTelefonecelular(etTelefoneCelular.getText().toString());
                    }else{
                        throw new Exception("\nTelefone Celular inválido !");
                    }//fecha if else

                    if(Validacao.verificarEmail(etEmail.getText().toString())){
                        c.setEmail(etEmail.getText().toString());
                    }else{
                        throw new Exception("\nEmail inválido !");
                    }//fecha if else

                    c.setObs(etObs.getText().toString());

                    AlertDialog.Builder msg = new AlertDialog.Builder(NewCadastroCliente.this);
                    msg.setTitle("Sucesso");
                    msg.setMessage("Cadastro efetuado com sucesso!");
                    msg.setNeutralButton("OK", null);
                    msg.show();

                    /*Toast.makeText(
                            getBaseContext(),
                            "Cadastrado efetuado com sucesso!",
                            Toast.LENGTH_LONG
                    ).show();*/

                    //Enviando para o método cadastrar
                    cDAO.inserir(c);
                    limpar();

                }catch(Exception e){

                    AlertDialog.Builder msg = new AlertDialog.Builder(NewCadastroCliente.this);
                    msg.setTitle("Erro ao cadastrar");
                    msg.setMessage("Houve o seguinte erro: " + e.getMessage());
                    msg.setNeutralButton("Ok", null);
                    msg.show();

                }//fecha try-catch

            }//fecha onclick
        });//fecha setOnClickListener


        //Alterar
        String acao = getIntent().getStringExtra("acao");

        if(acao != null){

            btnAlterar.setVisibility(View.VISIBLE);
            btnCadastrar.setVisibility(View.INVISIBLE);
            etIdCliente.setVisibility(View.VISIBLE);
            etIdCliente.setEnabled(false);

            Cliente c = (Cliente) getIntent().getSerializableExtra("c");
            etIdCliente.setText(String.valueOf(c.getIdcliente()));
            etNome.setText(String.valueOf(c.getNome()));
            etRg.setText(String.valueOf(c.getRg()));
            etCpf.setText(String.valueOf(c.getCpf()));
            etEndereco.setText(String.valueOf(c.getEndereco()));
            etNumero.setText(String.valueOf(c.getNumeroendereco()));
            etBairro.setText(String.valueOf(c.getBairro()));
            etCidade.setText(String.valueOf(c.getCidade()));
            etUf.setText(String.valueOf(c.getUf()));
            etCep.setText(String.valueOf(c.getCep()));
            etTelefoneFixo.setText(String.valueOf(c.getTelefonefixo()));
            etTelefoneCelular.setText(String.valueOf(c.getTelefonecelular()));
            etEmail.setText(String.valueOf(c.getEmail()));
            etObs.setText(String.valueOf(c.getObs()));

        }//fecha if

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c = new Cliente();

                c.setIdcliente(Long.parseLong(etIdCliente.getText().toString()));

                try{
                    if(Validacao.verificarNome(etNome.getText().toString())){
                        c.setNome(etNome.getText().toString());
                    }else{
                        throw new Exception("\nNome inválido !");
                    }//fecha if else

                    c.setRg(etRg.getText().toString());

                    if(Validacao.verificarCpf(etCpf.getText().toString())){
                        c.setCpf(etCpf.getText().toString());
                    }else{
                        throw new Exception("\nCpf inválido !");
                    }//fecha if else

                    c.setEndereco(etEndereco.getText().toString());

                    if(Validacao.verificarNumero(etNumero.getText().toString())){
                        c.setNumeroendereco(etNumero.getText().toString());
                    }else{
                        throw new Exception("\nNúmero inválido !");
                    }//fecha if else

                    c.setBairro(etBairro.getText().toString());

                    c.setCidade(etCidade.getText().toString());

                    if(Validacao.verificarEstado(Validacao.converterMaiuscula(etUf.getText().toString()))){
                        c.setUf(etUf.getText().toString());
                    }else{
                        throw new Exception("\nEstado inválido !");
                    }//fecha if else

                    c.setCep(etCep.getText().toString());

                    if(Validacao.verificarTelefone(etTelefoneFixo.getText().toString())){
                        c.setTelefonefixo(etTelefoneFixo.getText().toString());
                    }else{
                        throw new Exception("\nTelefone Fixo inválido !");
                    }//fecha if else

                    if(Validacao.verificarTelefone(etTelefoneCelular.getText().toString())){
                        c.setTelefonecelular(etTelefoneCelular.getText().toString());
                    }else{
                        throw new Exception("\nTelefone Celular inválido !");
                    }//fecha if else

                    if(Validacao.verificarEmail(etEmail.getText().toString())){
                        c.setEmail(etEmail.getText().toString());
                    }else{
                        throw new Exception("\nEmail inválido !");
                    }//fecha if else

                    c.setObs(etObs.getText().toString());

                    AlertDialog.Builder msg = new AlertDialog.Builder(NewCadastroCliente.this);
                    msg.setTitle("Sucesso");
                    msg.setMessage("Alteração efetuada com sucesso!");
                    msg.setNeutralButton("OK", null);
                    msg.show();

                    /*Toast.makeText(
                            getBaseContext(),
                            "Alterado com sucesso. ",
                            Toast.LENGTH_LONG
                    ).show();*/

                    cDAO.alterar(c);
                    limpar();

                    btnAlterar.setVisibility(View.INVISIBLE);
                    btnCadastrar.setVisibility(View.VISIBLE);
                    etIdCliente.setVisibility(View.INVISIBLE);

                }catch(Exception e){

                    AlertDialog.Builder msg = new AlertDialog.Builder(NewCadastroCliente.this);
                    msg.setTitle("Erro ao alterar");
                    msg.setMessage("Houve o seguinte erro: " + e.getMessage());
                    msg.setNeutralButton("Ok", null);
                    msg.show();

                }//fecha try-catch

            }//fecha onclick

        });//fecha alterar

    }//fecha oncreate

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //Toda vez que a Activity receber o foco, ativamos a conexão com o bd
        cDAO.abrirBanco();
    }//fecha onResume

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        //Toda vez que a Activity perder o foco, encerramos a conexão com o bd
        cDAO.fecharBanco();
    }//fecha onPause

    private void limpar(){
        etNome.setText(null);
        etRg.setText(null);
        etCpf.setText(null);
        etEndereco.setText(null);
        etNumero.setText(null);
        etBairro.setText(null);
        etCidade.setText(null);
        etUf.setText(null);
        etCep.setText(null);
        etTelefoneFixo.setText(null);
        etTelefoneCelular.setText(null);
        etEmail.setText(null);
        etObs.setText(null);
        etIdCliente.setText(null);
    }//fecha limpar
}//fecha classe
