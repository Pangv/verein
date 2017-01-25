package de.lebk.verein.entry;

import de.lebk.verein.club.Club;
import de.lebk.verein.data_access.DataAccess;
import de.lebk.verein.utilities.MainFrame;
import de.lebk.verein.utilities.Warning;

import javax.xml.bind.JAXBException;

/**
 *
 * @author sopaetzel
 */
public class Entry {

    private static DataAccess doa;
    private static Club club;
    public static void main(String[] args) {

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

        new MainFrame(club);

    }

}
