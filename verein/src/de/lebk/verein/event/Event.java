package de.lebk.verein.event;

import de.lebk.verein.club.Club;
import de.lebk.verein.member.Member;

import javax.xml.bind.annotation.*;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author sopaetzel
 */
@XmlRootElement(name = "event")
@XmlType(name = "event", propOrder = {"eventType", "title", "dateTime", "organisator", "attendees"})
public class Event {

    private String eventType;
    private String title;
    private List<Member> attendees;
    private Member organisator;
    private GregorianCalendar dateTime;

    public Event() {
    }

    public Event(String eventType, String title, Member organisator, GregorianCalendar dateTime) {
        this.eventType = eventType;
        this.title = title;
        this.organisator = organisator;
        this.dateTime = dateTime;
        System.out.println("Event erstellt: "
                + this.getTitle() + " [" + this.getEventType() + "," + this.getOrganisator().getFullName() + "," + this.getDateTime() + "]");
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
    public GregorianCalendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(GregorianCalendar dateTime) {
        this.dateTime = dateTime;
    }

    @XmlElement(name = "organisator")
    public Member getOrganisator() {
        return organisator;
    }

    public void setOrganisator(Member organisator) {
        this.organisator = organisator;
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
        member.addEvent(this);
    }

    public void removeAttendee(Member member) {
        this.attendees.remove(member);
        member.removeEvent(this);
    }

    public void changeOrgnisator(Member member) {
        this.organisator = member;
    }

    public void cancelEvent(Club club) {
        club.getEvents().remove(this);
    }

    public void addEvent(Club club) {
        club.getEvents().add(this);
    }

    public void changeDateTime(int year, int month, int day, int hour, int minute) {
        this.dateTime = new GregorianCalendar(year, month, month, hour, minute);
    }

}
