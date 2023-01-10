import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 */

/**
 * @author moises.alonso
 *
 */
class CalculadoraSumasTest {

	@Test
	void SumaDosEnterosPositosTest() {
		CalculadoraSumas miCalculadora = new CalculadoraSumas();
		int resultado = miCalculadora.add(3, 5);
		assertEquals(8, resultado);
	}
	
	@Test
	void SumaDosEnterosPrimeroNegativoTest() {
		CalculadoraSumas miCalculadora = new CalculadoraSumas();
		int resultado = miCalculadora.add(-3, 5);
		assertEquals(2, resultado);
	}
	
	@Test
	void SumaDosEnterosAmbosNegativosTest() {
		CalculadoraSumas miCalculadora = new CalculadoraSumas();
		int resultado = miCalculadora.add(-3, -5);
		assertEquals(-8, resultado);
	}
	
	@Test
	void SumaDosEnterosPrimeroEsCeroTest() {
		CalculadoraSumas miCalculadora = new CalculadoraSumas();
		int resultado = miCalculadora.add(0, 5);
		assertEquals(5, resultado);
	}
	
	@Test
	void MultiplicacionDosEnterosTest() {
		CalculadoraSumas miCalculadora = new CalculadoraSumas();
		int resultado = miCalculadora.mult(3, 2);
		assertEquals(6, resultado);
	}

}
