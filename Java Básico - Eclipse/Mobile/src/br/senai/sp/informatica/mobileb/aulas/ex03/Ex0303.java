// Crie um programa para ler dois números e informar se um é divisível pelo outro ou não

package br.senai.sp.informatica.mobileb.aulas.ex03;

import br.senai.sp.informatica.mobileb.lib.Util;

public class Ex0303 {
	public static void main(String[] args) {
		
		int n1 = Util.leInteiro("Informe o valor de A: ");
		int n2 = Util.leInteiro("Informe o valor de B: ");
		String msg;
		
		if (n1 % n2 == 0) {
			msg = "É divisível.";
		} else {
			msg = "Não é divisível.";
		}
		
		Util.escreva(msg);
	}
}
