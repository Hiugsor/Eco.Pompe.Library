package com.processing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader
{
	/**
	 * Charge la liste des propri�t�s contenu dans le fichier sp�cifi�
	 *
	 * @param filename le fichier contenant les propri�t�s
	 *           
	 * @return un objet Properties contenant les propri�t�s du fichier
	 */
	public static Properties load(String filename) throws IOException, FileNotFoundException
	{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		properties.load(classLoader.getResourceAsStream(filename));
		
			return properties;
	}

	/**
	 * Charge les propri�t�s contenu dans le fichier sp�cifi�
	 *
	 * @param key la cl� contenant la propri�t�
	 *           
	 * @return la valeur de la cl�
	 */
	public static String loadPropertie(String key)
	{
		String property=null;
		try
		{
			// chargement des propri�t�s
			Properties prop = PropertyLoader.load("config.properties");
			// R�cup�re la propri�t� ma.cle
			// Si la propri�t� n'existe pas, retourne la valeur par d�faut
			// null
			return property = prop.getProperty(key);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return property = null;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return property = null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return property = null;
		}
	}
}
