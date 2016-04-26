package Controlleur;

import Vue.*;

import java.util.Optional;
import java.util.ResourceBundle;

import Modele.Database;

import java.io.File;
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
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.fxml.Initializable;


public class Options implements Initializable{

	@FXML Button btDefaut;
	@FXML ComboBox<String> cbTheme;
	@FXML CheckBox cbConfirmation;
	
	
	
	@Override
	public void  initialize(URL location, ResourceBundle resources){
		
		cbTheme.getItems().addAll("Overflow","Null");
		cbTheme.setValue("Overflow");
	
	}
	
	public void handlerCheckboxConfirmation(ActionEvent event){
		
		if(cbConfirmation.isSelected()){
			
			JavaOverflow.confirmClose = true;
		}else
		{
			JavaOverflow.confirmClose = false;
		}
		
	}
	public void handlerBtDefaut(ActionEvent event){
		
		
	}
	
	
	
}
