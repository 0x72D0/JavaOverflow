/**
 * @author: Duy, 2016/02/16
 */
//Modifications par Gio 2016/03/5
import java.io.*;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Database {

	static ArrayList<Question> questions = new ArrayList<Question>();
	int numeroQ;
	
	//Create database
	
	public static  void createData() throws IOException{
			String q = null;
			String a = null;
			
			//Cree le chemin des fichiers questions
			Path currentRelativePath = Paths.get(""); 
			File dir = new File(currentRelativePath.toAbsolutePath().toString()+File.separator+"Questions");
			System.out.println(currentRelativePath.toAbsolutePath().toString()+File.separator+"Questions");

			//Cree un array de tout les fichiers trouves	
			File[] listeChemins = dir.listFiles();
			System.out.println(listeChemins.length);
		
			
			for (int i = 0; i < listeChemins.length; i++){
				
				if(listeChemins[i].isFile()){
					
					try {
						
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

						
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if (listeChemins[i].isDirectory()) {
			        //System.out.println("Directory " + list[i].getName());
				}
			}
			

	}
	
	/*public static void main(String args[]) throws IOException
	{
		createData();
		
	}*/
	
	//prendre question et poser
	public static String prendreQ (int q){
		return questions.get(q).getEnonce();
	}
	//prendre reponse et montrer
	public static Object prendreA (int q){
		return questions.get(q).getAnswerX(q);
	}
	
	//montrer feedback
	
	//prendre la reponses entree
	public static Object comparerA(String a, int q){
		if(a.equals(questions.get(q).getAnswerX(q))){
			return "Good job. KFC for you";
		}else{
			return "Sorry, no KFC for you";
		}
	}
	//Duy fixe cette methode stp
	/*public static String getSolutionnaire() {
		String solutionnaire ="";
		for(int i = 0 ; i<questions.size();i++){
			solutionnaire += "Question "+(i+1)+" :"+questions.get(i).getQuestion()+" "+"Reponse "+(i+1)+" :"+questions.get(i).getAnswer()+"\n";
		}
		return solutionnaire;
	}*/
	
	
	//getters&setters
	public static ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	//parse the string
	public static Question parse(String buffer)
	{
		String[] splitString = buffer.split("~");

		//split all the answer
		ArrayList<String> answer = new ArrayList<String>(Arrays.asList(splitString[3].split("$")));
		
		//create the Question object
		Question question = new Question(splitString[1], answer);
		return question;
	}
	
}
