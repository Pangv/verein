package de.lebk.verein.utilities;

import de.lebk.verein.login.LoginDialog;
import de.lebk.verein.member.ProfileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author sopaetzel
 */
public class MainMenu extends JMenuBar {

  // base menus
  private final JMenu jMenuFile = new JMenu("Datei");
  private final JMenu jMenuTest = new JMenu("Test");
  // sub menus
  private final JMenuItem jMenuLogin = new JMenuItem("Zeige Login");
  private final JMenuItem jMenuProfile = new JMenuItem("Zeige Profil");

  private final JMenuItem jMenuExit = new JMenuItem("Schließen");

  public MainMenu() {
    createComponent();
  }

  private void createComponent() {
    // addItems
    jMenuTest.add(jMenuLogin);
    jMenuTest.add(jMenuProfile);
    jMenuFile.add(jMenuExit);

    // TODO: addTestActions REMOVE LATER
    this.defineTestActions();

    // addMenu
    this.add(jMenuFile);
    this.add(jMenuTest);
  }

  private void defineTestActions() {
    /**
     * Shows the Login dialog
     */
    jMenuLogin.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        LoginDialog l = new LoginDialog(null, "Test Login");

      }
    });

    jMenuProfile.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ProfileDialog d = new ProfileDialog(null, "Test Profile");
      }
    });



    jMenuExit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

  }
}
