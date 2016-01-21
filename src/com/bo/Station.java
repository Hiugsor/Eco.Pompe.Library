
package com.bo;

import java.util.List;
import java.sql.Time;

public class Station {

	private Integer id;

	private Adresse adresse;

	private String nom;

	private String enseigne;

	private List<Carburant> carburants;

	private List<TypeService> services;

	private Time heureOuverture;

	private Time heureFermeture;

	private List<String> joursFermeture;

	private TypeService typeService;

	private TypeRoute typeRoute;

	public Station(Integer id, Adresse adresse, String nom, List<Carburant> carburants, List<TypeService> services,
			Time heureOuverture, Time heureFermeture, List<String> joursFermeture, TypeRoute typeRoute) {
		this.setAdresse(adresse);
		this.setNom(nom);
		this.setCarburants(carburants);
		this.setServices(services);
		this.setHeureOuverture(heureOuverture);
		this.setHeureFermeture(heureFermeture);
		this.setTypeRoute(typeRoute);
		this.setJoursFermeture(joursFermeture);
		this.setId(id);
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setEnseigne(String enseigne) {
		this.enseigne = enseigne;
	}

	public void setCarburants(List<Carburant> carburants) {
		this.carburants = carburants;
	}

	public void setServices(List<TypeService> services) {
		this.services = services;
	}

	public void setHeureOuverture(Time heureOuverture) {
		this.heureOuverture = heureOuverture;
	}

	public void setHeureFermeture(Time heureFermeture) {
		this.heureFermeture = heureFermeture;
	}

	public void setJoursFermeture(List<String> joursFermeture) {
		this.joursFermeture = joursFermeture;
	}

	
	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}

	public void setTypeRoute(TypeRoute typeRoute) {
		this.typeRoute = typeRoute;
	}

	public Integer getId() {
		return id;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public String getNom() {
		return nom;
	}

	public String getEnseigne() {
		return enseigne;
	}

	public List<Carburant> getCarburants() {
		return carburants;
	}

	public List<TypeService> getServices() {
		return services;
	}

	public Time getHeureOuverture() {
		return heureOuverture;
	}

	public Time getHeureFermeture() {
		return heureFermeture;
	}

	public List<String> getJoursFermeture() {
		return joursFermeture;
	}

	
	/**
	 * @return the typeService
	 */
	public TypeService getTypeService() {
		return typeService;
	}

	/**
	 * @return the typeRoute
	 */
	public TypeRoute getTypeRoute() {
		return typeRoute;
	}

	private Double distance;

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

}
