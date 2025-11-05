package it.v1nc3nz0.musimathics.data.musicfiles.entity;

import it.v1nc3nz0.musimathics.api.musicfiles.MusicFileEntity;
import lombok.Getter;
import lombok.Setter;

public class Metric implements MusicFileEntity
{

	@Getter
	@Setter
	private String metric;
	
	private double value;
	
	public Metric(String metric)
	{
		this.metric = metric;

		String[] split = metric.split("/+");
		double n = (double) Integer.valueOf(split[0]);
		double d = (double) Integer.valueOf(split[1]);
		
		this.value = (n/d)*4;
	}
	
	public double get()
	{
		return this.value;
	}
	
	@Override
	public String symbol()
	{
		return "M"+getMetric();
	}
	
	@Override
	public String toString()
	{
		return Metric.word()+" "+getMetric();
	}

	public static String word()
	{
		return "METRIC";
	}

}
