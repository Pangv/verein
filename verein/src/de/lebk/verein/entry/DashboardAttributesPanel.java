package de.lebk.verein.entry;

import de.lebk.verein.club.Club;

import javax.swing.*;
import java.awt.*;

/**
 * @author sopaetzel
 */
class DashboardAttributesPanel extends JPanel {

    private Club club;

    public DashboardAttributesPanel(Club club) {
        this.club = club;

        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(new GridLayout(1, 3));

        JLabel lblMemberCount = new JLabel("Mitgliederanzahl: " + club.getMemberCount());
        JLabel lblOfficerCount = new JLabel("Vorstandsanzahl: " + club.getOfficerCount());
        JLabel lblMoney = new JLabel("Kasse: " + club.getMoney() + " €");

        lblMemberCount.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        lblOfficerCount.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        lblMoney.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));

        this.add(lblMemberCount);
        this.add(lblOfficerCount);
        this.add(lblMoney);


    }

}
