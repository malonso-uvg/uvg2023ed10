import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import structure5.RedBlackSearchTree;
import edu.uvg.ht9.ArbolRojoNegro;
import edu.uvg.ht9.Palabra;

/**
 * 
 */

/**
 * @author MAAG
 *
 */
class ArbolRojoNegroTestSuite {

	@Test
	void InsertionRedBlackTree() {
		ArbolRojoNegro<Palabra> arbolPrueba = new ArbolRojoNegro<Palabra>(); 
		
		arbolPrueba.add(new Palabra("house", "casa"));
		arbolPrueba.add(new Palabra("dog", "perro"));
		arbolPrueba.add(new Palabra("window", "ventana"));
		
		
		Palabra palabraBuscada = arbolPrueba.get(new Palabra("dog", ""));
		assertEquals("perro", palabraBuscada.getSpanish());
		
		palabraBuscada = arbolPrueba.get(new Palabra("house", ""));
		assertEquals("casa", palabraBuscada.getSpanish());
		
		palabraBuscada = arbolPrueba.get(new Palabra("window", ""));
		assertEquals("ventana", palabraBuscada.getSpanish());
		
	}

}
