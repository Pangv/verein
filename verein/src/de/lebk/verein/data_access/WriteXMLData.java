package de.lebk.verein.data_access;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class WriteXMLData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try 
		{
			// Object der Hauptklasse
			// Object wird erstellt �ber
			// Single-Ton Pattern
			DocumentBuilderFactory factory =
					DocumentBuilderFactory.newInstance();
			// Builder Aufbau der Grundstruktur f�r XML
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			// Document f�r die Aufnahme aller Knoten und Attribute
			Document document = builder.newDocument();
			
			// Erzeugen der Elemente
			// root ist das oberste Element in der Hierachie
			Element root = document.createElement("Test");
			Element unter1 = document.createElement("unterelement");
			unter1.setAttribute("Attribut", "Inhalt");
			unter1.setTextContent("Eintrag1");
			
			// Aufbau der Hierarchie
			// unter dem root Element soll in unserem Falle 
			// unter1 erstellt werden
			root.appendChild(unter1);
			
			// Dem Document den root Node zuordnen
			document.appendChild(root);
			
			//Festlegen der Datei in der XML geschrieben werden soll
			File f = new File("./Schueler2.xml");
			Result result = new StreamResult(f);
			
			// Source f�r die Entegegename unseres Dokuments
			// nach den Vorgaben des DOM modelles
			Source source = new DOMSource(document);
			
			// Umwandeln und Schreiben des Dokumentes in die Datei
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			// Methode die den XML Aufbau und den Stream f�r das Schreiben durchf�hrt
			transformer.transform(source, result);
			System.out.println("Erstellung beendet");
			
			
		
		}
		catch(Exception e)
		{
			
		}
		

	}

}
