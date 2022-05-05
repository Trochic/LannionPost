import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * Fenêtre modifier mot de passe.
 */
public class FenetreModifierMdp extends Stage {
	// les composants de la fenêtre
	private VBox 					racine			= new VBox();
	private GridPane 				zoneSaisie 		= new GridPane();
	private HBox 					zoneBoutons 	= new HBox(20);
	private Label 					lblLogin		= new Label("Identifiant : "+Principale.getLogin());
	private Label 					lblPass			= new Label("Mot de passe actuel");
	private Label 					lblPass2		= new Label("Nouveau mot de passe :");
	private PasswordField			txtPass2		= new PasswordField();
	private PasswordField			txtPass			= new PasswordField();
	private Button 					bnOK			= new Button("OK");
	private Button 					bnAnnuler		= new Button("Annuler");
	private Label 					erreur 			= new Label("");
	
	// constructeur : initialisation de la fenêtre
	/**
	 * Crée la fenêtre.
	 */
	public FenetreModifierMdp(){
		this.setTitle("Modifier votre mot de passe");
		this.setWidth(350);
		this.setResizable(false);
		this.setScene(new Scene(creerContenu()));
	}
	
	// création du Scene graph
	/**
	 * Crée le contenu de la fenêtre modifier mot de passe.
	 */
	private Parent creerContenu() {
		this.bnOK.setPrefWidth(100);
		this.bnAnnuler.setPrefWidth(100);
		this.bnOK.setDisable(true);
		
		erreur.setTextFill(Color.RED);
		
		zoneSaisie.setHgap(10);
		zoneSaisie.setVgap(10);
		zoneSaisie.add(lblLogin,  0,  0);
		zoneSaisie.add(lblPass, 0, 1);
		zoneSaisie.add(txtPass, 0, 2);
		zoneSaisie.add(lblPass2,  0,  4);
		zoneSaisie.add(txtPass2,  0,  5);
		zoneSaisie.add(erreur,  0,  6);
		
		lblLogin.setPrefWidth(220);
					
		zoneBoutons.getChildren().addAll(bnAnnuler, bnOK);
		zoneBoutons.setAlignment(Pos.CENTER_RIGHT);
		
		racine.getChildren().addAll(zoneSaisie, zoneBoutons);
		VBox.setMargin(zoneSaisie, new Insets(20, 20, 20, 20));
		VBox.setMargin(zoneBoutons, new Insets(20, 20, 20, 20));

		// détection et traitement des événements
		bnOK.setOnAction( e -> gererBoutons(e));
		bnAnnuler.setOnAction( e -> gererBoutons(e));
		txtPass2.setOnKeyReleased( e -> gererSaisie(e));
		
		return racine;
	}
	/**
	 * Initialisation de la fenêtre.
	 */
	public void init(){
		lblLogin.setText("Identifiant : "+Principale.getLogin()); 
	}
	
	public void setLogin(String login){
		lblLogin.setText(login);
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
			String pass;
			try {
				pass = Files.readAllLines(Paths.get("fichiers/" + Principale.getLogin() + "_pass.dat")).get(0);
				if (txtPass.getText().equals(pass)) {
					Alert a = new Alert(AlertType.CONFIRMATION, Principale.getLogin()+", voulez-vous vraiment changer de mot de passe ?",ButtonType.YES,ButtonType.NO);
					a.setTitle("Confirmation de changement de mot de passe");
					a.setHeight(100);
					Optional<ButtonType> result = a.showAndWait();
					 
					if(result.get() == ButtonType.YES) {
						a.close();
						reCreerCompte();
						Principale.ouvrirConnexion();
					}
				}
				else {
					erreur.setText("L'ancien mot de passe n'est pas correct.");
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource()==bnAnnuler) {
			this.close();
		}
	}
	/**
	 * Gestion de la touche "Entrer".
	 * @param e
	 */
	private void gererSaisie(KeyEvent e) {
		if (txtPass.getText().equals("")) { // vérifie que tous les champs sont remplis
			this.activeBoutonOK(false);
		}
		else {
			this.activeBoutonOK(true);
			if (e.getCode() == KeyCode.ENTER) {
				String pass;
				try {
					pass = Files.readAllLines(Paths.get("fichiers/" + Principale.getLogin() + "_pass.dat")).get(0);
					if (txtPass.getText().equals(pass)) {
						Alert a = new Alert(AlertType.CONFIRMATION, Principale.getLogin()+", voulez-vous vraiment changer de mot de passe ?",ButtonType.YES,ButtonType.NO);
						a.setTitle("Confirmation de changement de mot de passe");
						a.setHeight(100);
						Optional<ButtonType> result = a.showAndWait();
						 
						if(result.get() == ButtonType.YES) {
							a.close();
							reCreerCompte();
							Principale.ouvrirConnexion();
						}
					}
					else {
						erreur.setText("L'ancien mot de passe n'est pas correct.");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	/**
	 * Méthode qui permet de modifier le mot de passe.
	 */
	private void reCreerCompte() {
		FileWriter ecriture;
		try {
			ecriture = new FileWriter("fichiers/" + Principale.getLogin() + "_pass.dat");
			ecriture.write(txtPass2.getText());
			ecriture.close();
			
			System.out.println("Mdp modifié de l'identifiant : "+Principale.getLogin());
			this.close();
			Principale.fermerInformation();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
