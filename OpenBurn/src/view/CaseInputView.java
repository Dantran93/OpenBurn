package view;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * CaseInputView.java
 * 
 * Purpose: Contains the input fields for a case on the
 * 		OpenBurn graphical user interface.
**/

public class CaseInputView extends Pane
{
	// Labels
	private static final String CASE_MASS_PROMPT     = "Enter mass of the case";
	private static final String CASE_DIAMETER_PROMPT = "Enter diameter of the case";
	private static final String CASE_LENGTH_PROMPT   = "Enter length of the case";
	
	
	
	// Components
	private Text massText;
	private TextField massTextField;
	private Text diameterText;
	private TextField diameterTextField;
	private Text lengthText;
	private TextField lengthTextField;
	
	
	
	/**
	 * CaseInputView Constructor
	 * 
	 * Purpose: Creates and initializes the input fields for a case.
	**/
	
	public CaseInputView ()
	{
		// Invoke Pane super constructor
		super();
		
		// Add components
		addMassInput();
		addDiameterInput();
		addLengthInput();
	} // CaseInputView Constructor
	
	
	
	/**
	 * addMassInput()
	 * 
	 * Purpose: Adds the mass input field.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: void.
	**/
	
	private void addMassInput ()
	{
		// Mass prompt
		massText = new Text(CASE_MASS_PROMPT);
		massText.setTranslateX(20);
		massText.setTranslateY(20);
		this.getChildren().add(massText);
		
		// Mass input field
		massTextField = new TextField();
		massTextField.setTranslateX(20);
		massTextField.setTranslateY(30);
		this.getChildren().add(massTextField);
	} // addMassInput()
	
	
	
	/**
	 * addDiameterInput()
	 * 
	 * Purpose: Adds the diameter input field.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: void.
	**/
	
	private void addDiameterInput ()
	{
		// Diameter prompt
		diameterText = new Text(CASE_DIAMETER_PROMPT);
		diameterText.setTranslateX(20);
		diameterText.setTranslateY(100);
		this.getChildren().add(diameterText);
		
		// Diameter input field
		diameterTextField = new TextField();
		diameterTextField.setTranslateX(20);
		diameterTextField.setTranslateY(110);
		this.getChildren().add(diameterTextField);
	} // addDiameterInput()
	
	
	
	/**
	 * addLengthInput()
	 * 
	 * Purpose: Adds the length input field.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: void.
	**/
	
	private void addLengthInput ()
	{
		// Length prompt
		lengthText = new Text(CASE_LENGTH_PROMPT);
		lengthText.setTranslateX(20);
		lengthText.setTranslateY(180);
		this.getChildren().add(lengthText);
		
		// Length input field
		lengthTextField = new TextField();
		lengthTextField.setTranslateX(20);
		lengthTextField.setTranslateY(190);
		this.getChildren().add(lengthTextField);
	} // addLengthInput()
	
} // class CaseInputView
