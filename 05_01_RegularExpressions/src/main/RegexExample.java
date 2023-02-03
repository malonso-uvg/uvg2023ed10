/**
 * 
 */
package main;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author MAAG
 *
 */
public class RegexExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Ejemplo reconocer numeros
		
		System.out.println("** PROGRAMA DE EJEMPLO REGEX **");
		System.out.println("** Escriba una cadena y el programa le dira si es numero o no, escriba EXIT para salir **");
		Scanner in = new Scanner(System.in);
		String option = in.nextLine();
		
		while (!option.equals("EXIT")) {
			Pattern pattern = Pattern.compile("^([0-9])+$", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(option);
			boolean matchFound = matcher.find();
			if(matchFound) {
				System.out.println("La cadena ingresada SI ES UN NUMERO");
			} else {
				System.out.println("La cadena ingresada NO ES UN NUMERO");
			}
			
			System.out.println("** Escriba una cadena y el programa le dira si es numero o no, escriba EXIT para salir **");
			option = in.nextLine();
		}
		
		
		//Ejemplo reconocer correos electronicos
		System.out.println("** MUY BIEN, Escriba una cadena y el programa le dira si es un email valido, escriba EXIT para salir **");
		option = in.nextLine();
		
		while (!option.equals("EXIT")) {
			Pattern pattern = Pattern.compile("^([a-z]|[A-Z]|[0-9])+((\\.|_)?([a-z]|[A-Z]|[0-9])+)*@([a-z]|[A-Z]|[0-9])+(\\.([a-z]|[A-Z])+)+$", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(option);
			boolean matchFound = matcher.find();
			if(matchFound) {
				System.out.println("El email SI es valido");
			} else {
				System.out.println("El email NO es valido");
			}
			
			System.out.println("** Escriba una cadena y el programa le dira si es un email valido, escriba EXIT para salir **");
			option = in.nextLine();
		}
		

	}

}
