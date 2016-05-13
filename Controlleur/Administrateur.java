package Controlleur;

import Vue.*;

import java.util.Optional;
import java.util.ResourceBundle;

import Modele.Database;
import java.awt.Dimension;

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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Administrateur implements Initializable{
	
	@FXML TextField tfPassword,tfConfirmer,tfCategorie,tfQuestion;
	@FXML Button btAppliquerA,btAppliquerC,btVerifier, aide_admin, aide_web, aide_questions, quitter_admin, quitter_web, quitter_question;

	@Override
	public void  initialize(URL location, ResourceBundle resources){

	
	}
	//Bouton admin
	public void handlerBtAppliquerA(ActionEvent event)
	{
        if(tfPassword.getText().equals(tfConfirmer.getText()))
        {
            if(tfPassword.getText().equals(""))
            {
                final Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("mot de passe faible");
                alert.setHeaderText("attention!!!");
                alert.setContentText("etes-vous sur de vouloir une chaine de \ncaractere vide comme mot de passe?");

                alert.getButtonTypes().clear();
                alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);

                final Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.YES)
                {
                    JavaOverflow.adminPassword(tfPassword.getText());
                }
            }
            else
            {
                JavaOverflow.adminPassword(tfPassword.getText());
            }
            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "les mots de passe ne sont pas identique");
        }
	}
	
	public void aide_handle(ActionEvent event)
	{
		JTextArea textArea = new JTextArea(JavaOverflow.aide_adminstrateur());
        JScrollPane scrollPane = new JScrollPane(textArea);  
        textArea.setLineWrap(true);  
        textArea.setWrapStyleWord(true); 
        scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
        JOptionPane.showMessageDialog(null, scrollPane, "dialog test with textarea", JOptionPane.PLAIN_MESSAGE);
	}
	//Bouton Categorie
	public void handlerbtAppliquerC(ActionEvent event){
		JavaOverflow.adminTryWebsite(tfCategorie.getText());	
	}
	public void handlerbtVerifier(ActionEvent event){
		JavaOverflow.adminTryQuestionsPresence(tfQuestion.getText());
	}
    
    public void handleExit(ActionEvent event)
    {
        Stage currentStage = (Stage)btVerifier.getScene().getWindow();
        currentStage.hide();
    }
	
	
}
