package de.lebk.verein.storage;

import de.lebk.verein.lease.Lease;
import de.lebk.verein.member.Member;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author ebrinker
 */
@XmlRootElement(name = "storage")
@XmlType(name = "storage", propOrder = {"amount", "leases"})
public class Storage {

    @XmlElement
    private int amount;
    @XmlElementWrapper(name = "leases")
    @XmlElement(name = "lease")
    private List<Lease> listOfLeases;

    public int getAmount() {
        return amount;
    }

    public List<Lease> getListOfLeases() {
        return listOfLeases;
    }

    public void removeLease(Lease lease) {
        listOfLeases.remove(lease);
        lease.getMember().getLeases().remove(lease);
    }

    public void addLease(Member member, int amount, GregorianCalendar dueDate) {
        listOfLeases.add(new Lease(member, amount, dueDate));
    }

    public Map<Member, List<Lease>> getAllOverdueLeases() {
        GregorianCalendar today = new GregorianCalendar();
        Map<Member, List<Lease>> overdueLeases = new HashMap<>();
        for (Lease lease : listOfLeases) {
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
        for (Lease lease : listOfLeases) {
            if (lease.getMember() == member && lease.getDueDate().before(today)) {
                overdueLeases.add(lease);
            }
        }
        return overdueLeases;
    }
}
