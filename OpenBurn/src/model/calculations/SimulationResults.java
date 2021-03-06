package model.calculations;

/**
 * SimulationResults.java
 * 
 * Contains the data for a set of results from a grain simulation.
**/

public class SimulationResults
{
	// Constant string labels
	private static final String COMMA = ",";
	private static final String TIME = "Time (seconds)";
	private static final String MASS_OVERALL = "Mass Generated Overall (lbm)";
	private static final String MASS_PER_GRAIN = "Mass Generated by grain: ";
	private static final String PORT_TO_THROAT = "Port to Throat by port: ";
	private static final String MASS_FLOW_PER_GRAIN = "Mass Flow per grain: ";
	private static final String BURN_AREA = "Burn Area (in^2)";
	private static final String BURN_RATE = "Burn Rate (in /sec)";
	private static final String PRESSURE = "Pressure (psi)";
	private static final String KN = "KN ()";
	private static final String L_STAR = "L Star (in)";
	private static final String SYSTEM_MASS = "Mass of the System (lbm)";
	private static final String SYSTEM_CENTER_GRAVITY = "Center of Gravity (inches)";
	
	
	
	// Private fields for the results
	private double time;
	private double massGeneratedOverall;
	private double massGeneratedPerGrain[];
	private double portToThroat[];
	private double massFlowPerAreaGrain[];
	private double burnArea;
	private double burnRate;
	private double chamberPressure;
	private double kn;
	private double cg;
	private double lStar;
	private double systemMass;
	private double systemCenterOfGravity;
	private double thrust;
	
	
	/**
	 * equals()
	 * 
	 * Purpose: allows two simulation results to be compared
	 * 
	 * Parameters: Object -- the object to be compared to.  must be of type object because inheritance.
	 * 
	 * Returns: boolean reflecting the equality of two objects
	**/
	@Override
	public boolean equals(Object other)
	{
		if(other.getClass() == SimulationResults.class)
		{
			SimulationResults o = (SimulationResults) other;
			return (this.time == o.time) && (this.thrust == o.thrust) && (this.chamberPressure == o.chamberPressure);
		}
		
		return false; // if other is not a simulation result, than they are definitely not equal
	}
	
	/**
	 * getLabels()
	 * 
	 * Purpose: Returns a string with all the labels, separated by commas.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: String. All the labels separated by commas.
	**/
	
	public String getLabels()
	{
		StringBuilder output = new StringBuilder();
		
		output.append(TIME + COMMA);
		output.append(PRESSURE + COMMA);
		output.append(MASS_OVERALL + COMMA);
		
		for(int i = 0; i < massGeneratedPerGrain.length; i++)
			output.append(MASS_PER_GRAIN + i + COMMA);
		for(int i = 0; i < portToThroat.length; i++)
			output.append(PORT_TO_THROAT + i + COMMA);
		for(int i = 0; i < massFlowPerAreaGrain.length; i++)
			output.append(MASS_FLOW_PER_GRAIN + i + COMMA);
		
		output.append(BURN_AREA + COMMA);
		output.append(BURN_RATE + COMMA);
		output.append(KN + COMMA);
		output.append(L_STAR + COMMA);
		output.append(SYSTEM_MASS + COMMA);
		output.append(SYSTEM_CENTER_GRAVITY + "\n");   // no comma because it's the end of the line
		
		return output.toString();
	} // getLabels()
	
	
	
	/**
	 * toString()
	 * 
	 * Purpose: Returns the string representation of the simulation results.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: String. The string representation of the simulation results.
	**/
	
	@Override
	public String toString ()
	{
		StringBuilder output = new StringBuilder();
		
		output.append(time + COMMA);
		output.append(chamberPressure + COMMA);
		output.append(massGeneratedOverall + COMMA);
		
		for(int i = 0; i < massGeneratedPerGrain.length; i++)
			output.append(massGeneratedPerGrain[i] + COMMA);
		for(int i = 0; i < portToThroat.length; i++)
			output.append(portToThroat[i] + COMMA);
		for(int i = 0; i < massFlowPerAreaGrain.length; i++)
			output.append(massFlowPerAreaGrain[i] + COMMA);
		
		output.append(burnArea + COMMA);
		output.append(burnRate + COMMA);
		output.append(kn + COMMA);
		output.append(lStar + COMMA);
		output.append(systemMass + COMMA);
		output.append(systemCenterOfGravity); // no comma because it's the end of the line
		
		return output.toString();
	} // toString()
	
	
	
	/**
	 * getTime()
	 * 
	 * Purpose: Returns the time for the results.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The time for the results.
	**/
	
	public double getTime ()
	{
		return time;
	} // getTime()
	
	
	
