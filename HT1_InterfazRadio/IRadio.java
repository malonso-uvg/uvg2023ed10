/**
 * @author moises.alonso
 */
public interface IRadio {
	
	public void on();
	
	public void off();
	
	/***
	 * Este metodo nos indica si la radio esta encendida o apagada
	 * @return true si la radio esta encendida y false cuando la radio este apagada
	 */
	public boolean isOn();
	
	/***
	 * Este metodo nos ayuda a establecer la frecuencia, recibe un parametro llamado freq que puede "AM" o "FM"
	 * @param freq La frecuencia la cual puede ser AM o FM, de lo contrario error.
	 */
	public void setFrequence(String freq) throws Exception;
	
	public String getFrequence();
	
	public void Forward();
	
	public void Backward();
	
	public double getFMActualStation();
	
	public int getAMActualStation();
	
	public void setFMActualStation(double actualStation);
	
	public void setAMActualStation(int actualStation); 
	
	public void saveFMStation(double actualStation, int slot);
	
	public void saveAMStation(int actualStation, int slot);
	
	public double getFMSlot(int slot);
	
	public int getAMSlot(int slot);
}