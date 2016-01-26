package com.dao;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.app.StringOrder;
import com.mysql.jdbc.CallableStatement;

public class VendreDao
{
	private VendreDao()
	{
	}

	/**
	 * Requete d'update des prix carburants
	 *
	 * @param idStation l'id de la station
	 *           *           
	 * @param idCarburant l'id du carburant
	 *           
	 * @param valeur prix du carburant
	 */
	public static void updateVendreBDD(int idStation, int idCarburant, String valeur) throws SQLException
	{
		Connection connection = ConnexionManager.GetConnection();

		String requete = "Update vendre set prix=(?) where id_Station=(?) and id_Carburant=(?)";
		try
		{
			PreparedStatement requeteSql = connection.prepareStatement(requete);

			requeteSql.setFloat(1, Float.parseFloat(valeur));
			requeteSql.setInt(2, idStation);
			requeteSql.setInt(3, idCarburant);
			System.out.println("requete " + requeteSql);
			requeteSql.executeUpdate();
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();

		}
		System.out.println("REQUETE done!");

	}

	/**
	 * Requete d'update des prix carburants
	 *
	 * @param idStation l'id de la station
	 *           *           
	 * @param prix  du carburant
	 *           
	 */
	public static void updateVendrePrix(int idStation, String prix) throws SQLException 
	{
		List<String> listePrix = StringOrder.getDatas(prix, "/");
		for (String carbuItem : listePrix)
		{
			if (!carbuItem.equals(""))
			{
				String[] compoCarbu = carbuItem.split(";");
				updateVendreBDD(idStation, Integer.parseInt(compoCarbu[0]), compoCarbu[1]);
			}
		}

	}

	public static void procedureStock() // APPEL de la procédure stockée de la BDD
	{
		String requete = "call Mise_a_jour_nat();";
		Connection connection = ConnexionManager.GetConnection();
		try
		{
			CallableStatement callStm = (CallableStatement) connection.prepareCall(requete);
			callStm.executeQuery();
			System.out.println("STAT OK!!");

		}
		catch (SQLException e)
		{
			e.getMessage();
		}

	}

	// public static void updateVendreRupture(int idStation, String rupture)
	// throws SQLException
	// {
	// List<String> listeRupture = StringOrder.getDatas(rupture, "/");
	// for (String ruptureItem : listeRupture)
	// {
	// if (!ruptureItem.equals(""))
	// {
	// String[] compoRupture = ruptureItem.split(";");
	// System.out.println("STATION " + idStation + " IDCARBU " +
	// Integer.parseInt(compoRupture[0])
	// + " RUPTURE " + compoRupture[1]);
	// String requete = "Update vendre set Rupture=(?) where id_Station=(?) and
	// id_Carburant=(?)";
	// updateVendreBDD(idStation, Integer.parseInt(compoRupture[0]), "1");
	// }
	// }
	// ConnexionManager.GetInstance();
	// ConnexionManager.GetConnection().rollback();
	//
	// }
}
