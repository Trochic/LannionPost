/**
 * La classe transport définit tous les types de transports.
 */
public abstract class Transport {
	/**
	 * La vitesse maximale du transport en question.
	 */
	private double vitesseMax;
	/**
	 * Capacité du transport pour transporter les courriers.
	 */
	private double capacite;
	/**
	 * Droit piéton, un booléen.
	 */
	private boolean droitPieton;
	
	/**
	 * Crée un nouveau Transport.
	 * @param vMax
	 * @param capac
	 * @param dPieton
	 */
	public Transport (double vMax, double capac, boolean dPieton) {
		this.vitesseMax = vMax;
		this.capacite = capac;
		this.droitPieton = dPieton;
	}
	
	/**
	 * Retourne les caractéristiques d'un Transport.
	 */
	@Override
	public String toString() {
		return "Transport [vitesseMax=" + vitesseMax + ", capacite=" + capacite + ", droitPieton=" + droitPieton + "]";
	}

	/**
	 * Retourne la vitesse maximale du Transport.
	 * @return
	 */
	public double getVitesseMax() {
		return vitesseMax;
	}

	/**
	 * Affecte une vitesse maximale au Transport.
	 * @param vitesseMax
	 */
	public void setVitesseMax(double vitesseMax) {
		this.vitesseMax = vitesseMax;
	}

	/**
	 * Retourne la capacité.
	 * @return
	 */
	public double getCapacite() {
		return capacite;
	}

	/**
	 * Affecte une capacité au Transport.
	 * @param capacite
	 */
	public void setCapacite(double capacite) {
		this.capacite = capacite;
	}
	
	/**
	 * Retourne le droit piéton en cas de Rue piétonne.
	 * @return boolean
	 */

	public boolean isDroitPieton() {
		return droitPieton;
	}

	/**
	 * Affecte un droit piéton (boolean)
	 * @param droitPieton
	 */
	public void setDroitPieton(boolean droitPieton) {
		this.droitPieton = droitPieton;
	}	
}
