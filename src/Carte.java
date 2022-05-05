import java.util.*;

/**
 * La classe Carte qui définit les maisons pour l'itinéraire du facteur.
 */
public class Carte {

	/** 
	 * Liste des maisons formant les sommets de notre carte 
	**/
	private Set<Maison> maisons = new HashSet<>();
	
	/** 
	 * Ajouter une maison à la Carte
	 * @param maisonA
	 */
	public void ajouterMaison(Maison maisonA) {
		maisons.add(maisonA);
	}

	/**
	 * Retourne la liste des maisons de la Carte
	 * @return
	 */
	public Set<Maison> getMaisons() {
		return maisons;
	}

	/**
	 * Permet d'importer un set de maisons directement
	 * @param maisons
	 */
	public void setMaisons(Set<Maison> maisons) {
		this.maisons = maisons;
	}

	/**
	 * Retourne une carte de maisons.
	 */
	@Override
	public String toString() {
		return "Carte [maisons=" + maisons + "]\n";
	}
	
}
