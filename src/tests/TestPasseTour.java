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
        System.out.println("\n\t\t\t----- Tests Passe Tour -----");

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
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
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
        Carte bleuSept = new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.SEPT);

        bob.donnerCarte(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.SIX));
        bob.donnerCarte(vertSix);
        bob.donnerCarte(bleuSept);

        Carte vertPasse = new CartePasserTour(ECarteCouleur.VERT);
        Carte bleuUn = new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.UN);

        charles.donnerCarte(bleuUn);
        charles.donnerCarte(vertPasse);
        charles.donnerCarte(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.UN));


        /* ***** ***** 1er test : Test de coups légaux avec des cartes « Passe ton tour » ***** ***** */
        passeTourLegaux(compteurTest, partie, rougePasse, vertSix, vertPasse);

        /* ***** ***** 2e test : Test d’une carte simple illégale sur un « Passe ton tour » ***** ***** */
        carteSimpleSurPasseTourIllegale(compteurTest, charles, partie, rougePasse, bleuUn);

        /* ***** ***** 3e test : Test PasseTour illégale sur carte simple ***** ***** */
        passeTourSurCarteSimpleIllegale(compteurTest, charles, partie, bleuNeuf, bleuSept, vertPasse);


        /* ***** ***** Fin du test, renvoie si test ok et affiche le résultat global ***** ***** */
        return compteurTest.afficheResultatsTest();
    }

    private static void passeTourLegaux(CompteurTest compteurTest, Partie partie, Carte rougePasse, Carte vertSix, Carte vertPasse) {
        System.out.println("\tTest de coups légaux avec des cartes « Passe ton tour »");

        /* ***** Bloc des premiers copie pour les tests ***** */
        Partie partieTest = null;
        try {
            partieTest = partie.copiePartie();
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        Joueur aliceTest = partieTest.getListJoueur().get(0);
        Joueur bobTest = partieTest.getListJoueur().get(1);
        Joueur charlesTest = partieTest.getListJoueur().get(2);
        /* ************************************************** */

        // 1) Verifier qu'Alice est bien le joueur courant

        if (partieTest.joueurCourant().equals(aliceTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Bob mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        // 2) Alice pose le "passe ton tour rouge"
        try {
            aliceTest.poserCarte(rougePasse);
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 3) Alice finit son tour
        try {
            aliceTest.finTour();
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 4) Verifier que Charles est le joueur courant
        if (partieTest.joueurCourant().equals(charlesTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Charles mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        // 5) Vérifier que la carte	au sommet du tas est le	« passe ton tour Rouge »
        if (partieTest.carteAuDessusTas().equals(rougePasse)) {
            compteurTest.testOK();
        } else {
            System.out.println("La carte de la pioche est le " + partieTest.carteAuDessusTas() + " -_-");
            compteurTest.testFaux();
        }

        // 6) Charles pose le passe ton tour vert
        try {
            charlesTest.poserCarte(vertPasse);
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 7) charles finit son tour
        try {
            charlesTest.finTour();
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 8) verifier que le joueur courant = Bob
        if (partieTest.joueurCourant().equals(bobTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Bob mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        // 9) Vérifier que la carte	au sommet du tas est le	« passe ton tour Vert »
        if (partieTest.carteAuDessusTas().equals(vertPasse)) {
            compteurTest.testOK();
        } else {
            System.out.println("La carte de la pioche est le " + partieTest.carteAuDessusTas() + " -_-");
            compteurTest.testFaux();
        }

        // 10) Bob pose le 6 vert
        try {
            bobTest.poserCarte(vertSix);
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 11) Bob finit son tour
        try {
            bobTest.finTour();
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 12) verifier que le joueur courant = Charles
        if (partieTest.joueurCourant().equals(charlesTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Charles mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        // 13) Vérifier que la carte	au sommet du tas est le	« 6 vert »
        if (partieTest.carteAuDessusTas().equals(vertSix)) {
            compteurTest.testOK();
        } else {
            System.out.println("La carte de la pioche est le " + partieTest.carteAuDessusTas() + " -_-");
            compteurTest.testFaux();
        }
    }

    private static void carteSimpleSurPasseTourIllegale(CompteurTest compteurTest, Joueur charles, Partie partie, Carte rougePasse, Carte bleuUn) {
        System.out.println("\tTest d’une carte simple illégale sur un « Passe ton tour »");

        /* ***** Bloc des premiers copie pour les tests ***** */
        Partie partieTest = null;
        try {
            partieTest = partie.copiePartie();
            compteurTest.testOK();
        } catch (PartieProblemeNombreJoueurException e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        Joueur aliceTest = partieTest.getListJoueur().get(0);
        Joueur bobTest = partieTest.getListJoueur().get(1);
        Joueur charlesTest = partieTest.getListJoueur().get(2);
        /* ************************************************** */

        // 1) Verifier qu'Alice est le joueur courant
        if (partieTest.joueurCourant().equals(aliceTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Alice mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        // 2) Alice pose le passe tour rouge
        try {
            aliceTest.poserCarte(rougePasse);
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 3) Alice finit tour
        try {
            aliceTest.finTour();
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        //4) Verifier que Charles est le joueur courant
        if (partieTest.joueurCourant().equals(charlesTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Charles mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        // 5) Verifier que Charles possède bien 3 cartes
        if (charlesTest.nbCarteEnMain() == 3) {
            compteurTest.testOK();
        } else {
            System.out.println("Charles a  " + charles.nbCarteEnMain() + " cartes -_-");
            compteurTest.testFaux();
        }

        // 6) Charles pose le 1 bleu
        try {
            charlesTest.poserCarte(bleuUn);
            System.out.println("Charles à réussi à mettre un « 1 Bleu » sur « Passe ton tout rouge »");
            compteurTest.testFaux();
        } catch (Exception e) {
            try {
                charlesTest.setAvoirJouerSonTour(true);
                charlesTest.finTour();
                compteurTest.testOK();
            } catch (Exception ex) {
                System.out.println(ex);
                compteurTest.testFaux();
            }
        }

        // 7) Verifier que Charles possède bien 3 cartes
        if (charlesTest.nbCarteEnMain() == 3) {
            compteurTest.testOK();
        } else {
            System.out.println("Charles a  " + charles.nbCarteEnMain() + " cartes -_-");
            compteurTest.testFaux();
        }
    }

    private static void passeTourSurCarteSimpleIllegale(CompteurTest compteurTest, Joueur charles, Partie partie, Carte bleuNeuf, Carte bleuSept, Carte vertPasse) {
        System.out.println("\tTest PasseTour illégale sur carte simple");

        /* ***** Bloc des premiers copie pour les tests ***** */
        Partie partieTest = null;
        try {
            partieTest = partie.copiePartie();
            compteurTest.testOK();
        } catch (PartieProblemeNombreJoueurException e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        Joueur aliceTest = partieTest.getListJoueur().get(0);
        Joueur bobTest = partieTest.getListJoueur().get(1);
        Joueur charlesTest = partieTest.getListJoueur().get(2);
        /* ************************************************** */

        // 1) Verifier qu'Alice est le joueur courant

        if (partieTest.joueurCourant().equals(aliceTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Alice mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        // 2) Alice pose le passe tour rouge
        try {
            aliceTest.poserCarte(bleuNeuf);
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 3) Alice finit tour
        try {
            aliceTest.finTour();
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 4) Bob pose le 7bleu
        try {
            bobTest.poserCarte(bleuSept);
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 5) Bob finit son tour
        try {
            bobTest.finTour();
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 6) verifier que charles est le joueur courant
        if (partieTest.joueurCourant().equals(charlesTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Charles mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        // 7) verifier que charles possède 3 cartes
        if (charlesTest.nbCarteEnMain() == 3) {
            compteurTest.testOK();
        } else {
            System.out.println("Charles a  " + charles.nbCarteEnMain() + " cartes -_-");
            compteurTest.testFaux();
        }

        // 8) Charles pose le passeTour vert
        try {
            charlesTest.poserCarte(vertPasse);
            System.out.println("Charles à réussi à poser une « Passe ton tour vert » sur un « 7 Bleu » -_-");
            compteurTest.testFaux();
        } catch (Exception e) {
            try {
                charlesTest.setAvoirJouerSonTour(true);
                charlesTest.finTour();
                compteurTest.testOK();
            } catch (Exception ex) {
                System.out.println(ex);
                compteurTest.testFaux();
            }
        }

        //9) verifier que charles a tjr 3 cartes :
        if (charlesTest.nbCarteEnMain() == 3) {
            compteurTest.testOK();
        } else {
            System.out.println("Charles a  " + charles.nbCarteEnMain() + " cartes -_-");
            compteurTest.testFaux();
        }
    }
}

