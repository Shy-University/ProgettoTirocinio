package it.v1nc3nz0.musimathics.data.musicfiles.io;

import java.io.FileNotFoundException;
import java.io.FileReader;

import it.v1nc3nz0.musimathics.data.musicfiles.MusicFile;

public class MusicReader extends FileReader 
{

	public MusicReader(MusicFile file) throws FileNotFoundException 
	{
		super(file);
	}

}
