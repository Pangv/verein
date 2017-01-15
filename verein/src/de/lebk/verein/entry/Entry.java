package de.lebk.verein.entry;

import de.lebk.verein.club.Club;
import de.lebk.verein.data_access.DataAccess;
import de.lebk.verein.data_access.FileHandler;
import de.lebk.verein.login.LoginDialog;
import de.lebk.verein.member.Member;
import de.lebk.verein.storage.Storage;
import de.lebk.verein.utilities.MainFrame;
import de.lebk.verein.utilities.Warning;
import java.util.GregorianCalendar;
import javax.xml.bind.JAXBException;

/**
 *
 * @author sopaetzel
 */
public class Entry {

    private static MainFrame mainFrame;
    private static LoginDialog loginDialog;
    private static DataAccess doa;
    private static Club club;

    // TODO: Remove later
    static boolean loggedIn = true;

    public static void main(String[] args) {

        FileHandler fh = new FileHandler();
        fh.createFolder();
        Member test = new Member("John-Ebenezer", "Scrooge Doe", "start", "john", 'm', new GregorianCalendar());
        Storage s = new Storage();
        s.addLease(test, 5, new GregorianCalendar(1992, 19, 10));
        s.addLease(test, 5, new GregorianCalendar(1992, 19, 10));
        s.addLease(test, 5, new GregorianCalendar(1992, 19, 10));
        s.addLease(test, 5, new GregorianCalendar(1992, 19, 10));
        

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

        mainFrame = new MainFrame(club, test, loggedIn);
        club.setStorage(s);

        club.getMembers().forEach((member) -> {
            System.out.println("Members: " + member.getFullName());
        });

        club.getOfficers().forEach((officer) -> {
            System.out.println("Officers: " + officer.getFullName());
        });

        club.getEvents().forEach((eventsv) -> {
            System.out.println("Events: " + eventsv.getTitle());
        });

        if (club.getStorage() != null) {
            club.getStorage().getLeases().forEach((lease) -> {
                System.out.println("Lease: " + lease.getMember().getFullName() + " " + lease.getDueDate().getTime());
            });
        }

    }

}
