package it.v1nc3nz0.musimathics.data.musicfiles.entity;

import it.v1nc3nz0.musimathics.api.musicfiles.MusicFileEntity;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.info.Duration;
import it.v1nc3nz0.musimathics.data.musicfiles.exceptions.InvalidDurationException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pause implements MusicFileEntity 
{

	private Duration duration;
	
	public Pause(String duration) throws InvalidDurationException
	{
		this.duration = new Duration(duration);
	}
	
	@Override
	public String symbol()
	{
		return "R" + duration.get();
	}
	
	@Override
	public String toString()
	{
		return Pause.word() + " " + duration.get();
	}

	public static String word()
	{
		return "PAUSE";
	}

}
