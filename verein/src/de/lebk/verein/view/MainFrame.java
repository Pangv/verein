/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.lebk.verein.view;

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
    LoginDialog loginDialog;

    public MainFrame(boolean loggedIn) throws HeadlessException {
        if (!loggedIn) {
            loginDialog = new LoginDialog(this, "Anmeldung");
            this.createAndHideGUI();
        } else if (loggedIn) {
            this.createAndShowGUI();
        }
    }

    private void createGUI(){
        this.setJMenuBar(new MainMenu());

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Vereinsverwaltung");

        this.setSize(initDimension);
        this.setMinimumSize(minDimension);
        this.setPreferredSize(initDimension);
        
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
