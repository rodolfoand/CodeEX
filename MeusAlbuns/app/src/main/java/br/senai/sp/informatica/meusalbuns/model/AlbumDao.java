package br.senai.sp.informatica.meusalbuns.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 34023325821 on 16/11/2017.
 */

public class AlbumDao {
    public static AlbumDao manager = new AlbumDao();
    private List<Album> lista;
    private long id = 0;
    private String ordem = "Banda";

    private AlbumDao(){
        lista = new ArrayList<>();

        //Criação de albuns para apresentação
        //TODO: ajustar criação de capa
        lista.add(new Album(id++, "Novo Album", id + "Novo Artista", "Novo Genero", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album2", id + "Novo Artista", "Novo Genero2", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album3", id + "Novo Artista", "Novo Genero3", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album4", id + "Novo Artista", "Novo Genero4", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album", id + "Novo Artista", "Novo Genero", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album2", id + "Novo Artista", "Novo Genero2", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album3", id + "Novo Artista", "Novo Genero3", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album4", id + "Novo Artista", "Novo Genero4", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album", id + "Novo Artista", "Novo Genero", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album2", id + "Novo Artista", "Novo Genero2", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album3", id + "Novo Artista", "Novo Genero3", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album4", id + "Novo Artista", "Novo Genero4", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album", id + "Novo Artista", "Novo Genero", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album2", id + "Novo Artista", "Novo Genero2", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album3", id + "Novo Artista", "Novo Genero3", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album4", id + "Novo Artista", "Novo Genero4", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album", id + "Novo Artista", "Novo Genero", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album2", id + "Novo Artista", "Novo Genero2", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album3", id + "Novo Artista", "Novo Genero3", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album4", id + "Novo Artista", "Novo Genero4", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album", id + "Novo Artista", "Novo Genero", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album2", id + "Novo Artista", "Novo Genero2", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album3", id + "Novo Artista", "Novo Genero3", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album4", id + "Novo Artista", "Novo Genero4", Calendar.getInstance().getTime(), true));

    }
    public List<Album> getLista(String ordem){
        for (int i = 0; i < lista.size(); i++) {
            if (!lista.get(i).isAtivo()){
                lista.remove(i);
            }
        }
        switch (ordem) {
            case "Banda":
                Collections.sort(lista);
                break;
            case "Album":
                Collections.sort(lista, new ordenaPorAlbum());
                break;
            default:
                Collections.unmodifiableList(lista);
        }
        return lista;
    }
    public Album getAlbum(Long id){
        Album album = null;
        for (Album obj : lista) {
            if (obj.getId() == id){
                album = obj;
            }
        }
        return album;
    }
    public void salvar(Album album){
        if (album.getId() == null){
            album.setId(id++);
            album.setAtivo(true);
            lista.add(album);
        } else {
            int i = lista.indexOf(new Album(album.getId()));
            lista.set(i, album);
        }
    }

    public void retiraSelecionados(List<Long> listaSelecionados) {
        Album album = null;
        for (Long id : listaSelecionados) {
            album = this.getAlbum(id);
            album.setAtivo(!album.isAtivo());
        }
        this.getLista(ordem);
    }

   
    class ordenaPorAlbum implements Comparator<Album>{

        @Override
        public int compare(Album o1, Album o2) {
            return o1.getAlbum().compareToIgnoreCase(o2.getAlbum());
        }
    }
}
