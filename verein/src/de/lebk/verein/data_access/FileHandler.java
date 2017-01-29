package de.lebk.verein.data_access;

import de.lebk.verein.utilities.Warning;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.Properties;

/**
 * @author sopaetzel
 */
class FileHandler {

    private Properties prop = new Properties();
    private String lastFile;
    private OutputStream output = null;
    private InputStream input = null;

    private final String PATH = System.getProperty("user.home");

    private void createProps(String lastFile) {
        try {
            output = new FileOutputStream("config.properties");
            prop.setProperty("file", lastFile);

            prop.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String readProps() {
        String filePath = null;
        try {
            input = new FileInputStream("config.properties");
            prop.load(input);
            filePath = prop.getProperty("file");
        } catch (FileNotFoundException e) {
            Warning.displayWarning(e.getMessage(), "Einstellungen wurden nicht gefunden");
            e.printStackTrace();
        } catch (IOException e) {
            Warning.displayWarning(e.getMessage(), "Einstellungen konnten nicht geladen werden");
            e.printStackTrace();
        }


        return filePath;

    }


    File openFile() throws NullPointerException {
        File directory = new File(PATH);
        File file = null;


        System.out.println("jfc");
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(directory);
        jFileChooser.setFileFilter(new FileNameExtensionFilter("Vereinsdatei", "xml"));


        if(readProps() != null && JOptionPane.showConfirmDialog(null, "Wollen Sie Ihren letzten Verein laden?", "Letzte Einstellungen laden", JOptionPane.OK_CANCEL_OPTION ,JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION){
            System.out.println("Show Properties");
            file = new File(readProps());
        }else {
            jFileChooser.showSaveDialog(null);

            if (!jFileChooser.getSelectedFile().exists()) {
                try {
                    file = chooseFile(jFileChooser);
                } catch (IOException e) {
                    e.printStackTrace();
                    Warning.displayWarning(e.getMessage(), "Das XML konnte nicht erstellt werden");
                }
            } else {

                file = jFileChooser.getSelectedFile();
            }

            if (file != null) {
                createProps(file.getAbsolutePath());
            }
        }


        System.out.println(jFileChooser.getSelectedFile());

        return file;

    }

    private File chooseFile(JFileChooser jFileChooser) throws IOException {
        FileWriter fw = null;
        jFileChooser.getSelectedFile().createNewFile();
        File file = jFileChooser.getSelectedFile();
        fw = new FileWriter(file);
        fw.write("<club></club>");
        fw.close();

        return file;
    }

}


