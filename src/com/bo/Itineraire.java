package com.bo;

import java.util.Date;

public class Itineraire {

	private Adresse depart;

	private Adresse arrivee;

	private Date duree;

	private Float distance;

	public Itineraire(Adresse depart, Adresse arrivee, Date duree, Float distance) {

	}

	public void setDepart(Adresse depart) {
		this.depart = depart;
	}

	public Adresse getDepart() {
		return this.depart;
	}

	public void setArrivee(Adresse arrivee) {
		this.arrivee = arrivee;
	}

	public Adresse getArrivee() {
		return this.arrivee;
	}

	public void setDuree(Date duree) {
		this.duree = duree;
	}

	public Date getDuree() {
		return this.duree;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
	}

	public Float getDistance() {
		return this.distance;
	}

}
