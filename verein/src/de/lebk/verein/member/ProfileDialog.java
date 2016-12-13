/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package de.lebk.verein.member;

import de.lebk.verein.utilities.ImageLabel;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author sopaetzel
 */
public class ProfileDialog extends JDialog {

  // Components
  private JLabel jLblUserImage;
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
    createDialog();
  }

  public void createDialog(/* TODO add Member */) {

    try {
      this.setTitle(dialogTitle + " von sopaetzel");
      this.setLayout(new GridBagLayout());
      GridBagConstraints grid = new GridBagConstraints();


      //jLblUserImage = new ImageLabel("resources/profile.png");
//      jLblUserImage = new ImageLabel(ProfileDialog.class.getResource("/profile.png"));

       jLblUserImage = new
       ImageLabel("D:\\workspaces\\MultiProjects\\verein\\verein\\resources\\profile.png");
      jLblLastName = new JLabel("Pätzel");
      jLblFirstName = new JLabel("Sven-Oliver");
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
      this.add(jLblUserImage, grid);

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
      grid.gridy = 3;
      this.add(jLblDateJoined, grid);

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
    } catch (IOException ex) {
      Logger.getLogger(ProfileDialog.class.getName()).log(Level.SEVERE, null, ex);
    }

    this.defineActions();
  }

  private void defineActions() {

    jBtnLeaveClub.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showConfirmDialog(null, "Sind Sie sicher, dass Sie austreten wollen?");
      }
    });

    /**
     * Special Characters - Not Allowed Spaces - Not Allowed Minimum and Maximum Length of field - 6
     * to 12 Characters Met by [a-zA-Z0-9@]{6,12} Numeric Character - At least one character Met by
     * positive lookahead (?=.*\d) At least one Capital Letter Met by positive lookahead (?=.*[A-Z])
     * Repetitive Characters - Allowed only two repetitive characters I am not sure what you mean by
     * this. The negative lookahead (?!.*(.)\1\1) makes sure that no character is allowed to appear
     * more than two times in a row. Substring aa is okay, aaa is not. Make it (?!.*(.+)\1\1) to
     * reject repeated substrings of length more than one (like ababab) or add .* before \1 to
     * reject non-continuous repeated appearances too.
     */
    jBtnChangePassword.addActionListener(new ActionListener() {
      String securePasswordPolicy = "^(?=.*[A-Z])(?=.*\\d)(?!.*(.)\\1\\1)[a-zA-Z0-9@]{6,12}$";

      @Override

      public void actionPerformed(ActionEvent e) {

      }
    });

  }

  // private boolean checkPasswordPolicy(String newPassword, String passwordPolicy) {
  // if (newPassword != null) {
  // if (newPassword.matches(passwordPolicy)) {
  // return true;
  // } else {
  // JOptionPane.showMessageDialog(null,
  // "Das Passwort entspricht nicht unseren Passwort Kriterien", "Passwort ist nicht sicher",
  // JOptionPane.WARNING_MESSAGE);
  // return false;
  // }
  //
  // }
  // return true; // abort
  // }
  //
  // private boolean checkPasswordEquality(String newPassword, String confirmPassword) {
  // if (newPassword.equals(confirmPassword)) {
  // return true;
  // } else {
  // JOptionPane.showMessageDialog(null, "Die Wiederholung entspricht nicht Ihrerm neuen Passwort",
  // "Passwörter nicht identisch", JOptionPane.WARNING_MESSAGE);
  // return false;
  // }
}
