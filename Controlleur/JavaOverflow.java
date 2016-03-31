//push comment
package Controlleur;

import Modele.*;
import Vue.*;
import javafx.application.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.util.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class JavaOverflow
 * classe principale initialisant le GUI ainsi que la base de donnee.
 * @author Lucas 23 fevrier 2016
 */
public class JavaOverflow extends Application{

    //
    // Fields
    //param return desc

    public static Database database;
	public static Question cwq; // cwq : current working question
    
    //
    // Constructors
    //

    public JavaOverflow () { };
    
    //
    // Methods
    //
/**
 * Initialise la base de donne en chargeant les fichiers questions. 
 * @return void
 */
	public static void generateQuestion()
	{	boolean allDone = true;
		for(Question q:database.getQuestions())
		{	if(!q.isDone())
				allDone=false;
		}
		Random rand = new Random();
		int i = rand.nextInt(database.getQuestions().size()-1);
		if(allDone)
			System.out.println("Toutes les questions ont étées réussies");
		else
			if(database.getQuestions().get(i).getDifficulty()==categoryLevel(database.getQuestions().get(i).getCategory()))
				if(!database.getQuestions().get(i).isDone())
					cwq = database.getQuestions().get(i);
				else
					generateQuestion();
			else
				generateQuestion();
	}
/**
 * Verifie une reponse entre en parametre en la comparant avec les reponses
 * de la question actuelle.
 * @param rep Une reponse  de l'utilisateur
 * @return Vrai si la reponse est vrai, faux dans le cas contraire.
 */
 
	public static String formatString(String str)
	{
		str = str.toLowerCase();
		return str;
	}
	
	public static boolean verifyStringAnswer(String rep)
	{	
		// dans cet algorithme rep est la reponse entree par l'utilisateur
		// et ici answer represente les reponse attendu par le programme
		
		rep = formatString(rep);
		System.out.println(rep);
		
		for(String ans : cwq.getReponses())
		{
			ans = formatString(ans);
			System.out.println(ans);
			if(rep.equals(ans))
			{
				cwq.setDone(true);
				return true;
			}
		}
		
		return verifyFormatAnswer(rep, formatString(cwq.getReponses().get(cwq.getReponses().size()-1)));
	}
	
	public static boolean verifyFormatAnswer(String rep, String ans)
	{
		String[] forAns = ans.split(" ");
		String[] forRep = rep.split(" ");
		
		
		for(int i = 0; i < forAns.length; i++)
		{
			String bufAns = forAns[i];
			String bufRep = forRep[i];
			
			//check l'answer
			if(bufAns.charAt(0) == '/')
			{
				if(bufAns.charAt(1) == '*')
				{
					System.out.println("found");
					continue;
				}
				else if(bufAns.charAt(1) == '/')
				{
					bufAns = '/' + bufAns.substring(2);
				}
			}
			
			//apres check l'egalite
			if(bufAns.equals(bufRep))
			{
				continue;
			}
			else
			{
				return false;
			}
		}
		return true;
	}
	
	public static short categoryLevel(String category)
    {
        
        for(short level =1;level<101;level++)
        {
            for(Question q:database.getQuestions())
            {
                if(q.getDifficulty()==level) {
                    if(q.getCategory().equals(category)) {

                        if(!q.isDone()) {
                            return level;
                        }

                    }
                }
            }
        }
        return 100;
    }

    //
    // Accessor methods
    //
  

    //
    // Other methods
    //

    public static void main(String[]args)
    {
    	
		try
		{
			Path currentRelativePath = Paths.get("");
			File seri = new File(currentRelativePath.toAbsolutePath().toString()+File.separator+ "database.Jobj");
			FileInputStream fin = new FileInputStream(seri);
			ObjectInputStream ois = new ObjectInputStream(fin);
			database = (Database) ois.readObject();
			System.out.println(database.getCategory().size());
			
			//new MainMenu(); Plus besoin vu que on utilize pas swing
			
			launch(args); //Lance JavaFx
			
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    
	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("/Vue/Accueil.fxml"));
		
		Scene scene = new Scene(root, 600,400);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("JavaOverflow - Acceuil");
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(e -> {
			e.consume();
			closeProgram(primaryStage);
		});	
		
	}
	
	public static void closeProgram(Stage theStage){
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Fermeture");
		alert.setHeaderText("Fermeture du programme");
		alert.setContentText("Etes-vous sur de vouloir fermer?");
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			theStage.close();
		} else {
		   
		}
	}
	

}
