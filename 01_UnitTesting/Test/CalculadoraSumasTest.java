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

	//ICalculator miCalculadora = new CalculadoraSumas();
	ICalculator miCalculadora = new CalculadoraNormal();
	//CalculadoraSumas miCalculadora = new CalculadoraSumas();
	
	@Test
	void TC1_SumaPositivosTest() {
		int result = miCalculadora.add(3, 5);
		assertEquals(8, result);
	}
	
	@Test
	void TC2_SumaPrimeroNegativoTest() {
		int result = miCalculadora.add(-3, 5);
		assertEquals(2, result);
	}
	
	@Test
	void TC3_SumaSegundoNegativoTest() {
		int result = miCalculadora.add(3, -5);
		assertEquals(-2, result);
	}
	
	@Test
	void TC4_SumaAmbosNegativosTest() {
		int result = miCalculadora.add(-3, -5);
		assertEquals(-8, result);
	}
	
	@Test
	void TC5_SumaPrimeroCero() {
		int result = miCalculadora.add(0, 5);
		assertEquals(5, result);
	}
	
	@Test
	void TC6_SumaSegundoCero() {
		int result = miCalculadora.add(5, 0);
		assertEquals(5, result);
	}
	
	@Test
	void TC7_RestaPositivosPrimeroMayorTest() {
		int result = miCalculadora.subs(5, 3);
		assertEquals(2, result);
	}
	
	@Test
	void TC8_RestaPositivosSegundoMayorTest() {
		int result = miCalculadora.subs(3, 5);
		assertEquals(-2, result);
	}
	
	@Test
	void TC9_RestaPrimeroNegativoTest() {
		int result = miCalculadora.subs(-3, 5);
		assertEquals(-8, result);
	}
	
	@Test
	void TC10_RestaSegundoNegativoTest() {
		int result = miCalculadora.subs(3, -5);
		assertEquals(8, result);
	}
	
	@Test
	void TC11_RestaAmbosNegativosTest() {
		int result = miCalculadora.subs(-3, -5);
		assertEquals(2, result);
	}
	
	@Test
	void TC12_RestaPrimeroCero() {
		int result = miCalculadora.subs(0, 5);
		assertEquals(-5, result);
	}
	
	@Test
	void TC13_RestaSegundoCero() {
		int result = miCalculadora.subs(5, 0);
		assertEquals(5, result);
	}
	
	@Test
	void TC14_MultiplicacionAmbosPositivosTest() {
		int result = miCalculadora.mult(3, 5);
		assertEquals(15, result);
	}
	
	@Test
	void TC15_MultiplicacionPrimeroNegativo() {
		int result = miCalculadora.mult(-3, 5);
		assertEquals(-15, result);
	}
	
	@Test
	void TC16_MultiplicacionSegundoNegativo() {
		int result = miCalculadora.mult(3, -5);
		assertEquals(-15, result);
	}
	
	@Test
	void TC17_MultiplicacionAmbosNegativosTest() {
		int result = miCalculadora.mult(-5, -3);
		assertEquals(15, result);
	}
	
	@Test
	void TC18_MultiplicacionPrimeroCeroTest() {
		int result = miCalculadora.mult(0, 5);
		assertEquals(0, result);
	}
	
	@Test
	void TC19_MultiplicacionSegundoCeroTest() {
		int result = miCalculadora.mult(3, 0);
		assertEquals(0, result);
	}

}
