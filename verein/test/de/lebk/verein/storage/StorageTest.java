package de.lebk.verein.storage;

import de.lebk.verein.member.Member;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import java.util.GregorianCalendar;
import static org.junit.Assert.assertEquals;
import org.junit.Before;

/**
 * @author Eva Brinker
 */
public class StorageTest {
    
    @Test
    public void testRemoveLease() {
        Storage storage = new Storage();
        Member ownerOfLease = new Member();
        storage.addLease(ownerOfLease, 5, new GregorianCalendar());

        storage.removeLease(ownerOfLease.getLeases().get(0));

        assertEquals("If a lease is removed by the storage the lease should be "
                + "removed from its owner", ownerOfLease.getLeases().size(), 0);
        assertEquals("If a lease is removed by the storage the lease should be "
                + "removed from the storage", storage.getLeases().size(), 0);
    }

    @Test
    public void testAddLease() {
        Storage storage = new Storage();
        Member ownerOfLease = new Member();

        storage.addLease(ownerOfLease, 5, new GregorianCalendar());

        assertEquals("If a lease is added by the storage the lease should be "
                + "added to the given member", ownerOfLease.getLeases().size(), 1);
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

    }

    @Test
    public void testGetOverdueLeasesForMember() {

    }

}