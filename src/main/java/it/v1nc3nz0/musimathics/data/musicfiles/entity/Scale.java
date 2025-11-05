package it.v1nc3nz0.musimathics.data.musicfiles.entity;

import java.util.HashMap;

import it.v1nc3nz0.musimathics.api.musicfiles.MusicFileEntity;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.info.Alteration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Scale implements MusicFileEntity
{
	
	public enum Notes
	{
		C(0,0),D(1,2),E(2,4),F(3,5),G(4,7),A(5,9),B(6,11);
		
		private int arrayIndex;  // array index
		private int scaleIndex;  // scale index
		
		Notes(int arrayIndex, int scaleIndex)
		{
			this.arrayIndex = arrayIndex;
			this.scaleIndex = scaleIndex;
		}
		
		public int arrayIndex()
		{
			return arrayIndex;
		}
		
		public int scaleIndex()
		{
			return scaleIndex;
		}
		
		public static Notes fromArrayIndex(int value)
		{
			for(Notes note : Notes.values())
			{
				if(note.arrayIndex() == value) return note;
			}
			return null;
		}
		
		public static Notes fromScaleIndex(int value)
		{
			for(Notes note : Notes.values())
			{
				if(note.scaleIndex() == value) return note;
			}
			return null;
		}
		
	}
	
	public enum Type
	{
		MAJ(new int[] {2,2,1,2,2,2,1}),
		MIN(new int[] {2,1,2,2,1,2,2}),
		MIN_A(new int[] {2,1,2,2,1,3,1}),
		MIN_M(new int[] {2,1,2,2,3,2,1});
		
		private int[] tones;
		
		Type(int[] tones)
		{
			this.tones = tones;
		}
		
		public int tone(int index)
		{
			return tones[index];
		}
		
		public int[] tones()
		{
			return tones;
		}
	}
	
	private HashMap<Notes,Alteration.Symbol> alterations;
	private Alteration.Symbol alteration;
	private Notes note;
	private Type type;
	
	public Scale(Notes note, Alteration.Symbol alteration, Type type)
	{
		this.note = note;
		this.alteration = alteration;
		this.type = type;
		this.alterations = new HashMap<>();
		buildScale();
	}
	
	public void buildScale()
	{

		alterations.put(note, alteration);
		
		int lastIndex = note.arrayIndex();
		int lastTone = note.scaleIndex();
		if(alteration != Alteration.Symbol.NATURAL)
		{
			if(alteration == Alteration.Symbol.FLAT) lastTone -= 1;
			else lastTone += 1;
		}
		
		for(int x = 0; x < 6;x++)
		{
			// index note
			int i = (lastIndex+1)%7;
			
			// semitoni nella scala
			int a = (lastTone+type.tones()[x])%12;
			
			Notes note = Notes.fromScaleIndex(a);
			
			if(note == null)
			{
				note = Notes.fromArrayIndex(i);
				a -= note.scaleIndex();
				
				if(a < 0)
				{
					alterations.put(note, Alteration.Symbol.FLAT);
				}
				else
				{
					alterations.put(note, Alteration.Symbol.SHARP);
				}
			}
			else
			{
				alterations.put(note, Alteration.Symbol.NATURAL);
			}
			
			lastIndex = i;
			lastTone = a;
			
		}
		
	}

	public Alteration getAlteration(Notes note)
	{
		Alteration.Symbol symb = alterations.get(note);
		if(symb == Alteration.Symbol.FLAT) Alteration.createFlat();
		if(symb == Alteration.Symbol.SHARP) Alteration.createSharp();
		return Alteration.createNatural();
	}
	
	@Override
	public String symbol()
	{
		return "S" + note.name() + type.name().toLowerCase();
	}
	
	@Override
	public String toString()
	{
		return Scale.word() + " "+ note.name() + " " + type.name();
	}
	
	public static String word()
	{
		return "SCALE";
	}

}
