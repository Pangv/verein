package de.lebk.verein.entry;

import de.lebk.verein.club.Club;
import de.lebk.verein.member.Member;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 *
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
        Collections.reverse(reversedMember);

        for (Member member : reversedMember) {
            JPanel userPanel = new JPanel();
            userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
            //userPanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.lightGray, 2), BorderFactory.createEmptyBorder(2, 2, 2, 2)));


            userPanel.add(new JLabel(member.getFullName()));
            userPanel.add(new JLabel("Bekannt als: " + member.getUsername()));
            userPanel.add(new JLabel("Mitglied seit:" + member.getDateTimeEntered()));


            this.add(userPanel);


        }
    }

}
