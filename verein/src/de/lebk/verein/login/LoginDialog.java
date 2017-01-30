package de.lebk.verein.login;

import de.lebk.verein.utilities.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author sopaetzel
 */
public class LoginDialog extends JDialog {

    private final String dialogTitle;
    private JTextField jTFLoginname;
    private JPasswordField jPFPassword;

    private JButton jBtnLogin;

    private boolean logged = false;

    public LoginDialog(MainFrame owner, String dialogTitle) {
        super(owner, true);
        this.dialogTitle = dialogTitle;
        createDialog();
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    private void createDialog() {


        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        int verticalGap = 5;
        int horizontalGap = 10;
        JPanel jPanel = new JPanel(new GridLayout(4, 2, horizontalGap, verticalGap));
        jPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel jLblMessage = new JLabel("Bitte melden Sie sich an");
        JLabel jLblLoginname = new JLabel("Anmeldename");
        JLabel jLblPassword = new JLabel("Passwort");
        jTFLoginname = new JTextField();
        jPFPassword = new JPasswordField();

        jBtnLogin = new JButton("Anmelden");

        this.setTitle(dialogTitle);

        jPanel.add(jLblMessage, CENTER_ALIGNMENT);
        jPanel.add(new JLabel());

        jPanel.add(jLblLoginname);
        jPanel.add(jTFLoginname);

        jPanel.add(jLblPassword);
        jPanel.add(jPFPassword);

        jPanel.add(new JLabel());
        jPanel.add(jBtnLogin);

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
                } catch (UserNotFoundException | WrongPasswordException ex) {
                    JOptionPane.showMessageDialog(null, "Username oder Password nicht korrekt.",
                            "Nicht gefunden", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
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
                    } catch (UserNotFoundException | WrongPasswordException ex) {
                        JOptionPane.showMessageDialog(null, "Username oder Password nicht korrekt.",
                                "Nicht gefunden", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Do Nothing
            }
        });


        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                if(!getParent().isVisible()){
                    System.exit(0);
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });


    }

    private void tryLogin() throws UserNotFoundException, WrongPasswordException {
        //noinspection deprecation
        logged = Auth.getInstance().login(jTFLoginname.getText(), jPFPassword.getText());
        dispose();
    }


}
