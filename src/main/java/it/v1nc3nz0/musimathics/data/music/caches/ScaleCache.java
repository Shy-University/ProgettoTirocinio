package it.v1nc3nz0.musimathics.data.music.caches;

import java.util.HashMap;

import it.v1nc3nz0.musimathics.data.music.caches.entity.ScaleKey;
import it.v1nc3nz0.musimathics.data.musicfiles.entity.Scale;

public class ScaleCache
{

	private static HashMap<ScaleKey, Scale> scaleCache;
	
	static
	{
		ScaleCache.scaleCache = new HashMap<>();
	}
	
	public static void add(ScaleKey key, Scale scale)
	{
		if(ScaleCache.scaleCache.isEmpty())
		{
			ScaleCache.scaleCache.put(key, scale);
			return;
		}
		
		if(ScaleCache.scaleCache.keySet().stream().filter(filterKey -> filterKey.equals(key)).count() > 0)
		{
			return;
		}
		
		ScaleCache.scaleCache.put(key, scale);
		
	}
	
	public static Scale get(ScaleKey key)
	{
		Scale scale = ScaleCache.scaleCache.get(key);
		if(scale == null)
		{
			scale = new Scale(key.getNote(),key.getAlt(),key.getType());
			ScaleCache.add(key, scale);
		}
		return scale;
	}
	
}
