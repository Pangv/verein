package de.lebk.verein.data_access;

import de.lebk.verein.utilities.Warning;

import java.io.File;

/**
 *
 * @author sopaetzel
 */
public class FileHandler {

    private final String OS_NAME = System.getProperty("os.name");

    private final String PATH_UNIX = "/usr/tmp/verein";
    private final String PATH_WINDOWS = "C:/verein";


    public String checkOperatingSystem() {
        String os = "";
        if (OS_NAME.startsWith("Windows")) {
            System.out.println("Windows detected! Version: " + OS_NAME);
            os = "Windows";
        }
        if (OS_NAME.startsWith("Mac") || OS_NAME.startsWith("Linux")) {
            System.out.println("UNIX detected! Version: " + OS_NAME);
            os = "unix";
        }

        return os;
    }

    public void createFolder() {

        File directory = null;

        if (checkOperatingSystem().equals("unix")) {
            directory = new File(PATH_UNIX);
        } else if (checkOperatingSystem().equals("windows")) {
            directory = new File(PATH_WINDOWS);
        } else {
            Warning.displayWarning("", "Dieses Betriebssystem ist noch nicht untersützt.");
        }

        if (!directory.exists()) {
            System.out.println("Der Ordner existiert nicht.");
            directory.mkdir();
        } else {
            System.out.println("Der Ordner existiert.");
        }


    }

}
