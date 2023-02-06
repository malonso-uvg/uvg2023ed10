/**
 * 
 */
package edu.uvg.factory;

import java.util.Scanner;

/**
 * @author MAAG
 *
 */
public class PortalElectronico {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		User loggedUser;
		Login myLogin = new Login();
		UserInstanceCreator factory = new UserInstanceCreator();
		Scanner in = new Scanner(System.in);
		
		System.out.println("Bienvenido al portal electronico");
		System.out.println("Ingrese su usuario (correo electronico)");
		String username = in.nextLine();
		System.out.println("Ingrese password");
		String password = in.nextLine();
		
		if (myLogin.hasAccess(username, password)) {
			loggedUser = factory.getInstance(username);
			loggedUser.printMenu();
		} else {
			System.out.println("Usuario o Password incorrectos");
		}
		
	}

}
