package gestiongala.ihm;

import java.util.Scanner;

public class Ihm {
	
	
	public String recupererEtudantouPersonnel() {
		
		Scanner sc=scanner();
		String reponse="";
		
		System.out.println("Logiciel Gestion Gala");
		
		while(!reponse.equals("personnel") && !reponse.equals("etudiant")) {
			System.out.println("etudiant ou personnel");
			reponse=sc.next();
			
		}
		
		
		
		return reponse;
		
	}
	
	public int recupererNumero() {
		Scanner sc=scanner();
		int reponse;
		String s="";
		
		System.out.println("Entez votre numéro");
		while(sc.hasNextInt()==false) 
		{
			System.out.println("Mettez votre numéro");
			s=sc.next();
		
		}
		reponse=Math.abs(sc.nextInt());
		System.out.println("Votre numéro est le"+reponse);
		
		return reponse;
	}
	
	public int recupererAnnee() {
		Scanner sc=scanner();

		System.out.println("Votre année entre 1 et 5");
		
		int s=pasString();
		

		
			 
		while (s<1 || s>5) {
			System.out.println("Votre année entre 1 et 5");
			s=pasString();
			
		}
			 
		
		return s;
	}
	

	private static int pasString() {
		Scanner sc=new Scanner(System.in);
		
		while(sc.hasNextInt()==false) {
			System.out.println("On souhaite un entier");
			String s=sc.next();
			
		
		}
		
		int s=sc.nextInt();
		return s;
	}
	
	public int recupererTable(String categorie) {
		Scanner sc=scanner();

		int a=0;
		int b=0;
		
		if(categorie.equals("personnel")) {
			a=1;
			b=10;
			
		} else {
			a=11;
			b=25;
		}
		System.out.println("Table entre" +a+"et" +b);
		int s=pasString();
				
			 
		while (s<a || s>b) {
			System.out.println("Table entre" +a+"et" +b);
			s=pasString();
			
		}
			 
		
		return s;
	}
	
	public String recupererEntre(String s) {
		System.out.println(s);
		
		Scanner sc=scanner();
		String reponse=sc.next();
		
		while(reponse=="\n") {
			System.out.println(s);
			reponse=sc.next();
		}
		
		
		return reponse;
	}
	public void afficher(String s) {
		System.out.println(s);
	}
	
	public String proposition(String message) {
		Scanner sc=scanner();
		
	
		String reponse="";
		
		while(!reponse.equals("oui") && !reponse.equals("non")) {
			System.out.println(message);
			reponse=sc.next();
			
			
		}
		
		return reponse;
	}
	public int recupererParticipant(String categorie,int annee) {
		Scanner sc=scanner();

		int a=0;
		int b=0;
		
		if(categorie.equals("etudiant") && annee==5) {
			a=0;
			b=3;
			
		} else {
			a=0;
			b=1;
		}
		System.out.println("Nombre accompagnant entre" +a+"et" +b);
		int s=pasString();
				
			 
		while (s<a || s>b) {
			System.out.println("Nombre accompagnant entre" +a+"et" +b);
			s=pasString();
			
		}
			 
		
		return s;
	}
	
	
	public int sousMenu() {
		System.out.println("1:Gérer places du dîner");
		System.out.println("2:Se désinscrire");
		System.out.println("3:Quitter");
		
		int s=pasString();
				 
		while (s<1 || s>3) {
			
			System.out.println("Choissisez entre 1 et 3");
			s=pasString();
			
		}
		
		return s;
	}
	
	
	
	public Scanner scanner() {
		return new Scanner(System.in);
	}
	
}
