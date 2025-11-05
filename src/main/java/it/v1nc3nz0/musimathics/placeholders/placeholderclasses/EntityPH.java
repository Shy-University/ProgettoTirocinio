package it.v1nc3nz0.musimathics.placeholders.placeholderclasses;

import it.v1nc3nz0.musimathics.placeholders.Placeholders;

public class EntityPH
{

	// semitone in units
	public static String stUnits(String str,int units)
	{
		return Placeholders.replace("{units}", str, String.valueOf(units));
	}
	
	public static String duration(String str, Object value)
	{
		return Placeholders.replace("{duration}", str, value);
	}
	
}
