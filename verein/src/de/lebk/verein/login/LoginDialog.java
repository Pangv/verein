package de.lebk.verein.login;

import de.lebk.verein.club.Club;
import de.lebk.verein.utilities.MainFrame;
import de.lebk.verein.utilities.Warning;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author sopaetzel
 */
public class LoginDialog extends JDialog {

    private final String dialogTitle;
    private JTextField jTFLoginname;
    private JPasswordField jPFPassword;

    private JButton jBtnLogin;

    private Club club;
    private boolean logged = false;

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public boolean isLogged() {
        return logged;
    }

    public LoginDialog(MainFrame owner, Club club, String dialogTitle) {
        super(owner, true);
        this.club = club;
        this.dialogTitle = dialogTitle;
        createDialog();
    }

    private void createDialog() {


        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        int verticalGap = 5;
        int horizontalGap = 10;
        JPanel jPanel = new JPanel(new GridLayout(4, 2, horizontalGap, verticalGap));
        jPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel jLblPlaceholder = new JLabel(" ");
        JLabel jLblMessage = new JLabel("Bitte melden Sie sich an");
        JLabel jLblLoginname = new JLabel("Anmeldename");
        JLabel jLblPassword = new JLabel("Passwort");
        jTFLoginname = new JTextField();
        jPFPassword = new JPasswordField();

        jBtnLogin = new JButton("Anmelden");
        JButton jBtnRegister = new JButton("Registrieren");

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

        this.initActionListeners();
        this.pack();
        this.setVisible(true);
    }


    private void initActionListeners() {
        jBtnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tryLogin();
                } catch (UserNotFoundException ex) {
                    ex.printStackTrace();
                    Warning.displayWarning(ex.getMessage(), "Nutzer oder Passwort nicht gefunden");
                }
            }
        });


        jPFPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {// Do Nothing
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        tryLogin();
                    } catch (UserNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Do Nothing
            }
        });


    }

    private void tryLogin() throws UserNotFoundException {
        logged = Auth.getInstance().login(club, jTFLoginname.getText(), jPFPassword.getText());
        dispose();
    }


}
