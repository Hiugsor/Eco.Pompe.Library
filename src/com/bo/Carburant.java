package com.bo;

public class Carburant {

	private String nom;

	private TypeCarburant type;

	private Float prix;

	public Boolean getEstDisponible() {
		return estDisponible;
	}



	private Boolean estDisponible;

	private TypeCarburant typeCarburant;

	public Carburant() {

	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return this.nom;
	}

	public void setType(TypeCarburant type) {
		this.type = type;
	}

	public TypeCarburant getType() {
		return this.type;
	}

	public void setPrix(Float prix) {
		this.prix = prix;
	}

	public Float getPrix() {
		return this.prix;
	}
	
	public void setEstDisponible(Boolean estDisponible) {
		this.estDisponible = estDisponible;
	}

	public TypeCarburant getTypeCarburant() {
		return typeCarburant;
	}

	public void setTypeCarburant(TypeCarburant typeCarburant) {
		this.typeCarburant = typeCarburant;
	}

}
