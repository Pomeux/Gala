package gestiongala.controleur;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import gestiongala.controleur.stockage.ServiceStockage;
import gestiongala.gala.Gala;
import gestiongala.ihm.Ihm;

public class Controleur
{
	
	
	private ServiceStockage si;
	private Gala a;
	
	private Ihm ihm;
	
	
	
	public Controleur(LocalDate date) throws IOException {
		
		
		a=new Gala(date);
		
		ihm=new Ihm();
		
		si=new ServiceStockage();
		
		si.enregistrer(a);
		
		
		modifierFichier();
		
		deroullement();
		
	}
	
	public Controleur() throws ClassNotFoundException, IOException 
	{
		
		ihm=new Ihm();
		
		si=new ServiceStockage();
		
		a=(Gala) si.charger();
		
		if(a==null) 
		{
			throw new IOException("La classe Gala n'a pu être charger");
		}
		
		
		deroullement();
		
	
	}
	public void deroullement() throws IOException {
		
		a.confirmation();

		
		ihm.afficher(a.toString());
		
		String etudiantOuPersonnel=ihm.recupererEtudantouPersonnel();
		
		int numero=ihm.recupererNumero();
		
		
		
		boolean verif=false;
		

		if(a.retrouverPersonne(numero, etudiantOuPersonnel)!=null) {
			verif=true;
			ihm.afficher(a.retrouverPersonne(numero, etudiantOuPersonnel).toString());
		}
				
		if(verif==true) 
		{
			
			menu(etudiantOuPersonnel,numero);	
		} 
		
		else 
		{
			String inscription=ihm.proposition("Vous n'êtes pas inscrit souhaitez-vous vous s'inscre? oui ou non");
			
			
			if(inscription.equals("oui")) {
				
				int annee=0;
				
				String nom=ihm.recupererEntre("Mettez votre nom");
				String prenom=ihm.recupererEntre("Mettez votre prenom");
				String tel=ihm.recupererEntre("Mettez votre numéro de télephone");
				String email=ihm.recupererEntre("Mettez votre email");
				
				if(etudiantOuPersonnel.equals("etudiant")) {
					annee=ihm.recupererAnnee();
				}
				a.ajouterPersonne(numero, nom, prenom, tel, email, annee, etudiantOuPersonnel);
				ihm.afficher(a.retrouverPersonne(numero, etudiantOuPersonnel).toString()+"a bien été ajouter");
				menu(etudiantOuPersonnel,numero);
			} 
			else 
			{
				quitter();
			}
			
		}
		
		
	}
	
	public void menu(String categorie,int numero) throws IOException {
		int menu=ihm.sousMenu();
		
		switch(menu) {
			case 1:
				consultationReservation(categorie,numero);
				break;
			case 2:
				ihm.afficher(a.retrouverPersonne(numero, categorie).toString()+"a bien été supprimer"); //vider la liste attente confirmation et ensemble correspondant
				a.supprimerPersonneFileAttente(a.retrouverPersonne(numero, categorie));
				a.supprimerReservation(a.retrouverPersonne(numero, categorie));
				a.supprimerConfirmationReservation(a.retrouverPersonne(numero, categorie));
				a.supprimerPersonneTable(numero,categorie); //expliquer pourquoi la on utilise pas retrouverPersonne
				
				a.supprimerPersonne(numero, categorie);
				
				
			case 3:
				quitter();
				break;
				
			
		}
		
	}
	
	
	
	public void consultationReservation(String categorie,int numero) throws IOException {
	
		if(a.estDansLaFileAttente(numero)!=null) {
			ihm.afficher("Vous êtes toujours dans la file attente");
			quitter();
		}
		else if(a.estDansConfirmation(numero)==true) {
				reservation(categorie, numero, 1);
		}
				
		else if(a.estDansTotal(numero, categorie)!=null) 
		{
			
			if(a.tropTard()==true) {
				
				ihm.afficher(a.estDansTotal(numero, categorie)+" "+a.retrouverPersonne(numero, categorie));
			} 
			else {
				String t =ihm.recupererEntre("voulez-vous annulez?");
				if(t.equals("non")) {
					ihm.afficher(a.estDansTotal(numero, categorie)+" "+a.retrouverPersonne(numero, categorie));
				} else {
					a.supprimerReservation(a.retrouverPersonne(numero, categorie));
					a.supprimerPersonneTable(numero,categorie);
				}
			}
		}	
		else {
		
			reservation(categorie,numero,0);
		}
		
		quitter();
	}
	
