package br.senai.sp.informatica.mobileb.aulas.ex02;

import javax.swing.JOptionPane;

public class Exercicio2a {
	public static void main(String[] args) {
		String temp = JOptionPane.showInputDialog("Informe a Largura: ");
		double largura = Double.parseDouble(temp);
		
		temp = JOptionPane.showInputDialog("Informe o Comprimento: ");
		double comprimento = Double.parseDouble(temp);
		
		temp = JOptionPane.showInputDialog("Informe a Profundidade: ");
		double profundidade=Double.parseDouble(temp);
		
		double metro3 = largura * comprimento * profundidade;
		
		double valor = metro3 * 45;
		
		JOptionPane.showMessageDialog(null, "O Preço final é de R$" + valor);
		
	}
}
