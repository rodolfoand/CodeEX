package br.senai.sp.informatica.mobileb.aulas.exemplos;

import java.util.Arrays;

import br.senai.sp.informatica.mobileb.lib.Util;

public class ExemploVetorOrdenado {
	public static void main(String[] args) {
		String[] lista = new String[3];
		int indice = 0;
		
		String nome = Util.leTexto("Informe o ",indice+1,"º Nome");
		while(!nome.equals("fim")) {
			if(indice >= lista.length) {
				lista = Arrays.copyOf(lista, lista.length + 3);
			}
			lista[indice++] = nome;
			
			nome = Util.leTexto("Informe o ",indice+1,"º Nome");
		}
		
		// <<====

		String menor;
		String maior;
		for (int i = 0; i < indice; i++) {
			for (int j = i; j < indice; j++) {
				if (lista[i].compareTo(lista[j]) > 0) {
					
					//Util.escreva(lista[i], lista [j]);
					
					menor =  lista[j];
					maior =  lista[i];
					lista[i] = menor;
					lista[j] = maior;
					
					//Util.escreva(lista);
					
				}
			}
		}
		
		// <<====
		String texto = "";
		for (int i = 0; i < indice; i++) {
			texto += lista[i] + "\n";
		}
		Util.escreva(texto);
	}
}
