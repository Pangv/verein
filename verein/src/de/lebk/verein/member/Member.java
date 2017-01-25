package de.lebk.verein.member;

import de.lebk.verein.event.Event;
import de.lebk.verein.storage.Lease;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author mraddatz
 */
@XmlType(propOrder = {"firstName", "lastName", "password", "username", "sex", "entered", "leases", "events"})
public class Member {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private char sex;
    private GregorianCalendar entered;
    private List<Lease> leases;
    private List<Event> events;

    public Member() {
    }

    public Member(String firstName, String lastName, String username, String password, char sex, GregorianCalendar entered) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.entered = entered;
    }

    public Member(String firstName, String lastName, String username, String password, char sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.sex = sex;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getDateTimeEntered() {
        return entered.get(Calendar.DATE) + "." + entered.get(Calendar.MONTH) + "."
                + entered.get(Calendar.YEAR);
    }

    @XmlElement(name = "firstname")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name = "lastname")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlElement(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlElement(name = "sex")
    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    @XmlElement(name = "entered")
    public GregorianCalendar getEntered() {
        return entered;
    }

    public void setEntered(GregorianCalendar entered) {
        this.entered = entered;
    }

    @XmlElement(name = "leases", namespace = "de.lebk.verein.member")
    public List<Lease> getLeases() {
        return leases;
    }

    public void setLeases(List<Lease> leases) {
        this.leases = leases;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public void removeEvent(Event event) {
        this.events.remove(event);
    }

}
