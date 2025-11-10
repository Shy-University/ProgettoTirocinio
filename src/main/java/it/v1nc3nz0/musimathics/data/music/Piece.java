package it.v1nc3nz0.musimathics.data.music;

import java.util.List;

import it.v1nc3nz0.musimathics.data.configuration.MusicFileSettings;
import it.v1nc3nz0.musimathics.data.music.caches.ScaleCache;
import it.v1nc3nz0.musimathics.data.music.caches.entity.ScaleKey;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.BPM;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.Metric;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.Scale;
import it.v1nc3nz0.musimathics.data.musicfiles.generics.MusicFileEntityList;
import jm.music.data.Part;
import jm.music.data.Score;
import jm.util.Play;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Piece
{
	
	private Metric metric;
	private Scale scale;
	private BPM tempo; // bpm
	private String title;
	private int voices; // number of voice
	
	private List<MusicFileEntityList> voicesInfo;
	private Score score;
	
	public Piece(MusicFileSettings mfs,List<MusicFileEntityList> voices)
	{
		setMetric(new Metric(mfs.getMetric()));
		setScale(ScaleCache.get(ScaleKey.create(mfs.getScaleNote(), mfs.getScaleAlteration(), mfs.getScaleType())));
		setTempo(new BPM(mfs.getBPM()));
		setTitle(mfs.getTitle());
		setVoices(mfs.getVoices());
		
		setVoicesInfo(voices);
		setScore(compose());
	}
	
	public Score compose()
	{
		Score score = new Score();
		score.setTitle(title);
		
		for(MusicFileEntityList voiceInfo : voicesInfo)
		{
			List<Part> list = voiceInfo.composePart(this);
			list.forEach(part -> score.add(part));
		}
		
		return score;
	}
	
	public void play()
	{
		Play.midi(score);
	}
	
}
