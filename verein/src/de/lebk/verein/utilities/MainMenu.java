package de.lebk.verein.utilities;

import de.lebk.verein.club.Club;
import de.lebk.verein.data_access.DataAccess;
import de.lebk.verein.login.Auth;
import de.lebk.verein.member.ProfileDialog;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author sopaetzel
 */
class MainMenu extends JMenuBar {

    // base menu
    private final JMenu jMenuFile = new JMenu("Datei");

    // sub menu items
    private final JMenuItem jMenuSave = new JMenuItem("Sichern");

    // sub menu buttons
    private final JButton jMenuProfile = new JButton("Profil");
    private final JButton jMenuLogout = new JButton("Ausloggen");

    private Club club;
    private JFrame parent;

    MainMenu(MainFrame parent, Club club) {
        this.parent = parent;
        this.club = club;
        createComponent();
    }

    private void createComponent() {
        // addItems
        jMenuFile.add(jMenuSave);

        this.initActionsListeners();

        // addMenu
        this.add(jMenuFile);
        this.add(Box.createHorizontalGlue());
        this.add(jMenuProfile);
        this.add(jMenuLogout);
    }

    private void initActionsListeners() {

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

        jMenuProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ProfileDialog(parent, club);
            }
        });

        jMenuLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    JOptionPane.showMessageDialog(jMenuProfile, "Ausgeloggt!");
                    DataAccess.getInstance().writeXML(club);
                    parent.dispose();
                    Auth.getInstance().logout();
                } catch (JAXBException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
}
