package de.lebk.verein.entry;

import de.lebk.verein.club.Club;
import de.lebk.verein.member.Member;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author sopaetzel
 */
class DashboardUsersPanel extends JPanel {

    private Club club;

    public DashboardUsersPanel(Club club) {
        this.club = club;
        this.createComponent();
    }

    public void createComponent() {
        this.setLayout(new GridLayout(5, 4));

        this.add(new JLabel("Neue Mitglieder"));
        this.add(new JLabel());
        this.add(new JLabel());

        List<Member> reversedMember = club.getMembers();
        Collections.reverse(reversedMember);

        for (Member member : reversedMember) {
            JPanel userPanel = new JPanel();

            userPanel.add(new JLabel(member.getFullName()));
            userPanel.add(new JLabel("alias: " + member.getUsername()));
            userPanel.add(new JLabel("anno:" + member.getDateTimeEntered()));

            userPanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.lightGray, 2), BorderFactory.createEmptyBorder(2, 2, 2, 2)));

            this.add(userPanel);


        }
    }

}
