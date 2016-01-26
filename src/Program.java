
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.bo.*;
import com.dao.ConnexionParameters;
import com.dao.StationDao;
import com.dao.VendreDao;
import com.processing.GestionRecherche;
import com.processing.PropertyLoader;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

		System.out.println("Debut Test n°1 @ " + timeStamp);

		// System.out.println("URL: "+ConnexionParameters.getDatabaseUrl());
		/*
		 * Recherche recherche = new Recherche(); GestionRecherche grecherche =
		 * new GestionRecherche(); Critere critere = new Critere(); Point
		 * position = new Point(); Coordonnees coordonnee = new Coordonnees();
		 * coordonnee.setLatitude(43.6244855);
		 * coordonnee.setLongitude(3.862568);
		 * position.setCoordonnee(coordonnee); critere.setPosition(position);
		 * recherche.setCritere(critere); critere.setRayon(30);
		 */

		/*
		 * Recherche recherche = new Recherche(); GestionRecherche grecherche =
		 * new GestionRecherche(); Critere critere = new Critere(); Point
		 * position = new Point(); Coordonnees coordonnee = new Coordonnees();
		 * coordonnee.setLatitude(43.6244855);
		 * coordonnee.setLongitude(3.862568);
		 * position.setCoordonnee(coordonnee); critere.setPosition(position);
		 * recherche.setCritere(critere); critere.setRayon(30);
		 */

		GestionRecherche grecherche = new GestionRecherche();
		ArrayList<Station> stationsRes = null;

		// stationsRes =
		// grecherche.recupereStations(43.6244855,3.862568,"Gazole", 30);
		// = grecherche.recupereStations(48.865562,2.447129,"Gazole", 30);

		// 10 rue DANTON MONTREUIL 63100
		// stationsRes = grecherche.recupereStations("10 rue danton 93100
		// Montreuil","e10", 30);
		stationsRes = grecherche.recupereStations(48.865562, 2.447129, "Gazole", 30);
		// stationsRes =
		// grecherche.recupereStations(48.8656267,2.447129999,"e10", 30);
		// stationsRes = grecherche.recupereStations(48.865627, 2.4472, "e10",
		// 30);

		// stationsRes = grecherche.recupereStations("98 avenue de
		// toulouse","34000" ,"Montpellier","Gazole", 30);
		// stationsRes = grecherche.recupereStations("98 avenue de toulouse
		// 34000 Montpellier","Gazole", 30);
		// stationsRes = grecherche.recupereStations("13 avenue madame curie
		// 93220 gagny","Gazole", 30);
		if (stationsRes != null) {
			System.out.println("Nombre de stations :" + stationsRes.size());

			for (Station st : stationsRes) {
				/*
				 * System.out.println("Type route : " +
				 * st.getTypeRoute().getNom()); System.out.println(
				 * "Jour Fermeture    Station: :" + st.getNom()); for(String j :
				 * st.getJoursFermeture()) System.out.println("fermé le :" + j);
				 * System.out.println("Distance : " + st.getDistance());
				 * 
				 * System.out.println("Nom : " + st.getNom() + "   Cp : " +
				 * st.getAdresse().getCodepostal() + "   Ville : " +
				 * st.getAdresse().getVille()); for (TypeService ts :
				 * st.getServices()) System.out.println("Service : " +
				 * ts.getNom());
				 */
				/*
				 * System.out.println("Nom : " + st.getNom() + "   Cp : " +
				 * st.getAdresse().getCodepostal() + "   Ville : " +
				 * st.getAdresse().getVille()); for (Carburant ts :
				 * st.getCarburants()) System.out.println("Carburants : " +
				 * ts.getNom() + " Prix : " + ts.getPrix());
				 */
			}
		} else
			System.out.println("Erreur");

		/*
		 * System.out.println(""); System.out.println(""); System.out.println(
		 * "Test n°2"); grecherche = new GestionRecherche(); critere = new
		 * Critere(); Adresse adresse = new Adresse(); adresse.setRue(
		 * "98 avenue de toulouse"); adresse.setVille("Montpellier");
		 * adresse.setCodepostal("34000"); critere.setAdresse(adresse);
		 * critere.setRayon(30); recherche.setCritere(critere); stations = null;
		 * stationsRes = null; stationdao = new StationDao(); stations =
		 * (ArrayList<Station>) stationdao.getStations(); stationsRes =
		 * grecherche.recupereStations(recherche, stations); if (stationsRes !=
		 * null) { System.out.println("Nombre de stations :" +
		 * stationsRes.size()); for (Station st : stationsRes)
		 * System.out.println("Nom : " + st.getNom() + "   Cp : " +
		 * st.getAdresse().getCodepostal() + "   Ville : " +
		 * st.getAdresse().getVille()); } else System.out.println("");
		 */
		/*
		 * StationDao stationdao = new StationDao(); List<Carburant> carburants
		 * = stationdao.getCarburants(); for(Carburant carb : carburants)
		 * System.out.println("Nom: " + carb.getNom());
		 * 
		 * List<String> enseignes = stationdao.getEnseignes(); for(String e :
		 * enseignes) System.out.println("Nom: " + e);
		 */
		/*
		 * StationDao stationdao = new StationDao(); List<Carburant> carburants
		 * = stationdao.getCarburants(); for (Carburant carb : carburants)
		 * System.out.println("Nom: " + carb.getNom());
		 */

		List<Stats> liste = VendreDao.recupereStatsNat();
		for (Stats carb : liste)
			System.out.println("Nom: " + carb.getCarburant().getNom() + " moyenne : " + carb.getMoyenne());

		System.out.println("Prix carburant dans un rayon");
		if (stationsRes != null) {
			liste = VendreDao.recupereStats(stationsRes);
			for (Stats carb : liste)
				System.out.println("Nom: " + carb.getCarburant().getNom() + " moyenne : " + carb.getMoyenne());
		}
		timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());

		System.out.println("Fin Test n°1 @ " + timeStamp);

	}

}
