package com.processing;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;

import com.bo.Coordonnees;
import com.bo.Borders;
import com.bo.Point;
import com.bo.Recherche;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;


public class GeoProcessing {

	//Attributs
	//Definition Maths
	public static final double PI = Math.PI;	
	//Definition de l'ellipsoide WGS84
	public static final double EarthRadius = 6378.137; //en km
	
	
	
	/**
	 * Calcule la distance entre 2 coordonnées
	 * 
	 * @param lat1 - latitude de la première position  Type : Double
	 * @param lng1 - longitude de la première position Type : Double
	 * @param lat2 - latitude de la seconde position  Type : Double
	 * @param lng2 - longitude de la seconde position Type : Double
	 * @return renvoie une distance  Type : Double
	 */
	public static double distance(Double lat1, Double lng1, Double lat2, Double lng2)
	{
		//convertion degres decimaux en radian		
		lat1 = lat1 * PI / 180;
		lng1 = lng1 * PI / 180;
		lat2 = lat2 * PI / 180;
		lng2 = lng2 * PI / 180;		
		return EarthRadius * Math.acos(Math.cos(lat1) * Math.cos(lat2) * Math.cos(lng2-lng1) + Math.sin(lat1) * Math.sin(lat2));		
	}
	 
	
		
	/**
	 *  Calcule les coordonnées d'un point en fonction d'une distance et d'un azimut
	 * @param latitude   Type : Double
	 * @param longitude Type : Double
	 * @param distance  Type : Float
	 * @param azimuth Type : Float
	 * @return renvoie une position 
	 */
	public static Point Polar(Double latitude, Double longitude, float distance, float azimuth)
	{
		double distanceAngulaire = distance/EarthRadius;
		latitude = latitude * PI /180;
		longitude = longitude * PI / 180;
		azimuth = (float) (azimuth * PI / 180);
		double newLat = Math.asin(Math.sin(latitude)* Math.cos(distanceAngulaire) + Math.cos(latitude)*Math.sin(distanceAngulaire)*Math.cos(azimuth));
		System.out.println("New Lat (Rad)" + newLat);
		double newLong = longitude + Math.atan2(Math.sin(azimuth) * Math.sin(distanceAngulaire) * Math.cos(latitude), Math.cos(distanceAngulaire) - Math.sin(latitude) * Math.sin(newLat));
		//Conversion rad en deg
		newLat = newLat * 180 / PI;
		newLong = newLong * 180 / PI;		
		Point pt = new Point( newLat, newLong);
		return pt;		
	}
	
	
	
	/**
	 * Calcule une limite autour du cercle de recherche afin de limiter la recherche en BDD
	 * @param latitude  Type : Double
	 * @param longitude  Type : Double
	 * @param distance(valeur du rayon)  Type : Double
	 * @return renvoie une limite (un carré) définie par ses sommets Nord-Ouest et Sud-Est
	 */
	public static Borders calculLimiteZone(double latitude, double longitude, double distance)
	{
		double angularDistance = distance/EarthRadius;
		
		//Azimuth des points encadrant le cercle de recherche
		float azmNO = 315;
		float azmSE = 135;
				
		//Conversion Degré vers Radian		
		latitude = latitude * PI /180;
		longitude = longitude * PI / 180;
		azmNO = (float) (azmNO * PI / 180);	
		azmSE = (float) (azmSE * PI / 180);	
		
		//Calcul de la Latitude du Point du cadre Nord Ouest
		double latNO = Math.asin(Math.sin(latitude)* Math.cos(angularDistance) + Math.cos(latitude)*Math.sin(angularDistance)*Math.cos(azmNO));
		//Calcul de la Longitude du Point du cadre Nord Ouest
		double longNO = longitude + Math.atan2(Math.sin(azmNO) * Math.sin(angularDistance) * Math.cos(latitude), Math.cos(angularDistance) - Math.sin(latitude) * Math.sin(latNO));
		//Conversion rad en deg
		latNO = latNO * 180 / PI;
		longNO = longNO * 180 / PI;		
		Point ptNO = new Point( latNO, longNO);
		
		
		//Calcul de la Latitude du Point du cadre Sud Est
		double latSE = Math.asin(Math.sin(latitude)* Math.cos(angularDistance) + Math.cos(latitude)*Math.sin(angularDistance)*Math.cos(azmSE));
		//Calcul de la Longitude du Point du cadre Sud Est
		double longSE = longitude + Math.atan2(Math.sin(azmSE) * Math.sin(angularDistance) * Math.cos(latitude), Math.cos(angularDistance) - Math.sin(latitude) * Math.sin(latSE));
		//Conversion rad en deg
		latSE = latSE * 180 / PI;
		longSE = longSE * 180 / PI;		
		Point ptSE = new Point( latSE, longSE);
		
		return new Borders(ptNO, ptSE);		
	}	

/**
 * Géolocalise une adresse incluse dans une recherche
 * @param recherche critere de recherche : adresse ou position
 * @return renvoie une position
 */
	public static Point geolocalise(Recherche recherche) {

		LatLng long_lat;

		Point position = null;
		Coordonnees coordonnee = null;
		GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyBI_p53rZMpZby3ZK93f0HgnK7UBE73zEM");

		SocketAddress addr = new InetSocketAddress("10.127.254.1", 80);
		Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);

		context.setProxy(proxy);

		// System.out.println("CA COINCE ?");
		GeocodingResult[] results;

		try {

			// System.out.println("AVANT le results");
			results = GeocodingApi.geocode(context,
					recherche.getCritere().getAdresse().getRue() + ", "
							+ recherche.getCritere().getAdresse().getCodepostal() + ", "
							+ recherche.getCritere().getAdresse().getVille())
					.await();
			// System.out.println("APRES le results");

			if (results.length != 0) {
				//System.out.println(results[0].geometry.location);
				long_lat = results[0].geometry.location;
				position = new Point();
				coordonnee = new Coordonnees();
				coordonnee.setLatitude(long_lat.lat);
				coordonnee.setLongitude(long_lat.lng);
				position.setCoordonnee(coordonnee);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

		return position;
	}



}
