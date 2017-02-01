package de.lebk.verein.storage;

/**
 * @author mraddatz
 */
 class OutOfStonesException extends RuntimeException {
     OutOfStonesException() {
        super("Not enough stones in storage");
    }
}
