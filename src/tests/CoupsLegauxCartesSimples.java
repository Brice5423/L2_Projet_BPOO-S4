package tests;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
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
        System.out.println("\n\t\t\t----- Tests coups légaux avec des cartes simples -----");

        /* ***** ***** initialisation des compteurs du test ***** ***** */
        CompteurTest compteurTest = new CompteurTest("Coup légaux avec des cartes simples");


        /* ***** ***** Initialiser le fichier test ***** ***** */

        ArrayList<Carte> pioche = new ArrayList<Carte>();

        pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.ZERO));
        pioche.add(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.CINQ));
        pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.DEUX));
        pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.QUATRE));
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
        Carte rougeUn = new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.UN);

        alice.donnerCarte(vertDeux);
        alice.donnerCarte(jauneSix);
        alice.donnerCarte(rougeUn);

        Carte bleuDeux = new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.DEUX);
        Carte jauneQuatre = new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.QUATRE);
        Carte rougeNeuf = new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.NEUF);

        bob.donnerCarte(bleuDeux);
        bob.donnerCarte(jauneQuatre);
        bob.donnerCarte(rougeNeuf);

        charles.donnerCarte(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.NEUF));
        charles.donnerCarte(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.SEPT));
        charles.donnerCarte(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.ZERO));


        /* ***** ***** Debut test : Bob joue une carte de couleur différente, mais de même valeur. ***** ***** */
        testCarteBonneCouleur(compteurTest, alice, bob, partie, tas, vertDeux, jauneSix, rougeUn);

        /* ***** ***** Debut test : Alice joue une carte de la bonne couleur. ***** ***** */
        testCarteMemeValeur(compteurTest, bob, charles, partie, tas, bleuDeux, jauneQuatre, rougeNeuf);


        /* ***** ***** Fin du test, renvoie si test ok et affiche le résultat global ***** ***** */
        return compteurTest.afficheResultatsTest();
    }

    private static void testCarteMemeValeur(CompteurTest compteurTest, Joueur bob, Joueur charles, Partie partie, ArrayList<Carte> tas, Carte bleuDeux, Carte jauneQuatre, Carte rougeNeuf) {
        System.out.println("\tBob joue une carte de couleur différente, mais de même valeur.");

        // 1) Vérifier	que	Bob	possède	bien 3 cartes
        if (bob.nbCarteEnMain() == 3) {
            compteurTest.testOK();
        } else {
            System.out.println("Bob possède " + bob.nbCarteEnMain() + " cartes -_-");
            compteurTest.testFaux();
        }

        // 2) Bob pose le « 2 bleu »,
        try {
            bob.poserCarte(bleuDeux);
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 3) Vérifier que	Bob	possède	bien 2 cartes,
        if (bob.nbCarteEnMain() == 2) {
            compteurTest.testOK();
        } else {
            System.out.println("Bob possède " + bob.nbCarteEnMain() + " cartes -_-");
            compteurTest.testFaux();
        }

        // 4) Vérifier que	les cartes de Bob sont le « 4 jaune » et le	« 9	rouge »,
        if (bob.nbCarteEnMain() == 2 && bob.getMainDuJoueur().get(0).equals(jauneQuatre) && bob.getMainDuJoueur().get(1).equals(rougeNeuf)) {
            compteurTest.testOK();
        } else {
            System.out.println("Bob n'a pas 2 cartes ou le 4 jaune et le 9 rouge -_-");
            compteurTest.testFaux();
        }

        // 5) Vérifier que la carte au sommet du tas est le « 2 Bleu »,
        if (partie.carteAuDessusTas() == bleuDeux) {
            compteurTest.testOK();
        } else {
            System.out.println("La carte au sommet du tas est " + partie.carteAuDessusTas() + " -_-");
            compteurTest.testFaux();
        }

        // 6) Vérifier	que le nombre de cartes du tas est 3,
        if (tas.size() == 3) {
            compteurTest.testOK();
        } else {
            System.out.println("Le nombre de carte dans le tas est de " + tas.size() + " -_-");
            compteurTest.testFaux();
        }

        // 7) Bob finit le tour,
        try {
            bob.finTour();
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 8) Vérifier que le joueur courant est Charles.
        if (partie.joueurCourant().equals(charles)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Charles mais " + partie.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }
    }

    private static void testCarteBonneCouleur(CompteurTest compteurTest, Joueur alice, Joueur bob, Partie partie, ArrayList<Carte> tas, Carte vertDeux, Carte jauneSix, Carte rougeUn) {
        System.out.println("\tAlice joue une carte de la bonne couleur.");

        // 1) Vérifier que le joueur courant est bien Alice,
        if (partie.joueurCourant().equals(alice)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Alice mais " + partie.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        // 2) Vérifier qu'Alice possède bien 3 cartes,
        if (alice.nbCarteEnMain() == 3) {
            compteurTest.testOK();
        } else {
            System.out.println("Alice possède " + alice.nbCarteEnMain() + " cartes -_-");
            compteurTest.testFaux();
        }

        // 3) Alice joue le « 2 Vert »,
        try {
            alice.poserCarte(vertDeux);
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 4) Vérifier qu'Alice possède bien 2 cartes,
        if (alice.nbCarteEnMain() == 2) {
            compteurTest.testOK();
        } else {
            System.out.println("Alice possède " + alice.nbCarteEnMain() + " cartes -_-");
            compteurTest.testFaux();
        }

        // 5) Vérifier que les cartes d’Alice sont le « 6 jaune » et le « 1 rouge »
        if (alice.nbCarteEnMain() == 2 && alice.getMainDuJoueur().get(0).equals(jauneSix) && alice.getMainDuJoueur().get(1).equals(rougeUn)) {
            compteurTest.testOK();
        } else {
            System.out.println("Alice n'a pas 2 cartes ou le 6 jaune et le 1 rouge -_-");
            compteurTest.testFaux();
        }

        // 6) Vérifier que la carte au sommet du tas est le « 2 Vert »
        if (partie.carteAuDessusTas() == vertDeux) {
            compteurTest.testOK();
        } else {
            System.out.println("La carte au sommet du tas est " + partie.carteAuDessusTas() + " -_-");
            compteurTest.testFaux();
        }

        // 7) Vérifier que le nombre de cartes du tas est 2,
        if (tas.size() == 2) {
            compteurTest.testOK();
        } else {
            System.out.println("Le nombre de carte dans le tas est de " + tas.size() + " -_-");
            compteurTest.testFaux();
        }

        // 8) Alice finit le tour
        try {
            alice.finTour();
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
        }

        // 9) Vérifier que le joueur courant est Bob.
        if (partie.joueurCourant().equals(bob)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant est " + partie.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }
    }
}
