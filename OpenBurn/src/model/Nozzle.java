package model;

/**
 * Nozzle.java
 * 
 * Contains data for an individual nozzle for a rocket motor.
 * Crucial to graph calculations.
**/

public class Nozzle
{
	// Private fields for the Nozzle
	private double throatDiameter;
	private double throatArea;
	private double entranceArea;
	private double exitDiameter;
	private double cf;
	private int entranceID;
	private int throatID;
	
	
	
	/**
	 * Nozzle Constructor
	 * 
	 * Purpose: Creates and initializes a Nozzle object using the given
	 * 		throat diameter, entrance diameter, exit diameter, cf, and
	 * 		number of grains.
	**/
	
	public Nozzle (double throatDiameter, double entranceDiameter, double exitDiameter, double cf, int numberOfGrains)
	{
		this.throatDiameter = throatDiameter;
		this.throatArea = Math.PI * (throatDiameter / 2) * (throatDiameter / 2);
		this.entranceArea = Math.PI * (entranceDiameter / 2) * (entranceDiameter / 2);
		this.cf = cf;
		this.exitDiameter = exitDiameter;
		this.entranceID = numberOfGrains + 1;
		this.throatID = numberOfGrains + 2;
	} // Nozzle Constructor
	
	
	
	/**
	 * getThroatDiameter()
	 * 
	 * Purpose: Returns the throat diameter of the nozzle.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The throat diameter of the nozzle.
	**/
	
	public double getThroatDiameter()
	{
		return throatDiameter;
	} // getThroatDiameter()
	
	
	
	/**
	 * getExitDiameter()
	 * 
	 * Purpose: Returns the exit diameter of the nozzle.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The exit diameter of the nozzle.
	**/
	
	public double getExitDiameter ()
	{
		return exitDiameter;
	} // getExitDiameter()
	
	
	
	/**
	 * getCf()
	 * 
	 * Purpose: Returns the cf of the nozzle.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The cf of the nozzle.
	**/
	
	public double getCf ()
	{
		return cf;
	} // getCf()
	
	
	
	/**
	 * getEntranceID()
	 * 
	 * Purpose: Returns the entrance ID of the nozzle.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: int. The entrance ID of the nozzle.
	**/
	
	public int getEntranceID ()
	{
		return entranceID;
	} // getEntranceID()
	
	
	
	/**
	 * getThroatID()
	 * 
	 * Purpose: Returns the throat ID of the nozzle.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: int. The throat ID of the nozzle.
	**/
	
	public int getThroatID ()
	{
		return throatID;
	} // getThroatID()
	
	
	
	/**
	 * getThroatArea()
	 * 
	 * Purpose: Returns the throat area of the nozzle.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The throat area of the nozzle.
	**/
	
	public double getThroatArea ()
	{
		return throatArea;
	} // getThroat()
	
	
	
	/**
	 * getEntranceArea()
	 * 
	 * Purpose: Returns the entrance area of the nozzle.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The entrance area of the nozzle.
	**/
	
	public double getEntranceArea ()
	{
		return entranceArea;
	} // getEntranceArea()
	
} // class Nozzle
