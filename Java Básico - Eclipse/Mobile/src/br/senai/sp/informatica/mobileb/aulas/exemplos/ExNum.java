package br.senai.sp.informatica.mobileb.aulas.exemplos;

import javax.swing.JOptionPane;

public class ExNum {
	public static void main(String[] args) {
		String temp = JOptionPane.showInputDialog("Informe um nº");
		int num = Integer.parseInt(temp);
		
		int x = num % 100;
		
		JOptionPane.showMessageDialog(null, "nº antes: " + num +  "\ndepois: " + x);
	}
}
