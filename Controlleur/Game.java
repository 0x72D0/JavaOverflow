package Controlleur;

import Vue.*;
import Controlleur.JavaOverflow;
import java.awt.Desktop;
import java.awt.Dimension;
import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Game implements Initializable{

	@FXML TextField fieldReponse;
	@FXML Button btConfirmer, retour;
	@FXML TextArea textArea;
	@FXML Menu menuFichier,menuEdition,menuAide;
	@FXML MenuItem itemFermer,itemCopier,itemAide;
	@FXML BorderPane borderPane;
	@FXML Label scoreLabel;
	
	private Stage currentStage;
	public static String textAreaColor = "Black";
	public static int textAreaPolice = 16;
	
	@Override
	public void  initialize(URL location, ResourceBundle resources){

		JavaOverflow.generateQuestion();
		textArea.setText(JavaOverflow.cwq.getEnonce());
		scoreLabel.setText("Score: "+JavaOverflow.cs.getPoints());
        btConfirmer.setDefaultButton(true);
		textArea.setStyle("-fx-font: "+ textAreaPolice +" arial");
		textArea.setStyle("-fx-text-fill: "+ textAreaColor);
        
        fieldReponse.addEventFilter(KeyEvent.KEY_PRESSED, 
		        new EventHandler<KeyEvent>() {
		    public void handle(KeyEvent event) {
		        if(event.getCode() == KeyCode.ENTER) {
		        	try {
						confirmerReponse();
					} catch (IOException e){
						
						e.printStackTrace();
					}
		        	
		        } 

		    };
		});
	}
	
	public void setStage(Stage theStage)
	{
		this.currentStage = theStage;
	}
	/**
     *  gere le bouton confirmer.
     * @param event
     * @throws IOException 
     */
	public void btConfirmerHandler(ActionEvent event) throws IOException
    {
		try 
        {
            confirmerReponse();
		} 
        catch (IOException e)
        {	
            e.printStackTrace();
		}
	}
    
	public void btSuivantHandler(ActionEvent event){
		
		JavaOverflow.generateQuestion();
		textArea.setText(JavaOverflow.cwq.getEnonce());
		
	}
    
    /**
     * gere l'option fermer
     * @param event 
     */
	public void itemFermerHandler(ActionEvent event){
		
		JavaOverflow.closeProgram((Stage)borderPane.getScene().getWindow());

	}
	public void itemCopierhandler(ActionEvent event){
		
		
	}
    /**
     * gere l'option aide.
     * @param event 
     */
	public void itemAidehandler(ActionEvent event)
    {
		JOptionPane.showMessageDialog(null, JavaOverflow.aide_utilisateur());
	}
	public void fieldReponseHandler(ActionEvent event){
		
		
	}
    /**
     * gere le bouton retour.
     * @param event 
     */
    public void handleReturn(ActionEvent event)
    {
        try
        {
            launchPane("/Vue/Accueil.fxml","JavaOverflow - Acceuil",1,600,400);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * gere la confirmation de reponse.
     * @throws IOException 
     */
    public void confirmerReponse() throws IOException{
		if(JavaOverflow.verifyStringAnswer(fieldReponse.getText())){
			
		JTextArea text = new JTextArea(JavaOverflow.cwq.getBonFeedBack());
        JScrollPane scrollPane = new JScrollPane(text);  
        text.setLineWrap(true);  
        text.setWrapStyleWord(true); 
        scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
        JOptionPane.showMessageDialog(null, scrollPane, "bonne reponse!", JOptionPane.PLAIN_MESSAGE);
			
			JavaOverflow.generateQuestion();
			textArea.setText(JavaOverflow.cwq.getEnonce());
			fieldReponse.setText("");
			scoreLabel.setText("Score: "+JavaOverflow.cs.getPoints());
			
		}
        else
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
                    JOptionPane.showMessageDialog(null, "echec de l'URI");
                }
            }
            
            JTextArea textArea = new JTextArea(JavaOverflow.cwq.getMauvaisFeedBack());
            JScrollPane scrollPane = new JScrollPane(textArea);  
            textArea.setLineWrap(true);  
            textArea.setWrapStyleWord(true); 
            scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
            JOptionPane.showMessageDialog(null, scrollPane, "mauvaise reponse!", JOptionPane.PLAIN_MESSAGE);
                        
		}
		
	}
    /**
     * 
     * @param ressourcePath
     * @param paneTitle
     * @param mode
     * @param sizex
     * @param sizey
     * @throws IOException 
     */
	public void launchPane(String ressourcePath,String paneTitle,int mode,int sizex,int sizey) throws IOException
	{
		Stage currentStage = (Stage)retour.getScene().getWindow();
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
	/*public void textAreaHandler(ActionEvent event){
		
		
	}*/
	
	
}
