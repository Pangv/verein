package de.lebk.verein.data_access;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ReadXMLData {

    public ReadXMLData(String filename) {
        
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(""));
        } catch (Exception e) {
        }
        
    }
    
    
        

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new File("./Schueler.xml"));
			
			Node rootNode = doc.getDocumentElement();
			
			NodeRekursion(rootNode);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		

	}
	
	static void NodeRekursion(Node n)
	{
		// Auswertung der Knoten
		// Knoten mit den Attributen
		if (n.getNodeType() == Node.ELEMENT_NODE)
		{
	         System.out.println("Node:\t\t"+n.getNodeName());
	         
	         for(int i=0;i<n.getAttributes().getLength();i++)
	         {
	        	 //Ausgabe des Namens und des Attributes und
	        	 // des Inhalt
	        	 System.out.println("Attribute> \t"+
	        	           n.getAttributes().item(i).getNodeName()+" "+
	        			   n.getAttributes().item(i).getTextContent());
	         }
		}
		
		if(n.getNodeType() == Node.TEXT_NODE)
		{
			String s = n.getNodeValue().trim();
			if (s.length() != 0)
				System.out.println("Inhalt> \t"+s);
		}
		
		//Rekursion Aufruf der selben Methode für die abhängigen Knoten
		NodeList kinder = n.getChildNodes();
		if(kinder != null)
		{
			for(int i=0;i<kinder.getLength();i++)
			{
				// Aufruf der selben Methode für jeden Knoten
				NodeRekursion(kinder.item(i));
			}
		}
	}

}
