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
import tests.controleur.CompteurTest;

import java.util.ArrayList;

public class TestUno {

    public static boolean executionDuTest() {


        System.out.println("\n\t\t----- Tests Uno -----\n");


        /* ***** ***** initialisation des compteurs du test ***** ***** */
        CompteurTest compteurTest = new CompteurTest("Test Uno");

        /* ***** ***** Initialiser le fichier test ***** ***** */

        ArrayList<Carte> pioche = new ArrayList<Carte>();


        pioche.add(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.TROIS));
        pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.ZERO));
        pioche.add(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.CINQ));
        pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.DEUX));
        pioche.add(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.SIX));

        ArrayList<Joueur> listJoueur = new ArrayList<Joueur>();

        Joueur alice = new Joueur("Alice");
        Joueur bob = new Joueur("Bob");
        Joueur charles = new Joueur("Charles");

        listJoueur.add(alice);
        listJoueur.add(bob);
        listJoueur.add(charles);

        Partie partie = new Partie(listJoueur, pioche);

        ArrayList<Carte> tas = new ArrayList<Carte>();

        Carte vertHuit = new CarteBasique(ECarteCouleur.VERT, ECarteValeur.HUIT);
        tas.add(vertHuit);
        partie.setTas(tas);

        Carte vertDeux = new CarteBasique(ECarteCouleur.VERT, ECarteValeur.DEUX);
        Carte jauneSix = new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.SIX);

        alice.donnerCarte(vertDeux);
        alice.donnerCarte(jauneSix);

        Carte bleuDeux = new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.DEUX);

        bob.donnerCarte(bleuDeux);
        bob.donnerCarte(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.QUATRE));


        Carte bleuSept = new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.SEPT);

        charles.donnerCarte(bleuSept);

        charles.donnerCarte(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.NEUF));

        System.out.println("\n\tTest lorsqu’Alice dit « Uno ! » au bon moment");
        Partie partieTest = partie.copiePartie();

        Joueur aliceTest = partieTest.getListJoueur().get(0);
        Joueur bobTest = partieTest.getListJoueur().get(1);
        Joueur charlesTest = partieTest.getListJoueur().get(2);


        if (aliceTest.nbCarteEnMain() == 2) {
            System.out.println("Alice a 2 cartes ^^");
            compteurTest.testOK();
        } else {
            System.out.println("Alice a " + aliceTest.nbCarteEnMain() + "-_-");
            compteurTest.testFaux();
        }

        try {
            aliceTest.poserCarte(vertDeux);

        } catch (JoueurCarteIllegalException | JoueurJoueMultipleException e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        aliceTest.ditUNO();

        try {
            aliceTest.finTour();
        } catch (JoueurOublieDireUnoException e) {
            System.out.println(e);

            int tailleTas = partieTest.getTas().size();
            Carte carteJouer = partieTest.getTas().remove(tailleTas - 1);

            System.out.println("Le joueur " + aliceTest.getNom() + " récupère ça carte : " + carteJouer);
            aliceTest.donnerCarte(carteJouer);
            aliceTest.punition();
            compteurTest.testOK();
        }

        if (aliceTest.nbCarteEnMain() == 1) {
            System.out.println("Alice a 1 cartes ^^");
            compteurTest.testOK();
        } else {
            System.out.println("Alice a " + aliceTest.nbCarteEnMain() + "-_-");
            compteurTest.testFaux();
        }


        if (partieTest.carteAuDessusTas().equals(vertDeux)) {
            System.out.println("La carte de la pioche est le 2 vert ^^");
            compteurTest.testOK();

        } else {
            System.out.println("La carte de la pioche est le " + partieTest.carteAuDessusTas() + " -_-");
            compteurTest.testFaux();
        }

        if (partieTest.joueurCourant().equals(bobTest)) {
            System.out.println("Le joueur courant est bien Bob ^^");
            compteurTest.testOK();

        } else {
            System.out.println("Le joueur courant n'est pas Bob mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }


        System.out.println("\n\tTest lorsqu’Alice oubli de dire « Uno ! »");
        partieTest = partie.copiePartie();

        aliceTest = partieTest.getListJoueur().get(0);
        bobTest = partieTest.getListJoueur().get(1);
        charlesTest = partieTest.getListJoueur().get(2);

        try {
            aliceTest.poserCarte(vertDeux);
        } catch (JoueurCarteIllegalException | JoueurJoueMultipleException e) {
            System.out.println(e);
        }

        try {
            aliceTest.finTour();
            compteurTest.testFaux();
        } catch (JoueurOublieDireUnoException e) {
            System.out.println(e);
            int tailleTas = partieTest.getTas().size();
            Carte carteJouer = partieTest.getTas().remove(tailleTas - 1);

            System.out.println("Le joueur " + aliceTest.getNom() + " récupère ça carte : " + carteJouer);
            aliceTest.donnerCarte(carteJouer);
            aliceTest.punition();
            compteurTest.testOK();

            try {
                aliceTest.finTour();
            } catch (JoueurOublieDireUnoException ex) {
                System.out.println(ex);
            }

        }


        if (aliceTest.nbCarteEnMain() == 4) {
            System.out.println("Alice a 4 cartes ^^");
            compteurTest.testOK();
        } else {
            System.out.println("Alice a " + aliceTest.nbCarteEnMain() + "cartes -_-");
            compteurTest.testFaux();
        }

        if (partieTest.carteAuDessusTas().equals(vertHuit)) {
            System.out.println("La carte de la pioche est le 2 vert ^^");
            compteurTest.testOK();

        } else {
            System.out.println("La carte de la pioche est le " + partieTest.carteAuDessusTas() + " -_-");
            compteurTest.testFaux();
        }

        if (partieTest.joueurCourant().equals(bobTest)) {
            System.out.println("Le joueur courant est bien Bob ^^");
            compteurTest.testOK();

        } else {
            System.out.println("Le joueur courant n'est pas Bob mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }


        return compteurTest.afficheResultatsTest();
    }
}
