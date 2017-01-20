package de.lebk.verein.utilities;

import de.lebk.verein.club.Club;
import de.lebk.verein.club.ClubManager;
import de.lebk.verein.entry.Dashboard;
import de.lebk.verein.event.EventManager;
import de.lebk.verein.login.Auth;
import de.lebk.verein.storage.StorageManager;
import javax.swing.JTabbedPane;

/**
 *
 * @author sopaetzel
 */
public class TabContainer extends JTabbedPane {

    private Club club;
    
    private Dashboard dashboard;
    private EventManager eventManager;
    private ClubManager clubManager;
    private StorageManager storageManager;

    public TabContainer(Club club) {
        this.club = club;
        this.createAndAddTabs();
    }

    private void createAndAddTabs() {

        dashboard = new Dashboard(club);
        eventManager = new EventManager(club, Auth.getInstance().getCurrentUser());
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
