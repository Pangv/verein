/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.lebk.verein.view;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author sopaetzel
 */
public class LoginDialog extends JDialog {

    // Properties
    private final int initWidth = 600;
    private final int initHeight = 350;
    private final int minWidth = 500;
    private final int minHeight = 250;

    // Components
    private JPanel jPanel;
    private String dialogTitle;
    private JLabel jLblMessage;
    private JLabel jLblLoginname;
    private JLabel jLblPassword;
    private JTextField jTFLoginname;
    private JPasswordField jPFPassword;

    public LoginDialog(JFrame owner, String dialogTitle) {
        super(owner);
        this.dialogTitle = dialogTitle;
        addAndCreateDialog();
    }

    private void addAndCreateDialog() {

        jPanel = new JPanel(new BorderLayout(5, 10));

        jLblMessage = new JLabel("Bitte melden Sie sich an");
        jLblLoginname = new JLabel("Anmeldename");
        jLblPassword = new JLabel("Passwort");
        jTFLoginname = new JTextField();
        jPFPassword = new JPasswordField();

        this.setTitle(dialogTitle);

        jPanel.add(jLblMessage, BorderLayout.NORTH);
        jPanel.add(jLblLoginname, BorderLayout.WEST);
        jPanel.add(jTFLoginname, BorderLayout.SOUTH);
        jPanel.add(jLblPassword, BorderLayout.EAST);
        jPanel.add(jPFPassword, BorderLayout.CENTER);

        this.add(jPanel);
        this.pack();
        this.setVisible(true);
    }

}
