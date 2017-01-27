package de.lebk.verein.login;

/**
 * @author mraddatz
 */
class WrongPasswordException extends RuntimeException {

    WrongPasswordException() {
        super("Wrong password!");
    }
}
