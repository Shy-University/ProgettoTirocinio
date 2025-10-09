package it.v1nc3nz0.musimathics.data.musicfiles.exceptions;

import it.v1nc3nz0.musimathics.data.configuration.enums.MessagesData;

/*
 * Called when file do not end with .mf extension
 */
@SuppressWarnings("serial")
public class InvalidMusicFileException extends MusicFileException
{
	
	public InvalidMusicFileException()
	{
		super(MessagesData.ERROR_MUSIC_FILES_ISNT_VALID_EXTENSION.getString());
	}
	
}
