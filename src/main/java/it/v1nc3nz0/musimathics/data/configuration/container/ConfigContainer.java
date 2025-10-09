package it.v1nc3nz0.musimathics.data.configuration.container;

import it.v1nc3nz0.musimathics.data.configuration.ApplicationConfig;
import it.v1nc3nz0.musimathics.data.configuration.MessagesConfig;
import lombok.Getter;

public class ConfigContainer
{	
	
	@Getter
	private static ApplicationConfig applicationConfig;
	
	@Getter
	private static MessagesConfig messagesConfig;
	
	/**
	 * Initialize Config
	 */
	public static void init()
	{
		ConfigContainer.applicationConfig = new ApplicationConfig();
		ConfigContainer.applicationConfig.loadConfiguration();
		ConfigContainer.applicationConfig.loadData();
		
		ConfigContainer.messagesConfig = new MessagesConfig();
		ConfigContainer.messagesConfig.loadConfiguration();
	}
	
}
