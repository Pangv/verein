package de.lebk.verein.utilities;

import de.lebk.verein.club.Club;
import de.lebk.verein.data_access.DataAccess;
import de.lebk.verein.login.Auth;
import de.lebk.verein.login.LoginDialog;
import de.lebk.verein.member.Member;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author sopaetzel
 */
public class MainFrame extends JFrame {

    private boolean DEBUG = false;

    private final int initWidth = 1000;
    private final int initHeight = initWidth / 16 * 9;
    private final int minWidth = 600;
    private final int minHeight = 300;

    private final Dimension initDimension = new Dimension(initWidth, initHeight);
    private final Dimension minDimension = new Dimension(minWidth, minHeight);

    private Club club;
    private Member member;

    public MainFrame(Club club) {
        this.club = club;
        this.setCustomLookAndFeel(LookAndFeel.SYSTEM);
        this.createAndHideGUI();

        if (DEBUG) {
            this.showGUI();
            try {
                Auth.getInstance().login(club, "admin", "admin");
                member = Auth.getInstance().getCurrentUser();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (Auth.getInstance().getCurrentUser() == null) {
                LoginDialog loginDialog = new LoginDialog(this, club, "Anmeldung");
                member = Auth.getInstance().getCurrentUser();
                if (loginDialog.isLogged()) {
                    this.showGUI();
                }
            }
        }


        this.setTitle(this.getTitle() + " [" + member.getFullName() + "]");
    }

    /**
     * Creates Main Frame (Window) of our Application and sets some initial
     * values.
     */
    private void createGUI() {
        JFrame frame = this;
        // this properties
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onCloseOperation(frame);
            }
        });
        this.setTitle("Vereinsverwaltung");
        this.setPreferredSize(initDimension);
        this.setMinimumSize(minDimension);
        this.setIconImage(new ImageIcon(ClassLoader.getSystemResource("./logo.png")).getImage());

        // components
        this.setJMenuBar(new MainMenu(this, club));
        this.getContentPane().add(new TabContainer(club), BorderLayout.CENTER);

        this.pack();
    }

    private void createAndHideGUI() {
        this.createGUI();
        this.hideGUI();
    }

    private void hideGUI() {
        this.setVisible(false);
    }

    private void showGUI() {
        this.setVisible(true);
    }

    private void setCustomLookAndFeel(LookAndFeel uiStyle) {
        try {
            switch (uiStyle) {
                case SYSTEM: {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                break;
                case METAL: {
                    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                }
                break;
                case MOTIF: {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                }
                break;
                case GTK: {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
                }
                break;
                case NIMBUS: {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                }
                break;
            }
        } catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }


    private void onCloseOperation(JFrame frame) {
        try {
            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(frame, "Wollen Sie Ihre Änderungen vor dem Schließen speichern?", "Ungespeicherte Änderungen", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE)) {
                DataAccess.getInstance().writeXML(club);
                System.out.println("Schließen mit Speichern");
            } else {
                System.out.println("Schließen ohne Speichern");
            }
        } catch (JAXBException e1) {
            e1.printStackTrace();
            Warning.displayWarning(e1.getMessage(), "Die Änderungen konnten nicht gespeichert werden.");
        }
        frame.dispose();
        System.exit(0);
    }
}
