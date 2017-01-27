package de.lebk.verein.login;

/**
 * @author mraddatz
 * @author sopaetzel
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("No such user: " + username);
    }

}
