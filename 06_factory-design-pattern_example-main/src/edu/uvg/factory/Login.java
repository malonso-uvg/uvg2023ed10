/**
 * 
 */
package edu.uvg.factory;

import java.util.HashMap;

/**
 * @author MAAG
 *
 */
public class Login {

	HashMap<String, String> users;
	
	public Login() {
		users = new HashMap<String, String>();
		users.put("alo171164@uvg.edu.gt", "password1");
		users.put("maalonso@uvg.edu.gt", "password2");
		users.put("maalonso_adm@uvg.edu.gt", "password3");
	}
	
	public boolean hasAccess(String username, String password) {
		String savedPassword = users.get(username);
		if (savedPassword != null) {
			if (savedPassword.equals(password)) {
				return true;
			} else {
				return false;
			}
			
		} else {
			return false;
		}
	}
}
