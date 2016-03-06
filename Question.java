/*Giovanni E. Desroches
 * Description: Classes representants une question, les questions seront entreposer dans
 * la classe database.
 * 
 */

import java.util.ArrayList;

/**
 * Class Question
 */
public class Question {

    // Attributs
    private ArrayList<String> reponses;
    private String enonce;

    // Constructeurs
    public Question(String unEnonce, ArrayList<String>desReponses){ 
    	
    	this.enonce = unEnonce;
    	this.reponses = desReponses;
    	
    }
    
    //gets & sets
	public ArrayList<String> getReponses() {
		return reponses;
	}

	public void setReponses(ArrayList<String> reponses) {
		this.reponses = reponses;
	}

	public String getEnonce() {
		return enonce;
	}

	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}
	
	//Methodes
	public String getAnswerX(int pos){
		return reponses.get(pos);
	}
}
    
    
    
    
