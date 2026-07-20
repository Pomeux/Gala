package gestiongala.gala.objet;


/**
 * 
 * Classe Etudiant 
 * Est fille de Personne 
 * Caracteriser de plus par une année de type entier
 * Défini le modèle Etudiant
 * 
 **/

public class Etudiant extends Personne {

	private int annee;
	
	/**
	 * Consctructeur de Etudiant 
	 * @exception IllegalArgumentException
	 * Lève exception IllegalArgumentException si l'année n'est pas comprise entre 1 et 5
	 * @param numero
	 * @param nom
	 * @param prenom
	 * @param telephone
	 * @param email
	 * @param annee
	 * 
	 */
	
	public Etudiant(int numeroEtudiant,String nom, String prenom, String telephone, String email,int annee) {
		super(numeroEtudiant,nom, prenom, telephone, email);
		if(1<=annee && annee<=5) {
	
			this.annee=annee;
		} else 
		{
			throw new IllegalArgumentException("L'année doit être comprise entre 1 et 5");
		}
	}
	/**
	 * int getAnnee
	 * Getteur Annee 
	 * 
	 * 
	 * 
	 **/
	public int getAnnee() {
		return annee;
	}

	

	/**
	 * String ToString   
	 * Redefinition de toString pour retourner un string avec en plus l'année et la catégorie 
	 * 
	 * 
	 **/
	
	public String toString() {
		return "Catégorie:E "+super.toString()+"Année: "+annee;
	}
	
	/**
	 * boolean equals 
	 * @param Object o
	 * Redefinition simple de equals de plus annee
	 * 
	 **/
	
	public boolean equals(Object o) {
		if(o==null || getClass()!=o.getClass()) {
			return false;
		}
		if(o==this) {
			return true;
		}
		Etudiant e=(Etudiant) o;
		
		return e.annee==annee && super.equals(o);
		
	
	}
	
	/**
	 * int hashCode 
	 * 
	 * Redefinition de hashCode de annee
	 * 
	 **/
	
	public int hashCode() {
		final int prime = 41; 
		int resultat = super.hashCode();
		
		resultat=resultat*prime+annee;
		
		return resultat;
	}
	


}
