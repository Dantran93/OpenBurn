package controller;

import java.util.List;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Case;
import model.Nozzle;
import model.NumberTextField;
import model.calculations.RocketMath;
import model.calculations.SimulationResults;
import model.grains.Grain;
import view.CSVConverter;
import view.CaseInputView;
import view.GraphView;
import view.NozzleInputView;
import view.grain.input.GrainInputView;

/**
 * OpenBurnGUI.java
 * 
 * Purpose: Main class for the graphical user interface of OpenBurn.
**/

public class OpenBurnGUI extends Application
{
	// Labels
	private static final String WINDOW_TITLE      = "OpenBurn - Iteration 2";
	private static final String DENSITY_PROMPT    = "Enter propellant density";
	private static final String TIME_DELTA_PROMPT = "Enter change in time";
	private static final String SIMULATE          = "Simulate";
	private static final String EXPORT_CSV        = "Export to CSV";
	private static final String EXPORT_RSE        = "Export to RSE";
	private static final String ICON_FILE_PATH    = "./../images/OpenBurnLogo_1.png";
	
	private static final String TEMP_LEGEND_NAME = "Sim1";
	
	
	
	// Constants
	private static final int WINDOW_WIDTH   = 1200;
	private static final int WINDOW_HEIGHT  = 820;
	private static final int GRAIN_INPUT_X  = 230;
	private static final int GRAIN_INPUT_Y  = 10;
	private static final int NOZZLE_INPUT_X = 755;
	private static final int NOZZLE_INPUT_Y = 10;
	private static final int CASE_INPUT_X   = 975;
	private static final int CASE_INPUT_Y   = 10;
	private static final int GRAPH_WIDTH    = 1150;
	private static final int GRAPH_HEIGHT   = 450;
	private static final int GRAPH_X        = 20;
	private static final int GRAPH_Y        = 330;
	
	
	
	// Components
	private Text propDensityText;
	private Text timeDeltaText;
	private NumberTextField propDensityTextField;
	private NumberTextField timeDeltaTextField;
	private GrainInputView grainInputs;
	private NozzleInputView nozzleInputs;
	private CaseInputView caseInputs;
	private GraphView outputGraph;
	private Button simButton;
	private Button csvButton;
	private Button rseButton;
	
	
	
	/**
	 * main()
	 * 
	 * Purpose: Runs the GUI for OpenBurn.
	 * 
	 * Parameters:
	 * 		String[] args -- Command-line arguments, not used.
	 * 
	 * Returns: void.
	**/
	
	public static void main (String[] args)
	{
		launch(args);
	} // main()
	
	
	
	/**
	 * start()
	 * 
	 * Purpose: Creates and initializes all OpenBurn GUI components
	 * 		for the primary window.
	 * 
	 * Parameters:
	 * 		Stage stage -- Primary window of the GUI.
	 * 
	 * Returns: void.
	**/
	
	@Override
	public void start (Stage stage)
	{
		// Initialize primary window
		Pane root = new Pane();
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		stage.setScene(scene);
		stage.setTitle(WINDOW_TITLE);
		stage.getIcons().add(new Image(this.getClass().getResourceAsStream(ICON_FILE_PATH)));
		stage.setResizable(false);
		Pane frame = new Pane();
		scene.setRoot(frame);
		
		// Initialize and set components
		addPropDensityInput(frame);
		addTimeDeltaInput(frame);
		addGrainTable(frame);
		addNozzleInputs(frame);
		addCaseInputs(frame);
		addGraph(frame);            // Initially Thrust vs. Time
		addSimulateButton(frame);
		addExportButtons(frame);    // CSV and RSE
		
		// Display window
		stage.show();
	} // start()
	
	
	
	/**
	 * addPropDensityInput()
	 * 
	 * Purpose: Adds the propellant density input fields to
	 * 		to the given Pane.
	 * 
	 * Parameters:
	 * 		Pane frame -- The Pane to add to.
	 * 
	 * Returns: void.
	**/
	
