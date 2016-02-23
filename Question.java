import java.util.ArrayList;

/**
 * Class Question
 */
public class Question {

    //
    // Fields
    //

    private ArrayList<String> reponse;
    private String enonce;
    
    //
    // Constructors
    //
    public Question () { };
    
    //
    // Methods
    //


    //
    // Accessor methods
    //

    /**
     * Set the value of reponse
     * @param newVar the new value of reponse
     */
    private void setReponse (ArrayList<String> newVar) {
        reponse = newVar;
    }

    /**
     * Get the value of reponse
     * @return the value of reponse
     */
    private ArrayList<String> getReponse () {
        return reponse;
    }

    /**
     * Set the value of enonce
     * @param newVar the new value of enonce
     */
    private void setEnonce (String newVar) {
        enonce = newVar;
    }

    /**
     * Get the value of enonce
     * @return the value of enonce
     */
    private String getEnonce () {
        return enonce;
    }

    //
    // Other methods
    //

}
