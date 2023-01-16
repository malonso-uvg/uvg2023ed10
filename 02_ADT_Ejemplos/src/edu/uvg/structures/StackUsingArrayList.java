/**
 * 
 */
package edu.uvg.structures;

import java.util.ArrayList;

/**
 * @author MAAG
 *
 */
public class StackUsingArrayList<T> implements IStack<T> {

	private ArrayList<T> miListaInterna;
	
	public StackUsingArrayList() {
		miListaInterna = new ArrayList<T>(); 
	}
	
	@Override
	public int count() {
		return miListaInterna.size();
	}

	@Override
	public boolean isEmpty() {
		return miListaInterna.isEmpty();
	}

	@Override
	public void push(T value) {
		miListaInterna.add(0, value);
	}

	@Override
	public T pull() {
		return miListaInterna.remove(0);
	}

	@Override
	public T peek() {		
		return miListaInterna.get(0);
	}

}
