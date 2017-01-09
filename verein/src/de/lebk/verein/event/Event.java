/*
 * The MIT License
 *
 * Copyright 2016 sopaetzel.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package de.lebk.verein.event;

import de.lebk.verein.club.Club;
import de.lebk.verein.member.Member;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
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

    @XmlElement(name = "dateTime")
    public GregorianCalendar getDateTime() {
        return dateTime;
    }

    @XmlElement(name = "organisator")
    public Member getOrganisator() {
        return organisator;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAttendees(List<Member> attendees) {
        this.attendees = attendees;
    }

    public void setOrganisator(Member organisator) {
        this.organisator = organisator;
    }

    public void setDateTime(GregorianCalendar dateTime) {
        this.dateTime = dateTime;
    }

}
