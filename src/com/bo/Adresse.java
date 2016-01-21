package com.bo;

public class Adresse {

	
	 private String adresseComplete;
	 
	 
	public String getAdresseComplete() {
		return adresseComplete;
	}

	public void setAdresseComplete(String adresseComplete) {
		this.adresseComplete = adresseComplete;
	}

	private String rue;

	private String codepostal;

	private String ville;

	private Point position;

	public Adresse() {

	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getRue() {
		return this.rue;
	}

	public void setCodepostal(String codepostal) {
		this.codepostal = codepostal;
	}

	public String getCodepostal() {
		return this.codepostal;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getVille() {
		return this.ville;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Point getPosition() {
		return this.position;
	}

}
