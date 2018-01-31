package com.example.rodol.android_meusalbuns_sql.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.rodol.android_meusalbuns_sql.model.Album;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by rodol on 17/01/2018.
 */

public class AlbumDao {

    private SQLiteDatabase db;
    private AlbumDBHelper dbo;

    private final static DateFormat fmt = DateFormat.getDateInstance(DateFormat.SHORT);
    private final SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yy");

    public AlbumDao(Context context) {
        dbo = new AlbumDBHelper(context);
    }

    public List<Album> getList(String ordem){
        List<Album> albuns = new LinkedList<>();
        String rQuery = "SELECT _id, artista, nome, genero, dt_lancamento, ativo FROM " + dbo.TABELA;
                //+ " WHERE ativo = ?";
        db = dbo.getReadableDatabase();
        Cursor cursor = db.rawQuery(rQuery, null);
        Album album;
        while (cursor.moveToNext()){
            album = new Album();
            album.setId(cursor.getLong(0));
            album.setArtista(cursor.getString(1));
            album.setNome(cursor.getString(2));
            album.setGenero(cursor.getString(3));
            Calendar calendar = Calendar.getInstance();
            try {
                calendar.setTime(fmt.parse(cursor.getString(4)));
            } catch (ParseException e) {}
            album.setDtLancamento(calendar.getTime());
            //TODO: Preencher ativo, e capa
            albuns.add(album);
        }
        db.close();

        switch (ordem){
            case "Artista":
                Collections.sort(albuns);
                break;
            case "Album":
                //TODO: Ajustar sort por Album
                Collections.sort(albuns);
                break;
            default:

        }
        return albuns;
    }

    public Album getAlbum(Long id){
        String rQuery = "SELECT _id, artista, nome, genero, dt_lancamento, ativo FROM " +
                dbo.TABELA + " WHERE _id = ?";
        db = dbo.getReadableDatabase();
        Cursor cursor = db.rawQuery(rQuery, new String[]{String.valueOf(id)});
        Album album = null;
        if (cursor.moveToFirst()){
            album = new Album();
            album.setId(cursor.getLong(0));
            album.setArtista(cursor.getString(1));
            album.setNome(cursor.getString(2));
            album.setGenero(cursor.getString(3));
            Calendar calendar = Calendar.getInstance();
            try {
                /*
                Log.d("AlbumTeste:", "cursor: " + cursor.getString(4));
                Log.d("AlbumTeste:", "fmt: " + fmt.parse(cursor.getString(4)).toString());
                Log.d("AlbumTeste:", "parser: " + parser.parse(cursor.getString(4)).toString());
                */
                calendar.setTime(fmt.parse(cursor.getString(4)));
            } catch (ParseException e) {}
            album.setDtLancamento(calendar.getTime());
            album.setAtivo(cursor.getString(5).equals("1"));
            //TODO: Preencher ativo e capa
        }
        db.close();
        return album;
    }

    public void salvar(Album album){
        db = dbo.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dbo.ARTISTA, album.getArtista());
        values.put(dbo.NOME, album.getNome());
        values.put(dbo.GENERO, album.getGenero());
        values.put(dbo.DT_LANCAMENTO, parser.format(album.getDtLancamento()));
        values.put(dbo.ATIVO, album.isAtivo());
        //TODO: Salvar ativo e capa
        if (album.getId() == null){
            db.insert(dbo.TABELA, null, values);
        } else {
            db.update(dbo.TABELA, values, "_id = ?", new String[]{String.valueOf(album.getId())});
        }
        db.close();
    }

}
