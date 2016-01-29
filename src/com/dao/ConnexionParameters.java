package com.dao;


import com.processing.PropertyLoader;

public final class ConnexionParameters 
{	
	private static final String _databaseURL = PropertyLoader.loadPropertie("databaseURL")+PropertyLoader.loadPropertie("baseNom"); 
	private static final String _password =PropertyLoader.loadPropertie("password"); 
	private static final String _userName = PropertyLoader.loadPropertie("userName");
	private static final String _driverUrl = "com.mysql.jdbc.Driver";
	
	public static String getDatabaseUrl() 
	{
		return _databaseURL;
	}

	public static String getPassword() 
	{
		return _password;
	}

	public static String getUserName() 
	{
		return _userName;
	}

	public static String getDriverUrl() 
	{
		return _driverUrl;
	}
	
	private ConnexionParameters()
	{}
	
	
}