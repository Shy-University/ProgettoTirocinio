package it.v1nc3nz0.musimathics.data.musicfiles.entity;

import it.v1nc3nz0.musimathics.api.musicfiles.MusicFileEntity;

public class Bar implements MusicFileEntity 
{
	
	@Override
	public String symbol()
	{
		return "|";
	}
	
	@Override
	public String toString()
	{
		return Bar.word();
	}

	public static String word()
	{
		return "BAR";
	}

}
