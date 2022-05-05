import java.util.ArrayList;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Fenêtre de la gestion des données.
 */
public class FenetreGestionDonnees extends Stage {
	private double	tailleX		  = 450;
	private double	tailleY		  = 550;
	private boolean estSauvegarde = true; // permet d'avertir l'utilisateur s'il quitte sans sauvegarder
	// les composants de la fenêtre
	private AnchorPane  				racine			= new AnchorPane();
	private TabPane 					tabPane 		= new TabPane();
	private Tab 						tabFacteurs		= new Tab("Facteurs");
	private Tab							tabGarage 		= new Tab("Garage");
	private Tab 						tabCourriers	= new Tab("Courriers");
	private Tab							tabItineraires 	= new Tab("Itinéraires");
	private ListView<Facteur> 			facteurs		= new ListView<Facteur>();
	private ListView<VehiculeMotorise> 	garage			= new ListView<VehiculeMotorise>();
	private ListView<Courrier> 			courriers		= new ListView<Courrier>();
	private ListView<Itineraire> 		itineraires		= new ListView<Itineraire>();
	private Label						details 		= new Label("");
	private Button 						bnAjouter 		= new Button("Ajouter...");
	private Button 						bnModifier 		= new Button("Modifier...");
	private Button 						bnSupprimer 	= new Button("Supprimer");
	private Button 						bnSauver 		= new Button("Sauvegarder");
	private Button 						bnFermer 		= new Button("Fermer");
	private MenuItem 					optionAjouter	= new MenuItem("Ajouter...");
	private MenuItem 					optionModifier 	= new MenuItem("Modifier...");
	private MenuItem 					optionSupprimer = new MenuItem("Supprimer");
	private ContextMenu 				menu 			= new ContextMenu(optionAjouter, new SeparatorMenuItem(), optionModifier, new SeparatorMenuItem(), optionSupprimer);
	
	// constructeur : initialisation de la fenêtre
	/**
	 * Crée la fenêtre de la gestion des données.
	 */
	public FenetreGestionDonnees(){
		this.setTitle("Gestion des données");
		this.setResizable(true);
		this.setMinWidth(tailleX);
		this.setMinHeight(tailleY);
		this.setScene(new Scene(creerContenu()));
	}
	
	/**
	 * Initialise la fénetre.
	 * @param facteurs
	 * @param garage
	 * @param courriers
	 * @param itineraires
	 */
	
	public void init(ArrayList<Facteur> facteurs, Garage garage, ArrayList<Courrier> courriers, ArrayList<Itineraire> itineraires){
		estSauvegarde = true;
		this.facteurs.getItems().setAll(facteurs);
		this.garage.getItems().setAll(garage.getVehicules());
		this.courriers.getItems().setAll(courriers);
		this.itineraires.getItems().setAll(itineraires);
	}
	
