package com.bo;

public class Critere {

	private Adresse adresse;

	private Carburant carburant;

	private Integer rayon;

	private Point position;

	public Critere() {

	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Adresse getAdresse() {
		return this.adresse;
	}

	public void setCarburant(Carburant carburant) {
		this.carburant = carburant;
	}

	public Carburant getCarburant() {
		return this.carburant;
	}

	public void setRayon(Integer rayon) {
		this.rayon = rayon;
	}

	public Integer getRayon() {
		return this.rayon;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Point getPosition() {
		return this.position;
	}

}