	public void reservation(String categorie,int numero,int confi) throws IOException {
		
		String reponse="";

		int nbPlace=0;
		int table=0;
		
		int depassePas=0;
		
		if(categorie.equals("personnel") || (categorie.equals("etudiant") && confi==1)) {
			ihm.afficher("Il reste " +a.nbRestantTable(categorie)+"places libres");
		
			reponse=ihm.proposition("Voulez-vous consulter le plan des tables? oui ou non");
		}
		

		
		if(reponse.equals("oui")) {
			String recupererContenuTable=a.recupererTable(categorie);
			ihm.afficher(recupererContenuTable);
			
			if(categorie.equals("personnel")) {
				
				nbPlace=ihm.recupererParticipant(categorie,0);
			}
			
			table=ihm.recupererTable(categorie);
			
			if(categorie.equals("personnel")) {
			
				depassePas=a.ajouterReservationTable(categorie, a.retrouverPersonne(numero, categorie), table, nbPlace+1);
			} else if(confi==1){
				
				
				
				nbPlace=a.getNpPlace(numero);
				depassePas=a.ajouterReservationTable(categorie, a.retrouverPersonne(numero, categorie), table-10, nbPlace+1);
			}
			
		} 
		else
		{
			if(categorie.equals("personnel")) {
				nbPlace=ihm.recupererParticipant(categorie,0);
				
				depassePas=a.ajouterReservationTable(categorie, a.retrouverPersonne(numero, categorie),-1, nbPlace+1);
			}
			
			
			if(categorie.equals("etudiant") && confi==1) {
				nbPlace=a.getNpPlace(numero);
				depassePas=a.ajouterReservationTable(categorie, a.retrouverPersonne(numero, categorie),-1, nbPlace+1);
			}
			
		}

		
		if(categorie.equals("etudiant") && confi==0) {
			
			int annee=a.retournerEtudiant(a.retrouverPersonne(numero, categorie)).getAnnee();
			nbPlace=ihm.recupererParticipant(categorie,annee);
			
			a.ajouterFileAttente(a.retournerEtudiant(a.retrouverPersonne(numero, categorie)),a.instancierReservation(LocalDate.now(),-1,nbPlace,annee,categorie));
			quitter();	
			
		} 
		else if(categorie.equals("etudiant") && confi==1) {
			
		}
		
		
		if(depassePas==0) 
		{
			ihm.afficher("Trop accompagnant recommencez");
		} else {
			if(categorie.equals("personnel")) {
				a.ajouterPersonneReservation(a.retrouverPersonne(numero, categorie), a.instancierReservation(LocalDate.now(),depassePas, nbPlace, -1, categorie));
			} else 
			{  //commentez
				a.supprimerConfirmationReservation(a.retrouverPersonne(numero, categorie));
				a.ajouterPersonneReservation(a.retrouverPersonne(numero, categorie), a.instancierReservation(LocalDate.now(),depassePas, nbPlace, a.retournerEtudiant(a.retrouverPersonne(numero, categorie)).getAnnee(), categorie));
			}
		}
		quitter();
		
	}
	
	public void quitter() throws IOException {
		
		
		si=new ServiceStockage();
		
		si.enregistrer(a);
		
		ihm.afficher("Fin de l'application");
		
		System.exit(0);
	}
	
	
	public void modifierFichier() throws IOException
	{
		
		File file=new File("./Ressources/Lancement/marche.txt");
		
			
		FileWriter filewriter = new FileWriter(file);
		filewriter.write("1");
			
		filewriter.flush();
		filewriter.close();
			
		
		
		
	}
}
