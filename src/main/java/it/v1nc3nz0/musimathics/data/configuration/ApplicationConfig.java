package it.v1nc3nz0.musimathics.data.configuration;

import java.io.File;

import it.v1nc3nz0.musimathics.api.configuration.AbstractConfiguration;
import it.v1nc3nz0.musimathics.data.configuration.enums.ApplicationData;
import lombok.Getter;

public class ApplicationConfig extends AbstractConfiguration
{

	@Getter
	private static File musicFileSettingsFolder;
	
	public ApplicationConfig()
	{
		super(null, "applications.yml");
	}
	
	public void loadData()
	{
		ApplicationConfig.musicFileSettingsFolder = new File(ApplicationData.MUSIC_FILES_SETTINGS_LOCATION.getString());
	}
	
}
