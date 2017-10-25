package model.grains;

/**
 * Grain.java
 * 
 * Contains data for an individual grain for a rocket motor.
 * Crucial to graph calculations. Includes methods that allow the
 * grain to change over time.
**/

abstract public class Grain
{
	// Error messages
	protected static final String OUTER_DIAMETER_ERR_MSG  = "ERROR: Outer diameter must be positive!\n";
	protected static final String INNER_DIAMETER_ERR_MSG  = "ERROR: Inner diameter must be positive!\n";
	protected static final String OUTER_INNER_ERR_MSG     = "ERROR: Outer diameter must be greater than inner diameter.\n";
	protected static final String LENGTH_ERR_MSG          = "ERROR: Length must be positive.\n";
	protected static final String BURNING_ENDS_ERR_MSG    = "ERROR: Burning ends must be 0, 1, or 2.\n";
	protected static final String PROP_DENSITY_ERR_MSG    = "ERROR: Propellant density must be positive!\n";
	protected static final String NEGATIVE_VOLUME_ERR_MSG = "ERROR: Negative volume change occurred!\n";
	
	
	
	// Propellant density is the same for all Grains
	protected double propellantDensity;
	protected double length;
	protected double outerDiameter;
	protected double innerDiameter; 
	protected final double initialLength;
	protected int numBurningEnds;
	protected double burnoutTime;
	protected boolean isBurning;
	
	
	
	/**
	 * Grain Constructor
	 * 
	 * Purpose: Creates and initializes a Grain object using the given
	 * 		values for outer diameter, inner diameter, and the number of
	 * 		burning ends. Burnout time is initialized to 0 seconds.
	 * 
	 * 		Requirements:
	 * 		- Outer and inner diameters MUST be positive.
	 * 		- Outer diameter MUST be greater than inner diameter.
	 * 		- Length MUST be positive.
	 * 		- Number of burning ends must be 0, 1, or 2.
	 * 
	 * 		NOTE: Propellant density is not set here, user must call
	 * 			setPropellantDensity() to set it.
	**/
	
	public Grain (double length, double outerDiameter, double innerDiameter, int numBurningEnds)
	{
		// Error check all inputs
		if (outerDiameter <= 0)
			throw new IllegalArgumentException(OUTER_DIAMETER_ERR_MSG);
		if (innerDiameter <= 0)
			throw new IllegalArgumentException(INNER_DIAMETER_ERR_MSG);
		if (innerDiameter >= outerDiameter)
			throw new IllegalArgumentException(OUTER_INNER_ERR_MSG);
		if (length < 0)
			throw new IllegalArgumentException(LENGTH_ERR_MSG);
		if (numBurningEnds < 0 || numBurningEnds > 2)
			throw new IllegalArgumentException(BURNING_ENDS_ERR_MSG);
		
		// Set fields
		this.outerDiameter  = outerDiameter;
		this.length         = length;
		this.innerDiameter  = innerDiameter;
		this.numBurningEnds = numBurningEnds;
		this.initialLength  = length;
		this.isBurning      = true;
		
		// Initialize burnout time to 0 seconds
		setBurnoutTime(0.0);
	} // Grain Constructor
	
	
	
	/**
	 * getPropellantDensity()
	 * 
	 * Purpose: Returns the propellant density.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The propellant density.
	**/
	
	abstract public double getPropellantDensity ();
	
	
	
	/**
	 * setPropellantDensity()
	 * 
	 * Purpose: Changes the propellant density to a new value.
	 * 
	 * Parameters:
	 * 		double density -- New value to set the propellant density.
	 * 
	 * Returns: void.
	**/
	
	abstract public void setPropellantDensity (double density);

	
	
	/**
	 * getLength()
	 * 
	 * Purpose: Returns the length of the grain.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The length of the grain.
	**/
	
	abstract public double getLength ();
	
	
	
	/**
	 * getOuterDiameter()
	 * 
	 * Purpose: Returns the outer diameter of the grain.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The outer diameter of the grain.
	**/
	
	abstract public double getOuterDiameter ();
	
	
	
	/**
	 * getInnerDiameter()
	 * 
	 * Purpose: Returns the inner diameter of the grain.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The inner diameter of the grain.
	**/
	
	abstract public double getInnerDiameter ();
	
	
	
	/**
	 * getNumBurningEnds()
	 * 
	 * Purpose: Returns the number of burning ends.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: int. The number of burning ends.
	**/
	
	abstract public int getNumBurningEnds ();
	
	
	
	/**
	 * getBurnoutTime()
	 * 
	 * Purpose: Returns the burnout time.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The burnout time.
	**/
	
	abstract public double getBurnoutTime ();
	
	
	
	/**
	 * setBurnoutTime()
	 * 
	 * Purpose: Changes the burnout time to a new value.
	 * 
	 * Parameters:
	 * 		double burnoutTime -- New value to set the burnout time.
	 * 
	 * Returns: void.
	**/
	
	abstract public void setBurnoutTime (double newBurnoutTime);
	
	
	
	/**
	 * isBurning()
	 * 
	 * Purpose: Getter for the isBurning boolean. This is set initially as
	 * 		true. The update geometry method is responsible for setting
	 * 		this to false.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: boolean. The status on if the grain is burning or not.
	**/
	
	abstract public boolean isBurning ();
	
	
	
	/**
	 * getVolume()
	 * 
	 * Purpose: Uses the current values of the grain properties to calculate
	 * 		and return the volume of the grain.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The volume of the grain.
	**/
	
	abstract public double getVolume ();
	
	
	
	/**
	 * getBurnArea()
	 * 
	 * Purpose: Uses the current values of the grain properties to calculate
	 * 		and return the burnable surface area of the grain. All calculations
	 * 		are based on those performed in cylindrical_grain_burn_area.m.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The burnable surface area of the grain.
	**/
	
	abstract public double getBurnArea ();
	
	
	
	/**
	 * updateGeometry()
	 * 
	 * Purpose: Updates the Grain object using calculations based on
	 * 		those performed in cylindrical_grain_geomtetry_update.m,
	 * 		using the given burn rate and change in time (seconds).
	 * 		
	 * 		The overall change in volume is returned.
	 * 
	 * Parameters:
	 * 		double burnRate -- Burn rate affecting the grain.
	 * 		double deltaTime -- Change in time.
	 * 
	 * Returns: double. The change in volume after calculations.
	**/
	
	abstract public double updateGeometry (double burnRate, double deltaTime);
	
	
	
	/**
	 * getCurrentInnerFlowArea()
	 * 
	 * Purpose: Uses the current geometry state to get the inner flow
	 * 		area of the motor. This is provided in the grains standard unit.
	 * 		This is used for mass flow per area and port to throat ratio
	 * 		calculations.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The inner flow area of the motor
	**/
	
	abstract public double getCurrentInnerFlowArea();
	
	
	
	/**
	 * getCurrentInnerFlowVolume()
	 * 
	 * Purpose: Uses the current geometry state to get the inner flow
	 * 		volume of the motor. This is provided in the grains standard unit.
	 * 		This is used for l* calculations.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The inner flow volume of the motor.
	**/
	
	abstract public double getCurrentInnerFlowVolume();
	
	
	
	/**
	 * getLengthDifference()
	 * 
	 * Purpose: Compares the current length of the motor with the its
	 * 		initial length and returns the difference. Used in l* calculation
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The difference between the initial and current 
	 * 		grain length.
	**/
	
	abstract public double getLengthDifference();
	
} // abstract class Grain
