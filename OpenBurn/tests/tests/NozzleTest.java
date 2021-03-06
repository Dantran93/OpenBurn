package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Nozzle;

/**
 * NozzleTest.java
 * 
 * Purpose: Contains unit testing for Nozzle.
**/

public class NozzleTest
{
	// Tolerance constant for double testing
	private static final double TOLERANCE = 0.01;
	
	
	
	// Test cases
	private Nozzle test1 = new Nozzle(2.0, 1.0, 1.0, 0.5, 2);
	
	
	
	@Test
	public void testGetThroatDiameter ()
	{
		assertEquals(test1.getThroatDiameter(), 2.0, TOLERANCE);
	} // testGetThroatDiameter()
	
	
	
	@Test
	public void testGetExitDiameter ()
	{
		assertEquals(test1.getExitDiameter(), 1.0, TOLERANCE);
	} // testGetExitDiameter()
	
	
	
	@Test
	public void testGetCf ()
	{
		assertEquals(test1.getCf(), 0.5, TOLERANCE);
	} // testGetCf()
	
	
	
	@Test
	public void testGetEntranceID ()
	{
		assertEquals(test1.getEntranceID(), 3);
	} // testGetCf()
	
	
	
	@Test
	public void testGetThroatID ()
	{
		assertEquals(test1.getThroatID(), 4);
	} // testGetThroatID()
	
	
	
	@Test
	public void testGetThroatArea ()
	{
		assertEquals(test1.getThroatArea(), 3.141592653589793, TOLERANCE);
	} // testGetThroatID()
	
	
	
	@Test
	public void testGetEntranceArea ()
	{
		assertEquals(test1.getEntranceArea(), 0.7853981633974483, TOLERANCE);
	} // testGetThroatID()
	
} // test NozzleTest
