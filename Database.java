/**
 * @author: Duy, 2016/02/16
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Database {

	static ArrayList<Question> questions = new ArrayList<Question>();
	int numeroQ;
	
	//Create database
	public static  void createData(){
			String q = null;
			String a = null;
			ArrayList<File> arr = new ArrayList<File>();
			File dir = new File("C:\\Users\\Binh Nguyen\\Documents\\workspace\\blabla\\src\\data\\");
			File[] list = dir.listFiles();
			
			for (int i = 0; i < list.length; i++){
				if(list[i].isFile()){
					arr.add(list[i]);
					try {
						Scanner s = new Scanner(arr.get(i));
						q=s.nextLine();
						a=s.nextLine();
						//System.out.println("question: "+q);
						//System.out.println("anwser: "+a);
						Question data = new Question(q,a);
						questions.add(data);
						//System.out.println("Element: "+i+" "+arr.get(i));
						//System.out.println("Element: "+i+" "+questions.get(i).getQuestion());
						//System.out.println("Element: "+i+" "+questions.get(i).getAnswer());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if (list[i].isDirectory()) {
			        //System.out.println("Directory " + list[i].getName());
				}
			}
			

	}
	
	//prendre question et poser
	public static String prendreQ (int q){
		return questions.get(q).getQuestion();
	}
	//prendre reponse et montrer
	public static Object prendreA (int q){
		return questions.get(q).getAnswer();
	}
	
	//montrer feedback
	
	//prendre la reponses entree
	public static Object comparerA(String a, int q){
		if(a == questions.get(q).getAnswer()){
			return "Good job. KFC for you";
		}else{
		return "Sorry, no KFC for you";
		}
	}
	public static String getSolutionnaire() {
		String solutionnaire ="";
		for(int i = 0 ; i<questions.size();i++){
			solutionnaire += "Question "+(i+1)+" :"+questions.get(i).getQuestion()+" "+"Reponse "+(i+1)+" :"+questions.get(i).getAnswer()+"\n";
		}
		return solutionnaire;
	}
	
	
	//getters&setters
	public static ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	
	
}
