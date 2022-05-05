import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
/**
 * Fenêtre itinéraire.
 */
public class FenetreItineraire extends Stage { 
	/**Code Direction.**/
	protected int codeDir=1;
	
	/**L'itinéraire du facteur.**/
	protected Itineraire iti;

	/**Nom de la rue à la prochaine étape.**/
	protected String nomRue = getRue(0);
	
	/**Numéro de la rue à la prochaine étape.**/
	protected String numRue = "Rue n°";
	
	/**Nombre de rues de l'itinéraire.**/
	private int nbRue;
	
	public int n=0;
	
	// Les composants de la fenêtre
	private Button 				bnFermer 					= new Button("Fermer");
	private Line				ligne						= new Line(0, 45.5, 180, 45.5);
	private Image 				flechedroite 				= new Image("file:/../images/flechedroite.png", 65, 40, true, true);
	private ImageView 			flechedroiteView 			= new ImageView(flechedroite);
	private Image 				flechegauche 				= new Image("file:/../images/flechegauche.png", 65, 40, true, true);
	private ImageView 			flechegaucheView 			= new ImageView(flechegauche);
	private Image 				gpsdroite 					= new Image("file:/../images/gpsdroite.png", 250, 250, true, true);
	private ImageView 			gpsdroiteView 				= new ImageView(gpsdroite);
	private Image 				gpsgauche 					= new Image("file:/../images/gpsgauche.png", 250, 250, true, true);
	private ImageView 			gpsgaucheView 				= new ImageView(gpsgauche);
	private Image 				gpsavant 					= new Image("file:/../images/gpsavant.png", 250, 250, true, true);
	private ImageView 			gpsavantView 				= new ImageView(gpsavant);
	private Image 				gpsarriere 					= new Image("file:/../images/gpsarriere.png", 250, 250, true, true);
	private ImageView 			gpsarriereView 				= new ImageView(gpsarriere);
	private Label				message						= new Label("L'itinéraire n'est pas encore créé.");
	private Label				direction1					= new Label("Direction de la prochaine étape");
	private Label				direction2					= new Label("Direction de la prochaine étape");
	private Label				direction3					= new Label("Direction de la prochaine étape");
	private Label				direction4					= new Label("Direction de la prochaine étape");
	private Label				msgdroite					= new Label("Tournez à droite");
	private Label				msggauche					= new Label("Tournez à gauche");
	private Label				msgavant					= new Label("Continuez tout droit");
	private Label				msgarriere					= new Label("Faites demi-tour dès que possible");
	private Label				rue							= new Label(numRue+(n+1)+"\n"+nomRue);
	private Button 				bnDroite 					= new Button();
	private Button 				bnGauche 					= new Button();
	private Rectangle 		    fondEcran					= new Rectangle();
	private double				tailleX						= 450;
	private double				tailleY						= 550;
	
	
	// Les layouts de la fenêtre
	HBox zoneFermer = new HBox();
	VBox vbrue = new VBox();
	VBox droite = new VBox();
	VBox gauche = new VBox();
	VBox vbdroite = new VBox();
	VBox vbgauche = new VBox();
	VBox vbavant = new VBox();
	VBox vbarriere = new VBox();
	BorderPane bp = new BorderPane();
	StackPane root = new StackPane();
	/**
	 * Crée la fenêtre itinéraire.
	 */
	public FenetreItineraire(){ 
		this.setTitle("Itinéraire par étape"); 
		this.setResizable(false);
		this.setMinWidth(tailleX);
		this.setMinHeight(tailleY);
		
		iti = new Itineraire(15,2.5);
		nbRue = iti.getNbRue();
		
		bnFermer.setOnAction(e -> gererFermer(e));
		bnDroite.setOnAction(e -> gererFleches(e));
		bnGauche.setOnAction(e -> gererFleches(e));
		
		Scene laScene = new Scene(creerContenu());
		this.setScene(laScene);
		this.sizeToScene();
	}
	
