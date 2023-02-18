/**
 * 
 */
import java.util.Scanner;

import structure5.AbstractList;
/**
 * @author MAAG
 *
 */
public class DriverProgram {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);
		String palabra = "";
		System.out.println("Ingrese la palabra single para usar una lista simplemente encadenada o double para usar una doblemente encadenada");
		palabra = in.nextLine();
		
		
		AbstractList<String> miLista = (new ListFactory<String>()).getInstance(palabra);
		
		do {
			System.out.println("Ingrese palabras y si desea salir escriba la palabra salir");
			palabra = in.nextLine();
			miLista.addLast(palabra);
		}while(!palabra.equalsIgnoreCase("salir")); 
		
		System.out.println(miLista.toString());
		
	}

}
