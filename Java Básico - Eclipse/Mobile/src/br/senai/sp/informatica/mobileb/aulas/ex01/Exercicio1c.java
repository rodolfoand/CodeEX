package br.senai.sp.informatica.mobileb.aulas.ex01;

import javax.swing.JOptionPane;

public class Exercicio1c {
	public static void main(String[] args) {
		String temp = JOptionPane.showInputDialog("Informe a base: ");
		double base = Double.parseDouble(temp);
		
		temp = JOptionPane.showInputDialog("Informe a altura: ");
		double altura = Double.parseDouble(temp);
		
		double area = (base * altura) / 2;
		
		JOptionPane.showMessageDialog(null, "A área é de: " + area);
		
	}
}
