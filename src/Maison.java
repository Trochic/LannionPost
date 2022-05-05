import java.util.*;

/**
 * La classe Maison où se trouve un destinataire de chaque courrier.
 */
public class Maison {
	
	/** 
	 * Une maison est avant tout une adresse dans la vie réelle
	 **/
	private String adresse;
	
	/**
	 *  Une maison possède un destinataire/propriétaire
	 */
	private Destinataire destinataire;
	
	/**
	 * Je sais pas encore
	 */
	private List<Maison> cheminCourt = new LinkedList<>();
	
	/** 
	 * Distance de la source
	 */
	private float distance = Float.MAX_VALUE;
	
	/**
	 * 	Une maison possède une boîte aux lettres
	 */
	public Collection<Courrier> boiteAuxLettres;
	
	/**
	 * On garde en mémoire les voisins d'une Maison
	 */
	public Map<Maison, Float> voisins = new HashMap<>();
	
	/**
	 * Code de direction
	 */
	public static Map<String, Integer> hmRue = new HashMap<>();
	
	
	/** Créé une maison avec une adresse et un destnataire **/
	public Maison(String adresse, Destinataire _destinataire) {
		this.adresse = adresse;
		this.destinataire = _destinataire;
	}
	
	/** Ajoute une destination accessible depuis la maison **/
	public void ajouterDestination(Maison destination, float distance, int codeRue) {
		voisins.put(destination, distance);
		hmRue.put(this.adresse, codeRue);
		
	}
	
	/* Affiche le contenu de la boîte aux lettres */
	/**
	 * Affiche le contenu de la boîte aux lettres.
	 */
	public void contenuBoite() {
		for(Courrier c : boiteAuxLettres) {
			c.affiche();
		}
	}
	
	/* Permet à un facteur d'ajouter un colis à la boîte aux lettres */
	/**
	 * Ajoute un courrier à la boîte aux lettres.
	 * @param courr
	 * @return boolean
	 */
	public boolean ajouterBoite(Courrier courr) {
		this.boiteAuxLettres.add(courr);
		return true;
	}

	/** Getters et Setters **/
	/**
	 * Retourne une adresse.
	 * @return adresse
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * Affecte une adresse.
	 * @param adresse
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * Retourne un Destinataire.
	 * @return Destinataire
	 */
	public Destinataire getDestinataire() {
		return destinataire;
	}

	/**
	 * Affecte un Destinataire.
	 * @param destinataire
	 */
	public void setDestinataire(Destinataire destinataire) {
		this.destinataire = destinataire;
	}

	/**
	 * Retourne un chemin le plus court avec une liste de maisons.
	 * @return cheminCourt
	 */
	public List<Maison> getCheminCourt() {
		return cheminCourt;
	}

	/**
	 * Affecte un chemin le plus court avec une liste de maisons.
	 * @param cheminCourt
	 */
	public void setCheminCourt(List<Maison> cheminCourt) {
		this.cheminCourt = cheminCourt;
	}

	
	/**
	 * Retourne une distance.
	 * @return distance
	 */
	public float getDistance() {
		return distance;
	}

	/**
	 * Affecte une distance.
	 * @param distance
	 */
	public void setDistance(float distance) {
		this.distance = distance;
	}

	/**
	 * Retourne un voisin.
	 * @return voisin
	 */
	public Map<Maison, Float> getVoisins() {
		return voisins;
	}

	/**
	 * Affecte un voisin.
	 * @param voisins
	 */
	public void setVoisins(Map<Maison, Float> voisins) {
		this.voisins = voisins;
	}

	public static int getHmRue(String adresse) {
		return hmRue.get(adresse);
	}

	public void setHmRue(Map<String, Integer> hmRue) {
		Maison.hmRue = hmRue;
	}

	/**
	 * Retourne les caractéristiques d'une maison.
	 */
	@Override
	public String toString() {
		return "Maison "+adresse+" Distance :" +distance;
	}			
}
