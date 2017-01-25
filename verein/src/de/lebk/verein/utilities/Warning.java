package de.lebk.verein.utilities;

import javax.swing.*;

/**
 *
 * @author sopaetzel
 */
public class Warning extends JOptionPane {

    public Warning(Object message, int messageType) {
        super(message, messageType);
    }

    public static void displayWarning(Object error_message, String message) {
        Warning warning = new Warning(message, WARNING_MESSAGE);
        System.err.println(error_message);
        showMessageDialog(null, warning.message);
    }

}
