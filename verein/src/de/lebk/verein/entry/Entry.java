package de.lebk.verein.entry;

import de.lebk.verein.view.dialog.LoginDialog;
import de.lebk.verein.view.MainFrame;

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

    mainFrame = new MainFrame(loggedIn, "Steve Biffer");


  }

}
