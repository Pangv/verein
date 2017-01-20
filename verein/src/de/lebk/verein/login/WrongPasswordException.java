package de.lebk.verein.login;

/**
 * Created by marcel on 13.01.17.
 */
public class WrongPasswordException extends RuntimeException {
	public WrongPasswordException() {
		super("Wrong password!");
	}
}
