
package com.bo;



public class Stats {

	private Float moyenne;

	public Stats() {
		
	}

	private Carburant carburant;

	public Carburant getCarburant() {
		return this.carburant;
	}

	public void setCarburant(Carburant carburant) {
		this.carburant = carburant;
	}

	public void setMoyenne(Float moyenne) {
		this.moyenne = moyenne;
	}

	public Float getMoyenne() {
		return this.moyenne;
	}

}
