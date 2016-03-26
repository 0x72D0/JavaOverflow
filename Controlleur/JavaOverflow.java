//push comment
package Controlleur;

import Modele.*;
import Vue.*;
import java.util.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class JavaOverflow
 * classe principale initialisant le GUI ainsi que la base de donnee.
 * @author Lucas 23 fevrier 2016
 */
public class JavaOverflow {

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
	{
		Random rand = new Random();
		int i = rand.nextInt(database.getQuestions().size()-1);
		cwq = database.getQuestions().get(i);
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
		
		rep = rep.toLowerCase();
		
		for(String ans : cwq.getReponses())
		{
			if(rep.equals(ans))
			{
				cwq.setDone(true);
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean verifyFormatAnswer(String rep, String ans)
	{
		
		for(int i = 0; i < ans.length(); i++)
		{
			if(rep.charAt(i) == '/')
			{
				
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
			new MainMenu();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }


}
