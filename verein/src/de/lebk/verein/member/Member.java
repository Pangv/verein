/*
 * The MIT License
 *
 * Copyright 2016 sopaetzel.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.lebk.verein.member;

import de.lebk.verein.event.Event;
import de.lebk.verein.lease.Lease;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 *
 * @author raddatz
 * @date 15.12.2016
 */
@XmlType(propOrder = {"firstName", "lastName", "password", "username", "sex", "entered", "leases", "events"})
public class Member {

    private String firstName;
    private String lastName;
    private String password;
    private String username;
    private char sex;
    private GregorianCalendar entered;
    private List<Lease> leases;
    private List<Event> events;

    public Member(String firstName, String lastName, String password, String username, char sex,
            GregorianCalendar entered) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.username = username;
        this.sex = sex;
        this.entered = entered;
    }

    public Member() {
    }

    public Member(String password, String username, char sex) {
        this.password = password;
        this.username = username;
        this.sex = sex;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getDateTimeEntered() {
        return entered.get(Calendar.DATE) + "." + entered.get(Calendar.MONTH) + "."
                + entered.get(Calendar.YEAR);
    }

    private String generateUsername(String fName, String lName) {
        /* TODO make usernames unique prevent same username
        do this by checking the "data storage"
         */
        Random uniqueNumber = new Random();
        return fName.toLowerCase().substring(0, 2) + (lName.toLowerCase().contains(" ") ? lName.toLowerCase().substring(0, lName.toLowerCase().indexOf(" ")) : lName.toLowerCase()) + uniqueNumber.nextInt(Integer.MAX_VALUE);
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
