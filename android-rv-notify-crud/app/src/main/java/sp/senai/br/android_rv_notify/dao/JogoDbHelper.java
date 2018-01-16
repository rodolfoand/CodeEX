package sp.senai.br.android_rv_notify.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 34023325821 on 16/01/2018.
 */

public class JogoDbHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "dbjogos.db";
    public static final String TABELA = "jogos";
    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String FABRICANTE = "fabricante";
    private static final int VERSAO = 1;

    public JogoDbHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String criarDB = "CREATE TABLE " + TABELA + " ("
                + ID + "integer primary key autoincrement ,"
                + NOME + "text,"
                + FABRICANTE + "text)";
        db.execSQL(criarDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
