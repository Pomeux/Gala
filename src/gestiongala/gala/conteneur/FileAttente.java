package gestiongala.gala.conteneur;

import java.io.Serializable;
import java.util.Iterator;
import java.util.PriorityQueue;

import gestiongala.gala.objet.Couple;
import gestiongala.gala.objet.Etudiant;
import gestiongala.gala.objet.Personne;
import gestiongala.gala.objet.Reservation;

/**
 * 
 * FileAttente implemente Serializable
 * Caractériser par un attribut PriorityQueue fileAttente
 * 
 **/

public class FileAttente implements Serializable{
	
	private PriorityQueue<Couple> fileAttente;
	
	/**
	 * 
	 * Constructeur FileAttente
	 * 
	 **/
	
	public FileAttente() 
	{
		
		fileAttente=new PriorityQueue<Couple>();
	
	}
	
	
	/**
	 * 
	 * boolean ajouterFileAttente
	 * @param Couple p
	 * Ajoute un couple à la file attente
	 * @exception IllegalArgumentException si la personne est déjà dans la file attente ou null
	 * 
	 **/
	public boolean ajouterFileAttente(Couple p) {
		if(fileAttente.contains(p)==false && p!=null) {
			fileAttente.add(p);
			return true;
		} 
		else {
			throw new IllegalArgumentException("Personne déjà dans la file attente ou null");
		}
	}
	
	/**
	 * 
	 * int longueurFile
	 * retournela longueur de la fileAttente
	 * 
	 **/
	
	public int longueurFile() {
		return fileAttente.size();
	}
	
	/**
	 * 
	 * Couple verifFilePersonne
	 * @param Personne p
	 * verifie si la perosnne p est dans la file attente
	 * 
	 **/
	

	
	public Couple verifFilePersonne(Personne p) {
		
		Iterator<Couple> it=iterator();
		
		while(it.hasNext()) {
			Couple c=it.next();
			
			if(c.getT().equals(p)) {
				return c;
			}
		}
		
		return null;
	}
	/**
	 * 
	 * boolean supprimerPersonne
	 * @param Personne p
	 * supprime le couple qui contient p et retourne vrai dans ce cas
	 * 
	 **/
	
	
	public boolean supprimerPersonne(Personne p) {
		Couple c=verifFilePersonne(p);
		
		return supprimerCouple(c);
	}
	
	/**
	 * 
	 * boolean supprimerCouple
	 * @param Couple c
	 * supprime le couple et retourne vrai dans ce cas
	 * 
	 **/
	
	public boolean supprimerCouple(Couple c) {
		return fileAttente.remove(c);
	}
	
	/**
	 * 
	 * Couple supprimerTete
	 * file la file de sa tête et la retourne
	 * 
	 **/
	
	
	public Couple supprimerTete() {
		return fileAttente.poll();
	}
	
	/**
	 * 
	 * Iterator iterator
	 * retourne iterator de fileAttente
	 * 
	 **/
	
	public Iterator<Couple> iterator() 
	{
		return fileAttente.iterator();
	}

	/**
	 * 
	 * String toString
	 * retourne un string de comment la file sera extraite
	 * 
	 **/
	
	
	public String toString() { 
        String s="[Element de file attente";
		
		PriorityQueue<Couple> copy=new PriorityQueue<Couple>();
		
		Iterator<Couple> it=iterator();
		

		while(it.hasNext()==true) {
			Couple e=it.next();
			
			copy.add(e);
		}
		
		Couple e=copy.peek();
		
		while(e!=null) {
			e=copy.poll();
			
			if(e!=null) {
				Etudiant etudiant=e.getT();
				s+=","+etudiant;
			}
		}
		return s+"]";
		
	}
	
}
