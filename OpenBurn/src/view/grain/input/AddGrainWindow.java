package view.grain.input;

import controller.GrainTableHandle;
import controller.NumberTextField;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.grains.CylindricalGrain;
import model.grains.Grain;
import model.grains.GrainFactory;
import model.grains.GrainType;
import controller.LengthUnitsSelector;

/**
 * AddGrainWindow.java
 * 
 * Purpose: Contains all the components for grain adding
 * 		in the OpenBurn graphical user interface.
**/

public class AddGrainWindow extends Stage
{
	// Labels
	private static final String ADD_GRAIN_TITLE       = "Add a Grain";
	private static final String LENGTH_PROMPT         = "Length";
	private static final String OUTER_DIAMETER_PROMPT = "Outer Diameter";
	private static final String INNER_DIAMETER_PROMPT = "Inner Diameter";
	private static final String BURNING_ENDS_PROMPT   = "Number of Burning Ends";
	private static final String SUBMIT_BUTTON_TEXT    = "Add Grain";
	private static final String ERROR_TEXT            = "Inner diameter must be less than outer diameter!";
	private static final String EMPTY                 = "";
	private static final String ICON_FILE_PATH        = "/images/OpenBurnLogo_1.png";
	
	
	
	// Constants
	private static final int WINDOW_WIDTH  = 450;
	private static final int WINDOW_HEIGHT = 270;
	
	
	
	// GUI Components
	private Text lengthText;
	private Text outerDiameterText;
	private Text burningEndsText;
	private Text innerDiameterText;
	private Text errorText;
	private NumberTextField lengthTextField;
	private NumberTextField outerDiameterTextField;
	private ComboBox<String> burningEndsSelection;
	private NumberTextField innerDiameterTextField;
	private Button submitButton;
	
	
	
	// Fields
	private GrainTableHandle tableHandle;
	private BooleanBinding lengthTextFieldNotValid;
	private BooleanBinding outerDiameterTextFieldNotValid;
	private BooleanBinding burningEndsSelectionNotValid;
	private BooleanBinding innerDiameterTextFieldNotValid;
	
	
	
	/**
	 * AddGrainWindow Constructor
	 * 
	 * Purpose: Creates and initializes the grain adding window.
	**/
	
	public AddGrainWindow (GrainTableHandle tableHandle)
	{
		// Invoke Pane super constructor
		super();
		
		// Set handle
		this.tableHandle = tableHandle;
		
		// Initialize window
		this.getIcons().add(new Image(this.getClass().getResourceAsStream(ICON_FILE_PATH)));
		this.setTitle(ADD_GRAIN_TITLE);
		this.setResizable(false);
		Pane addPane = new Pane();
        Scene addScene = new Scene(addPane, WINDOW_WIDTH, WINDOW_HEIGHT);
        addScene.setRoot(addPane);
        this.setScene(addScene);
        
        // Add components
        addErrorText(addPane);
        addLengthInput(addPane);
		addOuterDiameterInput(addPane);
		addBurningEndsInput(addPane);
		addInnerDiameterInput(addPane);
		addSubmitButton(addPane);
		
		// Cannot click back to main GUI until this window is closed
		this.initModality(Modality.WINDOW_MODAL);
		this.initOwner(tableHandle.getInputView().getScene().getWindow());
	} // AddGrainWindow Constructor
	
	
	
	/**
	 * addLengthInput()
	 * 
	 * Purpose: Adds the length input field to the given Pane.
	 * 		Sets the binding rule for if the field is empty.
	 * 
	 * Parameters:
	 * 		Pane frame -- The Pane to add the length input text
	 * 			field to.
	 * 
	 * Returns: void.
	**/
	
