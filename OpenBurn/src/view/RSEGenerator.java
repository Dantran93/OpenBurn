
package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import model.Case;
import model.calculations.SimulationSummary;
import model.Nozzle;
import model.calculations.SimulationResults;

import model.unitConversion.*;

/**
 * RSEGenerator.java
 * 
 * Purpose: 
 **/

public class RSEGenerator
{
	/**
	 * RSEGenerator Constructor
	 * 
	 * Purpose: 
	 **/
	private static final String DIR_ERROR_MSG = "ERROR: Results folder could not be created!\n";

	// Other strings
	private static final String RESULTS_DIRECTORY = "./../OpenBurn_results/";

	public RSEGenerator(String teamName, List <SimulationResults> results, Case c, SimulationSummary classifier, Nozzle no)
	{
		String classification = "Test";//classifier.getClassification(); // TODO
		double isp = classifier.ISP();
		double massFrac = classifier.getMassFrac();
		double diameter = UnitConverter.convertLengthfromInternal(c.getDiameter(), LengthUnits.MILLIMETERS);
		double mass = UnitConverter.convertMassFromInternal(results.get(0).getSystemMass(), MassUnits.GRAMS);
		double burnTime = classifier.getBurnTime();
		double throatDiameter = UnitConverter.convertLengthfromInternal(no.getThroatDiameter(), LengthUnits.MILLIMETERS);
		double exitDiameter = UnitConverter.convertLengthfromInternal(no.getExitDiameter(), LengthUnits.MILLIMETERS);
		double caseMass = UnitConverter.convertMassFromInternal(c.getCaseMass(), MassUnits.GRAMS);
		double length = UnitConverter.convertLengthfromInternal(c.getLength(), LengthUnits.MILLIMETERS);
		double impulse = classifier.getImpulse();
		double averageThrust = UnitConverter.convertForceFromInternal(classifier.getaverageThrust(), ForceUnits.NEWTONS);
		//reloadable =0; //0;

		File resultsDir = new File(RESULTS_DIRECTORY);
		if (!resultsDir.exists())
			if (resultsDir.mkdir() == false)
				throw new RuntimeException(DIR_ERROR_MSG);

		File file = new File(RESULTS_DIRECTORY+"results.rse");
		//file.getParentFile().mkdirs();

		try {
			PrintWriter printWriter = new PrintWriter(file);

			//Header stuff

			printWriter.println("<engine-database>");
			printWriter.println("  <engine-list>");

			//Engine data
			printWriter.format("    <engine  mfg=\"%s\" ", teamName);
			printWriter.format("code=\"%s\" ",classification);
			printWriter.print("Type=\"reloadable\" "); //Hard coded
			printWriter.format("dia=\"%.0f.\" ",diameter);
			printWriter.format("len=\"%.0f.\"\n",length);
			printWriter.format("initWt=\"%.1f\" ", mass);
			printWriter.format("propWt=\"%.2f\" ",mass-caseMass);
			printWriter.println("delays=\""+1000+"\" auto-calc-mass=\"1\"");//Defaults
			printWriter.print("auto-calc-cg=\"1\" ");
			printWriter.format("avgThrust=\"%.2f\" ",averageThrust); //mean
			printWriter.print("peakThrust=\""+results.get(0).getThrust()+"\" "); //max
			printWriter.println("throatDia=\""+throatDiameter+"\"");
			printWriter.print("exitDia=\""+exitDiameter+"\" ");
			printWriter.print("Itot=\""+impulse+".\" "); // added null value
			printWriter.print("burn-time=\""+burnTime+"\" "); //+time(length(time))+ original code
			printWriter.print("massFrac=\""+massFrac+"\" ");
			printWriter.println("Isp=\""+isp+"\"");
			printWriter.println("tDiv=\"20\" tStep=\"-1.\" tFix=\"1\" FDiv=\"20\" FStep=\"-1.\" FFix=\"1\" mDiv=\"10\""); // All hard coded values
			printWriter.print("mStep=\"-1.\" mFix=\"1\" cgDiv=\"10\" cgStep=\"-1.\" cgFix=\"1\">"); 
			printWriter.println("    <data>");

			//Need to remove the case mass to get the propellant mass
			mass = mass-caseMass;

			//Time dependant data
			for (int i = 0; i < burnTime;i++){ // length(time)
				printWriter.print("      <eng-data  ");
				printWriter.print("t=\""+results.get(i).getTime()+"\" ");
				printWriter.print("f=\""+results.get(i).getThrust()+"\" ");
				printWriter.print("m=\""+results.get(i).getSystemMass()+"\" ");
				printWriter.println("cg=\""+results.get(i).getCg()+"\"/>"); //?
			}

			//File close and cleanup
			printWriter.println("    </data>");
			printWriter.println("  </engine>");
			printWriter.println("</engine-list>");
			printWriter.println("</engine-database>");
			printWriter.close();
		}
		catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	} 
} 