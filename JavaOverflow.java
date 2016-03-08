import java.util.*;

/**
 * Class JavaOverflow
 * classe principale initialisant le GUI ainsi que la base de donnee.
 * @author Lucas
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
 * @param void
 * @return void
 * /
	public static void generateQuestion()
	{
		Random rand = new Random();
		int i = rand.nextInt(database.getQuestions().size()-1);
		cwq = database.getQuestions().get(i);
	}
/**
 * Verifie une reponse entre en parametre en la comparant avec les reponses
 * de la question actuelle.
 * @param Une reponse  de l'utilisateur
 * @return Vrai si la reponse est vrai, faut dans le cas contraire.
 * /
	public static boolean verifyStringAnswer(String rep)
	{
		for(String ans: cwq.getReponses())
		{
			if(rep.equals(ans))
			{
				return true;
			}
		}
		
		return false;
	}


    //
    // Accessor methods
    //
  

    //
    // Other methods
    //

    /**
     */
    public static void main(String[]args)
    {
		database = new Database();
        new MainMenu();
    }


}
