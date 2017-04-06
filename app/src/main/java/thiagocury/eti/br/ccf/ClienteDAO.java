package thiagocury.eti.br.ccf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by aluno on 24/12/2015.
 */
public class ClienteDAO {

    private SQLiteDatabase database;
    private BaseDAO dbHelper;

    public ClienteDAO(Context context){
        dbHelper = new BaseDAO(context);
    }//fecha clienteDAO

    public void abrirBanco(){
        database = dbHelper.getWritableDatabase();
    }//fecha abrirBanco

    public void fecharBanco(){
        dbHelper.close();
    }//fecha fecharBanco

    public long inserir(Cliente c){
        ContentValues cv = new ContentValues();

        cv.put(BaseDAO.CLIENTE_NOMECOMPLETO, c.getNome());
        cv.put(BaseDAO.CLIENTE_RG, c.getRg());
        cv.put(BaseDAO.CLIENTE_CPF, c.getCpf());
        cv.put(BaseDAO.CLIENTE_ENDERECO, c.getEndereco());
        cv.put(BaseDAO.CLIENTE_NUMERO, c.getNumeroendereco());
        cv.put(BaseDAO.CLIENTE_BAIRRO, c.getBairro());
        cv.put(BaseDAO.CLIENTE_CIDADE, c.getCidade());
        cv.put(BaseDAO.CLIENTE_UF, c.getUf());
        cv.put(BaseDAO.CLIENTE_CEP, c.getCep());
        cv.put(BaseDAO.CLIENTE_TELEFONEFIXO, c.getTelefonefixo());
        cv.put(BaseDAO.CLIENTE_TELEFONECELULAR, c.getTelefonecelular());
        cv.put(BaseDAO.CLIENTE_EMAIL, c.getEmail());
        cv.put(BaseDAO.CLIENTE_OBS, c.getObs());

        return database.insert(BaseDAO.TBL_CLIENTE, null, cv);
    }//fecha inserir

    public long alterar(Cliente c){

        long id = c.getIdcliente();

        ContentValues cv = new ContentValues();
        cv.put(BaseDAO.CLIENTE_ID, c.getIdcliente());
        cv.put(BaseDAO.CLIENTE_NOMECOMPLETO, c.getNome());
        cv.put(BaseDAO.CLIENTE_RG, c.getRg());
        cv.put(BaseDAO.CLIENTE_CPF, c.getCpf());
        cv.put(BaseDAO.CLIENTE_ENDERECO, c.getEndereco());
        cv.put(BaseDAO.CLIENTE_NUMERO, c.getNumeroendereco());
        cv.put(BaseDAO.CLIENTE_BAIRRO, c.getBairro());
        cv.put(BaseDAO.CLIENTE_CIDADE, c.getCidade());
        cv.put(BaseDAO.CLIENTE_UF,c.getUf());
        cv.put(BaseDAO.CLIENTE_CEP,c.getCep());
        cv.put(BaseDAO.CLIENTE_TELEFONEFIXO,c.getTelefonefixo());
        cv.put(BaseDAO.CLIENTE_TELEFONECELULAR,c.getTelefonecelular());
        cv.put(BaseDAO.CLIENTE_EMAIL, c.getEmail());
        cv.put(BaseDAO.CLIENTE_OBS, c.getObs());

        return database.update(
                BaseDAO.TBL_CLIENTE,
                cv,
                BaseDAO.CLIENTE_ID + "=?",
                new String[]{String.valueOf(id)
                }

        );

}//fecha alterar


    public int excluir(Cliente c){

        long id = c.getIdcliente();

        return database.delete(
                BaseDAO.TBL_CLIENTE,
                BaseDAO.CLIENTE_ID+"=?",
                new String[]{String.valueOf(id)}

        );

    }//fecha excluir

    public ArrayList<Cliente> consultar(){

        ArrayList<Cliente> cliAux = new ArrayList<>();

        Cursor cursor = database.query(
                BaseDAO.TBL_CLIENTE,
                BaseDAO.TBL_CLIENTE_COLUNAS,
                null,
                null,
                null,
                null,
                BaseDAO.CLIENTE_ID // order by
        );

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){

            Cliente c = new Cliente();
            c.setIdcliente(cursor.getLong(0));
            c.setNome(cursor.getString(1));
            c.setRg(cursor.getString(2));
            c.setCpf(cursor.getString(3));
            c.setEndereco(cursor.getString(4));
            c.setNumeroendereco(cursor.getString(5));
            c.setBairro(cursor.getString(6));
            c.setCidade(cursor.getString(7));
            c.setUf(cursor.getString(8));
            c.setCep(cursor.getString(9));
            c.setTelefonefixo(cursor.getString(10));
            c.setTelefonecelular(cursor.getString(11));
            c.setEmail(cursor.getString(12));
            c.setObs(cursor.getString(13));
            cursor.moveToNext();
            cliAux.add(c);
        }//fecha while
        cursor.close();
        return cliAux;
    }//fecha consultar

    public ArrayList<Cliente> filtrar(String query){

        ArrayList<Cliente> cliAux = new ArrayList<>();

        Cursor cursor = database.query(
                BaseDAO.TBL_CLIENTE,
                BaseDAO.TBL_CLIENTE_COLUNAS,
                query,
                null,
                null,
                null,
                BaseDAO.CLIENTE_ID

        );

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){

            Cliente c = new Cliente();
            c.setIdcliente(cursor.getLong(0));
            c.setNome(cursor.getString(1));
            c.setRg(cursor.getString(2));
            c.setCpf(cursor.getString(3));
            c.setEndereco(cursor.getString(4));
            c.setNumeroendereco(cursor.getString(5));
            c.setBairro(cursor.getString(6));
            c.setCidade(cursor.getString(7));
            c.setUf(cursor.getString(8));
            c.setCep(cursor.getString(9));
            c.setTelefonefixo(cursor.getString(10));
            c.setTelefonecelular(cursor.getString(11));
            c.setEmail(cursor.getString(12));
            c.setObs(cursor.getString(13));
            cursor.moveToNext();
            cliAux.add(c);
        }//fecha while
        cursor.close();
        return cliAux;
    }//fecha filtrar
}//fecha classe