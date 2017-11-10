package br.senai.sp.informatica.mobileb.aulas.exemplos.fornecedor;

public class Fornecedor {
	private String nome;
	private int id;
	private String endereco;
	
	public String getNome() {
		return nome;
	}
	@Override
	public String toString() {
		return "nome: " + nome + " id: " + id + " endereco: " + endereco;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
}
