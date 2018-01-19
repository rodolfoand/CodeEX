package com.example.rodol.android_meusalbuns_sql.model;

import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by rodol on 17/01/2018.
 */

public class Album implements Comparable<Album>{
    private Long id;
    private String artista;
    private String nome;
    private String genero;
    private Date dtLancamento;
    private boolean ativo;
    private byte[] capa;

    public Album() {
    }

    public Album(Long id) {
        this.id = id;
    }

    public Album(Long id, String artista, String nome, String genero, Date dtLancamento, boolean ativo) {
        this.id = id;
        this.artista = artista;
        this.nome = nome;
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

        public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
