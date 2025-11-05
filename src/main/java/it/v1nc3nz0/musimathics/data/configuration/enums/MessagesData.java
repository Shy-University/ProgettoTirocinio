package it.v1nc3nz0.musimathics.data.configuration.enums;

import it.v1nc3nz0.musimathics.data.configuration.container.ConfigContainer;

public enum MessagesData
{

	ERROR_MUSIC_FILES_ENTITIES_CANNOT_MIX_ALT_SYMBOLS("error.music-files.entities.cannot-mix-alt-symbols"),
	ERROR_MUSIC_FILES_ENTITIES_INVALID_DURATION("error.music-files.entities.invalid-duration"),
	ERROR_MUSIC_FILES_ENTITIES_NOTE_OUTOFBOUND("error.music-files.entities.note-outofbound"),
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
