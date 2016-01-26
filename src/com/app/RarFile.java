package com.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.zip.ZipInputStream;

public class RarFile
{

	/**
	 * Extrat le fichier contenue dans une archive
	 *
	 * @param extractedFile fichier extrait de l'archive
	 *           
	 * @param file fichier  de l'archive à extract
	 *                      
	 */
	public static boolean extractRarFile(String extractedFile, File file)
	{

		ZipInputStream zipInputStream = null;
		FileOutputStream fileOutputStream = null;
		String split = null;
		if (extractedFile != null)
		{

			split = extractedFile.substring(0, extractedFile.length() - 4);
		}


		String extractedLocation = "src\\" + extractedFile;

		try
		{
			zipInputStream = new ZipInputStream(new FileInputStream(file));
		}
		catch (FileNotFoundException ex)
		{
			Logger.getLogger(ex.getMessage());
		}

		try
		{
			fileOutputStream = new FileOutputStream(extractedLocation);
		}
		catch (FileNotFoundException ex)
		{
			Logger.getLogger(ex.getMessage());
		}

		byte[] readingBytes = new byte[10000];

		int byteReaded = 0;

		try
		{
			if ((zipInputStream.getNextEntry()) != null)
			{
				while ((byteReaded = zipInputStream.read(readingBytes)) > 0)
				{

					fileOutputStream.write(readingBytes, 0, byteReaded);

				}
			}
		}
		catch (IOException ex)
		{
			Logger.getLogger(ex.getMessage());
		}

		try
		{
			fileOutputStream.flush();
		}
		catch (IOException ex)
		{
			Logger.getLogger(ex.getMessage());
		}
		try
		{
			zipInputStream.close();
		}
		catch (IOException ex)
		{
			Logger.getLogger(ex.getMessage());
		}

		try
		{
			fileOutputStream.close();
		}
		catch (IOException ex)
		{
			Logger.getLogger(ex.getMessage());
		}

		file.deleteOnExit();

		return true;

	}
}
