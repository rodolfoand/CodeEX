//Crie um programa para ler um número e informar se ele é par ou impar

package br.senai.sp.informatica.mobileb.aulas.ex03;

import br.senai.sp.informatica.mobileb.lib.Util;

public class Ex0302 {
	public static void main(String[] args) {
		
		int n1 = Util.leInteiro("Informe um nº: ");
		String msg;
		
		if (n1 % 2 == 0) {
			msg = "É par.";
		} else {
			msg = "É impar.";
		}
		
		Util.escreva(msg);
	}
}
