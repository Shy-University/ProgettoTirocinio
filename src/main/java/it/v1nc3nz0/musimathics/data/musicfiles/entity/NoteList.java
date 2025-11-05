package it.v1nc3nz0.musimathics.data.musicfiles.entity;

import java.util.ArrayList;

import it.v1nc3nz0.musimathics.api.musicfiles.MusicFileEntity;

@SuppressWarnings("serial")
public class NoteList extends ArrayList<Note> implements MusicFileEntity
{

	public NoteList() {}
	
	public NoteList(Note... notes)
	{
		for(Note note : notes)
		{
			add(note);
		}
	}
	
	
	@Override
	public String symbol()
	{
		String values = "";
		for(Note note : this)
		{
			values = values.concat(note.symbol()) + " ";
		}
		return values.substring(0, values.length()-1);
	}
	
	@Override
	public String toString()
	{
		return NoteList.word()+" "+symbol();
	}
	
	public static String word()
	{
		return "NOTE";
	}

}
