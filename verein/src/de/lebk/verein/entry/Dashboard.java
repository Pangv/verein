package de.lebk.verein.entry;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author sopaetzel
 */
public class Dashboard extends JPanel {

    public Dashboard() {
        createComponent();
    }

    public void createComponent() {

        this.setLayout(new GridLayout(2, 2));

        this.add(new DashboardUsersPanel());
        this.add(new JButton("Rechts 1"));
        this.add(new JButton("Links 2"));
        this.add(new JButton("Rechts 2"));

        this.setVisible(true);

    }

}
