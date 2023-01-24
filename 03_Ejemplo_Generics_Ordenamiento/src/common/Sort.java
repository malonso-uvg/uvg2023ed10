/**
 * 
 */
package common;

import java.io.File;
import java.io.IOException;

/**
 * @author moise
 *
 */
public class Sort <T> {
	public IComparator myCompare;
	
	/**
	 * Constructor of Sort Class, a Icomparator object is necessary to create 
	 * a object of this class
	 * @param _Compare a class that implements IComparator interface necessary
	 * to know how compare the elements of generic arrays. 
	 */
	public Sort(IComparator _Compare) {
		myCompare = _Compare;
	}
	
	/***
	 * Sorts the specified array of objects using the bubble
	 * sort algorithm.
	 * @param myArray List of elements need to be sorted
	 */
	public void BubbleSort(T[] myArray) {
		for (int i = 0; i < myArray.length - 1; i++) {	  //n				// n
			for (int j = i + 1; j < myArray.length; j++) {	// n
				if (myCompare.Compare(myArray[i], myArray[j]) > 0) {    // 1
					T temp = myArray[i];			//1					// 1
					myArray[i] = myArray[j];		//1					// 1
					myArray[j] = temp;			    // 1
				}
			}
		}
	}
	
	/***
	 * Sorts the specified array of objects using the selection
	 * sort algorithm.
	 * @param myArray List of elements need to be sorted
	 */
   public void selectionSort(T[] myArray)
   {
      int min;
      T temp;

      for (int index = 0; index < myArray.length-1; index++)
      {
         min = index;
         for (int scan = index+1; scan < myArray.length; scan++) {
        	 if (myCompare.Compare(myArray[scan], myArray[min]) < 0) {
             	min = scan;
             } 
         }
         
         temp = myArray[min];
         myArray[min] = myArray[index];
         myArray[index] = temp;
      }
   }


   /***
	 * Sorts the specified array of objects using the insertion
	 * sort algorithm.
	 * @param myArray List of elements need to be sorted
	 */
   public void insertionSort (T[] myArray)
   {
      for (int index = 1; index < myArray.length; index++)
      {
         T key = myArray[index];
         int position = index;

         //  Shift larger values to the right
         while (position > 0 && (myCompare.Compare(key, myArray[position-1]) < 0) )
         {
        	 myArray[position] = myArray[position-1];
            position--;
         }
            
         myArray[position] = key;
      }
   }
   
   /***
	 * Sorts the specified array of objects using recursive quicksort 
	 * sort algorithm.
	 * @param myArray List of elements need to be sorted
	 * @param inf lower index limit of the array, when you call this method you should us 0
	 * @param sup upper index limit of the array, when you call this method you should us .size  - 1 or .length - 1 
	 */
   public void quickSort(T[] myArray, int inf, int sup) {
	   int i = inf - 1;
	   int j = sup;
	   boolean flag = true;
	   T temp;
	   
	   
	   if (inf >= sup) {
		   return;
	   }
	   
	   T elem_div = myArray[sup];
	   
	   
	   while (flag) {
		   while(myCompare.Compare(myArray[++i], elem_div) < 0); //Move the index i until it finds an element bigger than elem_div
		   while((myCompare.Compare(myArray[--j], elem_div) > 0)  && (j > inf)); //Move the index j until it finds element smaller than elem_div
		   
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
	   quickSort(myArray, inf, i - 1);
	   quickSort(myArray, i + 1, sup);
   }
}
