
/**
 * La classe Destinataire est une personne qui reçoit un courrier soit un pli, une lettre ou un colis.
 */
public class Destinataire extends Personne{
	
	/**
	 * Le nom de la rue du destinataire.
	 */
	private String nomRue;
	
	
	/** 
	  * 
	  * Destinataire(String prenom,String nom,String nomRue,Adresse adr)
	  *
	  * Paramètres : String prenom, String nom, String nomRue, Adresse adr
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Crée un nouveau Destinaire avec un nom, un prénom
	  *    et une adresse passés en paramètres
	  * 
	  */
	public Destinataire(String prenom,String nom,String nomRue) {
		super(prenom,nom);
		this.nomRue=nomRue;
	}

	
	/** 
	  * 
	  * String getNomRue()
	  *
	  * Paramètres : aucun
	  * 
	  * Retourne   : String nomRue
	  * 
	  * Description:
	  *    Retourne le nom de la rue du Destinataire
	  * 
	  */
	public String getNomRue() {
		return nomRue;
	}

	
	/** 
	  * 
	  * String setNomRue()
	  *
	  * Paramètres : String nomRue
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Change le nom de la rue du Destinataire
	  * 
	  */
	public void setNomRue(String nomRue) {
		this.nomRue = nomRue;
	}

	
	/** 
	  * 
	  * void afficheDestinataire()
	  *
	  * Paramètres : aucun
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Affiche les détails du Destinataire
	  * 
	  */
	public void afficheDestinataire() {
		System.out.println("Destinataire [ "+super.toString()+"]");
	}
}
