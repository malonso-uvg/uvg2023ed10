import java.util.Scanner;

/**
 * 
 */

/**
 * @author moises.alonso
 *
 */
public class DriverProgram {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Seleccione el tipo de interfaz a utilizar");
		System.out.println("1. Console UI");
		System.out.println("2. Aplicaciones de ventanas");
		
		Scanner in = new Scanner(System.in);
		String opt = in.nextLine();
		
		switch(opt) {
		
		case "1": {
			CalculadoraConsoleUI consoleUI = new CalculadoraConsoleUI(in, new CalculadoraSumas());
			consoleUI.MenuPrincipal();
		} break;
		
		case "2": {
			
		} break;
		
		default:{
			
		} break;
		
		}
		
	}

}
