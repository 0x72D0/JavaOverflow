//push comment
package Controlleur;

import Modele.*;
import javafx.application.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.util.*;
import java.io.*;
import java.awt.Desktop;
import java.lang.reflect.Field;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import java.security.*;
import java.util.regex.PatternSyntaxException;

/**
 * Class JavaOverflow
 * classe principale initialisant le GUI ainsi que la base de donnee.
 * @author Lucas 23 fevrier 2016
 */
public class JavaOverflow extends Application{

    //
    // Fields
    //param return desc

    public static Database database;
	public static Question cwq; // cwq : current working question
    public static short userProgress=1;
    public static boolean confirmClose = true;
    public static ArrayList<User> eleves = new ArrayList<User>();
    public static User cs;
    //
    // Constructors
    //

    public JavaOverflow () { };
    //
    // Methods
    //
    
    /**
    * Initialise la base de donne en chargeant les fichiers questions. 
    */
	public static void generateQuestion()
	{
		boolean allDone = true;
		boolean catAllDone = true;
        
        for(Question q : database.getQuestions())
        {
            if(!q.isDone())
            {
                allDone = false;
                break;
            }
            
            if(q.getCategory().getAvgDiff()==userProgress)
            {
				if(!q.isDone())
					catAllDone=false;
			}
			if(!allDone&&!catAllDone)
				break;
        }
        
        Random rand = new Random();
        int i = rand.nextInt(database.getQuestions().size());
        if(allDone)
            JOptionPane.showMessageDialog(null, "Toutes les questions ont étées réussies");
        else
        {
			if(database.getQuestions().get(i).getCategory().getAvgDiff()==userProgress)
				if(database.getQuestions().get(i).getDifficulty()==categoryLevel(database.getQuestions().get(i).getCategory()))
					if(!database.getQuestions().get(i).isDone())
						cwq = database.getQuestions().get(i);
					else
						generateQuestion();
				else
					generateQuestion();
			else
				generateQuestion();
        }
	}
    
    /**
     * met la chaine de caractere envoyer en tant que nouveau password
     * @param password nouveau password
     */
    public static void adminPassword(String password)
    {
        database.setPassword(password);
    }
    
