package br.senai.sp.informatica.mobileb.aulas.ex05;

import br.senai.sp.informatica.mobileb.lib.Util;

public class Ex0504 {
	public static void main(String[] args) {
		
		int qtd= Util.leInteiro("Informe a quantidade:");
		int maior = 0;
		
		for (int i = 0; i < qtd; i++) {
			int num = Util.leInteiro("Informe um nº:");
			if (num > maior) {
				maior = num;
			}
		}
		
		Util.escreva("O maior: ", maior);			
	}
}
