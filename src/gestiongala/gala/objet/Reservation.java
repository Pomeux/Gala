package gestiongala.gala.objet;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe Reservation   
 * Implémente Serializable
 * Caractériser par une date de type LocalDate, un nbTable,nbPlace de type int et un montant de type double
 * 
 **/

public class Reservation implements Serializable
{
	private LocalDate date;
	private int nbTable;
	private int nbPlace;
	private double montant;
	
	/**
	 * Constructeur Reservation
	 * @param LocalDate date
	 * @param int nbTable
	 * @param int nbPlace
	 * @param double montant
	 * 
	 **/
	
	
	public Reservation(LocalDate date,int nbTable, int nbPlace, double montant) 
	{
		this.date=date;
		this.nbTable=nbTable;
		this.nbPlace=nbPlace;
		this.montant=montant;
	}
	
	/**
	 * LocalDAte getDate
	 * Getteur date 
	 * 
	 * 
	 * 
	 **/
	
	public LocalDate getDate() {
		return date;
	}
	/**
	 * int getnbPlace
	 * Getteur nbPlace 
	 * 
	 * 
	 * 
	 **/
	
	public int getnbPlace() {
		return nbPlace;
	}
	/**
	 * int getnbPlace
	 * Getteur nbPlace 
	 * 
	 * 
	 * 
	 **/
	
	public int getnbTable() {
		return nbTable;
	}

	/**
	 * boolean equals
	 * @param Object o
	 * Redifiniton simple de equals selon les attributs
	 * 
	 * 
	 * 
	 **/
	
	public boolean equals(Object o) {
		if(o==null || o.getClass()!=getClass()) {
			return false;
		}
		if(o==this) {
			return true;
		}
		Reservation r=(Reservation) o;

		return r.date==date && r.montant==montant && r.nbPlace==nbPlace && r.nbTable==nbTable;
		
	}
	
	/**
	 * String toString
	 * Redéfinition de toString pour retourner un string avec les attributs
	 * 
	 **/
	
	public String toString() {
		return "Reservation: "+" Date: "+date+" Nombre Place: "+nbPlace+" Nombre Table: "+nbTable+" Montant: "+montant;
	}
}
