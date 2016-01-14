package com.processing;

import java.util.ArrayList;

import com.bo.Borders;
import com.bo.Point;
import com.bo.Recherche;
import com.bo.Station;
import com.dao.StationDao;

public class GestionRecherche {

	private ArrayList<Station> resultat;
	private StationDao stationdao;
	private Recherche recherche;
	private ArrayList<Station> stations;

	public GestionRecherche() {
		this.stationdao = new StationDao();
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
	 * R�cup�re la liste des stations en fonction de la recherche : position
	 * initiale ou adresse g�olocalis�e
	 * 
	 * @param recherche
	 *            contient soit l'adresse de d�part soit la position de d�part
	 * @return renvoie une liste de station
	 */
	public ArrayList<Station> recupereStations(Recherche recherche) {
		

		if (recherche == null)
			return null;

		// 1- GEOCODE ADRESSE ou r�cup�re une position
		Point positionDepart = null;

		if (recherche.getCritere().getPosition() != null)
			positionDepart = recherche.getCritere().getPosition();
		else
			positionDepart = GeoProcessing.geolocalise(recherche);

		if (positionDepart == null)
			return null;

		// 2 - CALCUL Limite(Perimetre)
		Borders limite = GeoProcessing.getWGS84FrameLimits(positionDepart.getCoordonnee().getLatitude(),
				positionDepart.getCoordonnee().getLongitude(), recherche.getCritere().getRayon());

		if (limite == null)
			return null;

		this.stations = (ArrayList<Station>) stationdao.getStations(limite);

		if (stations == null)
			return null;

		// Recup�re les stations incluses dans le p�rim�tre de rayon r

		for (Station st : stations) 
		{
			if (GeoProcessing.getDistance(positionDepart.getCoordonnee().getLatitude(),
					positionDepart.getCoordonnee().getLongitude(),
					st.getAdresse().getPosition().getCoordonnee().getLatitude(),
					st.getAdresse().getPosition().getCoordonnee().getLongitude()) < recherche.getCritere().getRayon()) 
			{
				if (this.resultat == null)
					this.resultat = new ArrayList<Station>();

				this.resultat.add(st);
			}


		}
		return this.resultat;
	}
}
