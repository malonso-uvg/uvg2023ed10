import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.uvg.ht9.ComparadorPalabras;
import edu.uvg.ht9.Palabra;
import structure5.SplayTree;

class PalabraInSplayTree {

	@Test
	void test() {
		SplayTree<edu.uvg.ht9.Palabra> miDiccionario = new SplayTree<Palabra>(new ComparadorPalabras<Palabra>());
		
		miDiccionario.add(new Palabra("dog", "perro"));
		miDiccionario.add(new Palabra("house", "casa"));
		miDiccionario.add(new Palabra("window", "ventana"));
		
		
		miDiccionario.get(new Palabra("house", ""));
		miDiccionario.get(new Palabra("house", ""));
	}

}
