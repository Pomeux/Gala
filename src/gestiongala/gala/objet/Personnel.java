package gestiongala.gala.objet;

/**
 * Classe Personnel
 * Fille de Personne 
 * Défini le modèle Personnel
 * 
 **/

public class Personnel extends Personne {


	/**
	 * Constructeur de Personnel
	 * 
	 * @param numero
	 * @param nom
	 * @param prenom
	 * @param telephone
	 * @param email
	 * 
	 **/
	public Personnel(int numero,String nom, String prenom, String telephone, String email) {
		super(numero,nom, prenom, telephone, email);
		
	}
		
	/**
	 * String ToString   
	 * Redefinition de toString pour retrouver un string qui contient en plus la catégorie
	 * 
	 * 
	 **/
	public String toString() {
		return "Catégorie:P"+super.toString();
	}
	
	

}
