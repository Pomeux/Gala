package gestiongala.gala.objet;

import java.io.Serializable;


/**
 * Classe couple
 * Implemente Serializable et Comparable
 * Le Couple est comme son nom l'indique un couple des deux classes
 * 
 * Caracterise par deux attributs Etudiant et Reservation
 * 
 **/

public class Couple implements Serializable,Comparable<Couple>{
	
	private Etudiant t;
	private Reservation v;
	
	/**
	 * Constructeur de Couple 
	 * @param Etudiant t
	 * @param Reservation v
	 * 
	 * 
	 **/
	
	public Couple(Etudiant t,Reservation v) {
		this.t=t;
		this.v=v;
	}
	
	/**
	 * Eutidant getT
	 * Getteur Etudiant 
	 * 
	 * 
	 * 
	 **/
	
	public Etudiant getT() {
		return t;
	}
	/**
	 * Reservation getV
	 * Getteur Reservation 
	 * 
	 * 
	 * 
	 **/
	public Reservation getV() {
		return v;
	}
	
	/**
	 * boolean equals 
	 * @param Object o
	 * Redefinition de equals en fonction de si les deux élements sont égaux à l'autre couple
	 * 
	 **/
	
	public boolean equals(Object o) {
		if(o==this) {
			return true;
		}
		if(o==null || o.getClass()!=getClass()) {
			return false;
		}
		Couple c=(Couple) o;
		Etudiant e=c.getT();
		Reservation r=c.getV();
		
		
		return t.equals(e) && v.equals(r);
	}
	
	/**
	 * int compareTo
	 * @param Couple e2
	 * Implémentation de compareTo
	 * On souhaite que le couple qui a un etudiant  d'une année 5 soit inférieur pour qu'il sorte en premier de la queue(voir classe FileAttente et Gala)
	 * Si c'est autre alors on considère que c'est égaux
	 * 
	 **/
	
	@Override
	public int compareTo(Couple e2) {
		Etudiant t2=e2.getT();
		
		if(t.getAnnee()==5 && t2.getAnnee()!=5) {
			return -1;
		} else if(t.getAnnee()!=5 && t2.getAnnee()==5) {
			return 1;
		}
		return 0;
	}

	
}
