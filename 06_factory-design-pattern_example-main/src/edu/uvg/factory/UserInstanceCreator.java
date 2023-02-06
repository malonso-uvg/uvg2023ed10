/**
 * 
 */
package edu.uvg.factory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author MAAG
 *
 */
public class UserInstanceCreator {
	
	public User getInstance(String username) {
		User myUser;
		
		switch(getType(username)) {
		case 1:{
			myUser = new Student();
		}break;
		
		case 2:{
			myUser = new Professor();
		}break;
		case 3:{
			myUser = new Employee();
		}break;
		default:{
			myUser = null;
		}break;
		}
		
		return myUser;
	}

	private int getType(String username) {
		if (evaluate("^[a-z][a-z][a-z][0-9]+@uvg.edu.gt$", username)) //This is a student
			return 1;
		else if (evaluate("^[a-z]+@uvg.edu.gt$",username)) //This is a professor
			return 2;
		else if (evaluate("^[a-z]+_adm@uvg.edu.gt$",username)) //This is an employee
			return 3;
		else 
			return -1; //if no match found then the expression is incorrect
	}
	
	private boolean evaluate(String regex, String expresion) {
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(expresion);
	    return matcher.find();
	}
}
