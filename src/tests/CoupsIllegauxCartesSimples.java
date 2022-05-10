package tests;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.metier.Joueur;
import home.metier.Partie;
import home.metier.carte.Carte;
import home.metier.carte.CarteBasique;
import tests.controleur.CompteurTest;

import java.util.ArrayList;

public class CoupsIllegauxCartesSimples {
    /**
     * Tous ces tests vont lancer des exceptions.
     * Toutes ces anomalies sont (en principe !) sanctionnées par 2 cartes de punitions,
     * et fin du tour automatique si c’est le joueur courant qui commet la faute.
     * C’est dans le catch de l’exception que doivent être réalisées ou non ces deux actions.
     * Mais pour l’instant, il n’est pas utile de gérer la « punition ».
     * Nous le ferons dans d’autres tests par la suite (une chose après l’autre).
     * Pour chacun des tests de cette partie,
     * il faut réinitialiser la partie pour se retrouver dans les conditions des tests précédents
     * ________________________________________________________________________________
     * Test d’une carte illégale
     *
     * 1) Alice pose le « 6 jaune »
     * 2) Vérifier dans le catch approprié qu'Alice possède toujours 3 cartes dont le « 6 Jaune »
     * ________________________________________________________________________________
     * Test d’un joueur qui pose deux cartes légales de suite
     *
     * 1) Alice pose le « 2 Vert » et finit son tour
     * 2) Bob pose le « 2 Bleu » et finit son tour
     * 3) Charles pose le « 6 Bleu » (RAS, c’est correct, mais Charles ne finit pas le tour)
     * 4) Vérifier que Charles possède 2 cartes
     * 5) Charles pose le « 7 Bleu » (Carte légale, mais il a déjà posé…)
     * 6) Vérifier dans le catch approprié que Charles possède toujours 2 cartes dont le « 2 Bleu »
     * ________________________________________________________________________________
     * Test d’un joueur qui finit son tour sans rien faire
     *
     * 1) Alice finit son tour
     * 2) Vérifier dans le catch approprié qu'Alice possède toujours 3 cartes
     * ________________________________________________________________________________
     * Test d’un joueur qui joue puis pioche
     *
     * 1) Alice joue le « 2 Vert » (RAS, le coup est légal)
     * 2) Alice pioche
     * 3) Vérifier dans le catch approprié qu'Alice possède toujours 2 cartes
     * 4) Vérifier que la carte de la pioche est toujours le « 6 jaune »
     *
     * @return true si succès est à 100% sinon false
     */
    public static boolean executionDuTest() {
        System.out.println("\n\t\t----- Tests coups légaux avec des cartes simples -----\n");


        /* ***** ***** initialisation des compteurs du test ***** ***** */
        CompteurTest compteurTest = new CompteurTest("coups illégaux avec des cartes simples");

        /* ***** ***** Initialiser le fichier test ***** ***** */

        ArrayList<Carte> pioche = new ArrayList<Carte>();

        pioche.add(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.SIX));
        pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.QUATRE));
        pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.DEUX));
        pioche.add(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.CINQ));
        pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.ZERO));

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

        alice.piocherCarte(vertDeux);
        alice.piocherCarte(jauneSix);
        alice.piocherCarte(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.UN));

        Carte bleuDeux = new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.DEUX);

        bob.piocherCarte(bleuDeux);
        bob.piocherCarte(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.QUATRE));
        bob.piocherCarte(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.NEUF));

        Carte bleuSix = new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.SIX);
        Carte bleuSept = new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.SEPT);

        charles.piocherCarte(bleuSept);
        charles.piocherCarte(bleuSix);

        charles.piocherCarte(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.ZERO));

        /* ***** ***** Bloc des premiers copie pour les tests ***** ***** */

        Partie partieTest = partie.copiePartie();

        Joueur aliceTest = partieTest.getListJoueur().get(0);
        Joueur bobTest = partieTest.getListJoueur().get(1);
        Joueur charlesTest = partieTest.getListJoueur().get(2);

        /* ***** ***** Debut test : Test d’une carte illégale ***** ***** */

        aliceTest.poserCarte(jauneSix);

        //TODO : verifier qu'alice a tjr sa main : elle n'a pas le droit de poser du jaune sur du bleu !

        if (aliceTest.getMainDuJoueur().size() == 3) {
            System.out.println("Alice à 3 carte ^^");
        } else {
            System.out.println("Alice n'à pas 3 carte mais " + aliceTest.getMainDuJoueur().size() + " -_-");
        }

        partieTest = partie.copiePartie();

        aliceTest = partieTest.getListJoueur().get(0);
        bobTest = partieTest.getListJoueur().get(1);
        charlesTest = partieTest.getListJoueur().get(2);

        /* ***** ***** Debut test : Test d’un joueur qui pose deux cartes légales de suite ***** ***** */

        aliceTest.poserCarte(vertDeux);
        aliceTest.finTour();

        bobTest.poserCarte(bleuDeux);
        bobTest.finTour();

        charlesTest.poserCarte(bleuSix);

        if (bobTest.nbCarteEnMain() == 2) {
            System.out.println("Charles possède bien 2 cartes ^^");
            compteurTest.testOK();
        } else {
            System.out.println("Charles possède " + charlesTest.nbCarteEnMain() + " cartes -_-");
            compteurTest.testFaux();
        }

        charlesTest.poserCarte(bleuSept);

        // TODO : verifier dans le catch que charles a tjr 2 cartes : Il n'a pas le droit de jouer 2 fois de suite

        partieTest = partie.copiePartie();

        aliceTest = partieTest.getListJoueur().get(0);
        bobTest = partieTest.getListJoueur().get(1);
        charlesTest = partieTest.getListJoueur().get(2);


        /* ***** ***** Debut test : Test d’un joueur qui finit son tour sans rien faire ***** ***** */

        aliceTest.finTour();
        // TODO : verifier qu'alice possede toujours 3 cartes : Elle n'a pas le droit de pas jouer

        partieTest = partie.copiePartie();

        aliceTest = partieTest.getListJoueur().get(0);
        bobTest = partieTest.getListJoueur().get(1);
        charlesTest = partieTest.getListJoueur().get(2);

        /* ***** ***** Debut test : Test d’un joueur qui joue puis pioche ***** ***** */

        aliceTest.poserCarte(vertDeux);
        // aliceTest.pioche();
        // TODO : verifier qu'alice a toujours 2 cartes : elle n'a pas le droit de piocher si elle a deja joué !
        // TODO : verifier que la premiere carte de la pioche n'a pas changé : 6 Jaune

        return compteurTest.afficheResultatsTest();
    }
}
