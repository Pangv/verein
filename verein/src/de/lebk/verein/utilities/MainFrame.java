package de.lebk.verein.utilities;

import de.lebk.verein.club.Club;
import de.lebk.verein.login.Auth;
import de.lebk.verein.login.LoginDialog;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author sopaetzel
 */
public class MainFrame extends JFrame {

    private final int initWidth = 1000;
    private final int initHeight = initWidth / 16 * 9;
    private final int minWidth = 600;
    private final int minHeight = 300;

    private final Dimension initDimension = new Dimension(initWidth, initHeight);
    private final Dimension minDimension = new Dimension(minWidth, minHeight);

    private MainMenu mainMenu;
    private LoginDialog loginDialog;
    private final Club club;

    public MainFrame(Club club) throws HeadlessException {
        this.club = club;
        this.setCustomLookAndFeel(LookAndFeel.SYSTEM);
        this.createAndHideGUI();           
        
        
        do {            
            
        } while (Auth.getInstance().getCurrentUser() == null);
            loginDialog = new LoginDialog(this, club, "Anmeldung");
        if (Auth.getInstance().getCurrentUser() == null) {
              
        } 
        this.createAndShowGUI();
        this.setTitle(this.getTitle() + " [" + Auth.getInstance().getCurrentUser().getFullName()+ "]");
        
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

        // components
        this.setJMenuBar(new MainMenu(this,club));
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
        } 
         catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}
