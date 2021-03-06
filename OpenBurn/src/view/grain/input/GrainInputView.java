package view.grain.input;

import java.util.Collections;

import controller.GrainTableHandle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.grains.Grain;

/**
 * GrainInputView.java
 * 
 * Purpose: Contains all the components for grain input
 * 		in the OpenBurn graphical user interface.
**/

public class GrainInputView extends Pane
{
	// Labels
	private static final String GRAINS         = "Grains";
	private static final String GRAIN_ID       = "#";
	private static final String LENGTH         = "Length";
	private static final String OUTER_DIAMETER = "Outer Diameter";
	private static final String INNER_DIAMETER = "Inner Diameter";
	private static final String BURNING_ENDS   = "Burning Ends";
	private static final String ADD            = "Add";
	private static final String REMOVE         = "Remove";
	private static final String EDIT           = "Edit";
	private static final String EMPTY_TEXT     = "No Grains";
	private static final String CENTER_ALIGN   = "-fx-alignment: CENTER";
	private static final String UP_ARROW       = "/images/upArrow.png";
	private static final String DOWN_ARROW     = "/images/downArrow.png";
	
	
	
	// Constants
	private static final int TITLE_X                = 235;
	private static final int TITLE_Y                = 20;
	private static final int TABLE_Y                = 30;
	private static final int TABLE_HEIGHT           = 230;
	private static final int ID_COL_WIDTH           = 30;
	private static final int LENGTH_COL_WIDTH       = 80;
	private static final int DIAMETER_COL_WIDTH     = 140;
	private static final int BURNING_ENDS_COL_WIDTH = 120;
	private static final int BUTTON_WIDTH           = 150;
	private static final int BUTTON_HEIGHT          = 35;
	private static final int BUTTON_Y               = 270;
	private static final int REMOVE_BUTTON_X        = 181;
	private static final int EDIT_BUTTON_X          = 362;
	private static final int EMPTY                  = 0;
	
	
	
	// Components
	private Text grainText;
	private Button upButton;
	private Button downButton;
	private TableView<Grain> table;
	private Button addButton;
	private Button removeButton;
	private Button editButton;
	private BooleanBinding grainsNotValid;
	
	
	// Fields
	private GrainTableHandle tableHandle;
	
	
	
	/**
	 * GrainInputView Constructor
	 * 
	 * Purpose: Creates and initializes the grain input table, along
	 * 		with Add, Remove, and Edit buttons underneath.
	**/
	
	public GrainInputView ()
	{
		// Invoke Pane super constructor
		super();
		
		// Create the table handle
		this.tableHandle = new GrainTableHandle(this);
		
		// Add components
		addTableTitle();
		configureTable();
		addSortButtons();
		addAddButton();
        addRemoveButton();
        addEditButton();
        
        grainsNotValid = Bindings.createBooleanBinding(() ->
		{
			return table.getItems().isEmpty();
		}, table.getItems());
	} // GrainInputView Constructor
	
	public BooleanBinding getBindingIsNotValid()
	{
		return grainsNotValid;
	}
	
	/**
	 * getTable()
	 * 
	 * Purpose: Returns the table of the input view.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: TableView<Grain>. The table of the input view.
	**/
	
	public TableView<Grain> getTable ()
	{
		return table;
	} // getTable()
	
	
	
	/**
	 * addRow()
	 * 
	 * Purpose: Adds the specified grain to the grain table.
	 * 
	 * Parameters:
	 * 		Grain newGrain -- New grain to add to the table.
	 * 
	 * Returns: void.
	**/
	
	public void addRow (Grain newGrain)
	{
		table.getItems().add(newGrain);
	} // addRow()
	
	
	
	/**
	 * removeRow()
	 * 
	 * Purpose: Removes the specified grain row from the table.
	 * 
	 * Parameters:
	 * 		int row -- The grain row to remove.
	 * 
	 * Returns: void.
	**/
	
	public void removeRow (int row)
	{
		table.getItems().remove(row);
	} // removeRow()
	
	
	
	/**
	 * 
	**/
	
	public void editRow (int row, Grain newGrain)
	{
		newGrain.setGrainID(table.getItems().get(row).getGrainID());
		table.getItems().set(row, newGrain);
	} // 
	
	
	
	/**
	 * addTableTitle()
	 * 
	 * Purpose: Adds the table title to the Pane.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: void.
	**/
	
	private void addTableTitle ()
	{
		grainText = new Text(GRAINS);
		grainText.setTranslateX(TITLE_X);
		grainText.setTranslateY(TITLE_Y);
		this.getChildren().add(grainText);
	} // addTableTitle()
	
	
	
