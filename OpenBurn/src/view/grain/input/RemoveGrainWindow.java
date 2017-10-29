package view.grain.input;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RemoveGrainWindow extends Stage
{
	// Labels
	private static final String REMOVE_GRAIN_TITLE = "Remove a Grain";
	
	private static final String REMOVE_GRAIN_PROMPT = "Are you sure you want to remove this grain?";
	
	private static final String YES = "Yes";
	private static final String NO  = "No";
	
	
	// Constants
	private static final int WINDOW_WIDTH  = 350;
	private static final int WINDOW_HEIGHT = 150;
	
	
	
	// Components
	private Text removeText;
	
	private Button yesButton;
	private Button noButton;
	
	
	
	public RemoveGrainWindow ()
	{
		super();
		
		this.getIcons().add(new Image(this.getClass().getResourceAsStream("./../../../images/OpenBurnLogo_1.png")));
		
		
		this.setTitle(REMOVE_GRAIN_TITLE);
		this.setResizable(false);
		
		Pane removePane = new Pane();
        Scene removeScene = new Scene(removePane, WINDOW_WIDTH, WINDOW_HEIGHT);
        removeScene.setRoot(removePane);
        
        this.setScene(removeScene);
        
        
        removeText = new Text(REMOVE_GRAIN_PROMPT);
        removeText.setTranslateX(30);
    	removeText.setTranslateY(50);
    	removePane.getChildren().add(removeText);
    	
    	
    	yesButton = new Button(YES);
    	yesButton.setTranslateX(40);
    	yesButton.setTranslateY(80);
    	yesButton.setPrefHeight(35);
    	yesButton.setPrefWidth(120);
    	removePane.getChildren().add(yesButton);
    	
    	noButton = new Button(NO);
    	noButton.setTranslateX(190);
    	noButton.setTranslateY(80);
    	noButton.setPrefHeight(35);
    	noButton.setPrefWidth(120);
    	removePane.getChildren().add(noButton);
	}
	
	
	
}
