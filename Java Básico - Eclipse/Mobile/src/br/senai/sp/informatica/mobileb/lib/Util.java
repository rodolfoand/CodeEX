package br.senai.sp.informatica.mobileb.lib;

import javax.swing.JOptionPane;

public class Util {
	
	// Início: Métodos criados por Rodolfo Andrade
	
	public static String leString(String msg) {
		String temp = JOptionPane.showInputDialog(msg);
				
		return temp;
	}
	
	public static String tamanhoTexto(String texto, int tam) {
		
		while (texto.length() <= tam) {
			texto = "0" + texto;
		}	
		return texto;
	}
	//Fim
	
	//Início: Métodos criados por Professor
	
	public static String leTexto(Object ... msg) {
		return JOptionPane.showInputDialog(formataTexto(msg));
	}
	
	public static int leInteiro(Object ... msg) {
		String temp = JOptionPane.showInputDialog(formataTexto(msg));
		
		int num = Integer.parseInt(temp);
		return num;
	}

	public static double leReal(Object ... msg) {
		String temp = JOptionPane.showInputDialog(formataTexto(msg));

		return Double.parseDouble(temp);
	}
	
	public static void escreva(Object ... msg) {
		JOptionPane.showMessageDialog(null, formataTexto(msg));
	}

	private static String formataTexto(Object ... texto) {
		String fmt = "";
		
		for (int i = 0; i < texto.length; i++) {
			Object obj = texto[i];
			
			if(obj instanceof String) {
				fmt += "%s";
			} else if(obj instanceof Integer) {
				fmt += "%d";
			} else if(obj instanceof Double) {
				fmt += "%,.2f";
			}
		}
		
		return String.format(fmt, texto);
	}
	//Fim
}
