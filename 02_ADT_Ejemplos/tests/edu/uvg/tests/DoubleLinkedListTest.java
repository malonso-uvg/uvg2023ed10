/**
 * 
 */
package edu.uvg.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.uvg.structures.DoubleLinkedList;
import edu.uvg.structures.SingleLinkedList;

/**
 * @author MAAG
 *
 */
class DoubleLinkedListTest {

	@Test
	void InsertAtStartEmptyListtest() {
		DoubleLinkedList<Integer> testList = new DoubleLinkedList<Integer>();
		testList.InsertAtStart(3);
		assertEquals(testList.Get(0), 3);
	}
	
	@Test
	void InsertAtStartNonEmptyListtest() {
		DoubleLinkedList<Integer> testList = new DoubleLinkedList<Integer>();
		testList.InsertAtStart(3);
		testList.InsertAtStart(5);
		assertEquals(testList.Get(0), 5);
		assertEquals(testList.Count(), 2);
		assertEquals(testList.Get(1), 3);
	}
	
	@Test
	void InsertAtEndEmptyListtest() {
		DoubleLinkedList<String> testList = new DoubleLinkedList<String>();
		testList.InsertAtEnd("Hola");
		assertEquals(testList.Get(0), "Hola");
	}
	
	@Test
	void InsertAtEndNonEmptyListtest() {
		DoubleLinkedList<String> testList = new DoubleLinkedList<String>();
		testList.InsertAtEnd("Hola");
		testList.InsertAtEnd("Mundo");
		assertEquals(testList.Get(0), "Hola");
		assertEquals(testList.Count(), 2);
		assertEquals(testList.Get(1), "Mundo");
	}
	
	@Test
	void InsertAtIndexNonEmptyListtest() {
		DoubleLinkedList<String> testList = new DoubleLinkedList<String>();
		testList.InsertAtStart("Hola");
		testList.InsertAtEnd("Mundo");
		testList.Insert("UVG", 1);
		testList.Insert("GT", 1);
		
		assertEquals(testList.Count(), 4);
		assertEquals(testList.Get(0), "Hola");
		assertEquals(testList.Get(1), "GT");
		assertEquals(testList.Get(2), "UVG");
		assertEquals(testList.Get(3), "Mundo");
	}
	

	@Test
	void InsertAtIndexEmptyListtest() {
		DoubleLinkedList<String> testList = new DoubleLinkedList<String>();
		testList.Insert("Hola", 5);
		assertEquals(testList.Count(), 1);
		assertEquals(testList.Get(0), "Hola");
	}
	
	@Test
	void DeleteAtStartNonEmptyOnlyOneElementListtest() {
		DoubleLinkedList<String> testList = new DoubleLinkedList<String>();
		testList.InsertAtStart("Hola");
		assertEquals(testList.Count(), 1);
		assertEquals(testList.DeleteAtStart(), "Hola");
		assertEquals(testList.Count(), 0);
	}
	
	@Test
	void DeleteAtStartNonEmptyManyElementsListtest() {
		DoubleLinkedList<String> testList = new DoubleLinkedList<String>();
		testList.InsertAtStart("Hola");
		testList.InsertAtStart("Mundo");
		assertEquals(testList.Count(), 2);
		assertEquals(testList.DeleteAtStart(), "Mundo");
		assertEquals(testList.Count(), 1);
		assertEquals(testList.DeleteAtStart(), "Hola");
		assertEquals(testList.Count(), 0);
	}

}
