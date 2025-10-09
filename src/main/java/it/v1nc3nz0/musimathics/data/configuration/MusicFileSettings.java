package it.v1nc3nz0.musimathics.data.configuration;

import it.v1nc3nz0.musimathics.api.configuration.AbstractConfiguration;

public class MusicFileSettings extends AbstractConfiguration
{
	
	public MusicFileSettings(String name)
	{
		super(ApplicationConfig.getMusicFileSettingsFolder(), name);
	}
	
}