	/**
	 * setTime()
	 * 
	 * Purpose: Changes the time of the results to a new value.
	 * 
	 * Parameters:
	 * 		double newTime -- New value to set the time.
	 * 
	 * Returns: void.
	**/
	
	public void setTime (double newTime)
	{
		this.time = newTime;
	} // setTime()
	
	
	
	/**
	 * getMassGeneratedOverall()
	 * 
	 * Purpose: Returns the mass generated overall.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. the mass generated overall.
	**/
	
	public double getMassGeneratedOverall ()
	{
		return massGeneratedOverall;
	} // getMassGeneratedOverall()
	
	
	
	/**
	 * setMassGeneratedOverall()
	 * 
	 * Purpose: Changes the mass generated overall to a new value.
	 * 
	 * Parameters:
	 * 		double newMassGeneratedOverall -- New value to set the
	 * 			mass generated overall.
	 * 
	 * Returns: void.
	**/
	
	public void setMassGeneratedOverall (double newMassGeneratedOverall)
	{
		this.massGeneratedOverall = newMassGeneratedOverall;
	} // setMassGeneratedOverall()
	
	
	
	/**
	 * getMassGeneratedPerGrain()
	 * 
	 * Purpose: Returns an array containing the mass generated for all
	 * 		of the grains in a simulation.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double[]. The mass generated for each grain in a simulation.
	**/
	
	public double[] getMassGeneratedPerGrain ()
	{
		return massGeneratedPerGrain;
	} // getMassGeneratedPerGrain()
	
	
	
	/**
	 * setMassGeneratedPerGrain()
	 * 
	 * Purpose: Changes the mass generated per grain for each grain to
	 * 		new values.
	 * 
	 * Parameters:
	 * 		double[] newMassGeneratedPerGrain -- New value to set the
	 * 			mass generated per grain, for each grain.
	 * 
	 * Returns: void.
	**/
	
	public void setMassGeneratedPerGrain (double[] newMassGeneratedPerGrain)
	{
		this.massGeneratedPerGrain = newMassGeneratedPerGrain;
	} // setMassGeneratedPerGrain()
	
	
	
	/**
	 * getPortToThroat()
	 * 
	 * Purpose: Returns the port-to-throat of each grain in a simulation.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double[]. The port-to-throat for each grain in a simulation.
	**/
	
	public double[] getPortToThroat ()
	{
		return portToThroat;
	} // getPortToThroat()
	
	
	
	/**
	 * setPortToThroat()
	 * 
	 * Purpose: Changes the port to throat values for each grain
	 * 		in a simulation to new values.
	 * 
	 * Parameters:
	 * 		double[] newPortToThroat -- New values to set the port-
	 * 			to-throat for each grain.
	 * 
	 * Returns: void.
	 **/
	
	public void setPortToThroat (double[] newPortToThroat)
	{
		this.portToThroat = newPortToThroat;
	} // setPortToThroat()
	
	
	
	/**
	 * getMassFlowPerAreaGrain()
	 * 
	 * Purpose: Returns an array that contains the mass flow per area
	 * 		grain for each grain in a simulation.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double[]. An array that contains the mass flow per area
	 * 		grain for each grain in a simulation.
	**/
	
	public double[] getMassFlowPerAreaGrain ()
	{
		return massFlowPerAreaGrain;
	} // getMassFlowPerAreaGrain()
	
	
	
	/**
	 * setMassFlowPerAreaGrain()
	 * 
	 * Purpose: Changes the mass flow per area grain for each grain to
	 * 		new values.
	 * 
	 * Parameters:
	 * 		double[] newMassPerAreaGrain -- New values to set the mass flow
	 * 			per area grain for each grain.
	 * 
	 * Returns: void.
	**/
	
	public void setMassFlowPerAreaGrain (double[] newMassFlowPerAreaGrain)
	{
		this.massFlowPerAreaGrain = newMassFlowPerAreaGrain;
	} // setMassFlowPerAreaGrain()
	
	
	
	/**
	 * getBurnArea()
	 * 
	 * Purpose: Returns the burn area of the results.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The burn area of the results.
	**/
	
	public double getBurnArea ()
	{
		return burnArea;
	} // getBurnArea()
	
	
	
	/**
	 * setBurnArea()
	 * 
	 * Purpose: Changes the motor available area of results to
	 * 		a new value.
	 * 
	 * Parameters:
	 * 		double newMotorAvailableArea -- New value to set
	 * 			the motor available area.
	 * 
	 * Returns: void.
	**/
	
	public void setBurnArea (double newMotorAvailableArea)
	{
		this.burnArea = newMotorAvailableArea;
	} // setBurnArea()
	
	
	
	/**
	 * getBurnRate()
	 * 
	 * Purpose: Returns the burn rate from the results.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The burn rate from the results.
	**/
	
