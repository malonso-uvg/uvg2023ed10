import java.util.ArrayList;

/**
 * 
 */

/**
 * @author MAAG
 *
 */
public class GuardarEstudianteEnArrayList<K, V> implements ITraversal<K, V> {

	private ArrayList<Estudiante> miListado = new ArrayList<Estudiante>();
	
	@Override
	public void visit(TreeNode<K, V> actualNode) {
		Estudiante estudianteActual = (Estudiante) actualNode.getValue();
		miListado.add(estudianteActual);
	}

	/**
	 * @return the miListado
	 */
	public ArrayList<Estudiante> getMiListado() {
		return miListado;
	}

	/**
	 * @param miListado the miListado to set
	 */
	public void setMiListado(ArrayList<Estudiante> miListado) {
		this.miListado = miListado;
	}

	
	
}
