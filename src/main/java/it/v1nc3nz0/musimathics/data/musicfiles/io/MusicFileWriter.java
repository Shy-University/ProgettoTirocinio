package it.v1nc3nz0.musimathics.data.musicfiles.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

public class MusicFileWriter extends BufferedWriter 
{

	public MusicFileWriter(Writer out) 
	{
		super(out);
	}
	
	public void append(String[] lines) throws IOException
	{
		for(String note : lines)
		{
			append(note);
			newLine();
		}
	}

}
