package gestiongala.gala;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import gestiongala.gala.conteneur.EnsembleTotal;
import gestiongala.gala.conteneur.FileAttente;
import gestiongala.gala.conteneur.ListeTable;
import gestiongala.gala.conteneur.MapPersonneReservation;
import gestiongala.gala.objet.Couple;
import gestiongala.gala.objet.Etudiant;
import gestiongala.gala.objet.Personne;
import gestiongala.gala.objet.Personnel;
import gestiongala.gala.objet.Reservation;
import gestiongala.gala.objet.Table;

public class Gala implements Serializable{

	
	public static final long serialVersionUID = 7462292957033130755L;

	private LocalDate date;
	
	
	private FileAttente fileAttente;
	private EnsembleTotal<Etudiant> ensembleEtudiant;
	private EnsembleTotal<Personnel> ensemblePersonnel;
	
	private ListeTable listeTablePersonnel;
	private ListeTable listeTableEtudiant;
	
	private MapPersonneReservation personneReservation;
	
	private MapPersonneReservation confirmationReservation;
	
	
	public static final int tarif1 = 10;
	public static final int tarif2 = 15;
	public static final int tarif3 = 20;
	
	public static final int nbTableEtudiant=15;
	public static final int nbTablePersonnel=10;
	
	
	public Gala(LocalDate date) throws FileNotFoundException 
	{
		this.date=date;
		
		//
		fileAttente=new FileAttente();
	
		
		ensembleEtudiant=new EnsembleTotal<Etudiant>();
		ensemblePersonnel=new EnsembleTotal<Personnel>();
		initialisationEnsembleTotal(0);
		initialisationEnsembleTotal(1);
		
		listeTableEtudiant=new ListeTable();
		listeTablePersonnel=new ListeTable();
		
		personneReservation=new MapPersonneReservation();
		
		confirmationReservation=new MapPersonneReservation();
		
		for(int i=0;i<nbTablePersonnel;i++) {
			Table t=new Table();
			
			for(int j=0;j<8;j++) {
				t.ajouterVide();
				
			}
			
			listeTablePersonnel.ajouterTable(t);
			
		}

		
		for(int i=0;i<nbTableEtudiant;i++) {
			Table t=new Table();
			
			for(int j=0;j<8;j++) {
				t.ajouterVide();
			}
			listeTableEtudiant.ajouterTable(t);
		}
		
		
			
	}
	
	public void initialisationEnsembleTotal(int categorie) throws FileNotFoundException {
		File file=null;

		if(categorie==0) {
			file=new File("./Ressources/Listes/etudiants.txt");
			
		} else if(categorie==1) {
			file=new File("./Ressources/Listes/personnel.txt");
		}
			
		Scanner scanner=new Scanner(file);
		
		int annee=0;
		
		while(scanner.hasNextLine()==true) {
			String line=scanner.nextLine();
			
			Scanner scLigne=new Scanner(line);
			
			int numero=scLigne.nextInt();
			
			String nom=scLigne.next();
			
			String prenom=scLigne.next();
			
			String tel=scLigne.next();
			
			String mail=scLigne.next();
			
			if(categorie==0) {
				annee=scLigne.nextInt();
			}
			
			if(categorie==0) {
				ensembleEtudiant.ajouterPersonne(new Etudiant(numero,nom,prenom,tel,mail,annee));
			} else {
				ensemblePersonnel.ajouterPersonne(new Personnel(numero,nom,prenom,tel,mail));
			}
			
		}
		
		
	}

	
	public boolean ajouterPersonne(int numero,String nom,String prenom,String tel,String num,int annee,String cat) {
		if(cat.equals("etudiant")) {
			return ensembleEtudiant.ajouterPersonne(new Etudiant(numero,nom,prenom,tel,num,annee));
			
		} else {
			return ensemblePersonnel.ajouterPersonne(new Personnel(numero,nom,prenom,tel,num));
		}
	}
	
	public Personne retrouverPersonne(int numero,String cat) {
		if(cat.equals("etudiant")) {
			return ensembleEtudiant.retrouverPersonne(numero);
		} else {
			return ensemblePersonnel.retrouverPersonne(numero);
		}
	}
	
	public boolean supprimerPersonne(int numero,String cat) {
		if(cat.equals("etudiant")) {
			return ensembleEtudiant.supprimerPersonne(numero);
		} else {
			return ensemblePersonnel.supprimerPersonne(numero);
		}
	}
	
	public boolean ajouterFileAttente(Etudiant e,Reservation r) 
	{
		Couple c=new Couple(e,r);
		
		return fileAttente.ajouterFileAttente(c);
	}
	
