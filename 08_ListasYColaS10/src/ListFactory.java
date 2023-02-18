/**
 * 
 */
import structure5.AbstractList;
import structure5.SinglyLinkedList;
import structure5.DoublyLinkedList;

/**
 * @author MAAG
 *
 */
public class ListFactory<T> {
	
	public AbstractList<T> getInstance(String type){
		
		AbstractList<T> listInstance;
		
		switch(type.toLowerCase()) {
		case "single":{
			listInstance = new SinglyLinkedList<T>();
		}break;
		
		case "double":{
			listInstance = new DoublyLinkedList<T>();
		}break;
		
		default:{
			listInstance = null;
		}break;
		}
		
		return listInstance;
	}

}
