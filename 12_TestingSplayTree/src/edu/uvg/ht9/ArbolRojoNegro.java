/**
 * 
 */
package edu.uvg.ht9;

import structure5.RedBlackSearchTree;
/**
 * @author MAAG
 *
 */
public class ArbolRojoNegro<T extends Comparable<T>> implements EstructuraArbol<T> {

	private RedBlackSearchTree<T> miArbolInterno;
	
	public ArbolRojoNegro() {
		miArbolInterno = new RedBlackSearchTree<T>();
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
	
	public RedBlackSearchTree<T> getInternalTree(){
		return miArbolInterno;
	}

}