	// création du Scene graph
	/**
	 * Crée le contenu de la fenêtre.
	 * @return
	 */
	private Parent creerContenu() {
		details.setPrefWidth(120);
		bnAjouter.setPrefWidth(120);
		bnModifier.setPrefWidth(120);
		bnSupprimer.setPrefWidth(120);
		bnSauver.setPrefWidth(120);
		bnFermer.setPrefWidth(120);
		
		facteurs.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		facteurs.setContextMenu(menu);
		garage.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		garage.setContextMenu(menu);
		courriers.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		courriers.setContextMenu(menu);
		itineraires.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		itineraires.setContextMenu(menu);
		
		AnchorPane.setTopAnchor(details, 40.0);
		AnchorPane.setRightAnchor(details, 10.0);
		AnchorPane.setBottomAnchor(bnAjouter, 170.0);
		AnchorPane.setRightAnchor(bnAjouter, 10.0);
		AnchorPane.setBottomAnchor(bnModifier, 130.0);
		AnchorPane.setRightAnchor(bnModifier, 10.0);
		AnchorPane.setBottomAnchor(bnSupprimer, 90.0);
		AnchorPane.setRightAnchor(bnSupprimer, 10.0);
		AnchorPane.setBottomAnchor(bnSauver, 50.0);
		AnchorPane.setRightAnchor(bnSauver, 10.0);
		AnchorPane.setBottomAnchor(bnFermer, 10.0);
		AnchorPane.setRightAnchor(bnFermer, 10.0);
		
		AnchorPane.setTopAnchor(facteurs, 40.0);
		AnchorPane.setLeftAnchor(facteurs, 10.0);
		AnchorPane.setRightAnchor(facteurs, 150.0);
		AnchorPane.setBottomAnchor(facteurs, 10.0); 
		AnchorPane.setTopAnchor(garage, 40.0);
		AnchorPane.setLeftAnchor(garage, 10.0);
		AnchorPane.setRightAnchor(garage, 150.0);
		AnchorPane.setBottomAnchor(garage, 10.0);
		AnchorPane.setTopAnchor(courriers, 40.0);
		AnchorPane.setLeftAnchor(courriers, 10.0);
		AnchorPane.setRightAnchor(courriers, 150.0);
		AnchorPane.setBottomAnchor(courriers, 10.0);
		AnchorPane.setTopAnchor(itineraires, 40.0);
		AnchorPane.setLeftAnchor(itineraires, 10.0);
		AnchorPane.setRightAnchor(itineraires, 150.0);
		AnchorPane.setBottomAnchor(itineraires, 10.0);

        tabPane.getTabs().addAll(tabFacteurs, tabGarage, tabCourriers, tabItineraires);
        tabPane.setMinWidth(tailleX-16);
        tabPane.setMaxWidth(tailleX-16);
        tabPane.setOpaqueInsets(new Insets(0));
        tabPane.setTabMinWidth((tailleX-16) / tabPane.getTabs().size() - 17);
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        
        racine.getChildren().addAll(tabPane, details, bnAjouter, bnModifier, bnSupprimer, bnSauver, bnFermer, facteurs);
        
		activeBoutons(false);
		
		// détection des événements
		tabFacteurs.setOnSelectionChanged( e -> gererTabs(e));
		tabGarage.setOnSelectionChanged( e -> gererTabs(e));
		tabCourriers.setOnSelectionChanged( e -> gererTabs(e));
		tabItineraires.setOnSelectionChanged( e -> gererTabs(e));
		
		bnAjouter.setOnAction( e -> gererBoutons(e));
		bnModifier.setOnAction( e -> gererBoutons(e));
		bnSupprimer.setOnAction( e -> gererBoutons(e));
		bnSauver.setOnAction( e -> gererBoutons(e));
		optionAjouter.setOnAction( e -> gererBoutons(e));
		optionModifier.setOnAction( e -> gererBoutons(e));
		optionSupprimer.setOnAction( e -> gererBoutons(e));
		bnFermer.setOnAction( e -> gererBoutons(e));
		
		facteurs.setOnMouseClicked( e -> gererClic(e));
		garage.setOnMouseClicked( e -> gererClic(e));
		courriers.setOnMouseClicked( e -> gererClic(e));
		itineraires.setOnMouseClicked( e -> gererClic(e));
		
		this.widthProperty().addListener((obs, oldVal, newVal) -> {
			tailleX = (double) newVal;
			tabPane.setMinWidth(tailleX-16);
	        tabPane.setMaxWidth(tailleX-16);
	        tabPane.setTabMinWidth((tailleX-16) / tabPane.getTabs().size() - 18);
		});

		return racine;
	}	

	/**
	 * Actualise les listes.
	 * @param facteurs
	 * @param garage
	 * @param courriers
	 * @param itineraires
	 */
	public void actualiserListes(ObservableList<Facteur> facteurs, ObservableList<VehiculeMotorise> garage, ObservableList<Courrier> courriers, ObservableList<Itineraire> itineraires) {
		this.facteurs.setItems(facteurs);
		this.garage.setItems(garage);
		this.courriers.setItems(courriers);
		this.itineraires.setItems(itineraires);
		
		activeBoutons(false);
		estSauvegarde = false;
	}
	
