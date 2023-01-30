/**
 * 
 */
package gui;

import java.util.Comparator;

/**
 * @author MAAG
 *
 */
public class BubbleSort<T> {
	
	public void sort(T[] arreglo, Comparator<T> comparador) {
		for (int i = 0; i < arreglo.length - 1; i++) {
			for (int j = i + 1; j < arreglo.length; j++) {
				if (comparador.compare(arreglo[i], arreglo[j]) > 0) {
					T aux = arreglo[j];
					arreglo[j] = arreglo[i];
					arreglo[i] = aux;
				} 
			}
		}
	}
}