	private void addLengthInput (Pane frame)
	{
		// Length input prompt
		lengthText = new Text(LENGTH_PROMPT);
    	lengthText.setTranslateX(25);
    	lengthText.setTranslateY(40);
    	frame.getChildren().add(lengthText);
    	
    	// Length input field
    	lengthTextField = new NumberTextField();
    	lengthTextField.setTranslateX(25);
    	lengthTextField.setTranslateY(50);
		frame.getChildren().add(lengthTextField);
		
		LengthUnitsSelector units = new LengthUnitsSelector(lengthTextField);
		units.setTranslateX(135);
		units.setTranslateY(50);
		lengthTextField.setPrefWidth(110);
		frame.getChildren().add(units);
		
		// Set binding rules on length input
		lengthTextFieldNotValid = Bindings.createBooleanBinding(() ->
		{
    	    return lengthTextField.getText().equals(EMPTY);
    	}, lengthTextField.textProperty());
	} // addLengthInput()
	
	
	
	/**
	 * addOuterDiameterInput()
	 * 
	 * Purpose: Adds the outer diameter input field to the given Pane.
	 * 		Sets the binding rule for if the field is empty.
	 * 
	 * Parameters:
	 * 		Pane frame -- The Pane to add the outer diameter input
	 * 			text field to.
	 * 
	 * Returns: void.
	**/
	
	private void addOuterDiameterInput (Pane frame)
	{
		// Outer Diameter prompt
		outerDiameterText = new Text(OUTER_DIAMETER_PROMPT);
		outerDiameterText.setTranslateX(25);
    	outerDiameterText.setTranslateY(130);
		frame.getChildren().add(outerDiameterText);
		
		// Outer Diameter input field
		outerDiameterTextField = new NumberTextField();
		outerDiameterTextField.setTranslateX(25);
    	outerDiameterTextField.setTranslateY(140);
		frame.getChildren().add(outerDiameterTextField);
		
		LengthUnitsSelector units = new LengthUnitsSelector(outerDiameterTextField);
		units.setTranslateX(135);
		units.setTranslateY(140);
		outerDiameterTextField.setPrefWidth(110);
		frame.getChildren().add(units);
		
		// Set binding rules on outer diameter input
		outerDiameterTextFieldNotValid = Bindings.createBooleanBinding(() ->
		{
    	    return outerDiameterTextField.getText().equals(EMPTY);
    	}, outerDiameterTextField.textProperty());
	} // addOuterDiameterInput()
	
	
	
	/**
	 * addBurningEndsInput()
	 * 
	 * Purpose: Adds the burning ends input field to the given Pane.
	 * 		Sets the binding rule for if the field is empty.
	 * 
	 * Parameters:
	 * 		Pane frame -- The Pane to add the burning ends
	 * 			text field to.
	 * 
	 * Returns: void.
	**/
	
	private void addBurningEndsInput (Pane frame)
	{
		
		
		// Burning Ends prompt
		burningEndsText = new Text(BURNING_ENDS_PROMPT);
		burningEndsText.setTranslateX(235);
    	burningEndsText.setTranslateY(40);
		frame.getChildren().add(burningEndsText);
		
		// Burning Ends input field
		ObservableList<String> options = FXCollections.observableArrayList("0", "1", "2");
		burningEndsSelection = new ComboBox<String>(options);
		burningEndsSelection.getSelectionModel().selectFirst();
		burningEndsSelection.setPrefWidth(185);
		burningEndsSelection.setTranslateX(235);
    	burningEndsSelection.setTranslateY(50);
		frame.getChildren().add(burningEndsSelection);
		
		// Set binding rule on burning ends input
		burningEndsSelectionNotValid = Bindings.createBooleanBinding(() ->
		{
    	    return burningEndsSelection.getValue().equals(EMPTY);
    	}, burningEndsSelection.valueProperty());
	} // addBurningEndsInput()
	
	
	
	/**
	 * addInnerDiameterInput()
	 * 
	 * Purpose: Adds the inner diameter input field to the given Pane.
	 * 		Sets the binding rule for if the field is empty.
	 * 
	 * Parameters:
	 * 		Pane frame -- The Pane to add the inner diameter
	 * 			text field to.
	 * 
	 * Returns: void.
	**/
	
