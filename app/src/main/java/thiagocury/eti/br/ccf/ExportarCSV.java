package thiagocury.eti.br.ccf;


import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import au.com.bytecode.opencsv.CSVWriter;

/**
 * Created by mauriciormp on 19/02/2016.
 */
public class ExportarCSV {

    public void exportarCSV(Context context, ArrayList<Cliente> clientes, String nome) throws IOException{

        //montando data atual
        DateFormat data = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
        data.setLenient(false);
        Date hoje = new Date();
        String dataHoje = data.format(hoje);

        //montando o arquivo
        //String csv = context.getFilesDir().getPath() + "/Folder/" +nome+"-"+dataHoje+".csv";
        //String csv = Environment.getExternalStorageDirectory() + "/Folder/" +nome+"-"+dataHoje+".csv";
        //String csv = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/Folder/" +nome+"-"+dataHoje+".csv";

        String csv = Environment.getExternalStorageDirectory() + "/" +nome+"-"+dataHoje+".csv";

        CSVWriter writer = new CSVWriter(new FileWriter(csv));

            try{
                List<String[]> dados = new ArrayList<String[]>();
                ArrayList<Cliente> cli = clientes;
                dados.add(new String[] {"Codigo", "Nome", "Rg", "Cpf", "Endereco", "Numero", "Bairro", "Cidade", "Uf", "Cep", "Telefone Fixo", "Telefone Celular", "Email"});

                //preenchendo o arquivo
                for(int i = 0; i < cli.size(); i++){

                    dados.add(new String[] {
                            String.valueOf(cli.get(i).getIdcliente()),
                            String.valueOf(cli.get(i).getNome()),
                            String.valueOf(cli.get(i).getRg()),
                            String.valueOf(cli.get(i).getCpf()),
                            String.valueOf(cli.get(i).getEndereco()),
                            String.valueOf(cli.get(i).getNumeroendereco()),
                            String.valueOf(cli.get(i).getBairro()),
                            String.valueOf(cli.get(i).getCidade()),
                            String.valueOf(cli.get(i).getUf()),
                            String.valueOf(cli.get(i).getCep()),
                            String.valueOf(cli.get(i).getTelefonefixo()),
                            String.valueOf(cli.get(i).getTelefonecelular()),
                            String.valueOf(cli.get(i).getEmail()),
                            String.valueOf(cli.get(i).getObs())
                    });
                }//fecha for
                writer.writeAll(dados);
                writer.close();
            }catch(IOException ioe){
                System.out.println("Erro: " + ioe.getMessage());
            }//fecha try-catch
    }//fecha exportarCSV
}//fecha classe