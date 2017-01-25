package de.lebk.verein.entry;

import de.lebk.verein.club.Club;
import de.lebk.verein.data_access.DataAccess;
import de.lebk.verein.data_access.FileHandler;
import de.lebk.verein.member.Officer;
import de.lebk.verein.utilities.MainFrame;
import de.lebk.verein.utilities.Warning;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *
 * @author sopaetzel
 */
public class Entry {

    private static DataAccess doa;
    private static Club club;
    public static void main(String[] args) {


        JOptionPane.showMessageDialog(null, "Wählen Sie den Speicherpunkt Ihrer club.xml");
        FileHandler fileHandler = new FileHandler();


        try {
            doa = DataAccess.getInstance();
        } catch (JAXBException e) {
            Warning.displayWarning(e.getMessage(), "Instanz von JAXBContext konnte nicht erstellt werden.");
            e.printStackTrace();
        }
        try {
            club = doa.readXML(fileHandler.openFile());
        } catch (JAXBException e) {
            Warning.displayWarning(e.getMessage(), "Die XML Datei konnte nicht eingelesen werden.");
            e.printStackTrace();
        }

        Officer admin = new Officer("John", "Lassiter", "admin", "admin", 'm', new GregorianCalendar(
                Locale.getDefault()
        ));
        club.getMembers().add(admin);

        new MainFrame(club);

    }

}
