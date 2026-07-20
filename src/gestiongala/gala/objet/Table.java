package gestiongala.gala.objet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Classe Table
 * Implements Serializable
 * Une table est caractériser par un attribut numeroTable de type int qui est unique pour chaque table et un attribut contenu qui contient 8 Personne de type List de longueur max 8
 * Et un attribut nbPlaceLibre de type int 
 * 
 **/

public class Table implements Serializable{
	
	/**
	 * attribut static num pour définir un unique numeroTable
	 **/
	
	private int numeroTable;
	private static int num=1;
	
	private int nbPlaceLibre;
	
	/**
	 * linked liste qui contient les personnes inscrites mais également un personne qui sers à remplir jusqu'à 8 pour les places libres
	 **/
	
	private List<Personne> contenu=new LinkedList<Personne>();
	
	/**
	 * Constructeur Table 
	 * @exception IllegalStateException si dépassement de la limite de table
	 **/
	
	public Table() {
		if(numeroTable<=25) {
			numeroTable=num++;
		
			nbPlaceLibre=8;
				
			
		} else {
			throw new IllegalStateException("Dépassement du nombre de table");
		}
	
	}
	
	/**
	 * int getNumeroTable
	 * getteur numeroTable
	 * 
	 **/
	
	public int getNumeroTable() {
		return numeroTable;
	}
	/**
	 * int getnbPlaceLibre
	 * getteur nbPlaceLibre
	 * 
	 **/
	
	public int getnbPlaceLibre() {
		return nbPlaceLibre;
	}

	/**
	 * boolean contientPersonne
	 * @param Personne p
	 * renvoie true si p est dans contenu
	 * 
	 **/
	
	
	public boolean contientPersonne(Personne p) {
		
		return contenu.contains(p);
	}
	
	/**
	 * boolean ajouterPersonne(Personne p)
	 * Ajoute une Personne en la remplacant par une personne libre et soustrait -1 à nbPlacelLibre
	 **/
	
	public boolean ajouterPersonne(Personne p) {
		
		if(nbPlaceLibre>0) {
			Personne pNull=new Personne(-1, null, null, null, null);
			
				
				int index=contenu.indexOf(pNull);
				contenu.remove(pNull);
				contenu.add(index, p);
				nbPlaceLibre--;
				
				
				return true;
			
		} 
		
		else {
			throw new IllegalArgumentException("Nombre de place libre complet");
		}
	}
	
	/**
	 * ajouterVide
	 * Ajout une personne libre
	 **/
	
	public void ajouterVide() {
		Personne p=new Personne(-1, null, null, null, null);
		contenu.add(p);
		
	}
	
	/**
	 * boolean ajouterAccompagnant
	 * @param Personne p
	 * @param int nb
	 * 
	 * ajout les Accompagnant de personne et soustrait nb à nbPlaceLibre
	 **/
	
	
	public boolean ajouterAccompagnant(Personne p,int nb) {
		if(nbPlaceLibre>0 && p!=null) {
			
			Personne pNull=new Personne(-1, null, null, null, null);
			for(int i=0;i<nb;i++) {
				int index=contenu.indexOf(pNull);
				contenu.remove(pNull);
				nbPlaceLibre--;
				
				contenu.add(index,new Personne(p.getNumero(),"Accompagnant"+p.getNom()+" "+p.getPrenom(),""+i+1,"",""));
			}
			
			return true;
		
		} else {
			throw new IllegalArgumentException("Nombre de place libre complet");
		}
	}
	/**
	 * boolean supprimer
	 * @param Personne p
	 * Supprime la personne passer en paramètre et nbPlaceLibre+1
	 **/
	

	
	public boolean supprimer(Personne p) {
		if(contientPersonne(p)==true) {
			
			nbPlaceLibre++;
			contenu.remove(p);
			ajouterVide();
			
			return true;
		}
		else {
			throw new IllegalArgumentException("La personne n'est pas assigné à une table");
		}
	}
	
	/**
	 * boolean supprimerAccompagnant
	 * @param int num
	 * Supprime les accompagnants de la personne passé en paramètre et nbPlaceLibre+nombre Accompagnant
	 **/
	public void supprimerAccompagnant(int num) {
		Iterator<Personne> it=iterator();
		
		Personne[] pe=new Personne[3];
		
		int i=0;
		while(it.hasNext()) {
			Personne p=it.next();
			
			
			
			if(p.getNumero()==num) {
				pe[i]=p;
				nbPlaceLibre++;
				i+=1;
				
			}
		}
		for(int a=0;a<3;a++) {
			contenu.remove(pe[a]);
		}
	}
	

	/**
	 * Iterator iterator
	 * 
	 * renvooie l'iterator de contenu
	 **/
	
	public Iterator<Personne> iterator() {
		return contenu.iterator();
	}
	
	/**
	 * String toString
	 * 
	 * redefini la classe toString pour renvoyer le contenu de la table si personne alors affiche la personnne sinon affiche place libre
	 **/
	
	public String toString() {
		String s="Table Numéro:"+numeroTable+" "+nbPlaceLibre+" places libres"+"\n";
		s+="Contenu:"+"\n";
		Iterator<Personne> it=iterator();
		while(it.hasNext()) {
			
			
			Personne p=it.next();
			
			if(!p.equals(new Personne(-1, null, null, null, null))) {
				s+="Place "+p.getNom()+" "+p.getPrenom()+" "+" "+p.getNumero()+"\n";
			} else {
				
				s+="Place"+" Libre"+"\n";
			} 
			
		}
		return s;
	}

	/**
	 * boolean equals
	 * @param Object o
	 * 
	 * redefini la methode equals
	 **/
	
	public boolean equals(Object o) {
		if(o==this) {
			return true;
		}
		if(o==null || o.getClass()!=getClass()) {
			return false;
		}
		Table t=(Table) o;
		return t.numeroTable==numeroTable;
	}


	
	
	
}
