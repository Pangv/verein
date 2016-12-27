package de.lebk.verein.entry;

import de.lebk.verein.login.LoginDialog;
import de.lebk.verein.member.Member;
import de.lebk.verein.utilities.MainFrame;

import java.util.GregorianCalendar;

/**
 *
 * @author sopaetzel
 */
public class Entry {

    private static MainFrame mainFrame;
    private static LoginDialog loginDialog;

    // TODO: Remove later
    static boolean loggedIn = true;

    public static void main(String[] args) {
        System.out.println("It is alive!");

		Member test = new Member("John-Ebenezer ", "Scrooge Doe", "secret", "awesomeUser", 'm',
			new GregorianCalendar());

		mainFrame = new MainFrame(test, loggedIn);

    }

}
