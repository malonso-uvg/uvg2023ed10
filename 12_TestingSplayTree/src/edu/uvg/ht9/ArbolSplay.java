/**
 * 
 */
package edu.uvg.ht9;

import java.util.Comparator;

import structure5.SplayTree;
/**
 * @author MAAG
 *
 */
public class ArbolSplay<T extends Comparable<T>> implements EstructuraArbol<T> {

	private SplayTree<T> miArbolInterno;
	
	public ArbolSplay() {
		miArbolInterno = new SplayTree<T>(); 
	}
	
	public ArbolSplay(Comparator<T> comparador) {
		miArbolInterno = new SplayTree<T>(comparador); 
	}
	
	@Override
	public void add(T value) {
		miArbolInterno.add(value);
		
	}

	@Override
	public T get(T key) {
		
		return miArbolInterno.get(key);
	}

	@Override
	public T remove(T key) {
		
		return miArbolInterno.remove(key);
	}

	@Override
	public int count() {
		return miArbolInterno.size();
	}

	@Override
	public boolean isEmpty() {
		return miArbolInterno.isEmpty();
	}

	public SplayTree<T> getInternalTree(){
		return miArbolInterno;
	} 
}
