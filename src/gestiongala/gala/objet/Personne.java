package gestiongala.gala.objet;

import java.io.Serializable;


/** 
 * Classe Personne
 * Caractériser par nom,prenom,telephone,email de type String et numero de type entier
 * Défini le modèle Personne
 *
 */

public class Personne implements Serializable {
	
	private String nom;
	private String prenom;
	private String telephone;
	private String email;
	private int numero;
	
	/**
	 * Consctructeur de Personne 
	 * @param numero
	 * @param nom
	 * @param prenom
	 * @param telephone
	 * @param email
	 */
	
	public Personne(int numero,String nom,String prenom,String telephone,String email) {
		this.numero=numero;
		this.nom=nom;
		this.prenom=prenom;
		this.telephone=telephone;
		this.email=email;
	}
	
	/**
	 * int getNumero
	 * Getteur numero 
	 * 
	 * 
	 * 
	 **/
	
	
	public int getNumero() {
		return numero;
	}
	/**
	 * String getNom
	 * Getteur Nom 
	 * 
	 * 
	 * 
	 **/
	
	public String getNom() {
		return nom;
	}
	
	/**
	 * String getPrenom
	 * Getteur Prenom 
	 * 
	 * 
	 * 
	 **/
	public String getPrenom() {
		return prenom;
	}
	/**
	 * String getTelephone
	 * Getteur Téléphone 
	 * 
	 * 
	 * 
	 **/
	public String getTelephone() {
		return telephone;
	}
	/**
	 * String getTelephone
	 * Getteur Email 
	 * 
	 * 
	 * 
	 **/
	public String getEmail() {
		return email;
	}
	
	/**
	 * 
	 * String ToString   
	 * Redefinition de toString pour retourner un string qui contient les attributs
	 * 
	 * 
	 **/
	public String toString() {
		return "Numéro"+numero+"Nom: "+nom+" Prenom: "+prenom+" Telephone: " +telephone+" Email: "+email;
	}
	
	/**
	 * boolean equals 
	 * @param Object o
	 * Redefinition simple de equals en fonction des attributs
	 * 
	 **/
	
	public boolean equals(Object o) {
		if(o==null || getClass()!=o.getClass()) {
			return false;
		}
		if(o==this) {
			return true;
		}
		Personne e=(Personne) o;
		
		return numero==e.getNumero() && nom==e.nom && prenom==e.prenom && email==e.email && telephone==e.telephone;
		
	
	}
	
	/**
	 * int hashCode 
	 * 
	 * Redefinition de hashCode avec une fonction de hachage en prenant en compte les attributs
	 * 
	 **/
	
	public int hashCode() {
		final int prime = 41; //nombre premier
		int resultat = 1;
		
		if(nom!=null) {
			resultat=prime*resultat+nom.hashCode();
		} else {
			resultat=prime*resultat;
		}
		
		if(prenom!=null) {
			resultat=prime*resultat+prenom.hashCode();
		} else {
			resultat=prime*resultat;
		}
		
		if(telephone!=null) {
			resultat=prime*resultat+telephone.hashCode();
		} else {
			resultat=prime*resultat;
		}
		
		if(email!=null) {
			resultat=prime*resultat+email.hashCode();
		} else {
			resultat=prime*resultat;
		}
		resultat=prime*resultat+numero;
	
		return resultat;
	}
	

}
