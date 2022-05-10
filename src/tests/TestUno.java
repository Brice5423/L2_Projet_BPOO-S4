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


        /* ***** ***** Debut test : Test lorsqu’Alice dit « Uno ! » au bon moment ***** ***** */
        System.out.println("\tTest lorsqu’Alice dit « Uno ! » au bon moment");

        /* ***** Bloc des premiers copie pour les tests ***** */
        Partie partieTest = partie.copiePartie();

        Joueur aliceTest = partieTest.getListJoueur().get(0);
        Joueur bobTest = partieTest.getListJoueur().get(1);
        Joueur charlesTest = partieTest.getListJoueur().get(2);
        /* ************************************************** */

        // 1) Vérifier qu’Alice	a bien 2 cartes
        if (aliceTest.nbCarteEnMain() == 2) {
            System.out.println("Alice a 2 cartes ^^");
            compteurTest.testOK();
        } else {
            System.out.println("Alice a " + aliceTest.nbCarteEnMain() + "-_-");
            compteurTest.testFaux();
        }

        // 2) Alice	pose le	« 2	Vert »
        try {
            aliceTest.poserCarte(vertDeux);
        } catch (JoueurCarteIllegalException | JoueurJoueMultipleException e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 3) Alice	dit	« Uno ! »
        aliceTest.ditUNO();

        // 4) Alice	finit	son	tour
        try {
            aliceTest.finTour();
        } catch (JoueurOublieDireUnoException e) {
            System.out.println(e);
            compteurTest.testFaux();

            /*int tailleTas = partieTest.getTas().size();
            Carte carteJouer = partieTest.getTas().remove(tailleTas - 1);

            System.out.println("Le joueur " + aliceTest.getNom() + " récupère ça carte : " + carteJouer);
            aliceTest.donnerCarte(carteJouer);
            aliceTest.punition();*/
        }

        // 5) Vérifier qu’Alice n’a plus qu’une	seule carte
        if (aliceTest.nbCarteEnMain() == 1) {
            System.out.println("Alice a 1 cartes ^^");
            compteurTest.testOK();
        } else {
            System.out.println("Alice a " + aliceTest.nbCarteEnMain() + "-_-");
            compteurTest.testFaux();
        }

        // 6) Vérifier que la carte au sommet du tas est le « 2 Vert »
        if (partieTest.carteAuDessusTas().equals(vertDeux)) {
            System.out.println("La carte de la pioche est le 2 vert ^^");
            compteurTest.testOK();
        } else {
            System.out.println("La carte de la pioche est le " + partieTest.carteAuDessusTas() + " -_-");
            compteurTest.testFaux();
        }

        // 7) Vérifier que le joueur courant est Bob
        if (partieTest.joueurCourant().equals(bobTest)) {
            System.out.println("Le joueur courant est bien Bob ^^");
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Bob mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }


        /* ***** ***** Debut test : Test lorsqu’Alice oubli de dire « Uno ! » ***** ***** */
        System.out.println("\n\tTest lorsqu’Alice oubli de dire « Uno ! »");

        partieTest = partie.copiePartie();

        aliceTest = partieTest.getListJoueur().get(0);
        bobTest = partieTest.getListJoueur().get(1);
        charlesTest = partieTest.getListJoueur().get(2);

        // 1) Alice	pose le « 2 Vert »
        try {
            aliceTest.poserCarte(vertDeux);
        } catch (JoueurCarteIllegalException | JoueurJoueMultipleException e) {
            System.out.println(e);
        }

        // 2) Alice	finit son tour
        try {
            aliceTest.finTour();
            compteurTest.testFaux();
        } catch (JoueurOublieDireUnoException e) {
            // 3) Punir	Alice
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

        // 4) Vérifier qu’Alice	a maintenant 4 cartes
        if (aliceTest.nbCarteEnMain() == 4) {
            System.out.println("Alice a 4 cartes ^^");
            compteurTest.testOK();
        } else {
            System.out.println("Alice a " + aliceTest.nbCarteEnMain() + "cartes -_-");
            compteurTest.testFaux();
        }

        // 5) Vérifier que la carte	au sommet du tas est le	« 8	Vert »
        if (partieTest.carteAuDessusTas().equals(vertHuit)) {
            System.out.println("La carte de la pioche est le 2 vert ^^");
            compteurTest.testOK();
        } else {
            System.out.println("La carte de la pioche est le " + partieTest.carteAuDessusTas() + " -_-");
            compteurTest.testFaux();
        }

        // 6) Vérifier que le joueur courant est Bob
        if (partieTest.joueurCourant().equals(bobTest)) {
            System.out.println("Le joueur courant est bien Bob ^^");
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Bob mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        /* ***** ***** Debut test : Test lorsque Bob dit « Uno ! » quand ce n’est pas son tour ***** ***** */
        System.out.println("\n\tTest lorsque Bob dit « Uno ! » quand ce n’est pas son tour");
        partieTest = partie.copiePartie();

        aliceTest = partieTest.getListJoueur().get(0);
        bobTest = partieTest.getListJoueur().get(1);
        charlesTest = partieTest.getListJoueur().get(2);

        // 1) Vérifier qu'Alice	est	le joueur courant
        if (partieTest.joueurCourant().equals(aliceTest)) {
            System.out.println("Le joueur courant est bien Alice ^^");
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Alice mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        // 2) Bob dit « Uno ! »   &   3) Punir Bob
        bobTest.ditUNO();

        // 4) Vérifier que Bob a maintenant	4 cartes
        if (bobTest.nbCarteEnMain() == 4) {
            System.out.println("Bob a 4 cartes ^^");
            compteurTest.testOK();
        } else {
            System.out.println("Bob a " + bobTest.nbCarteEnMain() + "cartes -_-");
            compteurTest.testFaux();
        }

        // 5) Vérifier qu’Alice	est	toujours le	joueur courant
        if (partieTest.joueurCourant().equals(aliceTest)) {
            System.out.println("Le joueur courant est bien Alice ^^");
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Alice mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        // 6) Vérifier que la carte	au sommet du tas est le	« 8	Vert »
        if (partieTest.carteAuDessusTas().equals(vertHuit)) {
            System.out.println("La carte de la pioche est le 2 vert ^^");
            compteurTest.testOK();
        } else {
            System.out.println("La carte de la pioche est le " + partieTest.carteAuDessusTas() + " -_-");
            compteurTest.testFaux();
        }


        /* ***** ***** Fin du test, renvoie si test ok et affiche le résultat global ***** ***** */
        return compteurTest.afficheResultatsTest();
    }
}
