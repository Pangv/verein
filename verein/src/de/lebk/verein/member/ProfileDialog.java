package de.lebk.verein.member;

import de.lebk.verein.club.Club;
import de.lebk.verein.data_access.DataAccess;
import de.lebk.verein.login.Auth;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author sopaetzel
 */
public class ProfileDialog extends JDialog {

    // Components

    JButton btnResignation;
    private JButton btnLeaveClub;
    private JButton btnChangePassword;
    private Member member = Auth.getInstance().getCurrentUser();
    private Club club;

    public ProfileDialog(JFrame owner, Club club) {
        super(owner);
        this.club = club;
        createDialog();
    }

    public void createDialog() {

        this.setTitle("Profil von " + member.getUsername().replaceAll("\\d", ""));
        this.setLayout(new GridBagLayout());
        GridBagConstraints grid = new GridBagConstraints();
        JLabel jLblLastName = new JLabel("Nachname:\t" + member.getLastName());
        JLabel jLblFirstName = new JLabel("Vorname:\t" + member.getFirstName());
        JLabel jLblDateJoined = new JLabel("Mitglied seit: " + member.getDateTimeEntered());

        btnLeaveClub = new JButton("Austreten");
        btnResignation = new JButton("Rücktritt");
        btnChangePassword = new JButton("Passwort ändern");

        grid.anchor = GridBagConstraints.FIRST_LINE_START;
        grid.fill = GridBagConstraints.VERTICAL;
        grid.insets = new Insets(5, 10, 2, 10);
        grid.gridheight = 2;
        grid.gridwidth = 1;
        grid.gridx = 0;
        grid.gridy = 0;

        // adding first name
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.weightx = 1;
        grid.gridheight = 1;
        grid.gridwidth = 2;
        grid.gridx = 1;
        grid.gridy = 0;
        this.add(jLblFirstName, grid);
        // adding last name

        grid.gridheight = 1;
        grid.gridwidth = 2;
        grid.gridx = 1;
        grid.gridy = 1;
        this.add(jLblLastName, grid);
        // adding date a member joined

        grid.gridwidth = 2;
        grid.gridx = 0;
        grid.gridy = 3;
        this.add(jLblDateJoined, grid);

        grid.anchor = GridBagConstraints.EAST;
        grid.weightx = 0.3;
        // adding leave button
        grid.gridwidth = 1;
        grid.gridx = 3;
        grid.gridy = 0;
        this.add(btnLeaveClub, grid);
        // adding resignation button
        grid.gridwidth = 1;
        grid.gridx = 3;
        grid.gridy = 1;
        this.add(btnResignation, grid);
        // adding changePassword button
        grid.gridwidth = 1;
        grid.gridx = 3;
        grid.gridy = 2;
        this.add(btnChangePassword, grid);

        if (!Auth.getInstance().getCurrentUser().getClass().getSimpleName().equals("Officer")) {
            this.remove(btnResignation);
        }

        this.setResizable(false);
        this.pack();
        this.setVisible(true);

        this.defineActions();
    }

    private void defineActions() {

        btnLeaveClub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Sind Sie sicher, dass Sie austreten wollen?") == JOptionPane.OK_OPTION) {
                    JOptionPane.showMessageDialog(null, "Och nö");
                    club.leave(member);
                    try {
                        DataAccess.getInstance().writeXML(club);
                    } catch (JAXBException e1) {
                        e1.printStackTrace();
                    }
                    System.exit(-99);
                }
            }
        });


        btnResignation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Sind Sie sicher, dass Sie zurücktreten wollen?") == JOptionPane.OK_OPTION) {
                    club.retireOfficer((Officer) Auth.getInstance().getCurrentUser());
                }
                try {
                    DataAccess.getInstance().writeXML(club);
                } catch (JAXBException e1) {
                    e1.printStackTrace();
                }
                System.exit(-88);
            }
        });

        /*
         * Special Characters - Not Allowed Spaces - Not Allowed Minimum and
         * Maximum Length of field - 6 to 12 Characters Met by
         * [a-zA-Z0-9@]{6,12} Numeric Character - At least one character Met by
         * positive lookahead (?=.*\d) At least one Capital Letter Met by
         * positive lookahead (?=.*[A-Z]) Repetitive Characters - Allowed only
         * two repetitive characters I am not sure what you mean by this. The
         * negative lookahead (?!.*(.)\1\1) makes sure that no character is
         * allowed to appear more than two times in a row. Substring aa is okay,
         * aaa is not. Make it (?!.*(.+)\1\1) to reject repeated substrings of
         * length more than one (like ababab) or add .* before \1 to reject
         * non-continuous repeated appearances too.
         */
        btnChangePassword.addActionListener(new ActionListener() {
            String securePasswordPolicy = "^(?=.*[A-Z])(?=.*\\d)(?!.*(.)\\1\\1)[a-zA-Z0-9@]{6,12}$";

            @Override
            public void actionPerformed(ActionEvent e) {
                String oldPassword = Auth.getInstance().getCurrentUser().getPassword();
                String newPassword;
                if ((newPassword = JOptionPane.showInputDialog("Ändern Sie Ihr Passwort")) != null) {
                    if (!oldPassword.equals(newPassword)) {
                        Auth.getInstance().getCurrentUser().setPassword(newPassword);
                    } else {
                        JOptionPane.showMessageDialog(null, "Das Passwort unterscheidet sich nicht. Versuchen Sie es erneut.", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
                        Auth.getInstance().getCurrentUser().setPassword(oldPassword);
                    }
                }


            }
        });

    }
}
