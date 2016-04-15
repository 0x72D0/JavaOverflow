package Controlleur;

import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;

import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class Game implements Initializable{

	@FXML TextField fieldReponse;
	@FXML Button btConfirmer,btSuivant;
	@FXML TextArea textArea;
	@FXML Menu menuFichier,menuEdition,menuAide;
	@FXML MenuItem itemFermer,itemCopier,itemAide;
	@FXML BorderPane borderPane;
	
	private Stage currentStage;
	
	@Override
	public void  initialize(URL location, ResourceBundle resources){

		JavaOverflow.generateQuestion();
		textArea.setText(JavaOverflow.cwq.getEnonce());
	}
	
	public void setStage(Stage theStage)
	{
		this.currentStage = theStage;
	}
	
	public void btConfirmerHandler(ActionEvent event){
		
		if(JavaOverflow.verifyStringAnswer(fieldReponse.getText())){
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("JavaOverflow");
			alert.setHeaderText("Bonne reponse.");
			alert.setContentText("Bravo!");
			alert.showAndWait();
			
			JavaOverflow.generateQuestion();
			textArea.setText(JavaOverflow.cwq.getEnonce());
			fieldReponse.setText("");
			
		}else
		{
                        JavaOverflow.database.getEleve().setNbreEssaiRate((short)(JavaOverflow.database.getEleve().getNbreEssaiRate()+1));
			if(JavaOverflow.database.getEleve().getNbreEssaiRate()==JavaOverflow.database.getEssaisAvantAide())
                        {
                            //TODO
                        }
                        Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("JavaOverflow");
			alert.setHeaderText("Mauvaise reponse.");
			alert.setContentText("Essaye encore.");
			alert.showAndWait();
		}
		
	}
	public void btSuivantHandler(ActionEvent event){
		
		JavaOverflow.generateQuestion();
		textArea.setText(JavaOverflow.cwq.getEnonce());
		
	}
	public void itemFermerHandler(ActionEvent event){
		
		JavaOverflow.closeProgram((Stage)borderPane.getScene().getWindow());

	}
	public void itemCopierhandler(ActionEvent event){
		
		
	}
	public void itemAidehandler(ActionEvent event){
		
		
	}
	public void fieldReponseHandler(ActionEvent event){
		
		
	}
	/*public void textAreaHandler(ActionEvent event){
		
		
	}*/
	
	
}
