import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.Stage;

/**
 * Classe principale qui permet de tester l'application.
 * @author Antho
 *
 */
public class Principale extends Application {
	/**
	 * Le login du facteur (de l'utilisateur).
	 */
	private static String login;
	/**
	 * Liste de facteurs.
	 */
	private static ArrayList<Facteur> facteurs;
	/**
	 * Le garage de la poste.
	 */
	private static Garage garage;
	/**
	 * Liste de courriers.
	 */
	private static ArrayList<Courrier> courriers;
	/**
	 * Liste de itinéraires.
	 */
	private static ArrayList<Itineraire> itineraires;
	/**
	 * Taille des listes.
	 */	
	private static int tailleListes = 30;
	static private FenetreConnexion 	 	 	fconnex   = new FenetreConnexion();
	static private FenetreCreerCompte 			fcrcmpt   = new FenetreCreerCompte();
	static private FenetreAccueil				faccueil  = new FenetreAccueil();
	static private FenetreGestionDonnees		fgestdata = new FenetreGestionDonnees();
	
	static private FenetreAjoutFacteur			fajfact   = new FenetreAjoutFacteur();
	static private FenetreModifierFacteur		fmdfact	  = new FenetreModifierFacteur();
	static private FenetreAjoutVehicule			fajvehi   = new FenetreAjoutVehicule();
	static private FenetreModifierVehicule		fmdvehi	  = new FenetreModifierVehicule();
	static private FenetreAjoutCourrier			fajcour   = new FenetreAjoutCourrier();
	static private FenetreModifierCourrier		fmdcour	  = new FenetreModifierCourrier();
	static private FenetreAjoutItineraire		fajitin   = new FenetreAjoutItineraire();
	static private FenetreModifierItineraire	fmditin	  = new FenetreModifierItineraire();
	static private FenetreInformation 			finfo	  = new FenetreInformation();
	static private FenetreModifierMdp			fmodifmdp = new FenetreModifierMdp();	
	static private FenetreAjoutInfo				fajoutinfo= new FenetreAjoutInfo();
	
	static private FenetreItineraire			fit 	  = new FenetreItineraire();
	static private FenetreItineraireDetaille	fitdetail = new FenetreItineraireDetaille();

	/**
	 * Représente l'index du véhicule sélectionné dans la liste s'il y en a
	 */
	static private int index; // représente l'index du véhicule sélectionné dans la liste s'il y en a
	
	/**
	 * Démarre l'application, affiche la première fenêtre (de connexion).
	 */
	public void start(Stage primaryStage) throws Exception {
		login = null;
		initListes();
		primaryStage = fconnex;
		primaryStage.show();
	}
	
	/////////////////////////////////////////////////////
	// Gestion des fenêtres
	/////////////////////////////////////////////////////
	/**
	 * Méthode qui permet d'ouvrir la fenêtre de connexion.
	 */
	
