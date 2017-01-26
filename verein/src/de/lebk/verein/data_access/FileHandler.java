package de.lebk.verein.data_access;

import de.lebk.verein.utilities.Warning;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author sopaetzel
 */
public class FileHandler {

    private final String PATH = System.getProperty("user.home");


    public File openFile() throws NullPointerException {
        File directory = new File(PATH);
        File file = null;


        System.out.println("jfc");
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(directory);
        jFileChooser.setFileFilter(new FileNameExtensionFilter("Vereinsdatei", "xml"));

        jFileChooser.showSaveDialog(null);

        if (!jFileChooser.getSelectedFile().exists()) {
            try {
                file = createNewFile(jFileChooser, file);
            } catch (IOException e) {
                e.printStackTrace();
                Warning.displayWarning(e.getMessage(), "Das XML konnte nicht erstellt werden");
            }
        } else {
            file = jFileChooser.getSelectedFile();
        }

        System.out.println(jFileChooser.getSelectedFile());
        return file;

    }

    private File createNewFile(JFileChooser jFileChooser, File file) throws IOException {
        FileWriter fw = null;
        jFileChooser.getSelectedFile().createNewFile();
        file = jFileChooser.getSelectedFile();
        fw = new FileWriter(file);
        fw.write("<club></club>");
        fw.close();

        return file;
    }

}


