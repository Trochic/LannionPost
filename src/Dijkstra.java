import java.util.*;
import java.util.Map.Entry;

/**
 * La classe Dijkstra permet de calculer la plus petite distance de la source (la poste) aux maisons dans une Carte.
 */

public class Dijkstra {
	
	/**
	 * Itineraire de maison
	 */
	private ArrayList<Maison> itineraire;
		
	public ArrayList<Maison> getItineraire() {
		return itineraire;
	}

	public void setItineraire(ArrayList<Maison> itineraire) {
		this.itineraire = itineraire;
	}
	
	/**
	 * Constructeur initialisant l'itineraire
	 */
	public Dijkstra() {
		this.itineraire = new ArrayList<>();
	}

	/**
	 
	 */
	@Override
	public String toString() {
		return "Dijkstra [itineraire=" + itineraire + "]";
	}

	/**
	 * Permet de calculer le chemin le plus court depuis une source pour n'importe quelle maison de la carte
	 * @param carte
	 * @param source
	 * @return une Carte
	 */
	public static Carte calculCheminPlusCourtDepuisSource(Carte carte, Maison source) {
		List<Maison> vide = new LinkedList<>();
		for (Maison aZero : carte.getMaisons()) {
			aZero.setCheminCourt(vide);
		}
	    source.setDistance(0);

	    Set<Maison> maisonVues = new HashSet<>();
	    Set<Maison> maisonNonVues = new HashSet<>();

	    maisonNonVues.add(source);
	 
	    while (maisonNonVues.size() != 0) {
	        Maison maisonCourante = getMaisonLaPlusProche(maisonNonVues);
	        maisonNonVues.remove(maisonCourante);
	        
	        for (Entry<Maison, Float> paireAdjacente: maisonCourante.getVoisins().entrySet()) 
	        {
	            Maison maisonAdjacente = paireAdjacente.getKey();
	            Float distanceMaisonAdjacente = paireAdjacente.getValue();
	            if (!maisonVues.contains(maisonAdjacente)) 
	            {
	            	calculDistanceMinimum(maisonAdjacente, distanceMaisonAdjacente, maisonCourante);
	                maisonNonVues.add(maisonAdjacente);
	            }
	        }
	        maisonVues.add(maisonCourante);
	    }
	    return carte;
	}
	
	
	/**
	 * Permet d'obtenir la maison la plus proche.
	 * @param maisonNonVues
	 * @return une Maison
	 */
    private static Maison getMaisonLaPlusProche(Set<Maison> maisonNonVues) {
    	Maison maisonPlusProche = null;
    	float distancePlusCourte = Float.MAX_VALUE;
        for (Maison maison : maisonNonVues) {
            float distanceMaison = maison.getDistance();
            if (distanceMaison < distancePlusCourte) {
            	distancePlusCourte = distanceMaison;
                maisonPlusProche = maison;
            }
        }
        return maisonPlusProche;
    }
		

    /**
     * Permet de calculer la distance minimum entre la source et une maison.
     * @param maisonComparaison
     * @param distanceMaisonComparaison
     * @param maisonSource
     */
    private static void calculDistanceMinimum(Maison maisonComparaison, Float distanceMaisonComparaison, Maison maisonSource) {
    	Float sourceDistance = maisonSource.getDistance();
        if (sourceDistance + distanceMaisonComparaison < maisonComparaison.getDistance()) {
        	maisonComparaison.setDistance(sourceDistance + distanceMaisonComparaison);
            LinkedList<Maison> cheminPlusCourt = new LinkedList<>(maisonSource.getCheminCourt());
            cheminPlusCourt.add(maisonSource);
            maisonComparaison.setCheminCourt(cheminPlusCourt);
        }
    }
}
