package it.v1nc3nz0.musimathics.data.configuration.enums;

import it.v1nc3nz0.musimathics.data.configuration.container.ConfigContainer;

public enum MessagesData
{

	ERROR_MUSIC_FILES_ISNT_VALID_EXTENSION("error.music-files.isnt-valid-extension");
	
	private String path;
	
	MessagesData(String path)
	{
		this.path = path;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public String getString()
	{
		return ConfigContainer.getMessagesConfig().getString(getPath());
	}
	
	public String toString()
	{
		return getPath();
	}
	
}
