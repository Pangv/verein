package de.lebk.verein.member;

import de.lebk.verein.login.Auth;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author sopaetzel
 */
public class MemberManager extends JPanel {

    JTable tblMember = new JTable(new DefaultTableModel(new Object[]{"Username","Vorname", "Nachname", "Geschlecht", "Eintritt"}, 0));
    JTable tblOfficer = new JTable(new DefaultTableModel(new Object[]{"Username","Vorname", "Nachname", "Geschlecht", "Eintritt"}, 0));

    JScrollPane scrlMemberPane = new JScrollPane(tblMember);
    JScrollPane scrlOfficerPane = new JScrollPane(tblOfficer);


    public MemberManager() {
        createComponent();
    }

    private void createComponent(){

        JPanel pnlMember= new JPanel();
        pnlMember.setLayout(new BoxLayout(pnlMember, BoxLayout.Y_AXIS));
        JPanel pnlOfficer  = new JPanel();
        pnlOfficer.setLayout(new BoxLayout(pnlOfficer, BoxLayout.Y_AXIS));


        pnlMember.add(new JLabel("Mitglieder"));
        pnlMember.add(scrlMemberPane);
        pnlOfficer.add(new JLabel("Vorstände"));
        pnlOfficer.add(scrlOfficerPane);

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
