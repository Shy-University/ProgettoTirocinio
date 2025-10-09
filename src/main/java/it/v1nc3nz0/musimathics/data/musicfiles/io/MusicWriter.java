package it.v1nc3nz0.musimathics.data.musicfiles.io;

import java.io.FileWriter;
import java.io.IOException;

import it.v1nc3nz0.musimathics.data.musicfiles.MusicFile;

public class MusicWriter extends FileWriter 
{

	public MusicWriter(MusicFile file) throws IOException 
	{
		super(file);
	}
	
	public MusicWriter(MusicFile file,boolean append) throws IOException 
	{
		super(file,append);
	}

}
