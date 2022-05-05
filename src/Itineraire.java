import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Itineraire {
	
	/** La distance de l'itineraire en Km. **/
	private double distance;
	
	/** La duree de l'itineraire en heures, minutes. **/
	private double duree;
	
	/** Le nombre de maisons de l'itineraire. **/
	private int nbMaisons;
	
	/** La liste de rues non triée. **/
	private ArrayList<Rue> itNonTriee;
	
	/** La liste d'un itinéraire. **/
	private ArrayList<Rue> it;
	
	/** La carte **/
	private Carte graph;
	
	/**
	 * La liste de maisons à livrer.
	 */
	private ArrayList<Maison> maisonAlivrer;
	
	/**
	 * Liste des maisons de la carte.
	 */
	private ArrayList<Maison> maisonDeLaCarte;
	
	/**
	 * La source (la poste).
	 */
	private Maison source;
	
	/**
	 * Couple de String,Integer qui représente un nom de rue et un code de direction.
	 */
	protected HashMap<String,Integer> hmRue;
	
	/**
	 * L'itinéraire de maisons.
	 */
	private ArrayList<Maison> itineraire;
	
	/**
	 * Le nombre de rues.
	 */
	private int nbr;
	
	/**
	 * Crée un nouvel itinéraire.
	 * @param distance
	 * @param duree
	 */
	public Itineraire(double distance,double duree) {
		this.distance=distance;
		this.duree=duree;
		Destinataire dest1 = new Destinataire("Pierre", "Jean", "Grtgio 2");
		Destinataire dest2 = new Destinataire("Jean", "Jean", "Mlojgr 2");
		Destinataire dest3 = new Destinataire("Lucas", "Jean", "Aiokg 2");
		Destinataire dest4 = new Destinataire("Remy", "Jean", "Pouebd 2");
		Destinataire dest5 = new Destinataire("Noam", "Jean", "Christophe 2");
		Destinataire dest6 = new Destinataire("Goksa", "Jean", "Olezea 2");
		Destinataire dest7 = new Destinataire("aregztg", "Jean", "Oemame 2");
		Destinataire dest8 = new Destinataire("cvsdfvs", "Jean", "David 2");
		Destinataire dest9 = new Destinataire("Wxcqdf", "Jean", "Ceroir 2");
		Destinataire dest10 = new Destinataire("yhjryjr", "Jean", "Alizar 2");
		
		Rue r1 = new Rue("Georges Clémenceau",false,false,80,3,35,0.6);
		Rue r2 = new Rue("Edouard Branly",false,false,50,2,20,0.25);
		Rue r3 = new Rue("Général De Gaulle",true,true,30,1,40,0.48);
		Rue r4 = new Rue("Jean Moulin",false,false,50,2,23,0.9);
		Rue r5 = new Rue("Victor Hugo",false,false,30,1,19,0.21);
		Rue r6 = new Rue("Jack Denis",false,false,50,2,15,0.16);
		Rue r7 = new Rue("Michel Dupond",false,false,50,2,15,0.16);
		Rue r8 = new Rue("Hector le Brave",false,false,30,3,24,1);
		Rue r9 = new Rue("Jean Mirar",false,true,80,1,20,0.5);
		Rue r10 = new Rue("Jacques Louche",true,false,50,4,21,1.9);
		
		
		Maison MaisonA = new Maison(r1.getNom(),dest1);
		Maison MaisonB = new Maison(r2.getNom(),dest2);
		Maison MaisonC = new Maison(r3.getNom(),dest3);
		Maison MaisonD = new Maison(r4.getNom(),dest4); 
		Maison MaisonE = new Maison(r5.getNom(),dest5);
		Maison MaisonF = new Maison(r6.getNom(),dest6);
		Maison MaisonG = new Maison(r7.getNom(),dest7);
		Maison MaisonH = new Maison(r8.getNom(),dest8);
		Maison MaisonI = new Maison(r9.getNom(),dest9);
		Maison MaisonJ = new Maison(r10.getNom(),dest10);

		MaisonA.ajouterDestination(MaisonB, r1.getLongueur(),2);
		MaisonA.ajouterDestination(MaisonC, r2.getLongueur(),1);

		MaisonB.ajouterDestination(MaisonD, r3.getLongueur(),2);
		MaisonB.ajouterDestination(MaisonF, r4.getLongueur(),3);
		MaisonB.ajouterDestination(MaisonA, r3.getLongueur(),4);

		MaisonC.ajouterDestination(MaisonE, r5.getLongueur(),4);
		MaisonC.ajouterDestination(MaisonA, r2.getLongueur(),4);
		
		MaisonD.ajouterDestination(MaisonE, r6.getLongueur(),2);
		MaisonD.ajouterDestination(MaisonF, r7.getLongueur(),1);
		MaisonB.ajouterDestination(MaisonA, r3.getLongueur(),4);
		
		MaisonE.ajouterDestination(MaisonF, r8.getLongueur(),2);
		MaisonE.ajouterDestination(MaisonD, r6.getLongueur(),4);
		MaisonE.ajouterDestination(MaisonJ, r5.getLongueur(),3);
		MaisonE.ajouterDestination(MaisonA, r1.getLongueur(),2);
		
		MaisonF.ajouterDestination(MaisonE, r8.getLongueur(),3);
		MaisonF.ajouterDestination(MaisonE, r8.getLongueur(),2);
		MaisonF.ajouterDestination(MaisonG, r5.getLongueur(),1);
		
		MaisonG.ajouterDestination(MaisonA, r1.getLongueur(),4);
		MaisonG.ajouterDestination(MaisonE, r7.getLongueur(),3);
		MaisonG.ajouterDestination(MaisonI, r10.getLongueur(),3);
		
		MaisonH.ajouterDestination(MaisonG, r7.getLongueur(),1);
		MaisonH.ajouterDestination(MaisonJ, r8.getLongueur(),4);
		
		MaisonI.ajouterDestination(MaisonH, r9.getLongueur(),1);
		MaisonI.ajouterDestination(MaisonJ, r10.getLongueur(),3);
		MaisonI.ajouterDestination(MaisonG, r10.getLongueur(),2);
		
		MaisonJ.ajouterDestination(MaisonI, r10.getLongueur(),4);
		MaisonJ.ajouterDestination(MaisonD, r3.getLongueur(), 3);
		MaisonJ.ajouterDestination(MaisonA, r1.getLongueur(), 3);
		
		graph = new Carte();

		graph.ajouterMaison(MaisonA);
		graph.ajouterMaison(MaisonB);
		graph.ajouterMaison(MaisonC);
		graph.ajouterMaison(MaisonD);
		graph.ajouterMaison(MaisonE);
		
		graph.ajouterMaison(MaisonF);
		graph.ajouterMaison(MaisonG);
		graph.ajouterMaison(MaisonH);
		graph.ajouterMaison(MaisonI);
		graph.ajouterMaison(MaisonJ);
		
		this.source = MaisonA;
		
		maisonAlivrer = new ArrayList<>(this.getGraph().getMaisons().size());
		maisonDeLaCarte = new ArrayList<>(this.getGraph().getMaisons().size());
		for(Maison aAjouter : this.getGraph().getMaisons()) {
			maisonDeLaCarte.add(aAjouter);
		}
		
		maisonAlivrer.add(MaisonE);
		maisonAlivrer.add(MaisonJ);
		maisonAlivrer.add(MaisonD);
		maisonAlivrer.add(MaisonG);
		
		itineraire = new ArrayList<Maison>();
		itineraire = Itineraire.calcul(maisonAlivrer, source, graph);
		this.nbMaisons = itineraire.size();
		System.out.println(itineraire.size());
		
		
		
		itNonTriee = new ArrayList<Rue>();
		itNonTriee.add(r1);
		itNonTriee.add(r2);
		itNonTriee.add(r3);
		itNonTriee.add(r4);
		itNonTriee.add(r5);
		itNonTriee.add(r6);
		itNonTriee.add(r7);
		itNonTriee.add(r8);
		itNonTriee.add(r9);
		itNonTriee.add(r10);
		nbr = this.itNonTriee.size();
		it = new ArrayList<Rue>();
		System.out.println(itineraire);
		int i=0;
        int j=0;
        while(i<nbr) {
            while(j<itineraire.size()) {
                if(itNonTriee.get(i).getNom().equals(itineraire.get(j).getAdresse())) {
                    it.add(itNonTriee.get(i));
                    System.out.println(itNonTriee.get(i).getNom());
                }
                j++;
            }
            j=0;
            i++;
        }
        System.out.println("-----------------------");
        System.out.println(it);
		
		
		hmRue = new HashMap<String,Integer>();
		hmRue.put("Rue 1",1);
		hmRue.put("Rue 2",2);
		hmRue.put("Rue 3",4);
		hmRue.put("Rue 4",3);
		hmRue.put("Rue 5",2);
		hmRue.put("Rue 6",1);
		hmRue.put("Rue 7",2);
		hmRue.put("Rue 8",3);
		hmRue.put("Rue 9",1);
		hmRue.put("Rue 10",4);
	}

	/**
	 * Crée un nouvel itinéraire.
	 * @param it (liste)
	 */
	public Itineraire(ArrayList<Rue> it) {
		this.distance=0;
		this.duree=0;
		this.nbMaisons=0;
		this.it = it;
		this.hmRue = new HashMap<String,Integer>();
	}
	/**
	 * Renvoie le nom de la rue e l'indice i.
	 * @param i
	 * @return
	 */
	public String getIt(int i) {
		return this.itNonTriee.get(i).toString();
	}
	
	/** Retourne le nombre de rue de l'itineraire.
	 * @return int.**/
	public int getNbRue() {
		return nbr;
	}
	
	public double getDistance() {
		return this.distance;
	}
	
	public double getDuree(){
		return this.duree;
	}	
	
	public ArrayList<Rue> getIt() {
		return itNonTriee;
	}
	
	/**
	 * Retourne un carte.
	 */
	public Carte getGraph() {
		return graph;
	}

	public void setGraph(Carte graph) {
		this.graph = graph;
	}

	public ArrayList<Maison> getMaisonAlivrer() {
		return maisonAlivrer;
	}

	public void setMaisonAlivrer(ArrayList<Maison> maisonAlivrer) {
		this.maisonAlivrer = maisonAlivrer;
	}

	public ArrayList<Maison> getMaisonDeLaCarte() {
		return maisonDeLaCarte;
	}
	
	public int getNbMaisons() {
		return this.nbMaisons;
	}

	public void setMaisonDeLaCarte(ArrayList<Maison> maisonDeLaCarte) {
		this.maisonDeLaCarte = maisonDeLaCarte;
	}

	/** Ajoute une rue à l'itinéraire. 
	 ** @param ru **/
	public void ajouterRueItineraire(Rue ru) {
		itNonTriee.add(ru);
	}
	
	/**
	 * Retourne le nom d'une rue à l'indice passé en paramètre.
	 * @param i
	 */
	public String getRueIt(int i){
		return this.itNonTriee.get(i).getNom();
	}
	
	/** Affiche l'itineraire. **/
	public void afficheItineraire() {
		Iterator<Rue> i = itNonTriee.iterator();
		System.out.println("La distance e parcourir : "+this.distance+"Km.");
	    while (i.hasNext()) {
	    	System.out.println(i.next());
	    }
	}
	
	/**
	 * Retourne le code de direction de la rue à l'indice i.
	 * @param i
	 * @return code de la rue (int)
	 */
	public int getCodeRue(int i) {
		return hmRue.get("Rue "+i);
	}
	
	/**
	 * Tri dans l'ordre les maisons.
	 * @param tab
	 * @return
	 */
	private static Maison[] tri_selection(Maison[] tab)
    {
         for (int i = 0; i < tab.length - 1; i++)  
         {
              int index = i;  
              for (int j = i + 1; j < tab.length; j++)
              {
                   if ((int) tab[j].getDistance() < (int) tab[index].getDistance()){ 
                        index = j;
                   }
              }
              Maison min = tab[index];
              tab[index] = tab[i]; 
              tab[i] = min;
         }
         return tab;
    }
	
	/**
	 * La fonction qui renvoit une liste des maisons où doit passer le facteur.
	 * @param maisonsAlivrer
	 * @param source
	 * @param graph
	 */
	public static ArrayList<Maison> calcul(ArrayList<Maison> maisonsAlivrer, Maison source, Carte graph) {
		
		Dijkstra itineraire = new Dijkstra();		
		graph = Dijkstra.calculCheminPlusCourtDepuisSource(graph, source);
		Maison[] tab = new Maison[graph.getMaisons().size()];
		int i = 0;
		int compteur = 0;
		int autreCompteur = 0;
		boolean livred = false;
		List<Maison> trajet = new LinkedList<>();
		while(!maisonsAlivrer.isEmpty()) {
			//La je vais iterer jusqua avoir plus rien a livrer, a chaque fois je tri le graphe pour avoir les maisons les plus proches dans l ordre
			for(Maison toute : graph.getMaisons()) {
				tab[i] = toute;
				i++;
			}
			tab = tri_selection(tab);
			//Quand jai mes maisons les plus proches dans lordre je vais aller livrer la plus proche
			while(compteur < tab.length && !livred) {
				if(maisonsAlivrer.contains(tab[compteur])) {
					
					//La hop on livre et je met a jour les trucs, faudra rajouter les interactions avec le facteur
					livred = true;
					maisonsAlivrer.remove(tab[compteur]);
					trajet = tab[compteur].getCheminCourt();
					trajet.add(tab[compteur]);
					
					for (Maison maisonTrajet : trajet) {
						if(autreCompteur != 0 && maisonTrajet.getDistance() == 0) {
							
						}
						else {
	 						itineraire.getItineraire().add(maisonTrajet);
						}
					}
					//Je refait dijkstra depuis l ou on est pour voir qui est le plus proche et j'affiche les maisons restante a livrer et la nouvelle carte
					graph = Dijkstra.calculCheminPlusCourtDepuisSource(graph, tab[compteur]);
					autreCompteur++;
				}
				compteur++;
			}
			livred = false;
			compteur = 0;
			i = 0;
		}
		return itineraire.getItineraire();
	}
}
