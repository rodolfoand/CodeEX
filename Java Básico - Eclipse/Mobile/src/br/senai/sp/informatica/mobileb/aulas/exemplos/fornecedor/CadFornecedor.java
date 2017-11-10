package br.senai.sp.informatica.mobileb.aulas.exemplos.fornecedor;

import java.util.ArrayList;
import java.util.List;

import br.senai.sp.informatica.mobileb.lib.Util;

public class CadFornecedor {
	public static void main(String[] args) {
		List<Fornecedor> lista = new ArrayList<>();
		
		String nome = Util.leTexto("Informe o Nome do Fornecedor: ");
		
		while (!nome.equals("fim")) {
			int id = Util.leInteiro("Informe o ID do Fornecedor: ");
			String endereco = Util.leString("Informe o Endereço do Fornecedor: ");
			
			Fornecedor forn = new Fornecedor();
			forn.setNome(nome);
			forn.setId(id);
			forn.setEndereco(endereco);
			
			lista.add(forn);
			
			nome = Util.leTexto("Informe o Nome do Fornecedor: ");
			
		}
		
		String texto = "";
		for (Fornecedor forn : lista) {
			texto += forn + "\n";
		}
		Util.escreva(texto);
	}
}
