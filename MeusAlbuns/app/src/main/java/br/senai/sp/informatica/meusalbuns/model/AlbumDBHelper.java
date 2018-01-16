package br.senai.sp.informatica.meusalbuns.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 34023325821 on 16/01/2018.
 */

public class AlbumDBHelper extends SQLiteOpenHelper{
    private static final String NOME_BANCO = "album.db";
    private static final String TABELA = "album";
    private static final int VERSAO = 1;
    private static final String ID = "_id";

    public AlbumDBHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String criarTabela = "CREATE TABLE "+ TABELA
                + " (" + ID + " integer primary key autoincrement";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
