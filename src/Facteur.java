import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

/**
 * La classe Facteur définit un facteur, l'utilisateur de l'application.
 */
public class Facteur {
	/**
	 * Un facteur a un identifiant.
	 */
	private int id;
	/**
	 * Compteur du nombre de facteurs qui s'incrémente à chaque création de facteur.
	 */
	private static int nbFacteurs = 0; // compteur du nombre de facteurs qui s'incrémente à chaque création de facteur
	/**
	 * Nom du facteur.
	 */
	private String nom;
	/**
	 * Prénom du facteur.
	 */
	private String prenom;
	/**
	 * Sexe du facteur avec un caractère.
	 */
	private char sexe;
	/**
	 * Date de naissance.
	 */
	private Calendar dateNaissance;
	/**
	 * L'âge du facteur.
	 */
	private int age;
	/**
	 * Permis A.
	 */
	private boolean permisA;
	/**
	 * Permis B.
	 */
	private boolean permisB;
	/**
	 * Liste des transports utilisés.
	 */
	private Collection<Transport> mesTransports; // liste des transports utilisés
	/**
	 * Liste des courriers livrés ou à livrer.
	 */
	private Collection<Courrier> mesCourriers; // liste des courriers livrés ou à livrer
	/**
	 * Liste des transports suivis ou à suivre.
	 */
	private Collection<Itineraire> mesItineraires; // liste des transports suivis ou à suivre
	
	
	/** 
	  * 
	  * Facteur(String nom, String prenom, boolean sexe, Calendar dateNaissance, boolean permisA, boolean permisB)
	  *
	  * Paramètres : String nom, String prenom, char sexe, Calendar dateNaissance, boolean permisA, boolean permisB
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Crée un nouveau Facteur avec les variables passés en paramètres
	  *    avec des listes vides et un id généré automatiquement
	  * 
	  */
	public Facteur(String nom, String prenom, char sexe, Calendar dateNaissance, boolean permisA, boolean permisB) {
		if (dateNaissance.after(Calendar.getInstance())) {
			System.out.println("Date de naissance invalide");
		}
		else {
			this.id = ++nbFacteurs; // incrémentation du nombre de facteurs puis met ce nombre dans id
			this.nom = nom;
			this.prenom = prenom;
			this.sexe = sexe;
			this.dateNaissance = dateNaissance;
			this.age = Calendar.getInstance().get(Calendar.YEAR) - dateNaissance.get(Calendar.YEAR); // calcul de l'âge
			this.permisA = permisA;
			this.permisB = permisB;
			this.mesTransports = new ArrayList<Transport>();
			this.mesCourriers = new ArrayList<Courrier>();
			this.mesItineraires = new ArrayList<Itineraire>();
		}
	}
	
	
	/** 
	  * 
	  * Facteur(String nom, String prenom, boolean sexe, Calendar dateNaissance, boolean permisA, boolean permisB)
	  *
	  * Paramètres : int id, String nom, String prenom, char sexe, Calendar dateNaissance, boolean permisA, boolean permisB,
	  * 			 ArrayList<Transport> transports, ArrayList<Courrier> courriers, ArrayList<Itineraire> itineraires
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Crée un nouveau Facteur avec les variables passés en paramètres
	  *    avec des listes pré-remplies et un id
	  * 
	  */
	public Facteur(int id, String nom, String prenom, char sexe, Calendar dateNaissance, boolean permisA, boolean permisB,
			ArrayList<Transport> transports, ArrayList<Courrier> courriers, ArrayList<Itineraire> itineraires) {
		if (dateNaissance.after(Calendar.getInstance())) {
			System.out.println("Date de naissance invalide");
		}
		else {
			this.id = id; // incrémentation du nombre de facteurs puis met ce nombre dans id
			this.nom = nom;
			this.prenom = prenom;
			this.sexe = sexe;
			this.dateNaissance = dateNaissance;
			this.age = Calendar.getInstance().get(Calendar.YEAR) - dateNaissance.get(Calendar.YEAR); // calcul de l'âge
			this.permisA = permisA;
			this.permisB = permisB;
			this.mesTransports = transports;
			this.mesCourriers = courriers;
			this.mesItineraires = itineraires;
		}
	}

	// Getters
	
	/**
	 * Retourne l'id du facteur.
	 * @return id (int)
	 */
	public int getId() {
		return id;
	}

	/**
	 * Retourne le nom du facteur.
	 * @return nom (String)
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Retourne le prénom.
	 * @return prénom (String)
	 */

	public String getPrenom() {
		return prenom;
	}

	/**
	 * Retourne le sexe du facteur.
	 * @return sexe (char)
	 */
	public char getSexe() {
		return sexe;
	}

	/**
	 * Retourne la date de naissance.
	 */
	public Calendar getDateNaissance() {
		return dateNaissance;
	}
	
	/**
	 * Retourne true si le facteur a le permis A, faux sinon.
	 * @return boolean
	 */
	public boolean aPermisA() {
		return permisA;
	}

	/**
	 * Retourne true si le facteur a le permis B, faux sinon.
	 * @return boolean
	 */
	public boolean aPermisB() {
		return permisB;
	}

	/**
	 * Retourne une liste de moyens de transport.
	 * @return Collection<Transport>
	 */
	public Collection<Transport> getMesTransports() {
		return mesTransports;
	}

	/**
	 * Retourne une liste de courriers que doit livrer le facteur.
	 * @return Collection<Courrier>
	 */
	public Collection<Courrier> getMesCourriers() {
		return mesCourriers;
	}

