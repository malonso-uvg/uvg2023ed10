/**
 * 
 */
package edu.uvg.factory;

/**
 * @author MAAG
 *
 */
public class Professor extends User {

	@Override
	public void printMenu() {
		System.out.println("Bienvenido Profesor");
		System.out.println("Seleccione su opcion");
		System.out.println("1. Ingresar notas");
		System.out.println("2. Sincronizar contenido");
	}

}
