/**
 * 
 */
package edu.uvg.bank;

/**
 * @author MAAG
 *
 */
public class SingletonException extends RuntimeException {
	
	//new exception type for singleton classes
	public SingletonException(){
		super();
	}
	
	public SingletonException(String s){
		super(s);
	}
}
