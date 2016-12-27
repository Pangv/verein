package de.lebk.verein.entry;

import de.lebk.verein.member.Member;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author sopaetzel
 */
public class Dashboard extends JPanel {

    private Member member;

    public Dashboard(Member member) {
        this.member = member;
        createComponent();
    }

    public void createComponent() {

        this.setLayout(new GridLayout(2, 2));

        this.add(new DashboardUsersPanel(member));
        this.add(new JButton("Diagramme Platzhalter 1"));
        this.add(new JButton("Event Kalender Platzhalter"));
        this.add(new JButton("Diagramme Platzhalter 2"));

        this.setVisible(true);

    }

}