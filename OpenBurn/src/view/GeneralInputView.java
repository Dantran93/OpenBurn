package view;

import controller.NumberTextField;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * GeneralInputView.java
 * 
 * Purpose: Contains the input fields for general inputs
 * 		on the OpenBurn graphical user interface.
**/

public class GeneralInputView extends Pane
{
	// Labels
	private static final String TIME_DELTA_PROMPT 	= "Change in time";
	private static final String SECONDS 			= "seconds";
	private static final String EMPTY               = "";
	
	
	// Constants
	private static final int FIRST_COL_X        = 20;
	private static final int FIRST_ROW_PROMPT_Y = 30;
	private static final int FIRST_ROW_FIELD_Y  = 40;
	
	
	
	// Components
	private Text timeDeltaText;
	private Text secondsText;
	private NumberTextField timeDeltaTextField;
	private BooleanBinding timeFieldsNotValid;
	
	
	/**
	 * GeneralInputView Constructor
	 * 
	 * Purpose: Creates and initializes the input fields for
	 * 		general inputs.
	**/
	
	public GeneralInputView ()
	{
		// Invoke Pane super constructor
		super();
		
		// Add components
		addTimeInput();
		
		timeFieldsNotValid = Bindings.createBooleanBinding(() ->
		{
			boolean time = timeDeltaTextField.getText().equals(EMPTY);
			
			return time;
		}, timeDeltaTextField.textProperty());
	} // GeneralInputView Constructor
	
	public BooleanBinding getBindingIsNotValid()
	{
		return timeFieldsNotValid;
	}
	
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
	
	private void addTimeInput ()
	{
		// Prompt
		timeDeltaText = new Text(TIME_DELTA_PROMPT);
		timeDeltaText.setTranslateX(FIRST_COL_X);
		timeDeltaText.setTranslateY(FIRST_ROW_PROMPT_Y);
		this.getChildren().add(timeDeltaText);
		
		// Input field
		timeDeltaTextField = new NumberTextField();
		timeDeltaTextField.setTranslateX(FIRST_COL_X);
		timeDeltaTextField.setTranslateY(FIRST_ROW_FIELD_Y);
		this.getChildren().add(timeDeltaTextField);
		
		// Units
		secondsText = new Text(SECONDS);
		secondsText.setTranslateX(220);
		secondsText.setTranslateY(FIRST_ROW_FIELD_Y+20);
		this.getChildren().add(secondsText);
	} // addTimeInput()
	
	
	
	/**
	 * getTimeDeltaInput()
	 * 
	 * Purpose: Returns the value currently in the time delta field.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: double. The value currently in the time delta field.
	**/
	
	public double getTimeDeltaInput ()
	{
		return Double.parseDouble(timeDeltaTextField.getText().toString());
	} // getTimeDeltaInput()
	
	/**
	 * getTimeDeltaTextField()
	 * 
	 * Purpose: Returns the value currently in the time delta number text field.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: NumberTextField.
	**/
	
	public NumberTextField getTimeDeltaTextField ()
	{
		return timeDeltaTextField;
	} // getTimeDeltaTextField()
	
	
	
} // class GeneralInputView
