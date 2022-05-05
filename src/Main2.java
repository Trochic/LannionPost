import java.util.ArrayList;

public class Main2 {
	
	public static Maison[] tri_selection(Maison[] tab)
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

	@SuppressWarnings("unused")
	public static void main (String[] args) {
		
		//JE PEUPLE LA CARTE
		
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
		
		Rue r1 = new Rue("A", 10);
		Rue r2 = new Rue("B ", 15);
		Rue r3 = new Rue("C", 12);
		Rue r4 = new Rue("D", 15);
		Rue r5 = new Rue("E", 10);
		Rue r6 = new Rue("F", 2);
		Rue r7 = new Rue("G", 1);
		Rue r8 = new Rue("H", 5);
		Rue r9 = new Rue("I", 5);
		Rue r10 = new Rue("J", 5);
		Rue r11 = new Rue("K", 5);
		Rue r12 = new Rue("L", 5);
		Rue r13 = new Rue("M", 5);
		Rue r14 = new Rue("N", 5);
		Rue r15 = new Rue("O", 5);
		
		
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

		MaisonD.ajouterDestination(MaisonE, r6.getLongueur(),2);
		MaisonD.ajouterDestination(MaisonF, r7.getLongueur(),1);
		MaisonB.ajouterDestination(MaisonA, r3.getLongueur(),4);

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

		Carte graph = new Carte();

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
		
		
		//ARRAYLIST DES MAISONS A LIVRER
		ArrayList<Maison> maisons = new ArrayList<>(10);
		maisons.add(MaisonE);
		maisons.add(MaisonG);
		maisons.add(MaisonD);
		maisons.add(MaisonH);
		
		//CALCUL DE LITINERAIRE EN GROS
		
		calcul(maisons, MaisonA, graph);
		System.out.println("Livred");
	}
	
	public static void calcul(ArrayList<Maison> maisons, Maison source, Carte graph) {
		graph = Dijkstra.calculCheminPlusCourtDepuisSource(graph, source);
		Maison[] tab = new Maison[10];
		int i = 0;
		int compteur = 0;
		boolean livred = false;
		while(!maisons.isEmpty()) {
			//La je vais iterer jusqua avoir plus rien a livrer, a chaque fois je tri le graphe pour avoir les maisons les plus proches dans l ordre
			for(Maison toute : graph.getMaisons()) {
				tab[i] = toute;
				i++;
			}
			tab = tri_selection(tab);
			
			//Quand jai mes maisons les plus proches dans lordre je vais aller livrer la plus proche
			while(compteur < tab.length && !livred) {
				if(maisons.contains(tab[compteur])) {
					
					//La hop on livre et je met a jour les trucs, faudra rajouter les interactions avec le facteur
					livred = true;
					maisons.remove(tab[compteur]);
					
					//Je refait dijkstra depuis l� ou on est pour voir qui est le plus proche et j'affiche les maisons restante a livrer et la nouvelle carte
					graph = Dijkstra.calculCheminPlusCourtDepuisSource(graph, tab[compteur]);
					System.out.println(graph);
					System.out.println(maisons);
					System.out.println("------");
				}
				compteur++;
			}
			livred = false;
			compteur = 0;
			i = 0;
		}
		
	}

}
