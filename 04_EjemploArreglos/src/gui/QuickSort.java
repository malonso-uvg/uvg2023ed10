/**
 * 
 */
package gui;

import java.util.Comparator;

/**
 * @author MAAG
 *
 */
public class QuickSort<T> {

	 /***
		 * Sorts the specified array of objects using recursive quicksort 
		 * sort algorithm.
		 * @param myArray List of elements need to be sorted
		 * @param inf lower index limit of the array, when you call this method you should us 0
		 * @param sup upper index limit of the array, when you call this method you should us .size  - 1 or .length - 1 
		 */
	   public void quickSort(T[] myArray, int inf, int sup, Comparator<T> myCompare) {
		   int i = inf - 1;
		   int j = sup;
		   boolean flag = true;
		   T temp;
		   
		   
		   if (inf >= sup) {
			   return;
		   }
		   
		   T elem_div = myArray[sup];
		   
		   
		   while (flag) {
			   while(myCompare.compare(myArray[++i], elem_div) < 0); //Move the index i until it finds an element bigger than elem_div
			   while((myCompare.compare(myArray[--j], elem_div) > 0)  && (j > inf)); //Move the index j until it finds element smaller than elem_div
			   
			   if (i < j) {
				   temp = myArray[i];
				   myArray[i] = myArray[j];
				   myArray[j] = temp;
			   } else {
				   flag = false;
			   }
		   }
		   
		   temp = myArray[i];
		   myArray[i] = myArray[sup];
		   myArray[sup] = temp;
		   quickSort(myArray, inf, i - 1, myCompare);
		   quickSort(myArray, i + 1, sup, myCompare);
	   }
}
