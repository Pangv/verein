/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.lebk.verein.view.dialog;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
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
    private final int horizontalGap = 10;
    private final int verticalGap = 5;
    

    // Components
    private JPanel jPanel;
    private final String dialogTitle;
    private JLabel jLblPlaceholder;
    private JLabel jLblMessage;
    private JLabel jLblLoginname;
    private JLabel jLblPassword;
    private JTextField jTFLoginname;
    private JPasswordField jPFPassword;
    
    private JButton jBtnLogin;
    private JButton jBtnRegister;

    public LoginDialog(JFrame owner, String dialogTitle) {
        super(owner);
        this.dialogTitle = dialogTitle;
        addAndCreateDialog();
    }

    private void addAndCreateDialog() {
        
        jPanel = new JPanel(new GridLayout(4, 2, horizontalGap, verticalGap));

        jLblPlaceholder = new JLabel(" ");
        jLblMessage = new JLabel("Bitte melden Sie sich an");
        jLblLoginname = new JLabel("Anmeldename");
        jLblPassword = new JLabel("Passwort");
        jTFLoginname = new JTextField();
        jPFPassword = new JPasswordField();
        
        jBtnLogin = new JButton("Anmelden");
        jBtnRegister = new JButton("Registrieren");


        this.setTitle(dialogTitle);

        jPanel.add(jLblMessage, CENTER_ALIGNMENT);
        jPanel.add(jLblPlaceholder);
        
        jPanel.add(jLblLoginname);
        jPanel.add(jTFLoginname);
        
        jPanel.add(jLblPassword);
        jPanel.add(jPFPassword);
        
        jPanel.add(jBtnLogin);
        jPanel.add(jBtnRegister);

        this.add(jPanel);

        this.setResizable(false);

        this.pack();
        this.setVisible(true);
    }

}
