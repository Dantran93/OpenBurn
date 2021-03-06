package view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import model.calculations.SimulationResults;

/**
 * CSVConverter.java
 * 
 * The CSVConverter class can be used to convert data in an array into a csv file.
 * There are two methods, one for writing SimulationResults, the other for primitive ints.
**/

public class CSVConverter
{
	// Error messages
	private static final String CSV_ERROR_MSG = "ERROR: File must have .csv extension!\n";
	private static final String ARR_ERROR_MSG = "ERROR: Given array is null!\n";
	private static final String DIR_ERROR_MSG = "ERROR: Results folder could not be created!\n";
	
	// Other strings
	private static final String CSV_EXTENSION = ".csv";
	private static final String RESULTS_DIRECTORY = "./../OpenBurn_results/";
	
	
	
	/**
	 * writeResultsArr()
	 * 
	 * Purpose: This method takes an array of Objects and writes them to the 
	 * 		specified file in the second parameter in the form of a CSV file.
	 *     
	 * Parameters:
	 * 		List<SimulationResults> theResults -- List of simulation results to
	 * 			write to the CSV file.
	 * 		String filename -- Name of the file to write to. Must end with .csv.
	 * 
	 * Returns: void.
	**/
	
	public static void writeResultsArr (List<SimulationResults> theResults, File file)
	{
		// Create the directory to send the results file to
		// Throw exception if directory was not created
		//File resultsDir = new File(RESULTS_DIRECTORY);
		//if (!resultsDir.exists())
		//	if (resultsDir.mkdir() == false)
		//		throw new RuntimeException(DIR_ERROR_MSG);
		
		// Create the file writer
		BufferedWriter fileWriter = null;
		try {
			fileWriter = new BufferedWriter(new FileWriter(file));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// Go through entire array and build the string to write to file
		StringBuilder sb = new StringBuilder();
		sb.append(theResults.get(0).getLabels());
		for (Object element : theResults)
		{
			sb.append(element.toString());
			sb.append("\n");   // Append a comma to separate into different cells
		}

		// Write the built up string to the CSV file, and close the file
		try {
			fileWriter.write(sb.toString());
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	} // writeResultsArr()
	
	
	
	/**
	 * writeIntArr()
	 * 
	 * Purpose: This method takes an array of ints and writes them to the 
	 * 		specified file in the second parameter in the form of a csv file.
	 * 
	 * Parameters:
	 * 		int[] arr -- The array of integers to write to the CSV file.
	 * 		String filename -- Name of the file to write to. Must end with .csv.
	 * 
	 * Returns: void
	**/
	
	public void writeIntArr (int[] arr, String filename)
	{
		// Check if given arguments are valid
		errorCheckArgs(arr, filename);
		
		// Create the directory to send the results file to
		// Throw exception if directory was not created
		File resultsDir = new File(RESULTS_DIRECTORY);
		if (!resultsDir.exists())
			if (resultsDir.mkdir() == false)
				throw new RuntimeException(DIR_ERROR_MSG);
		
		// Create the file writer
		BufferedWriter fileWriter = null;
		try {
			fileWriter = new BufferedWriter(new FileWriter(filename));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// Go through entire array and build the string to write to file
		StringBuilder sb = new StringBuilder();
		for (int element : arr) {
		 sb.append(element);
		 sb.append(",");
		}

		// Write the built up string to the CSV file, and close the file
		try {
			fileWriter.write(sb.toString());
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // writeIntArr()
	
	
	
	/**
	 * errorCheckArgs()
	 * 
	 * Purpose: Checks to given arguments for the methods above.
	 * 
	 * 		Throws an IllegalArgumentException if the given array is null or
	 * 		the filename does not end with .csv.
	 * 
	 * Parameters:
	 * 		Object arr -- The given array, whatever the type may be.
	 * 		String filename -- Name of the file to write to. Must end with .csv.
	 * 
	 * Returns: void.
	**/
	
	private static void errorCheckArgs (Object arr, String filename)
	{
		// Error check that the file to write to is a CSV file
		if (!filename.endsWith(CSV_EXTENSION))
			throw new IllegalArgumentException(CSV_ERROR_MSG);
						
		// Error check that the given array is not null
		if (arr == null)
			throw new IllegalArgumentException(ARR_ERROR_MSG);
	} // errorCheckArgs()
	
} // class CSVConverter
