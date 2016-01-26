package com.app;

import java.util.ArrayList;
import java.util.List;

public class StringOrder
{

	/**
	 * transformer une chaine en liste de nom
	 *
	 * @param colonne la colone du fichier XML
	 * 
	 * @param charSplit le caract�re de s�paration des strings
	 *           
	 * @return la list des string s�par�s
	 */
	public static List<String> getDatas(String colonne, String charSplit)
	{
		ArrayList<String> mots = new ArrayList<String>();

		String[] motsCol = colonne.split(charSplit);

		for (String string : motsCol)
		{
			mots.add(string);
		}
		return mots;
	}
}
