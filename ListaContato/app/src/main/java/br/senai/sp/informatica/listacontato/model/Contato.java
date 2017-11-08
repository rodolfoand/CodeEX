package br.senai.sp.informatica.listacontato.model;

import android.support.annotation.NonNull;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by WEB on 01/11/2017.
 */

public class Contato implements Comparable<Contato>{
    private Long id;
    private String nome;
    private String email;
    private Date dtNascimento;

    public Contato(Long id, String nome, String email, Date dtNascimento) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dtNascimento = dtNascimento;
    }

    public Contato() {
    }

    public Contato(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contato contato = (Contato) o;

        return id.equals(contato.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public int compareTo(@NonNull Contato outro) {
        return nome.compareTo(outro.nome);
    }
}
