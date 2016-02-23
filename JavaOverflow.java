import java.util.ArrayList;

/**
 * Class JavaOverflow
 */
public class JavaOverflow {

    //
    // Fields
    //

    private ArrayList<Question> database;
    
    //
    // Constructors
    //
    public JavaOverflow () { };
    
    //
    // Methods
    //


    //
    // Accessor methods
    //

    /**
     * Set the value of database
     * @param newVar the new value of database
     */
    private void setDatabase (ArrayList<Question> newVar) {
        database = newVar;
    }

    /**
     * Get the value of database
     * @return the value of database
     */
    private ArrayList<Question> getDatabase () {
        return database;
    }

    //
    // Other methods
    //

    /**
     */
    public static void main(String[]args)
    {
        new MainMenu();
    }


}
