package br.senai.sp.informatica.mobileb.aulas.ex05;

import br.senai.sp.informatica.mobileb.lib.Util;

public class Ex0503 {
	public static void main(String[] args) {
		
		int soma = 0;
		int media = 0;
		int i = 0;
		
		while(i < 5) {
			soma += Util.leInteiro("Informe um nº:");
			i++;
		}
		media = soma / i;
		
		Util.escreva("A soma: ", soma, "\n", "A média: ", media);	
	}
}
