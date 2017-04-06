package thiagocury.eti.br.ccf;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by aluno on 24/12/2015.
 */
public class BaseDAO extends SQLiteOpenHelper{

    //tabela
    public static final String TBL_CLIENTE = "cliente";
    public static final String CLIENTE_ID = "idcliente";
    public static final String CLIENTE_NOMECOMPLETO = "nomecompleto";
    public static final String CLIENTE_RG = "rg";
    public static final String CLIENTE_CPF = "cpf";
    public static final String CLIENTE_ENDERECO = "endereco";
    public static final String CLIENTE_NUMERO = "numero";
    public static final String CLIENTE_BAIRRO = "bairro";
    public static final String CLIENTE_CIDADE = "cidade";
    public static final String CLIENTE_UF = "uf";
    public static final String CLIENTE_CEP = "cep";
    public static final String CLIENTE_TELEFONEFIXO = "telefonefixo";
    public static final String CLIENTE_TELEFONECELULAR = "telefonecelular";
    public static final String CLIENTE_EMAIL = "email";
    public static final String CLIENTE_OBS = "obs";

    /*Colunas da tabela CLIENTE. São publicas para qualquer classe*/
    public static final String[] TBL_CLIENTE_COLUNAS = {
            BaseDAO.CLIENTE_ID,
            BaseDAO.CLIENTE_NOMECOMPLETO,
            BaseDAO.CLIENTE_RG,
            BaseDAO.CLIENTE_CPF,
            BaseDAO.CLIENTE_ENDERECO,
            BaseDAO.CLIENTE_NUMERO,
            BaseDAO.CLIENTE_BAIRRO,
            BaseDAO.CLIENTE_CIDADE,
            BaseDAO.CLIENTE_UF,
            BaseDAO.CLIENTE_CEP,
            BaseDAO.CLIENTE_TELEFONEFIXO,
            BaseDAO.CLIENTE_TELEFONECELULAR,
            BaseDAO.CLIENTE_EMAIL,
            BaseDAO.CLIENTE_OBS
    };

    //BANCO + NOME + VERSAO
    public static final String DATABASE_NAME = "cliente.sqlite";
    public static final int DATABASE_VERSION = 2;

    //DDL - CRIAÇÃO DA TABELA
    public static final String CREATE_CLIENTE =
            "create table "+TBL_CLIENTE+"("+
                    CLIENTE_ID+ " integer primary key autoincrement, "+
                    CLIENTE_NOMECOMPLETO+ " text not null , " +
                    CLIENTE_RG+ " text not null , " +
                    CLIENTE_CPF+ " text not null , " +
                    CLIENTE_ENDERECO+ " text , "+
                    CLIENTE_NUMERO+ " text , "+
                    CLIENTE_BAIRRO+ " text , "+
                    CLIENTE_CIDADE+ " text , "+
                    CLIENTE_UF+" text , "+
                    CLIENTE_CEP+ " text , "+
                    CLIENTE_TELEFONEFIXO+" text , "+
                    CLIENTE_TELEFONECELULAR+" text , "+
                    CLIENTE_EMAIL+" text , "+
                    CLIENTE_OBS+" text);";

    //DDL - EXCLUSÃO DA TABELA
    public static final String DROP_CLIENTE =
            "drop table if exists " + TBL_CLIENTE;

    //DDL - ALTERAÇÃO DA TABELA
    public static final String ALTER_TABLE_RG =
            "ALTER TABLE "+TBL_CLIENTE+" ADD COLUMN "+CLIENTE_RG +" text before "+CLIENTE_CPF;

    public static final String ALTER_TABLE_OBS =
            "ALTER TABLE "+TBL_CLIENTE+" ADD COLUMN "+CLIENTE_OBS +" text after "+CLIENTE_EMAIL;

    public BaseDAO(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        /*Criando tabela*/
        db.execSQL(CREATE_CLIENTE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        if(newVersion == 2) {
            //acrescentando duas colunas
            db.execSQL(ALTER_TABLE_RG);
            db.execSQL(ALTER_TABLE_OBS);
        }
        //onCreate(db);
    }
}//fecha baseDAO