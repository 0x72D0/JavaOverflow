/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.io.Serializable;

/**
 *
 * @author waldo
 */

public class User implements Serializable{
    private short points;
    private Category difficulty;
    private short nbreEssaiRate;
    private String mdp;
    
    /**
     * 
     * Basic Constructor
     */
    public User()
    {
        this.points = 0;
        this.difficulty = new Category();
        this.nbreEssaiRate = 0;
        this.mdp ="";
    }
    
    public User(String mdp)
    {
        this.points = 0;
        this.difficulty = new Category();
        this.nbreEssaiRate = 0;
        this.mdp = mdp;
    }
    
    /**
     * 
     * @param pts initial value of points
     * @param diff initial value of difficulty
     * @param nbreEssaiRate initial value of nbreEssaiRate
     * @param mdp initial User password
     */
    public User(short pts,Category diff,short nbreEssaiRate, String mdp)
    {
        this.points = pts;
        this.difficulty = diff;
        this.nbreEssaiRate = nbreEssaiRate;
        this.mdp = mdp;
    }
    /**
     * 
     * @return actual Value of mdp
     */
    public String getMdp() {
        return mdp;
    }
    /**
     * 
     * @param mdp new value of mdp;
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    /**
     * 
     * @return points value 
     */
    public short getPoints(){return this.points;}
    /**
     * 
     * @return Category object representing difficulty
     */
    public Category getDifficulty(){return this.difficulty;}
    /**
     * 
     * @param newPts new value of pts
     */
    public void setPoints(short newPts){this.points = newPts;}
    /**
     * 
     * @param newDiff  new Value of difficulty
     */
    public void setDifficulty(Category newDiff){this.difficulty = newDiff;}
    /**
     *
     * @param ptsQty number of pts to add
     */
    public void add(short ptsQty){this.points+=ptsQty;}
    /**
     * 
     * @return actual number of unsuccessful try for this category
     */
    public short getNbreEssaiRate(){return this.nbreEssaiRate;}
    /**
     * 
     * @param newNbreEssai new value of nbreEssaiRate
     */
    public void setNbreEssaiRate(short newNbreEssai){this.nbreEssaiRate = newNbreEssai;}
    
}
