package tests;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.exception.JoueurCarteIllegalException;
import home.exception.JoueurJoueMultipleException;
import home.exception.PartieException;
import home.metier.Joueur;
import home.metier.Partie;
import home.metier.carte.Carte;
import home.metier.carte.CarteBasique;
import tests.controleur.CompteurTest;

import java.util.ArrayList;

public class CoupsLegauxCartesSimples {
    /**
     * Alice joue une carte de la bonne couleur.
     * <p>
     * 1) Vérifier que le joueur courant est bien Alice,
     * 2) Vérifier qu'Alice possède bien 3 cartes,
     * 3) Alice joue le « 2 Vert »,
     * 4) Vérifier qu'Alice possède bien 2 cartes,
     * 5) Vérifier que les cartes d’Alice sont le « 6 jaune » et le « 1 rouge »,
     * 6) Vérifier que la carte au sommet du tas est le « 2 Vert »,
     * 7) Vérifier que le nombre de cartes du tas est 2,
     * 8) Alice finit le tour,
     * 9) Vérifier que le joueur courant est Bob.
     * _____________________________________________________________________________
     * Bob joue une carte de couleur différente, mais de même valeur.
     * <p>
     * 1) Vérifier	que	Bob	possède	bien 3 cartes,
     * 2) Bob pose le « 2 bleu »,
     * 3) Vérifier que	Bob	possède	bien 2 cartes,
     * 4) Vérifier que	les cartes de Bob sont le « 4 jaune » et le	« 9	rouge »,
     * 5) Vérifier que la carte au sommet du tas est le « 2 Bleu »,
     * 6) Vérifier	que le nombre de cartes du tas est 3,
     * 7) Bob finit le tour,
     * 8) Vérifier que le joueur courant est Charles.
     */
    public static boolean executionDuTest() {
        System.out.println("\n\t\t----- Tests coups légaux avec des cartes simples -----\n");

        /* ***** ***** initialisation des compteurs du test ***** ***** */
        CompteurTest compteurTest = new CompteurTest("Coup légaux avec des cartes simples");

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

        alice.donnerCarte(vertDeux);
        alice.donnerCarte(jauneSix);
        alice.donnerCarte(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.UN));

        Carte bleuDeux = new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.DEUX);

        bob.donnerCarte(bleuDeux);
        bob.donnerCarte(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.QUATRE));
        bob.donnerCarte(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.NEUF));

        charles.donnerCarte(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.NEUF));
        charles.donnerCarte(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.SEPT));
        charles.donnerCarte(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.ZERO));

        /* ***** ***** Debut test : Alice joue une carte de la bonne couleur. ***** ***** */

        System.out.println("\tAlice joue une carte de la bonne couleur.");

        // 1) Vérifier que le joueur courant est bien Alice,
        if (partie.joueurCourant().equals(alice)) {
            System.out.println("Le joueur courant est bien Alice ^^");
            compteurTest.testOK();

        } else {
            System.out.println("Le joueur courant n'est pas Alice mais " + partie.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        // 2) Vérifier qu'Alice possède bien 3 cartes,
        if (alice.nbCarteEnMain() == 3) {
            System.out.println("Alice possède bien 3 cartes ^^");
            compteurTest.testOK();
        } else {
            System.out.println("Alice possède " + alice.nbCarteEnMain() + " cartes -_-");
            compteurTest.testFaux();
        }

        // 3) Alice joue le « 2 Vert »,
        try {
            alice.poserCarte(vertDeux);

        } catch (JoueurJoueMultipleException | JoueurCarteIllegalException e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 4) Vérifier qu'Alice possède bien 2 cartes,
        if (alice.nbCarteEnMain() == 2) {
            System.out.println("Alice possède bien 2 cartes ^^");
            compteurTest.testOK();

        } else {
            System.out.println("Alice possède " + alice.nbCarteEnMain() + " cartes -_-");
            compteurTest.testFaux();
        }

        // 5) Vérifier que les cartes d’Alice sont le « 6 jaune » et le « 1 rouge »
        alice.afficheCarteEnMain();

        // 6) Vérifier que la carte au sommet du tas est le « 2 Vert »
        if (partie.carteAuDessusTas() == vertDeux) {
            System.out.println("La carte au sommet du tas est bien le 2 vert ^^");
            compteurTest.testOK();

        } else {
            System.out.println("La carte au sommet du tas est " + partie.carteAuDessusTas() + " -_-");
            compteurTest.testFaux();
        }

        // 7) Vérifier que le nombre de cartes du tas est 2,
        if (tas.size() == 2) {
            System.out.println("Le nombre de carte dans le tas est de 2 ^^");
            compteurTest.testOK();

        } else {
            System.out.println("Le nombre de carte dans le tas est de " + tas.size() + " -_-");
            compteurTest.testFaux();
        }

        // 8) Alice finit le tour
        alice.finTour();

        // 9) Vérifier que le joueur courant est Bob.
        if (partie.joueurCourant().equals(bob)) {
            System.out.println("Le joueur courant est Bob ^^");
            compteurTest.testOK();

        } else {
            System.out.println("Le joueur courant est " + partie.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }


        /* ***** ***** Debut test : Bob joue une carte de couleur différente, mais de même valeur. ***** ***** */

        System.out.println("\n\tBob joue une carte de couleur différente, mais de même valeur.");


        // 1) Vérifier	que	Bob	possède	bien 3 cartes
        if (bob.nbCarteEnMain() == 3) {
            System.out.println("Bob possède bien 3 cartes ^^");
            compteurTest.testOK();

        } else {
            System.out.println("Bob possède " + bob.nbCarteEnMain() + " cartes -_-");
            compteurTest.testFaux();
        }


        // 2) Bob pose le « 2 bleu »,
        try {
            bob.poserCarte(bleuDeux);

        } catch (JoueurJoueMultipleException | JoueurCarteIllegalException e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 3) Vérifier que	Bob	possède	bien 2 cartes,
        if (bob.nbCarteEnMain() == 2) {
            System.out.println("Bob possède bien 2 cartes ^^");
            compteurTest.testOK();

        } else {
            System.out.println("Bob possède " + bob.nbCarteEnMain() + " cartes -_-");
            compteurTest.testFaux();
        }

        // 4) Vérifier que	les cartes de Bob sont le « 4 jaune » et le	« 9	rouge »,
        bob.afficheCarteEnMain();

        // 5) Vérifier que la carte au sommet du tas est le « 2 Bleu »,
        if (partie.carteAuDessusTas() == bleuDeux) {
            System.out.println("La carte au sommet du tas est bien le 2 bleu ^^");
            compteurTest.testOK();

        } else {
            System.out.println("La carte au sommet du tas est " + partie.carteAuDessusTas() + " -_-");
            compteurTest.testFaux();
        }

        // 6) Vérifier	que le nombre de cartes du tas est 3,
        if (tas.size() == 3) {
            System.out.println("Le nombre de carte dans le tas est de 3 ^^");
            compteurTest.testOK();

        } else {
            System.out.println("Le nombre de carte dans le tas est de " + tas.size() + " -_-");
            compteurTest.testFaux();
        }

        // 7) Bob finit le tour,
        bob.finTour();

        // 8) Vérifier que le joueur courant est Charles.
        if (partie.joueurCourant().equals(charles)) {
            System.out.println("Le joueur courant est bien Charles ^^");
            compteurTest.testOK();

        } else {
            System.out.println("Le joueur courant n'est pas Charles mais " + partie.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        /* ***** ***** Fin du test, renvoie si test ok et affiche le résultat global ***** ***** */
        return compteurTest.afficheResultatsTest();
    }
}
