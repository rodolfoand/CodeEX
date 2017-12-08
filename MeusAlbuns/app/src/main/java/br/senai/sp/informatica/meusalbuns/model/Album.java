package br.senai.sp.informatica.meusalbuns.model;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by 34023325821 on 14/11/2017.
 */

public class Album implements Comparable<Album> {
    private Long id;
    private String album;
    private String artista;
    private String genero;
    private Date dtLancamento;
    private boolean ativo;
    private byte[] capa;

    public Album() {
    }

    public Album(Long id) {
        this.id = id;
    }

    public Album(Long id, String nome, String artista, String genero, Date dtLancamento, boolean ativo) {
        this.id = id;
        this.album = nome;
        this.artista = artista;
        this.genero = genero;
        this.dtLancamento = dtLancamento;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String nome) {
        this.album = nome;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getDtLancamento() {
        return dtLancamento;
    }

    public void setDtLancamento(Date dtLancamento) {
        this.dtLancamento = dtLancamento;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public byte[] getCapa() {
        return capa;
    }

    public void setCapa(byte[] capa) {
        this.capa = capa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        return id.equals(album.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public int compareTo(@NonNull Album o) {
        return artista.compareToIgnoreCase(o.artista);
    }
}