	static public void ouvrirConnexion(){
		faccueil.close();
		initInfo();
		initListes();
		fconnex.init();
		fconnex.show();
	}
	/**
	 * Méthode qui permet d'ouvrir la fenêtre de créer un nouveau compte.
	 */
	static public void ouvrirCreerCompte(){
		fcrcmpt.init();
		fcrcmpt.show();
	}
	/**
	 * Méthode qui permet d'ouvrir la fenêtre d'accueil.
	 */
	static public void ouvrirAccueil(Stage fenetre) {
		fenetre.close();
		initInfo();
		initListes();
		faccueil.init(login);
		faccueil.show();
	}
	/**
	 * Méthode qui permet d'ouvrir la fenêtre de gestion de données.
	 */
	static public void ouvrirGestionDonnees(){
		faccueil.close();
		fgestdata.init(Principale.facteurs, Principale.garage, Principale.courriers, Principale.itineraires);
		fgestdata.show();
	}
	/**
	 * Méthode qui permet d'ouvrir la fenêtre d'informations du facteur..
	 */
	static public void ouvrirInformation(){
		faccueil.close();
		finfo.init();
		finfo.show();
	}
	/**
	 * Méthode qui permet d'ouvrir la fenêtre pour ajouter des infos sur le facteur.
	 */
	public static void ajouterInfo(String nom,String prenom,String sexe,String age,boolean permisA,boolean permisB) {
		sauverInfo(nom,prenom,sexe,age,permisA,permisB);
		finfo.actualiserInfo(nom,prenom,sexe,age,permisA,permisB);
		finfo.init();
		finfo.show();
	}
	/**
	 * Méthode qui permet de fermer la fenêtre d'informations.
	 */
	static public void fermerInformation() {
		finfo.close();
	}
	/**
	 * Méthode qui permet d'ouvrir la fenêtre d'ajout d'un facteur.
	 */
	static public void ouvrirAjoutFacteur(){
		fajfact.init();
		fajfact.show();
	}
	/**
	 * Méthode qui permet d'ouvrir la fenêtre de modification d'un facteur.
	 */
	static public void ouvrirModifierFacteur(Facteur facteur, int i){
		//fmdfact.init(facteur);
		index = i;
		fmdfact.show();
		
	}
	/**
	 * Méthode qui permet d'ouvrir la fenêtre d'ajout d'un véhicule.
	 */
	static public void ouvrirAjoutVehicule(){
		fajvehi.init();
		fajvehi.show();
	}
	/**
	 * Méthode qui permet d'ouvrir la fenêtre de modification d'un véhicule.
	 */
	static public void ouvrirModifierVehicule(VehiculeMotorise vehicule, int i){
		fmdvehi.init(vehicule);
		index = i;
		fmdvehi.show();
		
	}
	/**
	 * Méthode qui permet d'ouvrir la fenêtre d'ajout d'un courrier.
	 */
	static public void ouvrirAjoutCourrier(){
		fajcour.init();
		fajcour.show();
	}
	/**
	 * Méthode qui permet d'ouvrir la fenêtre de modification d'un courrier.
	 */
	static public void ouvrirModifierCourrier(Courrier courrier, int i){
		fmdcour.init(courrier);
		index = i;
		fmdcour.show();
		
	}
	/**
	 * Méthode qui permet d'ouvrir la fenêtre d'ajout d'un itinéraire.
	 */
	static public void ouvrirAjoutItineraire(){
		fajitin.init();
		fajitin.show();
	}
	/**
	 * Méthode qui permet d'ouvrir la fenêtre de modification d'un itinéraire.
	 */
	static public void ouvrirModifierItineraire(Itineraire itineraire, int i){
		fmditin.init(itineraire);
		index = i;
		fmditin.show();
		
	}
	/**
	 * Méthode qui permet d'ouvrir la fenêtre de l'itinéraire par étape.
	 */
	static public void ouvrirItineraire() {
		faccueil.close();
		fit.show();
	}
	/**
	 * Méthode qui permet d'ouvrir la fenêtre de l'itinéraire détaillé.
	 */
	static public void ouvrirItineraireDetaille() {
		faccueil.close();
		fitdetail.show();
	}
	/**
	 * Méthode qui permet d'ouvrir la fenêtre d'ajout d'informations du facteur.
	 */
	static public void ouvrirAjoutInfo() {
		fajoutinfo.init();
		fajoutinfo.show();
	}
	/**
	 * Méthode qui permet d'ouvrir la fenêtre de modification des infos d'un facteur.
	 */
	static public void ouvrirModifierInfo(String nom, String prenom, String sexe, String age, Boolean a, Boolean b) {
		fajoutinfo.init(nom, prenom, sexe, age, a, b);
		fajoutinfo.show();
	}
	/**
	 * Méthode qui permet d'ouvrir la fenêtre de modification d'un mot de passe.
	 */
	static public void ouvrirModifierMdp() {
		fmodifmdp.init();
		fmodifmdp.show();
	}
	
