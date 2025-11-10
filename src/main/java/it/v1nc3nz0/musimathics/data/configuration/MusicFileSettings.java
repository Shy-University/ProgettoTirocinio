package it.v1nc3nz0.musimathics.data.configuration;

import it.v1nc3nz0.musimathics.api.configuration.AbstractConfiguration;
import it.v1nc3nz0.musimathics.data.configuration.enums.MusicFileSettingsData;

public class MusicFileSettings extends AbstractConfiguration
{
	
	public MusicFileSettings(String name)
	{
		super(ApplicationConfig.getMusicFileSettingsFolder(), name);
	}
	
	public int getBPM()
	{
		return getInt(MusicFileSettingsData.DEFAULT_MUSIC_BPM.getPath());
	}
	
	public String getMetric()
	{
		return getString(MusicFileSettingsData.DEFAULT_MUSIC_METRIC.getPath());
	}
	
	public String getScaleAlteration()
	{
		return getString(MusicFileSettingsData.DEFAULT_MUSIC_SCALE_ALTERATION.getPath());
	}
	
	public String getScaleNote()
	{
		return getString(MusicFileSettingsData.DEFAULT_MUSIC_SCALE_NOTE.getPath());
	}
	
	public String getScaleType()
	{
		return getString(MusicFileSettingsData.DEFAULT_MUSIC_SCALE_TYPE.getPath());
	}
	
	public int getVoices()
	{
		return getInt(MusicFileSettingsData.VOICES.getPath());
	}
	
	public String getTitle()
	{
		return getString(MusicFileSettingsData.TITLE.getPath());
	}
	
}
