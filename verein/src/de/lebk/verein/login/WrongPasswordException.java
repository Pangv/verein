package de.lebk.verein.login;

/**
 *
 * @author Marcel Raddatz
 */
class WrongPasswordException extends RuntimeException {

    WrongPasswordException() {
        super("Wrong password!");
    }
}
