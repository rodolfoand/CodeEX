/* Crie um programa para da classificar pela idade um nadador nas seguintes categorias:
 *  • Infantil = até 10 anos
 *  • Infanto = 11 a 15 anos
 *  • Juvenil = 16 a 18 anos
 *  • Adulto = maiores de 18 anos
 */

package br.senai.sp.informatica.mobileb.aulas.ex03;

import br.senai.sp.informatica.mobileb.lib.Util;

public class Ex0306 {
	public static void main(String[] args) {
		
		int idade = Util.leInteiro("Informe sua idade: ");
		String msg = "Sua classificação é: ";
		
		if (idade <= 10) {
			msg += "Infantil.";
		} else if (idade <= 15) {
			msg += "Infanto.";
		} else if (idade <= 18) {
			msg += "Juvenil.";
		} else {
			msg += "Adulto.";
		}
		
		Util.escreva(msg);
	}
}
