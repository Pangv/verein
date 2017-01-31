package de.lebk.verein.member;

import de.lebk.verein.login.Auth;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * @author sopaetzel
 */
public class MemberManager extends JPanel {

    private JTable tblMember = new JTable(new DefaultTableModel(new Object[]{"Username","Vorname", "Nachname", "Geschlecht", "Eintritt"}, 0));
    private JTable tblOfficer = new JTable(new DefaultTableModel(new Object[]{"Username","Vorname", "Nachname", "Geschlecht", "Eintritt"}, 0));

    private JScrollPane scrlMemberPane = new JScrollPane(tblMember);
    private JScrollPane scrlOfficerPane = new JScrollPane(tblOfficer);

    public MemberManager() {
        createComponent();
    }

    private void createComponent(){

        JButton btnPromote = new JButton("Zum Vorstand ernennen.");
        JButton btnDemote = new JButton("Vorstand 'entfernen'.");

        JPanel pnlMember= new JPanel();
        pnlMember.setLayout(new BoxLayout(pnlMember, BoxLayout.Y_AXIS));
        pnlMember.add(new JLabel("Mitglieder"));
        pnlMember.add(scrlMemberPane);
        pnlMember.add(btnDemote);

        JPanel pnlOfficer  = new JPanel();
        pnlOfficer.setLayout(new BoxLayout(pnlOfficer, BoxLayout.Y_AXIS));
        pnlOfficer.add(new JLabel("Vorstände"));
        pnlOfficer.add(scrlOfficerPane);
        pnlOfficer.add(btnPromote);

        addMember();
        addOfficer();

        this.add(pnlMember);
        this.add(pnlOfficer);
    }


    private void addMember(){
        DefaultTableModel model = (DefaultTableModel) tblMember.getModel();
        for (Member member : Auth.getInstance().getClub().getMembers()){
           model.addRow(new Object[]{member.getUsername(), member.getFirstName(), member.getLastName(), member.getSex(), member.getDateTimeEntered()});
        }
    }
   private void addOfficer(){
        DefaultTableModel model = (DefaultTableModel) tblOfficer.getModel();
        for (Officer officer : Auth.getInstance().getClub().getOfficers()){
           model.addRow(new Object[]{officer.getUsername(), officer.getFirstName(), officer.getLastName(), officer.getSex(), officer.getDateTimeEntered()});
        }
    }




}
