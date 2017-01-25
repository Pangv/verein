package de.lebk.verein.club;

import de.lebk.verein.event.Event;
import de.lebk.verein.member.Member;
import de.lebk.verein.member.Officer;
import de.lebk.verein.payment.Payment;
import de.lebk.verein.payment.PaymentState;
import de.lebk.verein.storage.Storage;
import de.lebk.verein.vote.Vote;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mraddatz
 */
@XmlRootElement(name = "club")
@XmlType(name = "club", propOrder = {"members", "officers", "events", "payments", "money", "storage"})
public class Club {

    private double money = 2000.00;
    @XmlAttribute(name = "currency")
    private final String currency = "eur";

    private List<Officer> officers = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private List<Event> events = new ArrayList<>();
	private Map<Member, ArrayList<Payment>> payments = new HashMap<>();
	private Vote currentVote;
    private Storage storage;

    @XmlElementWrapper(name = "members")
    @XmlElement(name = "member")
    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    @XmlAttribute(name = "memberCount")
    public int getMemberCount() {
        return this.members.size();
    }

    @XmlAttribute(name = "officerCount")
    public int getOfficerCount() {
        return this.officers.size();
    }

    @XmlElementWrapper(name = "events")
    @XmlElement(name = "event")
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    @XmlElementWrapper(name = "officers")
    @XmlElement
    public List<Officer> getOfficers() {
        return officers;
    }

    public void setOfficerList(List<Officer> officerList) {
        this.officers = officerList;
    }

    public void retireOfficier(Officer officer) {
        this.officers.remove(officer);
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

    @XmlElementWrapper(name = "payments")
    @XmlElement
    public Map<Member, ArrayList<Payment>> getPayments() {
        return payments;
    }

    public Vote getCurrentVote() {
        return currentVote;
    }

    @XmlElement
    public double getMoney() {
        return money;
    }

    public void requestMoney(Member member, double amount) {
		if (this.payments.containsKey(member)) {
			this.payments.get(member).add(new Payment(member, amount));
		} else {
			ArrayList<Payment> newPayments = new ArrayList<>();
			newPayments.add(new Payment(member, amount));
			this.payments.put(member, newPayments);
		}
	}

    public void markAsPaid(Member member, Payment payment) {
        int index = this.payments.get(member).indexOf(payment);
        this.payments.get(member).get(index).setState(PaymentState.PAID);
    }

	public Map<Member, ArrayList<Payment>> getOpenPayments() {
		Map<Member, ArrayList<Payment>> openPayments = new HashMap<>();
		for (Member member : this.payments.keySet()) {
			ArrayList<Payment> payments = this.payments.get(member);
			payments.removeIf(Payment::isPaid);
			openPayments.put(member, payments);
		}
		return openPayments;
	}

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

}