	public double getBurnRate ()
	{
		return burnRate;
	} // getBurnRate()
	
	
	
	/**
	 * setBurnRate()
	 * 
	 * Purpose: Changes the burn rate of the results to a new value.
	 * 
	 * Parameters:
	 * 		double newBurnRate -- New value to set the burn rate.
	 * 
	 * Returns: void.
	**/
	
	public void setBurnRate (double newBurnRate)
	{
		this.burnRate = newBurnRate;
	} // setBurnRate()
	
	
	
	/**
	 * getChamberPressure()
	 * 
	 * Purpose: Returns the chamber pressure of the results.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The chamber pressure of the results.
	**/
	
	public double getChamberPressure ()
	{
		return chamberPressure;
	} // getChamberPressure()
	
	
	
	/**
	 * setChamberPressure()
	 * 
	 * Purpose: Changes the chamber pressure of the results
	 * 		to a new value.
	 * 
	 * Parameters:
	 * 		double newChamberPressure -- New value to set the
	 * 			chamber pressure.
	 * 
	 * Returns: void.
	**/
	
	public void setChamberPressure (double newChamberPressure)
	{
		this.chamberPressure = newChamberPressure;
	} // setChamberPressure()
	
	
	
	/**
	 * getKn()
	 * 
	 * Purpose: Returns the ratio of the burn area of the propellant
	 * 		to the area of the nozzle throat (kn). 
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The ratio of the burn area of the propellant
	 * 		to the area of the nozzle throat (kn). 
	**/
	
	public double getKn ()
	{
		return kn;
	} // getKn()
	
	
	
	/**
	 * setKn()
	 * 
	 * Purpose: Changes the kn of the results to a new value.
	 * 
	 * Parameters:
	 * 		double newKn -- New value to set the kn.
	 * 
	 * Returns: void.
	**/
	
	public void setKn (double newKn)
	{
		this.kn = newKn;
	} // setKn()

	
	public double getCg() {
		return cg;
	}



	public void setCg(double cg) {
		this.cg = cg;
	}
	/**
	 * getLStar()
	 * 
	 * Purpose: Returns the characteristic length (L*) of the results.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The characteristic length (L*) of the results.
	**/
	
	public double getLStar ()
	{
		return lStar;
	} // getLStar()
	
	
	
	/**
	 * setLStar()
	 * 
	 * Purpose: Changes the characteristic length (L*) of the results
	 * 		to a new value.
	 * 
	 * Parameters:
	 * 		newLStar -- New value to set the characteristic length (L*).
	 * 
	 * Returns: void.
	**/
	
	public void setLStar (double newLStar)
	{
		this.lStar = newLStar;
	} // setLStar()
	
	
	
	/**
	 * getSystemMass()
	 * 
	 * Purpose: Returns the system mass of the results.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The system mass of the results.
	**/
	
	public double getSystemMass ()
	{
		return systemMass;
	} // getSystemMass()
	
	
	
	/**
	 * setSystemMass()
	 * 
	 * Purpose: Changes the system mass to a new value.
	 * 
	 * Parameters:
	 * 		double newSystemMass -- New value to set the system mass.
	 * 
	 * Returns: void.
	**/
	
	public void setSystemMass (double newSystemMass)
	{
		this.systemMass = newSystemMass;
	} // setSystemMass()

	
	
	/**
	 * getSystemCenterOfGravity()
	 * 
	 * Purpose: Returns the system center of gravity for the results.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The system center of gravity for the results.
	**/
	
	public double getSystemCenterOfGravity ()
	{
		return systemCenterOfGravity;
	} // getSystemCenterOfGravity()

	
	
	/**
	 * setSystemCenterOfGravity()
	 * 
	 * Purpose: Changes the system center of gravity to a new value.
	 * 
	 * Parameters:
	 * 		double newSystemCenterOfGravity -- New value to set the system
	 * 			center of gravity.
	 * 
	 * Returns: void.
	**/
	
	public void setSystemCenterOfGravity (double newSystemCenterOfGravity)
	{
		this.systemCenterOfGravity = newSystemCenterOfGravity;
	} // setSystemCenterOfGravity()

	
	/**
	 * getThrust()
	 * 
	 * Purpose: Returns the system thrust for the results.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The system thrust for the results.
	**/
	
	public double getThrust ()
	{
		return thrust;
	} // getThrust()

	
	/**
	 * setThrust()
	 * 
	 * Purpose: Changes the system thrust to a new value.
	 * 
	 * Parameters:
	 * 		double thrust -- New value to set the system thrust
	 * 
	 * Returns: void.
	**/
	
	public void setThrust (double thrust)
	{
		this.thrust = thrust;
	} // setThrust()
	
} // class SimulationResults
