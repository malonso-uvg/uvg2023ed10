/**
 * 
 */
package edu.uvg.bank;

import java.util.Scanner;

/**
 * @author MAAG
 *
 */
public class singleSpooler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		PrintSpooler pr1, pr2;
		
		//open one spooler--this should always work
		System.out.println("Opening one spooler");
		
		try{
			pr1 = PrintSpooler.getInstance();
			System.out.println("Actual Number of copies: " + pr1.getNumberOfCopies());
			System.out.println("Adding one more copy...");
			pr1.newCopy();
		} catch (SingletonException e){
			System.out.println(e.getMessage());
		}
		
		
		//try to open another spooler
		System.out.println("Opening two spoolers");
		try{
			int opt = 0;
			do {
				Scanner in = new Scanner(System.in);
				
				pr2 = PrintSpooler.getInstance();
				System.out.println("Actual Number of copies: " + pr2.getNumberOfCopies());
				System.out.println("Adding one more copy...");
				pr2.newCopy();
				
				System.out.println("Press 0 to exit or any key to make another copy");
				opt = Integer.parseInt(in.nextLine());
			} while (opt != 0);
			
			
		} catch (SingletonException e) {
			System.out.println(e.getMessage());
		}

	}

}
