package de.lebk.verein.login;

import javax.swing.JOptionPane;

/**
 *
 * @author Sven-Oliver Pätzel
 */
class UserNotFoundException extends Exception {

    UserNotFoundException() {
        JOptionPane.showMessageDialog(null, "Username oder Password nicht korrekt.", "Nicht gefunden", JOptionPane.ERROR_MESSAGE);
    }
    
}
