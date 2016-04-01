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
     * Verifie une reponse entre en parametre en la comparant avec les reponses
     * de la question actuelle.
     * @param rep Une reponse  de l'utilisateur
     * @return Vrai si la reponse est vrai, faux dans le cas contraire.
     */
	public static void generateQuestion()
	{
        boolean allDone = true;
		for(Question q:database.getQuestions())
		{	
            if(!q.isDone())
            {
				allDone=false;
                break;
            }
		}
		Random rand = new Random();
		int i = rand.nextInt(database.getQuestions().size());
		if(allDone)
			System.out.println("Toutes les questions ont étées réussies");
		else
        {
			if(database.getQuestions().get(i).getDifficulty()==categoryLevel(database.getQuestions().get(i).getCategory()))
				if(!database.getQuestions().get(i).isDone())
					cwq = database.getQuestions().get(i);
				else
					generateQuestion();
			else
				generateQuestion();
        }
	}

    /**
     * formate le String de la reponse et de la reponse de l'utilisateur
     * @param str
     * @return le String une fois formater
     */
	public static String formatString(String str)
	{
		str = str.toLowerCase();
        
        StringBuffer buf = new StringBuffer(str);
        
        for(int i = 0; i < buf.length(); i++)
        {
            char a = buf.charAt(i);
            
            switch(a)
            {
                case '\u00e0':
                    buf.setCharAt(i, 'a');
                    break;
                case '\u00e2':
                    buf.setCharAt(i, 'a');
                    break;
                case '\u00e8':
                    buf.setCharAt(i, 'e');
                    break;
                case '\u00e9':
                    buf.setCharAt(i, 'e');
                    break;
                case '\u00ea':
                    buf.setCharAt(i, 'e');
                    break;
                case '\u00eb':
                    buf.setCharAt(i, 'e');
                    break;
                case '\u00ee':
                    buf.setCharAt(i, 'i');
                    break;
                case '\u00ef':
                    buf.setCharAt(i, 'i');
                    break;
                case '\u00fb':
                    buf.setCharAt(i, 'u');
                    break;
            }
        }
		return buf.toString();
	}
	
    /**
    * Verifie une reponse entre en parametre en la comparant avec les reponses
    * de la question actuelle.
    * @param rep Une reponse  de l'utilisateur
    * @return Vrai si la reponse est vrai, faux dans le cas contraire.
    */
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
	
    /**
     * verifie la reponse modele avec la reponse de l'utilisateur
     * @param rep
     * @param ans
     * @return true si l'egalite est vrai, sinon false 
     */
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
	
    /**
     * calcule le niveau de la categorie pour aider a choisir les futurs questions
     * @param category
     * @return retourne le niveau de la categorie
     */
	public static short categoryLevel(String category)
    {
        for(short level = 1;level < 101;level++)
        {
            for(Question q:database.getQuestions())
            {
                if(q.getDifficulty()==level)
                {
                    if(q.getCategory().equals(category))
                    {
                        if(!q.isDone())
                        {
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
    
    /**
     * le main du programme
     * @param args 
     */
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
		if (result.get() == ButtonType.OK)
        {
			theStage.close();
		}
	}
}
