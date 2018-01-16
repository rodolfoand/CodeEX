package sp.senai.br.android_rv_notify.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import sp.senai.br.android_rv_notify.model.Jogo;

/**
 * Created by adminLocal on 10/01/2018.
 */

public class JogoDao {

    private SQLiteDatabase db;
    private JogoDbHelper dbo;



    public JogoDao(Context context) {
        dbo = new JogoDbHelper(context);
    }

    public List<Jogo> getLista() {
        List<Jogo> jogos = new LinkedList<>();
        String rawQuery = "SELECT _id, nome, fabricante FROM " + JogoDbHelper.TABELA;
        SQLiteDatabase db = dbo.getReadableDatabase();
        Cursor cursor = db.rawQuery(rawQuery, null);
        Jogo jogo = null;
        if (cursor.moveToFirst()){
            do {
                jogo = new Jogo();
                jogo.setId(cursor.getLong(0));
                jogo.setNome(cursor.getString(1));
                jogo.setFabricante(cursor.getString(2));
                jogos.add(jogo);
            } while (cursor.moveToNext());
        }
        db.close();
        return jogos;

    }

    public void remover(Jogo jogo) {
        SQLiteDatabase db = dbo.getWritableDatabase();
        String delete = "delete from " + JogoDbHelper.TABELA + "where _id = ?";
        db.execSQL(delete, new Object[]{jogo.getId()});
        db.close();
    }

    public void salvar(Jogo obj) {

        SQLiteDatabase db = dbo.getWritableDatabase();

        long resultado;
        ContentValues values = new ContentValues();
        values.put(JogoDbHelper.NOME, obj.getNome());
        values.put(JogoDbHelper.FABRICANTE, obj.getFabricante());
        resultado = db.insert(JogoDbHelper.TABELA, null, values);
        db.close();


    }

    public Jogo localizar(Long id) {
        SQLiteDatabase db = dbo.getWritableDatabase();
        String query = "SELECT _id, nome, fabricante from " + JogoDbHelper.TABELA + " where _id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});
        Jogo jogo = new Jogo();
        if  (cursor.moveToFirst()){
            jogo.setId(cursor.getLong(0));
            jogo.setNome(cursor.getString(1));
            jogo.setFabricante(cursor.getString(2));
        }
        db.close();
        return jogo;
    }

}