	private void addPropDensityInput (Pane frame)
	{
		// Prompt
		propDensityText = new Text(DENSITY_PROMPT);
		propDensityText.setTranslateX(20);
		propDensityText.setTranslateY(30);
		frame.getChildren().add(propDensityText);
		
		// Input field
		propDensityTextField = new  NumberTextField();
		propDensityTextField.setTranslateX(20);
		propDensityTextField.setTranslateY(40);
		frame.getChildren().add(propDensityTextField);
	} // addPropDensityInput()
	
	
	
	/**
	 * addTimeDeltaInput()
	 * 
	 * Purpose: Adds the change in time input fields to
	 * 		to the given Pane.
	 * 
	 * Parameters:
	 * 		Pane frame -- The Pane to add to.
	 * 
	 * Returns: void.
	**/
	
	private void addTimeDeltaInput (Pane frame)
	{
		// Prompt
		timeDeltaText = new Text(TIME_DELTA_PROMPT);
		timeDeltaText.setTranslateX(20);
		timeDeltaText.setTranslateY(110);
		frame.getChildren().add(timeDeltaText);
		
		// Input field
		timeDeltaTextField = new NumberTextField();
		timeDeltaTextField.setTranslateX(20);
		timeDeltaTextField.setTranslateY(120);
		frame.getChildren().add(timeDeltaTextField);
	} // addTimeDeltaInput()
	
	
	
	/**
	 * addGrainTable()
	 * 
	 * Purpose: Adds the grain input table and buttons
	 * 		to the given Pane.
	 * 
	 * Parameters:
	 * 		Pane pane -- The Pane to add to.
	 * 
	 * Returns: void.
	**/
	
	private void addGrainTable (Pane frame)
	{
		// Initialize table
		grainInputs = new GrainInputView();
		grainInputs.setTranslateX(GRAIN_INPUT_X);
		grainInputs.setTranslateY(GRAIN_INPUT_Y);
		frame.getChildren().add(grainInputs);
	} // addGrainTable()
	
	
	
	/**
	 * addNozzleInputs()
	 * 
	 * Purpose: Adds the nozzle input fields to
	 * 		the given Pane.
	 * 
	 * Parameters:
	 * 		Pane frame -- The Pane to add to.
	 * 
	 * Returns: void.
	**/
	
	private void addNozzleInputs (Pane frame)
	{
		// Initialize nozzle input view
		nozzleInputs = new NozzleInputView();
		nozzleInputs.setTranslateX(NOZZLE_INPUT_X);
		nozzleInputs.setTranslateY(NOZZLE_INPUT_Y);
		frame.getChildren().add(nozzleInputs);
	} // addNozzleInputs()
	
	
	
	/**
	 * addCaseInputs()
	 * 
	 * Purpose: Adds the case input fields to
	 * 		the given Pane.
	 * 
	 * Parameters:
	 * 		Pane frame -- The Pane the add to.
	 * 
	 * Returns: void.
	**/
	
	private void addCaseInputs (Pane frame)
	{
		// Initialize case input view
		caseInputs = new CaseInputView();
		caseInputs.setTranslateX(CASE_INPUT_X);
		caseInputs.setTranslateY(CASE_INPUT_Y);
		frame.getChildren().add(caseInputs);
	} // addCaseInputs()
	
	
	
	/**
	 * addGraph()
	 * 
	 * Purpose: Adds the graph (ScatterChart) to the given Pane.
	 * 
	 * Parameters:
	 * 		Pane frame -- The Pane to add to.
	 * 
	 * Returns: void.
	**/
	
	private void addGraph (Pane frame)
	{
		// Initialize graph, with Thrust vs. Time labels
		outputGraph = new GraphView(GraphView.THRUST_LABEL + GraphView.VERSUS_LABEL + GraphView.TIME_LABEL,
									GraphView.TIME_LABEL,
									GraphView.THRUST_LABEL);
		outputGraph.setTranslateX(GRAPH_X);
		outputGraph.setTranslateY(GRAPH_Y);
		outputGraph.getChart().setPrefWidth(GRAPH_WIDTH);
		outputGraph.getChart().setPrefHeight(GRAPH_HEIGHT);
		frame.getChildren().add(outputGraph);
	} // addGraph()
	
	
	
