package com.dao;
import java.util.List;

import com.bo.*;


public interface IStationDao {
		public List<Station> getStations();
		
		public List<Carburant> getCarburants();
		
		public List<String> getEnseignes();
		
 }
