/* Crie um programa para calcular o salário líquido de um funcionário.
 * O programa recebe através do teclado o salário bruto de um determinado funcionário,
 *  caso este seja inferior a R$ 300,00 é descontado 5% em impostos, se o salário variar de R$ 300,00 até 1.200,00 é descontado 10% em impostos, se esse for superior a R$ 1.200,00 é descontado 15% em impostos.
 * Ao final o algoritmo deve exibir o salário líquido do funcionário.
 * 
 * 
 */
package br.senai.sp.informatica.mobileb.aulas.ex03;

import br.senai.sp.informatica.mobileb.lib.Util;

public class Ex0304 {
	public static void main(String[] args) {
		
		double salBruto = Util.leReal("Informe o sal bruto: ");
		double salLiquido;
				
		if (salBruto < 300) {
			salLiquido = salBruto * 0.95;
		} else if (salBruto < 1200) {
			salLiquido = salBruto * 0.90;
		} else {
			salLiquido = salBruto * 0.85;
		}
		
		String msg = "O Sal Liq é de R$ " + salLiquido;
		
		Util.escreva(msg);
	}
}
