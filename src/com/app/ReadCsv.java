package com.app;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.csvparser.CsvFileHelper;
import com.csvparser.CsvFileRead;

public class ReadCsv
{

	/**
	 * Obtient la list des ligne contenu dans le CSV
	 *
	 * @param path chemin du fihier csv à lire
	 *           
	 * @return la list des lignes du CSV
	 */
	public static List<String[]> readCSV(String path)
	{

		List<String[]> lines = new ArrayList<String[]>();
		File fileCsv = CsvFileHelper.getResource(path);
		CsvFileRead StationCsv;

		StationCsv = new CsvFileRead(fileCsv);
		lines = StationCsv.getData();
		return lines;
	}
}
