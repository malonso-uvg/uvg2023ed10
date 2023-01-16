package edu.uvg.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.uvg.structures.BinarySearchTree;
import edu.uvg.structures.EjemploRecorrido;
import edu.uvg.structures.IntegerComparator;

class BinarySearchTreeTestSuite {

	@Test
	void InsertOneElementTest() {
        BinarySearchTree<Integer, String> myBST = new BinarySearchTree<Integer, String>(new IntegerComparator<Integer>());
        myBST.insert(10, "test");
        assertEquals(1, myBST.count());
        assertEquals("test", myBST.getElements().get(0));
	}
	
	@Test
	void InsertMultipleElementsTest() {
        BinarySearchTree<Integer, String> myBST = new BinarySearchTree<Integer, String>(new IntegerComparator<Integer>());
        myBST.insert(50, "cincuenta");
        myBST.insert(80, "ochenta");
        myBST.insert(90, "noventa");
        myBST.insert(60, "sesenta");
        myBST.insert(20, "veinte");
        myBST.insert(10, "diez");
        myBST.insert(30, "treinta");
        
        assertEquals(7, myBST.count());
        
        assertEquals("diez", myBST.getElements().get(0));
        assertEquals("veinte", myBST.getElements().get(1));
        assertEquals("treinta", myBST.getElements().get(2));
        assertEquals("cincuenta", myBST.getElements().get(3));
        assertEquals("sesenta", myBST.getElements().get(4));
        assertEquals("ochenta", myBST.getElements().get(5));
        assertEquals("noventa", myBST.getElements().get(6));
	}
	
	@Test
	void InOrderWalkTest() {
        BinarySearchTree<Integer, String> myBST = new BinarySearchTree<Integer, String>(new IntegerComparator<Integer>());
        myBST.insert(50, "cincuenta");
        myBST.insert(80, "ochenta");
        myBST.insert(90, "noventa");
        myBST.insert(60, "sesenta");
        myBST.insert(20, "veinte");
        myBST.insert(10, "diez");
        myBST.insert(30, "treinta");
        
        assertEquals(7, myBST.count());
        
        EjemploRecorrido<String> miRecorrido = new EjemploRecorrido<String>();
        myBST.inOrder(miRecorrido);
        
        assertEquals("diez", miRecorrido.miLista.get(0));
        assertEquals("veinte", miRecorrido.miLista.get(1));
        assertEquals("treinta", miRecorrido.miLista.get(2));
        assertEquals("cincuenta", miRecorrido.miLista.get(3));
        assertEquals("sesenta", miRecorrido.miLista.get(4));
        assertEquals("ochenta", miRecorrido.miLista.get(5));
        assertEquals("noventa", miRecorrido.miLista.get(6));
	}
	
	@Test
	void PreOrderWalkTest() {
        BinarySearchTree<Integer, String> myBST = new BinarySearchTree<Integer, String>(new IntegerComparator<Integer>());
        myBST.insert(50, "cincuenta");
        myBST.insert(80, "ochenta");
        myBST.insert(90, "noventa");
        myBST.insert(60, "sesenta");
        myBST.insert(20, "veinte");
        myBST.insert(10, "diez");
        myBST.insert(30, "treinta");
        
        assertEquals(7, myBST.count());
        
        EjemploRecorrido<String> miRecorrido = new EjemploRecorrido<String>();
        myBST.preOrder(miRecorrido);
        
        assertEquals("cincuenta", miRecorrido.miLista.get(0));
        assertEquals("veinte", miRecorrido.miLista.get(1));
        assertEquals("diez", miRecorrido.miLista.get(2));
        assertEquals("treinta", miRecorrido.miLista.get(3));
        assertEquals("ochenta", miRecorrido.miLista.get(4));
        assertEquals("sesenta", miRecorrido.miLista.get(5));
        assertEquals("noventa", miRecorrido.miLista.get(6));
	}

	@Test
	void PostOrderWalkTest() {
        BinarySearchTree<Integer, String> myBST = new BinarySearchTree<Integer, String>(new IntegerComparator<Integer>());
        myBST.insert(50, "cincuenta");
        myBST.insert(80, "ochenta");
        myBST.insert(90, "noventa");
        myBST.insert(60, "sesenta");
        myBST.insert(20, "veinte");
        myBST.insert(10, "diez");
        myBST.insert(30, "treinta");
        
        assertEquals(7, myBST.count());
        
        EjemploRecorrido<String> miRecorrido = new EjemploRecorrido<String>();
        myBST.postOrder(miRecorrido);
        
        assertEquals("diez", miRecorrido.miLista.get(0));
        assertEquals("treinta", miRecorrido.miLista.get(1));
        assertEquals("veinte", miRecorrido.miLista.get(2));
        assertEquals("sesenta", miRecorrido.miLista.get(3));
        assertEquals("noventa", miRecorrido.miLista.get(4));
        assertEquals("ochenta", miRecorrido.miLista.get(5));
        assertEquals("cincuenta", miRecorrido.miLista.get(6));
	}
	