	/**
	 * addSimulateButton()
	 * 
	 * Purpose: Adds the simulate button to the given Pane.
	 * 
	 * Parameters:
	 * 		Pane frame -- The Pane to add to.
	 * 
	 * Returns: void.
	**/
	
	private void addSimulateButton (Pane frame)
	{
		// Initialize simulate button
		simButton = new Button(SIMULATE);
		simButton.setTranslateX(20);
		simButton.setTranslateY(780);
		simButton.setPrefHeight(35);
		simButton.setPrefWidth(100);
		simButton.setDisable(false);
		frame.getChildren().add(simButton);
		
		// Run simulation on click
		simButton.setOnAction(new EventHandler<ActionEvent> ()
		{
		    @Override public void handle (ActionEvent e)
		    {
		    	runSimulation();
		    }
		});
	} // addSimulateButton()
	
	
	
	/**
	 * runSimulation()
	 * 
	 * Purpose: Runs simulation once all fields are filled and
	 * 		the simulation button is pressed.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: void.
	**/
	
	private void runSimulation ()
	{
		// Gather grain list
		List<Grain> grainList = grainInputs.getTable().getItems();
		
		// Gather propellant density and change in time
    	double propDensity = Double.parseDouble(propDensityTextField.getText().toString());
    	double deltaTime = Double.parseDouble(timeDeltaTextField.getText().toString());
    	
    	// Use nozzle inputs to create nozzle
    	Nozzle theNozzle = new Nozzle(nozzleInputs.getThroatDiameterInput(),
    							   	  nozzleInputs.getEntranceDiameterInput(),
    							   	  nozzleInputs.getExitDiameterInput(),
    							   	  nozzleInputs.getCfInput(),
    							   	  grainInputs.getTable().getItems().size());
    	
    	// Use case inputs to create case
    	Case theCase = new Case(caseInputs.getMassInput(),
    							caseInputs.getDiameterInput(),
    							caseInputs.getLengthInput());
    	
    	// Run simulation, gather list of results
    	List<SimulationResults> simResults = RocketMath.simulate(grainList, deltaTime, theNozzle, theCase);
    	
    	// Add thrust vs. time data to the chart
    	outputGraph.addData(TEMP_LEGEND_NAME, simResults);
	} // runSimulation()
	
	
	
	/**
	 * addExportButtons()
	 * 
	 * Purpose: Adds the export buttons to the given Pane.
	 * 		Currently handles CSV and RSE.
	 * 
	 * Parameters:
	 * 		Pane frame -- The Pane to add to.
	 * 
	 * Returns: void.
	**/
	
	private void addExportButtons (Pane frame)
	{
		// Export to CSV button
		csvButton = new Button(EXPORT_CSV);
		csvButton.setTranslateX(200);
		csvButton.setTranslateY(780);
		csvButton.setPrefHeight(35);
		csvButton.setPrefWidth(130);
		csvButton.setDisable(false);
		frame.getChildren().add(csvButton);
		
		csvButton.setOnAction(new EventHandler<ActionEvent> ()
		{
		    @Override public void handle (ActionEvent e)
		    {
		    	CSVConverter.writeResultsArr(outputGraph.getData(), TEMP_LEGEND_NAME + ".csv");
		    }
		});
		
		// Export to RSE button
		rseButton = new Button(EXPORT_RSE);
		rseButton.setTranslateX(340);
		rseButton.setTranslateY(780);
		rseButton.setPrefHeight(35);
		rseButton.setPrefWidth(130);
		rseButton.setDisable(false);
		frame.getChildren().add(rseButton);
		
		// TEMPORARY: Disable buttons
		rseButton.setDisable(true);
	} // addExportButtons()
	
} // class OpenBurnGUI
