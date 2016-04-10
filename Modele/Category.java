/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author Lucas Mongrain, 2016/04/10
 */
public class Category 
{
    private String name;
    private String ressource;
    private short avgDiff;
    
    public Category()
    {
        this.name = "";
        this.ressource = "";
        this.avgDiff = 1;
    }
    
    public Category(String name, String ressource,short diff)
    {
        this.name = name;
        this.ressource = ressource;
        this.avgDiff = diff;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getRessource()
    {
        return ressource;
    }
    
    public short getAvgDiff()
    {
		return avgDiff
	}
    
    public void setName(String str)
    {
        name = str;
    }
    
    public void setRessource(String str)
    {
        ressource = str;
    }
}
