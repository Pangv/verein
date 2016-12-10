/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.lebk.verein.view.dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author sopaetzel
 */
public class ProfileDialog extends JDialog {

    // Components
    private JPanel jPnlPicture;
    private String dialogTitle;

    private JLabel jLblLastName;
    private JLabel jLblFirstName;
    private JLabel jLblUsername;
    private JLabel jLblDateJoined;

    private JButton jBtnLeaveClub;
    private JButton jBtnSaveChanges;
    private JButton jBtnResignation;
    private JButton jBtnChangePassword;

    public ProfileDialog(Frame owner, String dialogTitle) {
        super(owner);
        this.dialogTitle = dialogTitle;
        addAndCreateDialog();
    }

    public void addAndCreateDialog(/*TODO add Member*/) {

        this.setTitle(dialogTitle);
        this.setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();

        JButton jTest = new JButton("TEST");

        jLblLastName = new JLabel("Pätzel");
        jLblFirstName = new JLabel("Sven-Oliver");
        jLblUsername = new JLabel("Anmeldename: sopaetzel");
        jLblDateJoined = new JLabel("Mitglied seit: 2016-12-01");

        jBtnLeaveClub = new JButton("Austreten");
        jBtnSaveChanges = new JButton("Sichern");
        jBtnResignation = new JButton("Rücktritt");
        jBtnChangePassword = new JButton("Passwort ändern");

        grid.anchor = GridBagConstraints.FIRST_LINE_START;
        grid.fill = GridBagConstraints.VERTICAL;
        grid.gridheight = 2;
        grid.gridwidth = 1;
        grid.gridx = 0;
        grid.gridy = 0;
        this.add(jTest, grid);

        // adding first name
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.weightx = 0.5;
        grid.gridheight = 1;
        grid.gridwidth = 2;
        grid.gridx = 1;
        grid.gridy = 0;
        this.add(jLblFirstName, grid);
        // adding last name
        grid.weightx = 0.5;
        grid.gridwidth = 2;
        grid.gridx = 1;
        grid.gridy = 1;
        this.add(jLblLastName, grid);
        // adding date a member joined
        grid.weightx = 0.5;
        grid.gridwidth = 2;
        grid.gridx = 0;
        grid.gridy = 2;
        this.add(jLblDateJoined, grid);
        // adding user name
        grid.weightx = 0.5;
        grid.gridwidth = 2;
        grid.gridx = 0;
        grid.gridy = 3;
        this.add(jLblUsername, grid);
        

        // adding leave button
        grid.gridwidth = 1;
        grid.gridx = 3;
        grid.gridy = 0;
        this.add(jBtnLeaveClub, grid);
        // adding resignation button
        grid.gridwidth = 1;
        grid.gridx = 3;
        grid.gridy = 1;
        this.add(jBtnResignation, grid);
        // adding changePassword button
        grid.gridwidth = 1;
        grid.gridx = 3;
        grid.gridy = 2;
        this.add(jBtnChangePassword, grid);
        // adding saveChanges button
        grid.gridwidth = 1;
        grid.gridx = 3;
        grid.gridy = 3;
        this.add(jBtnSaveChanges, grid);

        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

}
