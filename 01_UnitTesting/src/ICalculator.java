/**
 * 
 */

/**
 * @author moises.alonso
 * This interface allow us implement a basic integer calculator
 */
public interface ICalculator {

	/***
	 * This method gets the sum of two numbers, ex. num1 = 4, num2 = 2, then return 6
	 * @param num1 The first integer number
	 * @param num2 The second integer number
	 * @return the sum of num1 and num2
	 */
	public int add(int num1, int num2);
	
	public int subs(int num1, int num2);
	
	public int mult(int num1, int num2); 
	
	public int div(int num1, int num2);
	
	public int mod(int num1, int num2);
}
