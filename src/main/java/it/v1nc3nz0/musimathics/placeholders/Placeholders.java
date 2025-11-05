package it.v1nc3nz0.musimathics.placeholders;

public class Placeholders
{

	public static String replace(String placeholder, String str, Object value)
	{
		if(str.contains(placeholder))
			str = str.replace(placeholder, String.valueOf(value));
		
		return str;
	}
	
}
