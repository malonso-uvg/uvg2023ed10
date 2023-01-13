import java.util.Scanner;

/**
 * 
 */

/**
 * @author moises.alonso
 *
 */
public class CalculadoraConsoleUI {

	private Scanner _in;
	private ICalculator miCalculadora;
	
	public CalculadoraConsoleUI(Scanner _scanner, ICalculator _calculator) {
		_in = _scanner;
		miCalculadora = _calculator;
	}
	
	public void MenuPrincipal() {
		
		int option = 0;
		
		do {
			
			System.out.println("*** CALCULADORA ***");
			System.out.println("Seleccione su opcion");
			System.out.println("1. Suma de dos numeros");
			System.out.println("2. Resta de dos numeros");
			System.out.println("3. Multiplicacion de dos numeros");
			System.out.println("4. Division Entera de dos numeros");
			System.out.println("5. Residuo de dos numeros");
			System.out.println("6. Salir");
			
			option = Integer.parseInt(_in.nextLine());
			
			switch (option) {
			case 1:{ EjecutarSuma(); }break;
			case 2:{ EjecutarResta(); }break;
			case 3:{ EjecutarMultiplicacion(); }break;
			case 4:{ EjecutarDivision(); }break;
			case 5:{ EjecutarModulo(); }break;
			case 6:{ System.out.println("HASTA PRONTO!"); }break;
			default:{ System.out.println("OPCION NO VALIDA, INTENTE NUEVAMENTE"); }break;
			}
			
		}while (option != 6);
		
	}
	
	private void EjecutarSuma() {
		System.out.println("*** SUMA DE DOS NUMEROS ***");
		System.out.println("Ingrese el primer numero");
		int numero1 = Integer.parseInt(_in.nextLine());
		
		System.out.println("Ingrese el segundo numero");
		int numero2 = Integer.parseInt(_in.nextLine());
		
		int resultado = miCalculadora.add(numero1, numero2);
		
		System.out.println("(" + numero1 + ") + (" + numero2 + ") = " + resultado);
	}
	
	private void EjecutarResta() {
		System.out.println("*** RESTA DE DOS NUMEROS ***");
		System.out.println("Ingrese el primer numero");
		int numero1 = Integer.parseInt(_in.nextLine());
		
		System.out.println("Ingrese el segundo numero");
		int numero2 = Integer.parseInt(_in.nextLine());
		
		int resultado = miCalculadora.subs(numero1, numero2);
		
		System.out.println("(" + numero1 + ") - (" + numero2 + ") = " + resultado);
	}
	
	private void EjecutarMultiplicacion() {
		System.out.println("*** MULTIPLICACION DE DOS NUMEROS ***");
		System.out.println("Ingrese el primer numero");
		int numero1 = Integer.parseInt(_in.nextLine());
		
		System.out.println("Ingrese el segundo numero");
		int numero2 = Integer.parseInt(_in.nextLine());
		
		int resultado = miCalculadora.mult(numero1, numero2);
		
		System.out.println("(" + numero1 + ") * (" + numero2 + ") = " + resultado);
	}
	
	private void EjecutarDivision() {
		System.out.println("*** DIVISION DE DOS NUMEROS ***");
		System.out.println("Ingrese el primer numero");
		int numero1 = Integer.parseInt(_in.nextLine());
		
		System.out.println("Ingrese el segundo numero");
		int numero2 = Integer.parseInt(_in.nextLine());
		
		int resultado = miCalculadora.div(numero1, numero2);
		
		System.out.println("(" + numero1 + ") / (" + numero2 + ") = " + resultado);
	}
	
	private void EjecutarModulo() {
		System.out.println("*** RESIDUO DE DOS NUMEROS ***");
		System.out.println("Ingrese el primer numero");
		int numero1 = Integer.parseInt(_in.nextLine());
		
		System.out.println("Ingrese el segundo numero");
		int numero2 = Integer.parseInt(_in.nextLine());
		
		int resultado = miCalculadora.mod(numero1, numero2);
		
		System.out.println("(" + numero1 + ") % (" + numero2 + ") = " + resultado);
	}
}
