/**
 * 
 */
package edu.uvg.factory;

/**
 * @author MAAG
 *
 */
public class Student extends User {

	@Override
	public void printMenu() {
		System.out.println("Bienvenido Alumno");
		System.out.println("Seleccione su opcion");
		System.out.println("1. Revisar cursos");
		System.out.println("2. Subir tarea");
	}

	
}
