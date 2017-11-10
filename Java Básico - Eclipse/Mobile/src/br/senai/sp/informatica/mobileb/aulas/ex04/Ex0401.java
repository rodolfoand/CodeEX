package br.senai.sp.informatica.mobileb.aulas.ex04;

import br.senai.sp.informatica.mobileb.lib.Util;

public class Ex0401 {
	public static void main(String[] args) {
		
		double nota = Util.leReal("Informe a nota: ");
		nota += Util.leReal("Informe a nota: ");
		nota += Util.leReal("Informe a nota: ");
		nota += Util.leReal("Informe a nota: ");
		
		nota = nota / 4;
		
		if (nota >= 7) {
			Util.escreva(nota, "\n", "Aprovado");
		} else {
			Util.escreva(nota, "\n", "Reprovado");
		}
	}
}
