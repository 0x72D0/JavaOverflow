/*Giovanni E. Desroches
 * Description: Classes representants une question, les questions seront entreposer dans
 * la classe database.
 */

import java.util.ArrayList;

/**
 * Class Question
 * classe qui intialise les objets questions avec ses attributs, questions et réponses, avec les accesseurs et les mutateurs
 * @author: Duy, 2016/02/16
 */
public class Question {

    // Attributs
    private ArrayList<String> reponses;
    private String enonce;

    // Constructeurs
    /**
     *Constructeur qui initialise chaque objet question et permet d'identifier les énoncés et les réponses
     */
    public Question(String unEnonce, ArrayList<String>desReponses){ 
    	
    	this.enonce = unEnonce;
    	this.reponses = desReponses;
    	
    }
    
    //gets & sets
    	/**
	* Accesseur permettant de retourner la réponse
	*/
	public ArrayList<String> getReponses() {
		return reponses;
	}
	/**
	 * mutateur permettant de modifier une réponse
	 * @param reponses
	 * 	ArrayList de réponses
	 */
	public void setReponses(ArrayList<String> reponses) {
		this.reponses = reponses;
	}
	/**
	 *accesseur retournant l'énoncé de l'objet question
	 */
	public String getEnonce() {
		return enonce;
	}
	/**
	 *mutateur pour modifier l'énoncé de la question
	 * @param enonce
	 * 	String, énoncé, question posé
	 */
	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}
	
	//Methodes
	/**
	 *accesseur pour retourner la réponse d'une question
	 * @param pos
	 * 	Int, chiffre qui identifie la position de la réponse dans le arrayList	
	 */
	public String getAnswerX(int pos){
		return reponses.get(pos);
	}
}
    
    
    
    
