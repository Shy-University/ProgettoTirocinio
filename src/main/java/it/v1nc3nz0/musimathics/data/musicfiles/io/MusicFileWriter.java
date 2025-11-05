package it.v1nc3nz0.musimathics.data.musicfiles.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

import it.v1nc3nz0.musimathics.api.musicfiles.MusicFileEntity;
import it.v1nc3nz0.musimathics.data.musicfiles.generics.MusicFileEntityList;

public class MusicFileWriter extends BufferedWriter 
{

	public MusicFileWriter(Writer out) 
	{
		super(out);
	}
	
	public void append(String[] lines) throws IOException
	{
		for(String line : lines)
		{
			append(line);
			newLine();
		}
	}
	
	public void save(MusicFileEntityList entities) throws IOException
	{
		for(MusicFileEntity ent : entities)
		{
			append(ent.toString());
			newLine();
		}
		flush();
	}

}