	// traitement des événements
	/**
	 * Activations ou non des boutons.
	 * @param b
	 */
	private void activeBoutons(boolean b){
		bnModifier.setDisable(!b);
		bnSupprimer.setDisable(!b);
		optionModifier.setDisable(!b);
		optionSupprimer.setDisable(!b);
	}
	/**
	 * Gestion des listes.
	 * @param e
	 */
	private void gererTabs(Event e) {
		activeBoutons(false);
		details.setText("");
		if (tabPane.getSelectionModel().getSelectedItem()==tabFacteurs) {
			racine.getChildren().removeAll(facteurs, garage, courriers, itineraires);
			racine.getChildren().add(facteurs);
		}
		if (tabPane.getSelectionModel().getSelectedItem()==tabGarage) {
			racine.getChildren().removeAll(facteurs, garage, courriers, itineraires);
			racine.getChildren().add(garage);
		}
		if (tabPane.getSelectionModel().getSelectedItem()==tabCourriers) {
			racine.getChildren().removeAll(facteurs, garage, courriers, itineraires);
			racine.getChildren().add(courriers);
		}
		if (tabPane.getSelectionModel().getSelectedItem()==tabItineraires) {
			racine.getChildren().removeAll(facteurs, garage, courriers, itineraires);
			racine.getChildren().add(itineraires);
		}
	}
	
	/**
	 * Gestion des boutons.
	 * @param e
	 */
	private void gererBoutons(Event e) {
		if (e.getSource()==bnAjouter || e.getSource()==optionAjouter) {
			if (tabPane.getSelectionModel().getSelectedItem()==tabFacteurs) {
				Principale.ouvrirAjoutFacteur();
			}
			if (tabPane.getSelectionModel().getSelectedItem()==tabGarage) {
				Principale.ouvrirAjoutVehicule();
			}
			if (tabPane.getSelectionModel().getSelectedItem()==tabCourriers) {
				Principale.ouvrirAjoutCourrier();
			}
			if (tabPane.getSelectionModel().getSelectedItem()==tabItineraires) {
				Principale.ouvrirAjoutItineraire();
			}			
		}
		
		if (e.getSource()==bnModifier || e.getSource()==optionModifier)  {
			if (tabPane.getSelectionModel().getSelectedItem()==tabFacteurs) {
				Principale.ouvrirModifierFacteur(facteurs.getSelectionModel().getSelectedItem(), facteurs.getSelectionModel().getSelectedIndex());
			}
			if (tabPane.getSelectionModel().getSelectedItem()==tabGarage) {
				Principale.ouvrirModifierVehicule(garage.getSelectionModel().getSelectedItem(), garage.getSelectionModel().getSelectedIndex());
			}
			if (tabPane.getSelectionModel().getSelectedItem()==tabCourriers) {
				Principale.ouvrirModifierCourrier(courriers.getSelectionModel().getSelectedItem(), courriers.getSelectionModel().getSelectedIndex());
			}
			if (tabPane.getSelectionModel().getSelectedItem()==tabItineraires) {
				Principale.ouvrirModifierItineraire(itineraires.getSelectionModel().getSelectedItem(), itineraires.getSelectionModel().getSelectedIndex());
			}
			
		}
		
		if (e.getSource()==bnSupprimer || e.getSource()==optionSupprimer) {
			if (tabPane.getSelectionModel().getSelectedItem()==tabFacteurs) {
				confirmerSupprFacteur(facteurs.getSelectionModel().getSelectedItem());
			}
			if (tabPane.getSelectionModel().getSelectedItem()==tabGarage) {
				confirmerSupprVehicule(garage.getSelectionModel().getSelectedItem());
			}
			if (tabPane.getSelectionModel().getSelectedItem()==tabCourriers) {
				confirmerSupprCourrier(courriers.getSelectionModel().getSelectedItem());
			}
			if (tabPane.getSelectionModel().getSelectedItem()==tabItineraires) {
				confirmerSupprItineraire(itineraires.getSelectionModel().getSelectedItem());
			}	
		}
		
		if (e.getSource()==bnSauver) {
			Principale.sauverListes();
			sauvegarde();
		}
		
		if (e.getSource()==bnFermer) {
			if (!estSauvegarde) {
				demandeSauvegarde();
			}
			Principale.ouvrirAccueil(this);
		}
	}
	
