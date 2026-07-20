package gestiongala.gala.conteneur;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import gestiongala.gala.objet.Personne;
import gestiongala.gala.objet.Reservation;

/**
 * 
 * Classe MapPersonneReservation
 * implemente Serializable
 * Caratérisé par un attribut de type Map mapReservation
 **/


public class MapPersonneReservation implements Serializable{
	
	
	
	private Map<Personne,Reservation> mapReservation;
	
	
	/**
	 * 
	 * Constructeur de MapPersonneReservation
	 * Initialise mapReservation a un HashMap
	 **/
	
	public MapPersonneReservation() {
		mapReservation=new HashMap<Personne,Reservation>();
	}

	/**
	 * 
	 * boolean ajouterCouple
	 * @param Personne e
	 * @param Reservation r
	 * 
	 * ajoute le couple (e,r)
	 * @exception lève excéption IllegalArgumentException si étudiant ou reservation sont null
	 **/
	
	public boolean ajouterCouple(Personne e,Reservation r) {
		if(e!=null && r!=null) {
			mapReservation.put(e, r);
			return true;
		} else {
			throw new IllegalArgumentException("Etudiant ou Reservation null");
		}
	}
	/**
	 * 
	 * boolean supprimerCouple
	 * @param Personne e
	 * 
	 * 
	 * renvoie truc si supprime le couple contenant e
	 **/
	public boolean supprimerCouple(Personne e) {
		
		if(mapReservation.remove(e)!=null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * boolean contientPersonne
	 * @param Personne p
	 * 
	 * 
	 * renvoie truc si contient p
	 **/
	
	public boolean contientPersonne(Personne p) {
		return mapReservation.containsKey(p);
	}
	
	/**
	 * 
	 * Reservation getReservation
	 * @param Personne p
	 * 
	 * 
	 * renvoie la reservation associé à P
	 **/
	public Reservation getReservation(Personne p ) {
	
		return mapReservation.get(p);
	}
	
	/**
	 * 
	 * String toString
	 * 
	 * 
	 * 
	 * renvoie string avec contenu mapReservation
	 **/
	
	public String toString() {
		String s="[";
		
		Set<Map.Entry<Personne,Reservation>> a=mapReservation.entrySet();
		
		Iterator<Map.Entry<Personne, Reservation>> it=a.iterator();
		
		while(it.hasNext()) {
			Map.Entry<Personne, Reservation> v=it.next();
			Personne p=v.getKey();
			Reservation r=v.getValue();
			
			s+="("+p+r+")";
		}
		return s+"]";
		
	}
}
