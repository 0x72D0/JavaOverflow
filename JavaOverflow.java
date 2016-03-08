import java.util.*;

/**
 * Class JavaOverflow
 */
public class JavaOverflow {

    //
    // Fields
    //

    public static Database database;
    
    //
    // Constructors
    //
    public JavaOverflow () { };
    
    //
    // Methods
    //

	public static Question generateQuestion()
	{
		Random rand = new Random();
		int i = rand.nextInt(database.getQuestions().size()-1);
		return database.getQuestions().get(i);
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
