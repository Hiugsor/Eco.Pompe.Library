package com.app;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import javax.naming.Context;

public class GetXml
{
	static Context			context;
	static URL				url;
	static URLConnection	connection;

	final static int		size	= 1024;

	
	/**
	 * definie les paramétres du fichier avec URL à télécharger
	 *
	 * @param fAddress URL du lien hypertext
	 *           
	 * @param localFileName nom du fichier local pour le téléchargement
	 *           
	 * @param destinationDir dossier destination pour le téléchargement
	 *           
	 */
	public static void fileUrl(String fAddress, String localFileName, String destinationDir)
	{
		OutputStream outStream = null;
		URLConnection uCon = null;

		InputStream is = null;
		try
		{
			URL url;
			byte[] buf;
			int byteRead, byteWritten = 0;
			url = new URL(fAddress);
			outStream = new BufferedOutputStream(new FileOutputStream(destinationDir + "\\" + localFileName));
			SocketAddress addr = new InetSocketAddress("10.127.254.1", 80);
			Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);

			uCon = url.openConnection(proxy);
			is = uCon.getInputStream();
			buf = new byte[size];
			while ((byteRead = is.read(buf)) != -1)
			{
				outStream.write(buf, 0, byteRead);
				byteWritten += byteRead;
			}
			System.out.println("Downloaded Successfully.");
			System.out.println("File name:\"" + localFileName + "\"\nNo ofbytes :" + byteWritten);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				is.close();
				outStream.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Télécharge le fichier avec URL donnée
	 *
	 * @param fAddress URL du lien hypertext
	 *           *           
	 * @param destinationDir dossier destination pour le téléchargement
	 *           
	 */
	public static void fileDownload(String fAddress, String destinationDir)
	{
		int slashIndex = fAddress.lastIndexOf('/');
		int periodIndex = fAddress.lastIndexOf('.');

		String fileName = fAddress.substring(slashIndex + 1) + ".rar";

		if (periodIndex >= 1 && slashIndex >= 0 && slashIndex < fAddress.length() - 1)
		{
			fileUrl(fAddress, fileName, destinationDir);
		}
		else
		{
			System.err.println("path or file name.");
		}
	}


}
