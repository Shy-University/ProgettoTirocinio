package it.v1nc3nz0.musimathics.data.music.caches.entity;

import it.v1nc3nz0.musimathics.data.musicfiles.entity.Scale;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.info.Alteration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ScaleKey
{

	private Scale.Notes note;
	private Alteration.Symbol alt;
	private Scale.Type type;
	
	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof ScaleKey key)) return false;
		return note.name().equals(key.getNote().name()) && alt.name().equals(key.getAlt().name())
				&& type.name().equals(key.getType().name());
	}
	
}
