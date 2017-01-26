package de.lebk.verein.login;

import javax.swing.JOptionPane;

/**
 *
 * @author Marcel Raddatz
 */
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException() {
		super("No such user");
	}

	public UserNotFoundException(String username) {
		super("No such user: " + username);
	}

}