	public Couple estDansLaFileAttente(int num) {
		return fileAttente.verifFilePersonne(retrouverPersonne(num, "etudiant"));
		
	}
	public Couple supprimerFileAttente() {
		return fileAttente.supprimerTete();
	}
	public void supprimerPersonneFileAttente(Personne p) {
		fileAttente.supprimerPersonne(p);
	}
	
	
	public boolean ajouterPersonneReservation(Couple c) {
		return personneReservation.ajouterCouple(c.getT(), c.getV());
	}
	public boolean ajouterPersonneReservation(Personne p,Reservation r) {
		return personneReservation.ajouterCouple(p, r);
	}
	public Reservation estDansTotal(int numero,String categorie) {
		
		return personneReservation.getReservation(retrouverPersonne(numero, categorie));
	}
	
	
	public void supprimerReservation(Personne p) {
		personneReservation.supprimerCouple(p);
	}
	
	public int getNpPlace(int numero) {
		
		
		
		return confirmationReservation.getReservation(retrouverPersonne(numero, "etudiant")).getnbPlace();
		
	}
	
	
	public void supprimerConfirmationReservation(Personne p) {
		confirmationReservation.supprimerCouple(p);
	}
	
	
	public boolean estDansConfirmation(int numero) {
		return confirmationReservation.contientPersonne(retrouverPersonne(numero, "etudiant"));
	}

	
	public int ajouterReservationTable(String categorie,Personne p,int idTable,int place) {
		
			
			Table t=null;
			
			if(categorie.equals("personnel")) {
				if(idTable!=-1 ) {
				
					t=listeTablePersonnel.getTable(idTable);
				} 	else {
					t=listeTablePersonnel.getTablePlace(place);
				}
			} else {
				if(idTable!=-1 ) {
					
					t=listeTableEtudiant.getTable(idTable);
				} 	else {
					t=listeTableEtudiant.getTablePlace(place);
				}
			}
			
			
			
			
			if(t.getnbPlaceLibre()>=place) {
				
				t.ajouterPersonne(p);
				t.ajouterAccompagnant(p, place-1);
				return t.getNumeroTable();
				
			} 
		
			
		
		return 0;
	}
	
	public void supprimerPersonneTable(int numero,String categorie) 
	{
		Personne p=retrouverPersonne(numero, categorie);
		
		if(categorie.equals("etudiant")) {
			listeTableEtudiant.supprimerPersonne(p);
		} else {
			listeTablePersonnel.supprimerPersonne(p);
		}
	}
	
	
	public String recupererTable(String categorie) {
		if(categorie.equals("etudiant")) {
			return ""+listeTableEtudiant;
		} else {
			return ""+listeTablePersonnel;
		}
	}
	
	
	
	public double calculMontant(int annee,String categorie,int nbPlace) {


		if(categorie.equals("etudiant")) {
			if(annee==5) {
				return tarif1+nbPlace*tarif3;
			} else {
				return tarif2+nbPlace*tarif2;
			}
		} else {
			return tarif3+nbPlace*tarif3;
		}
		
		
		
	}
	public int nbRestantTable(String categorie) {
		if(categorie.equals("etudiant")) {
			return listeTableEtudiant.nbTotal();
		} else {
			return listeTablePersonnel.nbTotal();
		}
	
	}
	
	public Etudiant retournerEtudiant(Personne p) {
		Etudiant e=(Etudiant) p;
		
		return e;
	}
	
	public Reservation instancierReservation(LocalDate date,int table,int nbPlace,int annee,String categorie) {
		double montant=calculMontant(annee, categorie, nbPlace);
		
		return new Reservation(date, table, nbPlace,montant);
	}
	
	public void confirmation() {

	
		if(ChronoUnit.DAYS.between(LocalDate.now(),date)<=30) 
		{
			
			Set<Couple> t=new HashSet<Couple>();
			
			int n=0;
			
			
			Couple c=null;
			
			while(fileAttente.longueurFile()!=0 && n<=listeTableEtudiant.nbTotal()) {
				
			
				
				c=fileAttente.supprimerTete();
				Reservation r=c.getV();
				
				
				n+=r.getnbPlace()+1;
				
				
				t.add(c);
				
				
			}
			if(n>listeTableEtudiant.nbTotal()) {
				t.remove(c);
			}
			
			
			Iterator<Couple> it=t.iterator();
			
			while(it.hasNext()) {
				c=it.next();
				Personne p=c.getT();
				Reservation r=c.getV();
				
				confirmationReservation.ajouterCouple(p, r);
			}
			
		}
	}
	
	public boolean tropTard() {
		
		if(ChronoUnit.DAYS.between(LocalDate.now(),date)<10) {
			return true;
		}
		return false;
	}

	
	
	public String toString() {
		
		String s="";
		s+="Ensemble Total Etudiant"+"\n";
		s+=ensembleEtudiant+"\n";
		s+="Ensemble Total Personnel"+"\n";
		s+=ensemblePersonnel+"\n";
		s+=fileAttente+"\n";
	
		
		
		s+="Ensemble Confirmation Reservation";
		s+=confirmationReservation+"\n";
		
		s+="Ensemble Final Reservation";
		s+=personneReservation+"\n";
		s+="Table Personnel"+"\n";
		s+=listeTablePersonnel+"\n";
		s+="Table Etudiant"+"\n";
		s+=listeTableEtudiant+"\n";
		


		
		
		
		return s;
	}



	
	
	
}
