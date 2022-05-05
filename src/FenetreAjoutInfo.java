import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Fenêtre pour ajouter les informations d'un facteur.
 */

public class FenetreAjoutInfo extends Stage {
	
	// les composants de la fenêtre
	private VBox 			racine				= new VBox();
	private GridPane 		zoneSaisie 			= new GridPane();
	private HBox 			zoneBoutons 		= new HBox(20);
	private Label 			lblEntrerInfo		= new Label("Entrer mes informations");
	private Label 			lblNom				= new Label("Nom : *");
	private TextField		txtNom				= new TextField();
	private Label 			lblPrenom			= new Label("Prénom : *");
	private TextField		txtPrenom			= new TextField();
	private Label 			lblSexe				= new Label("Sexe : *");
	private TextField		txtSexe				= new TextField();
	private Label 			lblAge				= new Label("Age : *");
	private TextField		txtAge				= new TextField();
	private CheckBox 		cbPermisA			= new CheckBox("Permis A");
	private CheckBox 		cbPermisB			= new CheckBox("Permis B");
	private Button 			bnOK				= new Button("OK");
	private Button 			bnAnnuler			= new Button("Annuler");
	private Label 			champs				= new Label("* champs obligatoires");
	
	// constructeur : initialisation de la fenêtre
	/**
	 * Crée une fenêtre ajouter informations.
	 */
	public FenetreAjoutInfo(){
		this.setTitle("Ajouter ou modifier informations");
		this.setResizable(false);
		this.setScene(new Scene(creerContenu()));
		this.sizeToScene();
	}
	
	// création du Scene graph
	/**
	 * Crée le contenu de la fenêtre ajouter informations.
	 * @return
	 */
	private Parent creerContenu() {
		this.bnOK.setPrefWidth(100);
		this.bnAnnuler.setPrefWidth(100);
		
		lblEntrerInfo.setUnderline(true);
		lblEntrerInfo.setFont(Font.font(14));
		lblEntrerInfo.setPadding(new Insets(10));
		
		txtNom.setPromptText("Exemple : Dupont");
		txtPrenom.setPromptText("Exemple : Michel");
		txtSexe.setPromptText("Exemple : M ou Masculin");
		txtAge.setPromptText("Exemple : 45");
		
		zoneSaisie.setHgap(10);
		zoneSaisie.setVgap(10);
		zoneSaisie.add(lblEntrerInfo,  0,  0);
		zoneSaisie.add(lblNom,  0,  1);
		zoneSaisie.add(txtNom,  1,  1);
		zoneSaisie.add(lblPrenom,  0,  2);
		zoneSaisie.add(txtPrenom,  1,  2);
		zoneSaisie.add(lblSexe, 0,  3);
		zoneSaisie.add(txtSexe, 1,  3);
		zoneSaisie.add(lblAge,     0,  4);
		zoneSaisie.add(txtAge,     1,  4);
		zoneSaisie.add(cbPermisA,     1,  6);
		zoneSaisie.add(cbPermisB,     1,  7);
		zoneSaisie.add(champs,        0,  9);
					
		zoneBoutons.getChildren().addAll(bnAnnuler, bnOK);
		zoneBoutons.setAlignment(Pos.CENTER_RIGHT);
		
		racine.getChildren().addAll(zoneSaisie, zoneBoutons);
		VBox.setMargin(zoneSaisie, new Insets(20, 20, 20, 20));
		VBox.setMargin(zoneBoutons, new Insets(20, 20, 20, 20));

		// détection et traitement des événements
		bnOK.setOnAction( e -> gererBoutons(e));
		bnAnnuler.setOnAction( e -> gererBoutons(e));
		txtNom.setOnKeyReleased( e -> gererZones(e));
		txtPrenom.setOnKeyReleased( e -> gererZones(e));
		txtSexe.setOnKeyReleased( e -> gererZones(e));
		txtAge.setOnKeyReleased( e -> gererZones(e));
		
		return racine;
	}
	
	/**
	 * Initialisation de la fenêtre à vide.
	 */
	public void init(){
		this.setTxtNom("");
		this.setTxtPrenom("");
		this.setTxtSexe("");
		this.setTxtAge("");
		this.setPermisA(false);
		this.setPermisB(false);
		bnOK.setDisable(true);
	}
	
	/**
	 * Initialisation de la fenêtre à vide avec les paramètres pour garder les inforamtions saisies.
	 * @param nom
	 * @param prenom
	 * @param sexe
	 * @param age
	 * @param a
	 * @param b
	 */
	public void init(String nom, String prenom, String sexe, String age, Boolean a, Boolean b){
		this.setTxtNom(nom);
		this.setTxtPrenom(prenom);
		this.setTxtSexe(sexe);
		this.setTxtAge(age);
		this.setPermisA(a);
		this.setPermisB(b);
		bnOK.setDisable(false);
	}


	public void setTxtNom(String nom){
		txtNom.setText(nom);
	}
	
	public void setTxtPrenom(String prenom){
		txtPrenom.setText(prenom);
	}
	
	public void setTxtSexe(String sexe){
		txtSexe.setText(sexe);
	}
	
	public void setTxtAge(String age){
		txtAge.setText(age);
	}
	
	public void setPermisA(Boolean b){
		cbPermisA.setSelected(b);
	}
	
	public void setPermisB(Boolean b){
		cbPermisB.setSelected(b);
	}
	
	/**
	 * Activation du bouton "OK".
	 * @param b
	 */
	public void activeBoutonOK(boolean b){
		bnOK.setDisable(!b);
	}

	// traitement des événements (les callbacks)
		
	/**
	 * Traitement du bouton "OK" et "Annuler".
	 * @param e
	 */
	private void gererBoutons(ActionEvent e) {
		if (e.getSource()==bnOK) {
			Alert a = new Alert(AlertType.CONFIRMATION, "Voulez-vous sauvegarder ces informations ?",ButtonType.YES,ButtonType.NO);
			a.setTitle("Sauvegarde des informations");
			a.setHeight(100);
			Optional<ButtonType> result = a.showAndWait();
			 
			if(result.get() == ButtonType.YES) {
				a.close();
				String nom = txtNom.getText();
				String prenom = txtPrenom.getText();
				String sexe = txtSexe.getText();
				String age = txtAge.getText();
				boolean bPA = cbPermisA.isSelected();
				boolean bPB = cbPermisB.isSelected();
				Principale.ajouterInfo(nom, prenom, sexe, age, bPA, bPB);
				this.close();
				Principale.ouvrirInformation();
			}
			else {
				a.close();
				this.close();
			}
		}
		if (e.getSource()==bnAnnuler) {
			this.close();
		}
	}
	
	/**
	 * Gestion visuelle du bouton "OK" en fonction des informations saisies.
	 * @param e
	 */
	private void gererZones(Event e) {
		if (txtNom.getText().equals("") || txtPrenom.getText().equals("") || txtSexe.getText().equals("") || txtAge.getText().equals("") ||txtNom.getText().equals(" ") || txtPrenom.getText().equals(" ") || txtSexe.getText().equals(" ") || txtAge.getText().equals(" ")) { // vérifie que tous les champs sont remplis
			this.activeBoutonOK(false);
		}
		else {
			this.activeBoutonOK(true);
		}
	}
}
