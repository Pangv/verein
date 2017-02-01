package de.lebk.verein.storage;

import de.lebk.verein.member.Member;

import javax.xml.bind.annotation.XmlType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * @author ebrinker
 */
@XmlType(name = "lease", propOrder = {"member", "amount", "dueDate"})
public class Lease {


    private Member member;
    private int amount;
    private GregorianCalendar dueDate;

    public Lease() {
    }

    Lease(Member member, int amount, GregorianCalendar dueDate) {
        this.member = member;
        this.amount = amount;
        this.dueDate = dueDate;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public GregorianCalendar getDueDate() {
        return dueDate;
    }

    public void setDueDate(GregorianCalendar dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        return getMember().getFullName() + "; " + getAmount() + "; " + dateFormat.format(getDueDate().getTime());
    }
}
