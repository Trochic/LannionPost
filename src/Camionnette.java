
/**
 * La classe Camionnette est étendue par la classe VehiculeMotorise.
 */
public class Camionnette extends VehiculeMotorise {
	
	/**
	 * Crée une nouvelle Camionnette.
	 * @param vMax
	 * @param capac
	 * @param dPieton
	 * @param matric
	 * @param carbu
	 * @param consoCarbu
	 * @param bPA
	 * @param bPB
	 */
	public Camionnette(double vMax, double capac, boolean dPieton, String matric, double carbu, double consoCarbu, boolean bPA,
			boolean bPB) {
		super(vMax, capac, dPieton, matric, carbu, consoCarbu, bPA, bPB);
	}

	//Ce toString() a été modifié
	/**
	 * Retourne les caractéristiques d'une camionnette.
	 */
	@Override
	public String toString() {
		return "Camionnette, \"" + getMatricule() + "\"";
	}
	
	/**
	 * Retourne le type de la camionnette.
	 */
	public String getType() {
		return "Camionnette";
	}
}
