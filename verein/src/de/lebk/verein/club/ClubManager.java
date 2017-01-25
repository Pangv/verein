package de.lebk.verein.club;

import de.lebk.verein.member.Member;
import de.lebk.verein.utilities.Warning;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author sopaetzel
 */
public class ClubManager extends JPanel {


    private Club club;

    public ClubManager(Club club) {
        this.club = club;
        this.createComponent();
    }

    private void createComponent() {
        this.setLayout(new BorderLayout());
        this.add(createUserPanel(), BorderLayout.SOUTH);
        this.add(createPaymentsPanel(), BorderLayout.NORTH);
    }

    private JPanel createFunctionsPanel() {
        JPanel pnlFunctions = new JPanel();
        JList<Member> lstMember = new JList<>();

        JButton btnShowMember = new JButton("Mitglieder anzeigen");

        btnShowMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog memberDialog = new JDialog();
                Member[] members = new Member[club.getMembers().size()];
                int i = 0;
                for (Member member : club.getMembers()) {
                    members[i] = member;
                    i++;
                }

            }
        });
        return pnlFunctions;
    }

    private JPanel createPaymentsPanel() {
        JPanel pnlPayments = new JPanel();
        pnlPayments.setLayout(new GridLayout(2, 5));


        JButton btnExecutePayment = new JButton("Beitragszahlung durchführen");
        this.add(btnExecutePayment);

        return pnlPayments;
    }


    private JPanel createUserPanel() {
        JPanel pnlCreateUser = new JPanel();
        pnlCreateUser.setLayout(new GridLayout(2, 6));

        JTextField txtUsername = new JTextField();
        JTextField txtFirstName = new JTextField();
        JTextField txtLastName = new JTextField();
        JPasswordField pwfPassword = new JPasswordField();

        JLabel lblUsername = new JLabel("Username");
        JLabel lblFirstName = new JLabel("Vorname");
        JLabel lblLastName = new JLabel("Nachname");
        JLabel lblPassword = new JLabel("Passwort");
        JLabel lblSex = new JLabel("Geschlecht");

        ButtonGroup btnGrpSex = new ButtonGroup();
        JRadioButton rbtnMale = new JRadioButton("Männlich");
        JRadioButton rbtnFemale = new JRadioButton("Weiblich");
        btnGrpSex.add(rbtnMale);
        btnGrpSex.add(rbtnFemale);


        JButton btnCreateUser = new JButton("Erstellen");

        pnlCreateUser.add(lblUsername);
        pnlCreateUser.add(lblFirstName);
        pnlCreateUser.add(lblLastName);
        pnlCreateUser.add(lblPassword);
        pnlCreateUser.add(lblSex);
        pnlCreateUser.add(new JLabel());
        pnlCreateUser.add(new JLabel());


        pnlCreateUser.add(txtUsername);
        pnlCreateUser.add(txtFirstName);
        pnlCreateUser.add(txtLastName);
        pnlCreateUser.add(pwfPassword);
        pnlCreateUser.add(rbtnMale);
        pnlCreateUser.add(rbtnFemale);
        pnlCreateUser.add(btnCreateUser);

        rbtnMale.setSelected(true);


        btnCreateUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!txtFirstName.getText().equals("") && !txtLastName.getText().equals("") && !txtUsername.getText().equals("") && !pwfPassword.getText().equals("")) {
                    club.join(txtFirstName.getText(), txtLastName.getText(), txtUsername.getText(), pwfPassword.getText(), rbtnMale.isSelected() ? 'm' : 'f');
                    JOptionPane.showMessageDialog(null, "");
                } else {
                    Warning.displayWarning("Fields are empty", "Alle Felder müssen gefüllt sein.");
                }
            }
        });
        return pnlCreateUser;
    }


}
