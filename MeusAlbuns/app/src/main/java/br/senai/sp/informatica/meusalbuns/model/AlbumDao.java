package br.senai.sp.informatica.meusalbuns.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Created by 34023325821 on 16/11/2017.
 */

public class AlbumDao {
    public static AlbumDao manager = new AlbumDao();
    private List<Album> lista;
    private long id = 0;

    private AlbumDao(){
        lista = new ArrayList<>();

        //Criação de albuns para apresentação
        //TODO: ajustar criação de capa
        lista.add(new Album(id++, "Novo Album", "Novo Artista", "Novo Genero", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album2", "Novo Artista2", "Novo Genero2", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album3", "Novo Artista3", "Novo Genero3", Calendar.getInstance().getTime(), true));
        lista.add(new Album(id++, "Novo Album4", "Novo Artista4", "Novo Genero4", Calendar.getInstance().getTime(), true));
    }
    public List<Album> getLista(){
        for (int i = 0; i < lista.size(); i++) {
            if (!lista.get(i).isAtivo()){
                lista.remove(i);
            }
        }
        return Collections.unmodifiableList(lista);
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
        this.getLista();
    }
}
