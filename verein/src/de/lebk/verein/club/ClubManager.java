/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.lebk.verein.club;

import de.lebk.verein.event.EventPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author sopaetzel
 */
public class ClubManager extends JPanel {

    public ClubManager() {
        this.createComponent();
    }

    private void createComponent() {
        this.setLayout(new GridLayout(2, 1));
        this.add(new EventPanel());
        this.add(new JButton("Platzhalter"));
    }

}
