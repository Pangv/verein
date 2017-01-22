package de.lebk.verein.login;

/**
 * Created by marcel on 13.01.17.
 */
class WrongPasswordException extends RuntimeException {

    WrongPasswordException() {
        super("Wrong password!");
    }
}
