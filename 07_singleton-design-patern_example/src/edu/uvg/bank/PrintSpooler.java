/**
 * 
 */
package edu.uvg.bank;

/**
 * @author MAAG
 *
 */
public class PrintSpooler {

	//this is a prototype for a printer-spooler class
	//such that only one instance can ever exist
	private static boolean instance_flag = false; //true if 1 instance
	private static PrintSpooler _theOnlyPrintSpooler;
	private int numberOfCopies;
	
	private PrintSpooler() throws SingletonException {
		numberOfCopies = 0;
		instance_flag = true;
	}
	
	public static PrintSpooler getInstance() {
		if (instance_flag) {
			return _theOnlyPrintSpooler;
		} else {
			_theOnlyPrintSpooler = new PrintSpooler();
			return _theOnlyPrintSpooler;
		}
	}
	
	public void newCopy() {
		numberOfCopies++;
	}
	
	public int getNumberOfCopies() {
		return numberOfCopies;
	}
	
	public void finalize() {
		instance_flag = false; //clear if destroyed
	}
}