	/**
	 * Gestion des clics de la souris.
	 * @param e
	 */
	private void gererClic(MouseEvent e) {
		if (e.getSource()==facteurs) {
			if (e.getClickCount() == 2 && e.getButton() == MouseButton.PRIMARY && e.getTarget() instanceof Text) {
				Principale.ouvrirModifierFacteur(facteurs.getSelectionModel().getSelectedItem(), facteurs.getSelectionModel().getSelectedIndex());
			}
			
			if (facteurs.getSelectionModel().getSelectedIndex() != -1) {
				activeBoutons(true);
				details.setText(getDetails(facteurs.getSelectionModel().getSelectedItem()));
			}
			else {
				activeBoutons(false);
			}
		}
		
		if (e.getSource()==garage) {
			if (e.getClickCount() == 2 && e.getButton() == MouseButton.PRIMARY && e.getTarget() instanceof Text) {
				Principale.ouvrirModifierVehicule(garage.getSelectionModel().getSelectedItem(), garage.getSelectionModel().getSelectedIndex());
			}
			
			if (garage.getSelectionModel().getSelectedIndex() != -1) {
				activeBoutons(true);
				details.setText(getDetails(garage.getSelectionModel().getSelectedItem()));
			}
			else {
				activeBoutons(false);
			}
		}
		
		if (e.getSource()==courriers) {
			if (e.getClickCount() == 2 && e.getButton() == MouseButton.PRIMARY && e.getTarget() instanceof Text) {
				Principale.ouvrirModifierCourrier(courriers.getSelectionModel().getSelectedItem(), courriers.getSelectionModel().getSelectedIndex());
			}
			
			if (courriers.getSelectionModel().getSelectedIndex() != -1) {
				activeBoutons(true);
				details.setText(getDetails(courriers.getSelectionModel().getSelectedItem()));
			}
			else {
				activeBoutons(false);
			}
		}
		
		if (e.getSource()==itineraires) {
			if (e.getClickCount() == 2 && e.getButton() == MouseButton.PRIMARY && e.getTarget() instanceof Text) {
				Principale.ouvrirModifierItineraire(itineraires.getSelectionModel().getSelectedItem(), itineraires.getSelectionModel().getSelectedIndex());
			}
			
			if (itineraires.getSelectionModel().getSelectedIndex() != -1) {
				activeBoutons(true);
				details.setText(getDetails(itineraires.getSelectionModel().getSelectedItem()));
			}
			else {
				activeBoutons(false);
			}
		}
	}
	
	/**
	 * Permet la sauvegarde.
	 */
	private void sauvegarde() { 
		String message = "Sauvegarde réussie";
		Alert alert = new Alert(AlertType.INFORMATION, message, ButtonType.OK);
		alert.setTitle("Sauvegarde réussie");
		estSauvegarde = true;
		alert.showAndWait();
	}
	/**
	 * Demande la sauvegarde.
	 */
	
