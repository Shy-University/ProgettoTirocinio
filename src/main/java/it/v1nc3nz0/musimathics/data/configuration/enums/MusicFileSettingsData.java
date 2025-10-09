package it.v1nc3nz0.musimathics.data.configuration.enums;

public enum MusicFileSettingsData
{

	DEFAULT_MUSIC_BPM("default-music-bpm"),
	DEFAULT_MUSIC_METRIC("default-music-metric"),
	DEFAULT_MUSIC_SCALE_ALTERATION("default-music-scale.alteration"),
	DEFAULT_MUSIC_SCALE_NOTE("default-music-scale.note"),
	DEFAULT_MUSIC_SCALE_TYPE("default-music-scale.type"),
	VOICES("voices");
	
	
	private String path;
	
	MusicFileSettingsData(String path)
	{
		this.path = path;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public String toString()
	{
		return getPath();
	}
	
}
