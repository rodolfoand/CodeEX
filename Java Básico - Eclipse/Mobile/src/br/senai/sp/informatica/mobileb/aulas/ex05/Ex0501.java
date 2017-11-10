package br.senai.sp.informatica.mobileb.aulas.ex05;

import br.senai.sp.informatica.mobileb.lib.Util;

public class Ex0501 {
	public static void main(String[] args) {
		String numeros="";
		String temp;
		for (int i = 0; i <=1000; i++) {
			
			if (i % 2 == 0) {
				temp = i + "";
				
				temp = Util.tamanhoTexto(temp, 4);
				
				if (i % 44 == 0) {
					numeros += "\n";
				}
				
				numeros += temp + " ";
			}
			
		}
		Util.escreva(numeros);
	}
}
