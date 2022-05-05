/**
 * La classe Adresse composée d'un numéro.
 */
public class Adresse {
	
	/**
	 * Numéro de l'adresse.
	 */
	private int numAdr;
	
	/**
	 * Crée une nouvelle adresse avec un entier (numéro d'adresse) en paramètre.
	 * @param numAdr
	 */
	public Adresse(int numAdr) {
		this.numAdr=numAdr;
	}
	
	
	/**
	 * Return les caractéristiques d'une adresse.
	 */
	public String toString(){
		return "Numéro d'adresse : "+this.numAdr;
	}
	
	/**
	 * Retourne le numéro de l'adresse.
	 * @return
	 */
	public int getNumAdr() {
		return this.numAdr;
	}
	
	/**
     * Affiche une Adresse.
     */
	public void afficheAdr() {
		System.out.println(this.toString());
	}
}
