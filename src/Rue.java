/**
 * La classe Rue définit une rue par laquelle un facteur doit passer et donc sera présente dans un itinéraire.
 */
public class Rue {
	/**
	 * Nom de la rue.
	 */
	private String nom;
	/**
	 * Vrai si la rue est à sens unique, faux sinon.
	 */
	private boolean sensUnique;
	/**
	 * Vrai si la rue est piétonne, faux sinon.
	 */
	private boolean pietonne;
	/**
	 * En Km/h
	 */
	private int limitationVitesse; 
	/**
	 * En Km
	 */
	private float longueur;
	
	/**
	 * Temps en heures.
	 */
	private double temps;
	
	/**
	 * Nombre de numéros de la rue.
	 */
	private int nbNum;
	
	/**
	 * On fixe la vitesse (en Km/h) moyenne du facteur à pieds.
	 */
	public final int apieds=5;
	
	
	/** Rue(String nom,boolean sens,boolean pieds,int limit,float longue,int nbNum)
	  * Description:
	  *    Crée une nouvelle Rue avec les variables passés en paramètres.
	  * @param nom
	  * @param sens
	  * @param pieds
	  * @param limit
	  * @param longue
	  * @param nbNum
	  */
	public Rue(String nom,boolean sens,boolean pieds,int limit,float longue,int nbNum,double tps) {
		this.nom=nom;
		this.sensUnique=sens;
		this.pietonne=pieds;
		this.limitationVitesse=limit;
		this.longueur=longue;
		this.nbNum=nbNum;
		this.temps=tps;
	}
	
	/** Rue(String nom, float longue)
	 * Description:
	 * Crée une nouvelle Rue avec les variables passés en paramètres.
	 * @param nom
	 * @param longue
	 */
	public Rue(String nom, float longue) {
		this.nom = nom;
		this.longueur = longue;
	}
	
	/** Retourne le parcours d'une rue en Km/h.
     * @return Le parcours en Km/h (float). **/
	public float parcoursRue(){
		float parcours;
		if(this.pietonne) {
			parcours=this.longueur*apieds;
		}
		else {
			parcours=this.limitationVitesse*this.longueur;
		}
		return parcours;
	}
	
	/** Retourne vrai si la rue est piétonne, faux sinon.
	 * @return Un booléen.**/
	public boolean isPietonne() {
		return this.pietonne;
	}
	
	/**
     * Retourne le nombre de numéros de la rue.
     * 
     * @return Le nombre de numéros de la rue.
     */
	public int getNbNum() {
		return this.nbNum;
	}
	
	/**
	 * Retourne le nom de la Rue.
	 * @return nom
	 */
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * Retourne le sens de la rue, vrai si sens unique, faux sinon.
	 * @return boolean
	 */
	public boolean isSensUnique() {
		return sensUnique;
	}

	/**
	 * Retourne la limitation de vitesse de la rue en km/h.
	 * @return limitationVitesse
	 */
	public int getLimitationVitesse() {
		return limitationVitesse;
	}

	/**
	 * Retourne la longueur d'une rue en km.
	 * @return longueur
	 */
	public float getLongueur() {
		return longueur;
	}

	/**
	 * Retourne le temps à parcourir pour une rue (en heure).
	 * @return temps
	 */
	public double getTemps() {
		return temps;
	}

	/**
     * Retourne les caractéristiques d'une Rue.
     * 
     * @return Une Rue.
     */
	public String toString() {
		String sens="à deux sens";
		String pieds="non piétonne";
		if(sensUnique) {
			sens="à sens unique";
		}
		if(pietonne) {
			pieds="piétonne";
		}
		return "Rue "+this.nom+"\n22300, LANNION\nRue "+sens+" et "+pieds+".\nLimite de vitesse : "+this.limitationVitesse+"Km/h, longueur : "+this.longueur+"Km. \nNombre de maisons : "+this.nbNum+".\nLe temps estimé pour cette rue : "+getTemps(temps)+".";
	}
	
	/**
	 * Méthode qui passe un nombre (double) au format heure,minute (Exemple avec 2.3 : 2h30). Amélioration de l'affichage du temps.
	 * @param t
	 * @return temps
	 */
	public String getTemps(double t) {
		int heure,min;
		heure = (int) t;
		min = (int) ((t-heure)*60);
		return heure+"h"+min+"minutes";
	}
	
	
	/**
     * Affiche une Rue.
     */
	public void afficheRue() {
		System.out.println(this.toString());
	}
	
	/**
     * Test de la classe Rue.
     */
	public static void main(String[] args) { // Test de la classe Rue
		Rue r1 = new Rue("Général De Gaulle",false,false,30,2,35,0.3);
		
		r1.afficheRue();
	}
}
