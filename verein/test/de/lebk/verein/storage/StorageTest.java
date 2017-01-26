/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.lebk.verein.storage;

import de.lebk.verein.lease.Lease;
import de.lebk.verein.member.Member;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Eva Brinker
 */
public class StorageTest {
    
    public StorageTest() {
    }

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
       
    }

    @Test
    public void testGetOverdueLeasesForMember() {

    }
    
}