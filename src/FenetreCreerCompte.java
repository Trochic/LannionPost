import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * La fenêtre pour créer un compte.
 */
public class FenetreCreerCompte extends Stage {
	// les composants de la fenêtre
	private VBox 					racine			= new VBox();
	private GridPane 				zoneSaisie 		= new GridPane();
	private HBox 					zoneBoutons 	= new HBox(20);
	private Label 					lblLogin		= new Label("Login (lettres minuscules uniquement)");
	private TextField				txtLogin		= new TextField();
	private Label 					erreurLogin		= new Label("");
	private Label 					lblPass			= new Label("Mot de passe");
	private PasswordField			txtPass			= new PasswordField();
	private Button 					bnOK			= new Button("OK");
	private Button 					bnAnnuler		= new Button("Annuler");
	private Pattern					carAutorises	= Pattern.compile("[a-zA-Z]*");
	private TextFormatter<String> 	formatter	 	= new TextFormatter<String>( change -> {
														change.setText(change.getText().replaceAll("[^a-zA-Z]", ""));
														change.setText(change.getText().toLowerCase());
													    return change; 
														});
	// constructeur : initialisation de la fenêtre
	/**
	 * Crée une fenêtre pour créer un compte.
	 */
	public FenetreCreerCompte(){
		this.setTitle("Créer un compte");
		this.setResizable(false);
		this.setScene(new Scene(creerContenu()));
		this.sizeToScene();
	}
	
	// création du Scene graph
	/**
	 * Crée le contenu de créer un compte.
	 */
	private Parent creerContenu() {
		this.bnOK.setPrefWidth(100);
		this.bnAnnuler.setPrefWidth(100);
		this.bnOK.setDisable(true);
		
		zoneSaisie.setHgap(10);
		zoneSaisie.setVgap(10);
		zoneSaisie.add(lblLogin,  0,  0);
		zoneSaisie.add(txtLogin,  0,  1);
		zoneSaisie.add(erreurLogin,  0,  2);
		zoneSaisie.add(lblPass,  0,  3);
		zoneSaisie.add(txtPass,  0,  4);
		
		erreurLogin.setTextFill(Color.RED);
		
		txtLogin.setPrefWidth(220);
		txtLogin.setTextFormatter(formatter);
					
		zoneBoutons.getChildren().addAll(bnAnnuler, bnOK);
		zoneBoutons.setAlignment(Pos.CENTER_RIGHT);
		
		racine.getChildren().addAll(zoneSaisie, zoneBoutons);
		VBox.setMargin(zoneSaisie, new Insets(20, 20, 20, 20));
		VBox.setMargin(zoneBoutons, new Insets(20, 20, 20, 20));

		// détection et traitement des événements
		bnOK.setOnAction( e -> gererBoutons(e));
		bnAnnuler.setOnAction( e -> gererBoutons(e));
		txtLogin.setOnKeyReleased( e -> gererSaisie(e));
		txtPass.setOnKeyReleased( e -> gererSaisie(e));
		
		return racine;
	}
	
	/**
	 * Initialisation de la fenêtre.
	 */
	public void init(){
		this.setLogin("");
		this.setPass("");
	}
	
	public void setLogin(String login){
		txtLogin.setText(login);
	}
	
	public void setPass(String pass){
		txtPass.setText(pass);
	}
	
	/**
	 * Activation ou non du bouton "OK".
	 * @param b
	 */
	public void activeBoutonOK(boolean b){
		bnOK.setDisable(!b);
	}

	// traitement des événements (les callbacks)

	/**
	 * Gestion des boutons de la fenêtre.
	 * @param e
	 */
	private void gererBoutons(ActionEvent e) {
		if (e.getSource()==bnOK) {
			creerCompte();
		}
		if (e.getSource()==bnAnnuler) {
			this.close();
		}
	}
	
	/**
	 * Gestion de la saisie au clavier.
	 * @param e
	 */
	private void gererSaisie(KeyEvent e) {
		if (e.getSource() == txtLogin && !carAutorises.matcher(e.getText()).matches() && e.getCode() != KeyCode.TAB) {
			System.out.print(e.getText());
			erreurLogin.setText("Caractère invalide");
		}
		else {
			erreurLogin.setText("");
		}
		
		if (txtLogin.getText().equals("") || txtPass.getText().equals("")) { // vérifie que tous les champs sont remplis
			this.activeBoutonOK(false);
		}
		else {
			this.activeBoutonOK(true);
			if (e.getCode() == KeyCode.ENTER) {
				creerCompte();
			}
		}
	}
	
	/**
	 * Méthode pour créer un nouveau compte.
	 */
	private void creerCompte() {
		if (!carAutorises.matcher(txtLogin.getText()).matches()) {
			erreurLogin.setText("Login invalide");
		}
		else {				
			try {
				File fichier = new File("fichiers/" + txtLogin.getText() + "_pass.dat");
				if (fichier.createNewFile()) {
					FileWriter ecriture = new FileWriter("fichiers/" + txtLogin.getText() + "_pass.dat");
					ecriture.write(txtPass.getText());
					ecriture.close();
					
					new File("fichiers/" + txtLogin.getText() + "_garage.dat").createNewFile();
					new File("fichiers/" + txtLogin.getText() + "_facteurs.dat").createNewFile();
					new File("fichiers/" + txtLogin.getText() + "_courriers.dat").createNewFile();
					new File("fichiers/" + txtLogin.getText() + "_itineraires.dat").createNewFile();
					new File("fichiers/" + txtLogin.getText() + "_infos.dat").createNewFile();
					
					System.out.println("Compte et fichiers créé : " + txtLogin.getText());
					this.close();
				}
				else {
					System.out.println("Ce login existe déjà");
					erreurLogin.setText("Ce login existe déjà");
				}
			}
			catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}
}
