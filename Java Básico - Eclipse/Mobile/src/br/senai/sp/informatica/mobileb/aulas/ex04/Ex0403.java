package br.senai.sp.informatica.mobileb.aulas.ex04;

import br.senai.sp.informatica.mobileb.lib.Util;

public class Ex0403 {
	public static void main(String[] args) {
		
		double a = Util.leReal("Informe o valor de a: ");
		double b = Util.leReal("Informe o valor de b: ");
		double c = Util.leReal("Informe o valor de c: ");
		
		double x1 = (-b + Math.sqrt( Math.pow(b, 2) - 4 * a *c)) / 2 * a;
		double x2 = (-b - Math.sqrt( Math.pow(b, 2) - 4 * a *c)) / 2 * a;
		
		Util.escreva("x' = ", x1, "\n", "x'' = ", x2);
	}
}
