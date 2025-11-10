package it.v1nc3nz0.musimathics.data.musicfiles.entity.info;

import it.v1nc3nz0.musimathics.data.musicfiles.exceptions.NoMixedSymbolsException;
import lombok.Getter;

@Getter
public class Alteration
{

	public enum Symbol
	{
		FLAT("FLAT","b",-1),
		NATURAL("NATURAL","n",0),
		SHARP("SHARP","#",1);
		
		private String name;
		private String symbol;
		private int multiplier;
		
		Symbol(String name, String symbol,int multiplier)
		{
			this.name = name;
			this.symbol = symbol;
			this.multiplier = multiplier;
		}
		
		public int getMultiplier() { return multiplier; }
		
		public String getName() { return name; }
		
		public String getSymbol() { return symbol; }
		
		public static Alteration.Symbol fromSymbol(String symbol)
		{
			if(symbol.equals("#")) return Alteration.Symbol.SHARP;
			else if(symbol.equals("b")) return Alteration.Symbol.FLAT;
			else return Alteration.Symbol.NATURAL;
		}
	}
	
	private String value;
	
	private Alteration()
	{
		naturalize();
	}
	
	public Alteration(String note) throws NoMixedSymbolsException
	{
		naturalize();
		
		while(note.endsWith(Alteration.Symbol.SHARP.getSymbol()))
		{
			this.value += Alteration.Symbol.SHARP.getSymbol();
		}
		
		while(note.endsWith(Alteration.Symbol.FLAT.getSymbol()))
		{
			this.value += Alteration.Symbol.FLAT.getSymbol();
		}
		
		if(isSharp() && isFlat())
			throw new NoMixedSymbolsException();
	
	}
	
	public static Alteration createFlat()
	{
		Alteration alt = new Alteration();
		alt.value = "b";
		return alt;
	}
	
	public static Alteration createNatural()
	{
		return new Alteration();
	}
	
	public static Alteration createSharp()
	{
		Alteration alt = new Alteration();
		alt.value = "#";
		return alt;
	}
	
	public static Alteration fromSymbol(String symbol)
	{
		if(symbol.equals("#")) return Alteration.createSharp();
		else if(symbol.equals("b")) return Alteration.createFlat();
		else return Alteration.createNatural();
	}
	
	public int get()
	{
		if(isFlat()) return value.length()*Alteration.Symbol.FLAT.getMultiplier();
		
		if(isSharp()) return value.length()*Alteration.Symbol.SHARP.getMultiplier();
		
		return value.length()*Alteration.Symbol.NATURAL.getMultiplier();
	}
	
	public boolean isFlat()
	{
		if(isNatural()) return false;
		return value.contains(Alteration.Symbol.FLAT.getSymbol());
	}
	
	public boolean isNatural()
	{
		return value.length() == 0;
	}
	
	public boolean isSharp()
	{
		if(isNatural()) return false;
		return value.contains(Alteration.Symbol.SHARP.getSymbol());
	}
	
	public void naturalize()
	{
		this.value = "";
	}
	
	public void union(Alteration alteration)
	{
		if(alteration.isNatural()) return;
		
		if(isNatural())
		{
			this.value = alteration.getValue();
			return;
		}
		
		if(isSharp() == alteration.isSharp() 
				|| isFlat() == alteration.isFlat())
		{
			this.value += alteration.getValue();
			return;
		}
		
		int diffLen = (this.value.length() < alteration.getValue().length()) 
				? alteration.getValue().length() - this.value.length() 
				: this.value.length() - alteration.getValue().length();
		
		this.value = (this.value.length() < alteration.getValue().length())
				? alteration.getValue().substring(0,diffLen)
				: this.value.substring(0, diffLen);
	}
	
}
