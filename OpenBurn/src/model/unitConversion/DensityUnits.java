package model.unitConversion;

public enum DensityUnits
{
	GRAMS_PER_CUBIC_CENTIMETER ("g/cm^3"),
	POUNDS_MASS_PER_CUBIC_FOOT ("lbm/ft^3"),
	POUNDS_MASS_PER_CUBIC_INCH ("lbm/in^3"),
	GRAMS_PER_CUBIC_INCH ("g/in^3"),
	KILOGRAMS_PER_CUBIC_METER ("kg/m^3");
	
	
	private String abbr;
	
	private DensityUnits (String abbr)
	{
		this.abbr = abbr;
	}

	public String getAbbr() {
		return abbr;
	}
}
