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

import de.lebk.verein.member.Member;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author sopaetzel
 */
public abstract class Event {

    //TODO DOA
    private String eventType;
    private List<Member> attendees;
    private List<Member> organisator;
    private GregorianCalendar dateTime;

    public Event(String eventType, List<Member> organisator, GregorianCalendar dateTime) {
        this.eventType = eventType;
        this.organisator = organisator;
        this.dateTime = dateTime;
    }

    public void addAttendee(Member member) {
        this.attendees.add(member);
    }

    public void removeAttendee(Member member) {
        this.attendees.remove(member);
    }

//    
//    public void addOrganisator(Member member){
//        this.organisator.add((Officer) member);
//    }
//    
//    public void removeOrganisator(Member member){
//        this.organisator.remove((Officer) member);
//    }
    public void cancelEvent() {
        //TODO implement Method Cancel Event
    }

    // TODO change dateTime
}
