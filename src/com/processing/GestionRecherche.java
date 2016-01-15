package com.processing;

import java.util.ArrayList;

import com.bo.Adresse;
import com.bo.Borders;
import com.bo.Coordonnees;
import com.bo.Critere;
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
	 * Récupère la liste des stations en fonction de la recherche : position
	 * initiale ou adresse géolocalisée
	 * 
	 * @param recherche
	 *            contient soit l'adresse de départ soit la position de départ
	 * @return renvoie une liste de station
	 */
	public ArrayList<Station> recupereStations(Recherche recherche) {

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
		Borders limite = GeoProcessing.getWGS84FrameLimits(positionDepart.getCoordonnee().getLatitude(),
				positionDepart.getCoordonnee().getLongitude(), recherche.getCritere().getRayon());

		if (limite == null)
			return null;

		this.stations = (ArrayList<Station>) stationdao.getStations(limite);

		if (stations == null)
			return null;

		// Recupère les stations incluses dans le périmètre de rayon r

		for (Station st : stations) {
			double distance = GeoProcessing.getDistance(positionDepart.getCoordonnee().getLatitude(),
					positionDepart.getCoordonnee().getLongitude(),
					st.getAdresse().getPosition().getCoordonnee().getLatitude(),
					st.getAdresse().getPosition().getCoordonnee().getLongitude());
			if (distance < recherche.getCritere().getRayon()) {
				st.getAdresse().getPosition().setDistance(distance);
				if (this.resultat == null)
					this.resultat = new ArrayList<Station>();

				this.resultat.add(st);
			}

		}
		return this.resultat;
	}

	/**
	 *  Recupere une liste de station en fonction d'une latitude et d'une longitude et d'un rayon
	 * @param latitude
	 *            Double
	 * @param longitude
	 *            Double
	 * @param rayon
	 *            Int
	 * @return liste de stations
	 */
	public ArrayList<Station> recupereStations(Double latitude, Double longitude, int rayon) {
		Recherche recherche = new Recherche();
		GestionRecherche grecherche = new GestionRecherche();
		Critere critere = new Critere();
		Point position = new Point();
		Coordonnees coordonnee = new Coordonnees();
		coordonnee.setLatitude(43.6244855);
		coordonnee.setLongitude(3.862568);
		position.setCoordonnee(coordonnee);
		critere.setPosition(position);
		critere.setRayon(rayon);
		recherche.setCritere(critere);

		return recupereStations(recherche);

	}

	
	/**
	 * Recupere une liste de station en fonction d'une adresse et d'un rayon
	 * @param rue String
	 * @param codepostal  String
	 * @param ville String
	 * @param rayon Int
	 * @return liste de stations
	 */
	public ArrayList<Station> recupereStations(String rue, String codepostal, String ville, int rayon) {
		Recherche recherche = new Recherche();
		GestionRecherche grecherche = new GestionRecherche();
		Critere critere = new Critere();
		Adresse adresse = new Adresse();
		adresse.setRue(rue);
		adresse.setVille(ville);
		adresse.setCodepostal(codepostal);
		critere.setAdresse(adresse);
		critere.setRayon(rayon);
		recherche.setCritere(critere);

		return recupereStations(recherche);

	}

}
