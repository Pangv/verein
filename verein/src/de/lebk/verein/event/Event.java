package de.lebk.verein.event;

import de.lebk.verein.login.Auth;
import de.lebk.verein.member.Member;

import javax.xml.bind.annotation.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author sopaetzel
 */
@XmlRootElement(name = "event")
@XmlType(name = "event", propOrder = {"eventType", "title", "dateTime", "organizer", "attendees"})
public class Event {

    private String eventType;
    private String title;
    private List<Member> attendees = new ArrayList<>();
    private Member organizer;
    private GregorianCalendar dateTime;

    public Event() {
    }

    public Event(String eventType, String title, Member organizer, GregorianCalendar dateTime) {
        this.eventType = eventType;
        this.title = title;
        this.organizer = organizer;
        this.dateTime = dateTime;
        System.out.println("Event erstellt: "
                + this.getTitle() + " [" + this.getEventType() + "," + this.getOrganizer().getFullName() + "," + this.getDateTime() + "]");
    }

    @XmlAttribute(name = "event-type")
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @XmlElement(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name = "dateTime")
    private GregorianCalendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(GregorianCalendar dateTime) {
        this.dateTime = dateTime;
    }

    @XmlElement(name = "organizer")
    public Member getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Member organizer) {
        this.organizer = organizer;
    }

    // @XmlAttribute(name = "attendee-count", required = false)
    public int getAttendeeCount() {
        return this.attendees.size();
    }

    @XmlElementWrapper(name = "attendees")
    @XmlElement(name = "attendee")
    public List<Member> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Member> attendees) {
        this.attendees = attendees;
    }

    public void addAttendee(Member member) {
        this.attendees.add(member);
    }

    public void removeAttendee(Member member) {
        this.attendees.remove(member);
    }

    public void changeOrganisator(Member member) {
        this.organizer = member;
    }

    public void cancelEvent() {
        Auth.getInstance().getClub().getEvents().remove(this);
    }

    public void addEvent() {
        Auth.getInstance().getClub().getEvents().add(this);
    }

    public void changeDateTime(int year, int month, int day, int hour, int minute) {
        this.dateTime = new GregorianCalendar(year, month, month, hour, minute);
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        return "Titel: " + this.title
                + "\t\t >>>>Veranstalter: " + this.organizer.getFullName()
                + "\t\t >>>>Datum & Uhrzeit: " + dateFormat.format(dateTime.getTime());
    }
}
