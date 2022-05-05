import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FenetreModifierItineraire extends Stage {
	// les composants de la fenêtre
	private VBox 			racine				= new VBox();
	private GridPane 		zoneSaisie 			= new GridPane();
	private HBox 			zoneBoutons 		= new HBox(20);
	private Label 			lblRue				= new Label("Rue 1 : *");
	private TextField		txtRue				= new TextField();
	private Button 			bnOK				= new Button("OK");
	private Button 			bnAnnuler			= new Button("Annuler");
	private Label 			champs				= new Label("* champs obligatoires");
	
	// constructeur : initialisation de la fenêtre
	public FenetreModifierItineraire(){
		this.setTitle("Ajouter un véhicule");
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
		zoneSaisie.add(lblRue, 0,  0);
		zoneSaisie.add(txtRue, 1,  0);
		zoneSaisie.add(champs, 0,  1);
					
		zoneBoutons.getChildren().addAll(bnAnnuler, bnOK);
		zoneBoutons.setAlignment(Pos.CENTER_RIGHT);
		
		racine.getChildren().addAll(zoneSaisie, zoneBoutons);
		VBox.setMargin(zoneSaisie, new Insets(20, 20, 20, 20));
		VBox.setMargin(zoneBoutons, new Insets(20, 20, 20, 20));

		// détection et traitement des événements
		bnOK.setOnAction( e -> gererBoutons(e));
		bnAnnuler.setOnAction( e -> gererBoutons(e));
		txtRue.setOnKeyReleased( e -> gererZones(e));
		
		return racine;
	}
	
	public void init(Itineraire i){
		this.setRue(i.getIt().get(0).getNom());
		this.bnOK.setDisable(false);
	}
	
	public void setRue(String rue){
		txtRue.setText(rue);
	}
	
	public void activeBoutonOK(boolean b){
		bnOK.setDisable(!b);
	}

	// traitement des événements (les callbacks)
		
	private void gererBoutons(ActionEvent e) {
		if (e.getSource()==bnOK) {
			ArrayList<Rue> liste = new ArrayList<Rue>();
			liste.add(new Rue(txtRue.getText(),false,false,50,2,23,0.9));
			Principale.modifierItineraire(new Itineraire(liste));
			this.close();
		}
		if (e.getSource()==bnAnnuler) {
			this.close();
		}
	}
	
	private void gererZones(Event e) {
		if (txtRue.getText().equals("")) { // vérifie que tous les champs sont remplis
			this.activeBoutonOK(false);
		}
		else {
			this.activeBoutonOK(true);
		}
	}
}
