package application;

import home.exception.*;
import home.metier.Joueur;
import home.metier.Partie;
import home.metier.carte.Carte;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class MainInterface extends Application {
    private static final int H_CANVAS = 130;
    private static final int L_CANVAS = 400;
    private static final int L_CARTE = 80;
    private static final int ECART = 30;

    private Canvas canSabot;
    private ArrayList<Canvas> listeCanMain;
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

            this.listeCanMain = new ArrayList<Canvas>();
            this.partie = new Partie();
            this.partie.genererPioche();
            this.partie.poserPremiereCarteDuTas();

            VBox joueurNord = this.initJoueur("Brice");
            root.setTop(joueurNord);

            VBox joueurEst = this.initJoueur("Alicia");
            root.setRight(joueurEst);

            VBox joueurSud = this.initJoueur("Nicolas");
            root.setBottom(joueurSud);

            VBox joueurOuest = this.initJoueur("Baptiste");
            root.setLeft(joueurOuest);

            root.setCenter(this.initSabot());

            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private VBox initJoueur(String nom) throws PartieProblemeNombreJoueurException, PartiePiocheVideException {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);

        Joueur joueur = new Joueur(nom);
        this.partie.ajoutJoueur(joueur);
        joueur.recupererNCarte(7);

        Label nomNord = this.initLabelNom(nom);
        Canvas canMain = this.initMain(joueur);
        this.listeCanMain.add(canMain);
        HBox boutonsUno = this.initBoutonUno(canMain, joueur);
        vBox.getChildren().addAll(nomNord, canMain, boutonsUno);

        return vBox;
    }

    private Label initLabelNom(String nom) {
        Label lNom = new Label(nom);

        lNom.setFont(new Font("Arial", 30));

        return lNom;
    }

    private Canvas initMain(Joueur joueur) {
        Canvas canMain = new Canvas(L_CANVAS, H_CANVAS);

        ArrayList<Carte> mainDuJoueur = joueur.getMainDuJoueur();
        this.dessinerMain(canMain, mainDuJoueur);

        canMain.setOnMouseClicked(clic -> {
            int x = (int) clic.getX();
            int nbCartes = mainDuJoueur.size();
            int lMain = L_CARTE + ((nbCartes - 1) * ECART);
            int pad = (L_CANVAS - lMain) / 2;

            if (x >= pad && x <= pad + lMain) {
                int num = (int) ((x - pad) / ECART);
                num = Math.min(nbCartes - 1, num);

                try {
                    System.out.println("Le joueur " + joueur.getNom() + " a sélectionné la carte " + mainDuJoueur.get(num));
                    joueur.poserCarte(mainDuJoueur.get(num));

                } catch (JoueurNonCourantException | JoueurJoueCarteAbsentMainException | JoueurCarteIllegalException
                         | JoueurJoueMultipleException e) {
                    try {
                        System.out.println("\t" + e + "\n\t\t-> punition");
                        joueur.punition();

                    } catch (PartiePiocheVideException ex) {
                        System.out.println("\t\t" + ex + "\n\t\t\t-> fait rien");
                    }

                } catch (JoueurEncaisserAttaqueException e) {
                    try {
                        System.out.println("\t" + e + "\n\t\t-> encaisseAttaque");
                        joueur.encaisseAttaque();

                    } catch (PartiePiocheVideException ex) {
                        System.out.println("\t\t" + ex + "\n\t\t\t-> fait rien");
                    }

                } catch (ExpertManquantException e) {
                    System.out.println("\t" + e + "\n\t\t-> fait rien : erreur");
                }
            }

            this.dessinerMain(canMain, joueur.getMainDuJoueur());
            this.dessinerSabot();

            if (mainDuJoueur.isEmpty()) {
                /* ***** Boite de dialogue, pour mettre fin au jeu ***** */
                // Créer la boite avec le dialogue
                Alert boitDialgue = new Alert(Alert.AlertType.CONFIRMATION);
                boitDialgue.setTitle("Victoire de " + joueur.getNom() + " !");
                boitDialgue.setHeaderText("Bravo le joueur " + joueur.getNom() + " a gagné la partie !");
                boitDialgue.setContentText("Appuyez sur un des boutons pour lancer une nouvelle partie ou pour en finir.");
                // Créer les boutons et met dans la boite
                ButtonType buttonNouvellePartie = new ButtonType("Nouvelle partie");
                ButtonType buttonFermeture = new ButtonType("Fermer partie");
                boitDialgue.getButtonTypes().setAll(buttonNouvellePartie, buttonFermeture);
                // Affiche la boite
                Optional<ButtonType> resultatBoitDialgue = boitDialgue.showAndWait();

                if (resultatBoitDialgue.get() == buttonNouvellePartie) {
                    // Met en place une nouvelle partie
                    this.partie.nouvellePartie();

                    // Redessine tous les éléments de la partie
                    this.dessinerSabot();
                    for (int i = 0; i < this.listeCanMain.size(); i++) {
                        this.dessinerMain(this.listeCanMain.get(i), this.partie.getListJoueur().get(i).getMainDuJoueur());
                    }

                } else {
                    // Ferme l'application quand la boite dialogue sera fermé.
                    Platform.exit();
                }
            }
        });

        return canMain;
    }

    private void dessinerMain(Canvas canvas, ArrayList<Carte> mainDuJoueur) {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int nbCartes = mainDuJoueur.size();
        int lMain = L_CARTE + ((nbCartes - 1) * ECART);
        int pad = (L_CANVAS - lMain) / 2;

        for (int i = 0; i < nbCartes; i++) {
            Image carte = new Image(Objects.requireNonNull(getClass().getResourceAsStream(mainDuJoueur.get(i).getCheminVersImage())));
            canvas.getGraphicsContext2D().drawImage(carte, pad + i * ECART, 0);
        }
    }

    private HBox initBoutonUno(Canvas canMain, Joueur joueur) {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);

        Button boutonPioche = new Button("Pioche");
        boutonPioche.setOnAction(select -> {
            if (this.partie.getNbCarteAPiocher() == 0) {
                try {
                    System.out.println("Le joueur " + joueur.getNom() + " pioche");
                    joueur.piocherCarte();

                } catch (JoueurNonCourantException | JoueurJoueMultipleException e) {
                    try {
                        System.out.println("\t" + e + "\n\t\t-> punition");
                        joueur.punition();

                    } catch (PartiePiocheVideException ex) {
                        System.out.println("\t\t" + ex + "\n\t\t\t-> fait rien");
                    }

                } catch (PartiePiocheVideException e) {
                    System.out.println("\t" + e + "\n\t\t-> fait rien");
                }

            } else {
                try {
                    System.out.println("Le joueur " + joueur.getNom() + " encaisse attaque (+2, +4...)");
                    joueur.encaisseAttaque();

                } catch (PartiePiocheVideException e) {
                    System.out.println("\t" + e + "\n\t\t-> fait rien");
                }
            }

            this.dessinerMain(canMain, joueur.getMainDuJoueur());
            this.dessinerSabot();
        });

        Button boutonDitUno = new Button("Uno !");
        boutonDitUno.setOnAction(select -> {
            try {
                System.out.println("Le joueur " + joueur.getNom() + " dit Uno !");
                joueur.ditUNO();

            } catch (JoueurNonCourantException | JoueurJouePasException | JoueurDireUnoTropTotException e) {
                try {
                    System.out.println("\t" + e + "\n\t\t-> punition");
                    joueur.punition();
                    this.dessinerSabot();

                } catch (PartiePiocheVideException ex) {
                    System.out.println("\t\t" + ex + "\n\t\t\t-> fait rien");
                }
            }

            this.dessinerMain(canMain, joueur.getMainDuJoueur());
        });

        Button boutonFiniTour = new Button("Fin tour");
        boutonFiniTour.setOnAction(select -> {
            try {
                System.out.println("Le joueur " + joueur.getNom() + " fini son tour");
                joueur.finTour();
                System.out.println(""); // Pour faire un saut de ligne dans les messages terminaux

            } catch (JoueurNonCourantException | JoueurJouePasException | JoueurOublieDireUnoException e) {
                try {
                    System.out.println("\t" + e + "\n\t\t-> punition");
                    joueur.punition();
                    this.dessinerSabot();

                    if ((e instanceof JoueurJouePasException) || (e instanceof JoueurOublieDireUnoException)) {
                        try {
                            System.out.println("\t\t-> finTour");
                            joueur.finTour();
                            System.out.println(""); // Pour faire un saut de ligne dans les messages terminaux

                        } catch (JoueurNonCourantException | JoueurJouePasException | JoueurOublieDireUnoException
                                 | JoueurEncaisserAttaqueException ex) {
                            System.out.println("\t\t" + ex + "\n\t\t\t-> fait rien");
                        }
                    }

                } catch (PartiePiocheVideException ex) {
                    System.out.println("\t\t" + ex + "\n\t\t\t-> fait rien");
                }

            } catch (JoueurEncaisserAttaqueException e) {
                System.out.println("\t" + e + "\n\t\t-> fait rien");
            }

            this.dessinerMain(canMain, joueur.getMainDuJoueur());
        });

        hBox.getChildren().addAll(boutonPioche, boutonDitUno, boutonFiniTour);

        return hBox;
    }

    private Canvas initSabot() {
        this.canSabot = new Canvas();

        this.dessinerSabot();

        this.canSabot.setOnMouseClicked(clic -> {
            System.out.println("=> Le joueur courant est " + this.partie.joueurCourant().getNom() + " !");
            /* TODO Voir si on le fait ou pas
             * J'ai prévu l'évènement, mais personnellement je ne l'utilise pas.
             * J'utilise le bouton prévu pour chaque joueur. Faites comme vous voulez !
             * A la base c'est fait pour piocher mais on le fait pas...
             */
        });

        return this.canSabot;
    }

    private void dessinerSabot() {
        Image sabot = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Sabot.png")));
        Image imageCarte = new Image(Objects.requireNonNull(getClass().getResourceAsStream(this.partie.carteAuDessusTas().getCheminVersImage())));
        Image dos = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/carte_dos.png")));

        this.canSabot.setWidth(sabot.getWidth());
        this.canSabot.setHeight(sabot.getHeight());

        this.canSabot.getGraphicsContext2D().drawImage(sabot, 0, 0);
        if (!this.partie.getTas().isEmpty()) {
            // Permet de voir si le tas est vide
            this.canSabot.getGraphicsContext2D().drawImage(imageCarte, 25, 20);
        }
        if (!this.partie.getPioche().isEmpty()) {
            // Permet de voir si la pioche est vide
            this.canSabot.getGraphicsContext2D().drawImage(dos, 124, 20);
        }
    }
}
