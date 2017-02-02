package de.lebk.verein.club;

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
    private double payment = 0.0;
    private boolean isPayed = false;


    public ClubManager(Club club) {
        this.club = club;
        this.createComponent();
    }

    private void createComponent() {
        this.setLayout(new BorderLayout());
        this.add(createUserPanel(), BorderLayout.NORTH);
        this.add(createPaymentsPanel(), BorderLayout.SOUTH);
    }

    private JPanel createPaymentsPanel() {
        JPanel pnlPayments = new JPanel(new GridLayout(0, 3));
        JButton btnExecutePayment = new JButton("Beitragszahlung durchführen");
        JButton btnRequestPayment = new JButton("Beitragszahlung auslösen");
        JTextField txtRequestAmount = new JTextField();
        JLabel lblAmount = new JLabel("Überweisungsmenge");
        JLabel lblPayment = new JLabel("");

        pnlPayments.add(lblAmount);
        pnlPayments.add(lblPayment);
        pnlPayments.add(new JLabel());
        pnlPayments.add(txtRequestAmount);
        pnlPayments.add(btnRequestPayment);
        pnlPayments.add(btnExecutePayment);

        btnRequestPayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (txtRequestAmount.getText().matches("\\d+")) {
                        if (lblPayment.getText().equals("") && !txtRequestAmount.getText().equals("")) {
                            lblPayment.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
                            lblPayment.setForeground(Color.red);
                            lblPayment.setText("Es ist eine Gebühr von " + txtRequestAmount.getText() + "€ offen.");

                            payment = Double.parseDouble(txtRequestAmount.getText());
                        } else {
                            JOptionPane.showMessageDialog(null, "Die Zahlung wurde bereits durchgeführt.");
                        }
                    }
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    Warning.displayWarning(ex.getMessage(), "Nur nummerische Werte eingeben");
                }

            }
        });

        btnExecutePayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPayed && lblPayment.getText().contains("Es")) {
                    JOptionPane.showMessageDialog(null, "Gebühr bezahlt", "", JOptionPane.INFORMATION_MESSAGE);
                    lblPayment.setText(" ");
                    club.setMoney(club.getMoney() + payment);
                    isPayed = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Die Zahlung wurde bereits durchgeführt.");

                }
            }
        });


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
                //noinspection deprecation
                if (!txtFirstName.getText().equals("") && !txtLastName.getText().equals("") && !txtUsername.getText().equals("") && !pwfPassword.getText().equals("")) {
                    //noinspection deprecation
                    club.join(txtFirstName.getText(), txtLastName.getText(), txtUsername.getText(), pwfPassword.getText(), rbtnMale.isSelected() ? 'm' : 'f');
                    JOptionPane.showMessageDialog(null, "Mitglied wurde erfolgreich erstellt");
                } else {
                    Warning.displayWarning("Fields are empty", "Alle Felder müssen gefüllt sein.");
                }
            }
        });
        return pnlCreateUser;
    }


}
