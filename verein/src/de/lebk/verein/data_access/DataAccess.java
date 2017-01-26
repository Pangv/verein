package de.lebk.verein.data_access;

import de.lebk.verein.club.Club;

import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 *
 * @author sopaetzel
 */
public class DataAccess {
    private static DataAccess instance = null;

    // Files
    //private final URL CLUB_XML_URL = ClassLoader.getSystemResource("club.xml");
    private final String CLUB_XML = "./resources/club.xml";
    private static JAXBContext jc;
    
    private DataAccess(){}

    /**
     * Erstellt eine globale Instanz (Singleton) des JAXBContext
     * @throws JAXBException wird geworfen wenn keine Instanz erstellt werden kann.
     */
    public static DataAccess getInstance() throws JAXBException{
        if (instance == null) {
            instance = new DataAccess();
            jc = JAXBContext.newInstance(Club.class);
        }
        return instance;
    }

    /**
     * Liest eine vorhandene XML Datei in den Speicher und erstellt anhand der
     * Klassendefinitionen, Objekte zur Laufzeit.
     *
     * @return das object welches an der spezifizierenten URL verpackt ist
     * @throws JAXBException wird geworfen, wenn die Datei ungültig ist
     */
    public Club readXML() throws JAXBException {
        System.out.println("Creating objects from xml input...");
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
    public void writeXML(Object element) throws JAXBException {
        System.out.println("Creating xml output from objects...");
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(element, new File(CLUB_XML));

        //FIXME SystemResource lässt sich nicht beschreiben / ändern
    }

}
