package de.lebk.verein.entry;

import de.lebk.verein.club.Club;
import de.lebk.verein.member.Member;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;

/**
 *
 * @author sopaetzel
 */
public class DashboardUsersPanel extends JPanel {

    private Club club;
    private JPanel userPanel;

    public DashboardUsersPanel(Club club) {
        this.club = club;
        this.createComponent();
    }

    public void createComponent() {
        this.setLayout(new GridLayout(3, 2));
        this.add(new JLabel("Neue Mitglieder"));
        this.add(new JLabel(""));

        List<Member> reversedMember = club.getMembers();
        Collections.reverse(reversedMember);

        for (Member member : reversedMember) {
            this.userPanel = new JPanel();

            userPanel.add(new JLabel(member.getFullName()));
            userPanel.add(new JLabel("alias: " + member.getUsername()));
            userPanel.add(new JLabel("anno:" + member.getDateTimeEntered()));


            this.add(userPanel);



           userPanel.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.lightGray, 2), BorderFactory.createEmptyBorder(2, 2, 2, 2)));
        }
    }

}