	/**
	 * addSortButtons()
	 * 
	 * Purpose: Adds the up and down buttons for rearranging
	 * 		grains in the table.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: void.
	**/
	
	private void addSortButtons ()
	{
		// Up button
		upButton = new Button();
		Image upImage = new Image(this.getClass().getResourceAsStream(UP_ARROW));
		upButton.setGraphic(new ImageView(upImage));
		upButton.setTranslateX(-55);
		upButton.setTranslateY(60);
		upButton.setPrefHeight(95);
		upButton.setPrefWidth(10);
		this.getChildren().add(upButton);
		
		// Disable the button when the table is empty or no item selected
		upButton.disableProperty().bind(Bindings.size(table.getItems()).isEqualTo(EMPTY).or(
										Bindings.isEmpty(table.getSelectionModel().getSelectedItems())));
		
		// Move currently selected grain up on click
		upButton.setOnAction(new EventHandler<ActionEvent> ()
		{
			@Override
			public void handle (ActionEvent e)
			{
				int row = table.getItems().indexOf(table.getSelectionModel().getSelectedItem());
				if (row != 0)
					Collections.swap(table.getItems(), row, row-1);
			}
		});
		
		// Down button
		downButton = new Button();
		Image downImage = new Image(this.getClass().getResourceAsStream(DOWN_ARROW));
		downButton.setGraphic(new ImageView(downImage));
		downButton.setTranslateX(-55);
		downButton.setTranslateY(165);
		downButton.setPrefHeight(95);
		downButton.setPrefWidth(10);
		this.getChildren().add(downButton);
		
		// Disable the button when the table is empty or no item selected
		downButton.disableProperty().bind(Bindings.size(table.getItems()).isEqualTo(EMPTY).or(
										  Bindings.isEmpty(table.getSelectionModel().getSelectedItems())));
		
		// Move currently selected grain up down click
		downButton.setOnAction(new EventHandler<ActionEvent> ()
		{
			@Override
			public void handle (ActionEvent e)
			{
				int row = table.getItems().indexOf(table.getSelectionModel().getSelectedItem());
				if (row != table.getItems().size()-1)
					Collections.swap(table.getItems(), row, row+1);
			}
		});
	} // addSortButton()
	
	
	
	/**
	 * configureTable()
	 * 
	 * Purpose: Initializes the details of the grain input table
	 * 		and adds it to the Pane.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: void.
	**/
	
	private void configureTable ()
	{
		table = new TableView<Grain>();
		table.setPlaceholder(new Label(EMPTY_TEXT));
		table.setEditable(false);
		table.setTranslateY(TABLE_Y);
		table.setPrefHeight(TABLE_HEIGHT);
		this.getChildren().add(table);
		setTableColumns();
	} // configureTable()
	
	
	
	/**
	 * setTableColumns()
	 * 
	 * Purpose: Creates and configures the columns for the
	 * 		grain input table, then adds them to the table.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: void.
	**/
	
	@SuppressWarnings("unchecked")
	private void setTableColumns ()
	{
		// Grain ID column
		TableColumn<Grain, String> grainIDCol = new TableColumn<Grain, String>(GRAIN_ID);
		grainIDCol.setResizable(false);
        grainIDCol.setCellValueFactory(new PropertyValueFactory<Grain, String>("GrainID"));
        grainIDCol.setPrefWidth(ID_COL_WIDTH);
        grainIDCol.setStyle(CENTER_ALIGN);
        
        // Length column
        TableColumn<Grain, String> lengthCol = new TableColumn<Grain, String>(LENGTH);
        lengthCol.setResizable(false);
        lengthCol.setCellValueFactory(new PropertyValueFactory<Grain,String>("Length"));
        lengthCol.setPrefWidth(LENGTH_COL_WIDTH);
        lengthCol.setStyle(CENTER_ALIGN);
		
        // Outer Diameter column
        TableColumn<Grain, String> outerDCol = new TableColumn<Grain, String>(OUTER_DIAMETER);
        outerDCol.setResizable(false);
        outerDCol.setCellValueFactory(new PropertyValueFactory<Grain,String>("OuterDiameter"));
        outerDCol.setPrefWidth(DIAMETER_COL_WIDTH);
        outerDCol.setStyle(CENTER_ALIGN);
        
        // Inner Diameter column
        TableColumn<Grain, String> innerDCol = new TableColumn<Grain, String>(INNER_DIAMETER);
        innerDCol.setResizable(false);
        innerDCol.setCellValueFactory(new PropertyValueFactory<Grain,String>("InnerDiameter"));
        innerDCol.setPrefWidth(DIAMETER_COL_WIDTH);
        innerDCol.setStyle(CENTER_ALIGN);
        
        // Number of Burning Ends column
        TableColumn<Grain, String> burningEndsCol = new TableColumn<Grain, String>(BURNING_ENDS);
        burningEndsCol.setResizable(false);
        burningEndsCol.setCellValueFactory(new PropertyValueFactory<Grain,String>("NumBurningEnds"));
        burningEndsCol.setPrefWidth(BURNING_ENDS_COL_WIDTH);
        burningEndsCol.setStyle(CENTER_ALIGN);
        
        // Add columns to the table
        table.getColumns().addAll(grainIDCol, lengthCol, outerDCol, innerDCol, burningEndsCol);
	} // setTableColumns()
	
	
	
