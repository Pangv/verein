package de.lebk.verein.lease;

import de.lebk.verein.member.Member;
import java.util.GregorianCalendar;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author ebrinker
 */
@XmlRootElement(name = "lease")
@XmlType(name = "lease", propOrder = {"member", "amount", "dueDate"})
public class Lease {

    @XmlElement
    private Member member;
    @XmlElement
    private int amount;
    @XmlElement
    private GregorianCalendar dueDate;

    public Lease(Member member, int amount, GregorianCalendar dueDate) {
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
}
