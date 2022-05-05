import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
 
/**
 * La classe FenetreAccueil est notre fenêtre d'accueil qui contient le menu de navigation, c'est la fenêtre principale de l'application.
 */
public class FenetreAccueil extends Stage { 
	// Les composants de la fenêtre
	private String				login				= null;
	private Button 				bnDeconnex 			= new Button("Se déconnecter");
	private Button 				bnFermer 			= new Button("Fermer le programme");
	private Rectangle 		    fondEcran			= new Rectangle();
	private Label 				txtBvn				= new Label(login+", \nBienvenue dans l'application de la tournée des facteurs !"); 
	private Button				eltMenu1			= new Button("Consulter l'itinéraire par étape");
	private Button				eltMenu2			= new Button("Consulter l'itinéraire détaillé");
	private Button				eltMenu3			= new Button("Consulter les données de la poste");
	private Button				eltMenu4			= new Button("Consulter vos informations");
	private Image 				logoit 				= new Image("file:/../images/logoit.png", 250, 250, true, true);
	private ImageView 			logoitView 			= new ImageView(logoit);
	private Image 				logoitdetaille 		= new Image("file:/../images/logoitdetaille.png", 250, 250, true, true);
	private ImageView 			logoitdetailleView 	= new ImageView(logoitdetaille);
	private Image 				logogarage 			= new Image("file:/../images/logogarage.png", 250, 250, true, true);
	private ImageView 			logogarageView 		= new ImageView(logogarage);
	private Image 				logoinfo 			= new Image("file:/../images/logoinfo.png", 250, 250, true, true);
	private ImageView 			logoinfoView 		= new ImageView(logoinfo);
	private double				tailleX				= 450;
	private double				tailleY				= 550;
	
	// Les layouts de la fenêtre
	HBox zoneBvn = new HBox();
	VBox zoneFermer = new VBox();
	HBox menu1 = new HBox();
	HBox menu2 = new HBox();
	VBox menu = new VBox();
	BorderPane bp = new BorderPane();
	StackPane root = new StackPane();
	
