import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IterativeHeapTest {

	@Test
	void test() {
		HeapUsingIterativeBinaryTree<Integer, String> myHeap = new HeapUsingIterativeBinaryTree<Integer, String>(new ComparadorNumeros<Integer>());
		myHeap.Insert(20, "veinte");
		myHeap.Insert(10, "diez");
		myHeap.Insert(15, "quince");
		myHeap.Insert(5, "cinco");
		myHeap.Insert(10, "diez");
		myHeap.Insert(15, "quince");
		
		assertEquals("veinte", myHeap.remove());
		assertEquals("quince", myHeap.remove());
		assertEquals("quince", myHeap.remove());
		assertEquals("diez", myHeap.remove());
		assertEquals("diez", myHeap.remove());
		assertEquals("cinco", myHeap.remove());
		
		
	}

}
