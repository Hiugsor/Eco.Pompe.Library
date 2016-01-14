package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

import java.util.List;

import com.bo.Adresse;
import com.bo.Borders;
import com.bo.Carburant;
import com.bo.Coordonnees;
import com.bo.Point;
import com.bo.Station;
import com.bo.TypeRoute;
import com.bo.TypeService;
import com.mysql.jdbc.Statement;
import com.processing.GeoProcessing;

public class StationDao {

	
	/**
	 * Recupère la liste de stations
	 * @param limite Border
	 * @return retourne une liste de stations
	 */
	public List<Station> getStations(Borders limite) {

		ArrayList<Station> stations = new ArrayList<Station>();
		Station station = null;
		Adresse adresse = null;
		ArrayList<Carburant> carburants = new ArrayList<Carburant>();
		ArrayList<TypeService> services = null;
		Time heureOuverture = null;
		Time heureFermeture = null;
		ArrayList<String> joursFermeture = null;
		TypeRoute emplacement = null;
		Carburant carbu = null;

		TypeService service = null;

		double latMax = limite.getBorderNO().getCoordonnee().getLatitude();
		double longMax = limite.getBorderSE().getCoordonnee().getLongitude();
		double latMin = limite.getBorderSE().getCoordonnee().getLatitude();
		double longMin = limite.getBorderNO().getCoordonnee().getLongitude();

		String requeteStation = "SELECT * FROM  ecopompe.stations WHERE (latitude BETWEEN " + latMin + " AND " + latMax
				+ ") AND (longitude BETWEEN " + longMin + " AND " + longMax + ")";
		String requeteCarbu = "SELECT nom, prix FROM  ecopompe.carburants INNER JOIN vendre ON vendre.id_carburant = carburants.id_carburant WHERE vendre.id_station = ?";
		String requeteServices = "SELECT types_services FROM  ecopompe.services INNER JOIN proposer ON proposer.id_service = services.id_service WHERE proposer.id_station = ?";

		

		ConnexionManager.GetInstance().open();
		ResultSet resultSet = null;
		ResultSet resultSetCarbu = null;
		ResultSet resultSetServices = null;
		int i = 0;
		try {

			Statement stmt = (Statement) ConnexionManager.GetInstance().GetConnection().createStatement();
			PreparedStatement stmtCarbu = (PreparedStatement) ConnexionManager.GetInstance().GetConnection()
					.prepareStatement(requeteCarbu);
			PreparedStatement stmtServices = (PreparedStatement) ConnexionManager.GetInstance().GetConnection()
					.prepareStatement(requeteServices);

			resultSet = stmt.executeQuery(requeteStation);
		PreparedStatement pstmt = null;

			while (resultSet.next()) {

				// ADRESSE
				adresse = new Adresse();
				adresse.setRue(resultSet.getString("Adresse"));
				adresse.setVille(resultSet.getString("Ville"));
				adresse.setCodepostal(resultSet.getString("CP"));
				Coordonnees coordonnee = new Coordonnees();
				coordonnee.setLatitude(resultSet.getDouble("latitude"));
				coordonnee.setLongitude(resultSet.getDouble("longitude"));
				Point position = new Point();
				position.setCoordonnee(coordonnee);
				adresse.setPosition(position);

				
				// CARBURANTS
				carburants = new ArrayList<Carburant>();

				stmtCarbu.setString(1, resultSet.getString("id_station"));
				resultSetCarbu = stmtCarbu.executeQuery();
				if (!resultSetCarbu.wasNull()) {

					while (resultSetCarbu.next()) {
						carbu = new Carburant();
						carbu.setNom(resultSetCarbu.getString("nom"));
						carbu.setPrix(resultSetCarbu.getFloat("prix"));
						carburants.add(carbu);
					}
				}

				// SERVICES
				services = new ArrayList<TypeService>();
				stmtServices.setString(1, resultSet.getString("id_station"));

				resultSetServices = stmtServices.executeQuery();
				if (!resultSetServices.wasNull()) {

					while (resultSetServices.next()) {
						service = new TypeService(resultSetServices.getString("types_services"));
						services.add(service);
					}
				}

				// HORAIRES
				heureOuverture = resultSet.getTime("Horaire_ouverture");
				heureFermeture = resultSet.getTime("Horaire_fermeture");

				// STATION
				station = new Station(resultSet.getInt("id_Station"), adresse, resultSet.getString("Nom"), carburants,
						services, heureOuverture, heureFermeture, joursFermeture, emplacement);

				stations.add(station);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionManager.GetInstance().close();
		}
		return stations;

	}

	
	/**
	 * Renvoie la liste des carburants Type: Carburant
	 * @return Liste des carburants
	 */
	public List<Carburant> getCarburants() {
		ArrayList<Carburant> carburants = null;
		Carburant carbu = null;
		String requete = "SELECT DISTINCT nom FROM  ecopompe.carburants";
		ConnexionManager.GetInstance().open();
		ResultSet resultSet = null;
		try {

			Statement stmt = (Statement) ConnexionManager.GetInstance().GetConnection().createStatement();
			resultSet = stmt.executeQuery(requete);
			if (!resultSet.wasNull()) {
				carburants = new ArrayList<Carburant>();
				while (resultSet.next()) {
					carbu = new Carburant();
					carbu.setNom(resultSet.getString("nom"));
					// carbu.setPrix(resultSet.getFloat("prix"));
					carburants.add(carbu);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionManager.GetInstance().close();
		}
		return carburants;
	}

	
	
	/**
	 * Renvoie une liste d'enseigne Type: string
	 * @return Liste de noms des stations
	 */
	public List<String> getEnseignes() {
		ArrayList<String> enseignes = null;
		String requete = "SELECT DISTINCT nom FROM  ecopompe.stations";
		ConnexionManager.GetInstance().open();
		ResultSet resultSet = null;
		try {

			Statement stmt = (Statement) ConnexionManager.GetInstance().GetConnection().createStatement();
			resultSet = stmt.executeQuery(requete);
			if (!resultSet.wasNull()) {
				enseignes = new ArrayList<String>();
				while (resultSet.next()) {
					enseignes.add(resultSet.getString("Nom"));

				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnexionManager.GetInstance().close();
		}

		return enseignes;
	}

}
