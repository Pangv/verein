/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.lebk.verein.view;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author sopaetzel
 */
public class MainFrame extends JFrame{
    
    private int initWidth = 300;
    private int initHeight = 250;
    
    Dimension dimension = new Dimension(initWidth, initHeight);
    
    public void initWindow(){
        this.setTitle("Vereinsverwaltung");
        this.setPreferredSize(dimension);
        this.setVisible(true);
    }
    
    
}
