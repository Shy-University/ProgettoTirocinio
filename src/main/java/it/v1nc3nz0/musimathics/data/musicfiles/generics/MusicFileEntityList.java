package it.v1nc3nz0.musimathics.data.musicfiles.generics;

import java.util.ArrayList;
import java.util.List;

import it.v1nc3nz0.musimathics.api.musicfiles.MusicFileEntity;
import it.v1nc3nz0.musimathics.data.music.Piece;
import it.v1nc3nz0.musimathics.data.music.caches.entity.ScaleKey;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.BPM;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.Bar;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.Metric;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.Note;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.NoteList;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.Pause;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.Scale;
import jm.music.data.CPhrase;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Rest;

@SuppressWarnings("serial")
public class MusicFileEntityList extends ArrayList<MusicFileEntity>
{

	public List<Part> composePart(Piece piece)
	{
		List<Part> list = new ArrayList<>();
		Part part = new Part();
		
		Metric lastMetric = piece.getMetric();
		BPM lastTempo = piece.getTempo();
		Scale lastScale = piece.getScale();
		
		part.setNumerator(lastMetric.getNumerator());
		part.setDenominator(lastMetric.getDenominator());
		part.setTempo(lastTempo.getValue());
		
		CPhrase cphrase = null;
		Phrase phrase = null;
		
		double endTime = -1;
		boolean recentlyNewPart = false;
		
		for(MusicFileEntity entity : this)
		{
			if(entity instanceof Bar) continue;
			
			if(entity instanceof BPM tempo)
			{
				if(cphrase != null) 
				{
					part.addCPhrase(cphrase);
					cphrase = null;
				}
				if(phrase != null)
				{
					part.addPhrase(phrase);
					phrase = null;
				}
				
				lastTempo = tempo;
				endTime = part.getEndTime();
				list.add(part);
				
				if(recentlyNewPart)
				{
					part.setTempo(lastTempo.getValue());
				}
				else
				{
					part = new Part();
					part.setNumerator(lastMetric.getNumerator());
					part.setDenominator(lastMetric.getDenominator());
					part.setTempo(lastTempo.getValue());
				}
				continue;
			}
			
			if(entity instanceof Metric metric)
			{
				if(cphrase != null) 
				{
					part.addCPhrase(cphrase);
					cphrase = null;
				}
				if(phrase != null)
				{
					part.addPhrase(phrase);
					phrase = null;
				}
				
				lastMetric = metric;
				endTime = part.getEndTime();
				list.add(part);
				
				if(recentlyNewPart)
				{
					part.setNumerator(lastMetric.getNumerator());
					part.setDenominator(lastMetric.getDenominator());
				}
				else
				{
					part = new Part();
					part.setNumerator(lastMetric.getNumerator());
					part.setDenominator(lastMetric.getDenominator());
					part.setTempo(lastTempo.getValue());
				}
				continue;
			}
			
			if(entity instanceof Note note)
			{
				if(cphrase != null)
				{
					part.addCPhrase(cphrase);
					cphrase = null;
				}
				if(phrase == null) 
				{
					phrase = new Phrase();
					if(endTime != -1) 
					{
						phrase.setStartTime(endTime);
						endTime = -1;
					}
				}
				
				note.adjust(lastScale);
				phrase.add(new jm.music.data.Note(note.getSemitone(),note.getDuration().get()));
				recentlyNewPart = false;
				continue;
			}
			
			if(entity instanceof NoteList noteList)
			{
				if(phrase != null)
				{
					part.add(phrase);
					phrase = null;
				}
				if(cphrase == null) 
				{
					cphrase = new CPhrase();
					if(endTime != -1) 
					{
						cphrase.setStartTime(endTime);
						endTime = -1;
					}
				}
				
				List<jm.music.data.Note> toJmNote = new ArrayList<>();
				for(Note tmp : noteList)
				{
					tmp.adjust(lastScale);
					toJmNote.add(new jm.music.data.Note(tmp.getSemitone(),tmp.getDuration().get()));
				}
				cphrase.addChord((jm.music.data.Note[]) toJmNote.toArray());
				recentlyNewPart = false;
				continue;
			}
			
			if(entity instanceof Pause pause)
			{
				if(cphrase != null)
				{
					part.addCPhrase(cphrase);
					cphrase = null;
				}
				if(phrase == null) phrase = new Phrase();
				phrase.addRest(new Rest(pause.getDuration().get()));
				recentlyNewPart = false;
				continue;
			}
			
			if(entity instanceof Scale scale)
			{
				ScaleKey key = new ScaleKey(scale.getNote(), scale.getAlteration(), scale.getType());
				if(key.equals(new ScaleKey(lastScale.getNote(),lastScale.getAlteration(),lastScale.getType())))
					continue;
				
				lastScale = scale;
			}
		}
		
		if(cphrase != null) 
		{
			part.addCPhrase(cphrase);
			cphrase = null;
		}
		
		if(phrase != null)
		{
			part.addPhrase(phrase);
			phrase = null;
		}
		
		list.add(part);
		
		return list;
	}
	
}
