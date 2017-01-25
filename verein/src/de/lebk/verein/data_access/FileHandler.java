package de.lebk.verein.data_access;

import de.lebk.verein.utilities.Warning;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/**
 * @author sopaetzel
 */
public class FileHandler {

    private final String OS_NAME = System.getProperty("os.name");

    private final String PATH_UNIX = "/tmp/";
    private final String PATH_WINDOWS = "C:/";


    private String checkOperatingSystem() {
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

    public File openFile() throws NullPointerException {
        File directory = null;

        if (checkOperatingSystem().equals("unix")) {
            directory = new File(PATH_UNIX);
        } else if (checkOperatingSystem().equals("windows")) {
            directory = new File(PATH_WINDOWS);
        } else {
            Warning.displayWarning("", "Dieses Betriebssystem ist noch nicht untersützt.");
        }
        System.out.println("jfc");
        JFileChooser jFileChooser = new JFileChooser(directory);
        jFileChooser.setFileFilter(new FileNameExtensionFilter("Vereinsdatei", "xml"));
        jFileChooser.showOpenDialog(null);

        return jFileChooser.getSelectedFile();

    }

}
