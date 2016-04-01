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
<<<<<<< HEAD
    
=======
>>>>>>> 973bacf3f1323b2baa302b2065e5469c1a08a4ec
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
<<<<<<< HEAD
    
    /**
	* Accesseur permettant de retourner la réponse
    * @return l'array de reponse
=======
    	/**
	* Accesseur permettant de retourner la réponse
>>>>>>> 973bacf3f1323b2baa302b2065e5469c1a08a4ec
	*/
	public ArrayList<String> getReponses() {
		return reponses;
	}
<<<<<<< HEAD
    
	/**
	 * mutateur permettant de modifier une réponse
	 * @param reponses
=======
	/**
	 * mutateur permettant de modifier une réponse
	 * @param reponses
	 * 	ArrayList de réponses
>>>>>>> 973bacf3f1323b2baa302b2065e5469c1a08a4ec
	 */
	public void setReponses(ArrayList<String> reponses) {
		this.reponses = reponses;
	}
<<<<<<< HEAD
    
	/**
     * accesseur retournant l'énoncé de l'objet question
     * @return l'enoncer de la question
     */
=======
	/**
	 *accesseur retournant l'énoncé de l'objet question
	 */
>>>>>>> 973bacf3f1323b2baa302b2065e5469c1a08a4ec
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
<<<<<<< HEAD
=======
	 * 	Int, chiffre qui identifie la position de la réponse dans le arrayList	
>>>>>>> 973bacf3f1323b2baa302b2065e5469c1a08a4ec
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
<<<<<<< HEAD
     * @param bonFeedBack
=======
     * @param bonFeedBack nouveau feedback
>>>>>>> 973bacf3f1323b2baa302b2065e5469c1a08a4ec
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
<<<<<<< HEAD
    public void setMauvaisFeedBack(String mauvaisFeedBack) {
=======
    public void setMauvaisFeedBack(String mauvaisFeedBack)
    {
>>>>>>> 973bacf3f1323b2baa302b2065e5469c1a08a4ec
        this.mauvaisFeedBack = mauvaisFeedBack;
    }

    /**
     * retourne la categorie actuelle
     * @return category actuelle
     */
<<<<<<< HEAD
    public String getCategory() {
=======
    public String getCategory()
    {
>>>>>>> 973bacf3f1323b2baa302b2065e5469c1a08a4ec
        return category;
    }

    /**
     * modifie la category
     * @param category nouvelle categorie
     */
<<<<<<< HEAD
    public void setCategory(String category) {
=======
    public void setCategory(String category)
    {
>>>>>>> 973bacf3f1323b2baa302b2065e5469c1a08a4ec
        this.category = category;
    }

    /**
     * retourne la difficulte actuelle
     * @return difficulty la difficulte actuelle
     */
<<<<<<< HEAD
    public short getDifficulty() {
=======
    public short getDifficulty()
    {
>>>>>>> 973bacf3f1323b2baa302b2065e5469c1a08a4ec
        return difficulty;
    }

    /**
     * modifier la difiiculte actuelle
     * @param difficulty
     */
<<<<<<< HEAD
    public void setDifficulty(short difficulty) {
=======
    public void setDifficulty(short difficulty)
    {
>>>>>>> 973bacf3f1323b2baa302b2065e5469c1a08a4ec
        this.difficulty = difficulty;
    }
    
    /**
<<<<<<< HEAD
     * mutateur de done
=======
     * mutateur de la variable done
>>>>>>> 973bacf3f1323b2baa302b2065e5469c1a08a4ec
     * @param done 
     */
    public void setDone(boolean done)
    {
        this.done=done;
    }
<<<<<<< HEAD
    
    /**
     * acesseur de done
     * @return retourne la variable done
=======
    /**
     * acesseur de la variable done
     * @return la valeur de done 
>>>>>>> 973bacf3f1323b2baa302b2065e5469c1a08a4ec
     */
    public boolean isDone()
    {
        return done;
    }
}
    
    
    
    