	/**
	 * Crée le contenu de la fenêtre.
	 */
	private Parent creerContenu(){
		message.setFont(Font.font(15));
		message.setUnderline(true);
		message.setTextFill(Color.RED);
		direction1.setFont(Font.font("Cambria",FontWeight.BOLD,17));
		direction1.setTextFill(Color.BLUEVIOLET);
		direction1.setUnderline(true);
		direction2.setFont(Font.font("Cambria",FontWeight.BOLD,17));
		direction2.setTextFill(Color.BLUEVIOLET);
		direction2.setUnderline(true);
		direction3.setFont(Font.font("Cambria",FontWeight.BOLD,17));
		direction3.setTextFill(Color.BLUEVIOLET);
		direction3.setUnderline(true);
		direction4.setFont(Font.font("Cambria",FontWeight.BOLD,17));
		direction4.setTextFill(Color.BLUEVIOLET);
		direction4.setUnderline(true);
		msgdroite.setFont(Font.font("Cambria",FontWeight.BOLD,15));
		msggauche.setFont(Font.font("Cambria",FontWeight.BOLD,15));
		msgavant.setFont(Font.font("Cambria",FontWeight.BOLD,15));
		msgarriere.setFont(Font.font("Cambria",FontWeight.BOLD,15));
		
		vbdroite.getChildren().addAll(direction1,msgdroite,gpsdroiteView);
		vbdroite.setAlignment(Pos.CENTER);
		vbdroite.setSpacing(10);
		vbgauche.getChildren().addAll(direction2,msggauche,gpsgaucheView);
		vbgauche.setAlignment(Pos.CENTER);
		vbgauche.setSpacing(10);
		vbavant.getChildren().addAll(direction3,msgavant,gpsavantView);
		vbavant.setAlignment(Pos.CENTER);
		vbavant.setSpacing(10);
		vbarriere.getChildren().addAll(direction4,msgarriere,gpsarriereView);
		vbarriere.setAlignment(Pos.CENTER);
		vbarriere.setSpacing(10);
		
		switch(codeDir) {
		case 1: bp.setCenter(vbdroite);
			break;
		case 2: bp.setCenter(vbgauche);
			break;
		case 3: bp.setCenter(vbavant);
			break;
		case 4: bp.setCenter(vbarriere);
			break;
		default: bp.setCenter(message);
			break;
		}
		
		bnFermer.setPrefWidth(80);
		fondEcran.setWidth(tailleX);
		fondEcran.setHeight(tailleY);
		fondEcran.setFill(Color.BEIGE);
		
		bnDroite.setGraphic(flechedroiteView);
		bnGauche.setGraphic(flechegaucheView);
		bnGauche.setDisable(true);
				
		zoneFermer.setSpacing(40);
		zoneFermer.setAlignment(Pos.CENTER);
		HBox.setMargin(bnFermer, new Insets(10));
		zoneFermer.getChildren().addAll(bnFermer);
		droite.getChildren().addAll(bnDroite);
		droite.setAlignment(Pos.CENTER);
		gauche.getChildren().addAll(bnGauche);
		gauche.setAlignment(Pos.CENTER);
		
		rue.setFont(Font.font(16));
		vbrue.getChildren().addAll(rue,ligne);
		vbrue.setAlignment(Pos.CENTER);
		vbrue.setSpacing(20);
		
		if(this.codeDir<5 && this.codeDir>0) {
			bp.setTop(vbrue);
		}
		bp.setRight(droite);
		bp.setLeft(gauche);
		bp.setBottom(zoneFermer);
		
		root.getChildren().addAll(fondEcran,bp);

		return root;
	}
	
	/** Retourne la rue à l'indice i.
	 * @param i
	 * @return String.**/
	protected String getRue(int i) {
		iti = new Itineraire(15,2.5);
		String rue = iti.getIt(i);
		return rue;
	}
	
	/**
	 * Initialise la fêtre lorsqu'on clique sur le bouton de droite.
	 */
	public void initDroite(){
		n++;
		nomRue = getRue(n);
		rue.setText(numRue+(n+1)+"\n"+nomRue);
		codeDir = iti.getCodeRue(n+1);
		switch(codeDir) {
		case 1: bp.setCenter(vbdroite);
			break;
		case 2: bp.setCenter(vbgauche);
			break;
		case 3: bp.setCenter(vbavant);
			break;
		case 4: bp.setCenter(vbarriere);
			break;
		default: bp.setCenter(message);
			break;
		}
		bnGauche.setDisable(false);
		if(n==nbRue-1) {
			bnDroite.setDisable(true);
		}
	}
	/**
	 * Initialise la fêtre lorsqu'on clique sur le bouton de gauche.
	 */
	public void initGauche(){
		n--;
		nomRue = getRue(n);
		rue.setText(numRue+(n+1)+"\n"+nomRue);
		codeDir = iti.getCodeRue(n+1);
		switch(codeDir) {
		case 1: bp.setCenter(vbdroite);
			break;
		case 2: bp.setCenter(vbgauche);
			break;
		case 3: bp.setCenter(vbavant);
			break;
		case 4: bp.setCenter(vbarriere);
			break;
		default: bp.setCenter(message);
			break;
		}
		bnDroite.setDisable(false);
		if(n==0) {
			bnGauche.setDisable(true);
		}
	}
	
	/**
	 * Gestion du clique sur les flèches.
	 * @param e
	 */
	private void gererFleches(ActionEvent e) {
		if(e.getSource()==bnDroite && n<nbRue-1) {
			initDroite();
		}
		else if(e.getSource()==bnGauche && n>0) {
			initGauche();
		}
	}
	
	/**
	 * Gestion de la fermeture de la fenêtre.
	 * @param e
	 */
	private void gererFermer(ActionEvent e) {
		Principale.ouvrirAccueil(this);
	}

	/**
	 * Affecte le code de direction.
	 * @param codeDir
	 */
	public void setCodeDir(int codeDir) {
		this.codeDir = codeDir;
	}
	
	/**
	 * Affecte le nom de la rue.
	 * @param rue
	 */
	public void setNomRue(String rue) {
		nomRue = rue;
	}
}
