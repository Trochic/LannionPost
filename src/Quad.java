/**
 * La classe Quad est un moyen de transport et également un véhicule motorisé.
 */
public class Quad extends VehiculeMotorise {
	
	//Main de test pour les véhicules
	/**
	 * Test de la classe Quad.
	 * @param args
	 */
	public static void main (String[] args) {
		Quad test = new Quad(50.0, 50.0, true, "ade451de", 100.0, 1.2, true, false);
		System.out.println(test);
		Staby teststab = new Staby(50.0,50.0,true,"4564ade", 100.0, 1.2, false, true);
		System.out.println(teststab);
	}
	
	/**
	 * Crée un nouveau Quad.
	 * @param vMax
	 * @param capac
	 * @param dPieton
	 * @param matric
	 * @param carbu
	 * @param consoCarbu
	 * @param bPA
	 * @param bPB
	 */
	public Quad(double vMax, double capac, boolean dPieton, String matric, double carbu, double consoCarbu, boolean bPA,
			boolean bPB) {
		super(vMax, capac, dPieton, matric, carbu, consoCarbu, bPA, bPB);
	}

	//Ce toString() a été modifié
	
	/**
	 * Retourne le matricule d'un Quad.
	 */
	@Override
	public String toString() {
		return "Quad, \"" + getMatricule() + "\"";
	}
	
	/**
	 * Retourne le type de Quad.
	 */
	public String getType() {
		return "Quad";
	}
}
