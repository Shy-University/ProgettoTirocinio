package it.v1nc3nz0.musimathics.data.musicfiles.exceptions;

import it.v1nc3nz0.musimathics.data.configuration.enums.MessagesData;
import it.v1nc3nz0.musimathics.placeholders.placeholderclasses.EntityPH;

/*
 * General exception of music files exception
 */
@SuppressWarnings("serial")
public class InvalidDurationException extends MusicFileException
{
	
	public InvalidDurationException(String value)
	{
		super(EntityPH.duration(MessagesData.ERROR_MUSIC_FILES_ENTITIES_INVALID_DURATION.getString(), value));
	}
	
}
