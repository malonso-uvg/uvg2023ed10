import java.util.Comparator;

/**
 * 
 */

/**
 * @author MAAG
 *
 */
public class BinarySearchTree<K, V> {

	private TreeNode<K, V> root;
	private Comparator<K> keyComparator;
	private IGetKey<K, V> keyGenerator;
	private boolean isEmpty;
	private int count;
	
	/**

	Constructs an empty binary search tree with the specified comparator and key generator
	@param _keyComparator the comparator to use for comparing keys
	@param _keyGenerator the key generator to use for generating keys from values
	*/
	public BinarySearchTree(Comparator<K> _keyComparator, IGetKey<K, V> _keyGenerator) {
		root = null;
		keyComparator = _keyComparator;
		keyGenerator = _keyGenerator;
		isEmpty = true;
		count = 0;
	}
	
	/**

	Adds the specified value to the tree
	@param value the value to be added
	*/
	public void add(V value) {
		TreeNode<K, V> newNode = new TreeNode<K, V>((K)keyGenerator.getKeyFromValue(value), value);
		if (isEmpty) { //Es el primer elemento que se inserta
			root = newNode;
			isEmpty = false;
			count++;
		} else { //Ya hay elementos insertados
			internalInsert(root, newNode);
		}
	}
	
	/**

	Recursively inserts a new node into the tree
	@param actualNode the node currently being compared
	@param newNode the node to be inserted
	*/
	private void internalInsert(TreeNode<K, V> actualNode, TreeNode<K, V> newNode) {
		int result = keyComparator.compare(actualNode.getKey(), newNode.getKey());
		//si actual es mayor entonces da un nuemero posito
		//si actual es menor entonces da un numero negativo
		//si son iguales da 0
		
		if (result > 0) { //Inserto el nuevo a la izquierda porque es menor
			if (actualNode.getLeft() == null) { //La izquierda esta vacia
				actualNode.setLeft(newNode);
				newNode.setParent(actualNode);
				count++;
			} else { //Existe nodo en la izquierda entonces ahora nuevo se compara con este
				internalInsert(actualNode.getLeft() ,newNode);
			}
		} else if (result < 0) { //Inserto el nuevo a la derecha porque es mayor
			if (actualNode.getRight() == null) { //La derecha esta vacia
				actualNode.setRight(newNode);
				newNode.setParent(actualNode);
				count++;
			} else { //Existe nodo en la derecha entonces ahora nuevo se compara con la derecha
				internalInsert(actualNode.getRight() ,newNode);
			}
		}
	}
	
	/**

	Inserts a node with the specified key and value into the tree
	@param id the key to be inserted
	@param value the value to be inserted
	*/
	public void insert(K id, V value) {
	
		TreeNode<K, V> newNode = new TreeNode<K, V>(id, value);
		if (isEmpty()) {
			root = newNode;
			count++;
			isEmpty = false;
		} else {
			internalInsert(root, newNode);
		}
	}
	
	/**

	Returns true if the tree is empty, false otherwise
	@return true if the tree is empty, false otherwise
	*/
	public boolean isEmpty() {
		return isEmpty;
	}
	
	/**

	Searches the tree for the node with the specified key and returns its value
	@param key the key to search for
	@return the value of the node with the specified key, or null if no such node exists
	*/
	public V search(K key) {
		return (V) internalSearch(root, key);
	}
	
	/**

	Recursively searches the tree for the node with the specified key and returns its value
	@param actual the node currently being compared
	@param key the key to search for
	@return the value serch
	*/
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
	
	public V delete(K id) {
		if (!isEmpty()) {
			int result = keyComparator.compare(root.getKey(), id);
			
			if (result > 0) {
				return (V)internalDelete(root.getLeft(), id, true);
			} else if (result < 0) {
				return (V)internalDelete(root.getRight(), id, false);
			} else { //Root is the deleted node
				
				if (count() == 1) {
					
					V temp = root.getValue();
					root = null;
					count--;
					return temp;
					
				} else {
					
					V tempValue = root.getValue();
					
					if (root.getRight() != null) { //Buscar hijo derecho mas izquierdo
							
						TreeNode<K, V> leftOfTheRights = root.getRight();
						
						while(leftOfTheRights.getLeft() != null) {
							leftOfTheRights = leftOfTheRights.getLeft(); 
						}
						
						//Assigning the left side
						leftOfTheRights.setLeft(root.getLeft());
						if (leftOfTheRights.getLeft() != null)
							leftOfTheRights.getLeft().setParent(leftOfTheRights);
						
						//Assiginig the right side
						if (keyComparator.compare(root.getRight().getKey(), leftOfTheRights.getKey()) != 0) { //Only if the leftOfTheRights is different than root.right
							leftOfTheRights.getParent().setLeft(null);
							
							TreeNode<K, V> newRootRight = leftOfTheRights;
							
							while (newRootRight.getRight() != null) {
								newRootRight = newRootRight.getRight();
							}
							
							newRootRight.setRight(root.getRight());
							if (newRootRight.getRight() != null) {
								newRootRight.getRight().setParent(newRootRight);;
							}
							
						}
						
						//Assginig the new parents
						if (leftOfTheRights.getRight() != null)
							leftOfTheRights.getRight().setParent(leftOfTheRights);
						
						leftOfTheRights.setParent(null);
						root = leftOfTheRights;
						
						count--;
						return tempValue;
						
					} else { //Buscar hijo izquierdo mas derecho
						
						TreeNode<K, V> rightOfTheLefts = root.getLeft();
						
						while(rightOfTheLefts.getRight() != null) {
							rightOfTheLefts = rightOfTheLefts.getRight(); 
						}
						
						//Assigning the right side
						rightOfTheLefts.setRight(root.getRight());
						if (rightOfTheLefts.getRight() != null)
							rightOfTheLefts.getRight().setParent(rightOfTheLefts);
						
						//Assiginig the left side
						if (keyComparator.compare(root.getLeft().getKey(), rightOfTheLefts.getKey()) != 0) { //Only if the rightOfTheLefts is different than root.left
							rightOfTheLefts.getParent().setRight(null);
							
							TreeNode<K, V> newRootLeft = rightOfTheLefts;
							
							while (newRootLeft.getLeft() != null) {
								newRootLeft = newRootLeft.getLeft();
							}
							
							newRootLeft.setLeft(root.getLeft());
							if (newRootLeft.getLeft() != null) {
								newRootLeft.getLeft().setParent(newRootLeft);;
							}
							
						}
						
						//Assginig the new parentes
						if (rightOfTheLefts.getLeft() != null)
							rightOfTheLefts.getLeft().setParent(rightOfTheLefts);
						
						rightOfTheLefts.setParent(null);
						root = rightOfTheLefts;
						
						count--;
						return tempValue;
						
						
					}
					
					
				}
				
			}
		}
		
		return null;
	}
	
