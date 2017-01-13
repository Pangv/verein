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
        for(Lease lease : listOfLeases) {
            if(lease.getDueDate().before(today)) {
                List<Lease> overdueLeasesOfLeaseOwner = overdueLeases.get(lease.getMember());
                if(overdueLeasesOfLeaseOwner != null) {
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
        for(Lease lease : listOfLeases) {
            if(lease.getMember() == member && lease.getDueDate().before(today)) {
                overdueLeases.add(lease);
            }
        }
        return overdueLeases;
    } 
}
