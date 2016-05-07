package Controlleur;

import Controlleur.JavaOverflow;
import java.awt.Desktop;
import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
import javax.swing.JOptionPane;

public class Game implements Initializable{

	@FXML TextField fieldReponse;
	@FXML Button btConfirmer;
	@FXML TextArea textArea;
	@FXML Menu menuFichier,menuEdition,menuAide;
	@FXML MenuItem itemFermer,itemCopier,itemAide;
	@FXML BorderPane borderPane;
	@FXML Label scoreLabel;
	
	private Stage currentStage;
	
	@Override
	public void  initialize(URL location, ResourceBundle resources){

		JavaOverflow.generateQuestion();
		textArea.setText(JavaOverflow.cwq.getEnonce());
		scoreLabel.setText("Score: "+JavaOverflow.cs.getPoints());
	}
	
	public void setStage(Stage theStage)
	{
		this.currentStage = theStage;
	}
	
	public void btConfirmerHandler(ActionEvent event) throws IOException{
		
		if(JavaOverflow.verifyStringAnswer(fieldReponse.getText())){
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("JavaOverflow");
			alert.setHeaderText("Bonne reponse.");
			alert.setContentText("Bravo!");
			alert.showAndWait();
			
			JavaOverflow.generateQuestion();
			textArea.setText(JavaOverflow.cwq.getEnonce());
			fieldReponse.setText("");
			scoreLabel.setText("Score: "+JavaOverflow.cs.getPoints());
			
		}else
		{
                        JavaOverflow.cs.setNbreEssaiRate((short)(JavaOverflow.cs.getNbreEssaiRate()+1));
			if(JavaOverflow.cs.getNbreEssaiRate()==JavaOverflow.database.getEssaisAvantAide())
                        {
                            try
                            {
                            	if(Desktop.isDesktopSupported())
            			{
                			Desktop.getDesktop().browse(new URI(JavaOverflow.cwq.getCategory().getRessource()));
            		        }
                            }
                catch(URISyntaxException e)
			    {
			    	JOptionPane.showMessageDialog(null, "nous n'avons pas trouver l'URI");
			    }
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
