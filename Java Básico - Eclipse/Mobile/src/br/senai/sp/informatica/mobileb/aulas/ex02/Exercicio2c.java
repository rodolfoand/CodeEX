package br.senai.sp.informatica.mobileb.aulas.ex02;

import javax.swing.JOptionPane;

public class Exercicio2c {
	public static void main(String[] args) {
		
		String temp = JOptionPane.showInputDialog("Informe o valor do Saque: ");
		int saque = Integer.parseInt(temp);
		
		int nota100 = (int)saque / 100;
		int restoNota100 = saque % 100;
		
		int nota50 = restoNota100 / 50;
		int restoNota50 = restoNota100 % 50;
		
		int nota20 = restoNota50 / 20;
		int restoNota20 = restoNota50 % 20;
		
		int nota10 = restoNota20 / 10;
		int restoNota10 = restoNota20 % 10;
		
		int nota5 = restoNota10 / 5;
		int restoNota5 = restoNota10 % 5;
		
		int nota2 =  restoNota5 / 2;
		int restoNota2 = restoNota5 % 2;
		
		int nota1 = restoNota2 / 1;
		
		JOptionPane.showMessageDialog(null, "Notas de 100: " + nota100 + "\n"
				+ "Notas de 50: " + nota50 + "\n"
				+ "Notas de 20: " + nota20 + "\n"
				+ "Notas de 10: " + nota10 + "\n"
				+ "Notas de 5: " + nota5 + "\n"
				+ "Notas de 2: " + nota2 + "\n"
				+ "Notas de 1: " + nota1 + "\n"
				);
		
	}
}
