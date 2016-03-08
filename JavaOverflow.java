import java.util.*;

/**
 * Class JavaOverflow
 */
public class JavaOverflow {

    //
    // Fields
    //

    public static Database database;
	public static Question cwq; // cwq : current working question
    
    //
    // Constructors
    //
    public JavaOverflow () { };
    
    //
    // Methods
    //

	public static void generateQuestion()
	{
		Random rand = new Random();
		int i = rand.nextInt(database.getQuestions().size()-1);
		cwq = database.getQuestions().get(i);
	}

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
