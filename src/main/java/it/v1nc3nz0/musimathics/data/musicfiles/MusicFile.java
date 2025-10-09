package it.v1nc3nz0.musimathics.data.musicfiles;

import java.io.File;

import it.v1nc3nz0.musimathics.data.musicfiles.exceptions.InvalidMusicFileException;

/*
 * Music File
 */
@SuppressWarnings("serial")
public class MusicFile extends File
{

	public MusicFile(File parent, String child) throws InvalidMusicFileException 
	{
		super(parent, child);
		validate(child);
	}
	
	public MusicFile(String pathname) throws InvalidMusicFileException 
	{
		super(pathname);
		validate(pathname);
	}
	
	public MusicFile(String parent, String child) throws InvalidMusicFileException 
	{
		super(parent, child);
		validate(child);
	}
	
	
	private void validate(String fileName) throws InvalidMusicFileException
	{
		if(!fileName.endsWith(".mf")) throw new InvalidMusicFileException();
	}
}
