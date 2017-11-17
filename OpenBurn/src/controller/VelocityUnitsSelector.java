package controller;

import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import model.unitConversion.LengthUnits;
import model.unitConversion.UnitConverter;

/*
 * 
 */

public class VelocityUnitsSelector extends ComboBox<String>
{
	//
	private LengthUnits units;
	private NumberTextField valueField;
	
	
	
	/*
	 * 
	 */
	
	public VelocityUnitsSelector (NumberTextField valueField)
	{
		//
		super();
		
		this.valueField = valueField;
		
		//
		for (VelocityUnits units : VelocityUnits.values())
			this.getItems().add(units.getAbbr());
		
		// Initialize the 
		this.getSelectionModel().selectFirst();
		units = LengthUnits.INCHES;
		
		this.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) ->
		{
			if (!this.valueField.getText().equals(""))
			{
				VelocityUnits oldUnits = units;
				this.setUnits(newValue);
				VelocityUnits newUnits = units;
				
				//unitVelocityCOnverter need
				this.valueField.setText(String.valueOf(UnitConverter.unitLengthConverter(Double.parseDouble(this.valueField.getText()), oldUnits, newUnits)));
			}
			this.setUnits(newValue);
	    }); 
	} // 
	
	
	
	/*
	 * 
	 */
	
	private void setUnits (String newValue)
	{
		if (newValue.equals(LengthUnits.INCHES.getAbbr()))
			units = LengthUnits.INCHES;
		else if (newValue.equals(LengthUnits.FEET.getAbbr()))
			units = LengthUnits.FEET;
		else if (newValue.equals(LengthUnits.MILLIMETERS.getAbbr()))
			units = LengthUnits.MILLIMETERS;
		else if (newValue.equals(LengthUnits.CENTIMETERS.getAbbr()))
			units = LengthUnits.CENTIMETERS;
	} // 
	
} // 
