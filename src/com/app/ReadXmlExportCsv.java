package com.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;

public class ReadXmlExportCsv
{
	private static String	xmlSource;
	public static String	prix;
	public static String	rupture;

	
	
	/**
	 * Créer le fichier CSV pour l'update BDD 
	 * à partir du XML
	 *
	 */
	public static void creatUpdateBddCsv()
	{
		System.out.println("ReadXmlAndExportCsv PROCESS..");
		xmlSource = "src\\"+getLastXmlPath();
		try
		{
			String newFileName = "src\\data\\UpdateBDD.csv";
			File newFile = new File(newFileName);
			BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
			PrintWriter pw = new PrintWriter(writer);

			// Write to file for the first row
			pw.print("id");
			pw.print(",");
			pw.println("prix");
//			pw.print(",");
//			pw.println("rupture");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dombuilder = factory.newDocumentBuilder();

			org.w3c.dom.Document w3cDocument = dombuilder.parse(xmlSource);
			DOMBuilder builder = new DOMBuilder();
			Document doc = builder.build(w3cDocument);
			Element root = doc.getRootElement();
			List<Element> row = root.getChildren("pdv");
			for (int i = 0; i < row.size(); i++)
			{
				Element pdv =  row.get(i);
				String idPdv = pdv.getAttribute("id").getValue(); //récupération de l'ID de la station

				if (pdv.getChild("prix") != null)//récupération des ID,VALEUR des carburants
				{
					prix = "";
					List<Element> prixElements = pdv.getChildren("prix");
					for (Element element : prixElements)
					{
						prix = prix + "/" + element.getAttributeValue("id")
								+ ";" + element.getAttributeValue("valeur");
					}
				}
//				if (pdv.getChild("rupture") != null)//récupératiob de l'ID,NOM, des rupture de carburants
//				{
//					rupture = "";
//					List<Element> ruptureElements = pdv.getChildren("rupture");
//					for (Element ruptureElem : ruptureElements)
//					{
//						rupture = rupture + "/" + ruptureElem.getAttributeValue("id") + ";"+ ruptureElem.getAttributeValue("nom");
//					}
//				}
				pw.print(idPdv);
				pw.print(",");
				pw.println(prix);
//				pw.print(",");
//				pw.println(rupture);
			}
			pw.flush();
			// Close the Print Writer
			pw.close();
			// Close the File Writer
			writer.close();
		}
		catch (NumberFormatException e)
		{
			System.out.println("NumberFormatException " + e.getMessage());
		}
		catch (ParserConfigurationException e)
		{
			System.out.println("ParserConfigurationException " + e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println("IOException " + e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("Exception " + e.getMessage() + " OU " + e.getLocalizedMessage());
		}
		System.out.println("ReadXmlAndExportCsv Done!");
	}

	
	/**
	 * Obtient le path du XML 
	 *
	 * @param path chemin du fihier csv à lire
	 *           
	 * @return le path du XML
	 */
	private static String getLastXmlPath()
	{
		String xmlPath=null;
		
		String directoryPath = "src\\";
		File directory = new File(directoryPath);
		File moreRecentFile = null;
		
		if (!directory.exists()) {
			System.out.println("Le fichier/répertoire '" + directoryPath + "' n'existe pas");
		} else if (!directory.isDirectory()) {
			System.out.println("Le chemin '" + directoryPath + "' correspond à un fichier et non à un répertoire");
		} else {
			File[] subfiles = directory.listFiles();			
			if( subfiles.length > 0 ){
				moreRecentFile = subfiles[0];
				for (int i = 1; i < subfiles.length; i++) {
					File subfile = subfiles[i];
					//System.out.println(subfile.getName());
					
					if (subfile.lastModified() > moreRecentFile.lastModified()) 
						moreRecentFile = subfile;
				}
				xmlPath = moreRecentFile.getName();
			}else{
				System.out.println("Le répertoire ne contient pas de fichiers!!!");
				xmlPath = null;
			}
		}
		
		
		return xmlPath;
	}
}