	/**
	 * Crée la fenêtre d'accueil.
	 */
	public FenetreAccueil(){ 
		this.setTitle("Accueil"); 
		this.setResizable(false);
		this.setMinWidth(tailleX);
		this.setMinHeight(tailleY);
		
		bnDeconnex.setOnAction(e -> gererFermer(e));
		bnFermer.setOnAction(e -> gererFermer(e));
		eltMenu1.setOnAction(e -> gererOuvrir(e));
		eltMenu2.setOnAction(e -> gererOuvrir(e));
		eltMenu3.setOnAction(e -> gererOuvrir(e));
		eltMenu4.setOnAction(e -> gererOuvrir(e));
		
		Scene laScene = new Scene(creerContenu());
		this.setScene(laScene);
		this.sizeToScene();
	}
	/**
	 * Crée le contenu de la fenêtre d'accueil. 
	 * @return
	 */
	private Parent creerContenu(){
		txtBvn.setFont(Font.font("Cambria", FontWeight.BOLD, 15));
		txtBvn.setTextFill(Color.BLUEVIOLET);
		bnDeconnex.setPrefWidth(200);
		bnFermer.setPrefWidth(200);
		eltMenu1.setPrefWidth(180);
		eltMenu1.setPrefHeight(180);
		eltMenu1.setFont(Font.font("Cambria", FontWeight.BOLD, 14));
		eltMenu1.setWrapText(true);
		eltMenu1.setTextAlignment(TextAlignment.CENTER);
		logoitView.setFitWidth(100);
		logoitView.setFitHeight(100);
		logoitdetailleView.setFitWidth(100);
		logoitdetailleView.setFitHeight(100);
		logogarageView.setFitWidth(100);
		logogarageView.setFitHeight(100);
		logoinfoView.setFitWidth(100);
		logoinfoView.setFitHeight(100);
		eltMenu1.setGraphic(logoitView);
		eltMenu1.setContentDisplay(ContentDisplay.TOP);
		eltMenu2.setPrefWidth(180);
		eltMenu2.setPrefHeight(180);
		eltMenu2.setFont(Font.font("Cambria", FontWeight.BOLD, 14));
		eltMenu2.setWrapText(true);
		eltMenu2.setTextAlignment(TextAlignment.CENTER);
		eltMenu2.setGraphic(logoitdetailleView);
		eltMenu2.setContentDisplay(ContentDisplay.TOP);
		eltMenu3.setPrefWidth(180);
		eltMenu3.setPrefHeight(180);
		eltMenu3.setFont(Font.font("Cambria", FontWeight.BOLD, 14));
		eltMenu3.setWrapText(true);
		eltMenu3.setTextAlignment(TextAlignment.CENTER);
		eltMenu3.setGraphic(logogarageView);
		eltMenu3.setContentDisplay(ContentDisplay.TOP);
		eltMenu4.setPrefWidth(180);
		eltMenu4.setPrefHeight(180);
		eltMenu4.setFont(Font.font("Cambria", FontWeight.BOLD, 14));
		eltMenu4.setWrapText(true);
		eltMenu4.setTextAlignment(TextAlignment.CENTER);
		eltMenu4.setGraphic(logoinfoView);
		eltMenu4.setContentDisplay(ContentDisplay.TOP);
		fondEcran.setWidth(tailleX);
		fondEcran.setHeight(tailleY);
		fondEcran.setFill(Color.BEIGE);
		
		zoneBvn.setSpacing(40);
		zoneBvn.setAlignment(Pos.CENTER);
		zoneBvn.setPadding(new Insets(20));
		HBox.setMargin(txtBvn, new Insets(10));
		zoneBvn.getChildren().addAll(txtBvn);
				
		zoneFermer.setSpacing(0);
		zoneFermer.setAlignment(Pos.CENTER);
		VBox.setMargin(bnFermer, new Insets(10));
		zoneFermer.getChildren().addAll(bnDeconnex, bnFermer);
		
		menu1.getChildren().addAll(eltMenu1,eltMenu2);
		menu1.setSpacing(10);
		menu1.setAlignment(Pos.CENTER);
		
		menu2.getChildren().addAll(eltMenu3,eltMenu4);
		menu2.setSpacing(10);
		menu2.setAlignment(Pos.CENTER);
		
		menu.setSpacing(10);
		menu.getChildren().addAll(menu1,menu2);
		
		bp.setCenter(menu);
		bp.setTop(zoneBvn);
		bp.setBottom(zoneFermer);
		
		root.getChildren().addAll(fondEcran,bp);

		return root;
	}
	
	/**
	 * Méthode qui initialise la fenêtre d'accueil.
	 * @param login
	 */
	public void init(String login){
		this.login = login;
		txtBvn.setText(login+", \nBienvenue dans l'application de la tournée des facteurs !"); 
	}
	
	/**
	 * Méthode qui gère les boutons qui s'occupe de fermer la page d'accueil, c'est à dire les boutons "Fermer le programme" et "Se déconnecter".
	 * @param e
	 */
	private void gererFermer(ActionEvent e) {
		if (e.getSource()==bnDeconnex) {
			Alert a = new Alert(AlertType.WARNING, "Voulez-vous vraiment vous déconnecter ?",ButtonType.YES,ButtonType.NO);
			a.setTitle("Confirmation de déconnexion");
			a.setHeight(100);
			Optional<ButtonType> result = a.showAndWait();
			 
			if(result.get() == ButtonType.YES) {
				a.close();
				Principale.ouvrirConnexion();
			}
		}
		if (e.getSource()==bnFermer) {
			this.close();
		}
	}
	
	/**
	 * Méthode qui gère l'ouverture du menu de la page d'accueil.
	 * @param e
	 */
	private void gererOuvrir(ActionEvent e) {
		if(e.getSource()==eltMenu1) {
			Principale.ouvrirItineraire();
		}
		else if(e.getSource()==eltMenu2) {
			Principale.ouvrirItineraireDetaille();
		}
		else if(e.getSource()==eltMenu3) {
			Principale.ouvrirGestionDonnees();
		}
		else if(e.getSource()==eltMenu4) {
			Principale.ouvrirInformation();
		}
	}
}
