package it.v1nc3nz0.musimathics.data.musicfiles.io;

import it.v1nc3nz0.musimathics.data.musicfiles.entity.BPM;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.Bar;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.Metric;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.Note;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.NoteList;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.Pause;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.Scale;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.info.Alteration;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.info.Duration;
import it.v1nc3nz0.musimathics.data.musicfiles.exceptions.InvalidDurationException;
import it.v1nc3nz0.musimathics.data.musicfiles.exceptions.NoMixedSymbolsException;

public class MusicFileParser
{

	public Bar parseBar(String line)
	{
		if(!line.startsWith(Bar.word()))
			throw new IllegalArgumentException();
		
		return new Bar();
	}
	
	public BPM parseBPM(String line)
	{
		if(!line.startsWith(BPM.word()))
			throw new IllegalArgumentException();
		
		line = line.replace(BPM.word()+" ", "");
		double bpm = Double.parseDouble(line);
		
		return new BPM(bpm);
	}
	
	public Metric parseMetric(String line)
	{
		if(!line.startsWith(Metric.word()))
			throw new IllegalArgumentException();
		
		line = line.replace(Metric.word()+" ", "");
		
		return new Metric(line);
	}
	
	public Note parseNote(String line) throws NoMixedSymbolsException, InvalidDurationException
	{
		if(!line.startsWith(Note.word()))
			throw new IllegalArgumentException();
		
		line = line.replace(Note.word()+" ", "");
		
		String[] noteData = line.split(";");
		
		Alteration alt = new Alteration(noteData[0]);
		//unire alteration con quella della scala
		
		Duration dur = new Duration(noteData[1]);
		
		return new Note(noteData[0].substring(0,noteData[0].length()-alt.getValue().length()),alt,dur);
	}
	
	public NoteList parseNoteList(String line) throws NoMixedSymbolsException, InvalidDurationException
	{
		if(!line.startsWith(Note.word()))
			throw new IllegalArgumentException();
		
		line = line.replace(Note.word()+" ", "");
		
		NoteList list = new NoteList();
		
		String[] noteList = line.split("\\s");
		
		for(int x = 0; x < noteList.length;x++)
		{
			String[] noteData = noteList[x].split(";");
			
			Alteration alt = new Alteration(noteData[0]);
			//unire alteration con quella della scala
			
			Duration dur = new Duration(noteData[1]);
			
			list.add(new Note(noteData[0].substring(0,noteData[0].length()-alt.getValue().length()),alt,dur));
		}
		
		return list;
	}
	
	public Pause parsePause(String line) throws InvalidDurationException
	{
		if(!line.startsWith(Pause.word()))
			throw new IllegalArgumentException();
		
		line = line.replace(Pause.word()+" ", "");
		
		return new Pause(line);
	}
	
	public Scale parseScale(String line)
	{
		if(!line.startsWith(Scale.word()))
			throw new IllegalArgumentException();
		
		line = line.replace(Scale.word()+" ", "");
		
		return new Scale(line);
	}
	
}
