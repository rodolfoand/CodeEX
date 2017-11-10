package br.senai.sp.informatica.mobileb.aulas.ex02;

import javax.swing.JOptionPane;

public class Exercicio2b {
	public static void main(String[] args) {
		String temp = JOptionPane.showInputDialog("Informe um nº: ");
		
		int numero=Integer.parseInt(temp);
		
		/*Solução 1: Não utilizar
		 * String invertido = new StringBuilder(numero).reverse().toString();
		*/

		/*Solução 2: não funciona!
		
		
		char[] matriz = numero.toCharArray();
		
		
		char[] matriz1 = new String(matriz[1]);
		//char[] matriz2 = {matriz[2]}
		//char[] matriz3 = {matriz[3]}
		
		String invertido = new String(matriz);
		//String invertido += matriz[2];
		//String invertido += matriz[1];
		*/
		
		//Solução 3: Ok!
		
		int centena = numero / 100;
		String scentena = String.valueOf(centena);
		int restoCentena = numero % 100;
		
		int dezena = restoCentena / 10;
		int restoDezena = restoCentena % 10;
		String sdezena = String.valueOf(dezena);
		
		int unidade = restoDezena / 1;
		String sunidade = String.valueOf(unidade);
				
		
		JOptionPane.showMessageDialog(null, "inv: "+ sunidade 
				+ sdezena
				+ scentena);
		
	}
}
