package de.lebk.verein.club;

import de.lebk.verein.event.Event;
import de.lebk.verein.login.Auth;
import de.lebk.verein.login.UserNotFoundException;
import de.lebk.verein.member.Member;
import de.lebk.verein.member.Officer;
import de.lebk.verein.payment.Payment;
import de.lebk.verein.payment.PaymentState;
import de.lebk.verein.storage.Storage;

import javax.xml.bind.annotation.*;
import java.util.*;

/**
 * @author mraddatz
 */
@XmlRootElement(name = "club")
@XmlType(name = "club", propOrder = {"members", "officers", "events", "payments", "money", "storage"})
public class Club {

    @XmlAttribute(name = "currency")
    private final String currency = "eur";
    private double money = 2000.00;
    private List<Officer> officers = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private List<Event> events = new ArrayList<>();
    private Map<Member, ArrayList<Payment>> payments = new HashMap<>();
    private Storage storage = new Storage();
    private Auth auth = Auth.getInstance();

    public Club() {
        auth.setClub(this);
    }

    @XmlElementWrapper(name = "members")
    @XmlElement(name = "member")
    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }


    public Member getUser(String username) throws UserNotFoundException {
        for (Member member : this.members) {
            if (member.getUsername().equals(username)) {
                return member;
            }
        }
        for (Member member : this.officers) {
            if (member.getUsername().equals(username)) {
                return member;
            }
        }

        throw new UserNotFoundException(username);

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

/*    public Event getEvent(Event event){

        return
    }*/

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    /**
     * @return Eine Liste aller Vorstände
     */
    @XmlElementWrapper(name = "officers")
    @XmlElement
    public List<Officer> getOfficers() {
        return officers;
    }

    /**
     * Legt eine Liste aller Vorstände für diese Verein fest
     *
     * @param officerList Die Liste aller Vorstände
     */
    public void setOfficerList(List<Officer> officerList) {
        this.officers = officerList;
    }

    /**
     * Entfernt einen Vorstand aus der Liste der Vorstände dieses Vereins
     *
     * @param officer Der Vorstand der zurücktritt.
     */
    public void retireOfficer(Officer officer) {
        this.officers.remove(officer);
    }

    /**
     * Legt ein neues Mitglied für den Verein an
     *
     * @param firstName Der Vorname des Mitglieds
     * @param lastName  Der Nachname des Mitglieds
     * @param username  Der Username des Mitglieds
     * @param password  Das Passwort des Mitglieds
     * @param sex       Das Geschlecht des Mitglieds
     */
    public void join(String firstName, String lastName, String username, String password, char sex) {
        this.members.add(new Member(firstName, lastName, username, password, sex, new GregorianCalendar(Locale.getDefault())));
    }

    /**
     * Entfernt ein Mitglied aus dem Verein
     *
     * @param member Das Mitglied das den Verein verlässt
     */
    public void leave(Member member) {
        this.members.remove(member);
    }

    @XmlElement
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
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

    @XmlElementWrapper(name = "payments")
    @XmlElement
    public Map<Member, ArrayList<Payment>> getPayments() {
        return payments;
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

    /**
     * @return Das Lager des Vereins
     */
    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

}
