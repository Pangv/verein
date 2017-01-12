package de.lebk.verein.entry;

import de.lebk.verein.club.Club;
import de.lebk.verein.data_access.DataAccess;
import de.lebk.verein.event.Event;
import de.lebk.verein.login.LoginDialog;
import de.lebk.verein.member.Member;
import de.lebk.verein.member.Officer;
import de.lebk.verein.utilities.MainFrame;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author sopaetzel
 */
public class Entry {

    private static MainFrame mainFrame;
    private static LoginDialog loginDialog;

    // TODO: Remove later
    static boolean loggedIn = true;

    public static void main(String[] args) throws Exception {

        DataAccess doa = new DataAccess();
        Club club = doa.readXML();

//        Member alfred = new Member("Alfred", "Hitchcock", "hallo", "alfredo", 'm', new GregorianCalendar(1992, 1, 1));
//        Member anna = new Member("Anna", "Müller", "start", "annam", 'f', new GregorianCalendar(2000, 3, 3));
        Member test = new Member("John-Ebenezer", "Scrooge Doe", "start", "john", 'm', new GregorianCalendar());
//        Officer tim = new Officer("tim", "Struppi", "start", "a", 'm', new GregorianCalendar(1992, 3, 4));
//        Event e1 = new Event("Steinigung", "Spaß mit Wackersteinen", anna, new GregorianCalendar(2000, 3, 3, 12, 0));
//
//        ArrayList<Member> members = new ArrayList<>();
//        ArrayList<Officer> officers = new ArrayList<>();
//        ArrayList<Event> events = new ArrayList<>();
//
//        members.add(alfred);
//        members.add(anna);
//        members.add(test);
//
//        officers.add(tim);
//        events.add(e1);
//
//        club.setOfficerList(officers);
//        club.setMembers(members);
//        club.setEvents(events);
//
//        doa.writeXML(club);

        mainFrame = new MainFrame(club, test, loggedIn);

        club.getMembers().forEach((member) -> {
            System.out.println("Members: " + member.getFullName());
        });

        club.getOfficers().forEach((officer) -> {
            System.out.println("Officers: " + officer.getFullName());
        });

        club.getEvents().forEach((eventsv) -> {
            System.out.println("Events: " + eventsv.getTitle());
        });
        
        
        

    }

}
