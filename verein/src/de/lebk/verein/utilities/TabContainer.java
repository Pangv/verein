package de.lebk.verein.utilities;

import de.lebk.verein.club.Club;
import de.lebk.verein.club.ClubManager;
import de.lebk.verein.entry.Dashboard;
import de.lebk.verein.event.EventManager;
import de.lebk.verein.login.Auth;
import de.lebk.verein.storage.StorageManager;

import javax.swing.*;

/**
 * @author sopaetzel
 */
class TabContainer extends JTabbedPane {
    private Club club;

    TabContainer(Club club) {
        this.club = club;
        this.createAndAddTabs();
    }

    private void createAndAddTabs() {
        Dashboard dashboard = new Dashboard(club);
        EventManager eventManager = new EventManager(club, Auth.getInstance().getCurrentUser());
        ClubManager clubManager = new ClubManager(club);
        StorageManager storageManager = new StorageManager(club);

        this.setTabPlacement(TOP);
        this.setTabLayoutPolicy(WRAP_TAB_LAYOUT);

        this.addTab("Dashboard", dashboard);
        this.addTab("Veranstaltungen", eventManager);
        this.addTab("Vereinsverwaltung", clubManager);
        this.addTab("Lagerverwaltung", storageManager);

        this.setSelectedIndex(0);

    }

}
