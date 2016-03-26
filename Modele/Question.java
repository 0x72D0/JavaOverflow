package Modele;
/*Giovanni E. Desroches
 * Description: Classes representants une question, les questions seront entreposer dans
 * la classe database.
 */

import java.util.ArrayList;
import java.io.*;

/**
 * Class Question
 * classe qui intialise les objets questions avec ses attributs, questions et réponses, avec les accesseurs et les mutateurs
 * @author: Duy, 2016/02/16
 */
public class Question implements Serializable{

    // Attributs
    private ArrayList<String> reponses;
    private String enonce,bonFeedBack,mauvaisFeedBack,category;
    short difficulty;
    boolean done;
    // Constructeurs
    /**
     *Constructeur qui initialise chaque objet question et permet d'identifier les énoncés et les réponses
     */
    public Question(String unEnonce, ArrayList<String>desReponses,String category,short difficulty,String bonFeedBack,String mauvaisFeedBack,boolean done){
    	
    	this.enonce = unEnonce;
    	this.reponses = desReponses;
        this.category = category;
        this.difficulty = difficulty;
        this.bonFeedBack = bonFeedBack;
        this.mauvaisFeedBack = mauvaisFeedBack;
        this.done = done;
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

    /**
     *retourne bonfeedback actuel
     * @return bonFeedBack le feedback actuel
     */
    public String getBonFeedBack() {
        return bonFeedBack;
    }

    /**
     * modifie le bon feedback
     * @param bonFeedBack nouveau feedback
     */
    public void setBonFeedBack(String bonFeedBack) {
        this.bonFeedBack = bonFeedBack;
    }

    /**
     *retourne feedback actuel
     * @return mauvais FeedBack actuel
     */
    public String getMauvaisFeedBack() {
        return mauvaisFeedBack;
    }

    /**
     * change le mauvais feedback
     * @param mauvaisFeedBack nouveau feedback
     */
    public void setMauvaisFeedBack(String mauvaisFeedBack) {
        this.mauvaisFeedBack = mauvaisFeedBack;
    }

    /**
     * retourne la categorie actuelle
     * @return category actuelle
     */
    public String getCategory() {
        return category;
    }

    /**
     * modifie la category
     * @param category nouvelle categorie
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * retourne la difficulte actuelle
     * @return difficulty la difficulte actuelle
     */
    public short getDifficulty() {
        return difficulty;
    }

    /**
     * modifier la difiiculte actuelle
     * @param difficulty
     */
    public void setDifficulty(short difficulty) {
        this.difficulty = difficulty;
    }
    
    public void setDone(boolean done){this.done=done;}
    
    public boolean isDone(){return done;}
}
    
    
    
    
