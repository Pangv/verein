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
    private List<Lease> leases = new ArrayList<>();
    private int amount = 2000;
    private GregorianCalendar today = new GregorianCalendar();

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

	public void addLease(Member member, int amount, GregorianCalendar dueDate)
		throws OutOfStonesException {
		Lease leaseToAdd = new Lease(member, amount, dueDate);
        leases.add(leaseToAdd);
        member.getLeases().add(leaseToAdd);
        if ((getAmount() - amount) < 0) {
			throw new OutOfStonesException();
		} else {
            setAmount(getAmount() - amount);
        }
    }

    public Map<Member, List<Lease>> getAllOverdueLeases() {
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
        List<Lease> overdueLeases = new ArrayList<>();
        for (Lease lease : leases) {
            if (lease.getMember() == member && lease.getDueDate().before(today)) {
                overdueLeases.add(lease);
            }
        }
        return overdueLeases;
    }
    
    public void setToday(GregorianCalendar today) {
        this.today = today;
    }
}
