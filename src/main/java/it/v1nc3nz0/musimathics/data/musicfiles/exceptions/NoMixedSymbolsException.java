package it.v1nc3nz0.musimathics.data.musicfiles.exceptions;

import it.v1nc3nz0.musimathics.data.configuration.enums.MessagesData;

/*
 * General exception of music files exception
 */
@SuppressWarnings("serial")
public class NoMixedSymbolsException extends MusicFileException
{
	
	public NoMixedSymbolsException()
	{
		super(MessagesData.ERROR_MUSIC_FILES_ENTITIES_CANNOT_MIX_ALT_SYMBOLS.getString());
	}
	
}
