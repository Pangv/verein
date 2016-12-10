package de.lebk.verein.view;

import de.lebk.verein.view.dialog.LoginDialog;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author sopaetzel
 */
public class MainFrame extends JFrame {

    private final int initWidth = 600;
    private final int initHeight = 350;
    private final int minWidth = 500;
    private final int minHeight = 250;

    Dimension initDimension = new Dimension(initWidth, initHeight);
    Dimension minDimension = new Dimension(minWidth, minHeight);

    MainMenu mainMenu = new MainMenu();
    TabContainer tabContainer = new TabContainer();
    LoginDialog loginDialog;

    public MainFrame(boolean loggedIn, String username) throws HeadlessException {
        if (!loggedIn) {
            loginDialog = new LoginDialog(this, "Anmeldung");
            this.createAndHideGUI();
        } else if (loggedIn) {
            this.createAndShowGUI();
            this.setTitle(this.getTitle() + " [" + username +"]");
        }
    }

    /**
     * Creates Main Frame (Window) of out Application
     * and sets some initial values.
     */
    private void createGUI(){
        this.setJMenuBar(new MainMenu());

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Vereinsverwaltung");

//        this.setSize(initDimension);
        this.setMinimumSize(minDimension);
//        this.setPreferredSize(initDimension);
        this.getContentPane().add(tabContainer);
        
        this.pack();
    }
    
    
    
    private void createAndShowGUI() {
        this.createGUI();
        this.showGUI();
    }
    
    private void createAndHideGUI(){
        this.createGUI();
        this.hideGUI();
    }
    
    private void hideGUI(){
        this.setVisible(false);
    }

    private void showGUI() {  
        this.setVisible(true);
    }

}
