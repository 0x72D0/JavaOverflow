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
    
    public Category()
    {
        this.name = "";
        this.ressource = "";
    }
    
    public Category(String name, String ressource)
    {
        this.name = name;
        this.ressource = ressource;
    }
    
    public String getname()
    {
        return name;
    }
    
    public String getRessource()
    {
        return ressource;
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
