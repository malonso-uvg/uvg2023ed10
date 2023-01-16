/**
 * 
 */
package edu.uvg.structures;

import java.util.ArrayList;

/**
 * @author MAAG
 *
 */
public class EjemploRecorrido<V> implements ITreeTraversal<V> {

	public ArrayList<V> miLista = new ArrayList<V>();
	
	@Override
	public void Walk(V value) {
		miLista.add(value);
	}

}
