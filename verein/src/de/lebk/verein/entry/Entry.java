package de.lebk.verein.entry;

import de.lebk.verein.club.Club;
import de.lebk.verein.data_access.DataAccess;
import de.lebk.verein.member.Officer;
import de.lebk.verein.utilities.MainFrame;
import de.lebk.verein.utilities.Warning;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.util.GregorianCalendar;
import java.util.Iterator;
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


        if (!initialAdminExists()) {
            Officer admin = new Officer("John", "Lassiter", "admin", "admin", 'm', new GregorianCalendar(
                    Locale.getDefault()
            ));

            club.getOfficers().add(admin);
        }


        new MainFrame(club);

    }

    private static boolean initialAdminExists() {
        int i = 0;
        boolean adminExists = false;

        Iterator<Officer> officerIterator = club.getOfficers().iterator();
        Officer officer;
        while (officerIterator.hasNext()){
            officer = officerIterator.next();
            if (officer.getUsername().equals("admin")){
                adminExists = true;
                return adminExists;
            } else {
                adminExists = false;
            }
        }
        return adminExists;
    }

}
