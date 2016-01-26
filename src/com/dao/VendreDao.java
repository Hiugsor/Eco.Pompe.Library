package com.dao;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.StringOrder;
import com.bo.Carburant;
import com.bo.Station;
import com.bo.Stats;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Statement;

public class VendreDao {
	private VendreDao() {
	}

	/**
	 * Requete d'update des prix carburants
	 *
	 * @param idStation
	 *            l'id de la station *
	 * @param idCarburant
	 *            l'id du carburant
	 * 
	 * @param valeur
	 *            prix du carburant
	 */
	public static void updateVendreBDD(int idStation, int idCarburant, String valeur) throws SQLException {
		Connection connection = ConnexionManager.GetConnection();

		String requete = "Update vendre set prix=(?) where id_Station=(?) and id_Carburant=(?)";
		try {
			PreparedStatement requeteSql = connection.prepareStatement(requete);

			requeteSql.setFloat(1, Float.parseFloat(valeur));
			requeteSql.setInt(2, idStation);
			requeteSql.setInt(3, idCarburant);
			System.out.println("requete " + requeteSql);
			requeteSql.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();

		}
		System.out.println("REQUETE done!");

	}

	/**
	 * Requete d'update des prix carburants
	 *
	 * @param idStation
	 *            l'id de la station *
	 * @param prix
	 *            du carburant
	 * 
	 */
	public static void updateVendrePrix(int idStation, String prix) throws SQLException {
		List<String> listePrix = StringOrder.getDatas(prix, "/");
		for (String carbuItem : listePrix) {
			if (!carbuItem.equals("")) {
				String[] compoCarbu = carbuItem.split(";");
				updateVendreBDD(idStation, Integer.parseInt(compoCarbu[0]), compoCarbu[1]);
			}
		}

	}

	public static void procedureStock() // APPEL de la procédure stockée de la
										// BDD
	{
		String requete = "call Mise_a_jour_nat();";
		Connection connection = ConnexionManager.GetConnection();
		try {
			CallableStatement callStm = (CallableStatement) connection.prepareCall(requete);
			callStm.executeQuery();
			System.out.println("STAT OK!!");

		} catch (SQLException e) {
			e.getMessage();
		}

	}

	public static List<Stats> recupereStatsNat() {
		String requete = "SELECT c.nom, sn.Moy_Nation FROM ecopompetest.stat_nationnale sn INNER JOIN ecopompetest.carburants c ON c.id_Carburant = sn.id_carburant WHERE sn.Date_M_a_J = ( SELECT max(Date_M_a_J) FROM ecopompetest.stat_nationnale);";
		List<Stats> listMoyenne = new ArrayList<Stats>();
		Stats itemStat = null;
		ResultSet resultSet = null;
		ConnexionManager.GetInstance().open();
		try {
			Statement stmt = (Statement) ConnexionManager.GetConnection().createStatement();
			resultSet = stmt.executeQuery(requete);
			while (resultSet.next()) {
				itemStat = new Stats();
				Carburant carburant = new Carburant();
				carburant.setNom(resultSet.getString("nom"));
				itemStat.setCarburant(carburant);
				itemStat.setMoyenne(resultSet.getFloat("Moy_Nation"));
				listMoyenne.add(itemStat);
			}
		} catch (Exception ex) {
			ex.getMessage();
		} finally {
			ConnexionManager.GetInstance().close();
		}
		return listMoyenne;
	}

	public static List<Stats> recupereStats(List<Station> listStations) {

		List<Stats> listMoyenne = new ArrayList<Stats>();
		Stats itemStat = null;
		int nbGazole = 0;
		int nbSP95 = 0;
		int nbE85 = 0;
		int nbGPLc = 0;
		int nbE10 = 0;
		int nbSP98 = 0;

		Float sumGazole = 0f;
		Float sumSP95 = 0f;
		Float sumE85 = 0f;
		Float sumGPLc = 0f;
		Float sumE10 = 0f;
		Float sumSP98 = 0f;

		Float avgGazole = 0f;
		Float avgSP95 = 0f;
		Float avgE85 = 0f;
		Float avgGPLc = 0f;
		Float avgE10 = 0f;
		Float avgSP98 = 0f;

		if (listStations != null) {
			for (Station st : listStations) {
				for (Carburant carb : st.getCarburants())
					switch (carb.getNom()) {

					case "Gazole":
						nbGazole++;
						sumGazole += carb.getPrix();
						break;
					case "SP95":
						nbSP95++;
						sumSP95 += carb.getPrix();
						break;
					case "E85":
						nbE85++;
						sumE85 += carb.getPrix();
						break;
					case "GPLc":
						nbGPLc++;
						sumGPLc += carb.getPrix();
						break;
					case "E10":
						nbE10++;
						sumE10 += carb.getPrix();
						break;
					case "SP98":
						nbSP98++;
						sumSP98 += carb.getPrix();
						break;
					}
			}

		}

		Carburant carburant = null;
		if (nbGazole > 0) {
			carburant = new Carburant();
			carburant.setNom("Gazole");
			avgGazole = (sumGazole / nbGazole);
			itemStat = new Stats();
			itemStat.setCarburant(carburant);
			itemStat.setMoyenne(avgGazole);
			listMoyenne.add(itemStat);
		}

		if (nbSP95 > 0) {
			carburant = new Carburant();
			carburant.setNom("SP95");
			avgSP95 = (sumSP95 / nbSP95);
			itemStat = new Stats();
			itemStat.setCarburant(carburant);
			itemStat.setMoyenne(avgSP95);
			listMoyenne.add(itemStat);
		}

		if (nbE85 > 0) {
			carburant = new Carburant();
			carburant.setNom("E85");
			avgE85 = (sumE85 / nbE85);
			itemStat = new Stats();
			itemStat.setCarburant(carburant);
			itemStat.setMoyenne(avgE85);
			listMoyenne.add(itemStat);
		}

		if (nbGPLc > 0) {
			carburant = new Carburant();
			carburant.setNom("GPLc");
			avgGPLc = (sumGPLc / nbGPLc);
			itemStat = new Stats();
			itemStat.setCarburant(carburant);
			itemStat.setMoyenne(avgGPLc);
			listMoyenne.add(itemStat);
		}

		if (nbE10 > 0) {
			carburant = new Carburant();
			carburant.setNom("E10");
			avgE10 = (sumE10 / nbE10);
			itemStat = new Stats();
			itemStat.setCarburant(carburant);
			itemStat.setMoyenne(avgE10);
			listMoyenne.add(itemStat);
		}

		if (nbSP98 > 0) {
			carburant = new Carburant();
			carburant.setNom("SP98");
			avgSP98 = (sumSP98 / nbSP98);
			itemStat = new Stats();
			itemStat.setCarburant(carburant);
			itemStat.setMoyenne(avgSP98);
			listMoyenne.add(itemStat);
		}
		return listMoyenne;
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
