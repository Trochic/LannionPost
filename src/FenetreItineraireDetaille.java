import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
 
/**
 * Fenêtre itinéraire détaillé.
 */
public class FenetreItineraireDetaille extends Stage { 
	/**Distance de l'itinéraire du facteur.**/
	protected double distance;
	/**Durée de l'itinéraire du facteur.**/
	protected double duree;
	/**Nombre de maisons par lequel le facteur doit passer durant son itinéraire.**/
	protected int nbMaison;
	/**L'itinéraire du facteur.**/
	protected Itineraire iti;
	/**Nombre de rues de l'itinéraire.**/
	protected int nbRue;
	
	// Les composants de la fenêtre
	private ListView<String> 	liste		= new ListView<String>();
	private Button 				bnFermer 	= new Button("Fermer");
	private Button 				bnPlusDinfo = new Button("Plus d'info !");
	private Rectangle 		    fondEcran	= new Rectangle();
	private double				tailleX		= 450;
	private double				tailleY		= 550;
	
	// Les layouts de la fenêtre
	HBox zoneBoutons = new HBox();
	VBox it = new VBox();
	StackPane root = new StackPane();
	
	/**
	 * Crée une nouvelle fenêtre itinéraire détaillé.
	 */
	public FenetreItineraireDetaille(){ 
		this.setTitle("Itinéraire détaillé"); 
		this.setResizable(false);
		this.setMinWidth(tailleX);
		this.setMinHeight(tailleY);
		
		iti = new Itineraire(15,2.5);
		nbRue = iti.getNbRue();
		
		bnFermer.setOnAction(e -> gererFermer(e));
		bnPlusDinfo.setOnAction(e -> gererPlusDinfo(e));
		liste.setOnMouseClicked(e -> gererRue());
		liste.setOnKeyPressed(e -> gererRueClavier(e));
		
		Scene laScene = new Scene(creerContenu());
		this.setScene(laScene);
		this.sizeToScene();
	}
	/**
	 * Crée le contenu de cette fenêtre.
	 */
	private Parent creerContenu(){
		int numero=1;
		for (int i=0 ; i<nbRue ; i++){
			liste.getItems().add(numero+". Rue "+nomRue(i));
			numero++;
		}
		liste.setPrefWidth(tailleX);
		liste.setPrefHeight(tailleY-45);
		bnFermer.setPrefWidth(80);
		bnPlusDinfo.setPrefWidth(80);
		
		fondEcran.setWidth(tailleX);
		fondEcran.setHeight(tailleY);
		fondEcran.setFill(Color.BEIGE);
				
		zoneBoutons.setSpacing(40);
		zoneBoutons.setAlignment(Pos.CENTER);
		HBox.setMargin(bnFermer, new Insets(10));
		zoneBoutons.getChildren().addAll(bnFermer,bnPlusDinfo);
		
		it.getChildren().addAll(liste,zoneBoutons);
		
		root.getChildren().addAll(fondEcran,it);

		return root;
	}
	
	/**
	 * Méthode qui passe un nombre (double) au format heure,minute (Exemple avec 2.3 : 2h30). Amélioration de l'affichage du durée.
	 * @param d
	 * @return duree
	 */
	public String gererDuree(double d) {
		int heure,min;
		heure = (int) d;
		min = (int) ((d-heure)*60);
		return heure+"h"+min+"min.";
	}
	
	/**
	 * Initialise en savoir plus.
	 */
	public void initEnSavoirPlus() {
		nbMaison = iti.getNbMaisons();
		distance = iti.getDistance();
		duree = iti.getDuree();
		nbRue = iti.getNbRue();
	}
	
	/**
	 * Gestion du bouton "Plus d'info !".
	 * @param e
	 */
	private void gererPlusDinfo(ActionEvent e) {
		initEnSavoirPlus();
		Alert a = new Alert(AlertType.INFORMATION, "La distance de l'itinéraire est de "+this.distance+" Km et la durée estimée est de "+gererDuree(this.duree)+" \nVous passerez par "+nbRue+" rues et "+nbMaison+" maisons.\nBon courage et bonne journée !",ButtonType.CLOSE);
		a.setTitle("Plus d'information sur cet itinéraire");
		a.setHeight(300);
		Optional<ButtonType> result = a.showAndWait();
		 
		if(result.get() == ButtonType.CLOSE) {
			a.close();
		}
	}
	
	/**
	 * Retourne la rue à l'indice i.
	 * @param i
	 */
	protected String getRue(int i) {
		String rue = iti.getIt(i);
		return rue;
	}
	/**
	 * Retourne la rue à l'indice i.
	 * @param i
	 */
	protected String nomRue(int i) {
		String rue = iti.getRueIt(i);
		return rue;
	}
	
	/**
	 * Gestion d'un élément sélectionné dans la liste au clique.
	 */
	private void gererRue() {
		if(liste.getSelectionModel().getSelectedIndex()>-1) {
			String rang;
			if(liste.getSelectionModel().getSelectedIndex()==0) {
				rang = "ère";
			}
			else {
				rang = "ème";
			}
			Alert a = new Alert(AlertType.INFORMATION, "La rue "+getRue(liste.getSelectionModel().getSelectedIndex())+"\nC'est la "+(1+liste.getSelectionModel().getSelectedIndex())+rang+" rue de l'itinéraire.",ButtonType.CLOSE);
			a.setTitle("Plus d'information sur cette rue");
			a.setHeight(300);
			Optional<ButtonType> result = a.showAndWait();
			 
			if(result.get() == ButtonType.CLOSE) {
				a.close();
			}
		}
	}
	/**
	 * Gestion d'un élément sélectionné dans la liste au clavier avec "Entrer".
	 * @param e
	 */
	private void gererRueClavier(KeyEvent e) {
		if(liste.getSelectionModel().getSelectedIndex()>-1 && e.getCode()==KeyCode.ENTER) {
			String rang;
			if(liste.getSelectionModel().getSelectedIndex()==0) {
				rang = "ère";
			}
			else {
				rang = "ème";
			}
			Alert a = new Alert(AlertType.INFORMATION, "La rue "+getRue(liste.getSelectionModel().getSelectedIndex())+"\nC'est la "+(1+liste.getSelectionModel().getSelectedIndex())+rang+" rue de l'itinéraire.",ButtonType.CLOSE);
			a.setTitle("Plus d'information sur cette rue");
			a.setHeight(300);
			Optional<ButtonType> result = a.showAndWait();
			 
			if(result.get() == ButtonType.CLOSE) {
				a.close();
			}
		}
	}
	/**
	 * Gestion de la fermeture de la fenêtre.
	 * @param e
	 */
	private void gererFermer(ActionEvent e) {
		Principale.ouvrirAccueil(this);
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public void setDuree(double duree) {
		this.duree = duree;
	}

	public void setNbMaison(int nbMaison) {
		this.nbMaison = nbMaison;
	}
}
