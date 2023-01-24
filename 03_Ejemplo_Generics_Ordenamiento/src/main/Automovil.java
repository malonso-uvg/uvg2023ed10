/**
 * 
 */
package main;

import common.IComparator;

/**
 * @author moises
 *
 */
public class Automovil {
	
	private String marca;
	private String linea;
	private int anio_fabricacion;
	private double deposito_gasolina_actual;
	
	public Automovil() {
		marca = "Marca no asignada";
		linea = "Linea no asignada";
		anio_fabricacion = 2000;
		deposito_gasolina_actual = 1.5;
	}
	
	public Automovil(String _marca, String _linea, int _anio, double _cantidad_gasolina) {
		setMarca(_marca);
		setLinea(_linea);
		setAnio_Fabricacion(_anio);
		RepostarGasolina(_cantidad_gasolina);
	}
	
	/*Seccion de setters*/
	public void setMarca(String _marca) {
		if (!_marca.trim().equals("")) {
			marca = _marca;
		} 
	}
	
	public void setLinea(String _linea) {
		if (!_linea.trim().equals("")) {
			linea = _linea;
		}
	}
	
	public void setAnio_Fabricacion(int _anio) {
		if (_anio > 0) {
			anio_fabricacion = _anio;
		}
	}
	
	public void RepostarGasolina(double cantidad_galones) {
		
		double diferencia = 10 - deposito_gasolina_actual;
		
		if (cantidad_galones > diferencia) {
			deposito_gasolina_actual = 10;
		} else {
			deposito_gasolina_actual = deposito_gasolina_actual + cantidad_galones;
		}
		
	}
	
	/*Seccion de getters*/
	public String getMarca() {
		return marca;
	}
	
	public String getLinea() {
		return linea;
	}
	
	public int getAnio_Fabricacion() {
		return anio_fabricacion;
	}
	
	public double getCantidadGasolinaActual() {
		return deposito_gasolina_actual;
	}


}
