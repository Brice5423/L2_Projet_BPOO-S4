package application;

import home.exception.*;
import home.metier.Joueur;
import home.metier.Partie;
import home.metier.carte.Carte;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;


public class MainInterface extends Application {
    private static final int H_CANVAS = 130;
    private static final int L_CANVAS = 400;
    private static final int L_CARTE = 80;
    private static final int ECART = 30;
    private Canvas canSabot;

    private Partie partie;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            this.partie = new Partie();
            this.partie.genererPioche();
            this.partie.poserPremiereCarteDuTas();

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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private VBox initJoueur(String nom) throws PartieException {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);

        Joueur joueur = new Joueur(nom);
        this.partie.ajoutJoueur(joueur);
        joueur.recupererNCarte(7);

        Label nomNord = initLabelNom(nom);
        Canvas canMain = initMain(joueur);
        HBox boutonsUno = initBoutonUno(canMain, joueur);
        vBox.getChildren().addAll(nomNord, canMain, boutonsUno);

        return vBox;
    }

    private Label initLabelNom(String nom) {
        Label lNom = new Label(nom);
        lNom.setFont(new Font("Arial", 30));

        return lNom;
    }

    private HBox initBoutonUno(Canvas canMain, Joueur joueur) {
        /* TODO Voir si on a bien panser à tout
         * Cette partie est sans doute incomplète. Il y a sans doute d'autres actions à
         * prévoir que piocher et dire uno !
         */

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);

        Button boutonUno = new Button("Uno !");
        boutonUno.setOnAction(select -> {
            System.out.println("Le joueur " + joueur.getNom() + " dit Uno !");
            try {
                joueur.ditUNO();

            } catch (JoueurNonCourantException | JoueurJouePasException e) {
                System.out.println("\t" + e);
                try {
                    joueur.punition();

                } catch (Exception ex) {
                    System.out.println("\t\t" + ex);
                }

            } catch (Exception e) {
                System.out.println("\t" + e);
            }
            this.dessinerMain(canMain, joueur.getMainDuJoueur());
        });

        Button boutonPioche = new Button("Pioche");
        boutonPioche.setOnAction(select -> {
            System.out.println("Le joueur " + joueur.getNom() + " pioche");
            try {
                joueur.piocherCarte();

            } catch (JoueurNonCourantException | JoueurJoueMultipleException e) {
                System.out.println("\t" + e);
                try {
                    joueur.punition();

                } catch (Exception ex) {
                    System.out.println("\t\t" + ex);
                }

            } catch (Exception e) {
                System.out.println("\t" + e);
            }
            this.dessinerMain(canMain, joueur.getMainDuJoueur());
        });

        Button boutonEncaisseAttaque = new Button("Encaisse attaque");
        boutonEncaisseAttaque.setOnAction(select -> {
            System.out.println("Le joueur " + joueur.getNom() + " encaisse attaque");
            try {
                joueur.encaisseAttaque();

            } catch (JoueurNonCourantException e) {
                System.out.println("\t" + e);
                try {
                    joueur.punition();
                } catch (Exception ex) {
                    System.out.println("\t\t" + e);
                }

            } catch (Exception e) {
                System.out.println("\t" + e);
            }
            this.dessinerMain(canMain, joueur.getMainDuJoueur());
        });

        Button boutonFiniTour = new Button("Fini tour");
        boutonFiniTour.setOnAction(select -> {
            System.out.println("Le joueur " + joueur.getNom() + " fini son tour");
            try {
                joueur.finTour();

            } catch (JoueurOublieDireUnoException | JoueurJouePasException | JoueurNonCourantException e) {
                System.out.println("\t" + e);
                try {
                    joueur.punition();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } catch (Exception e) {
                System.out.println("\t" + e);
            }
            this.dessinerMain(canMain, joueur.getMainDuJoueur());
        });

        hBox.getChildren().addAll(boutonUno, boutonPioche, boutonEncaisseAttaque, boutonFiniTour);

        return hBox;
    }

    private Canvas initSabot() {
        canSabot = new Canvas();

        dessinerSabot();

        canSabot.setOnMouseClicked(clic -> {
            System.out.println("Le joueur courant est " + this.partie.joueurCourant().getNom() + " !");
            /* TODO Voir si on le fait ou pas
             * J'ai prévu l'évènement, mais personnellement je ne l'utilise pas.
             * J'utilise le bouton prévu pour chaque joueur. Faites comme vous voulez !
             * A la base c'est fait pour piocher met on le fait pas...
             */
        });

        return canSabot;
    }

    private void dessinerSabot() {
        Image sabot = new Image(getClass().getResourceAsStream("/Sabot.png"));
        Image dos = new Image(getClass().getResourceAsStream("/carte_dos.png"));
        canSabot.setWidth(sabot.getWidth());
        canSabot.setHeight(sabot.getHeight());

        Image imageCarte = new Image(getClass().getResourceAsStream(this.partie.carteAuDessusTas().getCheminVersImage()));

        canSabot.getGraphicsContext2D().drawImage(sabot, 0, 0);
        canSabot.getGraphicsContext2D().drawImage(imageCarte, 25, 20);
        canSabot.getGraphicsContext2D().drawImage(dos, 124, 20);
    }

    private Canvas initMain(Joueur joueur) {
        Canvas canMain = new Canvas(L_CANVAS, H_CANVAS);

        ArrayList<Carte> mainDuJoueur = joueur.getMainDuJoueur();
        dessinerMain(canMain, mainDuJoueur);

        canMain.setOnMouseClicked(clic -> {
            int x = (int) clic.getX();
            int nbCartes = mainDuJoueur.size();
            int lMain = L_CARTE + ((nbCartes - 1) * ECART);
            int pad = (L_CANVAS - lMain) / 2;

            if (x >= pad && x <= pad + lMain) {
                int num = (int) ((x - pad) / ECART);
                num = Math.min(nbCartes - 1, num);

                System.out.println("Le joueur " + joueur.getNom() + " a sélectionné la carte " + mainDuJoueur.get(num));
                try {
                    joueur.poserCarte(mainDuJoueur.get(num));

                } catch (JoueurOublieDireUnoException | JoueurJouePasException | JoueurNonCourantException e) {
                    System.out.println("\t" + e);
                    try {
                        joueur.punition();

                    } catch (Exception ex) {
                        System.out.println("\t\t" + e);
                    }

                } catch (JoueurMauvaiseCarteException | JoueurJoueMultipleException | JoueurCarteIllegalException e) {
                    System.out.println("\t" + e);
                    try {
                        ArrayList<Carte> tasPartie = this.partie.getTas();
                        joueur.donnerCarte(tasPartie.remove(tasPartie.size() - 1));
                        joueur.punition();

                    } catch (Exception ex) {
                        System.out.println("\t\t" + e);
                    }

                } catch (Exception e) {
                    System.out.println("\t" + e);
                }
            }
            this.dessinerMain(canMain, joueur.getMainDuJoueur());
            this.dessinerSabot();
        });

        return canMain;
    }

    private void dessinerMain(Canvas canvas, ArrayList<Carte> mainDuJoueur) {
        /* Liste est une liste de chaines de car. Mais vous devriez sans doute utiliser
         * vos propres classes, pas des Strings !
         */

        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int nbCartes = mainDuJoueur.size();
        int lMain = L_CARTE + ((nbCartes - 1) * ECART);
        int pad = (L_CANVAS - lMain) / 2;

        for (int i = 0; i < nbCartes; i++) {
            Image carte = new Image(getClass().getResourceAsStream(mainDuJoueur.get(i).getCheminVersImage())); /* à adapter */
            canvas.getGraphicsContext2D().drawImage(carte, pad + i * ECART, 0);
        }
    }
}
