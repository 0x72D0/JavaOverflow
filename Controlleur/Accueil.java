package Controlleur;

import Vue.*;
import Modele.*;

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
import javafx.fxml.Initializable;
/**
 * gere les evenements de la fenetre d'acceuil.
 * @author Giovanni
 */
public class Accueil implements Initializable{
	
	@FXML Button btCommencer,btOptions,btGenererQ,btQuitter,btAdministrateur;
	
	//private String password = "kfc";
	@Override
	public void  initialize(URL location, ResourceBundle resources){
		
		
	}
	
	/**
     * gere les evenement du bouton commencer
     * @param event
     * @throws IOException 
     */
	public void handlerCommencer(ActionEvent event) throws IOException
	{
        if(JavaOverflow.database.isPosessQuestions())
        {
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Mot de passe");
            dialog.setHeaderText("Mot de passe requit pour continuer");
            dialog.setContentText("Entrer votre mot de passe:");
            Optional<String> result = dialog.showAndWait();

            if(result.isPresent())
            {
                JavaOverflow.setCs(result.get());
                launchPane("/Vue/Game.fxml","JavaOverflow",1,755,500);
            }
        }
        else
        {
            Alert alert = new Alert(AlertType.WARNING,"votre database ne possede pas de question");
			alert.setTitle("JavaOverflow");
			alert.setHeaderText("Attention");
            alert.showAndWait();
        }
	}
	
    /**
     * gere les evenements du bouton option
     * @param event 
     */
	public void handlerOptions(ActionEvent event)
	{
		try{
			launchPane("/Vue/Option.fxml","JavaOverflow - Options",0,450,480);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
    /**
     * gere les evenements du bouton generer Questions
     * @param event 
     */
	public void handleGenererQ(ActionEvent event)
	{   
        TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Mot de passe");
		dialog.setHeaderText("Mot de passe requit pour continuer");
		dialog.setContentText("Entrer votre mot de passe:");
		Optional<String> result = dialog.showAndWait();
		
		if(result.isPresent())
		{
			if(result.get().equals(JavaOverflow.database.getPassword())){

				
				try 
                {
					JavaOverflow.database.generateDatabase();
                    Path currentRelativePath = Paths.get("");
                    File seri = new File(currentRelativePath.toAbsolutePath().toString()+File.separator+ "database.Jobj");
                    FileOutputStream fout = new FileOutputStream(seri);
                    ObjectOutputStream oos = new ObjectOutputStream(fout);
                    oos.writeObject(JavaOverflow.database);
                    oos.close();

                    Alert alert = new Alert(AlertType.INFORMATION,"Operation Reussi");
                    alert.setHeaderText("Creation questions");
                    alert.showAndWait();
				} 
                catch (IOException e) 
                {
					e.printStackTrace();
				}
			}
            else
            {
				Alert alert = new Alert(AlertType.WARNING,"Mot de passe incorrect!");
				alert.setTitle("JavaOverflow");
				alert.setHeaderText("Attention");
				alert.showAndWait();
			}
		}
	}
	
	/**
     * gere le bouton administrateur
     * @param event 
     */
	public void handlerAdministrateur(ActionEvent event)
	{
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Mot de passe");
		dialog.setHeaderText("Mot de passe requit pour continuer");
		dialog.setContentText("Entrer votre mot de passe:");
		Optional<String> result = dialog.showAndWait();
		
		if(result.isPresent())
		{
			if(result.get().equals(JavaOverflow.database.getPassword())){

				
				try {
					launchPane("/Vue/Administrateur.fxml","JavaOverflow - Admin",0,500,400);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
            else
            {
				Alert alert = new Alert(AlertType.WARNING,"Mot de passe incorrect!");
				alert.setTitle("JavaOverflow");
				alert.setHeaderText("Attention");
				alert.showAndWait();
			}
		}
		
	}
	
	/**
     * gere le bouton quitter
     * @param event 
     */
	public void handlerQuitter(ActionEvent event)
	{
		JavaOverflow.closeProgram((Stage)btQuitter.getScene().getWindow());
	}
	
    /**
     * permet de lancer une fenetre rapidement.
     * @param ressourcePath
     * @param paneTitle
     * @param mode
     * @param sizex
     * @param sizey
     * @throws IOException 
     */
	public void launchPane(String ressourcePath,String paneTitle,int mode,int sizex,int sizey) throws IOException
	{
		Stage currentStage = (Stage)btCommencer.getScene().getWindow();
		if(mode==1){
			currentStage.hide();
		}
		
		Parent root = FXMLLoader.load(getClass().getResource(ressourcePath));
		
		Scene scene = new Scene(root,sizex,sizey);
		
		
		Stage gameStage = new Stage();
		gameStage.setTitle(paneTitle);
		gameStage.setScene(scene);
		gameStage.show();
		
		gameStage.setOnCloseRequest(e ->{
			e.consume();
			JavaOverflow.closeProgram(gameStage);
		});
	}
	
}
