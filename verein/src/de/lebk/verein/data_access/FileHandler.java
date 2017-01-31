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
    private OutputStream output = null;

    private final String PATH = System.getProperty("user.home");

    /**
     * Speichert den Pfad einer aktuellen Sitzung
     * @param lastFile der Pfad der aktuellen Sitzung
     */
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

    /**
     *
     * @return Einen Pfad einer frühren Sitzung
     */
    private String readProps() {
        String filePath = null;
        try {
            InputStream input = new FileInputStream("config.properties");
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

    /**
     *
     * @return Eine gewählte Datei
     * @throws NullPointerException Wenn die Datei nicht gefunden wird
     */
    File openFile() throws NullPointerException {
        File directory = new File(PATH);
        File file = null;
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(directory);
        jFileChooser.setFileFilter(new FileNameExtensionFilter("Vereinsdatei", "xml"));
        if(readProps() != null && JOptionPane.showConfirmDialog(null, "Wollen Sie Ihren letzten Verein laden?", "Letzte Einstellungen laden", JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
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
        return file;

    }

    private File chooseFile(JFileChooser jFileChooser) throws IOException {
        FileWriter fw;
        //noinspection ResultOfMethodCallIgnored
        jFileChooser.getSelectedFile().createNewFile();
        File file = jFileChooser.getSelectedFile();
        fw = new FileWriter(file);
        fw.write("<club></club>");
        fw.close();

        return file;
    }

}


