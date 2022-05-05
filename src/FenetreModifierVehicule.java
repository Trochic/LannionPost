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

/**
 * Fenêtre mofifier véhicule.
 */
public class FenetreModifierVehicule extends Stage {
	// les composants de la fenêtre
	private VBox 			racine				= new VBox();
	private GridPane 		zoneSaisie 			= new GridPane();
	private HBox 			zoneBoutons 		= new HBox(20);
	private Label 			lblTypeVehi			= new Label("Type de véhicule : *");
	private ComboBox<String>listeTypeVehi		= new ComboBox<String>();
	private Label 			lblMatricule		= new Label("Immatriculation : *");
	private TextField		txtMatricule		= new TextField();
	private Label 			lblCarburant		= new Label("Capacité de carburant (L) : *");
	private TextField		txtCarburant		= new TextField();
	private Label 			lblConsoCarbu		= new Label("Consommation de carburant (L/100 km) : *");
	private TextField		txtConsoCarbu		= new TextField();
	private Label 			lblVitMax			= new Label("Vitesse maximale (km/h) : *");
	private TextField		txtVitMax			= new TextField();
	private Label 			lblCapMax			= new Label("Capacité maximale (kg) : *");
	private TextField		txtCapMax			= new TextField();
	private CheckBox 		cbPermisA			= new CheckBox("Permis A nécessaire");
	private CheckBox 		cbPermisB			= new CheckBox("Permis B nécessaire");
	private CheckBox 		cbDPieton			= new CheckBox("Droit piéton");
	private Button 			bnOK				= new Button("OK");
	private Button 			bnAnnuler			= new Button("Annuler");
	private Label 			champs				= new Label("* champs obligatoires");
	
	// constructeur : initialisation de la fenêtre
	/**
	 * Crée la fenêtre mofifier véhicule.
	 */
	public FenetreModifierVehicule(){
		this.setTitle("Ajouter un véhicule");
		this.setResizable(false);
		this.setScene(new Scene(creerContenu()));
		this.sizeToScene();
	}
	
	// création du Scene graph
	/**
	 * Crée le contenu de la fenêtre.
	 */
	private Parent creerContenu() {
		this.bnOK.setPrefWidth(100);
		this.bnAnnuler.setPrefWidth(100);
		
		zoneSaisie.setHgap(10);
		zoneSaisie.setVgap(10);
		zoneSaisie.add(lblTypeVehi,   0,  0);
		zoneSaisie.add(listeTypeVehi, 1,  0);
		zoneSaisie.add(lblMatricule,  0,  1);
		zoneSaisie.add(txtMatricule,  1,  1);
		zoneSaisie.add(lblCarburant,  0,  2);
		zoneSaisie.add(txtCarburant,  1,  2);
		zoneSaisie.add(lblConsoCarbu, 0,  3);
		zoneSaisie.add(txtConsoCarbu, 1,  3);
		zoneSaisie.add(lblVitMax,     0,  4);
		zoneSaisie.add(txtVitMax,     1,  4); 
		zoneSaisie.add(lblCapMax,  	  0,  5);
		zoneSaisie.add(txtCapMax,     1,  5); 
		zoneSaisie.add(cbPermisA,     1,  6);
		zoneSaisie.add(cbPermisB,     1,  7);
		zoneSaisie.add(cbDPieton,     1,  8);
		zoneSaisie.add(champs,        0,  9);
					
		zoneBoutons.getChildren().addAll(bnAnnuler, bnOK);
		zoneBoutons.setAlignment(Pos.CENTER_RIGHT);
		
		racine.getChildren().addAll(zoneSaisie, zoneBoutons);
		VBox.setMargin(zoneSaisie, new Insets(20, 20, 20, 20));
		VBox.setMargin(zoneBoutons, new Insets(20, 20, 20, 20));

		// détection et traitement des événements
		bnOK.setOnAction( e -> gererBoutons(e));
		bnAnnuler.setOnAction( e -> gererBoutons(e));
		txtMatricule.setOnKeyReleased( e -> gererZones(e));
		txtConsoCarbu.setOnKeyReleased( e -> gererZones(e));
		txtVitMax.setOnKeyReleased( e -> gererZones(e));
		txtCapMax.setOnKeyReleased( e -> gererZones(e));
		
		return racine;
	}
	
