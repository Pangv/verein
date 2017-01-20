package de.lebk.verein.data_access;

import de.lebk.verein.club.Club;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DataBindingException;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author sopaetzel
 */
public class DataAccess {

    // Files
    private final URL CLUB_XML_URL = ClassLoader.getSystemResource("club.xml");
    private final InputStream CLUB_XML = ClassLoader.getSystemResourceAsStream("club.xml");

    private JAXBContext jc;

    /**
     * Erstellt eine globale Instanz (Singleton) des JAXBContext
     *
     * @throws JAXBException wird geworfen wenn
     */
    public DataAccess() throws JAXBException {
        jc = JAXBContext.newInstance(Club.class);
    }

    /**
     * Liest eine vorhandene XML Datei in den Speicher und erstellt anhand der
     * Klassendefinitionen, Objekte zur Laufzeit.
     *
     * @return das object welches an der spezifizierenten URL verpackt ist
     * @throws JAXBException wird geworfen, wenn die Datei ungültig ist
     */
    public Club readXML() throws JAXBException {
        System.out.println("Read Club Data...");
        StreamSource xml = new StreamSource(CLUB_XML);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        JAXBElement<Club> rootElement = unmarshaller.unmarshal(xml, Club.class);
        return rootElement.getValue();
    }

    /**
     * Erstellt eine XML Datei, die die vorhandenen Objekte enthält.
     *
     * @param element
     * @throws JAXBException
     */
    public void writeXML(Object element) throws JAXBException, URISyntaxException {
        System.out.println("Creating output xml...");
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(element, new File(CLUB_XML_URL.toURI()));
        
        //FIXME SystemResource lässt sich nicht beschreiben / ändern
    }

    private File writeOutput(InputStream inputStream) {
        File output = null;
        OutputStream out = null;
        try {
            output = new File(CLUB_XML_URL.toURI());
            out = new FileOutputStream(output);

            byte[] buf = new byte[4096];
            int length;

            System.out.println("File: " + output.getCanonicalPath());
            while ((length = inputStream.read(buf)) > 0) {
                out.write(buf, 0, length);

            }
            out.close();
            inputStream.close();

        } catch (FileNotFoundException ex) {
           ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                 ex.printStackTrace();
            }
        }
        return output;
    }

}
