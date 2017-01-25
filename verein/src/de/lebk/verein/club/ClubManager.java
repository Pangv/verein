package de.lebk.verein.club;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
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

    private JPanel createPaymentsPanel() {
        JPanel pnlPayments = new JPanel();


        JTextField txtPaymentAmount = new JTextField();

        JButton btnExecutePayment = new JButton("Beitragszahlung durchführen");


        this.add(txtPaymentAmount);
        this.add(btnExecutePayment);

        return pnlPayments;
    }


    private JPanel createUserPanel() {
        String[] data = {"Mitglied", "Vorstand"};
        JPanel pnlCreateUser = new JPanel();
        pnlCreateUser.setLayout(new GridLayout(2, 6));

        JComboBox<String> cbxUserType = new JComboBox<>(data);
        JTextField txtUsername = new JTextField();
        JTextField txtFirstName = new JTextField();
        JTextField txtLastName = new JTextField();
        JPasswordField pwfPassword = new JPasswordField();

        JLabel lblUsername = new JLabel("Username");
        JLabel lblFirstName = new JLabel("Vorname");
        JLabel lblLastName = new JLabel("Nachname");
        JLabel lblPassword = new JLabel("Passwort");
        JLabel lblSex = new JLabel("Geschlecht");
        JLabel lblPlayholder = new JLabel("");

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
        pnlCreateUser.add(lblPlayholder);

        pnlCreateUser.add(cbxUserType);
        pnlCreateUser.add(txtUsername);
        pnlCreateUser.add(txtFirstName);
        pnlCreateUser.add(txtLastName);
        pnlCreateUser.add(pwfPassword);
        pnlCreateUser.add(rbtnMale);
        pnlCreateUser.add(rbtnFemale);

        pnlCreateUser.add(btnCreateUser);


        btnCreateUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                club.join(txtFirstName.getText(), txtLastName.getText(), txtUsername.getText(), pwfPassword.getText(), rbtnMale.isSelected() ? 'm' : 'f');
            }
        });

        return pnlCreateUser;


    }



}
