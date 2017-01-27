package de.lebk.verein.entry;

import de.lebk.verein.club.Club;
import de.lebk.verein.data_access.DataAccess;
import de.lebk.verein.member.Officer;
import de.lebk.verein.utilities.MainFrame;
import de.lebk.verein.utilities.Warning;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author sopaetzel
 */
public class Entry {

    private static DataAccess doa;
    private static Club club;

    public static void main(String[] args) {


        JOptionPane.showMessageDialog(null, "Wählen Sie den Speicherpunkt Ihrer club.xml");


        try {
            doa = DataAccess.getInstance();
        } catch (JAXBException e) {
            Warning.displayWarning(e.getMessage(), "Instanz von JAXBContext konnte nicht erstellt werden.");
            e.printStackTrace();
        }
        try {
            club = doa.readXML();
        } catch (JAXBException e) {
            Warning.displayWarning(e.getMessage(), "Die XML Datei konnte nicht eingelesen werden.");
            e.printStackTrace();
        }

        Officer admin = new Officer("John", "Lassiter", "admin", "admin", 'm', new GregorianCalendar(
                Locale.getDefault()
        ));

        if (!initialAdminExists()) {
            club.getOfficers().add(admin);
        }


        new MainFrame(club);

    }

    private static boolean initialAdminExists() {
        int i = 0;
        boolean adminExists = false;
        for (Officer officer : club.getOfficers()) {
            if (officer.getUsername().contains(club.getOfficers().get(i++).getUsername())) {
                System.out.println("admin vorhanden");
                adminExists = true;
            } else {
                System.out.println("admin nicht vorhanden");
                adminExists = false;
            }
        }
        return adminExists;
    }

}
