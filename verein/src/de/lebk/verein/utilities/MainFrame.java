package de.lebk.verein.utilities;

import de.lebk.verein.club.Club;
import de.lebk.verein.login.LoginDialog;
import de.lebk.verein.member.Member;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
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

    private Member member;
    private Club club;

    public MainFrame(Club club, Member member, boolean loggedIn) throws HeadlessException {
        this.club = club;
        this.member = member;

        this.setCustomLookAndFeel(LookAndFeel.SYSTEM);

        if (!loggedIn) {
            loginDialog = new LoginDialog(this, "Anmeldung");
            this.createAndHideGUI();
        } else if (loggedIn) {
            this.createAndShowGUI();
            this.setTitle(this.getTitle() + " [" + member.getFullName() + "]");
        }
    }

    /**
     * Creates Main Frame (Window) of our Application and sets some initial
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
        this.setJMenuBar(new MainMenu(club, member));
        this.getContentPane().add(new TabContainer(club, member), BorderLayout.CENTER);

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
                case SYSTEM: {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                break;
                case METAL: {
                    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                }
                break;
                case MOTIF: {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                }
                break;
                case GTK: {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
                }
                break;
                case NIMBUS: {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                }
                break;
            }
        } catch (Exception e) {
        }
    }

}
