package de.lebk.verein.club;

import java.awt.BorderLayout;
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
