import java.util.ArrayList;
import java.util.Collection;

/**
 * La classe Garage est l'entrepôt où sont stockés tous les véhicules de la poste.
 */
public class Garage {
	/**
	 * La capacité du garage.
	 */
	private int capacite;
	/**
	 * Liste de véhicules.
	 */
	private ArrayList<VehiculeMotorise> vehicules;
	
	
	/** 
	  * 
	  * Garage(int capacite)
	  *
	  * Paramètres : int capacite
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Crée un nouveau Garage avec une capacité limite
	  *    passée en paramètre
	  * 
	  */
	public Garage(int capacite) {
		this.capacite = capacite;
		this.vehicules = new ArrayList<VehiculeMotorise>(capacite);
	}
	
	
	/** 
	  * 
	  * void ajouterVehicule(VehiculeMotorise v)
	  *
	  * Paramètres : VehiculeMotorise v
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Ajoute le véhicule passé en paramètre si le
	  *    Garage n'est pas plein
	  * 
	  */
	public void ajouterVehicule(VehiculeMotorise v) {
		if (vehicules.size() < capacite) {
			vehicules.add(v);
		}
	}
	
	
	/** 
	  * 
	  * void modifierVehicule(VehiculeMotorise v, int i)
	  *
	  * Paramètres : VehiculeMotorise v, int i
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Modifie le véhicule de l'indice passé en paramètre
	  *    par le véhicule passé en paramètre
	  * 
	  */
	public void modifierVehicule(VehiculeMotorise v, int i) {
		vehicules.set(i, v);
	}
	
	
	/** 
	  * 
	  * void enleverVehicule(VehiculeMotorise v)
	  *
	  * Paramètres : VehiculeMotorise v
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Enlever le véhicule passé en paramètre
	  * 
	  */
	public void enleverVehicule(VehiculeMotorise v) {
		vehicules.remove(v);
	}
	
	
	/** 
	  * 
	  * void listerVehicules()
	  *
	  * Paramètres : aucun
	  * 
	  * Retourne   : rien
	  * 
	  * Description:
	  *    Liste les véhicules du Garage
	  * 
	  */
	public void listerVehicules() {
		System.out.println("Liste des véhicules : ");
		for (VehiculeMotorise v : vehicules) {
			System.out.println("	" + v);
		}
	}
	
	/** 
	  * 
	  * Collection<VehiculeMotorise> getVehicules()
	  *
	  * Paramètres : aucun
	  * 
	  * Retourne   : Collection de VehiculeMotorise
	  * 
	  * Description:
	  *    Retourne les véhicules du Garage
	  * 
	  */
	public Collection<VehiculeMotorise> getVehicules() {
		return vehicules;
	}
}
