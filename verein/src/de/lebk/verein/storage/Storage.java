package de.lebk.verein.storage;

import de.lebk.verein.member.Member;
import de.lebk.verein.utilities.Warning;

import javax.xml.bind.annotation.*;
import java.util.*;

/**
 * @author ebrinker
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Storage {


    private static List<Lease> leases = new ArrayList<>();
    private int amount = 2000;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @XmlElementWrapper(name = "leases")
    @XmlElement(name = "lease")
    public List<Lease> getLeases() {
        return leases;
    }

    public void removeLease(Lease lease) {
        leases.remove(lease);
        lease.getMember().getLeases().remove(lease);
    }

    public void addLease(Member member, int amount, GregorianCalendar dueDate) {
        leases.add(new Lease(member, amount, dueDate));
        if ((getAmount() - amount) < 0) {
            Warning.displayWarning("kleiner Null", "Es können nicht mehr Steine ausgeliehen werden als vorhanden sind.");
        } else {
            setAmount(getAmount() - amount);
        }
    }

    public Map<Member, List<Lease>> getAllOverdueLeases() {
        GregorianCalendar today = new GregorianCalendar();
        Map<Member, List<Lease>> overdueLeases = new HashMap<>();
        for (Lease lease : leases) {
            if (lease.getDueDate().before(today)) {
                List<Lease> overdueLeasesOfLeaseOwner = overdueLeases.get(lease.getMember());
                if (overdueLeasesOfLeaseOwner != null) {
                    overdueLeasesOfLeaseOwner.add(lease);
                } else {
                    List<Lease> leases = new ArrayList<>();
                    leases.add(lease);
                    overdueLeases.put(lease.getMember(), leases);
                }
            }
        }
        return overdueLeases;
    }

    public List<Lease> getLeasesForMember(Member member) {
        List<Lease> membersLeases = new ArrayList<>();
        for (Lease lease : leases) {
            if (lease.getMember() == member) {
                membersLeases.add(lease);
            }
        }
        return membersLeases;
    }


    public List<Lease> getOverdueLeasesForMember(Member member) {
        GregorianCalendar today = new GregorianCalendar();
        List<Lease> overdueLeases = new ArrayList<>();
        for (Lease lease : leases) {
            if (lease.getMember() == member && lease.getDueDate().before(today)) {
                overdueLeases.add(lease);
            }
        }
        return overdueLeases;
    }
}
