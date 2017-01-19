package de.lebk.verein.login;

import de.lebk.verein.club.Club;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private Club club;

    public LoginDialog(JFrame owner, Club club, String dialogTitle) {
        super(owner);
        this.club = club;
        this.dialogTitle = dialogTitle;
        createDialog();
    }

    private void createDialog() {

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
    
    
    private void initActionListeners(){
        jBtnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    Auth.getInstance().login(club, jTFLoginname.getText(), jPFPassword.getText());
                } catch (UserNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

}
