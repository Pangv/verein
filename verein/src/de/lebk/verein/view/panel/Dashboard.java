/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.lebk.verein.view.panel;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author sopaetzel
 */
public class Dashboard extends JPanel {

    public Dashboard() {
        createAndShowComponent();
    }
    
    

    public void createAndShowComponent() {

        this.setLayout(new GridLayout(2, 2));
        

        this.add(new JButton("Links 1"));
        this.add(new JButton("Rechts 1"));
        this.add(new JButton("Links 2"));
        this.add(new JButton("Rechts 2"));
        
        
        this.setVisible(true);
        
        

    }

}
