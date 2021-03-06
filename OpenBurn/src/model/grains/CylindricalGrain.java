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
		super(GrainType.Cylindrical, length, outerDiameter, innerDiameter, numBurningEnds);
	} // CylindricalGrain Constructor
	
	
	
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
	} // getBurnArea()
	
	
	
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
	} // getCurrentInnerFlowArea()
	
	
	
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
	} // getCurrentInnerFlowVolume()
	
	
	
	/**
	 * clone()
	 * 
	 * Purpose: Creates and returns a copy of this CylindricalGrain.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: Grain. A new CylindricalGrain object that represents a copy
	 * 		of this CylindricalGrain.
	**/
	
	public Grain clone ()
	{
		return new CylindricalGrain(this.length, this.outerDiameter, this.innerDiameter, this.numBurningEnds);
	} // clone()
	
} // class CylindricalGrain
