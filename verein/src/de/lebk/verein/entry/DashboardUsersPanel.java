package de.lebk.verein.entry;

import de.lebk.verein.club.Club;
import de.lebk.verein.member.Member;
import de.lebk.verein.member.Officer;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 * @author sopaetzel
 */
class DashboardUsersPanel extends JPanel {

    private Club club;

    DashboardUsersPanel(Club club) {
        this.club = club;
        this.createComponent();
    }

    private void createComponent() {
        this.setLayout(new GridLayout(0, 3));

        this.add(new JLabel("Neue Mitglieder"));
        this.add(new JLabel());
        this.add(new JLabel());

        List<Member> reversedMember = club.getMembers();
        List<Officer> reversedOfficer = club.getOfficers();
        Collections.reverse(reversedOfficer);
        Collections.reverse(reversedMember);

        for (Member member : reversedMember) {
            JPanel userPanel = new JPanel();
            userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));


            userPanel.add(new JLabel(member.getFullName()));
            userPanel.add(new JLabel("Bekannt als: " + member.getUsername()));
            userPanel.add(new JLabel("Mitglied seit:" + member.getDateTimeEntered()));


            this.add(userPanel);


        }

        this.add(new JLabel("Neue Vorstände"));
        this.add(new JLabel());
        this.add(new JLabel());

        for (Officer officer : reversedOfficer) {
            JPanel officerPanel = new JPanel();
            officerPanel.setLayout(new BoxLayout(officerPanel, BoxLayout.Y_AXIS));


            officerPanel.add(new JLabel(officer.getFullName()));
            officerPanel.add(new JLabel("Bekannt als: " + officer.getUsername()));
            officerPanel.add(new JLabel("Mitglied seit:" + officer.getDateTimeEntered()));


            this.add(officerPanel);


        }
    }

}
