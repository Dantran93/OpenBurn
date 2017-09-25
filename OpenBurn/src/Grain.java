/* Holds data about the individual grain
 * 
 * 
 * 
 */
public class Grain
{
	private static float propellantDensity; // pounds per inches^3

	private float outerDiameter; // inches
	private float length; // inches
	private float innerDiameter; // inches
	private int burningEnds; // 0,1 or 2
	private float burnout;  // the time that this grain burned out
	
	public Grain(float outerDiameter, float length, float innerDiameter, int burningEnds)
	{
		if(outerDiameter <= 0)
		{
			throw new IllegalArgumentException("outer diameter must be positive");
		}
		if(innerDiameter <= 0)
		{
			throw new IllegalArgumentException("inner diameter must be positive");
		}
		if(innerDiameter >= outerDiameter)
		{
			throw new IllegalArgumentException("outer diameter must be greater than inner diameter");
		}
		if(length < 0)
		{
			throw new IllegalArgumentException("length must be positive");
		}
		if(burningEnds < 0 || burningEnds > 2)
		{
			throw new IllegalArgumentException("burning ends must be 0, 1, or 2");
		}
		
		this.outerDiameter = outerDiameter;
		this.length = length;
		this.innerDiameter = innerDiameter;
		this.burningEnds = burningEnds;
		setBurnout(0);
	}
	
	public static void setPropelentDensity(float density)
	{
		if(density <= 0)
		{
			throw new IllegalArgumentException("propellent density must be positive");
		}
		propellantDensity = density;
	}

	public static float getPropellantDensity()
	{
		return propellantDensity;
	}

	public float getOuterDiameter()
	{
		return outerDiameter;
	}
	
	public float getLength()
	{
		return length;
	}
	
	public float getInnerDiameter()
	{
		return innerDiameter;
	}
	
	public int getBurningEnds()
	{
		return burningEnds;
	}

	public float getBurnout()
	{
		return burnout;
	}

	public void setBurnout(float burnout)
	{
		this.burnout = burnout;
	}
	
	// returns the volume of the grain
	// based off of cylindrical_grain_volume.m
	public double getVolume()
	{
		float innerRadius = innerDiameter/2;
		float outerRadius = outerDiameter/2;
		
		return Math.PI * length * (outerRadius * outerRadius - innerRadius * innerRadius );
	}
	
	// returns the burnable surface area for the grain
	// based off of cylindrical_grain_burn_area.m
	public double getBurnArea()
	{
		float innerRadius = innerDiameter/2;
		float outerRadius = outerDiameter/2;
		
		double face = Math.PI * burningEnds * (outerRadius * outerRadius - innerRadius * innerRadius );
		double innerBurnArea = 2 * Math.PI * innerRadius * length;
		double surfaceArea = face + innerBurnArea;
		return Math.max(surfaceArea, 0); // guarantees we never have negative surface area.
										 // based off of motor_internal_balistics.m line 136
	}
	
	// take in burn rate and delta time
	// updates the grain's geometry
	// return the change in volume
	// based off of cylindrical_grain_geomtetry_update.m
	public double updateGeometry(float burnRate, float deltaTime)
	{
		double initialVolume = getVolume();
		length = length - burningEnds * burnRate * deltaTime;
		innerDiameter = innerDiameter + 2*burnRate*deltaTime;
		length = Math.max(length, 0);
		innerDiameter = Math.min(innerDiameter, outerDiameter);
		double newVolume = getVolume();
		if(newVolume > initialVolume)
		{
			throw new ArithmeticException("Negative Volume change occured");
		}
		return initialVolume - newVolume;
	}
}
