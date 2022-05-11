package tests;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.exception.JoueurCarteIllegalException;
import home.exception.JoueurJoueMultipleException;
import home.exception.JoueurOublieDireUnoException;
import home.metier.Joueur;
import home.metier.Partie;
import home.metier.carte.Carte;
import home.metier.carte.CarteBasique;
import home.metier.carte.carteAEffetType.CartePasserTour;
import tests.controleur.CompteurTest;

import java.util.ArrayList;

public class TestPasseTour {
    public static boolean executionDuTest() {
        System.out.println("\n\t\t----- Tests Passe Tour -----\n");

        /* ***** ***** initialisation des compteurs du test ***** ***** */
        CompteurTest compteurTest = new CompteurTest("Test Passe Tour");


        /* ***** ***** Initialiser le fichier test ***** ***** */

        ArrayList<Carte> pioche = new ArrayList<Carte>();

        pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.DEUX));
        pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.QUATRE));
        pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.DEUX));
        pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.HUIT));
        pioche.add(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.ZERO));

        ArrayList<Joueur> listJoueur = new ArrayList<Joueur>();

        Joueur alice = new Joueur("Alice");
        Joueur bob = new Joueur("Bob");
        Joueur charles = new Joueur("Charles");

        listJoueur.add(alice);
        listJoueur.add(bob);
        listJoueur.add(charles);

        Partie partie = new Partie(listJoueur, pioche);

        ArrayList<Carte> tas = new ArrayList<Carte>();

        Carte neufRouge = new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.NEUF);
        tas.add(neufRouge);
        partie.setTas(tas);

        Carte rougePasse = new CartePasserTour(ECarteCouleur.ROUGE);
        Carte bleuNeuf = new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.NEUF);

        alice.donnerCarte(rougePasse);
        alice.donnerCarte(bleuNeuf);
        alice.donnerCarte(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.QUATRE));


        bob.donnerCarte(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.SIX));
        bob.donnerCarte(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.SIX));
        bob.donnerCarte(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.SEPT));

        Carte vertPasse = new CartePasserTour(ECarteCouleur.VERT);

        charles.donnerCarte(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.UN));
        charles.donnerCarte(vertPasse);
        charles.donnerCarte(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.UN));


        /* ***** ***** Debut test : Test de coups legaux avec des cartes passe ton tour ***** ***** */
        System.out.println("\tTest de coups legaux avec des cartes passe ton tour");

        /* ***** Bloc des premiers copie pour les tests ***** */
        Partie partieTest = partie.copiePartie();

        Joueur aliceTest = partieTest.getListJoueur().get(0);
        Joueur bobTest = partieTest.getListJoueur().get(1);
        Joueur charlesTest = partieTest.getListJoueur().get(2);
        /* ************************************************** */

        //1)Verifier qu'alice est bien le joueur courant :

        if (partieTest.joueurCourant().equals(aliceTest)) {
            System.out.println("Le joueur courant est bien Alice ^^");
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Bob mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        //2)Alice pose le "passe ton tour rouge"
        try {
            aliceTest.poserCarte(rougePasse);
            compteurTest.testOK();
        } catch (JoueurCarteIllegalException | JoueurJoueMultipleException e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        //3)Alice finit son tour
        try {
            aliceTest.finTour();
        } catch (JoueurOublieDireUnoException e) {
            System.out.println(e);
            compteurTest.testFaux();
        }
        //4)Verifier que Charles est le joueur courant
        if (partieTest.joueurCourant().equals(charlesTest)) {
            System.out.println("Le joueur courant est bien Charles ^^");
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Bob mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        // 5) Vérifier que la carte	au sommet du tas est le	« passe ton tour Rouge »
        if (partieTest.carteAuDessusTas().equals(rougePasse)) {
            System.out.println("La carte de la pioche est le passe ton tour Rouge ^^");
            compteurTest.testOK();
        } else {
            System.out.println("La carte de la pioche est le " + partieTest.carteAuDessusTas() + " -_-");
            compteurTest.testFaux();
        }

        //6) Charles pose le passe ton tour vert



        /* ***** ***** Fin du test, renvoie si test ok et affiche le résultat global ***** ***** */
        return compteurTest.afficheResultatsTest();
    }
}

