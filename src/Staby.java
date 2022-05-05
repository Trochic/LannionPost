/**
 * La classe Staby est un moyen de transport et également un véhicule motorisé.
 */
public class Staby extends VehiculeMotorise {

	/**
	 * Crée un nouveau Staby avec les paramètres passé en argument.
	 * @param vMax
	 * @param capac
	 * @param dPieton
	 * @param matric
	 * @param carbu
	 * @param consoCarbu
	 * @param bPA
	 * @param bPB
	 */
	public Staby(double vMax, double capac, boolean dPieton, String matric, double carbu, double consoCarbu,
			boolean bPA, boolean bPB) {
		super(vMax, capac, dPieton, matric, carbu, consoCarbu, bPA, bPB);
	}

	/**
	 * Retourne le matricule d'un Staby.
	 */
	@Override
	public String toString() {
		return "Staby, \"" + getMatricule() + "\"";
	}
	
	/**
	 * Retourne le type du Staby.
	 */
	public String getType() {
		return "Staby";
	}
}
