/**
 * La classe personne est une classe abstraite.
 */
public abstract class Personne {
	
	/**
	 * Prénom de la personne.
	 */
	private String prenom;
	
	/**
	 * Nom de la personne.
	 */
	private String nom;
	
	
	/** 
	  * 
	  * Personne(String prenom,String nom)
	  *
	  * Paramètres : (String prenom, String nom
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Crée une nouvelle Personne avec un nom
	  *    et un prénom passés en paramètres
	  * 
	  */
	public Personne(String prenom, String nom) {
		this.prenom=prenom;
		this.nom=nom;
	}

	
	/** 
	  * 
	  * String getPrenom()
	  *
	  * Paramètres : aucun
	  * 
	  * Retourne   : String prenom
	  * 
	  * Description:
	  *    Retourne le prénom de la Personne
	  * 
	  */
	public String getPrenom() {
		return prenom;
	}

	
	/** 
	  * 
	  * String getNom()
	  *
	  * Paramètres : aucun
	  * 
	  * Retourne   : String nom
	  * 
	  * Description:
	  *    Retourne le nom de la Personne
	  * 
	  */
	public String getNom() {
		return nom;
	}

	
	/** 
	  * 
	  * String toString()
	  *
	  * Paramètres : aucun
	  * 
	  * Retourne   : String
	  * 
	  * Description:
	  *    Retourne les détails des variables de la Personne
	  * 
	  */
	public String toString() {
		return "Prénom : " + this.prenom + ", nom : "+this.nom;
	}
}
