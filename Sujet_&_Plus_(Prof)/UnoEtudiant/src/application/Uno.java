package application;
	
import java.util.ArrayList;

/**
 * Pour iMac : 
 * Le chemin des modules FX sont là :
 * /Users/dordal/cdt-master/javafx-sdk-11.0.2/lib
 * Il faut ajouter les arguments :
 *  --module-path /Users/dordal/cdt-master/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml
 * et décocher la case ...Xstart.... 
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class Uno extends Application {


	private static final int H_CANVAS = 130;
	private static final int L_CANVAS = 400;
	private static final int L_CARTE = 80;
	private static final int ECART = 30;
	private Canvas canSabot;

	private ArrayList<String> listeCartes = new ArrayList<String>(); // Devrait disparaître en fonction des vos classes

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			

			listeCartes.add("/carte_1_Bleu.png");
			listeCartes.add("/carte_Passe_Jaune.png");
			
			VBox joueurNord = initJoueur("Yann");
			root.setTop(joueurNord);
			
			VBox joueurOuest = initJoueur("Camille");
			root.setRight(joueurOuest);

			VBox joueurSud = initJoueur("Isabelle");
			root.setBottom(joueurSud);
			
			VBox joueurEst = initJoueur("Charlotte");
			root.setLeft(joueurEst);
			

			root.setCenter(initSabot());
			
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	private VBox initJoueur(String nom) {
		VBox vBox = new VBox();
		vBox.setAlignment(Pos.CENTER);
		
		Label nomNord = initLabelNom(nom);
		Canvas canMainNord = initMain(listeCartes/* paramètres ?*/);
		HBox unoNord = initBoutonUno(canMainNord /* et d'autres paramètres ? */);
		vBox.getChildren().addAll(nomNord,canMainNord,unoNord);
		return vBox;
	}


	private HBox initBoutonUno(Canvas canMain /* et d'autres paramètres ? */) {
		/* Cette partie est sans doute incomplète. Il y a sans doute d'autres actions à 
		 * prévoir que piocher et dire uno !
		 */
		
		
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		Button	boutonUno = new Button("Uno !");

		boutonUno.setOnAction(select -> {
			System.out.println("Le joueur dit Uno !");
		});
		
		Button	boutonPioche = new Button("Pioche");

		boutonPioche.setOnAction(select -> {
			System.out.println("Le joueur pioche");
		});

		hBox.getChildren().addAll(boutonUno,boutonPioche);
		
		return hBox;
	}


	private Label initLabelNom(String nom) {
		Label bidon = new Label("");
		bidon.setFont(new Font("Arial", 30));

		Label lNom = new Label(nom);
		lNom.setFont(new Font("Arial", 30));

		return lNom;
	}
	

	private Canvas initSabot() {
		
		canSabot = new Canvas();

		dessinerSabot();
		
		canSabot.setOnMouseClicked(clic -> { 
			System.out.println("Pioche!");
			/* j'ai prévu l'évènement mais personnellement je ne l'utilise pas. J'utilise le bouton
			 * prévu pour chaque joueur. Faites coimme vous voulez !
			 */
		});

		return canSabot;
	}	
	
	private void dessinerSabot() {
		Image sabot = new Image(getClass().getResourceAsStream("/Sabot.png"));
		Image dos = new Image(getClass().getResourceAsStream("/carte_dos.png"));
		canSabot.setWidth(sabot.getWidth());
		canSabot.setHeight(sabot.getHeight());

		/* normalement, il faut retourner la première carte de la pioche pour amorcer
		 * la manche. J'initialise cela en dur mais vous devrez changer cela en fonction
		 * de vos classes
		 */
		Image imageCarte = new Image(getClass().getResourceAsStream("/carte_6_Rouge.png"));
		
		canSabot.getGraphicsContext2D().drawImage(sabot,0,0);
		canSabot.getGraphicsContext2D().drawImage(imageCarte,25,20);
		canSabot.getGraphicsContext2D().drawImage(dos,124,20);
	}


	private Canvas initMain(ArrayList<String> liste) {
		Canvas canMain = new Canvas(L_CANVAS,H_CANVAS);

		dessinerMain(liste, canMain);
		
		
		canMain.setOnMouseClicked(clic -> { 
			int x = (int) clic.getX();
			int nbCartes = liste.size();
			int lMain = L_CARTE+((nbCartes-1)*ECART);
			int pad = (L_CANVAS-lMain) / 2;

			if (x>=pad && x<=pad+lMain) {
				int num = (int) ((x-pad) / ECART);
				num = Math.min(nbCartes-1, num);
				System.out.println("Le joueur a sélectionné la carte "+num);
				/* sûrement à compléter */
			}
		});
		
		return canMain;
	}


	private void dessinerMain(ArrayList<String> liste, Canvas canvas) {
		/* liste est une liste de chaines de car. Mais vous devriez sans doute utiliser
		 * vos propres classes, pas des String !
		 */
		
		
		canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		int nbCartes = liste.size();
		int lMain = L_CARTE+((nbCartes-1)*ECART);
		int pad = (L_CANVAS-lMain) / 2;
		
		for (int i=0; i<nbCartes; i++) {
			Image carte = new Image(getClass().getResourceAsStream(liste.get(i))); /* à adapter */
			canvas.getGraphicsContext2D().drawImage(carte,pad+i*ECART,0);
		}
	}
	



	public static void main(String[] args) {
		launch(args);
	}
}
