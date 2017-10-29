package controller;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.grains.CylindricalGrain;
import model.grains.Grain;
import view.CaseInputView;
import view.GrainInputView;
import view.GraphView;
import view.NozzleInputView;



public class OpenBurnGUI extends Application
{
	// GUI Labels
	private static final String WINDOW_TITLE = "OpenBurn: Iteration 2";
	
	private static final String DENSITY_PROMPT = "Enter propellant density";
	private static final String FILE_PROMPT    = "Enter the desired name of the CSV file (Don't include \".csv\"): ";
	
	private static final String TIME_DELTA_PROMPT = "Enter change in time";
	
	private static final String SIMULATE = "Simulate";

	private static final String EXPORT_CSV = "Export to CSV";
	private static final String EXPORT_RSE = "Export to RSE";
	
	
	
	// Constants
	private static final int WINDOW_WIDTH  = 1200;
	private static final int WINDOW_HEIGHT = 900;
	private static final int GRAIN_INPUT_X = 230;
	private static final int GRAIN_INPUT_Y = 10;
	private static final int NOZZLE_INPUT_X = 755;
	private static final int NOZZLE_INPUT_Y = 10;
	private static final int CASE_INPUT_X = 975;
	private static final int CASE_INPUT_Y = 10;
	private static final int GRAPH_WIDTH = 1150;
	private static final int GRAPH_HEIGHT = 450;
	private static final int GRAPH_X = 20;
	private static final int GRAPH_Y = 380;
	
	
	// 
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
	
	
	
	
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	
	
	@Override
	public void start(Stage stage)
	{
		Pane root = new Pane();
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		stage.setScene(scene);
		stage.setTitle(WINDOW_TITLE);
		stage.setResizable(false);

		Pane frame = new Pane();
		scene.setRoot(frame);
		
		propDensityText = new Text(DENSITY_PROMPT);
		propDensityText.setTranslateX(20);
		propDensityText.setTranslateY(30);
		frame.getChildren().add(propDensityText);
		
		propDensityTextField = new TextField();
		propDensityTextField.setTranslateX(20);
		propDensityTextField.setTranslateY(40);
		frame.getChildren().add(propDensityTextField);
		
		
		timeDeltaText = new Text(TIME_DELTA_PROMPT);
		timeDeltaText.setTranslateX(20);
		timeDeltaText.setTranslateY(110);
		frame.getChildren().add(timeDeltaText);
		
		timeDeltaTextField = new TextField();
		timeDeltaTextField.setTranslateX(20);
		timeDeltaTextField.setTranslateY(120);
		frame.getChildren().add(timeDeltaTextField);
		
		grainInputs = new GrainInputView();
		grainInputs.setTranslateX(GRAIN_INPUT_X);
		grainInputs.setTranslateY(GRAIN_INPUT_Y);
		frame.getChildren().add(grainInputs);
		
		// Adding a sample input
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
		
		nozzleInputs = new NozzleInputView();
		nozzleInputs.setTranslateX(NOZZLE_INPUT_X);
		nozzleInputs.setTranslateY(NOZZLE_INPUT_Y);
		frame.getChildren().add(nozzleInputs);
		
		caseInputs = new CaseInputView();
		caseInputs.setTranslateX(CASE_INPUT_X);
		caseInputs.setTranslateY(CASE_INPUT_Y);
		frame.getChildren().add(caseInputs);
		
		outputGraph = new GraphView(GraphView.THRUST_LABEL + GraphView.VERSUS_LABEL + GraphView.TIME_LABEL,
				 					GraphView.TIME_LABEL,
				 					GraphView.THRUST_LABEL);
		outputGraph.getChart().setTranslateX(GRAPH_X);
		outputGraph.getChart().setTranslateY(GRAPH_Y);
		outputGraph.getChart().setPrefWidth(GRAPH_WIDTH);
		outputGraph.getChart().setPrefHeight(GRAPH_HEIGHT);
		frame.getChildren().add(outputGraph.getChart());
		
		simButton = new Button(SIMULATE);
		simButton.setTranslateX(20);
		simButton.setTranslateY(850);
		simButton.setPrefHeight(35);
		simButton.setPrefWidth(100);
		simButton.setDisable(false);
		frame.getChildren().add(simButton);
		
		csvButton = new Button(EXPORT_CSV);
		csvButton.setTranslateX(200);
		csvButton.setTranslateY(850);
		csvButton.setPrefHeight(35);
		csvButton.setPrefWidth(130);
		csvButton.setDisable(false);
		frame.getChildren().add(csvButton);
		
		rseButton = new Button(EXPORT_RSE);
		rseButton.setTranslateX(340);
		rseButton.setTranslateY(850);
		rseButton.setPrefHeight(35);
		rseButton.setPrefWidth(130);
		rseButton.setDisable(false);
		frame.getChildren().add(rseButton);
		
		stage.show();
	}

		
	
  
}

