package de.lebk.verein.member;

import de.lebk.verein.login.Auth;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

/**
 * @author sopaetzel
 */
public class MemberManager extends JPanel {

    private JTable tblMember = new JTable(new DefaultTableModel(new Object[]{"Username", "Vorname", "Nachname", "Geschlecht", "Eintritt"}, 0));
    private JTable tblOfficer = new JTable(new DefaultTableModel(new Object[]{"Username", "Vorname", "Nachname", "Geschlecht", "Eintritt"}, 0));

    private JScrollPane scrlMemberPane = new JScrollPane(tblMember);
    private JScrollPane scrlOfficerPane = new JScrollPane(tblOfficer);

    private JButton btnPromote = new JButton("Zum Vorstand ernennen.");
    private JButton btnDemote = new JButton("Vorstand 'entfernen'.");
    private JButton btnRemove = new JButton("Mitglied 'entfernen'.");


    public MemberManager() {
        createComponent();
    }

    private void createComponent() {


        JPanel pnlMember = new JPanel();
        pnlMember.setLayout(new BoxLayout(pnlMember, BoxLayout.Y_AXIS));
        pnlMember.add(new JLabel("Mitglieder"));
        pnlMember.add(scrlMemberPane);
        JPanel pnlMemberButtons = new JPanel();
        pnlMemberButtons.setLayout(new BoxLayout(pnlMemberButtons, BoxLayout.X_AXIS));
        pnlMemberButtons.add(btnRemove);
        pnlMemberButtons.add(btnPromote);
        pnlMember.add(pnlMemberButtons);

        JPanel pnlOfficer = new JPanel();
        pnlOfficer.setLayout(new BoxLayout(pnlOfficer, BoxLayout.Y_AXIS));
        pnlOfficer.add(new JLabel("Vorstände"));
        pnlOfficer.add(scrlOfficerPane);
        pnlOfficer.add(btnDemote);

        addMember();
        addOfficer();

        this.actionListeners();

        this.add(pnlMember);
        this.add(pnlOfficer);
    }


    private void addMember() {
        DefaultTableModel model = (DefaultTableModel) tblMember.getModel();
        for (Member member : Auth.getInstance().getClub().getMembers()) {
            model.addRow(new Object[]{member.getUsername(), member.getFirstName(), member.getLastName(), member.getSex(), member.getDateTimeEntered()});
        }
    }

    private void addOfficer() {
        DefaultTableModel model = (DefaultTableModel) tblOfficer.getModel();
        for (Officer officer : Auth.getInstance().getClub().getOfficers()) {
            model.addRow(new Object[]{officer.getUsername(), officer.getFirstName(), officer.getLastName(), officer.getSex(), officer.getDateTimeEntered()});
        }
    }


    private void actionListeners() {
        btnPromote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = (String) tblMember.getValueAt(tblMember.getSelectedRow(), 0);
                DefaultTableModel modelMember = (DefaultTableModel) tblMember.getModel();
                modelMember.setRowCount(0);
                DefaultTableModel modelOfficer = (DefaultTableModel) tblOfficer.getModel();
                modelOfficer.setRowCount(0);

                Iterator<Member> memberIterator = Auth.getInstance().getClub().getMembers().iterator();
                Member member;
                while (memberIterator.hasNext()) {
                    member = memberIterator.next();
                    if (member.getUsername().equals(username)) {
                        Auth.getInstance().getClub().getOfficers().add(new Officer(member.getFirstName(), member.getLastName(), member.getUsername(), member.getPassword(), member.getSex(), member.getEntered()));
                        Auth.getInstance().getClub().getMembers().remove(member);
                        addMember();
                        addOfficer();
                    }
                }
            }
        });


        btnDemote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = (String) tblOfficer.getValueAt(tblOfficer.getSelectedRow(), 0);
                DefaultTableModel modelMember = (DefaultTableModel) tblMember.getModel();
                modelMember.setRowCount(0);
                DefaultTableModel modelOfficer = (DefaultTableModel) tblOfficer.getModel();
                modelOfficer.setRowCount(0);

                Iterator<Officer> officerIterator = Auth.getInstance().getClub().getOfficers().iterator();
                Officer officer;
                while (officerIterator.hasNext()) {
                    officer = officerIterator.next();
                    if (officer.getUsername().equals(username)) {
                        Auth.getInstance().getClub().getMembers().add(new Member(officer.getFirstName(), officer.getLastName(), officer.getUsername(), officer.getPassword(), officer.getSex(), officer.getEntered()));
                        Auth.getInstance().getClub().getOfficers().remove(officer);
                        addMember();
                        addOfficer();
                    }
                }
            }
        });

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = (String) tblMember.getValueAt(tblMember.getSelectedRow(), 0);
                DefaultTableModel modelMember = (DefaultTableModel) tblMember.getModel();
                modelMember.setRowCount(0);
                DefaultTableModel modelOfficer = (DefaultTableModel) tblOfficer.getModel();
                modelOfficer.setRowCount(0);

                Iterator<Member> memberIterator = Auth.getInstance().getClub().getMembers().iterator();
                Member member;
                while (memberIterator.hasNext()) {
                    member = memberIterator.next();
                    if (member.getUsername().equals(username)) {
                        Auth.getInstance().getClub().getMembers().remove(member);
                        addMember();
                        addOfficer();
                        JOptionPane.showMessageDialog(null, "Mitglied " + member.getFullName() + " erfolgreich entfernt... speichern nicht vergessen!");
                    }
                }
            }
        });
    }


}