	private V internalDelete(TreeNode<K, V> actual, K id, boolean isLeft) {
		if (actual != null) {
			int result = keyComparator.compare(actual.getKey(), id);
			
			if (result > 0) { //search in the left side
				return internalDelete(actual.getLeft(), id, true);
			} else if (result < 0) {//search in the right side
				return internalDelete(actual.getRight(), id, false);
			} else { //actual is the node to be deleted
				
				//The actual node is a leaft
				if ( (actual.getLeft() == null) && (actual.getRight() == null) ) { // If is a leaft
					V tempValue = actual.getValue();
					if (isLeft) {
						actual.getParent().setLeft(null);
						actual.setParent(null);
					} else {
						actual.getParent().setRight(null);
						actual.setParent(null);
					}
					count--;
					return tempValue;
				} else { //Is intermediate node
				
					V tempValue = actual.getValue();
					
					if (actual.getRight() != null) { //Buscar hijo derecho mas izquierdo
						
						TreeNode<K, V> leftOfTheRights = actual.getRight();
						
						while(leftOfTheRights.getLeft() != null) {
							leftOfTheRights = leftOfTheRights.getLeft(); 
						}
						
						//Assigning the left side
						leftOfTheRights.setLeft(actual.getLeft());
						if (leftOfTheRights.getLeft() != null)
							leftOfTheRights.getLeft().setParent(leftOfTheRights);
						
						//Assiginig the right side
						if (keyComparator.compare(actual.getRight().getKey(), leftOfTheRights.getKey()) != 0) { //Only if the leftOfTheRights is different than root.right
							leftOfTheRights.getParent().setLeft(null);
							
							TreeNode<K, V> newRootRight = leftOfTheRights;
							
							while (newRootRight.getRight() != null) {
								newRootRight = newRootRight.getRight();
							}
							
							newRootRight.setRight(actual.getRight());
							if (newRootRight.getRight() != null) {
								newRootRight.getRight().setParent(newRootRight);;
							}
							
						}
						
						//Assginig the new parentes
						if (leftOfTheRights.getRight() != null)
							leftOfTheRights.getRight().setParent(leftOfTheRights);
						
						//Assigning new son to the parent
						leftOfTheRights.setParent(actual.getParent());
						if (isLeft) {
							leftOfTheRights.getParent().setLeft(leftOfTheRights);
						} else {
							leftOfTheRights.getParent().setRight(leftOfTheRights);
						}
						
						count--;
						return tempValue;
						
					} else { //Buscar hijo izquierdo mas derecho
						
						TreeNode<K, V> rightOfTheLefts = actual.getLeft();
						
						while(rightOfTheLefts.getRight() != null) {
							rightOfTheLefts = rightOfTheLefts.getRight(); 
						}
						
						//Assigning the right side
						rightOfTheLefts.setRight(actual.getRight());
						if (rightOfTheLefts.getRight() != null)
							rightOfTheLefts.getRight().setParent(rightOfTheLefts);
						
						//Assiginig the left side
						if (keyComparator.compare(actual.getLeft().getKey(), rightOfTheLefts.getKey()) != 0) { //Only if the rightOfTheLefts is different than root.left
							rightOfTheLefts.getParent().setRight(null);
							
							TreeNode<K, V> newRootLeft = rightOfTheLefts;
							
							while (newRootLeft.getLeft() != null) {
								newRootLeft = newRootLeft.getLeft();
							}
							
							newRootLeft.setLeft(actual.getLeft());
							if (newRootLeft.getLeft() != null) {
								newRootLeft.getLeft().setParent(newRootLeft);;
							}
							
						}
						
						//Assginig the new parentes
						if (rightOfTheLefts.getLeft() != null)
							rightOfTheLefts.getLeft().setParent(rightOfTheLefts);
						
						rightOfTheLefts.setParent(actual.getParent());
						if (isLeft) {
							rightOfTheLefts.getParent().setLeft(rightOfTheLefts);
						} else {
							rightOfTheLefts.getParent().setRight(rightOfTheLefts);
						}
						
						count--;
						return tempValue;
						
						
					}
					
					
					
					
					
					
					
					
				}
				
			}
			 
		} else {
			return null;
		}
	}
	
	public int count() {
		return count;
	}
}
