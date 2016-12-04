/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.lebk.verein.entry;

import de.lebk.verein.view.MainFrame;


/**
 *
 * @author sopaetzel
 */
public class Entry {
    
    private boolean running = false;
    private Thread thread;
    
    public static void main(String[] args) {
        System.out.println("It is alive!");
        
        
    }
    
    public synchronized void start(){
        running = true;
        thread = new Thread(, "Display");
        thread.start();
    }
    
    public synchronized void stop(){
        running = false;
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
