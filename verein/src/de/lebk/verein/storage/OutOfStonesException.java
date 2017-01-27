package de.lebk.verein.storage;

/**
 * Created by marcel on 27.01.17.
 */
public class OutOfStonesException extends RuntimeException {
	public OutOfStonesException() {
		super("Not enough stones in storage");
	}
}
