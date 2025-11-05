package it.v1nc3nz0.musimathics.data.musicfiles.exceptions;

import it.v1nc3nz0.musimathics.data.configuration.enums.MessagesData;
import it.v1nc3nz0.musimathics.placeholders.placeholderclasses.EntityPH;

/*
 * General exception of music files exception
 */
@SuppressWarnings("serial")
public class NoteOutOfBoundException extends MusicFileException
{
	
	public NoteOutOfBoundException(int units)
	{
		super(EntityPH.stUnits(MessagesData.ERROR_MUSIC_FILES_ENTITIES_NOTE_OUTOFBOUND.getString(), units));
	}
	
}