	@Test
	void findTest() {
        BinarySearchTree<Integer, String> myBST = new BinarySearchTree<Integer, String>(new IntegerComparator<Integer>());
        
        assertEquals(true, myBST.isEmpty());
        
        myBST.insert(50, "cincuenta");
        myBST.insert(80, "ochenta");
        myBST.insert(90, "noventa");
        myBST.insert(60, "sesenta");
        myBST.insert(20, "veinte");
        myBST.insert(10, "diez");
        myBST.insert(30, "treinta");
        
        assertEquals(7, myBST.count());
        
        assertEquals(false, myBST.isEmpty());
        
        assertEquals("sesenta", myBST.find(60));
	}
	
	@Test
	void DeleteRootOnlyOneElementTest() {
        BinarySearchTree<Integer, String> myBST = new BinarySearchTree<Integer, String>(new IntegerComparator<Integer>());
        
        assertEquals(true, myBST.isEmpty());
        
        myBST.insert(50, "cincuenta");
        
        assertEquals(1, myBST.count());
        
        assertEquals("cincuenta", myBST.delete(50));
        
        assertEquals(0, myBST.count());
        
        assertEquals(true, myBST.isEmpty());
        
	}

	
	@Test
	void DeleteLeaftTest() {
        BinarySearchTree<Integer, String> myBST = new BinarySearchTree<Integer, String>(new IntegerComparator<Integer>());
        
        assertEquals(true, myBST.isEmpty());
        
        myBST.insert(50, "cincuenta");
        myBST.insert(80, "ochenta");
        myBST.insert(90, "noventa");
        myBST.insert(60, "sesenta");
        myBST.insert(20, "veinte");
        myBST.insert(10, "diez");
        myBST.insert(30, "treinta");
        
        assertEquals(7, myBST.count());
        
        assertEquals("noventa", myBST.delete(90));

        EjemploRecorrido<String> miRecorrido = new EjemploRecorrido<String>();
        myBST.inOrder(miRecorrido);
        
        assertEquals("diez", miRecorrido.miLista.get(0));
        assertEquals("veinte", miRecorrido.miLista.get(1));
        assertEquals("treinta", miRecorrido.miLista.get(2));
        assertEquals("cincuenta", miRecorrido.miLista.get(3));
        assertEquals("sesenta", miRecorrido.miLista.get(4));
        assertEquals("ochenta", miRecorrido.miLista.get(5));
        
	}
	
	@Test
	void DeleteRootMoreThanOneElementTest() {
        BinarySearchTree<Integer, String> myBST = new BinarySearchTree<Integer, String>(new IntegerComparator<Integer>());
        
        assertEquals(true, myBST.isEmpty());
        
        myBST.insert(50, "cincuenta");
        myBST.insert(80, "ochenta");
        myBST.insert(90, "noventa");
        myBST.insert(60, "sesenta");
        myBST.insert(20, "veinte");
        myBST.insert(10, "diez");
        myBST.insert(30, "treinta");
        
        assertEquals(7, myBST.count());
        
        assertEquals("cincuenta", myBST.delete(50));

        EjemploRecorrido<String> miRecorrido = new EjemploRecorrido<String>();
        myBST.inOrder(miRecorrido);
        
        assertEquals("diez", miRecorrido.miLista.get(0));
        assertEquals("veinte", miRecorrido.miLista.get(1));
        assertEquals("treinta", miRecorrido.miLista.get(2));
        assertEquals("sesenta", miRecorrido.miLista.get(3));
        assertEquals("ochenta", miRecorrido.miLista.get(4));
        assertEquals("noventa", miRecorrido.miLista.get(5));
        
	}
	
	
	@Test
	void DeleteIntermediateNodeMoreThanOneElementTest() {
        BinarySearchTree<Integer, String> myBST = new BinarySearchTree<Integer, String>(new IntegerComparator<Integer>());
        
        assertEquals(true, myBST.isEmpty());
        
        myBST.insert(50, "cincuenta");
        myBST.insert(80, "ochenta");
        myBST.insert(90, "noventa");
        myBST.insert(60, "sesenta");
        myBST.insert(20, "veinte");
        myBST.insert(10, "diez");
        myBST.insert(30, "treinta");
        
        assertEquals(7, myBST.count());
        
        assertEquals("veinte", myBST.delete(20));

        EjemploRecorrido<String> miRecorrido = new EjemploRecorrido<String>();
        myBST.inOrder(miRecorrido);
        
        assertEquals("diez", miRecorrido.miLista.get(0));
        assertEquals("treinta", miRecorrido.miLista.get(1));
        assertEquals("cincuenta", miRecorrido.miLista.get(2));
        assertEquals("sesenta", miRecorrido.miLista.get(3));
        assertEquals("ochenta", miRecorrido.miLista.get(4));
        assertEquals("noventa", miRecorrido.miLista.get(5));
        
	}



}
