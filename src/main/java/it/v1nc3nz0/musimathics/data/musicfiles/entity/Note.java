package it.v1nc3nz0.musimathics.data.musicfiles.entity;

import it.v1nc3nz0.musimathics.api.musicfiles.MusicFileEntity;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.info.Alteration;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.info.Duration;
import it.v1nc3nz0.musimathics.data.musicfiles.exceptions.NoteOutOfBoundException;
import lombok.Getter;

@Getter
public class Note implements MusicFileEntity
{
	
	enum NoteInfo
	{
		A0("LA0",1),B0("SI0",3),
		C1("DO1",4),D1("RE1",6),E1("MI1",8),F1("FA1",9),G1("SOL1",11),A1("LA1",13),B1("SI1",15),
		C2("DO2",16),D2("RE2",18),E2("MI2",20),F2("FA2",21),G2("SOL2",23),A2("LA2",25),B2("SI2",27),
		C3("DO3",28),D3("RE3",30),E3("MI3",32),F3("FA3",33),G3("SOL3",35),A3("LA3",37),B3("SI3",39),
		C4("DO4",40),D4("RE4",42),E4("MI4",44),F4("FA4",45),G4("SOL4",47),A4("LA4",49),B4("SI4",51),
		C5("DO5",52),D5("RE5",54),E5("MI5",56),F5("FA5",57),G5("SOL5",59),A5("LA5",61),B5("SI5",63),
		C6("DO6",64),D6("RE6",66),E6("MI6",68),F6("FA6",69),G6("SOL6",71),A6("LA6",73),B6("SI6",75),
		C7("DO7",76),D7("RE7",78),E7("MI7",80),F7("FA7",81),G7("SOL7",83),A7("LA7",85),B7("SI7",87),
		C8("DO8",88);
		
		private String itaName;
		
		private int semitone;
		
		NoteInfo(String itaName, int semitone)
		{
			this.itaName = itaName;
			this.semitone = semitone;
		}
		
		public static NoteInfo fromName(String name)
		{
			if(name.startsWith("DO")) name = name.replaceFirst("DO","C");
			if(name.startsWith("RE")) name = name.replaceFirst("RE","D");
			if(name.startsWith("MI")) name = name.replaceFirst("MI","E");
			if(name.startsWith("FA")) name = name.replaceFirst("FA","F");
			if(name.startsWith("SOL")) name = name.replaceFirst("SOL","G");
			if(name.startsWith("LA")) name = name.replaceFirst("LA","A");
			if(name.startsWith("SI")) name = name.replaceFirst("SI","B");
			return NoteInfo.valueOf(name);
		}
		
		public int getSemitone()
		{
			return semitone;
		}
		
		public String toITA()
		{
			return itaName;
		}
		
		public String toString()
		{
			return name().toUpperCase();
		}
	}
	
	// Frequenza della nota A0
	private final static double START_FREQUENCE = 27.500;
	
	private Alteration alteration;
	
	private Duration duration;
	
	private NoteInfo noteInfo;
	
	private double frequence;
	
	private int semitone;
	
	public Note(int semitone, Alteration.Symbol scaleType, Duration duration)
	{

		if(scaleType == Alteration.Symbol.SHARP)
			this.alteration = Alteration.createSharp();
		
		if(scaleType == Alteration.Symbol.NATURAL)
			this.alteration = Alteration.createNatural();
		
		if(scaleType == Alteration.Symbol.FLAT)
			this.alteration = Alteration.createFlat();
		
		this.duration = duration;
		this.frequence = Note.semitoneToFrequence(semitone);
		this.semitone = semitone;
		validate();
		
		NoteInfo[] values = NoteInfo.values();
		
		for(int x = 0;x < values.length; x++)
		{
			if(values[x].getSemitone() == semitone)
			{
				this.noteInfo = values[x];
				break;
			}
			
			if(values[x].getSemitone() > semitone)
			{
				if(scaleType == Alteration.Symbol.SHARP)
					this.noteInfo = values[x-1];
				
				if(scaleType == Alteration.Symbol.FLAT)
					this.noteInfo = values[x];
			}
		}
	}
	
	public Note(String name, Alteration alteration, Duration duration)
	{
		this.noteInfo = NoteInfo.fromName(name);
		this.alteration = alteration;
		this.duration = duration;
		this.semitone = this.noteInfo.getSemitone()+this.alteration.get();
		validate();
		this.frequence = Note.semitoneToFrequence(this.semitone);
	}
	
	public static double semitoneToFrequence(int semitone)
	{
		return Note.START_FREQUENCE * Math.pow(1.059463,semitone-1);
	}
	
	public static String word()
	{
		return "NOTE";
	}
	
	@Override
	public String symbol()
	{
		return noteInfo.toString()+alteration.getValue()+";"+duration.getValue();
	}
	
	public String toITA()
	{
		return noteInfo.toITA()+alteration.getValue()+";"+duration.getValue();
	}
	
	@Override
	public String toString()
	{
		return Note.word()+" "+symbol();
	}
	
	public boolean validate()
	{
		try
		{
			if(semitone < 1)
				throw new NoteOutOfBoundException(1 - semitone);
			
			if(semitone > 88)
				throw new NoteOutOfBoundException(semitone - 88);
			
			return true;
		}
		catch(NoteOutOfBoundException e)
		{
			e.printStackTrace();
			return false;
		}
	}

}