	/**
	 * Retourne une liste d'itinéraires.
	 * @return Collection<Itineraire>
	 */
	public Collection<Itineraire> getMesItineraires() {
		return mesItineraires;
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
	  *    Retourne les détails des variables du Facteur
	  * 
	  */
	@Override
	public String toString() {
		return "id : " + id + ", nom : " + nom + ", prenom : " + prenom + ", sexe : " + sexe + ", dateNaissance : "
				+ dateNaissance + ", age : " + age + ", permisA : " + permisA + ", permisB : " + permisB
				+ ", mesTransports : " + mesTransports + ", mesCourriers : " + mesCourriers + ", mesItineraires : "
				+ mesItineraires;
	}
	
	/**
	 * Retourne une chaine organisée pour l'affichage du facteur dans "Vos informations".
	 */
	public String infoFacteur() {
		String permis = "Aucun permis";
		if(permisA) {
			permis = "permis A";
		}
		else if(permisB) {
			permis = "permis B";
		}
		else if(permisA && permisB) {
			permis = "permis A et permis B";
		}
		return "Id : "+id+"\n\nNom : "+nom+"\n\nPrénom : "+prenom+"\n\nSexe : "+sexe+"\n\nÂge : "+age+" ans\n\nDétient : "+permis;
	}

	/** 
	  * 
	  * setNbFacteurs(int nbFacteurs)
	  *
	  * Paramètres : int nbFacteurs
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Change le nombre de facteurs, cette méthode est utilisée au
	  *    chargement des fichiers
	  * 
	  */
	public static void setNbFacteurs(int nbFacteurs) {
		Facteur.nbFacteurs = nbFacteurs;
	}
	
	
	/** 
	  * 
	  * void setNom(String nom)
	  *
	  * Paramètres : String nom
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Change le nom du Facteur
	  * 
	  */
	public void setNom(String nom) {
		this.nom = nom;
	}

	
	/** 
	  * 
	  * void setPrenom(String prenom)
	  *
	  * Paramètres : String prenom
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Change le prénom du Facteur
	  * 
	  */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	
	/** 
	  * 
	  * void setPermisA(boolean permisA)
	  *
	  * Paramètres : boolean permisA
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Modifie la capacité à conduire certains véhicules
	  * 
	  */
	public void setPermisA(boolean permisA) {
		this.permisA = permisA;
	}

	
	/** 
	  * 
	  * void setPermisB(boolean permisB)
	  *
	  * Paramètres : boolean permisB
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Modifie la capacité à conduire certains véhicules
	  * 
	  */
	public void setPermisB(boolean permisB) {
		this.permisB = permisB;
	}

	
	/** 
	  * 
	  * void ajouterTransport(Transport t)
	  *
	  * Paramètres : Transport t
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Ajoute le transport passé en paramètre
	  * 
	  */
	public void ajouterTransport(Transport t) {
		mesTransports.add(t);
	}
	
	
	/** 
	  * 
	  * void enleverTransport(Transport t)
	  *
	  * Paramètres : Transport t
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Enlève le transport passé en paramètre
	  * 
	  */
	public void enleverTransport(Transport t) {
		mesTransports.remove(t);
	}
	
	
	/** 
	  * 
	  * void listerTransport()
	  *
	  * Paramètres : aucun
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Liste les transports utilisés par le Facteur
	  * 
	  */
	public void listerTransport() {
		System.out.println("Liste des transports : ");
		for (Transport t : mesTransports) {
			System.out.println("	" + t);
		}
	}
	
	
	/** 
	  * 
	  * void ajouterCourrier(Courrier c)
	  *
	  * Paramètres : Courrier c
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Ajoute le courrier passé en paramètre
	  * 
	  */
	public void ajouterCourrier(Courrier c) {
		mesCourriers.add(c);
	}
	
	
	/** 
	  * 
	  * void enleverCourrier(Courrier c)
	  *
	  * Paramètres : Courrier c
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Enlève le courrier passé en paramètre
	  * 
	  */
	public void enleverCourrier(Courrier c) {
		mesCourriers.remove(c);
	}
	
	
	/** 
	  * 
	  * void listerTransport()
	  *
	  * Paramètres : aucun
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Liste les courriers assignés au Facteur
	  * 
	  */
	public void listerCourrier() {
		System.out.println("Liste des courriers : ");
		for (Courrier c : mesCourriers) {
			System.out.println("	" + c);
		}
	}
	
	
	/** 
	  * 
	  * void ajouterItineraire(Itineraire i)
	  *
	  * Paramètres : Itineraire i
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Ajoute l'itinéraire passé en paramètre
	  * 
	  */
	public void ajouterItineraire(Itineraire i) {
		mesItineraires.add(i);
	}
	
	
	/** 
	  * 
	  * void enleverItineraire(Itineraire i)
	  *
	  * Paramètres : Itineraire i
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Enlève l'itinéraire passé en paramètre
	  * 
	  */
	public void enleverItineraire(Itineraire i) {
		mesItineraires.remove(i);
	}

	
	/** 
	  * 
	  * void listerItineraire()
	  *
	  * Paramètres : aucun
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Liste les itinéraires assignés au Facteur
	  * 
	  */
	public void listerItineraire() {
		System.out.println("Liste des itinéraires : ");
		for (Itineraire i : mesItineraires) {
			System.out.println("	" + i);
		}
	}
}