	/////////////////////////////////////////////////////
	// Gestion de la liste des données
	/////////////////////////////////////////////////////
	/**
	 * Méthode qui affecte un login.
	 */
	public static void setLogin(String login) {
		Principale.login = login;
	}
	/**
	 * Méthode qui initialise les infos du facteur.
	 */
	public static void initInfo() {
		if (login != null) {
			try (Scanner scanner = new Scanner(new FileReader("fichiers/" + login + "_infos.dat"))) {
		    	scanner.useDelimiter(";");
				String nom = scanner.next();
				String prenom = scanner.next();
				String sexe = scanner.next();
				String age = scanner.next();
				boolean permisA = scanner.nextBoolean();
				boolean permisB = scanner.nextBoolean();
				finfo.actualiserInfo(nom,prenom,sexe,age,permisA,permisB);
		    }
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else {
			finfo.actualiserInfo("","","","",false,false);
		}
	}
	/**
	 * Méthode qui permet de sauvegarder les infos du facteur.
	 */
	public static void sauverInfo(String nom,String prenom,String sexe,String age,boolean permisA,boolean permisB) {
		try {
			FileWriter ecriture = new FileWriter("fichiers/" + login + "_infos.dat");
			ecriture.write(nom + ";" + prenom + ";" + sexe + ";" + age + ";" + permisA + ";" + permisB + ";\n");
			ecriture.close();
			
			System.out.println("Infos sauvegardées");
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	/**
	 * Méthode qui initialise les listes.
	 */
	public static void initListes() {
		garage = new Garage(tailleListes);
		facteurs = new ArrayList<Facteur>(tailleListes);
		courriers = new ArrayList<Courrier>(tailleListes);
		itineraires = new ArrayList<Itineraire>(tailleListes);
		
		if (login != null) {
			initFacteurs();
			initGarage();
			initCourriers();
			initItineraires();
		}
	}
	/**
	 * Méthode qui permet de sauvegarder les listes.
	 */
	public static void sauverListes() {
		sauverFacteurs();
		sauverGarage();
		sauverCourriers();
		sauverItineraires();
	}
	
	
	
	// Facteurs
	/**
	 * Méthode qui permet d'initialiser les facteurs.
	 */
	public static void initFacteurs() {
		try (Scanner scanner = new Scanner(new FileReader("fichiers/" + login + "_facteurs.dat"))) {
			scanner.useDelimiter(";");
			int i = 0;
			
	        while (scanner.hasNextLine()) {
	        	ArrayList<Transport> transports = new ArrayList<Transport>();
				ArrayList<Courrier> courriers = new ArrayList<Courrier>();
				ArrayList<Itineraire> itineraires = new ArrayList<Itineraire>();
				
	        	int id = scanner.nextInt();
	        	String nom = scanner.next();
	        	String prenom = scanner.next();
	        	char sexe = scanner.next().charAt(0);
	     
	        	Calendar dateNaissance = Calendar.getInstance();
	        	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	        	try {
					dateNaissance.setTime(sdf.parse(scanner.next()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
	        	
	        	boolean permisA = scanner.nextBoolean();
	        	boolean permisB = scanner.nextBoolean();
	        	
	        	Scanner scanListe = new Scanner(scanner.next());
	        	scanListe.useDelimiter(",");
	        	while (scanListe.next().charAt(0) != ';') {
	        		transports.add(creerVehicule(scanner));
		        	scanListe.next();
		        }
	        	scanListe.close();
	        	
	        	scanListe = new Scanner(scanner.next());
	        	while (scanListe.next().charAt(0) != ';') {
	        		courriers.add(creerCourrier(scanner));
		        	scanListe.next();
		        }
	        	scanListe.close();
	        	
	        	scanListe = new Scanner(scanner.next());
	        	while (scanListe.next().charAt(0) != ';') {
	        		itineraires.add(creerItineraire(scanner));
	        		scanListe.next();
		        }
	        	scanListe.close();

				Principale.ajouterFacteur(new Facteur(id, nom, prenom, sexe, dateNaissance, permisA, permisB, transports, courriers, itineraires));

				i++;
	            scanner.nextLine();
	        }
	        Facteur.setNbFacteurs(i);
	    }
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode qui permet d'ajouter un facteur.
	 */
	public static void ajouterFacteur(Facteur facteur){
		// mise à jour des données : ajout
		facteurs.add(facteur);
		// actualisation de la liste dans la fenetre "FenetreGestionDonnees"
		fgestdata.actualiserListes(FXCollections.observableArrayList(facteurs), FXCollections.observableArrayList(garage.getVehicules()),
				FXCollections.observableArrayList(courriers), FXCollections.observableArrayList(itineraires));
	}
	/**
	 * Méthode qui permet de modifier un facteur.
	 */
	public static void modifierFacteur(Facteur facteur){
		// mise à jour des données : modification
		facteurs.set(index, facteur);
		// actualisation de la liste dans la fenetre "FenetreGestionDonnees"
		fgestdata.actualiserListes(FXCollections.observableArrayList(facteurs), FXCollections.observableArrayList(garage.getVehicules()),
				FXCollections.observableArrayList(courriers), FXCollections.observableArrayList(itineraires));
	}
	/**
	 * Méthode qui permet de supprimer un facteur.
	 */
	public static void supprimerFacteur(Facteur facteur) {
		// mise à jour des données : suppression
		facteurs.remove(facteur);
		// actualisation de la liste dans la fenetre "FenetreGestionDonnees"
		fgestdata.actualiserListes(FXCollections.observableArrayList(facteurs), FXCollections.observableArrayList(garage.getVehicules()),
				FXCollections.observableArrayList(courriers), FXCollections.observableArrayList(itineraires));
	}
	/**
	 * Méthode qui permet de sauvegarder les facteurs.
	 */
	public static void sauverFacteurs() {
		try {
			FileWriter ecriture = new FileWriter("fichiers/" + login + "_facteurs.dat");
			for (Facteur f : facteurs) {
				SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
				String dateNaissance = formatDate.format(f.getDateNaissance());
				
				ecriture.write(f.getId() + ";" + f.getNom() + ";" + f.getPrenom() + ";" + f.getSexe() + ";" + dateNaissance
								+ ";" + f.aPermisA() + ";" + f.aPermisB() + ";" + f.getMesTransports() + ";" + f.getMesCourriers()
								+ ";" + f.getMesItineraires() + ";\n");
			}
			ecriture.close();
			
			System.out.println("Liste des facteurs sauvegardée");
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
	}
		
		
	
	// Garage
	/**
	 * Méthode qui permet d'initialiser un garage.
	 */
	public static void initGarage() {
		try (Scanner scanner = new Scanner(new FileReader("fichiers/" + login + "_garage.dat"))) {
	        while (scanner.hasNextLine()) {
	        	Principale.ajouterVehicule(creerVehicule(scanner));
	            scanner.nextLine();
	        }
	    }
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Méthode qui permet de créer un véhicule motorisé.
	 */
	public static VehiculeMotorise creerVehicule(Scanner scanner) {
		VehiculeMotorise vehi = null;
		scanner.useDelimiter(";");
		
		String type = scanner.next();
    	double vMax = Double.parseDouble(scanner.next());
    	double capac = Double.parseDouble(scanner.next());
    	boolean dPieton = scanner.nextBoolean();
    	String matric = scanner.next();
		double carbu = Double.parseDouble(scanner.next());
		double consoCarbu = Double.parseDouble(scanner.next());
		boolean bPA = scanner.nextBoolean();
		boolean bPB = scanner.nextBoolean();
		
		switch(type) {
			case "Camionnette" :
				vehi = new Camionnette(vMax, capac, dPieton, matric, carbu, consoCarbu, bPA, bPB);
				break;
			case "Quad" :
				vehi = new Quad(vMax, capac, dPieton, matric, carbu, consoCarbu, bPA, bPB);
				break;
			case "Staby" :
				vehi = new Staby(vMax, capac, dPieton, matric, carbu, consoCarbu, bPA, bPB);
				break;
			default :
				break;
		}
		return vehi;
	}
	/**
	 * Méthode qui permet d'ajouter un véhicule motorisé au garage.
	 */
	public static void ajouterVehicule(VehiculeMotorise vehicule){
		// mise à jour des données : ajout
		garage.ajouterVehicule(vehicule);
		// actualisation de la liste dans la fenetre "FenetreGestionDonnees"
		fgestdata.actualiserListes(FXCollections.observableArrayList(facteurs), FXCollections.observableArrayList(garage.getVehicules()),
				FXCollections.observableArrayList(courriers), FXCollections.observableArrayList(itineraires));
	}
	/**
	 * Méthode qui permet de modifier un véhicule motorisé du garage.
	 */
	public static void modifierVehicule(VehiculeMotorise vehicule){
		// mise à jour des données : modification
		garage.modifierVehicule(vehicule, index);
		// actualisation de la liste dans la fenetre "FenetreGestionDonnees"
		fgestdata.actualiserListes(FXCollections.observableArrayList(facteurs), FXCollections.observableArrayList(garage.getVehicules()),
				FXCollections.observableArrayList(courriers), FXCollections.observableArrayList(itineraires));
	}
	/**
	 * Méthode qui permet de supprimer un véhicule motorisé du garage.
	 */
	public static void supprimerVehicule(VehiculeMotorise vehicule) {
		// mise à jour des données : suppression
		garage.enleverVehicule(vehicule);
		// actualisation de la liste dans la fenetre "FenetreGestionDonnees"
		fgestdata.actualiserListes(FXCollections.observableArrayList(facteurs), FXCollections.observableArrayList(garage.getVehicules()),
				FXCollections.observableArrayList(courriers), FXCollections.observableArrayList(itineraires));
	}
	/**
	 * Méthode qui permet de sauvegarder un garage.
	 */
	public static void sauverGarage() {
		try {
			FileWriter ecriture = new FileWriter("fichiers/" + login + "_garage.dat");
			for (VehiculeMotorise v : garage.getVehicules()) {
				ecriture.write(v.getType() + ";" + v.getVitesseMax() + ";" + v.getCapacite() + ";" + v.isDroitPieton() + ";" + v.getMatricule()
								+ ";" + v.getCarburant() + ";" + v.getConsoCarburant() + ";" + v.isBesoinPermisA() + ";" + v.isBesoinPermisB() + ";\n");
			}
			ecriture.close();
			
			System.out.println("Garage sauvegardé");
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	

		
	// Courriers
	/**
	 * Méthode qui permet d'initialiser des courriers dans une liste.
	 */
	public static void initCourriers() {
		try (Scanner scanner = new Scanner(new FileReader("fichiers/" + login + "_courriers.dat"))) {
	        while (scanner.hasNextLine()) {
				Principale.ajouterCourrier(creerCourrier(scanner));
	            scanner.nextLine();
	        }
	    }
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Méthode qui permet de créer des courriers.
	 */
	public static Courrier creerCourrier(Scanner scanner) {
		scanner.useDelimiter(";");
		float volume = Float.parseFloat(scanner.next());
    	boolean prioritaire = scanner.nextBoolean();
    	String type = scanner.next();
    	Destinataire dest = new Destinataire(scanner.next(), scanner.next(), scanner.next());
		
		return new Courrier(volume, prioritaire, type, dest);
	}
	/**
	 * Méthode qui permet d'ajouter des courriers dans une liste.
	 */
	public static void ajouterCourrier(Courrier courrier){
		// mise à jour des données : ajout
		courriers.add(courrier);
		// actualisation de la liste dans la fenetre "FenetreGestionDonnees"
		fgestdata.actualiserListes(FXCollections.observableArrayList(facteurs), FXCollections.observableArrayList(garage.getVehicules()),
				FXCollections.observableArrayList(courriers), FXCollections.observableArrayList(itineraires));
	}
	/**
	 * Méthode qui permet de modifier des courriers dans une liste.
	 */
	public static void modifierCourrier(Courrier courrier){
		// mise à jour des données : modification
		courriers.set(index, courrier);
		// actualisation de la liste dans la fenetre "FenetreGestionDonnees"
		fgestdata.actualiserListes(FXCollections.observableArrayList(facteurs), FXCollections.observableArrayList(garage.getVehicules()),
				FXCollections.observableArrayList(courriers), FXCollections.observableArrayList(itineraires));
	}
	/**
	 * Méthode qui permet de supprimer des courriers dans une liste.
	 */
	public static void supprimerCourrier(Courrier courrier) {
		// mise à jour des données : suppression
		courriers.remove(courrier);
		// actualisation de la liste dans la fenetre "FenetreGestionDonnees"
		fgestdata.actualiserListes(FXCollections.observableArrayList(facteurs), FXCollections.observableArrayList(garage.getVehicules()),
				FXCollections.observableArrayList(courriers), FXCollections.observableArrayList(itineraires));
	}
	/**
	 * Méthode qui permet de sauvegarder des courriers.
	 */
	public static void sauverCourriers() {
		try {
			FileWriter ecriture = new FileWriter("fichiers/" + login + "_courriers.dat");
			for (Courrier c : courriers) {
				String prenom = c.getDestinataire().getPrenom();
				String nom = c.getDestinataire().getNom();
				String nomRue = c.getDestinataire().getNomRue();
				ecriture.write(c.getVolume() + ";" + c.isPrioritaire() + ";" + c.getType() + ";"
						+ prenom + ";" + nom + ";" + nomRue + ";\n");
			}
			ecriture.close();
			
			System.out.println("Liste des courriers sauvegardée");
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	
	
	// Itineraires
	/**
	 * Méthode qui permet d'initialiser des itinéraires dans une liste.
	 */
	public static void initItineraires() {
		try (Scanner scanner = new Scanner(new FileReader("fichiers/" + login + "_itineraires.dat"))) {
	        while (scanner.hasNextLine()) {
				Principale.ajouterItineraire(creerItineraire(scanner));
	            scanner.nextLine();
	        }
	    }
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Méthode qui permet de créer un itinéraire.
	 */
	public static Itineraire creerItineraire(Scanner scanner) {
		scanner.useDelimiter(";");
		ArrayList<Rue> itListe = new ArrayList<Rue>();
		Scanner scanListe = new Scanner(scanner.next());
		scanListe.useDelimiter(",");
    	while (scanListe.hasNext()) {
    		Scanner scanRue = new Scanner(scanListe.next());
    		scanRue.useDelimiter(":");
    		
    		String nom = scanRue.next();
        	boolean sens = scanRue.nextBoolean();
        	boolean type = scanRue.nextBoolean();
        	int limit = scanRue.nextInt();
        	float longue = Float.parseFloat(scanRue.next());
        	int nbNum = scanRue.nextInt();
        	double tps = Double.parseDouble(scanRue.next());
        	
    		itListe.add(new Rue(nom, sens, type, limit, longue, nbNum, tps));
        }
		
		return new Itineraire(itListe);
	}
	/**
	 * Méthode qui permet d'ajouter des itinéraires dans une liste.
	 */
	public static void ajouterItineraire(Itineraire itineraire){
		// mise à jour des données : ajout
		itineraires.add(itineraire);
		// actualisation de la liste dans la fenetre "FenetreGestionDonnees"
		fgestdata.actualiserListes(FXCollections.observableArrayList(facteurs), FXCollections.observableArrayList(garage.getVehicules()),
				FXCollections.observableArrayList(courriers), FXCollections.observableArrayList(itineraires));
	}
	/**
	 * Méthode qui permet de modifier un itinéraire dans une liste.
	 */
	public static void modifierItineraire(Itineraire itineraire){
		// mise à jour des données : modification
		itineraires.set(index, itineraire);
		// actualisation de la liste dans la fenetre "FenetreGestionDonnees"
		fgestdata.actualiserListes(FXCollections.observableArrayList(facteurs), FXCollections.observableArrayList(garage.getVehicules()),
				FXCollections.observableArrayList(courriers), FXCollections.observableArrayList(itineraires));
	}
	/**
	 * Méthode qui permet de supprimer un itinéraire dans une liste.
	 */
	public static void supprimerItineraire(Itineraire itineraire) {
		// mise à jour des données : suppression
		itineraires.remove(itineraire);
		// actualisation de la liste dans la fenetre "FenetreGestionDonnees"
		fgestdata.actualiserListes(FXCollections.observableArrayList(facteurs), FXCollections.observableArrayList(garage.getVehicules()),
				FXCollections.observableArrayList(courriers), FXCollections.observableArrayList(itineraires));
	}
	/**
	 * Méthode qui permet de sauvegarder des itinéraires.
	 */
	public static void sauverItineraires() {
		try {
			FileWriter ecriture = new FileWriter("fichiers/" + login + "_itineraires.dat");
			for (Itineraire it : itineraires) {
				String itListe = "";
				
				for (Rue r : it.getIt()) {
					itListe += r.getNom() + ":" + r.isSensUnique() + ":" + r.isPietonne() + ":"
							+ r.getLimitationVitesse() + ":" + r.getLongueur() + ":"
							+ r.getNbNum() + ":" + r.getTemps() + ":,";
				}
					
				ecriture.write(itListe + ";\n");
			}
			ecriture.close();
			
			System.out.println("Liste des itinéraires sauvegardée");
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * Retourne le login du facteur.
	 */
	public static String getLogin(){
		return login;
	}	
	/**
	 * Lance l'application.
	 */
	public static void main(String[] args) { 
		Application.launch(args); 
	}
}
