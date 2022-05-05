import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Fenêtre de connexion.
 */
public class FenetreConnexion extends Stage {
	// les composants de la fenêtre
	private BorderPane  			bPane			= new BorderPane();
	private GridPane 				gPane			= new GridPane();
	private HBox 					hboxLogo 		= new HBox();
	private HBox 					hboxLogin 		= new HBox(10);
	private HBox 					hboxPass 		= new HBox(10);
	private VBox 					zoneBoutons 	= new VBox(10);
	private StackPane 				root 			= new StackPane();
	private Rectangle 		    	fondEcran		= new Rectangle();
	private Label 					titre 			= new Label("Connexion à Tournée du facteur");
	private Label					lblLogin 		= new Label("Login");
	private Label 					lblPass 		= new Label("Mot de passe");
	private Label 					deuxPointsLogin = new Label(":");
	private Label 					deuxPointsPass 	= new Label(":");
	private TextField				saisieLogin		= new TextField();
	private PasswordField			saisiePass		= new PasswordField();
	private Label 					erreur 			= new Label("");
	private Button 					bnConnexion 	= new Button("Se connecter");
	private Button 					bnCreerCompte 	= new Button("Créer un compte");
	private Button 					bnFermer 		= new Button("Fermer le programme");
	private Image 					image 			= new Image("file:/../images/logo.png", 250, 250, true, true);
	private ImageView 				imageView 		= new ImageView(image);
	private double					tailleX			= 450;
	private double					tailleY			= 550;
	private TextFormatter<String> 	formatter	 	= new TextFormatter<String>( change -> {
														change.setText(change.getText().replaceAll("[^a-zA-Z]", ""));
														change.setText(change.getText().toLowerCase());
													    return change; 
														});
	
	// constructeur : initialisation de la fenêtre
	/**
	 * Crée la fenêtre de connexion.
	 */
	public FenetreConnexion(){
		this.setTitle("Tournée du facteur");
		this.setResizable(false);
		this.setWidth(tailleX);
		this.setHeight(tailleY);
		
		Scene scene = new Scene(creerContenu());
		this.setScene(scene);
	}
	
	// création du Scene graph
	/**
	 * Crée le contenu de la page de connexion.
	 * @return
	 */
	private Parent creerContenu() {
		bPane.setPadding(new Insets(10));

		titre.setFont(Font.font("Cambria",15));
		titre.setPadding(new Insets(20));
		
		fondEcran.setWidth(tailleX);
		fondEcran.setHeight(tailleY);
		fondEcran.setFill(Color.BEIGE);
		
		hboxLogo.getChildren().add(imageView);
		hboxLogo.setAlignment(Pos.CENTER);
		
		lblLogin.setPrefWidth(75);
		lblLogin.setAlignment(Pos.CENTER_RIGHT);
		saisieLogin.setPrefWidth(200);
		saisieLogin.setTextFormatter(formatter);
		
		lblPass.setPrefWidth(75);
		lblPass.setAlignment(Pos.CENTER_RIGHT);
		saisiePass.setPrefWidth(200);
		
		erreur.setTextFill(Color.RED);
		
		hboxLogin.getChildren().addAll(lblLogin, deuxPointsLogin, saisieLogin);
		hboxPass.getChildren().addAll(lblPass, deuxPointsPass, saisiePass);
		zoneBoutons.getChildren().addAll(bnConnexion, bnCreerCompte, bnFermer);
		
		gPane.add(titre, 0, 0);
		gPane.add(hboxLogin, 0, 1);
		gPane.add(hboxPass, 0, 2);
		gPane.add(erreur, 0, 3);
		
		GridPane.setHalignment(titre, HPos.CENTER);
		GridPane.setHalignment(lblLogin, HPos.RIGHT);
		GridPane.setHalignment(lblPass, HPos.RIGHT);
		GridPane.setHalignment(erreur, HPos.CENTER);
		gPane.setHgap(5);
		gPane.setVgap(5);
		
		gPane.setAlignment(Pos.CENTER);
		
		bnConnexion.setPrefWidth(200);
		bnCreerCompte.setPrefWidth(200);
		bnFermer.setPrefWidth(200);
		zoneBoutons.setAlignment(Pos.CENTER);
		
		saisieLogin.setOnKeyPressed( e -> gererSaisie(e));
		saisiePass.setOnKeyPressed( e -> gererSaisie(e));
		bnConnexion.setOnAction( e -> gererBoutons(e));
		bnCreerCompte.setOnAction( e -> gererBoutons(e));
		bnFermer.setOnAction( e -> gererBoutons(e));
		
		bPane.setTop(hboxLogo);
		bPane.setCenter(gPane);
		bPane.setBottom(zoneBoutons);
		
		root.getChildren().addAll(fondEcran,bPane);

		return root;
	}
	
	/**
	 * Initialisation de la fenêtre connexion.
	 */
	
	public void init(){
		this.saisieLogin.setText("");
		this.saisiePass.setText("");
		this.erreur.setText("");
	}
	
	/**
	 * Gère la touche du clavier "Entrer". 
	 * @param e
	 */
	private void gererSaisie(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER) {
			connexion();
		}
		else {
			erreur.setText("");
		}
	}
	
	/**
	 * Gère les boutons de la fenêtre de connexion.
	 * @param e
	 */
	private void gererBoutons(ActionEvent e) {
		if (e.getSource()==bnConnexion) {
			connexion();
		}
		if (e.getSource()==bnCreerCompte) {
			Principale.ouvrirCreerCompte();
		}
		if (e.getSource()==bnFermer) {
			System.exit(0);
		}
	}
	
	/**
	 * Méthode qui permet de se connecter.
	 */
	private void connexion() {
		String login = saisieLogin.getText();
		File loginFile = new File("fichiers/" + login + "_pass.dat");
		if (loginFile.exists()) {
			String pass;
			try {
				pass = Files.readAllLines(Paths.get("fichiers/" + login + "_pass.dat")).get(0);
				if (saisiePass.getText().equals(pass)) {
					Principale.setLogin(login);
					Principale.ouvrirAccueil(this);
				}
				else {
					erreur.setText("Mot de passe incorrect.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			erreur.setText("Login ou mot de passe incorrect");
		}
	}
}
