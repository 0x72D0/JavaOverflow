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
    private String enonce,bonFeedBack,mauvaisFeedBack;
    private Category category;
    short difficulty;
    boolean done;
    // Constructeurs
    
    public Question(String unEnonce, ArrayList<String>desReponses,Category category,short difficulty,String bonFeedBack,String mauvaisFeedBack,boolean done){
    	
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
    * @return l'array de reponse
	*/
	public ArrayList<String> getReponses() {
		return reponses;
	}
	/**
	 * mutateur permettant de modifier une réponse
	 * @param reponses
	 */
	public void setReponses(ArrayList<String> reponses) {
		this.reponses = reponses;
	}
    
	/**
     * accesseur retournant l'énoncé de l'objet question
     * @return l'enoncer de la question
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
	 * @para<<<<<<< HEADm pos
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
     * @param bonFeedBack
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
    public void setMauvaisFeedBack(String mauvaisFeedBack)
    {
        this.mauvaisFeedBack = mauvaisFeedBack;
    }

    /**
     * retourne la categorie actuelle
     * @return category actuelle
     */
    public Category getCategory()
    {
        return category;
    }

    /**
     * modifie la category
     * @param category nouvelle categorie
     */
    public void setCategory(Category category)
    {
        this.category = category;
    }

    /**
     * retourne la difficulte actuelle
     * @return difficulty la difficulte actuelle
     */
    public short getDifficulty()
    {
        return difficulty;
    }

    /**
     * modifier la difiiculte actuelle
     * @param difficulty
     */
    public void setDifficulty(short difficulty)
    {
        this.difficulty = difficulty;
    }
    
    /**
     * mutateur de la variable done
     * @param done 
     */
    public void setDone(boolean done)
    {
        this.done=done;
    }
    
    /**
     * acesseur de la variable done
     * @return la valeur de done 
     */
    public boolean isDone()
    {
        return done;
    }
}
    
    
    
    
