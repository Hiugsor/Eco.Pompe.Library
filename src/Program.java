import java.util.ArrayList;
import java.util.List;

import com.bo.*;
import com.dao.StationDao;
import com.processing.GestionRecherche;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Test n�1");

		Recherche recherche = new Recherche();

		GestionRecherche grecherche = new GestionRecherche();
		Critere critere = new Critere();
		Point position = new Point();
		Coordonnees coordonnee = new Coordonnees();
		coordonnee.setLatitude(43.6244855);
		coordonnee.setLongitude(3.862568);
		position.setCoordonnee(coordonnee);
		critere.setPosition(position);
		recherche.setCritere(critere);
		critere.setRayon(30);

		ArrayList<Station> stations = null;
		ArrayList<Station> stationsRes = null;
		StationDao stationdao = new StationDao();
		stations = (ArrayList<Station>) stationdao.getStations();
		stationsRes = grecherche.recupereStations(recherche, stations);
		if (stationsRes != null) {
			System.out.println("Nombre de stations :" + stationsRes.size());
			for (Station st : stationsRes)
			{
				System.out.println("Nom : " + st.getNom() + "   Cp : " + st.getAdresse().getCodepostal() + "   Ville : "
						+ st.getAdresse().getVille());
				for(TypeService ts : st.getServices())
					System.out.println("Service : " + ts.getNom());
				
				for(Carburant ts : st.getCarburants())
					System.out.println("Carburants : " + ts.getNom());
			}
		} else
			System.out.println("Erreur");

		
		
		/*
		System.out.println("");
		System.out.println("");
		System.out.println("Test n�2");
		grecherche = new GestionRecherche();
		critere = new Critere();
		Adresse adresse = new Adresse();
		adresse.setRue("98 avenue de toulouse");
		adresse.setVille("Montpellier");
		adresse.setCodepostal("34000");
		critere.setAdresse(adresse);
		critere.setRayon(30);
		recherche.setCritere(critere);
		stations = null;
		stationsRes = null;
		stationdao = new StationDao();
		stations = (ArrayList<Station>) stationdao.getStations();
		stationsRes = grecherche.recupereStations(recherche, stations);
		if (stationsRes != null) {
			System.out.println("Nombre de stations :" + stationsRes.size());
			for (Station st : stationsRes)
				System.out.println("Nom : " + st.getNom() + "   Cp : " + st.getAdresse().getCodepostal() + "   Ville : "
						+ st.getAdresse().getVille());
		} else
			System.out.println("");
			*/
		/*
		StationDao stationdao = new StationDao();
		List<Carburant> carburants = stationdao.getCarburants();
		for(Carburant carb : carburants)
			System.out.println("Nom: " + carb.getNom());
		
		List<String> enseignes = stationdao.getEnseignes();
		for(String e : enseignes)
			System.out.println("Nom: " + e);
		*/
		
		
	}

}