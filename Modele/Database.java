package Modele;
/**
 * Cette classe crée un database dans un ArrayList pour poser les questions et prendre les réponses
 * 
 * @author: Duy, 2016/02/16
 */
//Modifications par Gio 2016/03/5

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Database implements Serializable{

	private ArrayList<Question> questions = new ArrayList<Question>();
	private ArrayList<String> category = new ArrayList<String>();
	
	/**
	 * Constructeur de la Database
	 */
	public Database()
	{
		try
		{
			createObject();
		}
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("File not found exception when create the database\n\n");
			e.printStackTrace();
		}
		catch(IOException e) 
		{
            System.out.println("IO exception when create the database\n\n");
            e.printStackTrace();
        }
        catch(ArrayIndexOutOfBoundsException E)
        {
			JOptionPane.showMessageDialog(null,"Des erreurs de fichiers sont apparues en essayant de créer la base de données.");
            System.exit(1);
        }
	}
	
	/**
	 * cree l'objet Database contenant tout les questions
	 * @throws Exception dans le cas où on ne trouve pas de fichier
	 * @throws Exception dans le cas où il y a un problème quelconque avec les fichiers
	 */
	private void createObject() throws IOException, FileNotFoundException, ArrayIndexOutOfBoundsException
	{
		String q = null;
		String a = null;
			
		//Cree le chemin des fichiers questions
		Path currentRelativePath = Paths.get(""); 
		File dir = new File(currentRelativePath.toAbsolutePath().toString()+File.separator+ "Questions");
		System.out.println(currentRelativePath.toAbsolutePath().toString()+File.separator+ "Questions");

		//Cree un array de tout les fichiers trouves	
		File[] listeChemins = dir.listFiles();
		System.out.println(listeChemins.length);
		
			
		for (int i = 0; i < listeChemins.length; i++)
		{
				
			if(listeChemins[i].isFile())
			{	
				BufferedReader read = new BufferedReader(new FileReader(listeChemins[i]));
				StringBuffer buffer = new StringBuffer();
				String line = null;
						
				// put the entire file inside a string
				while((line = read.readLine()) != null)
				{
					buffer.append(line);
					buffer.append("\n");
				}

				//parse the string and put the Question Object created inside the array
				questions.add(parse(buffer.toString()));
			}
			else if (listeChemins[i].isDirectory()) 
			{
				//System.out.println("Directory " + list[i].getName());
			}
		}
	}
	
	//getters&setters
    
	/**
	 *méthode pour retourner le ArrayList contenant les questions et ses réponses
	 * @return retourne le ArrayList (database)
	 */
	public ArrayList<Question> getQuestions() 
	{
		return questions;
	}
	/**
	 *méthode pour réécrire le database
	 * @param questions
	 * 	ArrayList contenant tout le database (textes de tous les fichiers)
	 */
	public void setQuestions(ArrayList<Question> questions) 
	{
		this.questions = questions;
	}

    /**
     * la categorie actuelle
     * @return category
     */
    public ArrayList<String> getCategory() 
    {
        return category;
    }

    /**
     * methode pour modifier la categorie de question
     * @param category the new category
     */
    public void setCategory(ArrayList<String> category) 
    {
        this.category = category;
    }
    //parse the string
	/**
	 * méthode pour séparer les questions des réponses.
     * @param buffer
     * 	String recevant le lecture du fichier
     */
	public Question parse(String buffer)
	{

            String[] splitString = buffer.split("~");
            String[] rep = splitString[3].split("#");
            
            //split all the answer
            ArrayList<String> answer = new ArrayList<String>(Arrays.asList(rep));

            //initialize the difficulty
            short dif = Short.parseShort(splitString[7]);

            if(dif < 0)
                dif = 0;

            if(dif > 100)
                dif = 100;

            //initialize the category(to optimize)
            String catbuf = splitString[5];
            int c = 0;

            for(String s:category)
            {
                if(s.equals(catbuf))
                    break;
                c++;
            }

            if(c == category.size())
            {
                category.add(catbuf);
            }
            
            System.out.println(catbuf);

            //create the Question object
            Question question = new Question(splitString[1], answer, catbuf, dif, splitString[9], splitString[11],false);

            return question;

    }
	
}
