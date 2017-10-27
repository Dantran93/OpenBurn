package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.grains.CylindricalGrain;
import model.grains.Grain;



public class OpenBurnGUI extends Application
{
	// GUI Labels
	private static final String DENSITY_PROMPT           = "Enter propellant density (Must be positive): ";
	private static final String FILE_PROMPT              = "\nEnter the desired name of the CSV file (Don't include \".csv\"): ";
	
	private static final String THROAT_DIAMETER_PROMPT   = "\nEnter nozzle throat diameter (Must be positive): ";
	private static final String ENTRANCE_DIAMETER_PROMPT = "Enter nozzle entrance diameter (Must be positive): ";
	private static final String EXIT_DIAMETER_PROMPT     = "Enter nozzle exit diameter (Must be positive): ";
	private static final String CF_PROMPT                = "Enter nozzle CF (Must be positive): ";
	private static final String TIME_DELTA_PROMPT        = "Enter change in time (Must be positive): ";
	private static final String SIMULATE                 = "Simulate";
	private static final String ADD                      = "Add";
	private static final String REMOVE                   = "Remove";
	private static final String EDIT                     = "Edit";
	
	
	
	// 
	private Text propDensityText;
	private TextField propDensityTextField;
	private GrainInputView grainInputs;
	
	
	
	
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	
	
	@Override
	public void start(Stage stage)
	{
		StackPane root = new StackPane();
		Scene scene = new Scene(root, 1200, 750);
		stage.setScene(scene);
		stage.setTitle("OpenBurn");
		stage.setResizable(false);

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		scene.setRoot(grid);
		
		
		
		propDensityText = new Text(DENSITY_PROMPT);
		GridPane.setConstraints(propDensityText, 0, 0);
		grid.getChildren().add(propDensityText);
		
		propDensityTextField = new TextField();
		propDensityTextField.setPrefColumnCount(10);
		GridPane.setConstraints(propDensityTextField, 0, 1);
		grid.getChildren().add(propDensityTextField);
		
		
		
		grainInputs = new GrainInputView();
		GridPane.setConstraints(grainInputs, 0, 3);
		grid.getChildren().add(grainInputs);
		
		// Adding a sample input
		grainInputs.addRow(new CylindricalGrain(2.0, 3.0, 2.0, 2));
		
		/*
		final TextField field8 = new TextField();
		field8.setPromptText(THROAT_DIAMETER_PROMPT);
		field8.setPrefColumnCount(10);
		field8.getText();
		GridPane.setConstraints(field8, 3, 3);
		grid.getChildren().add(field8);
		
		final TextField field9 = new TextField();
		field9.setPromptText(ENTRANCE_DIAMETER_PROMPT);
		field9.setPrefColumnCount(10);
		field9.getText();
		GridPane.setConstraints(field9, 3, 6);
		grid.getChildren().add(field9);
		
		final TextField field10 = new TextField();
		field10.setPromptText(EXIT_DIAMETER_PROMPT);
		field10.setPrefColumnCount(10);
		field10.getText();
		System.out.print(field10.getText());
		GridPane.setConstraints(field10, 3, 9);
		grid.getChildren().add(field10);
		
		final TextField field11 = new TextField();
		field11.setPromptText(CF_PROMPT);
		field11.setPrefColumnCount(10);
		field11.getText();
		GridPane.setConstraints(field11, 3, 12);
		grid.getChildren().add(field11);
		
		final TextField field12 = new TextField();
		field12.setPromptText(TIME_DELTA_PROMPT);
		field12.setPrefColumnCount(10);
		field12.getText();
		GridPane.setConstraints(field12, 3, 15);
		grid.getChildren().add(field12);
		*/
		
		//grid.getChildren().add(new GraphView("Pressure vs. Time", "Time", "Pressure").getChart());
		
    	
		stage.show();
	}

		
	
  
}

