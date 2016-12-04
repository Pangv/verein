/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.lebk.verein.entry;

import de.lebk.verein.view.LoginDialog;
import de.lebk.verein.view.MainFrame;

/**
 *
 * @author sopaetzel
 */
public class Entry {

    private static MainFrame mainFrame;
    private static LoginDialog loginDialog;
    
    public static void main(String[] args) {
        System.out.println("It is alive!");
  
        mainFrame = new MainFrame();
        loginDialog = new LoginDialog(mainFrame,"Anmeldung");
       
    }



}
