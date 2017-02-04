package de.lebk.verein.dashboard;

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

    private void createComponent() {
        this.setLayout(new BorderLayout(20, 20));
        this.add(new DashboardAttributesPanel(club), BorderLayout.NORTH);
        this.add(new DashboardUsersPanel(club), BorderLayout.CENTER);
        this.setVisible(true);

    }

}
