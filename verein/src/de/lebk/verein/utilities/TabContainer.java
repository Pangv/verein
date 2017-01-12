/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.lebk.verein.utilities;

import de.lebk.verein.club.Club;
import de.lebk.verein.club.ClubManager;
import de.lebk.verein.entry.Dashboard;
import de.lebk.verein.event.EventManager;
import de.lebk.verein.member.Member;
import de.lebk.verein.storage.StorageManager;
import javax.swing.JTabbedPane;

/**
 *
 * @author sopaetzel
 */
public class TabContainer extends JTabbedPane {

    private Dashboard dashboard;
    private EventManager eventManager;
    private ClubManager clubManager;
    private StorageManager storageManager;

    Member member;

    public TabContainer(Club club, Member member) {
        this.member = member;
        createAndAddTabs(club);
    }

    public void createAndAddTabs(Club club) {

        dashboard = new Dashboard(member);
        eventManager = new EventManager(club, member);
        clubManager = new ClubManager();
        storageManager = new StorageManager();

        this.setTabPlacement(TOP);
        this.setTabLayoutPolicy(WRAP_TAB_LAYOUT);

        this.addTab("Dashboard", dashboard);
        this.addTab("Veranstaltungen", eventManager);
        this.addTab("Vereinsverwaltung", clubManager);
        this.addTab("Lagerverwaltung", storageManager);

        this.setSelectedIndex(0);

    }

}
