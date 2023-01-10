/**
 * 
 */

/**
 * @author moises.alonso
 *
 */
public class CalculadoraSumas implements ICalculator {

	@Override
	public int add(int num1, int num2) {
		return num1 + num2;
	}

	@Override
	public int subs(int num1, int num2) {
		return num1 + (-num2);
	}

	@Override
	public int mult(int num1, int num2) {
		int result = 0;
		
		for (int i = 0; i < num1; i++) {
			result += num2;
		}
		
		return result;
	}

	@Override
	public int div(int num1, int num2) {
		int result = 0;
		while ((num1 - num2) > 0) {
			result++;
			num1 -= num2;
		}
		return result;
	}

	@Override
	public int mod(int num1, int num2) {
		
		while ((num1 - num2) > 0) {
			num1 -= num2;
		}
		return num1;
		
	}

}
