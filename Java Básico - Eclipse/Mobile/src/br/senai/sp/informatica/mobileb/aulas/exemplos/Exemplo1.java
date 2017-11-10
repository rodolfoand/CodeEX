package br.senai.sp.informatica.mobileb.aulas.exemplos;

import java.util.Scanner;

//Usando o Console do Eclipse para receber epresentar informações 


public class Exemplo1 {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.print("Informe seu nome: ");
		String nome = console.nextLine();
		System.out.print("Informe sua idade: ");
		int idade = console.nextInt();
		System.out.print("Informe sua pretenção salarial: ");
		double pretSalarial = console.nextDouble();
		
		System.out.println(nome + " com a idade de " + idade + " sua pretenção salarial é de R$ " + pretSalarial + " é alta");
		
		console.close();
	}
}
