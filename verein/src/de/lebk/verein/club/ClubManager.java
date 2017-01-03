/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.lebk.verein.club;

import de.lebk.verein.event.EventManager;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
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
        this.setLayout(new BorderLayout());
        this.add(new JButton("Beiträge Platzhalter"));
    }

}
