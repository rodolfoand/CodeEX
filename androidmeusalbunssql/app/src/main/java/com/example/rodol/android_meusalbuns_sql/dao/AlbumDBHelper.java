package com.example.rodol.android_meusalbuns_sql.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rodol on 17/01/2018.
 */

public class AlbumDBHelper extends SQLiteOpenHelper{

    private static final String NOME_BANCO = "album.db";
    public static final String TABELA = "album";

    public static final String ID = "_id";
    public static final String ARTISTA = "artista";
    public static final String NOME = "nome";
    public static final String GENERO = "genero";
    public static final String DT_LANCAMENTO = "dt_lancamento";
    public static final String ATIVO = "ativo";
    public static final String CAPA = "capa";

    private static final int VERSAO = 1;

    public AlbumDBHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String criarTabela = "CREATE TABLE "+ TABELA + " (" +
                ID + " integer primary key autoincrement," +
                ARTISTA + " text," +
                NOME + " text," +
                GENERO + " text," +
                DT_LANCAMENTO + " date," +
                ATIVO + " boolean," +
                CAPA +" blob)";
        db.execSQL(criarTabela);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABELA);
        onCreate(db);
    }
}
