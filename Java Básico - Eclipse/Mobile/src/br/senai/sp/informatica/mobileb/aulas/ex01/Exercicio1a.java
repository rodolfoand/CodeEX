package br.senai.sp.informatica.mobileb.aulas.ex01;

import javax.swing.JOptionPane;

public class Exercicio1a {
	public static void main(String[] args) {
		String num1 = JOptionPane.showInputDialog("Informe o valor de num1: ");
		String num2 = JOptionPane.showInputDialog("Informe o valor de num2: ");
		
		double total = Math.pow(Double.parseDouble(num1), Double.parseDouble(num2));
		
		JOptionPane.showMessageDialog(null, "O total é: " + total);
	}

}
