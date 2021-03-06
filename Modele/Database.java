package Modele;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Cette classe crée un database dans un ArrayList pour poser les questions et prendre les réponses
 * 
 * @author: Duy, 2016/02/16
 */
//Modifications par Gio 2016/03/5
public class Database implements Serializable{

	private ArrayList<Question> questions;
	private ArrayList<Category> category;
    private short essaisAvantAide;
    private boolean posessQuestions;
    private String password;
	
	/**
	 * Constructeur de la Database
	 */
	public Database()
	{
        this.essaisAvantAide = 5;
        this.password = "admin";
        this.posessQuestions = false;
	}
	
	/**
	 * cree l'objet Database contenant tout les questions
	 * @throws Exception dans le cas où on ne trouve pas de fichier
	 * @throws Exception dans le cas où il y a un problème quelconque avec les fichiers
	 */
	private void createObject() throws IOException, FileNotFoundException, ArrayIndexOutOfBoundsException
	{
        questions = new ArrayList<Question>();
        
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
			try
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
                    questions.add(parse(buffer.toString(), listeChemins[i].getName()));
                }
            
                else if(listeChemins[i].isDirectory()) 
                {
                    //System.out.println("Directory " + list[i].getName());
                }
            }
            catch(FileNotFoundException e)
            {
                JOptionPane.showMessageDialog(null, "le dossier Questions est introuvable");
                e.printStackTrace();
                System.exit(3);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "il y a une erreur lors du formatage du fichier suivant: " + listeChemins[i].getName());
                e.printStackTrace();
                System.exit(4);
            }
		}
	}
    
    /**
     * cree les categories dans le programme
     */
    public void createCategories()
    {
        try
        {
            category = new ArrayList<Category>();
            
            Path currentRelativePath = Paths.get(""); 
            File categoryFile = new File(currentRelativePath.toAbsolutePath().toString()+File.separator+ "category.txt");

            BufferedReader read = new BufferedReader(new FileReader(categoryFile));
            StringBuffer buffer = new StringBuffer();
            String line = null;

            // put the entire file inside a string
            while((line = read.readLine()) != null)
            {
                buffer.append(line);
                buffer.append("\n");
            }
            
            String buf = new String(buffer);
            String[] cat = buf.split("\\r?\\n");
            System.out.println(cat.length);
            
            for(int i = 0; i < (cat.length); i++)
            {
                System.out.println(cat[i]);
                String[] components = cat[i].split("\\\\");
                System.out.println(components[0]);
                System.out.println(components[1]);
                System.out.println(components[2]);
                Category catego = new Category(components[0], components[1],Short.parseShort(components[2]));
                category.add(catego);
            }
        }
        catch(FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "le fichier category.txt est introuvable");
            e.printStackTrace();
            System.exit(1);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "il y a une erreur lors du formatage du fichier category.txt");
            e.printStackTrace();
            System.exit(2);
        }
        
    }
    
    /**
     * genere la database
     */
    public void generateDatabase()
    {
        try
		{
            createCategories();
			createObject();
            this.posessQuestions = true;
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("File not found exception when create the database\n\n");
			e.printStackTrace();
            System.exit(120);
		}
		catch(IOException e) 
		{
            System.out.println("IO exception when create the database\n\n");
            e.printStackTrace();
            System.exit(110);
        }
        catch(ArrayIndexOutOfBoundsException E)
        {
			JOptionPane.showMessageDialog(null,"Des erreurs de fichiers sont apparues en essayant de créer la base de données.");
            System.exit(1);
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
    public ArrayList<Category> getCategory() 
    {
        return category;
    }
    /**
     * 
     * @param essaisAvantAide new Value of essaisAvantAide
     */
    public void setEssaisAvantAide(short essaisAvantAide) {
        this.essaisAvantAide = essaisAvantAide;
    }

    /**
     * 
     * @return password 
     */
    public String getPassword() 
    {
        return password;
    }

    /**
     * 
     * @param password le password
     */
    public void setPassword(String password) 
    {
        this.password = password;
    }

    /**
     * methode pour modifier la categorie de question
     * @param category the new category
     */
    public void setCategory(ArrayList<Category> category) 
    {
        this.category = category;
    }

    /**
     * 
     * @return 
     */
    public boolean isPosessQuestions() {
        return posessQuestions;
    }

    /**
     * 
     * @param posessQuestions 
     */
    public void setPosessQuestions(boolean posessQuestions) {
        this.posessQuestions = posessQuestions;
    }
    
    /**
     * 
     * @return  actual value of essai avant aide
     */
    public short getEssaisAvantAide(){return this.essaisAvantAide;}
    
    
    //parse the string
	/**
	 * méthode pour séparer les questions des réponses.
     * @param buffer
     * 	String recevant le lecture du fichier
     */
	public Question parse(String buffer, String fileName) throws Exception
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
            Category found = new Category();
            int c = 0;

            for(Category s : category)
            {
                if(s.getName().equals(catbuf))
                {
                    found = s;
                    break;
                }
                c++;
            }

            if(c >= category.size())
            {
               JOptionPane.showMessageDialog(null, "la categorie que vous avez mis dans le fichier n'existe pas dans le fichier Category.txt");
               throw new Exception();
            }
            
            System.out.println(catbuf);

            //create the Question object
            Question question = new Question(fileName, splitString[1], answer, found, dif, splitString[9], splitString[11],false);

            return question;

    }
	
}
