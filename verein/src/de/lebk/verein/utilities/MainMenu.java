package de.lebk.verein.utilities;

import de.lebk.verein.club.Club;
import de.lebk.verein.data_access.DataAccess;
import de.lebk.verein.login.Auth;
import de.lebk.verein.login.LoginDialog;
import de.lebk.verein.member.Member;
import de.lebk.verein.member.ProfileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBException;

/**
 *
 * @author sopaetzel
 */
public class MainMenu extends JMenuBar {

    // base menus
    private final JMenu jMenuFile = new JMenu("Datei");
    private final JMenu jMenuTest = new JMenu("Test");

    // sub menus
    private final JMenuItem jMenuLogin = new JMenuItem("Zeige Login");
    private final JMenuItem jMenuProfile = new JMenuItem("Zeige Profil");
    private final JMenuItem jMenuSave = new JMenuItem("Save");
    private final JButton jMenuConfig = new JButton("Einstellungen");
    private final JButton jMenuLogout = new JButton("Ausloggen");

    private final JMenuItem jMenuExit = new JMenuItem("Schließen");

    private JFrame parent;
    private Member member = Auth.getInstance().getCurrentUser();
    private Club club;

    public MainMenu(MainFrame parent, Club club) {
        this.parent = parent;
        this.club = club;
        createComponent();
    }

    private void createComponent() {
        // addItems
        jMenuTest.add(jMenuLogin);
        jMenuTest.add(jMenuProfile);
        jMenuTest.add(jMenuSave);
        jMenuFile.add(jMenuExit);

        // TODO: addTestActions REMOVE LATER
        this.defineTestActions();

        // addMenu
        this.add(jMenuFile);
        this.add(jMenuTest);

        this.add(Box.createHorizontalGlue());
        this.add(jMenuConfig);
        this.add(jMenuLogout);
    }

    private void defineTestActions() {
        /**
         * Shows the Login dialog
         */
        jMenuLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginDialog l = new LoginDialog(null, club,"Test Login");

            }
        });

        jMenuProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProfileDialog d = new ProfileDialog(null);
            }
        });

        jMenuExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jMenuSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(jMenuConfig, "Einstellungen für " + member.getFirstName() + " " + member.getLastName() + ".");
            }
        });

        jMenuLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(jMenuConfig, "Ausgeloggt!");
                    DataAccess.getInstance().writeXML(club);          
                    LoginDialog login = new LoginDialog(null, club, "Neu anmelden");
                } catch (JAXBException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
}
