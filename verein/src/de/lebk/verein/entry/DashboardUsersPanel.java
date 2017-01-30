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
        this.setLayout(new GridLayout(2, 0));

        this.add(latestMembersPanel());
        this.add(latestOfficersPanel());


    }

    private JPanel latestMembersPanel(){


        JPanel pnlLatestMembers = new JPanel();
        List<Member> reversedMember = club.getMembers();
        Collections.reverse(reversedMember);


        pnlLatestMembers.add(new JLabel("Neue Mitglieder"));
        pnlLatestMembers.add(new JLabel());
        pnlLatestMembers.add(new JLabel());



        for (Member member : reversedMember) {
            JPanel userPanel = new JPanel();
            userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));


            userPanel.add(new JLabel(member.getFullName()));
            userPanel.add(new JLabel("Bekannt als: " + member.getUsername()));
            userPanel.add(new JLabel("Mitglied seit:" + member.getDateTimeEntered()));


            pnlLatestMembers.add(userPanel);


        }

        return pnlLatestMembers;

    }

    private JPanel latestOfficersPanel(){

        JPanel pnlLatestOfficers = new JPanel();
        List<Officer> reversedOfficer = club.getOfficers();
        Collections.reverse(reversedOfficer);


        pnlLatestOfficers.add(new JLabel("Neue Vorstände"));
        pnlLatestOfficers.add(new JLabel());
        pnlLatestOfficers.add(new JLabel());

        for (Officer officer : reversedOfficer) {
            JPanel officerPanel = new JPanel();
            officerPanel.setLayout(new BoxLayout(officerPanel, BoxLayout.Y_AXIS));


            officerPanel.add(new JLabel(officer.getFullName()));
            officerPanel.add(new JLabel("Bekannt als: " + officer.getUsername()));
            officerPanel.add(new JLabel("Mitglied seit:" + officer.getDateTimeEntered()));


            pnlLatestOfficers.add(officerPanel);


        }

        return pnlLatestOfficers;

    }

}
