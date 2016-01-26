package com.processing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader
{
	/**
	 * Charge la liste des propriétés contenu dans le fichier spécifié
	 *
	 * @param filename le fichier contenant les propriétés
	 *           
	 * @return un objet Properties contenant les propriétés du fichier
	 */
	public static Properties load(String filename) throws IOException, FileNotFoundException
	{
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		properties.load(classLoader.getResourceAsStream(filename));
		
			return properties;
	}

	/**
	 * Charge les propriétés contenu dans le fichier spécifié
	 *
	 * @param key la clé contenant la propriété
	 *           
	 * @return la valeur de la clé
	 */
	public static String loadPropertie(String key)
	{
		String property=null;
		try
		{
			// chargement des propriétés
			Properties prop = PropertyLoader.load("config.properties");
			// Récupère la propriété ma.cle
			// Si la propriété n'existe pas, retourne la valeur par défaut
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
