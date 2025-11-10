package it.v1nc3nz0.musimathics.data.musicfiles.entity;

import it.v1nc3nz0.musimathics.api.musicfiles.MusicFileEntity;
import lombok.Getter;

@Getter
public class Metric implements MusicFileEntity
{

	private String metric;

	private int numerator;
	private int denominator;
	
	private double value;
	
	public Metric(String metric)
	{
		this.metric = metric;

		String[] split = metric.split("/+");
		this.numerator = Integer.valueOf(split[0]);
		this.denominator = Integer.valueOf(split[1]);
		
		double n = (double) numerator;
		double d = (double) denominator;
		
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
