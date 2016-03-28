package Controlleur;

import Vue.*;
import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;

import javafx.stage.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.Initializable;

public class Accueil implements Initializable{
	
	@FXML Button btCommencer,btOptions,btQuitter;
	
	@Override
	public void  initialize(URL location, ResourceBundle resources){
		
		
	}
	
	
	public void handlerCommencer(ActionEvent event) throws IOException
	{
		Stage currentStage = (Stage)btCommencer.getScene().getWindow();
		currentStage.hide();
		
		Parent root = FXMLLoader.load(getClass().getResource("/Vue/Game.fxml"));
		Scene scene = new Scene(root, 600,400);
		
		Stage gameStage = new Stage();
		gameStage.setTitle("JavaOverflow");
		gameStage.setScene(scene);
		gameStage.show();
		
		gameStage.setOnCloseRequest(e ->{
			e.consume();
			JavaOverflow.closeProgram(gameStage);
		});

	}
	
	public void handlerOptions(ActionEvent event)
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Oops!");
		alert.setHeaderText("Attention");
		alert.setContentText("Cette option n'est pas disponible dans cette version.");
		alert.showAndWait(); 
	}
	
	public void handlerQuitter(ActionEvent event)
	{
		JavaOverflow.closeProgram((Stage)btQuitter.getScene().getWindow());
	}
}
