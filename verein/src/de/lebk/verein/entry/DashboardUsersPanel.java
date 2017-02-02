package de.lebk.verein.entry;

import de.lebk.verein.club.Club;
import de.lebk.verein.member.Member;
import de.lebk.verein.member.Officer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        this.setLayout(new GridLayout(4, 0, 20, 20));

        JLabel lblNewMember = new JLabel("Neue Mitglieder");
        lblNewMember.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        lblNewMember.setBorder(new EmptyBorder(5, 5, 5, 5));

        JLabel lblNewOfficer = new JLabel("Neue Vorstände");
        lblNewOfficer.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        lblNewOfficer.setBorder(new EmptyBorder(5, 5, 5, 5));


        this.add(lblNewMember);
        this.add(latestMembersPanel());
        this.add(lblNewOfficer);
        this.add(latestOfficersPanel());
    }

    private JPanel latestMembersPanel() {


        JPanel pnlLatestMembers = new JPanel();
        List<Member> reversedMember = club.getMembers();
        Collections.reverse(reversedMember);

        pnlLatestMembers.setLayout(new BoxLayout(pnlLatestMembers, BoxLayout.X_AXIS));

        for (Member member : reversedMember) {
            JPanel userPanel = new JPanel();
            userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
            userPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

            userPanel.add(new JLabel(member.getFullName()));
            userPanel.add(new JLabel("Bekannt als: " + member.getUsername()));
            userPanel.add(new JLabel("Mitglied seit:" + member.getDateTimeEntered()));

            pnlLatestMembers.add(userPanel, BorderLayout.WEST);
        }

        return pnlLatestMembers;

    }

    private JPanel latestOfficersPanel() {

        JPanel pnlLatestOfficers = new JPanel();
        List<Officer> reversedOfficer = club.getOfficers();
        Collections.reverse(reversedOfficer);

        pnlLatestOfficers.setLayout(new BoxLayout(pnlLatestOfficers, BoxLayout.X_AXIS));


        for (Officer officer : reversedOfficer) {
            JPanel officerPanel = new JPanel();
            officerPanel.setLayout(new BoxLayout(officerPanel, BoxLayout.Y_AXIS));
            officerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

            officerPanel.add(new JLabel(officer.getFullName()));
            officerPanel.add(new JLabel("Bekannt als: " + officer.getUsername()));
            officerPanel.add(new JLabel("Mitglied seit:" + officer.getDateTimeEntered()));

            pnlLatestOfficers.add(officerPanel, BorderLayout.WEST);


        }

        return pnlLatestOfficers;

    }

}
