package tests;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.exception.*;
import home.metier.Joueur;
import home.metier.Partie;
import home.metier.carte.Carte;
import home.metier.carte.CarteBasique;
import tests.controleur.CompteurTest;

import java.util.ArrayList;

public class TestPunition {
    public static boolean executionDuTest() {
        System.out.println("\n\t\t----- Tests Punitions -----\n");


        /* ***** ***** initialisation des compteurs du test ***** ***** */
        CompteurTest compteurTest = new CompteurTest("Test Punition");

        /* ***** ***** Initialiser le fichier test ***** ***** */

        ArrayList<Carte> pioche = new ArrayList<Carte>();

        Carte rougeQuatre = new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.QUATRE);

        pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.ZERO));
        pioche.add(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.CINQ));
        pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.DEUX));
        pioche.add(rougeQuatre);
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

        tas.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.HUIT));
        partie.setTas(tas);

        Carte vertDeux = new CarteBasique(ECarteCouleur.VERT, ECarteValeur.DEUX);
        Carte jauneSix = new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.SIX);

        alice.donnerCarte(vertDeux);
        alice.donnerCarte(jauneSix);
        alice.donnerCarte(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.UN));

        Carte bleuDeux = new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.DEUX);

        bob.donnerCarte(bleuDeux);
        bob.donnerCarte(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.QUATRE));
        bob.donnerCarte(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.NEUF));

        Carte bleuSix = new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.SIX);
        Carte bleuSept = new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.SEPT);

        charles.donnerCarte(bleuSept);
        charles.donnerCarte(bleuSix);

        charles.donnerCarte(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.ZERO));


        /* ***** ***** Debut test : TestPunition ***** ***** */

        /* ***** Bloc des premiers copie pour les tests ***** */
        System.out.println("\n\tTest de la punition pour un coup illégal d’Alice (joueur courant)");
        Partie partieTest = partie.copiePartie();

        Joueur aliceTest = partieTest.getListJoueur().get(0);
        Joueur bobTest = partieTest.getListJoueur().get(1);
        Joueur charlesTest = partieTest.getListJoueur().get(2);
        /* ************************************************** */

        // 1er Test
        if (partie.joueurCourant().equals(aliceTest)) {
            System.out.println("Le joueur courant est bien Alice ^^");
            compteurTest.testOK();

        } else {
            System.out.println("Le joueur courant n'est pas Alice mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }


        try {
            aliceTest.poserCarte(jauneSix);

        } catch (Exception e) {
            System.out.println(e);
            aliceTest.punition();
        }


        try {
            aliceTest.finTour();
        } catch (JoueurOublieDireUnoException e) {
            System.out.println(e);
        }


        if (partieTest.joueurCourant().equals(bobTest)) {
            System.out.println("Le joueur courant est bien Bob ^^");
            compteurTest.testOK();

        } else {
            System.out.println("Le joueur courant n'est pas Bob mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        if (aliceTest.nbCarteEnMain() == 5 && aliceTest.getMainDuJoueur().get(3).equals(jauneSix) && aliceTest.getMainDuJoueur().get(4).equals(rougeQuatre)) {
            System.out.println("Alice a bien 5 cartes dont le 6 jaune et le 4 rouge ^^");
            compteurTest.testOK();

        } else {
            System.out.println("Alice n'a pas 5 cartes ou n'a pas les bonnes cartes de la pioche -_-");
            compteurTest.testFaux();
        }

        Carte cartePioche = partieTest.retirerCartePioche();

        if (cartePioche.equals(vertDeux)) {
            System.out.println("La carte de la pioche est le 2 vert ^^");
            compteurTest.testOK();

        } else {
            System.out.println("La carte de la pioche est le " + cartePioche + " -_-");
            compteurTest.testFaux();
        }

        //2e Test
        System.out.println("\n\tTest de la punition pour un coup illégal d’Alice (joueur courant)");
        partieTest = partie.copiePartie();

        aliceTest = partieTest.getListJoueur().get(0);
        bobTest = partieTest.getListJoueur().get(1);
        charlesTest = partieTest.getListJoueur().get(2);


        if (partieTest.joueurCourant().equals(aliceTest)) {
            System.out.println("Le joueur courant est bien Alice ^^");
            compteurTest.testOK();

        } else {
            System.out.println("Le joueur courant n'est pas Alice mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }


        try {
            bobTest.piocherCarte();
            compteurTest.testFaux();

        } catch (JoueurNonCourantException  e) {
            System.out.println(e);
            bobTest.punition();
            compteurTest.testOK();

        } catch (JoueurJoueMultipleException e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        if (partie.joueurCourant().equals(aliceTest)) {
            System.out.println("Le joueur courant est bien Alice ^^");
            compteurTest.testOK();

        } else {
            System.out.println("Le joueur courant n'est pas Alice mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        if (bobTest.nbCarteEnMain() == 5 && bobTest.getMainDuJoueur().get(3).equals(jauneSix) && bobTest.getMainDuJoueur().get(4).equals(rougeQuatre)) {
            System.out.println("Bob a bien 5 cartes dont le 6 jaune et le 4 rouge ^^");
            compteurTest.testOK();

        } else {
            System.out.println("Bob n'a pas 5 cartes ou n'a pas les bonnes cartes de la pioche -_-");
            compteurTest.testFaux();
        }


        if (cartePioche.equals(vertDeux)) {
            System.out.println("La carte de la pioche est le 2 vert ^^");
            compteurTest.testOK();

        } else {
            System.out.println("La carte de la pioche est le " + cartePioche + " -_-");
            compteurTest.testFaux();
        }


        /* ***** ***** Fin du test, renvoie si test ok et affiche le résultat global ***** ***** */
        return compteurTest.afficheResultatsTest();
    }
}


