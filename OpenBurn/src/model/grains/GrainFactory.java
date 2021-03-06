package model.grains;

/**
 * GrainFactory.java
 * 
 * Purpose: 
**/

public class GrainFactory
{
	/**
	 * createGrain()
	 * 
	 * Purpose:
	 * 
	 * Parameters:
	 * 
	 * Returns: Grain. 
	**/
	
	public static Grain createGrain (GrainType type, double length, double outerDiameter, double innerDiameter, int numBurningEnds)
	{
		if (type == GrainType.Cylindrical)
			return new CylindricalGrain(length, outerDiameter, innerDiameter, numBurningEnds);
		if (type == GrainType.Star)
			return null; // FIXME: Eventually return a star grain
		
		return null;
	} // createGrain()
	
	
	
	/**
	 * createClone()
	 * 
	 * Purpose:
	 * 
	 * Parameters:
	 * 
	 * Returns: 
	**/
	
	public static Grain createClone (Grain originalGrain)
	{
		return originalGrain.clone();
	} // createClone()
	
} // class GrainFactory
