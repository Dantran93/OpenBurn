package model;

/**
 * Case.java
 * 
 * Purpose: Represents the casing of a rocket during simulations.
**/

public class Case
{
	// Private fields
	private double caseMass;
	private double diameter;
	private double length;
	
	
	
	/**
	 * 
	**/
	
	public Case (double caseMass, double diameter, double length)
	{
		this.caseMass = caseMass;
		this.diameter = diameter;
		this.length = length;
	}
	
	public double getCaseMass ()
	{
		return caseMass;
	}
	
	public double getDiameter ()
	{
		return diameter;
	}
	
	public double getLength ()
	{
		return length;
	}
	
} // class Case
