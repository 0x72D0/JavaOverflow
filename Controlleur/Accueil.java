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
import javafx.fxml.Initializable;

public class Accueil implements Initializable{
	
	@FXML Button btCommencer,btOptions,btGenererQ,btQuitter;
	private String password = "kfc";
	@Override
	public void  initialize(URL location, ResourceBundle resources){
		
		
	}
	
	
	public void handlerCommencer(ActionEvent event) throws IOException
	{
		try
		{
			Path currentRelativePath = Paths.get("");
			File seri = new File(currentRelativePath.toAbsolutePath().toString()+File.separator+ "database.Jobj");
			FileInputStream fin = new FileInputStream(seri);
			ObjectInputStream ois = new ObjectInputStream(fin);
			JavaOverflow.database = (Database) ois.readObject();
			System.out.println(JavaOverflow.database.getCategory().size());
		
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
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	public void handlerOptions(ActionEvent event)
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Oops!");
		alert.setHeaderText("Attention");
		alert.setContentText("Cette option n'est pas disponible dans cette version.");
		alert.showAndWait(); 
	}
	
	public void handleGenererQ(ActionEvent event)
	{
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Mot de passe");
		dialog.setHeaderText("Mot de passe requit pour continuer");
		dialog.setContentText("Entrer votre mot de passe:");
		Optional<String> result = dialog.showAndWait();
		
		if(result.isPresent())
		{
			if(result.get().equals(password)){
				try
				{
					JavaOverflow.database = new Database();
					Path currentRelativePath = Paths.get("");
					File seri = new File(currentRelativePath.toAbsolutePath().toString()+File.separator+ "database.Jobj");
					FileOutputStream fout = new FileOutputStream(seri);
					ObjectOutputStream oos = new ObjectOutputStream(fout);
					oos.writeObject(JavaOverflow.database);
					oos.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}else{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("JavaOverflow");
				alert.setHeaderText("Attention");
				alert.setContentText("Mot de passe incorrect!");
				alert.showAndWait();
			}
		}
	}
	
	public void handlerQuitter(ActionEvent event)
	{
		JavaOverflow.closeProgram((Stage)btQuitter.getScene().getWindow());
	}
}
