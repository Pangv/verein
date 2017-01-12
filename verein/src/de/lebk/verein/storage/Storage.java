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
import sun.util.calendar.LocalGregorianCalendar.Date;

/**
 *
 * @author ebrinker
 */
public class Storage {

    private int amount;
    private List<Lease> listOfLeases;

    public int getAmount() {
        return amount;
    }

    public List<Lease> getListOfLeases() {
        return listOfLeases;
    }

    public void removeLease(Lease lease) {
        Lease leaseToRemove = null;
        for (Lease currentLease : listOfLeases) {
            if (currentLease.equals(lease)) {
                listOfLeases.remove(lease);
                //break wird nur so lange benötigt, bis es einen eindeutigen 
                //Identifier für Lease gibt (vgl. Lease-Klasse id)
                break;
            }
        }
    }

    public void addLease(Member member, int amount, GregorianCalendar dueDate) {
        listOfLeases.add(new Lease(member, amount, dueDate));
    }

    public Map<Member, List<Lease>> getAllOverdueLeases() {
        Map<Member, List<Lease>> overdueLeases = new HashMap<Member, List<Lease>>();
        return overdueLeases;
    }

    public List<Lease> getOverdueLeasesForMember(Member member) {
        List<Lease> overdueLeases = new ArrayList<Lease>();
        return overdueLeases;
    }
}
