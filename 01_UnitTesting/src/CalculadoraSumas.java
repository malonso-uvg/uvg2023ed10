/**
 * 
 */

/**
 * @author moises.alonso
 * This class implements all the arithmetic basic operations using only sum
 */
public class CalculadoraSumas implements ICalculator {

	@Override
	public int add(int num1, int num2) {
		return num1 + num2;
	}

	@Override
	public int subs(int num1, int num2) {
		return add(num1, -num2);
	}

	@Override
	public int mult(int num1, int num2) {
		boolean num1Neg = false;
		boolean num2Neg = false;
		
		if (num1 < 0) {
			num1 = num1 * (-1);
			num1Neg = true;
		}
		
		if (num2 < 0) {
			num2 = num2 * (-1);
			num2Neg = true;
		}
		
		
		int result = 0;
		for (int i = 0; i < num1; i++) {
			result = add(result, num2);
		}
		
		if (num1Neg && !num2Neg) {
			result = result * (-1);
		} else  if (!num1Neg && num2Neg) {
			result = result * (-1);
		}
		
		return result;
	}

	@Override
	public int div(int num1, int num2) {
		int result = 0;
		while (subs(num1, num2) > 0) {
			result++;
			num1 = subs(num1, num2);
		}
		return result;
	}

	@Override
	public int mod(int num1, int num2) {
		
		while (subs(num1, num2) > 0) {
			num1 = subs(num1, num2);
		}
		return num1;
		
	}

}
