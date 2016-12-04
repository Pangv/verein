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
public class MainFrame extends JFrame implements Runnable{
    
    private int initWidth = 500;
    private int initHeight = 250;
    private int minWidth = 500;
    private int minHeight = 250;
   
    Dimension initDimension = new Dimension(initWidth, initHeight);
    Dimension minDimension = new Dimension(minWidth, minHeight);
        
    MainMenu mainMenu = new MainMenu();
   
    
    
    
    private void initWindow(){
        this.setJMenuBar(new MainMenu());
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Vereinsverwaltung");
        
        this.setSize(initDimension);
        this.setMinimumSize(minDimension);
        this.setPreferredSize(initDimension);
        
        
        
        this.setVisible(true);
    }

    @Override
    public void run() {
      
    }
    
    
}
