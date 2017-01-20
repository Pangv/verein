package de.lebk.verein.entry;

import de.lebk.verein.club.Club;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author sopaetzel
 */
public class Dashboard extends JPanel {

    private Club club;

    public Dashboard(Club club) {
        this.club = club;
        createComponent();
    }

    public void createComponent() {
        this.setLayout(new GridLayout(1, 2));

        this.add(new DashboardUsersPanel(club));
        this.add(new JButton("Event Kalender Platzhalter"));

        this.setVisible(true);

    }

}
