package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.grains.CylindricalGrain;
import view.CaseInputView;
import view.GrainInputView;
import view.GraphView;
import view.NozzleInputView;

/**
 * OpenBurnGUI.java
 * 
 * Purpose: Main class for the graphical user interface of OpenBurn.
**/

public class OpenBurnGUI extends Application
{
	// Labels
	private static final String WINDOW_TITLE      = "OpenBurn: Iteration 2";
	private static final String DENSITY_PROMPT    = "Enter propellant density";
	private static final String TIME_DELTA_PROMPT = "Enter change in time";
	private static final String SIMULATE          = "Simulate";
	private static final String EXPORT_CSV        = "Export to CSV";
	private static final String EXPORT_RSE        = "Export to RSE";
	private static final String ICON_FILE_PATH    = "./../images/OpenBurnLogo_1.png";
	
	
	
	// Constants
	private static final int WINDOW_WIDTH   = 1200;
	private static final int WINDOW_HEIGHT  = 900;
	private static final int GRAIN_INPUT_X  = 230;
	private static final int GRAIN_INPUT_Y  = 10;
	private static final int NOZZLE_INPUT_X = 755;
	private static final int NOZZLE_INPUT_Y = 10;
	private static final int CASE_INPUT_X   = 975;
	private static final int CASE_INPUT_Y   = 10;
	private static final int GRAPH_WIDTH    = 1150;
	private static final int GRAPH_HEIGHT   = 450;
	private static final int GRAPH_X        = 20;
	private static final int GRAPH_Y        = 380;
	
	
	
	// Components
	private Text propDensityText;
	private TextField propDensityTextField;
	
	private Text timeDeltaText;
	private TextField timeDeltaTextField;
	
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
		// Add window icon
		stage.getIcons().add(new Image(this.getClass().getResourceAsStream(ICON_FILE_PATH)));
		
		// Initialize primary window
		Pane root = new Pane();
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		stage.setScene(scene);
		stage.setTitle(WINDOW_TITLE);
		stage.setResizable(false);
		Pane frame = new Pane();
		scene.setRoot(frame);
		
		// Propellant Density input
		addPropDensityInput(frame);
		
		// Change in Time input
		addTimeDeltaInput(frame);
		
		// Grain input table
		grainInputs = new GrainInputView();
		grainInputs.setTranslateX(GRAIN_INPUT_X);
		grainInputs.setTranslateY(GRAIN_INPUT_Y);
		frame.getChildren().add(grainInputs);
		
		// TEMPORARY: Adding a sample input
		grainInputs.addRow(new CylindricalGrain(2.0, 3.0, 2.0, 2));
		grainInputs.addRow(new CylindricalGrain(5.0, 2.0, 1.0, 0));
		grainInputs.addRow(new CylindricalGrain(2.0, 3.0, 2.0, 2));
		grainInputs.addRow(new CylindricalGrain(5.0, 2.0, 1.0, 0));
		grainInputs.addRow(new CylindricalGrain(2.0, 3.0, 2.0, 2));
		grainInputs.addRow(new CylindricalGrain(5.0, 2.0, 1.0, 0));
		grainInputs.addRow(new CylindricalGrain(2.0, 3.0, 2.0, 2));
		grainInputs.addRow(new CylindricalGrain(5.0, 2.0, 1.0, 0));
		grainInputs.addRow(new CylindricalGrain(2.0, 3.0, 2.0, 2));
		grainInputs.addRow(new CylindricalGrain(5.0, 2.0, 1.0, 0));
		grainInputs.addRow(new CylindricalGrain(2.0, 3.0, 2.0, 2));
		grainInputs.addRow(new CylindricalGrain(5.0, 2.0, 1.0, 0));
		grainInputs.addRow(new CylindricalGrain(2.0, 3.0, 2.0, 2));
		grainInputs.addRow(new CylindricalGrain(5.0, 2.0, 1.0, 0));
		grainInputs.addRow(new CylindricalGrain(2.0, 3.0, 2.0, 2));
		grainInputs.addRow(new CylindricalGrain(5.0, 2.0, 1.0, 0));
		grainInputs.addRow(new CylindricalGrain(2.0, 3.0, 2.0, 2));
		grainInputs.addRow(new CylindricalGrain(5.0, 2.0, 1.0, 0));
		grainInputs.addRow(new CylindricalGrain(2.0, 3.0, 2.0, 2));
		grainInputs.addRow(new CylindricalGrain(5.0, 2.0, 1.0, 0));
		
		// Nozzle Input fields
		nozzleInputs = new NozzleInputView();
		nozzleInputs.setTranslateX(NOZZLE_INPUT_X);
		nozzleInputs.setTranslateY(NOZZLE_INPUT_Y);
		frame.getChildren().add(nozzleInputs);
		
		// Case Input fields
		caseInputs = new CaseInputView();
		caseInputs.setTranslateX(CASE_INPUT_X);
		caseInputs.setTranslateY(CASE_INPUT_Y);
		frame.getChildren().add(caseInputs);
		
		// Graph, initially Thrust vs. Time
		outputGraph = new GraphView(GraphView.THRUST_LABEL + GraphView.VERSUS_LABEL + GraphView.TIME_LABEL,
				 					GraphView.TIME_LABEL,
				 					GraphView.THRUST_LABEL);
		outputGraph.getChart().setTranslateX(GRAPH_X);
		outputGraph.getChart().setTranslateY(GRAPH_Y);
		outputGraph.getChart().setPrefWidth(GRAPH_WIDTH);
		outputGraph.getChart().setPrefHeight(GRAPH_HEIGHT);
		frame.getChildren().add(outputGraph.getChart());
		
		// Simulate Button
		simButton = new Button(SIMULATE);
		simButton.setTranslateX(20);
		simButton.setTranslateY(850);
		simButton.setPrefHeight(35);
		simButton.setPrefWidth(100);
		simButton.setDisable(false);
		frame.getChildren().add(simButton);
		
		// Export to CSV button
		csvButton = new Button(EXPORT_CSV);
		csvButton.setTranslateX(200);
		csvButton.setTranslateY(850);
		csvButton.setPrefHeight(35);
		csvButton.setPrefWidth(130);
		csvButton.setDisable(false);
		frame.getChildren().add(csvButton);
		
		// Export to RSE button
		rseButton = new Button(EXPORT_RSE);
		rseButton.setTranslateX(340);
		rseButton.setTranslateY(850);
		rseButton.setPrefHeight(35);
		rseButton.setPrefWidth(130);
		rseButton.setDisable(false);
		frame.getChildren().add(rseButton);
		
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
	 * 		Pane frame -- The frame to add to.
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
		propDensityTextField = new TextField();
		propDensityTextField.setTranslateX(20);
		propDensityTextField.setTranslateY(40);
		frame.getChildren().add(propDensityTextField);
	} // addPropDensityInput()
	
	
	
	/**
	 * addTimeDeltaInput()
	 * 
	 * Purpose:
	 * 
	 * Parameters:
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
		timeDeltaTextField = new TextField();
		timeDeltaTextField.setTranslateX(20);
		timeDeltaTextField.setTranslateY(120);
		frame.getChildren().add(timeDeltaTextField);
	} // addTimeDeltaInput()
	
	
	
	
	
	
	
} // class OpenBurnGUI

