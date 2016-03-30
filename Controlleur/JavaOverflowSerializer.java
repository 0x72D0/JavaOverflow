package Controlleur;

import Modele.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JavaOverflowSerializer
{
	public static Database database;
	
	public static void main(String args[])
	{
		try
		{
			database = new Database();
			Path currentRelativePath = Paths.get("");
			File seri = new File(currentRelativePath.toAbsolutePath().toString()+File.separator+ "database.Jobj");
			FileOutputStream fout = new FileOutputStream(seri);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(database);
			oos.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
