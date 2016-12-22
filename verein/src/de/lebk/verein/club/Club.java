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
package de.lebk.verein.club;

import de.lebk.verein.event.Event;
import de.lebk.verein.event.EventGeneric;
import de.lebk.verein.event.EventLapidation;
import de.lebk.verein.member.Member;
import de.lebk.verein.member.Officier;
import de.lebk.verein.payment.Payment;
import de.lebk.verein.payment.PaymentState;
import de.lebk.verein.vote.Vote;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sopaetzel
 */
public class Club {
	private List<Officier> officiers;
	private List<Member> members;
	private double money;
	private List<Event> events;
	private Map<Member, List<Payment>> payments;
	private Vote currentVote;



	public List<Officier> getOfficierList() {
		return officiers;
	}

	public void setOfficierList(List<Officier> officierList) {
		this.officiers = officierList;
	}

	public void retireOfficier(Officier officier) {
		this.officiers.remove(officier);
	}

	public void join(String username, String password, Character sex) {
		this.members.add(new Member(username, password, sex));
	}

	public void leave(Member member) {
		this.members.remove(member);
	}

	public void initOfficerVote() {
		if (currentVote != null) {
			this.currentVote = new Vote();
		}
	}

	public void endVote() {
		/*
			TODO implement counting and results
		 */
		this.currentVote = null;
	}

	public void createGenericEvent(String eventType, List<Member> orga,
		GregorianCalendar dateTime) {
		this.events.add(new EventGeneric(eventType, orga, dateTime));
	}

	public void createLapidationEvent(String eventType, List<Member> orga,
		GregorianCalendar dateTime) {
		this.events.add(new EventLapidation(eventType, orga, dateTime));
	}

	public void deleteEvent(Event event) {
		this.events.remove(event);
	}

	public void requestMoney(Member member, double amount) {
		this.payments.get(member).add(new Payment(member, amount));
	}

	public void markAsPaid(Member member, Payment payment) {
		int index = this.payments.get(member).indexOf(payment);
		this.payments.get(member).get(index).setState(PaymentState.PAID);
	}

}
