/**
 * 
 */
package edu.uvg.factory;

/**
 * @author MAAG
 *
 */
public class Employee extends User {

	@Override
	public void printMenu() {
		System.out.println("Bienvenido");
		System.out.println("Seleccione su opcion");
		System.out.println("1. Clock-in and Clock-out");
		System.out.println("2. Payslip");
	}
}
