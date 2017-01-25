package de.lebk.verein.club;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author sopaetzel
 */
public class ClubManager extends JPanel {



    public ClubManager() {
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

        JLabel lblUsername = new JLabel("Username");
        JLabel lblFirstName = new JLabel("Vorname");
        JLabel lblLastName = new JLabel("Nachname");
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
        pnlCreateUser.add(lblSex);
        pnlCreateUser.add(lblPlayholder);
        pnlCreateUser.add(lblPlayholder);

        pnlCreateUser.add(cbxUserType);
        pnlCreateUser.add(txtUsername);
        pnlCreateUser.add(txtFirstName);
        pnlCreateUser.add(txtLastName);
        pnlCreateUser.add(rbtnMale);
        pnlCreateUser.add(rbtnFemale);

        pnlCreateUser.add(btnCreateUser);

        return pnlCreateUser;
    }



}
