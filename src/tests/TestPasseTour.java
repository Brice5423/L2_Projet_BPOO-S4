package tests;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.exception.*;
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

        Partie partie = null;
        try {
            partie = new Partie(listJoueur, pioche);
        } catch (Exception e) {
            System.out.println(e);
        }

        ArrayList<Carte> tas = new ArrayList<Carte>();

        Carte neufRouge = new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.NEUF);
        tas.add(neufRouge);
        partie.setTas(tas);

        Carte rougePasse = new CartePasserTour(ECarteCouleur.ROUGE);
        Carte bleuNeuf = new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.NEUF);

        alice.donnerCarte(rougePasse);
        alice.donnerCarte(bleuNeuf);
        alice.donnerCarte(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.QUATRE));

        Carte vertSix = new CarteBasique(ECarteCouleur.VERT, ECarteValeur.SIX);

        bob.donnerCarte(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.SIX));
        bob.donnerCarte(vertSix);
        bob.donnerCarte(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.SEPT));

        Carte vertPasse = new CartePasserTour(ECarteCouleur.VERT);

        charles.donnerCarte(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.UN));
        charles.donnerCarte(vertPasse);
        charles.donnerCarte(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.UN));


        /* ***** ***** Debut test : Test de coups legaux avec des cartes passe ton tour ***** ***** */
        System.out.println("\tTest de coups legaux avec des cartes passe ton tour");

        /* ***** Bloc des premiers copie pour les tests ***** */
        Partie partieTest = null;
        try {
            partieTest = partie.copiePartie();
        } catch (Exception e) {
            System.out.println(e);
        }

        Joueur aliceTest = partieTest.getListJoueur().get(0);
        Joueur bobTest = partieTest.getListJoueur().get(1);
        Joueur charlesTest = partieTest.getListJoueur().get(2);
        /* ************************************************** */

        //1)Verifier qu'alice est bien le joueur courant :

        if (partieTest.joueurCourant().equals(aliceTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Bob mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        //2)Alice pose le "passe ton tour rouge"
        try {

            aliceTest.poserCarte(rougePasse);
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        //3)Alice finit son tour
        try {

            aliceTest.finTour();
            compteurTest.testOK();

        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }
        //4)Verifier que Charles est le joueur courant
        if (partieTest.joueurCourant().equals(charlesTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Charles mais " + partieTest.joueurCourant().getNom() + " -_-");
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
        try {
            charlesTest.poserCarte(vertPasse);
            System.out.println("Charles pose le vertPasse^^");
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        //7) charles finit son tour
        try {
            charlesTest.finTour();
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        //8) verifier que le joueur courant = Bob
        if (partieTest.joueurCourant().equals(bobTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Bob mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        // 9) Vérifier que la carte	au sommet du tas est le	« passe ton tour Vert »
        if (partieTest.carteAuDessusTas().equals(vertPasse)) {
            System.out.println("La carte de la pioche est le passe ton tour Vert ^^");
            compteurTest.testOK();
        } else {
            System.out.println("La carte de la pioche est le " + partieTest.carteAuDessusTas() + " -_-");
            compteurTest.testFaux();
        }

        //10) Bob pose le 6 vert
        try {
            bobTest.poserCarte(vertSix);
            System.out.println("Charles pose le 6 vert^^");
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        //11) Bob finit son tour
        try {
            bobTest.finTour();
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }
        //12) verifier que le joueur courant = Charles
        if (partieTest.joueurCourant().equals(charlesTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Charles mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        // 13) Vérifier que la carte	au sommet du tas est le	« 6 vert »
        if (partieTest.carteAuDessusTas().equals(vertSix)) {
            System.out.println("La carte de la pioche est le 6 vert ^^");
            compteurTest.testOK();
        } else {
            System.out.println("La carte de la pioche est le " + partieTest.carteAuDessusTas() + " -_-");
            compteurTest.testFaux();
        }








        /* ***** ***** Fin du test, renvoie si test ok et affiche le résultat global ***** ***** */
        return compteurTest.afficheResultatsTest();
    }
}

