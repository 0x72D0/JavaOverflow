//push comment
package Controlleur;

import Modele.*;
import javafx.application.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.util.*;
import java.io.*;
import java.awt.Desktop;
import java.net.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JOptionPane;

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
    public static short userProgress=1;
    public static boolean confirmClose = true;
    //
    // Constructors
    //

    public JavaOverflow () { };
    //
    // Methods
    //
    
    /**
    * Initialise la base de donne en chargeant les fichiers questions. 
    */
	public static void generateQuestion()
	{
		boolean allDone = true;
		boolean catAllDone = true;
        
        for(Question q : database.getQuestions())
        {
            if(!q.isDone())
            {
                allDone = false;
                break;
            }
            
            if(q.getCategory().getAvgDiff()==userProgress)
            {
				if(!q.isDone())
					catAllDone=false;
			}
			if(!allDone&&!catAllDone)
				break;
        }
        
        Random rand = new Random();
        int i = rand.nextInt(database.getQuestions().size());
        if(allDone)
            System.out.println("Toutes les questions ont étées réussies");
        else
        {
			if(database.getQuestions().get(i).getCategory().getAvgDiff()==userProgress)
				if(database.getQuestions().get(i).getDifficulty()==categoryLevel(database.getQuestions().get(i).getCategory()))
					if(!database.getQuestions().get(i).isDone())
						cwq = database.getQuestions().get(i);
					else
						generateQuestion();
				else
					generateQuestion();
			else
				generateQuestion();
        }
	}
    
    public static void adminPassword(String password)
    {
        database.setPassword(password);
    }
    
    public static void adminTryQuestionsPresence(String fileName)
    {
        try
        {
            String buf = "";
            Question q = findQuestionFile(fileName);
            buf = "question:\n" + q.getEnonce();
            buf += "\nreponse:\n";
            for(String str : q.getReponses())
            {
                buf += str + "\n";
            }
            buf += "\ncategorie:\n" + q.getCategory().getName() + "\n\ndifficulter:\n" + q.getDifficulty() +
                "\n\nbon enoncer:\n" + q.getBonFeedBack() + "\n\nmauvais enoncer:\n" +q.getMauvaisFeedBack();
            
            JOptionPane.showMessageDialog(null, buf);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * allow the admin to check if the website is good 
     * @param category 
     */
    public static void adminTryWebsite(String category)
    {
        try
        {
            ArrayList<Category> categories = database.getCategory();
            Category cat = null;
            int i = 0;
        
            for(Category catego : categories)
            {
                if(catego.getName().equals(category))
                {
                    cat = catego;
                    break;
                }
                else
                {
                    i++;
                }
            }
            
            if(i == categories.size())
            {
                JOptionPane.showMessageDialog(null, "la category n'a pas ete trouver");
            }
        
            if(Desktop.isDesktopSupported())
            {
                System.out.println(cat.getRessource());
                Desktop.getDesktop().browse(new URI(cat.getRessource()));
            }
            else
            {
                JOptionPane.showMessageDialog(null, "le desktop n'est pas supporter par Java");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
    * formatte le String de la reponse et de la question pour tester leur egalite
    * @param str1
    * @return le string une fois formatter 
    */
	public static String formatString(String str1)
	{
		str1 = str1.toLowerCase();
		StringBuffer str = new StringBuffer(str1);
        for(int i = 0; i < str.length(); i++)
        {
            char a = str.charAt(i);
            
            switch(a)
            {
                case 224:str.setCharAt(i, 'a');
                break;
                case 226: str.setCharAt(i, 'a');
                break;
                
                case 232: str.setCharAt(i, 'e');
                break;
                case 233: str.setCharAt(i, 'e');
                break;
                case 234: str.setCharAt(i, 'e');
                break;
                case 235: str.setCharAt(i, 'e');
                break;
                
                case 238: str.setCharAt(i, 'i');
                break;
                case 239: str.setCharAt(i, 'i');
                break;
                
                case 251: str.setCharAt(i, 'u');
                break;
            }
        }
        return str.toString();
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
                database.getEleve().add(cwq.getDifficulty());
                System.out.println("vous avez "+database.getEleve().getPoints()+"points");
				return true;
			}
		}
		
		return verifyFormatAnswer(rep, formatString(cwq.getReponses().get(cwq.getReponses().size()-1)));
	}
    
    
	/**
     * check for a matching regex
     * @param rep
     * @param ans
     * @return true si les regex matche sinon false
     */
	public static boolean verifyFormatAnswer(String rep, String ans)
	{
        cwq.setDone(true);
        database.getEleve().add(cwq.getDifficulty());
		return rep.matches(ans);
	}
	
	public static short categoryLevel(Category category)
    {
        
        for(short level =1;level<101;level++)
        {
            for(Question q:database.getQuestions())
            {
                if(q.getDifficulty()==level) {
                    if(q.getCategory().getName().equals(category.getName())) 
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
    
    public static Question findQuestionFile(String fileName)
    {
        for(Question q : database.getQuestions())
        {
            if(q.getName().equals(fileName))
            {
                return q;
            }
        }
        
        return null;
    }

    public static void main(String[]args)
    {			
			launch(args); //Lance JavaFx
    }
    
	public void start(Stage primaryStage) throws Exception {
        
        try
		{
			Path currentRelativePath = Paths.get("");
			File seri = new File(currentRelativePath.toAbsolutePath().toString()+File.separator+ "database.Jobj");
			FileInputStream fin = new FileInputStream(seri);
			ObjectInputStream ois = new ObjectInputStream(fin);
			JavaOverflow.database = (Database) ois.readObject();
			System.out.println(JavaOverflow.database.getCategory().size());

		}
        catch(Exception e)
        {
			e.printStackTrace();
		}
		
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
        if(confirmClose)
        {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Fermeture");
            alert.setHeaderText("Fermeture du programme");
            alert.setContentText("Etes-vous sur de vouloir fermer?");
            Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
        {       
                try
				{
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
			theStage.close();
		}
        }
        else
        {
            try
				{
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
			theStage.close();
        }
	}
	

}
