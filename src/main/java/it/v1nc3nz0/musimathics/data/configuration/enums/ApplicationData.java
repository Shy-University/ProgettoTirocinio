package it.v1nc3nz0.musimathics.data.configuration.enums;

import it.v1nc3nz0.musimathics.data.configuration.container.ConfigContainer;

public enum ApplicationData
{

	APP_NAME("app-name"),
	MUSIC_FILES_LOCATION("music-files-location"),
	MUSIC_FILES_SETTINGS_LOCATION("music-files-settings-location");
	
	private String path;
	
	ApplicationData(String path)
	{
		this.path = path;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public String getString()
	{
		return ConfigContainer.getApplicationConfig().getString(getPath());
	}
	
	public String toString()
	{
		return getPath();
	}
	
}
