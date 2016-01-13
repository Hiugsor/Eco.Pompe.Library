package com.bo;

public class Point {

	private Coordonnees coordonnee;

	private Color couleur;

	public Point() {
		this.coordonnee = new Coordonnees();
	}
	
	public Point(double latitude, double longitude) {
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

}