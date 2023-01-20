/**
 * 
 */
package edu.uvg.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.uvg.structures.IStack;
import edu.uvg.structures.StackHandmade;
import edu.uvg.structures.StackUsingArrayList;
import edu.uvg.structures.StackUsingLinkedList;

/**
 * @author moises.alonso
 *
 */
class StackTests {

	IStack<Integer> myTestedStack = new StackHandmade<Integer>();
	//IStack<Integer> myTestedStack = new StackUsingArrayList<Integer>();
	//IStack<Integer> myTestedStack = new StackUsingLinkedList<Integer>();
	
	@Test
	void CountWhenEmptyTest() {
		assertEquals(0, myTestedStack.count());
		assertEquals(true, myTestedStack.isEmpty());
	}
	
	@Test
	void PushAndPullOperationsTest() {
		myTestedStack.push(10);
		assertEquals(1, myTestedStack.count());
		myTestedStack.push(20);
		assertEquals(2, myTestedStack.count());
		myTestedStack.push(30);
		assertEquals(3, myTestedStack.count());
		assertEquals(30, myTestedStack.pull());
		assertEquals(2, myTestedStack.count());
		assertEquals(20, myTestedStack.pull());
		assertEquals(1, myTestedStack.count());
		assertEquals(10, myTestedStack.pull());
		assertEquals(0, myTestedStack.count());
	}
	
	@Test
	void PushAndPeekOperationsTest() {
		myTestedStack.push(10);
		assertEquals(1, myTestedStack.count());
		assertEquals(10, myTestedStack.peek());
		assertEquals(1, myTestedStack.count());
		assertEquals(10, myTestedStack.pull());
		assertEquals(0, myTestedStack.count());
		//assertEquals(null, myTestedStack.peek());
	}

}