    /**
     * verifie si la question est entrer dans la database et l'affiche dans un JOptionPane
     * @param fileName nom de la question 
     */
    public static void adminTryQuestionsPresence(String fileName)
    {
        try
        {
            String buf = "";
            Question q = findQuestionFile(fileName);
            buf = "question:\n" + q.getEnonce();
            buf += "\nreponse:\n";
            for(String str : q.getReponses())
            {
                buf += str + "\n";
            }
            buf += "\ncategorie:\n" + q.getCategory().getName() + "\n\ndifficulter:\n" + q.getDifficulty() +
                "\n\nbon enoncer:\n" + q.getBonFeedBack() + "\n\nmauvais enoncer:\n" +q.getMauvaisFeedBack();
            
            JOptionPane.showMessageDialog(null, buf);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * allow the admin to check if the website is good 
     * @param category categorie a tester
     */
    public static void adminTryWebsite(String category)
    {
        try
        {
            ArrayList<Category> categories = database.getCategory();
            Category cat = null;
            int i = 0;
        
            for(Category catego : categories)
            {
                if(catego.getName().equals(category))
                {
                    cat = catego;
                    break;
                }
                else
                {
                    i++;
                }
            }
            
            if(i == categories.size())
            {
                JOptionPane.showMessageDialog(null, "la category n'a pas ete trouver");
            }
        
            if(Desktop.isDesktopSupported())
            {
                System.out.println(cat.getRessource());
                Desktop.getDesktop().browse(new URI(cat.getRessource()));
            }
            else
            {
                JOptionPane.showMessageDialog(null, "le desktop n'est pas supporter par Java");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
    * formatte le String de la reponse et de la question pour tester leur egalite
    * @param str1
    * @return le string une fois formatter 
    */
	public static String formatString(String str1)
	{
		str1 = str1.toLowerCase();
		StringBuffer str = new StringBuffer(str1);
        for(int i = 0; i < str.length(); i++)
        {
            char a = str.charAt(i);
            
            switch(a)
            {
                case 224:str.setCharAt(i, 'a');
                break;
                case 226: str.setCharAt(i, 'a');
                break;
                
                case 232: str.setCharAt(i, 'e');
                break;
                case 233: str.setCharAt(i, 'e');
                break;
                case 234: str.setCharAt(i, 'e');
                break;
                case 235: str.setCharAt(i, 'e');
                break;
                
                case 238: str.setCharAt(i, 'i');
                break;
                case 239: str.setCharAt(i, 'i');
                break;
                
                case 251: str.setCharAt(i, 'u');
                break;
            }
        }
        return str.toString();
	}
	
    /**
    * Verifie une reponse entre en parametre en la comparant avec les reponses
    * de la question actuelle.
    * @param rep Une reponse  de l'utilisateur
    * @return Vrai si la reponse est vrai, faux dans le cas contraire.
    */
	public static boolean verifyStringAnswer(String rep)
	{	
		// dans cet algorithme rep est la reponse entree par l'utilisateur
		// et ici answer represente les reponse attendu par le programme
		
		rep = formatString(rep);
		
		for(String ans : cwq.getReponses())
		{
			ans = formatString(ans);
			if(rep.equals(ans))
			{
				cwq.setDone(true);
                cs.add(cwq.getDifficulty());
				return true;
			}
		}
		
		return verifyFormatAnswer(rep, formatString(cwq.getReponses().get(cwq.getReponses().size()-1)));
	}
    
    
	/**
     * check for a matching regex
     * @param rep
     * @param ans
     * @return true si les regex matche sinon false
     */
	public static boolean verifyFormatAnswer(String rep, String ans)
	{
        try
        {
            cwq.setDone(true);
            cs.add(cwq.getDifficulty());
            return rep.matches(ans);
        }
        catch(PatternSyntaxException e)
        {
            JOptionPane.showMessageDialog(null, "il y a un probleme avec le regex, veuillez contacter l'adminstrateur");
            return false;
        }
	}
	
	public static short categoryLevel(Category category)
    {
        
        for(short level =1;level<101;level++)
        {
            for(Question q:database.getQuestions())
            {
                if(q.getDifficulty()==level) {
                    if(q.getCategory().getName().equals(category.getName())) 
                    {
                        if(!q.isDone()) 
                        {
                            return level;
                        }

                    }
                }
            }
        }
        return 100;
    }
    
    /**
     * importe les utilisateur dans le programme
     */
    public static void createEleves()
    {
        
        Path currentRelativePath = Paths.get("");
        File dir = new File(currentRelativePath.toAbsolutePath().toString()+File.separator+ "User");
        File[] listeChemins = dir.listFiles();

        for(int i = 0; i < listeChemins.length; i++)
        {
            try
            {
                if(listeChemins[i].isFile())
                {	
                    FileInputStream fin = new FileInputStream(listeChemins[i]);
                    ObjectInputStream ois = new ObjectInputStream(fin);
                    JavaOverflow.eleves.add((User) ois.readObject());
                }
            }
            catch(FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            catch(ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * sauvegarde des utilisateurs dans le programme
     */
    public static void saveEleves()
    {
        Path currentRelativePath = Paths.get("");
        new File(currentRelativePath.toAbsolutePath().toString() + File.separator + "User").mkdirs();
        File dir = new File(currentRelativePath.toAbsolutePath().toString() + File.separator + "User");
        int x = 0;
        for(User save : eleves)
        {
            try
            {
                File eleve = new File(dir + File.separator + x);
                FileOutputStream fout = new FileOutputStream(eleve);
				ObjectOutputStream oos = new ObjectOutputStream(fout);
                oos.writeObject(save);
				oos.close();
                x++;
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * prends le mot de passe et met l'utilisateur avec se password comme utilisateur principal.
     * @param userMdp 
     */
    public static void setCs(String userMdp)
    {
        for(User save : eleves)
        {
            if(save.getMdp().equals(userMdp))
            {
                cs = save;
                return;
            }
        }
        eleves.add(new User(userMdp));
        cs = eleves.get(eleves.size()-1);
    }

    //
    // Accessor methods
    //
  

    //
    // Other methods
    //
    
    /**
     * trouve la question a partir du nom du fichier
     * @param fileName le nom du fichier rechercher
     * @return la Question
     */
    public static Question findQuestionFile(String fileName)
    {
        for(Question q : database.getQuestions())
        {
            if(q.getName().equals(fileName))
            {
                return q;
            }
        }
        
        return null;
    }
    
    /**
     * retourne en String le contenu du fichier aide.txt
     * @return le contenu du fichier
     */
    public static String aide_utilisateur()
    {
        try
        {
            Path currentRelativePath = Paths.get(""); 
            File categoryFile = new File(currentRelativePath.toAbsolutePath().toString()+File.separator+ "aide.txt");

            BufferedReader read = new BufferedReader(new FileReader(categoryFile));
            StringBuffer buffer = new StringBuffer();
            String line = null;

            // put the entire file inside a string
            while((line = read.readLine()) != null)
            {
                buffer.append(line);
                buffer.append("\n");
            }
            String str = new String(buffer);
            return str;
        }
        catch(FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "le fichier d'aide n'a pas ete trouver");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * retourne en String le contenu de aide_admin.txt
     * @return le contenu de aide_admin.txt
     */
    public static String aide_adminstrateur()
    {
        try
        {
            Path currentRelativePath = Paths.get(""); 
            File categoryFile = new File(currentRelativePath.toAbsolutePath().toString()+File.separator+ "aide_admin.txt");

            BufferedReader read = new BufferedReader(new FileReader(categoryFile));
            StringBuffer buffer = new StringBuffer();
            String line = null;

            // put the entire file inside a string
            while((line = read.readLine()) != null)
            {
                buffer.append(line);
                buffer.append("\n");
            }
            String str = new String(buffer);
            return str;
        }
        catch(FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "le fichier d'aide n'a pas ete trouver");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * le main
     * @param args 
     */
    public static void main(String[]args)
    {	
        try
        {
            System.setProperty("file.encoding","UTF-8");
            Field charset = Charset.class.getDeclaredField("defaultCharset");
            charset.setAccessible(true);
            charset.set(null,null);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "votre ordinateur ne supporte pas utf-8");
        }
		launch(args); //Lance JavaFx
    }
    
    /**
     * le main de JavaFx
     * @param primaryStage
     * @throws Exception 
     */
	public void start(Stage primaryStage) throws Exception {
        
        try
		{
			Path currentRelativePath = Paths.get("");
			File seri = new File(currentRelativePath.toAbsolutePath().toString()+File.separator+ "database.Jobj");
			FileInputStream fin = new FileInputStream(seri);
			ObjectInputStream ois = new ObjectInputStream(fin);
			JavaOverflow.database = (Database) ois.readObject();
			System.out.println(JavaOverflow.database.getCategory().size());
            
            createEleves();

		}
        catch(FileNotFoundException e)
        {
			database = new Database();
		}
        catch(Exception e)
        {
            e.printStackTrace();
        }
		
		Parent root = FXMLLoader.load(getClass().getResource("/Vue/Accueil.fxml"));
		
		Scene scene = new Scene(root, 600,400);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("JavaOverflow - Acceuil");
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(e -> {
			e.consume();
			closeProgram(primaryStage);
		});	
		
	}
	
    /**
     * routine quand on ferme le programme.
     * @param theStage 
     */
	public static void closeProgram(Stage theStage){
        if(confirmClose)
        {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Fermeture");
            alert.setHeaderText("Fermeture du programme");
            alert.setContentText("Etes-vous sur de vouloir fermer?");
            Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
        {       
                try
				{
					Path currentRelativePath = Paths.get("");
					File seri = new File(currentRelativePath.toAbsolutePath().toString()+File.separator+ "database.Jobj");
					FileOutputStream fout = new FileOutputStream(seri);
					ObjectOutputStream oos = new ObjectOutputStream(fout);
					oos.writeObject(JavaOverflow.database);
					oos.close();
                    saveEleves();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			theStage.close();
		}
        }
        else
        {
            try
				{
					Path currentRelativePath = Paths.get("");
					File seri = new File(currentRelativePath.toAbsolutePath().toString()+File.separator+ "database.Jobj");
					FileOutputStream fout = new FileOutputStream(seri);
					ObjectOutputStream oos = new ObjectOutputStream(fout);
					oos.writeObject(JavaOverflow.database);
					oos.close();
                    saveEleves();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			theStage.close();
        }
    }
}
