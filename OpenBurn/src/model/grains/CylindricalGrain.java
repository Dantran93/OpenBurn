package model.grains;

/**
 * CylindricalGrain.java
 * 
 * Purpose: Represents a cylindrical grain, as opposed to other
 * 		types of grains whose geometry differs.
**/

public class CylindricalGrain extends Grain
{
	/**
	 * CylindricalGrain Constructor
	 * 
	 * Purpose: Initializes and creates a CylindricalGrain by invoking
	 * 		the Grain super-constructor.
	**/
	
	public CylindricalGrain (double length, double outerDiameter, double innerDiameter, int numBurningEnds)
	{
		super(length, outerDiameter, innerDiameter, numBurningEnds);
	} // CylindricalGrain Constructor
	
	
	
	/**
	 * getPropellantDensity()
	 * 
	 * Purpose: Returns the propellant density.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The propellant density.
	**/
	
	@Override
	public double getPropellantDensity () 
	{
		return this.propellantDensity;
	} // getPropellantDensity()
	
	
	
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
	
	@Override
	public void setPropellantDensity (double density)
	{
		// Density must not be negative
		if (density <= 0.0)
			throw new IllegalArgumentException(PROP_DENSITY_ERR_MSG);
		
		// Set density
		propellantDensity = density;
	}
	
	
	
	/**
	 * getLength()
	 * 
	 * Purpose: Returns the length of the grain.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The length of the grain.
	**/
	
	@Override
	public double getLength () 
	{
		return this.length;
	}
	
	
	
	/**
	 * getOuterDiameter()
	 * 
	 * Purpose: Returns the outer diameter of the grain.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The outer diameter of the grain.
	**/
	
	@Override
	public double getOuterDiameter () 
	{
		return this.outerDiameter;
	}
	
	
	
	/**
	 * getInnerDiameter()
	 * 
	 * Purpose: Returns the inner diameter of the grain.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The inner diameter of the grain.
	**/
	
	@Override
	public double getInnerDiameter () 
	{
			return innerDiameter;
	}
	
	
	
	/**
	 * getNumBurningEnds()
	 * 
	 * Purpose: Returns the number of burning ends.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: int. The number of burning ends.
	**/
	
	@Override
	public int getNumBurningEnds () 
	{
		return numBurningEnds;
	}
	
	
	
	/**
	 * getBurnoutTime()
	 * 
	 * Purpose: Returns the burnout time.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The burnout time.
	**/
	
	@Override
	public double getBurnoutTime () 
	{
		return burnoutTime;
	}
	
	
	
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
	
	@Override
	public void setBurnoutTime (double burnoutTime) 
	{
		this.burnoutTime = burnoutTime;
	} //
	
	
	
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
	
	@Override
	public boolean isBurning ()
	{
		return isBurning;
	}
	
	
	
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
	
	@Override
	public double getVolume () 
	{
		// Use radius instead of diameter for volume calculation
		double innerRadius = innerDiameter / 2;
		double outerRadius = outerDiameter / 2;
		
		// Calculate and return the volume of the grain using inner properties
		return Math.PI * length * (outerRadius * outerRadius - innerRadius * innerRadius );
	} // getVolume()
	
	
	
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
	
	@Override
	public double getBurnArea () 
	{
		double innerRadius = innerDiameter / 2;
		double outerRadius = outerDiameter / 2;
		
		double face = Math.PI * numBurningEnds * (outerRadius * outerRadius - innerRadius * innerRadius );
		double innerBurnArea = 2 * Math.PI * innerRadius * length;
		double surfaceArea = face + innerBurnArea;
		
		// Guarantees we never have negative surface area.
		// Based on motor_internal_balistics.m, line 136
		return Math.max(surfaceArea, 0); 					 
	}
	
	
	
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
	
	@Override
	public double updateGeometry (double burnRate, double deltaTime)
	{
		// Get current volume for initial value
		double initialVolume = getVolume();
		
		// Calculate new value for length, ensure it never becomes negative
		length = length - numBurningEnds * burnRate * deltaTime;
		length = Math.max(length, 0);
		
		// Calculate new value for inner diameter, take the smallest diameter
		// of the inner and outer diameter
		innerDiameter = innerDiameter + 2 * burnRate * deltaTime;
		innerDiameter = Math.min(innerDiameter, outerDiameter);
		
		// Get new value for the volume after calculations are complete
		double newVolume = getVolume();
		
		// Error check for negative volume change, volume should expand
		if (newVolume > initialVolume)
			throw new ArithmeticException(NEGATIVE_VOLUME_ERR_MSG);
		if (innerDiameter == outerDiameter)
		{
			this.isBurning = false;
		}
		// Return the change in volume
		return (initialVolume - newVolume);
	} // updateGeometry()

	
	
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
	
	@Override
	public double getCurrentInnerFlowArea () 
	{
		return Math.PI * Math.pow(this.innerDiameter / 2, 2);   // Area = pi*(d/2)^2
	}
	
	
	
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
	
	@Override
	public double getCurrentInnerFlowVolume () 
	{
		return this.getCurrentInnerFlowArea() * this.length;
	}
	
	
	
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
	
	@Override
	public double getLengthDifference ()
	{
		return this.initialLength - this.length;
	}
	
} // class CylindricalGrain
