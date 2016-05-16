package Controlleur;

import Vue.*;

import java.util.Optional;
import java.util.ResourceBundle;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.stage.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.fxml.Initializable;

/**
 * classe qui gere les evenements de la fenetre option.
 * @author Giovanni
 */
public class Options implements Initializable{

	@FXML Button btDefaut,btOk,btCancel,btAppliquer;
	@FXML ComboBox<String> cbTheme,cbCouleurPolice;
	@FXML CheckBox cbConfirmation;
	@FXML TextField tfPolice;
	
	
	
	@Override
	public void  initialize(URL location, ResourceBundle resources){
		
		cbTheme.getItems().addAll("Overflow","Null");
		cbTheme.setValue("Overflow");
		
		cbCouleurPolice.getItems().addAll("Orange","Black","Green","Yellow","Red");
		cbCouleurPolice.setValue(Game.textAreaColor);
		tfPolice.setText(Integer.toString(Game.textAreaPolice));
		
		if(JavaOverflow.confirmClose){
			cbConfirmation.setSelected(true);
		}else
		{
			cbConfirmation.setSelected(false);
		}
		
		tfPolice.addEventFilter(KeyEvent.KEY_PRESSED, 
		        new EventHandler<KeyEvent>() {
		    public void handle(KeyEvent event) {

		        if(event.getCode() == KeyCode.ENTER) {
		        	setTextPolice(Integer.parseInt(tfPolice.getText()),true);
		        } 

		    };
		});
	}

	public void handlerCheckboxConfirmation(ActionEvent event){
		
		if(cbConfirmation.isSelected()){
			
			JavaOverflow.confirmClose = true;
		}else
		{
			JavaOverflow.confirmClose = false;
		}
		
	}
	
	public void setTextPolice(int taille,Boolean prompt){
		
    	if(taille >=5 && taille<=30)
    	{
    		Game.textAreaPolice = taille;
    		if(prompt)
    		{
	    		Alert alert = new Alert(AlertType.INFORMATION);
	    		alert.setTitle("JavaOverflow - Confirmation");
	    		alert.setContentText("Police changer pour: "+Game.textAreaPolice);
	    		alert.showAndWait();
    		}
    	}else
    	{
 
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erreur");
			alert.setHeaderText("Erreur de format");
			alert.setContentText("Format de police doit etre entre 5 et 30 px");
			alert.showAndWait();
    	}
	
	}
	public void btOkHandler()
	{
		setTextPolice(Integer.parseInt(tfPolice.getText()),false);
		((Stage)btOk.getScene().getWindow()).close();
		
	}
	public void btAppliquerHandler()
	{
		setTextPolice(Integer.parseInt(tfPolice.getText()),true);
		
	}
	public void btCancelHandler()
	{
		((Stage)btCancel.getScene().getWindow()).close();
		
	}

	public void handlerCbCouleurPolice(ActionEvent event){
		
		Game.textAreaColor = cbCouleurPolice.getValue();
		
	}
	
	public void handlerCbTheme(ActionEvent event)
	{
		
		
	}
	
	public void handlerBtDefaut(ActionEvent event){
		
		setTextPolice(16,false);
		tfPolice.setText(Integer.toString(Game.textAreaPolice));
		Game.textAreaColor = "Black";
		cbCouleurPolice.setValue(Game.textAreaColor);
		JavaOverflow.confirmClose = true;
		cbConfirmation.setSelected(true);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("JavaOverflow - Confirmation");
		alert.setContentText("Preferences par default appliqer!");
		alert.showAndWait();
	}
	
	
}
