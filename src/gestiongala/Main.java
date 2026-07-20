package gestiongala;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import gestiongala.controleur.Controleur;
import gestiongala.exception.FileModifyException;

public class Main {
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		
		try {
		
			Scanner sc=new Scanner(new File("Ressources/Lancement/marche.txt"));
		
			String ligne="";
		
			Controleur c;
			
			if(sc.hasNextLine()) {
				ligne=sc.nextLine();
			} // doit lever une exception si le fichier n'a pas de ligne
		
			
			
			if(ligne.equals("0")) 
			{
				c=new Controleur(LocalDate.of(2022, 3, 10));
			} 
			else if(ligne.equals("1")) 
			{
				c=new Controleur();
			} 
			else
			{
				throw new FileModifyException("Le fichier de lancement a été modifié par un tier "); 
			}
		}
		catch(Exception e) {
			
			System.out.println(e.getCause()+e.getMessage());
		}
		
	}
}
