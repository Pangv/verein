/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.lebk.verein.view;

import de.lebk.verein.view.panel.ClubManager;
import de.lebk.verein.view.panel.Dashboard;
import de.lebk.verein.view.panel.StorageManager;
import java.awt.Component;
import javax.swing.JTabbedPane;

/**
 *
 * @author sopaetzel
 */
public class TabContainer extends JTabbedPane{
    
    private Dashboard dashboard;
    private ClubManager clubManager;
    private StorageManager storageManager;


    public void createAndAddTabs(){
        
        dashboard = new Dashboard();       
        clubManager = new ClubManager();
        storageManager = new StorageManager();

        
        
        this.addTab("Dashboard", dashboard);
        this.addTab("Vereinsverwaltung", clubManager);
        this.addTab("Lagerverwaltung", storageManager);
        
        
    }


    
    
    
    
    
}