	/**
	 * Initialisation de la fenêtre.
	 * @param v
	 */
	public void init(VehiculeMotorise v){
		this.listeTypeVehi.setItems(FXCollections.observableArrayList("Camionnette", "Quad", "Staby"));
		this.listeTypeVehi.setValue(v.getType());
		this.setMatricule(v.getMatricule());
		this.setCarburant(Double.toString(v.getCarburant()));
		this.setConsoCarbu(Double.toString(v.getConsoCarburant()));
		this.setVitMax(Double.toString(v.getVitesseMax()));
		this.setCapMax(Double.toString(v.getCapacite()));
		this.setPermisA(v.isBesoinPermisA());
		this.setPermisB(v.isBesoinPermisB());
		this.setDPieton(v.isDroitPieton());
		this.bnOK.setDisable(false);
	}
	
	public void setMatricule(String matricule){
		txtMatricule.setText(matricule);
	}
	
	public void setCarburant(String carbu){
		txtCarburant.setText(carbu);
	}
	
	public void setConsoCarbu(String conso){
		txtConsoCarbu.setText(conso);
	}
	
	public void setVitMax(String vitMax){
		txtVitMax.setText(vitMax);
	}
	
	public void setCapMax(String capMax){
		txtCapMax.setText(capMax);
	}
	
	public void setPermisA(Boolean b){
		cbPermisA.setSelected(b);
	}
	
	public void setPermisB(Boolean b){
		cbPermisB.setSelected(b);
	}
	
	public void setDPieton(Boolean b){
		cbDPieton.setSelected(b);
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
	 * Gestion des boutons.
	 * @param e
	 */
	private void gererBoutons(ActionEvent e) {
		if (e.getSource()==bnOK) {
			String matric = txtMatricule.getText();
			double carbu = Double.parseDouble(txtCarburant.getText());
			double consoCarbu = Double.parseDouble(txtConsoCarbu.getText());
			double vMax = Double.parseDouble(txtVitMax.getText());
			double capac = Double.parseDouble(txtCapMax.getText());
			boolean bPA = cbPermisA.isSelected();
			boolean bPB = cbPermisB.isSelected();
			boolean dPieton = cbDPieton.isSelected();
			
			switch(listeTypeVehi.getValue()) {
				case "Camionnette" :
					Principale.modifierVehicule(new Camionnette(vMax, capac, dPieton, matric, carbu, consoCarbu, bPA, bPB));
					break;
				case "Quad" :
					Principale.modifierVehicule(new Quad(vMax, capac, dPieton, matric, carbu, consoCarbu, bPA, bPB));
					break;
				case "Staby" :
					Principale.modifierVehicule(new Staby(vMax, capac, dPieton, matric, carbu, consoCarbu, bPA, bPB));
					break;
				default :
					break;
			}
			this.close();
		}
		if (e.getSource()==bnAnnuler) {
			this.close();
		}
	}
	/**
	 * Gestions des zones de saisie.
	 * @param e
	 */
	private void gererZones(Event e) {
		if (txtMatricule.getText().equals("") || txtCarburant.getText().equals("") || txtConsoCarbu.getText().equals("") || txtVitMax.getText().equals("") || txtCapMax.getText().equals("") || listeTypeVehi.getSelectionModel().getSelectedItem() == null) { // vérifie que tous les champs sont remplis
			this.activeBoutonOK(false);
		}
		else {
			this.activeBoutonOK(true);
		}
	}
}
