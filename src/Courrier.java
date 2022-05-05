/**
 * La classe Courrier est définit par trois types de courriers : Pli, Lettre et Colis.
 */

public class Courrier {

	// Type de courriers : LETTRE, PLI, COLIS 
	/**
	 * Constante pour définir un courrier de type : pli
	 */
	final public static String PLI = "pli";
	/**
	 * Constante pour définir un courrier de type : lettre
	 */
	final public static String LETTRE = "lettre";
	/**
	 * Constante pour définir un courrier de type : colis
	 */
	final public static String COLIS = "colis";

	
	
	
	// Variables d'instance de courriers
	/**
	 * Le volume du courrier en litre.
	 */
	private float volume;
	/**
	 * Vrai si le courrier est prioritaire, faux sinon.
	 */
	private boolean prioritaire;
	/**
	 * Type du courrier (seulement trois possibles : pli, lettre, colis)
	 */
	private String type;
	/**
	 * Le destinataire du courrier.
	 */
	private Destinataire dest;
	
	
	
	// Variable de classe
	/**
	 * Variable de classe donnant le nombre de courriers.
	 */
	public static int nbCourriers = 0; 
	
	

	/**
	 * Courrier(float volume, boolean prioritaire, String type, Destinataire dest)
	 *  
	 * Description: Crée un nouveau Courrier avec les variables pass�s en paramètres.
	 * 
	 * @param volume
	 * @param prioritaire
	 * @param type
	 * @param dest
	 */
	
	public Courrier(float volume, boolean prioritaire, String type, Destinataire dest) {

		this.volume = volume;
		this.prioritaire = prioritaire;
		type = type.trim(); // Passage de la cha�ne de caract�re type � une cha�ne type sans espace
		type = type.toLowerCase(); // Passage de la cha�ne de caract�re type � une cha�ne type tout en minuscule
		if (type.equals(PLI) || type.equals(LETTRE) || type.equals(COLIS)) {
			this.type = type;
			nbCourriers++;
		} else {
			throw new IllegalArgumentException("Le type de colis suivant n'est pas supporté : " + type);
		}
		this.dest=dest;
	}

	
	
	/**
	 * Retourne le volume du courrier.
	 * @return le volume en litre
	 */
	public float getVolume() {
		return this.volume;
	}

	/**
	 * Retourne vrai si le courrier est prioriatire, faux sinon.
	 * @return le statut prioritaire ou non du courrier
	 */
	public boolean isPrioritaire() {
		return this.prioritaire;
	}
	
	/**
	 * Retourne le type de courrier.
	 * @return le type (String)
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Affecte un volume pass� en param�tre au courrier.
	 * @param volume
	 */
	public void setVolume(float volume) {
		this.volume = volume;
	}

	
	/**
	 * Affecte un booléen passé en paramètre au courrier.
	 * @param prioritaire
	 */
	public void setPrioritaire(boolean prioritaire) {
		this.prioritaire = prioritaire;
	}
	
	
	
	/**
	 * Retourne le destinataire du courrier.
	 * @return un Destinataire
	 */
	public Destinataire getDestinataire() {
		return this.dest;
	}
	
	
	
	/**
	 * Affiche un Courrier.
	 */
	public void affiche() {
		String prio = "";
		if(this.prioritaire) {
			prio = " prioritaire.";
		}
		else {
			prio = " non prioritaire.";
		}
		System.out.println("Type : "+this.type+", volume : "+this.volume+","+prio);
		this.dest.afficheDestinataire();
	}
	
	
	/**
	 * Test de la classe Courrier.
	 */
	public static void main(String[] args) { // Test Class Courrier
		
		Destinataire d1 = new Destinataire("Pierre","Dane","Avenue du parc");
		Destinataire d3 = new Destinataire("Luc","Maille","Lieu-dit Jean-Moulin");
		
		Courrier c1 = new Courrier(2,false,LETTRE,d1);
		c1.affiche();
		Courrier c3 = new Courrier(4,false,"PLI",d3);
		c3.affiche();
		System.out.println(nbCourriers);

		// Adresse adr2 = new Adresse(2);
		// Destinataire d2 = new Destinataire("Jean","Denis","Rue du jardin");
		// Courrier c2 = new Courrier(2,false,"LOT",d2); // V�rification du type de courrier valide
		// c2.affiche(); // Indication d'une erreur � l'ex�cution
	}
}
