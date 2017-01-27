package de.lebk.verein.storage;

/**
 * @author mraddatz
 */
public class OutOfStonesException extends RuntimeException {
	public OutOfStonesException() {
		super("Not enough stones in storage");
	}
}
