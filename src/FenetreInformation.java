import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Fenêtre d'informations du facteur.
 */
public class FenetreInformation extends Stage {
	private String nom ="";
	private String prenom="";
	private String sexe="";
	private String age="";
	private boolean permisA=false;
	private boolean permisB=false;
	private String permis="";
	
	// les composants de la fenêtre
	private BorderPane  			bPane			= new BorderPane();
	private HBox 					mc				= new HBox();
	private VBox 					zoneBoutons 	= new VBox(10);
	private StackPane 				root 			= new StackPane();
	private Rectangle 		    	fondEcran		= new Rectangle();
	private Label 					monCompte		= new Label("Mon compte");
	private Label					txtFact			= new Label("");
	private Button 					bnModifierMdp 	= new Button("Modifier le mot de passe");
	private Button 					bnFermer 		= new Button("Fermer");
	private Button 					bnDeconnex 		= new Button("Se déconnecter");
	private Button 					bnAjoutInfo 	= new Button("Ajouter ou modifier informations");
	private Label 					aucuneInfo		= new Label("");
	private double					tailleX			= 450;
	private double					tailleY			= 550;
	
	// constructeur : initialisation de la fenêtre
	/**
	 * Crée une fenêtre d'informations.
	 */
	public FenetreInformation(){
		this.setTitle("Informations");
		this.setResizable(false);
		this.setWidth(tailleX);
		this.setHeight(tailleY);
		
		Scene scene = new Scene(creerContenu());
		this.setScene(scene);
	}
	
	// création du Scene graph
	/**
	 * Crée un contenu pour la fenêtre d'informations.
	 */
	private Parent creerContenu() {
		bPane.setPadding(new Insets(10));
		
		fondEcran.setWidth(tailleX);
		fondEcran.setHeight(tailleY);
		fondEcran.setFill(Color.BEIGE);
		
		monCompte.setFont(Font.font("Cambria",FontWeight.BOLD,22));
		mc.getChildren().addAll(monCompte);
		mc.setAlignment(Pos.CENTER);
		mc.setPadding(new Insets(10));
		txtFact.setFont(Font.font("Cambria",FontWeight.BOLD,18));
		
		zoneBoutons.getChildren().addAll(bnAjoutInfo,bnModifierMdp,bnDeconnex,bnFermer);
		
		bnAjoutInfo.setPrefWidth(200);
		bnDeconnex.setPrefWidth(200);
		bnModifierMdp.setPrefWidth(200);
		bnFermer.setPrefWidth(200);
		zoneBoutons.setAlignment(Pos.CENTER);

		bnAjoutInfo.setOnAction( e -> gererBoutons(e));
		bnModifierMdp.setOnAction( e -> gererBoutons(e));
		bnDeconnex.setOnAction(e -> gererBoutons(e));
		bnFermer.setOnAction( e -> gererBoutons(e));
		
		bPane.setTop(mc);
		bPane.setBottom(zoneBoutons);
		
		root.getChildren().addAll(fondEcran,bPane);

		return root;
	}
	/**
	 * Gestion des boutons de la fenêtre.
	 * @param e
	 */
	private void gererBoutons(ActionEvent e) {
		if (e.getSource()==bnModifierMdp) {
			Principale.ouvrirModifierMdp();
		}
		if (e.getSource()==bnAjoutInfo) {
			if(nom.equals("") && prenom.equals("") && sexe.equals("") && age.equals("") && !permisA && !permisB) {
				Principale.ouvrirAjoutInfo();
			}
			else {
				Principale.ouvrirModifierInfo(nom, prenom, sexe, age, permisA, permisB);
			}
		}
		if (e.getSource()==bnDeconnex) {
			Alert a = new Alert(AlertType.WARNING, "Voulez-vous vraiment vous déconnecter ?",ButtonType.YES,ButtonType.NO);
			a.setTitle("Confirmation de déconnexion");
			a.setHeight(100);
			Optional<ButtonType> result = a.showAndWait();
			 
			if(result.get() == ButtonType.YES) {
				a.close();
				this.close();
				Principale.ouvrirConnexion();
			}
		}
		if (e.getSource()==bnFermer) {
			this.close();
			Principale.ouvrirAccueil(this);
		}
	}
	/**
	 * Initialisation de la fenêtre à vide.
	 */
	public void init() {
		if(nom.equals("") && prenom.equals("") && sexe.equals("") && age.equals("") && !permisA && !permisB) {
			aucuneInfo.setText("Aucune information saisie, veuillez les ajouter en appuyant \n       sur le bouton \"Ajouter ou modifier informations\".");
			aucuneInfo.setTextFill(Color.BLUE);
			aucuneInfo.setFont(Font.font(15));
			bPane.setCenter(aucuneInfo);
		}
		else {
			actualiserInfo(nom,prenom,sexe,age,permisA,permisB);
			txtFact.setText("Nom : "+nom+"\n\nPrénom : "+prenom+"\n\nSexe : "+sexe+"\n\nAge : "+age+" ans\n\nDétention de permis : "+permis);
			bPane.setCenter(txtFact);
		}
	}

	/**
	 * Actualisation de la fenêtre avec les informations.
	 * @param nom
	 * @param prenom
	 * @param sexe
	 * @param age
	 * @param permisA
	 * @param permisB
	 */
	public void actualiserInfo(String nom,String prenom,String sexe,String age,boolean permisA,boolean permisB) {
		this.nom=nom;
		this.prenom=prenom;
		this.sexe=sexe;
		this.age=age;
		this.permisA=permisA;
		this.permisB=permisB;
		
		if(permisA && !permisB) {
			permis="permis A";
		}
		else if(!permisA && permisB) {
			permis="permis B";
		}
		else if(permisA && permisB) {
			permis="permis A et permis B";
		}
		else {
			permis="Aucun permis";
		}
	}
}
