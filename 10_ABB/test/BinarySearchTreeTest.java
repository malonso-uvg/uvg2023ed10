import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 */

/**
 * @author MAAG
 *
 */
class BinarySearchTreeTest {

	@Test
	void InsetAndInOrderTest() {
		CarnetComparator<Integer> comparadorCarnet = new CarnetComparator<Integer> ();
		ExtraerCarnetDeEstudiante<Integer, Estudiante> extractorCarnet = new ExtraerCarnetDeEstudiante<Integer, Estudiante>(); 
		
		BinarySearchTree<Integer, Estudiante> miArbolEstudiantes = new BinarySearchTree<Integer, Estudiante> (comparadorCarnet, extractorCarnet); 
		
		miArbolEstudiantes.add(new Estudiante(40, "Estudiante Prueba 1"));
		
		miArbolEstudiantes.add(new Estudiante(20, "Estudiante Prueba 2"));
		
		miArbolEstudiantes.add(new Estudiante(70, "Estudiante Prueba 3"));
		
		miArbolEstudiantes.add(new Estudiante(25, "Estudiante Prueba 4"));
		
		miArbolEstudiantes.add(new Estudiante(85, "Estudiante Prueba 5"));
		
		GuardarEstudianteEnArrayList<Integer, Estudiante> miRecorridoInorder = new GuardarEstudianteEnArrayList<Integer, Estudiante>(); 
		
		miArbolEstudiantes.InOrderTraversal(miRecorridoInorder);
		
		assertEquals(20, miRecorridoInorder.getMiListado().get(0).getCarnet());
		assertEquals(25, miRecorridoInorder.getMiListado().get(1).getCarnet());
		assertEquals(40, miRecorridoInorder.getMiListado().get(2).getCarnet());
		assertEquals(70, miRecorridoInorder.getMiListado().get(3).getCarnet());
		assertEquals(85, miRecorridoInorder.getMiListado().get(4).getCarnet());
		
	}
	
	
	
	void InsetAndPostOrderTest() {
		CarnetComparator<Integer> comparadorCarnet = new CarnetComparator<Integer> ();
		ExtraerCarnetDeEstudiante<Integer, Estudiante> extractorCarnet = new ExtraerCarnetDeEstudiante<Integer, Estudiante>(); 
		
		BinarySearchTree<Integer, Estudiante> miArbolEstudiantes = new BinarySearchTree<Integer, Estudiante> (comparadorCarnet, extractorCarnet); 
		
		miArbolEstudiantes.add(new Estudiante(17, "Estudiante Prueba 1"));
		
		miArbolEstudiantes.add(new Estudiante(3, "Estudiante Prueba 2"));
		
		miArbolEstudiantes.add(new Estudiante(100, "Estudiante Prueba 3"));
		
		
		GuardarEstudianteEnArrayList<Integer, Estudiante> miRecorridoPostOrder = new GuardarEstudianteEnArrayList<Integer, Estudiante>();
		miArbolEstudiantes.PostOrderTraversal(miRecorridoPostOrder);
		
		assertEquals(3, miRecorridoPostOrder.getMiListado().get(0).getCarnet());
		assertEquals(100, miRecorridoPostOrder.getMiListado().get(1).getCarnet());
		assertEquals(17, miRecorridoPostOrder.getMiListado().get(2).getCarnet());
		
	}
	
	
	void InsetAndPreOrderTest() {
		CarnetComparator<Integer> comparadorCarnet = new CarnetComparator<Integer> ();
		ExtraerCarnetDeEstudiante<Integer, Estudiante> extractorCarnet = new ExtraerCarnetDeEstudiante<Integer, Estudiante>(); 
		
		BinarySearchTree<Integer, Estudiante> miArbolEstudiantes = new BinarySearchTree<Integer, Estudiante> (comparadorCarnet, extractorCarnet); 
		
		miArbolEstudiantes.add(new Estudiante(40, "Estudiante Prueba 1"));
		
		miArbolEstudiantes.add(new Estudiante(20, "Estudiante Prueba 2"));
		
		miArbolEstudiantes.add(new Estudiante(70, "Estudiante Prueba 3"));
		
		miArbolEstudiantes.add(new Estudiante(25, "Estudiante Prueba 4"));
		
		miArbolEstudiantes.add(new Estudiante(85, "Estudiante Prueba 5"));
		
		GuardarEstudianteEnArrayList<Integer, Estudiante> miRecorridoPreorder = new GuardarEstudianteEnArrayList<Integer, Estudiante>(); 
		
		miArbolEstudiantes.PreOrderTraversal(miRecorridoPreorder);
		
		assertEquals(40, miRecorridoPreorder.getMiListado().get(0).getCarnet());
		assertEquals(20, miRecorridoPreorder.getMiListado().get(1).getCarnet());
		assertEquals(25, miRecorridoPreorder.getMiListado().get(2).getCarnet());
		assertEquals(70, miRecorridoPreorder.getMiListado().get(3).getCarnet());
		assertEquals(85, miRecorridoPreorder.getMiListado().get(4).getCarnet());		
	}
	

}
