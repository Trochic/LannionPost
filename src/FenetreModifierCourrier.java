import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FenetreModifierCourrier extends Stage {
	// les composants de la fenêtre
	private VBox 			racine				= new VBox();
	private GridPane 		zoneSaisie 			= new GridPane();
	private HBox 			zoneBoutons 		= new HBox(20);
	private Label 			lblTypeCourrier		= new Label("Type de courrier : *");
	private ComboBox<String>listeTypeCourrier	= new ComboBox<String>();
	private Label 			lblVolume			= new Label("Volume : *");
	private TextField		txtVolume			= new TextField();
	private CheckBox		cbPrioritaire		= new CheckBox("Prioritaire");
	private Label			lblPrenom 			= new Label("Prénom : *");
	private TextField		txtPrenom			= new TextField();
	private Label			lblNom 				= new Label("Nom : *");
	private TextField		txtNom				= new TextField();
	private Label			lblNomRue 			= new Label("Nom de la rue : *");
	private TextField		txtNomRue			= new TextField();
	private Label			lblAdresse 			= new Label("Numéro de rue : *");
	private TextField		txtAdresse			= new TextField();
	private Button 			bnOK				= new Button("OK");
	private Button 			bnAnnuler			= new Button("Annuler");
	private Label 			champs				= new Label("* champs obligatoires");
	
	// constructeur : initialisation de la fenêtre
	public FenetreModifierCourrier(){
		this.setTitle("Modifier un courrier");
		this.setResizable(false);
		this.setScene(new Scene(creerContenu()));
		this.sizeToScene();
	}
	
	// création du Scene graph
	private Parent creerContenu() {
		this.bnOK.setPrefWidth(100);
		this.bnAnnuler.setPrefWidth(100);
		
		zoneSaisie.setHgap(10);
		zoneSaisie.setVgap(10);
		zoneSaisie.add(lblTypeCourrier,  0,  0);
		zoneSaisie.add(listeTypeCourrier,1,  0);
		zoneSaisie.add(lblVolume, 		 0,  1);
		zoneSaisie.add(txtVolume, 		 1,  1);
		zoneSaisie.add(cbPrioritaire, 	 1,  2);
		zoneSaisie.add(lblPrenom, 		 0,  3);
		zoneSaisie.add(txtPrenom,		 1,  3);
		zoneSaisie.add(lblNom,			 0,  4);
		zoneSaisie.add(txtNom,   		 1,  4); 
		zoneSaisie.add(lblNomRue,  	  	 0,  5);
		zoneSaisie.add(txtNomRue,     	 1,  5); 
		zoneSaisie.add(lblAdresse,     	 0,  6);
		zoneSaisie.add(txtAdresse,     	 1,  6);
		zoneSaisie.add(champs,        	 0,  7);
					
		zoneBoutons.getChildren().addAll(bnAnnuler, bnOK);
		zoneBoutons.setAlignment(Pos.CENTER_RIGHT);
		
		racine.getChildren().addAll(zoneSaisie, zoneBoutons);
		VBox.setMargin(zoneSaisie, new Insets(20, 20, 20, 20));
		VBox.setMargin(zoneBoutons, new Insets(20, 20, 20, 20));

		// détection et traitement des événements
		bnOK.setOnAction( e -> gererBoutons(e));
		bnAnnuler.setOnAction( e -> gererBoutons(e));
		txtVolume.setOnKeyReleased( e -> gererZones(e));
		txtPrenom.setOnKeyReleased( e -> gererZones(e));
		txtNom.setOnKeyReleased( e -> gererZones(e));
		txtNomRue.setOnKeyReleased( e -> gererZones(e));
		txtAdresse.setOnKeyReleased( e -> gererZones(e));
		
		return racine;
	}
	
	public void init(Courrier c){
		this.listeTypeCourrier.setItems(FXCollections.observableArrayList("Pli", "Lettre", "Colis"));
		this.listeTypeCourrier.setValue(c.getType());
		this.setVolume(Double.toString(c.getVolume()));
		this.setPrenom(c.getDestinataire().getPrenom());
		this.setNom(c.getDestinataire().getNom());
		this.setNomRue(c.getDestinataire().getNomRue());
		this.setPrioritaire(c.isPrioritaire());
	}
	
	public void setVolume(String volume){
		txtVolume.setText(volume);
	}
	
	public void setPrenom(String prenom){
		txtPrenom.setText(prenom);
	}
	
	public void setNom(String nom){
		txtNom.setText(nom);
	}
	
	public void setNomRue(String nomRue){
		txtNomRue.setText(nomRue);
	}
	
	public void setAdresse(String adr){
		txtAdresse.setText(adr);
	}
	
	public void setPrioritaire(Boolean prio){
		cbPrioritaire.setSelected(prio);
	}
	
	public void activeBoutonOK(boolean b){
		bnOK.setDisable(!b);
	}

	// traitement des événements (les callbacks)
		
	private void gererBoutons(ActionEvent e) {
		if (e.getSource()==bnOK) {
			float volume = Float.parseFloat(txtVolume.getText());
			boolean prioritaire = cbPrioritaire.isSelected();
			String type = listeTypeCourrier.getSelectionModel().getSelectedItem().toLowerCase();
			String prenom = txtPrenom.getText();
			String nom = txtNom.getText();
			String nomRue = txtNomRue.getText();
			
			Principale.modifierCourrier(new Courrier(volume, prioritaire, type, new Destinataire(prenom, nom, nomRue)));
			this.close();
		}
		if (e.getSource()==bnAnnuler) {
			this.close();
		}
	}
	
	private void gererZones(Event e) {
		if (txtVolume.getText().equals("") || txtPrenom.getText().equals("") || txtNom.getText().equals("") || txtNomRue.getText().equals("") || txtAdresse.getText().equals("") || listeTypeCourrier.getSelectionModel().getSelectedItem() == null) { // vérifie que tous les champs sont remplis
			this.activeBoutonOK(false);
		}
		else {
			this.activeBoutonOK(true);
		}
	}
}
