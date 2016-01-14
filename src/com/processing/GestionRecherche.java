package com.processing;

import java.util.ArrayList;

import com.bo.Borders;
import com.bo.Point;
import com.bo.Recherche;
import com.bo.Station;
import com.dao.IStationDao;
import com.dao.StationDao;

public class GestionRecherche {

	private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json";
	private ArrayList<Station> resultat;
	private IStationDao stationdao;
	private Recherche recherche;
	private ArrayList<Station> stations;

	public GestionRecherche() {
		this.stationdao = new StationDao();
		// this.stations = (ArrayList<Station>) stationdao.getStations();
	}

	public Recherche getRecherche() {
		return recherche;
	}

	public void setRecherche(Recherche recherche) {
		this.recherche = recherche;
	}

	public void setResultat(ArrayList<Station> resultat) {
		this.resultat = resultat;
	}

	public ArrayList<Station> getResultat() {

		return this.resultat;
	}

	/**
	 * Récupère la liste des stations en fonction de la recherche : position
	 * initiale ou adresse géolocalisée
	 * 
	 * @param recherche
	 *            contient soit l'adresse de départ soit la position de départ
	 * @param stations
	 *            liste des stations de la table Station
	 * @return renvoie une liste de station
	 */
	public ArrayList<Station> recupereStations(Recherche recherche, ArrayList<Station> stations) {
		this.stations = (ArrayList<Station>) stationdao.getStations();
		Double distance;
		ArrayList<Station> stationsTemp = null;

		if (recherche == null)
			return null;

		// 1- GEOCODE ADRESSE ou récupère une position
		Point positionDepart = null;

		if (recherche.getCritere().getPosition() != null)
			positionDepart = recherche.getCritere().getPosition();
		else
			positionDepart = GeoProcessing.geolocalise(recherche);

		if (positionDepart == null)
			return null;

		// 2 - CALCUL Limite(Perimetre)
		Borders limite = GeoProcessing.calculLimiteZone(positionDepart.getCoordonnee().getLatitude(),
				positionDepart.getCoordonnee().getLongitude(), recherche.getCritere().getRayon());

		// Latitude et longitude MIN/MAX
		double latLimiteMax = limite.getBorderNO().getCoordonnee().getLatitude();
		double latLimiteMin = limite.getBorderSE().getCoordonnee().getLatitude();
		double longLimiteMax = limite.getBorderSE().getCoordonnee().getLongitude();
		double longLimiteMin = limite.getBorderNO().getCoordonnee().getLongitude();

		if (limite == null)
			return null;

		if (stations == null)
			return null;
		else
			stationsTemp = new ArrayList<Station>();

		// Récupère les stations incluses dans la zone limite
		for (Station st : stations) {

			double latStation = st.getAdresse().getPosition().getCoordonnee().getLatitude();
			double longStation = st.getAdresse().getPosition().getCoordonnee().getLongitude();

			if ((latStation <= latLimiteMax && latStation >= latLimiteMin)
					&& (longStation <= longLimiteMax && longStation >= longLimiteMin)) {
				stationsTemp.add(st);
			}

		}

		if (stationsTemp == null)
			return null;

		// Recupère les stations incluses dans le périmètre de rayon r
		for (Station st : stationsTemp) {
			distance = GeoProcessing.distance(positionDepart.getCoordonnee().getLatitude(),
					positionDepart.getCoordonnee().getLongitude(),
					st.getAdresse().getPosition().getCoordonnee().getLatitude(),
					st.getAdresse().getPosition().getCoordonnee().getLongitude());

			if (distance <= recherche.getCritere().getRayon())
				if (this.resultat == null)
					this.resultat = new ArrayList<Station>();

			this.resultat.add(st);

		}
		return this.resultat;
	}

	public ArrayList<Station> recupereStations(Recherche recherche) {
		Double distance;
		ArrayList<Station> stationsTemp = null;

		if (recherche == null)
			return null;

		// 1- GEOCODE ADRESSE ou récupère une position
		Point positionDepart = null;

		if (recherche.getCritere().getPosition() != null)
			positionDepart = recherche.getCritere().getPosition();
		else
			positionDepart = GeoProcessing.geolocalise(recherche);

		if (positionDepart == null)
			return null;

		// 2 - CALCUL Limite(Perimetre)
		Borders limite = GeoProcessing.calculLimiteZone(positionDepart.getCoordonnee().getLatitude(),
				positionDepart.getCoordonnee().getLongitude(), recherche.getCritere().getRayon());

		if (limite == null)
			return null;
		//
		this.stations = (ArrayList<Station>) stationdao.getStations(limite);

		if (stations == null)
			return null;

		// Recupère les stations incluses dans le périmètre de rayon r
		for (Station st : stations) {
			distance = GeoProcessing.distance(positionDepart.getCoordonnee().getLatitude(),
					positionDepart.getCoordonnee().getLongitude(),
					st.getAdresse().getPosition().getCoordonnee().getLatitude(),
					st.getAdresse().getPosition().getCoordonnee().getLongitude());

			if (distance <= recherche.getCritere().getRayon())
				if (this.resultat == null)
					this.resultat = new ArrayList<Station>();

			this.resultat.add(st);

		}
		return this.resultat;
	}

};
