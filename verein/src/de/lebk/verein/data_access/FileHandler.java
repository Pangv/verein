/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.lebk.verein.data_access;

/**
 *
 * @author sopaetzel
 */
public class FileHandler {
    
    private final String OS_NAME = System.getProperty("os.name");
    
    private final String PATH_UNIX = "";
    private final String PATH_WINDOWS = "";
    
    
    public void createFolder(){
        
       if(OS_NAME.startsWith("Windows")){
           System.out.println("Windows detected! Version: " + OS_NAME);
       }
       if(OS_NAME.startsWith("Mac") || OS_NAME.startsWith("Linux")){
           System.out.println("UNIX detected! Version: " + OS_NAME);
       }
        
        
        
        
        
    }
    
    
    
    
}
