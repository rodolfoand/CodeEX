package br.senai.sp.informatica.albunsmusicais.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import br.senai.sp.informatica.albunsmusicais.Main;
import br.senai.sp.informatica.albunsmusicais.lib.JSONParser;
import br.senai.sp.informatica.albunsmusicais.lib.UtilJson;

/**
 * Created by pena on 18/11/2017.
 */

public class AlbumDao extends SQLiteOpenHelper {

    public static AlbumDao instance = new AlbumDao();

    public int code;
    public String json;
    private String url = "http://172.16.2.250:8080/AlbumServer/";

    private AlbumDao() {
        super(Main.getContext(), "DbAlbum", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table album (" +
                "id integer primary key autoincrement," +
                "banda text not null," +
                "album text not null," +
                "genero text not null," +
                "lancamento long not null," +
                "del int not null," +
                "capa blob)"
//                "delA int not null, " +
//                "delB int not null)"
       );
    }

    /*
       Os campos comentados servem de exemplo para demonstrar como proceder
       nos casos de atualização do Banco de Dados para a aplicação.
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
//                db.execSQL("alter table album add column delA int not null");
            case 2:
//                db.execSQL("alter table album add column delB int not null");
        }

    }

    private void setData(SQLiteStatement sql, Album obj) {
        sql.bindString(1, obj.getBanda());
        sql.bindString(2, obj.getAlbum());
        sql.bindString(3, obj.getGenero());
        sql.bindLong(4, obj.getLancamento().getTime());
        sql.bindLong(5, (obj.isDel() ? 1 : 0));
        sql.bindBlob(6, obj.getCapa() != null ? obj.getCapa() : new byte[] {});
    }

    private Album getData(Cursor cursor) {
        Album obj = new Album();
        obj.setId(cursor.getLong(0));
        obj.setBanda(cursor.getString(1));
        obj.setAlbum(cursor.getString(2));
        obj.setGenero(cursor.getString(3));
        obj.setLancamento(new Date(cursor.getLong(4)));
        obj.setDel(cursor.getLong(5) == 1);
        obj.setCapa(cursor.getBlob(6).length > 0 ? cursor.getBlob(6) : null);
        return obj;
    }

    public void salvar(Album obj) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(obj);
            if (obj.getId() == null) {

                new JSONParser.Incluir(url, json, new JSONParser.LocationAndDataCallBack() {
                    @Override
                    public void setResponse(int code, String location, String json) {
                        Log.d("AlbumResponse", "code: " + String.valueOf(code));
                    }
                }).execute();
            } else {
                new JSONParser.Alterar(url + "album/" + obj.getId(), json, new JSONParser.LocationCallBack() {
                    @Override
                    public void setResponse(int code, String location) {
                        Log.d("AlbumResponse", "code: " + String.valueOf(code));
                    }
                }).execute();
            }
        } catch (Exception e){Log.d("Album", "Erro ao incluir.");}
    }

    public void remover(Long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from produto where id=" + id);
        db.close();
    }

    public List<Long> listarIds(String ordem) {
        List<Long> lista = new ArrayList<>();
        try {
            String json = new JSONParser.Consultar(url + "lista/" + ordem.toLowerCase(), new JSONParser.DataCallBack() {
                @Override
                public void setResponse(int code, String json) {

                }
            }).execute().get();
            ObjectMapper mapper = new ObjectMapper();
            lista = Arrays.asList(mapper.readValue(new StringReader(json), Long[].class));
        } catch (Exception e) {Log.d("Album", "Erro ao listar os IDs");}
        return lista;
    }

    public Album localizar(Long id) {
        Album album = null;

        try{
            //String json = leJson(url + "album/" + id);

            String json = new JSONParser.Consultar(url + "album/" + id, new JSONParser.DataCallBack() {
                @Override
                public void setResponse(int code, String json) {

                }
            }).execute().get();



            if (!json.isEmpty()){
                ObjectMapper mapper = new ObjectMapper();
                album = mapper.readValue(new StringReader(json), Album.class);
            }
        } catch (Exception e){}

        return album;
    }

    public void removerMarcados() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from album where del = 1");
        db.close();
    }

    public boolean existeAlbunsADeletar() {
        boolean existe = false;

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select count(*) " +
                "from album where del = 1", null);
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            if(cursor.getInt(0) > 0)
                existe = true;

            cursor.close();
        }

        db.close();

        return existe;
    }

    public void limpaMarcados() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("update album set del = 0 where del = 1");
        db.close();
    }

    public String leJson(final String url){
        code = HttpURLConnection.HTTP_INTERNAL_ERROR;
        json = null;

        try {
            HttpURLConnection connection = UtilJson.configConnection(url);

            if (connection != null){
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();

                code = connection.getResponseCode();

                if (code == HttpURLConnection.HTTP_OK){
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    json = UtilJson.readJson(in);
                    in.close();
                }
                connection.disconnect();
            }

        } catch (IOException e){}
        return json;
    }
}
