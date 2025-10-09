package it.v1nc3nz0.musimathics.api.configuration;

import java.io.File;

public interface Configuration
{

	/**
	 * Export default config
	 * into jar
	 */
	void copyDefaultConfig();
	
	/**
	 * Obtain a config folder
	 * 
	 * @return config folder
	 */
	File getConfigFolder();
	
	/**
	 * Obtain a config name
	 * 
	 * @return config name
	 */
	String getConfigName();
	
	/**
	 * Load configuration. This check
	 * if folder and files exists.
	 * If not exists copy it.
	 */
	void loadConfiguration();
	
}
