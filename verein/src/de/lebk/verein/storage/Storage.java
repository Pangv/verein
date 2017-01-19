package de.lebk.verein.storage;

import de.lebk.verein.lease.Lease;
import de.lebk.verein.member.Member;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author ebrinker
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Storage {

    
    private int amount;
    private static List<Lease> leases = new ArrayList<>();

    
    public int getAmount() {
        return amount;
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
        Lease leaseToAdd = new Lease(member, amount, dueDate);
        leases.add(leaseToAdd);
        member.getLeases().add(leaseToAdd);
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
