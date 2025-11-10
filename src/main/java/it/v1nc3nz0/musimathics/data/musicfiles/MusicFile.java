package it.v1nc3nz0.musimathics.data.musicfiles;

import java.io.File;
import java.io.IOException;

import it.v1nc3nz0.musimathics.data.music.caches.ScaleCache;
import it.v1nc3nz0.musimathics.data.music.caches.entity.ScaleKey;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.BPM;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.Bar;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.Metric;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.Note;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.Scale;
import it.v1nc3nz0.musimathics.data.musicfiles.exceptions.InvalidDurationException;
import it.v1nc3nz0.musimathics.data.musicfiles.exceptions.InvalidMusicFileException;
import it.v1nc3nz0.musimathics.data.musicfiles.exceptions.NoMixedSymbolsException;
import it.v1nc3nz0.musimathics.data.musicfiles.generics.MusicFileEntityList;
import it.v1nc3nz0.musimathics.data.musicfiles.io.MusicFileParser;
import it.v1nc3nz0.musimathics.data.musicfiles.io.MusicFileReader;
import it.v1nc3nz0.musimathics.data.musicfiles.io.MusicReader;

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
	
	public MusicFileEntityList getEntities() throws IOException, NoMixedSymbolsException, InvalidDurationException
	{
		MusicFileReader reader = new MusicFileReader(new MusicReader(this));
		MusicFileParser parser = new MusicFileParser();
		
		MusicFileEntityList entities = new MusicFileEntityList();
		String line = null;
		
		while((line = reader.readLine()) != null)
		{
			
			if(line.startsWith(Bar.word()))
			{
				entities.add(parser.parseBar(line));
				continue;
			}
			
			if(line.startsWith(BPM.word()))
			{
				entities.add(parser.parseBPM(line));
				continue;
			}
			
			if(line.startsWith(Metric.word()))
			{
				entities.add(parser.parseMetric(line));
				continue;
			}
			
			if(line.startsWith(Note.word()))
			{
				
				if(line.lastIndexOf(" ") == Note.word().length()+1)
					entities.add(parser.parseNote(line));
				else
					entities.add(parser.parseNoteList(line));
				
				continue;
			}
			
			if(line.startsWith(Scale.word()))
			{
				Scale scale = parser.parseScale(line);
				ScaleKey key = new ScaleKey(scale.getNote(),scale.getAlteration(),scale.getType());
				ScaleCache.add(key, scale);
				entities.add(scale);
			}
		}
		
		reader.close();
		
		return entities;
	}
	
	private void validate(String fileName) throws InvalidMusicFileException
	{
		if(!fileName.endsWith(".mf")) throw new InvalidMusicFileException();
	}
}
