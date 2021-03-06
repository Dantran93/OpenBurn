package view.grain.input;

import controller.GrainTableHandle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * RemoveGrainWindow.java
 * 
 * Purpose: Contains all the components for grain removing
 * 		in the OpenBurn graphical user interface.
**/

public class RemoveGrainWindow extends Stage
{
	// Labels
	private static final String REMOVE_GRAIN_TITLE  = "Remove Grain #";
	private static final String REMOVE_GRAIN_PROMPT = "Are you sure you want to remove this grain?";
	private static final String YES                 = "Yes";
	private static final String NO                  = "No";
	private static final String ICON_FILE_PATH      = "/images/OpenBurnLogo_1.png";
	
	
	
	// Constants
	private static final int WINDOW_WIDTH  = 350;
	private static final int WINDOW_HEIGHT = 150;
	
	
	
	// Components
	private Text removeText;
	private Button yesButton;
	private Button noButton;
	
	
	
	// Fields
	private GrainTableHandle tableHandle;
	private int row;
	
	
	
	/**
	 * RemoveGrainWindow Constructor
	 * 
	 * Purpose: Creates and initializes the grain removing window.
	**/
	
	public RemoveGrainWindow (GrainTableHandle tableHandle, int row)
	{
		// Invoke Pane super constructor
		super();
		
		// Set fields
		this.tableHandle = tableHandle;
		this.row = row;
		
		// Initialize window
		this.getIcons().add(new Image(this.getClass().getResourceAsStream(ICON_FILE_PATH)));
		this.setTitle(REMOVE_GRAIN_TITLE + this.tableHandle.getInputView().getTable().getItems().get(row).getGrainID());
		this.setResizable(false);
		Pane removePane = new Pane();
        Scene removeScene = new Scene(removePane, WINDOW_WIDTH, WINDOW_HEIGHT);
        removeScene.setRoot(removePane);
        this.setScene(removeScene);
        
        // Add components
        addRemoveText(removePane);
    	addYesButton(removePane);
    	addNoButton(removePane);
    	
    	// Cannot click back to main GUI until this window is closed
    	this.initModality(Modality.WINDOW_MODAL);
		this.initOwner(tableHandle.getInputView().getScene().getWindow());
	} // RemoveGrainWindow Constructor
	
	
	
	/**
	 * addRemoveText()
	 * 
	 * Purpose: Adds the remove text prompt on the given Pane.
	 * 
	 * Parameters:
	 * 		Pane frame -- The Pane to add the remove text to.
	 * 
	 * Returns: void.
	**/
	
	private void addRemoveText (Pane frame)
	{
		// Initialize remove text
		removeText = new Text(REMOVE_GRAIN_PROMPT);
        removeText.setTranslateX(30);
    	removeText.setTranslateY(50);
    	frame.getChildren().add(removeText);
	} // addRemoveText()
	
	
	
	/**
	 * addYesButton()
	 * 
	 * Purpose: Adds the yes button on the given Pane.
	 * 
	 * Parameters:
	 * 		Pane frame -- The Pane to add the yes button to.
	 * 
	 * Returns: void.
	**/
	
	private void addYesButton (Pane frame)
	{
		// Initialize the yes button
    	yesButton = new Button(YES);
    	yesButton.setTranslateX(40);
    	yesButton.setTranslateY(80);
    	yesButton.setPrefHeight(35);
    	yesButton.setPrefWidth(120);
    	frame.getChildren().add(yesButton);
    	
    	// Remove grain from table on click
    	yesButton.setOnAction(new EventHandler<ActionEvent> ()
		{
			@Override
			public void handle (ActionEvent e)
			{
				tableHandle.removeGrainFromTable(row);
				closeWindow();
			}
		});
	} // addYesButton()
	
	
	
	/**
	 * addNoButton()
	 * 
	 * Purpose: Adds the no button on the given Pane.
	 * 
	 * Parameters:
	 * 		Pane frame -- The Pane to add the no button to.
	 * 
	 * Returns: void.
	**/
	
	private void addNoButton (Pane frame)
	{
		// Initialize the no button
		noButton = new Button(NO);
    	noButton.setTranslateX(190);
    	noButton.setTranslateY(80);
    	noButton.setPrefHeight(35);
    	noButton.setPrefWidth(120);
    	frame.getChildren().add(noButton);
    	
    	// Close window on click
    	noButton.setOnAction(new EventHandler<ActionEvent> ()
		{
			@Override
			public void handle (ActionEvent e)
			{
				closeWindow();
			}
		});
	} // addNoButton()
	
	
	
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
	
} // class RemoveGrainWindow
