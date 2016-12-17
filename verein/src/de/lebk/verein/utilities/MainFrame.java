package de.lebk.verein.utilities;

import de.lebk.verein.login.Auth;
import de.lebk.verein.login.LoginDialog;
import de.lebk.verein.member.Member;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author sopaetzel
 */
public class MainFrame extends JFrame {

    private LookAndFeel yourFeel;
    private final int initWidth = 600;
    private final int initHeight = 350;
    private final int minWidth = 490;
    private final int minHeight = 300;

    private Dimension initDimension = new Dimension(initWidth, initHeight);
    private Dimension minDimension = new Dimension(minWidth, minHeight);

    private MainMenu mainMenu;
    private LoginDialog loginDialog;

    private Member member;

    public MainFrame(Member member, boolean loggedIn) throws HeadlessException {
        this.member = member;

        if (!loggedIn) {
            loginDialog = new LoginDialog(this, "Anmeldung");
            this.createAndHideGUI();
        } else if (loggedIn) {
            this.createAndShowGUI();
            this.setTitle(this.getTitle() + " [" + member.getFirstName() + " " + member.getLastName() + "]");
        }
    }

    /**
     * Creates Main Frame (Window) of out Application and sets some initial
     * values.
     */
    private void createGUI() {
        // this properties
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Vereinsverwaltung");
        this.setPreferredSize(initDimension);
        this.setMinimumSize(minDimension);
        this.setCustomLookAndFeel(yourFeel);

        // components
        this.setJMenuBar(new MainMenu(member));
        this.getContentPane().add(new TabContainer(member), BorderLayout.CENTER);
        this.getContentPane().add(new SideContainer(member), BorderLayout.EAST);

        this.pack();
    }

    private void createAndShowGUI() {
        this.createGUI();
        this.showGUI();
    }

    private void createAndHideGUI() {
        this.createGUI();
        this.hideGUI();
    }

    private void hideGUI() {
        this.setVisible(false);
    }

    private void showGUI() {
        this.setVisible(true);
    }

    private void setCustomLookAndFeel(LookAndFeel uiStyle) {
        try {
            switch (uiStyle) {
                case METAL: {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                break;
                case SYSTEM: {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
            }
        } catch (Exception e) {
        }
    }

}
