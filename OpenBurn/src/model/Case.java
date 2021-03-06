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
	 * Case Constructor
	 * 
	 * Purpose: Creates and initializes a case for the rocket during
	 * 		the simulation.
	**/
	
	public Case (double caseMass, double diameter, double length)
	{
		this.caseMass = caseMass;
		this.diameter = diameter;
		this.length = length;
	} // Case Constructor
	
	
	
	/**
	 * getCaseMass()
	 * 
	 * Purpose: Returns the mass of the case.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The mass of the case.
	**/
	
	public double getCaseMass ()
	{
		return caseMass;
	} // getCaseMass()
	
	
	
	/**
	 * getDiameter()
	 * 
	 * Purpose: Returns the diameter of the case.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The diameter of the case.
	**/
	
	public double getDiameter ()
	{
		return diameter;
	} // getDiameter()
	
	
	
	/**
	 * getLength()
	 * 
	 * Purpose: Returns the length of the case.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The length of the case.
	**/
	
	public double getLength ()
	{
		return length;
	} // getLength()
	
} // class Case
