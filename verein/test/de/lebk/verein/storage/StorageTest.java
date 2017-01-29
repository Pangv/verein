package de.lebk.verein.storage;

import de.lebk.verein.member.Member;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import java.util.GregorianCalendar;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Eva Brinker
 */
public class StorageTest {
    
    @Test
    public void testRemoveLease() {
        Storage storage = new Storage();
        Member ownerOfLease = new Member();
        storage.addLease(ownerOfLease,20, new GregorianCalendar());
        storage.removeLease(storage.getLeases().get(0));
        
        assertEquals("If a lease is removed by the storage the lease should be "
                + "removed from the storage", storage.getLeases().size(), 0);
    }

    @Test
    public void testAddLease() {
        Storage storage = new Storage();
        Member ownerOfLease = new Member();

        storage.addLease(ownerOfLease, 5, new GregorianCalendar());

        assertEquals("If a lease is added by the storage the lease should be "
                + "added to the storage", storage.getLeases().size(), 1);
    }

    @Test
    public void testGetAllOverdueLeases() {
        Storage storage = new Storage();
        
        Member member1 = new Member();
        member1.setFirstName("member1");
        Member member2 = new Member();
        member2.setFirstName("member2");
        Member member3 = new Member();
        member3.setFirstName("member3");
        
        GregorianCalendar today = new GregorianCalendar();
        today.set(2017, 2, 4);
        storage.setToday(today);
        
        GregorianCalendar dateBeforeToday = new GregorianCalendar();
        dateBeforeToday.set(2017, 1, 1);
        
        GregorianCalendar dateAfterToday = new GregorianCalendar();
        dateAfterToday.set(2017, 4, 2);
        
        storage.addLease(member1, 50, dateBeforeToday);
        storage.addLease(member1, 20, dateAfterToday);
        
        storage.addLease(member2, 15, dateBeforeToday);
        storage.addLease(member2, 22, dateBeforeToday);
        storage.addLease(member2, 11, dateAfterToday);
        
        storage.addLease(member3, 80, dateAfterToday);
        
        Map<Member, List<Lease>> overdueLeases = storage.getAllOverdueLeases();
        
        assertEquals("there should be 2 entries in overdue leases because there "
                + "are 2 members with overdue leases", 2, overdueLeases.size());
        assertTrue("one of the member with overdue leases should be member1", 
                overdueLeases.keySet().stream().anyMatch(member -> member.getFirstName().equals("member1")));
        assertTrue("one of the member with overdue leases should be member2", 
                overdueLeases.keySet().stream().anyMatch(member -> member.getFirstName().equals("member2")));
        assertEquals("member1 should have 1 overdue lease", 1, overdueLeases.get(member1).size());
        assertEquals("member2 should have 2 overdue lease", 2, overdueLeases.get(member2).size());
    }

    @Test
    public void testGetOverdueLeasesForMember() {
        Storage storage = new Storage();
       
        Member member = new Member();
        
        GregorianCalendar today = new GregorianCalendar();
        today.set(2017, 2, 4);
        storage.setToday(today);
        
        GregorianCalendar dateBeforeToday = new GregorianCalendar();
        dateBeforeToday.set(2017, 1, 1);
        
        GregorianCalendar dateAfterToday = new GregorianCalendar();
        dateAfterToday.set(2017, 4, 2);
        
        storage.addLease(member, 15, dateBeforeToday);
        storage.addLease(member, 22, dateBeforeToday);
        storage.addLease(member, 11, dateAfterToday);
        
        List<Lease> overdueLeases = storage.getOverdueLeasesForMember(member);
        
        assertEquals("there should be 2 overdue leases because the member has "
                + "2 overdue leases", 2, overdueLeases.size());
    }

}