/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.lebk.verein.view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author sopaetzel
 */
public class MainMenu extends JMenuBar{
    
    // base menus
    private JMenu jMenu = new JMenu("Datei");
    // sub menus
    private JMenuItem jMenuOpen = new JMenuItem("Öffnen");
    private JMenuItem jMenuExit = new JMenuItem("Schließen");

    public MainMenu() {
        initParts();
    }
 
    
    
    private void initParts(){
        //addItems
        jMenu.add(jMenuOpen);
        jMenu.add(jMenuExit);
        
        //addMenu
        this.add(jMenu);
    }
    
}