	private void demandeSauvegarde() { 
		String message = "Voulez-vous sauvegarder avant de quitter ?";
		Alert alert = new Alert(AlertType.WARNING, message, ButtonType.YES, ButtonType.NO);
		alert.setTitle("Garage non sauvegardé");
	
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.isPresent() && result.get() == ButtonType.YES) {
			Principale.sauverListes();
			sauvegarde();
		}
	}
	
	/**
	 * Envoie un message de confirmation de suppression d'un facteur.
	 * @param f
	 */
	private void confirmerSupprFacteur(Facteur f) { 
		String message = "Voulez-vous vraiment supprimer ce facteur : " + f + " ?";
		Alert alert = new Alert(AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO);
		alert.setTitle("Supprimer un facteur");
	
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.isPresent() && result.get() == ButtonType.YES) {
			Principale.supprimerFacteur(f);
			if (garage.getItems().size() == 0) {
				activeBoutons(false);
			}
		}
	}
	
	/**
	 * Envoie un message de confirmation de suppression d'un véhicule.
	 * @param v
	 */
	private void confirmerSupprVehicule(VehiculeMotorise v) { 
		String message = "Voulez-vous vraiment supprimer ce véhicule : " + v + " ?";
		Alert alert = new Alert(AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO);
		alert.setTitle("Supprimer un véhicule");
	
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.isPresent() && result.get() == ButtonType.YES) {
			Principale.supprimerVehicule(v);
			if (garage.getItems().size() == 0) {
				activeBoutons(false);
			}
		}
	}
	/**
	 * Envoie un message de confirmation de suppression d'un courrier.
	 * @param c
	 */
	private void confirmerSupprCourrier(Courrier c) { 
		String message = "Voulez-vous vraiment supprimer ce courrier : " + c + " ?";
		Alert alert = new Alert(AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO);
		alert.setTitle("Supprimer un courrier");
	
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.isPresent() && result.get() == ButtonType.YES) {
			Principale.supprimerCourrier(c);
			if (garage.getItems().size() == 0) {
				activeBoutons(false);
			}
		}
	}
	/**
	 * Envoie un message de confirmation de suppression d'un itinéraire.
	 * @param i
	 */
	private void confirmerSupprItineraire(Itineraire i) { 
		String message = "Voulez-vous vraiment supprimer cet itinéraire : " + i + " ?";
		Alert alert = new Alert(AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO);
		alert.setTitle("Supprimer un itinéraire");
	
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.isPresent() && result.get() == ButtonType.YES) {
			Principale.supprimerItineraire(i);
			if (garage.getItems().size() == 0) {
				activeBoutons(false);
			}
		}
	}
	
	/**
	 * Retourne les caractéristiques d'un facteur.
	 * @param f
	 */
	private String getDetails(Facteur f) {
		return f.toString();
	}
	
	/**
	 * Retourne les caractéristiques d'un véhicule motorisé.
	 * @param v
	 */
	private String getDetails(VehiculeMotorise v) {
		String str = v.getType() + "\n\n"
				+ "Immatriculation :\n"
				+ "	- " + v.getMatricule() + "\n"
				+ "Vitesse maximale :\n"
				+ "	- " + v.getVitesseMax() + " km/h" + "\n"
				+ "Capacité maximale :\n"
				+ "	- " + v.getCapacite() + " kg" + "\n"
				+ "Carburant maximum :\n"
				+ "	- " + v.getCarburant() + " L" + "\n"
				+ "Consommation :\n"
				+ "	- " + v.getConsoCarburant() + " L/100 km" + "\n";
		if (v.isDroitPieton()) {
			str += "\n" + "Droit piéton";
		}
		if (v.isBesoinPermisA()) {
			str += "\n" + "Permis A nécessaire";
		}
		if (v.isBesoinPermisB()) {
			str += "\n" + "Permis B nécessaire";
		}
		return str;
	}
	
	/**
	 * Retourne les caractéristiques d'un courrier.
	 * @param c
	 */
	private String getDetails(Courrier c) {
		return c.toString();
	}
	
	/**
	 * Retourne les caractéristiques d'un itinéraire.
	 * @param i
	 */
	private String getDetails(Itineraire i) {
		return i.toString();
	}
}