	private void addInnerDiameterInput (Pane frame)
	{
		// Inner Diameter prompt
		innerDiameterText = new Text(INNER_DIAMETER_PROMPT);
		innerDiameterText.setTranslateX(235);
    	innerDiameterText.setTranslateY(130);
		frame.getChildren().add(innerDiameterText);
		
		// Inner Diameter input field
		innerDiameterTextField = new NumberTextField();
		innerDiameterTextField.setTranslateX(235);
    	innerDiameterTextField.setTranslateY(140);
		frame.getChildren().add(innerDiameterTextField);
		
		LengthUnitsSelector units = new LengthUnitsSelector(innerDiameterTextField);
		units.setTranslateX(345);
		units.setTranslateY(140);
		innerDiameterTextField.setPrefWidth(110);
		frame.getChildren().add(units);
		
		// Set binding rule on inner diameter input
		innerDiameterTextFieldNotValid = Bindings.createBooleanBinding(() ->
		{
    	    return innerDiameterTextField.getText().equals(EMPTY);
    	}, innerDiameterTextField.textProperty());
	} // addInnerDiameterInput()
	
	
	
	/**
	 * addSubmitButton()
	 * 
	 * Purpose: Adds the submit button to the given Pane. Sets the
	 * 		binding rule for when to disable the button.
	 * 
	 * Parameters:
	 * 		Pane frame -- The Pane to add the button to.
	 * 
	 * Returns: void.
	**/
	
	private void addSubmitButton (Pane frame)
	{
		// Initialize submit button
		submitButton = new Button(SUBMIT_BUTTON_TEXT);
		submitButton.setTranslateX(155);
    	submitButton.setTranslateY(200);
    	submitButton.setPrefWidth(130);
    	submitButton.setPrefHeight(35);
    	frame.getChildren().add(submitButton);
    	
    	// Set button binding rules
    	submitButton.disableProperty().bind((lengthTextFieldNotValid.or(
    										 outerDiameterTextFieldNotValid).or(
    										 burningEndsSelectionNotValid.or(
    										 innerDiameterTextFieldNotValid))));	
    	
    	// Add new grain to table on click
    	submitButton.setOnAction(new EventHandler<ActionEvent> ()
		{
		    @Override public void handle (ActionEvent e)
		    {
		    	// Error check that inner diameter is less than outer diameter
		    	if (Double.parseDouble(innerDiameterTextField.getText()) < Double.parseDouble(outerDiameterTextField.getText()))
		    	{
		    		errorText.setText(EMPTY);
		    		
		    		// Gather grain data from input fields
		    		double length = Double.parseDouble(lengthTextField.getText());
		    		double outerDiameter = Double.parseDouble(outerDiameterTextField.getText());
		    		double innerDiameter = Double.parseDouble(innerDiameterTextField.getText());
		    		int numBurningEnds = Integer.parseInt(burningEndsSelection.getValue());
		    		
		    		// Create grain, add it to the table, close the window
		    		Grain newGrain = GrainFactory.createGrain(GrainType.Cylindrical, length, outerDiameter, innerDiameter, numBurningEnds);
		    		newGrain.setGrainID(tableHandle.getNextTableID());
		    		tableHandle.addGrainToTable(newGrain);
		    		closeWindow();
		    	}
		    	// Invalid inputs
		    	else
		    		errorText.setText(ERROR_TEXT);
		    }
		});
	} // addSubmitButton()
	
	
	
	/**
	 * addErrorText()
	 * 
	 * Purpose: Adds the error text to the given Pane.
	 * 
	 * Parameters:
	 * 		Pane frame -- The Pane to add the text to.
	 * 
	 * Returns: void.
	**/
	
	private void addErrorText (Pane frame)
	{
		errorText = new Text();
		errorText.setTranslateX(60);
		errorText.setTranslateY(265);
		frame.getChildren().add(errorText);
	} // addErrorText()
	
	
	
	/**
	 * closeWindow()
	 * 
	 * Purpose: Closes this window.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: void.
	**/
	
	private void closeWindow ()
	{
		this.close();
	} // closeWindow()
	
} // class AddGrainWindow
