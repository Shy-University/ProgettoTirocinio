package it.v1nc3nz0.musimathics.data.musicfiles.entity;

import it.v1nc3nz0.musimathics.api.musicfiles.MusicFileEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BPM implements MusicFileEntity
{

	private double value;
	
	@Override
	public String symbol()
	{
		return "T"+String.valueOf(getValue());
	}
	
	@Override
	public String toString()
	{
		return BPM.word()+" "+String.valueOf(getValue());
	}

	public static String word()
	{
		return "BPM";
	}

}
