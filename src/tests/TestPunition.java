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
        System.out.println("\n\t\t\t----- Tests Punitions -----");

        /* ***** ***** initialisation des compteurs du test ***** ***** */
        CompteurTest compteurTest = new CompteurTest("Test de la punition");


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

        Partie partie = null;
        try {
            partie = new Partie(listJoueur, pioche);
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

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


        /* ***** ***** Debut test : Test de la punition pour un coup illégal d’Alice (joueur courant) ***** ***** */
        punitionJoueurCourant(compteurTest, rougeQuatre, partie, vertDeux, jauneSix);

        /* ***** ***** Debut test : Test d’une action de bob lorsque ce n’est pas son tour ***** ***** */
        punitionJoueurNonCourant(compteurTest, rougeQuatre, partie, vertDeux, jauneSix);


        /* ***** ***** Fin du test, renvoie si test ok et affiche le résultat global ***** ***** */
        return compteurTest.afficheResultatsTest();
    }

    private static void punitionJoueurCourant(CompteurTest compteurTest, Carte rougeQuatre, Partie partie, Carte vertDeux, Carte jauneSix) {
        System.out.println("\tTest de la punition pour un coup illégal d’Alice (joueur courant)");

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
        //Joueur charlesTest = partieTest.getListJoueur().get(2);
        /* ************************************************** */

        // 1) Vérifier que le joueur courant est bien Alice
        if (partie.joueurCourant().equals(aliceTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Alice mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        // 2) Alice	pose le	« 6	jaune »	(coup illégal)
        try {
            aliceTest.poserCarte(jauneSix);
            System.out.println("Alice à réussi à faire un coup illégal -_-");
            compteurTest.testFaux();
        } catch (Exception e) {
            // 3) Punir	Alice
            try {
                aliceTest.punition();
                aliceTest.finTour();
                compteurTest.testOK();
            } catch (Exception ex) {
                System.out.println(ex);
                compteurTest.testFaux();
            }
        }

        // 4) Vérifier que Bob est le joueur courant
        if (partieTest.joueurCourant().equals(bobTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Bob mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        // 5) Vérifier que Alice possède 5 cartes dont le « 6 jaune » et le	« 4	rouge »	(les 2 cartes de la	pioche)
        if (aliceTest.nbCarteEnMain() == 5 && aliceTest.getMainDuJoueur().get(3).equals(jauneSix) && aliceTest.getMainDuJoueur().get(4).equals(rougeQuatre)) {
            compteurTest.testOK();
        } else {
            System.out.println("Alice n'a pas 5 cartes ou n'a pas les bonnes cartes de la pioche -_-");
            compteurTest.testFaux();
        }

        Carte cartePioche = partieTest.retirerCartePioche();

        // 6) Vérifier que la prochaine	carte de la	pioche est le « 2 vert »
        if (cartePioche.equals(vertDeux)) {
            compteurTest.testOK();
        } else {
            System.out.println("La carte de la pioche est le " + cartePioche + " -_-");
            compteurTest.testFaux();
        }
    }

    private static void punitionJoueurNonCourant(CompteurTest compteurTest, Carte rougeQuatre, Partie partie, Carte vertDeux, Carte jauneSix) {
        System.out.println("\tTest d’une action de bob lorsque ce n’est pas son tour");

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
        //Joueur charlesTest = partieTest.getListJoueur().get(2);
        /* ************************************************** */

        // 1) Vérifier que le joueur courant est bien Alice
        if (partieTest.joueurCourant().equals(aliceTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Alice mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        // 2) Bob pioche (ce n’est pas son tour)
        try {
            bobTest.piocherCarte();
            System.out.println("Bob à réussi à piocher alors que ce n'est pas son tour -_-");
            compteurTest.testFaux();
        } catch (JoueurNonCourantException e) {
            // 3) Punir	Bob
            try {
                bobTest.punition();
                compteurTest.testOK();
            } catch (Exception ex) {
                System.out.println(ex);
                compteurTest.testFaux();
            }
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 4) Vérifier qu'Alice	est	toujours le	joueur courant
        if (partie.joueurCourant().equals(aliceTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Alice mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        // 5) Vérifier que Bob possède 5 cartes	dont le	« 6	jaune »	et le « 4 rouge » (le 2	cartes de la pioche)
        if (bobTest.nbCarteEnMain() == 5 && bobTest.getMainDuJoueur().get(3).equals(jauneSix) && bobTest.getMainDuJoueur().get(4).equals(rougeQuatre)) {
            compteurTest.testOK();
        } else {
            System.out.println("Bob n'a pas 5 cartes ou n'a pas les bonnes cartes de la pioche -_-");
            compteurTest.testFaux();
        }

        Carte cartePioche = partieTest.retirerCartePioche();

        // 6) Vérifier que la prochaine	carte de la	pioche est le « 2 vert »
        if (cartePioche.equals(vertDeux)) {
            compteurTest.testOK();
        } else {
            System.out.println("La carte de la pioche est le " + cartePioche + " -_-");
            compteurTest.testFaux();
        }
    }
}


