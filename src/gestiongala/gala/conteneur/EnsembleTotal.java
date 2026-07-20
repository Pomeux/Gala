package gestiongala.gala.conteneur;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import gestiongala.gala.objet.Personne;

/**
 * Classe EnsembleTotal implementes Serializable
 * 
 * Caractériser par un attribut ensembleTotal de type Set
 **/

public class EnsembleTotal<T> implements Serializable {
	
	/**
	 * attribut ensembleTotal qui est le tout de la classe
	 * 
	 * 
	 **/
	
	private Set<T> ensembleTotal;
	
	/**
	 * Constructeur de EnsembleTotal initialise avec un HashSet
	 * 
	 * 
	 **/
	public EnsembleTotal() {
		ensembleTotal=new HashSet<T>();
	}
	
	/**
	 * T retrouverPersonne
	 * @param int numero
	 * Permet de retrouver une personne 
	 * 
	 * 
	 **/
	
	public T retrouverPersonne(int numero) {
		
	
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			T p=it.next();
			Personne pe=(Personne) p;
			
			if(pe.getNumero()==numero) {
				return p;
			}
			
		}
		
		return null;
	}
	
	
	
	public boolean contientPersonne(Personne p) {
		return ensembleTotal.contains(p);
	}
	
	/**
	 * boolean ajouterPerosnne
	 * @param T p
	 * permet ajouter une personne 
	 * @exception IllegalArgumentException est levé quand la personne n'est pas là 
	 * 
	 **/
	
	public boolean ajouterPersonne(T p) {
		if(p!=null) {
			
			return ensembleTotal.add(p);
		} 
		
		else {
			throw new IllegalArgumentException("La personne est null");
		}
	}
	
	/**
	 * boolean supprimerPersonne
	 * @param int num
	 * permet de supprimer une personne  
	 * @exception IllegalArgumentException est levé quand la personne n'est pas présente 
	 **/
	
	public boolean supprimerPersonne(int num) {
		T e=retrouverPersonne(num);
		if(e!=null) {
			ensembleTotal.remove(e);
			return true;
		}
		else {
			throw new IllegalArgumentException("La personne n'est pas présente");
		}
	}
	
	/**
	 * Iterator<T> iterator
	 * retourne l'iterator de ensembleTotal
	 **/
	
	public Iterator<T> iterator() {
		return ensembleTotal.iterator();
	}
	
	/**
	 * String toString
	 * retourne un string avec le contenu de ensemble
	 **/
	
	public String toString() {
		String s="[";
		
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			T p=it.next();
			
			s+=p+"|";
			
		}
		return s+"]";
	}
}
