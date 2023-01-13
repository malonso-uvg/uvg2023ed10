/**
 * 
 */

/**
 * @author moises.alonso
 *
 */
public class CalculadoraNormal implements ICalculator {

	@Override
	public int add(int num1, int num2) {
		return num1 + num2;
	}

	@Override
	public int subs(int num1, int num2) {
		return num1 - num2;
	}

	@Override
	public int mult(int num1, int num2) {
		return num1 * num2;
	}

	@Override
	public int div(int num1, int num2) {
		return num1 / num2;
	}

	@Override
	public int mod(int num1, int num2) {
		return num1 % num2;
	}

}
