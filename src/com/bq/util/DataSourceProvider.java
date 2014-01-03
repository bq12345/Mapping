package com.bq.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class DataSourceProvider {
	private static ComboPooledDataSource cpds = getDS();
	private static ComboPooledDataSource getDS(){
		InputStream is = DataSourceProvider.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties prop = new Properties();
		ComboPooledDataSource cpds = null;
		try {
			prop.load(is);
			cpds = new ComboPooledDataSource(); 
			cpds.setDriverClass(prop.getProperty("jdbc.driver").trim());
		    cpds.setJdbcUrl(prop.getProperty("jdbc.url").trim()); 
		    cpds.setUser(prop.getProperty("jdbc.username").trim()); 
		    cpds.setPassword(prop.getProperty("jdbc.password").trim()); 
		    cpds.setMaxStatements(Integer.parseInt(((String)prop.get("maxStatement")).trim() )); 
		    cpds.setMaxPoolSize(Integer.parseInt(((String)prop.get("maxPoolSize")).trim()));
		    cpds.setAutoCommitOnClose(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cpds;

	}
	
	public static Connection getConnection() throws SQLException {
		Connection conn= cpds.getConnection();
		conn.setAutoCommit(false);
		return conn;
	}

}
