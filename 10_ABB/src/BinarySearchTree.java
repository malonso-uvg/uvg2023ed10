import java.util.Comparator;

/**
 * 
 */

/**
 * @author MAAG
 *
 */
public class BinarySearchTree<K, V> {

	private TreeNode root;
	private Comparator keyComparator;
	private IGetKey keyGenerator;
	private boolean isEmpty;
	
	public BinarySearchTree(Comparator _keyComparator, IGetKey _keyGenerator) {
		root = null;
		keyComparator = _keyComparator;
		keyGenerator = _keyGenerator;
		isEmpty = true;
	}
	
	public void add(V value) {
		if (isEmpty) { //Es el primer elemento que se inserta
			TreeNode newNode = new TreeNode(keyGenerator.getKeyFromValue(value), value);
			root = newNode;
			isEmpty = false;
		} else { //Ya hay elementos insertados
			TreeNode newNode = new TreeNode(keyGenerator.getKeyFromValue(value), value);
			ineternalInsert(root, newNode);
		}
	}
	
	private void ineternalInsert(TreeNode actualNode, TreeNode newNode) {
		int result = keyComparator.compare(actualNode.getKey(), newNode.getKey());
		//si actual es mayor entonces da un nuemero posito
		//si actual es menor entonces da un numero negativo
		//si son iguales da 0
		
		if (result > 0) { //Inserto el nuevo a la izquierda porque es menor
			if (actualNode.getLeft() == null) { //La izquierda esta vacia
				actualNode.setLeft(newNode);
				newNode.setParent(actualNode);
			} else { //Existe nodo en la izquierda entonces ahora nuevo se compara con este
				ineternalInsert(actualNode.getLeft() ,newNode);
			}
		} else if (result < 0) { //Inserto el nuevo a la derecha porque es mayor
			if (actualNode.getRight() == null) { //La derecha esta vacia
				actualNode.setRight(newNode);
				newNode.setParent(actualNode);
			} else { //Existe nodo en la derecha entonces ahora nuevo se compara con la derecha
				ineternalInsert(actualNode.getRight() ,newNode);
			}
		}
	}
	
	public V search(K key) {
		return (V) internalSearch(root, key);
	}
	
	private V internalSearch(TreeNode<K, V> actual, K key) {
		
		if (actual != null) {
			
			int result = keyComparator.compare(actual.getKey(), key);
			
			// SI result es 0 entonces son iguales
			if (result == 0) {
				return actual.getValue();
			} else if (result > 0) {
				return (V) internalSearch(actual.getLeft(), key);
			}else {
				return (V) internalSearch(actual.getRight(), key);
			}
			
			
		} else {
			return null;
		}
		
		
	}
	
	public void InOrderTraversal(ITraversal<K, V> visitador) {
		internalInOrder(root, visitador);
	}
	
	private void internalInOrder(TreeNode<K, V> actual, ITraversal<K, V> visitador) {
		
		if (actual != null) {
			
			if (actual.getLeft() != null) {
				internalInOrder(actual.getLeft(), visitador);
			}
			
			visitador.visit(actual);
			
			if (actual.getRight() != null) {
				internalInOrder(actual.getRight(), visitador);
			}
			
		}
		
	}
	
	
	public void PostOrderTraversal(ITraversal<K, V> visitador) {
		internalPostOrder(root, visitador);
	}
	
	private void internalPostOrder(TreeNode<K, V> actual, ITraversal<K, V> visitador) {
		
		if (actual != null) {
			
			if (actual.getLeft() != null) {
				internalPostOrder(actual.getLeft(), visitador);
			}
			
			if (actual.getRight() != null) {
				internalPostOrder(actual.getRight(), visitador);
			}
			
			visitador.visit(actual);
			
		}
		
	}
	
	
	public void PreOrderTraversal(ITraversal<K, V> visitador) {
		internalPreOrder(root, visitador);
	}
	
	private void internalPreOrder(TreeNode<K, V> actual, ITraversal<K, V> visitador) {
		
		if (actual != null) {
			
			visitador.visit(actual);
			
			if (actual.getLeft() != null) {
				internalPreOrder(actual.getLeft(), visitador);
			}
			
			if (actual.getRight() != null) {
				internalPreOrder(actual.getRight(), visitador);
			}
			
		}
		
	}
}