	/**
	 * addAddButton()
	 * 
	 * Purpose: Adds the Add button to the Pane. Sets the event
	 * 		handler for when the button is clicked.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: void.
	**/
	
	private void addAddButton ()
	{
		// Initialize add button
		addButton = new Button(ADD);
		addButton.setTranslateY(BUTTON_Y);
		addButton.setPrefHeight(BUTTON_HEIGHT);
		addButton.setPrefWidth(BUTTON_WIDTH);
		addButton.setDisable(false);
		this.getChildren().add(addButton);
		
		// Open new window on click
		addButton.setOnAction(new EventHandler<ActionEvent> ()
		{
		    @Override public void handle (ActionEvent e)
		    {
		    	AddGrainWindow addGrain = new AddGrainWindow(tableHandle);
		    	tableHandle.setToAdd(addGrain);
		    	addGrain.show();
		    }
		});
	} // addAddButton()
	
	
	
	/**
	 * addRemoveButton()
	 * 
	 * Purpose: Adds the Add button to the Pane. Sets the event
	 * 		handler for when the button is clicked. Sets the
	 * 		binding for when to disable the button.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: void.
	**/
	
	private void addRemoveButton ()
	{
		// Initialize remove button
		removeButton = new Button(REMOVE);
		removeButton.setTranslateX(REMOVE_BUTTON_X);
		removeButton.setTranslateY(BUTTON_Y);
		removeButton.setPrefHeight(BUTTON_HEIGHT);
		removeButton.setPrefWidth(BUTTON_WIDTH);
		removeButton.setDisable(false);
		this.getChildren().add(removeButton);
		
		// Open new window on click
		removeButton.setOnAction(new EventHandler<ActionEvent> ()
		{
			@Override
			public void handle (ActionEvent e)
			{
				int row = table.getItems().indexOf(table.getSelectionModel().getSelectedItem());
				RemoveGrainWindow removeGrain = new RemoveGrainWindow(tableHandle, row);
				tableHandle.setToRemove(removeGrain);
				removeGrain.show();
			}
		});
		
		// Disable the button when the table is empty or no item selected
		removeButton.disableProperty().bind(Bindings.size(table.getItems()).isEqualTo(EMPTY).or(
										    Bindings.isEmpty(table.getSelectionModel().getSelectedItems())));
	} // addRemoveButton()
	
	
	
	/**
	 * addEditButton()
	 * 
	 * Purpose: Adds the Edit button to the Pane. Sets the event
	 * 		handler for when the button is clicked. Sets the
	 * 		binding for when to disable the button.
	 * 
	 * Parameters: None.
	 * 
	 * Returns: void.
	**/
	
	private void addEditButton ()
	{
		// Initialize edit button
		editButton = new Button(EDIT);
		editButton.setTranslateX(EDIT_BUTTON_X);
		editButton.setTranslateY(BUTTON_Y);
		editButton.setPrefHeight(BUTTON_HEIGHT);
		editButton.setPrefWidth(BUTTON_WIDTH);
		editButton.setDisable(false);
		this.getChildren().add(editButton);
		
		// Open new window on click
		editButton.setOnAction(new EventHandler<ActionEvent> ()
		{
			@Override
			public void handle (ActionEvent e)
			{
				int row = table.getItems().indexOf(table.getSelectionModel().getSelectedItem());
				EditGrainWindow editGrain = new EditGrainWindow(tableHandle, row);
				tableHandle.setToEdit(editGrain);
				editGrain.show();
			}
		});
		
		// Disable the button when the table is empty or no item selected
		editButton.disableProperty().bind(Bindings.size(table.getItems()).isEqualTo(EMPTY).or(
										  Bindings.isEmpty(table.getSelectionModel().getSelectedItems())));
	} // addEditButton()
	
} // class GrainInputView
