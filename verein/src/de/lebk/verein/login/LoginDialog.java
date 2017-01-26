package de.lebk.verein.login;

import de.lebk.verein.club.Club;
import de.lebk.verein.utilities.MainFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

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
    
    private boolean logged = false;

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public boolean isLogged() {
        return logged;
    }

	public LoginDialog(MainFrame owner, String dialogTitle) {
		super(owner, true);
        this.dialogTitle = dialogTitle;
        createDialog();
    }

    private void createDialog() {


        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        jPanel = new JPanel(new GridLayout(4, 2, horizontalGap, verticalGap));
        jPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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

        this.initActionListeners();
        this.pack();
        this.setVisible(true);
    }
    
    
    private void initActionListeners(){
        jBtnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
					logged =
						Auth.getInstance().login(jTFLoginname.getText(), jPFPassword.getText());
					dispose();
				} catch (UserNotFoundException | WrongPasswordException ex) {
					JOptionPane.showMessageDialog(null, "Username oder Password nicht korrekt.",
						"Nicht gefunden", JOptionPane.ERROR_MESSAGE);
					System.err.println(ex.getMessage());
				}

			}
        });



    }



}
