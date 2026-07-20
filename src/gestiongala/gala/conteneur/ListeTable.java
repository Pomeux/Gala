package gestiongala.gala.conteneur;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import gestiongala.gala.objet.Personne;
import gestiongala.gala.objet.Table;

/**
 * 
 * Classe ListTable
 * implemente Serializable
 * Caratérisé par un attribut de type List listeTotal
 **/


public class ListeTable implements Serializable{
	
	private List<Table> listeTotal;
	
	/**
	 * 
	 * Constructeur de ListTable
	 * Initialise listeTotal a un LinkedList
	 **/
	public ListeTable() {
		listeTotal=new LinkedList<Table>();
	}
	
	/**
	 * 
	 * Table getTable
	 * @param int num
	 * retourne la table de l'index num
	 * @exception IllegalArgumentException si le num n'est pas compris entre 1 et 25
	 **/
	
	public Table getTable(int num) {
		
		if(1<=num && num<=25) {
			
			num-=1;
			
		return listeTotal.get(num);
		}
		else {
			throw new IllegalArgumentException("num n'est pas compris entre 1 et 25");
		}
	}
	
	/**
	 * 
	 * boolean ajouterTable
	 * @param Table t
	 * retourne vrai si cela ajoute la table 
	 * 
	 **/
	
	public boolean ajouterTable(Table t) {
		return listeTotal.add(t); 
	}
	
	/**
	 * 
	 * Table getTablePlace
	 * @param int place
	 * retourne une table où il y a au minimum place places
	 * 
	 **/
	
	public Table getTablePlace(int place) {
		ListIterator<Table> it=listeTotal.listIterator();
		
		while(it.hasNext()==true) {
			Table t=it.next();
			
			if(place+1<=t.getnbPlaceLibre()) {
				return t;
			}
		}
		return null;
		
	}
	
	/**
	 * 
	 * supprimerPersonne
	 * @param Personne p
	 * supprime la personne correspondante et ses accompagnants
	 * 
	 **/
	
	public void supprimerPersonne(Personne p) {
		ListIterator<Table> it=listeTotal.listIterator();
		
		while(it.hasNext()==true) {
			Table t=it.next();
			
			if(t.contientPersonne(p)) {
				t.supprimer(p);
				t.supprimerAccompagnant(p.getNumero());
			}
		}
		
		
	}

	
	/**
	 * 
	 * int nbTotal 
	 * retourne le total des places libre pour chaque table
	 * 
	 **/
	
	public int nbTotal() {
		ListIterator<Table> it=listeTotal.listIterator();
		
		int s=0;
		
		while(it.hasNext()==true) {
			Table t=it.next();
			
			s+=t.getnbPlaceLibre();
		}
		return s;
	}
	
	/**
	 * 
	 * String toString 
	 * retourne un string du contenu de chaque table
	 * 
	 **/
	
	public String toString() {
		String s="";
		ListIterator<Table> it=listeTotal.listIterator();
		
		while(it.hasNext()==true) {
			Table t=it.next();
			
			s+=t;
		}
		return s;
	}
	
	
	
	
}
