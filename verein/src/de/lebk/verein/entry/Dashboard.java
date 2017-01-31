package de.lebk.verein.entry;

import de.lebk.verein.club.Club;

import javax.swing.*;
import java.awt.*;

/**
 * @author sopaetzel
 */
public class Dashboard extends JPanel {

    private Club club;

    public Dashboard(Club club) {
        this.club = club;
        createComponent();
    }

    public void createComponent() {
        this.setLayout(new BorderLayout(20, 20));
        this.add(new DashboardUsersPanel(club), BorderLayout.CENTER);
        this.setVisible(true);

    }

}
