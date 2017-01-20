package de.lebk.verein.utilities;

import de.lebk.verein.club.Club;
import de.lebk.verein.login.LoginDialog;
import de.lebk.verein.member.Member;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author sopaetzel
 */
public class MainFrame extends JFrame {

    private LookAndFeel yourFeel;
    private final int initWidth = 1000;
    private final int initHeight = initWidth / 16 * 9;
    private final int minWidth = 600;
    private final int minHeight = 300;

    private Dimension initDimension = new Dimension(initWidth, initHeight);
    private Dimension minDimension = new Dimension(minWidth, minHeight);

    private MainMenu mainMenu;
    private LoginDialog loginDialog;
    private Club club;

    private Member member;
    private Club club;

    public MainFrame(Club club, Member member, boolean loggedIn) throws HeadlessException {
        this.club = club;
        this.setCustomLookAndFeel(LookAndFeel.SYSTEM);
        this.createAndHideGUI();

        if (Auth.getInstance().getCurrentUser() == null) {
            loginDialog = new LoginDialog(this, club, "Anmeldung");
            if (loginDialog.isLogged()) {
                this.createAndShowGUI();
            }
        }

        this.setTitle(this.getTitle() + " [" + Auth.getInstance().getCurrentUser().getFullName() + "]");
        this.createAndShowGUI();
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
        this.setIconImage(new ImageIcon(ClassLoader.getSystemResource("logo.png")).getImage());
        this.setCustomLookAndFeel(yourFeel);

        // components
        this.setJMenuBar(new MainMenu(this, club));
        this.getContentPane().add(new TabContainer(club), BorderLayout.CENTER);
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

    public void hideGUI() {
        this.setVisible(false);
    }

    public void showGUI() {
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
        } catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private void initActionListener() {

    }

}
