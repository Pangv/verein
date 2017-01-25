package de.lebk.verein.utilities;

import de.lebk.verein.club.Club;
import de.lebk.verein.data_access.DataAccess;
import de.lebk.verein.login.Auth;
import de.lebk.verein.login.LoginDialog;
import de.lebk.verein.member.ProfileDialog;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sopaetzel
 */
public class MainMenu extends JMenuBar {

    // base menu
    private final JMenu jMenuFile = new JMenu("Datei");

    // sub menu items
    private final JMenuItem jMenuOpen = new JMenuItem("Öffnen");
    private final JMenuItem jMenuSave = new JMenuItem("Sichern");

    // sub menu buttons
    private final JButton jMenuConfig = new JButton("Profil");
    private final JButton jMenuLogout = new JButton("Ausloggen");

    private Club club;
    private JFrame parent;

    public MainMenu(MainFrame parent, Club club) {
        this.parent = parent;
        this.club = club;
        createComponent();
    }

    private void createComponent() {
        // addItems
        jMenuFile.add(jMenuOpen);
        jMenuFile.add(jMenuSave);

        this.initActionsListeners();

        // addMenu
        this.add(jMenuFile);
        this.add(Box.createHorizontalGlue());
        this.add(jMenuConfig);
        this.add(jMenuLogout);
    }

    private void initActionsListeners() {

        jMenuOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    DataAccess.getInstance().readXML();
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            }
        });

        jMenuSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    DataAccess doa = DataAccess.getInstance();
                    doa.writeXML(club);
                } catch (JAXBException ex) {
                    ex.printStackTrace();
                }
            }
        });

        jMenuConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              ProfileDialog d = new ProfileDialog(null);
            }
        });

        jMenuLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    JOptionPane.showMessageDialog(jMenuConfig, "Ausgeloggt!");
                    DataAccess.getInstance().writeXML(club);
                    parent.setVisible(false);
                    Auth.getInstance().logout();
                    LoginDialog login = new LoginDialog(null, club, "Neu anmelden");
                    if (login.isLogged()) {
                        parent.setVisible(true);
                    }

                } catch (JAXBException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
}
