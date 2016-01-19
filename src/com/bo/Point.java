package com.bo;

public class Point {

	private Coordonnees coordonnee;
	private Color couleur;
	private Double distance;
	
	public Point() {
		this.coordonnee = new Coordonnees();
	}
	
	public Point(Double latitude, Double longitude) {
		this.coordonnee = new Coordonnees();
		this.coordonnee.setLatitude(latitude);
		this.coordonnee.setLongitude(longitude);
	}
	

	public void setCoordonnee(Coordonnees coordonnee) {
		this.coordonnee = coordonnee;
	}

	public Coordonnees getCoordonnee() {
		return this.coordonnee;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public Color getCouleur() {
		return this.couleur;
	}
	

	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	

}
