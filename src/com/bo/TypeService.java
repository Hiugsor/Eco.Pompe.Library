package com.bo;

public class TypeService {

	private String nom;

	public TypeService(String typeService) {
		this.setNom(typeService);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
