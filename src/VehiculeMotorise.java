/**
 * La classe VehiculeMotorise définit un véhicule motorisé du type : Staby, Camionnette ou Quad.
 */
public abstract class VehiculeMotorise extends Transport {
	/**
	 * Le maticule du VehiculeMotorise.
	 */
	private String matricule;
	/**
	 * La capacité en carburant (Litre)
	 */
	private double carburant;
	/**
	 * La consommation en carburant (Litre) par km.
	 */
	private double consoCarburant;
	/**
	 * Le besoin du permis A à vrai, sinon faux. 
	 */
	private boolean besoinPermisA;
	/**
	 * Le besoin du permis B à vrai, sinon faux. 
	 */
	private boolean besoinPermisB;
	
	/**
	 * Crée un nouveau Véhicule Motorisé
	 * @param vMax
	 * @param capac
	 * @param dPieton
	 * @param matric
	 * @param carbu
	 * @param consoCarbu
	 * @param bPA
	 * @param bPB
	 */
	public VehiculeMotorise(double vMax, double capac, boolean dPieton, String matric, double carbu, double consoCarbu, boolean bPA, boolean bPB) {
		super(vMax, capac, dPieton);
		this.matricule = matric;
		this.carburant = carbu;
		this.consoCarburant = consoCarbu;
		this.besoinPermisA = bPA;
		this.besoinPermisB = bPB;
	}
	
	/**
	 * Le calcul du carburant Litre/km
	 * @param nbKm
	 * @return consoCarburant (double)
	 */
	public double calculCarburant(double nbKm) {
		
		return this.consoCarburant * nbKm;
	}

	/**
	 * Retourne les caractéristiques d'un véhicule motorisé.
	 */
	@Override
	public String toString() {
		return "VehiculeMotorise [matricule=" + matricule + ", carburant=" + carburant + ", consoCarburant="
				+ consoCarburant + ", besoinPermisA=" + besoinPermisA + ", besoinPermisB=" + besoinPermisB + "]";
	}
	
	/**
	 * Le type de véhicule motorisé en abstrait.
	 * @return
	 */
	abstract public String getType();
	
	/**
	 * Retourne le matricule du véhicule motorisé.
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * Affecte un matricule au véhicule motorisé.
	 * @param matricule
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	/**
	 * Retourne le carburant en litre du véhicule motorisé.
	 * @return carburant
	 */
	public double getCarburant() {
		return carburant;
	}

	/**
	 * Affecte le carburant en litre du véhicule motorisé.
	 * @param carburant
	 */
	public void setCarburant(double carburant) {
		this.carburant = carburant;
	}

	/**
	 * Retourne la consommation de carburant en litre/km du véhicule motorisé.
	 * @return consoCarburant
	 */
	public double getConsoCarburant() {
		return consoCarburant;
	}

	/**
	 * Affecte la consommation de carburant en litre/km du véhicule motorisé.
	 * @param consoCarburant
	 */
	public void setConsoCarburant(double consoCarburant) {
		this.consoCarburant = consoCarburant;
	}

	/**
	 * Retourne le besoin du permis A.
	 * @return boolean
	 */
	public boolean isBesoinPermisA() {
		return besoinPermisA;
	}

	/**
	 * Affecte le besoin ou non du permis A.
	 */
	public void setBesoinPermisA(boolean besoinPermisA) {
		this.besoinPermisA = besoinPermisA;
	}

	/**
	 * Retourne le besoin du permis B.
	 * @return boolean
	 */
	public boolean isBesoinPermisB() {
		return besoinPermisB;
	}
	/**
	 * Affecte le besoin ou non du permis B.
	 */
	public void setBesoinPermisB(boolean besoinPermisB) {
		this.besoinPermisB = besoinPermisB;
	}
}
