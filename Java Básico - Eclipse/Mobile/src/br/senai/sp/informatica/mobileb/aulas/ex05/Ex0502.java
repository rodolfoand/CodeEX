package br.senai.sp.informatica.mobileb.aulas.ex05;

import br.senai.sp.informatica.mobileb.lib.Util;

public class Ex0502 {
	public static void main(String[] args) {
		
		int n1 = Util.leInteiro("Informe o 1º nº:");
		int n2 = Util.leInteiro("Informe o último nº:");
		
		String numeros="";
		String temp;
		
		do {
			n1++;
			if (n1 % 2 == 0) {
				temp = n1 + "";
				temp = Util.tamanhoTexto(temp, 4);
				numeros += temp + " ";
			}
			
		} while (n1 < n2);
		Util.escreva(numeros);
	}
}
