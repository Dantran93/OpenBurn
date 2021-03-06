package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import model.grains.*;

/**
 * GrainTest.java
 * 
 * Contains unit testing for the methods in Grain.java.
**/

public class GrainTest
{	
	// Tolerance constant for double testing
	private static final double TOLERANCE = 0.01;
	
	
	
	// Test cases
	private Grain cylindricalTest = new CylindricalGrain(5.0, 2.0, 1.0, 1);
	
	
	
	@Test (expected = IllegalArgumentException.class)
	public void testLengthConstructorException_Cylindrical ()
	{
		Grain errorTest = new CylindricalGrain(0.0, 2.0, 1.0, 2);
	}
	
	
	
	@Test (expected = IllegalArgumentException.class)
	public void testOuterDiameterConstructorException_Cylindrical ()
	{
		Grain errorTest = new CylindricalGrain(1.0, 0.0, 0.0, 1);
	} // testOuterDiameterConstructorException()

	
	
	@Test (expected = IllegalArgumentException.class)
	public void testInnerDiameterConstructorException_Cylindrical ()
	{
		Grain errorTest = new CylindricalGrain(1.0, 1.0, 0.0, 1);
	} // testInnerDiameterConstructorException()
	
	
	
	@Test (expected = IllegalArgumentException.class)
	public void testInnerGreaterThanOuterConstructorException_Cylindrical ()
	{
		Grain errorTest = new CylindricalGrain(1.0, 1.0, 2.0, 1);
	} // testInnerGreaterThanOuterConstructorException_Cylindrical()
	
	
	
	@Test (expected = IllegalArgumentException.class)
	public void testInnerSameAsOuterConstructorException_Cylindrical ()
	{
		Grain errorTest = new CylindricalGrain(1.0, 1.0, 1.0, 1);
	} // testInnerSameAsOuterConstructorException_Cylindrical()
	
	
	
	@Test (expected = IllegalArgumentException.class)
	public void testNegativeBurningEndsConstructorException_Cylindrical ()
	{
		Grain errorTest = new CylindricalGrain(1.0, 2.0, 1.0, -1);
	} // testNegativeBurningEndsConstructorException_Cylindrical()
	
	
	
	@Test (expected = IllegalArgumentException.class)
	public void testTooManyBurningEndsConstructorException_Cylindrical ()
	{
		Grain errorTest = new CylindricalGrain(1.0, 2.0, 1.0, 3);
	} // testTooManyBurningEndsConstructorException_Cylindrical()
	
	
	
	@Test
	public void testGetLength_Cylindrical ()
	{
		assertEquals(cylindricalTest.getLength(), 5.0, TOLERANCE);
	} // testGetLength_Cylindrical()
	
	
	
	@Test
	public void testGetNumBurningEnds_Cylindrical ()
	{
		assertEquals(cylindricalTest.getNumBurningEnds(), 1);
	} // testGetNumBurningEnds_Cylindrical()
	
} // test GrainTest
