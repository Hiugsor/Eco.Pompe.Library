package com.app;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.dao.*;
import com.processing.PropertyLoader;

public class Program
{
	private static Logger LOGGER = Logger.getLogger("InfoLogging");

	public static void main(String[] args)
	{
		System.out.println(ConnexionParameters.getDatabaseUrl());

		Runner();
		ConnexionManager.GetInstance().close();

	}

	private static void Runner()
	{
		String xmlUrl = PropertyLoader.loadPropertie("xmlUrl");
		String tempDir = PropertyLoader.loadPropertie("tempDir");
		String dataDir = PropertyLoader.loadPropertie("dataDir");
		String rarFile = PropertyLoader.loadPropertie("rarFile");
		String xmlFile = PropertyLoader.loadPropertie("xmlFile");
		String csvFile = PropertyLoader.loadPropertie("csvFile");
		File tempDirFile = new File(tempDir);
		File dataDirFile = new File(dataDir);

		if (!tempDirFile.exists())
		{
			LOGGER.config("Create Temp Dir");
			System.out.println("PA LA");
			tempDirFile.mkdirs();
		}
		if (!dataDirFile.exists())
		{
			LOGGER.config("Create Data Dir");
			System.out.println("PA LA");
			dataDirFile.mkdirs();
		}

		List<String[]> csv;
		int idStation;
		String vendre = "";

		try

		{
			GetXml.fileDownload(xmlUrl, tempDir);
			File FileRar = new File(tempDir + rarFile);
			RarFile.extractRarFile(xmlFile, FileRar);
			ReadXmlExportCsv.creatUpdateBddCsv();
			csv = new ArrayList<String[]>(ReadCsv.readCSV(dataDir + csvFile));
			ConnexionManager.GetInstance().open();
			for (String[] station : csv)
			{

				if (!station[0].equals("id"))
				{
					idStation = Integer.parseInt(station[0]);

					vendre = station[1];

					if (!vendre.isEmpty()) // COLONE PRIX
					{
						VendreDao.updateVendrePrix(idStation, vendre);
						// System.out.println("vendre OK !!");
					}
				}

			}
			
			VendreDao.procedureStock();
		}
		catch (SQLException e)

		{
			e.printStackTrace();

		}

		catch (Exception e)

		{
			e.printStackTrace();

		}

	}

}
