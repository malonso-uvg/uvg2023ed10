/**
 * 
 */
package edu.uvg.structures;

import java.util.ArrayList;

/**
 * @author MAAG
 *
 */
public interface IBinarySearchTree<K, V> {

	void insert(K id, V value);
	
	V delete(K id);
	
	V find(K id);
	
	int count();
	
	boolean isEmpty();
	
	ArrayList<V> getElements();
	
	void inOrder(ITreeTraversal<V> traversal);
	
	void preOrder(ITreeTraversal<V> traversal);
	
	void postOrder(ITreeTraversal<V> traversal);
	
}
