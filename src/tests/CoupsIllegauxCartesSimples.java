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
     * <p>
     * 1) Alice pose le « 6 jaune »
     * 2) Vérifier dans le catch approprié qu'Alice possède toujours 3 cartes dont le « 6 Jaune »
     * ________________________________________________________________________________
     * Test d’un joueur qui pose deux cartes légales de suite
     * <p>
     * 1) Alice pose le « 2 Vert » et finit son tour
     * 2) Bob pose le « 2 Bleu » et finit son tour
     * 3) Charles pose le « 6 Bleu » (RAS, c’est correct, mais Charles ne finit pas le tour)
     * 4) Vérifier que Charles possède 2 cartes
     * 5) Charles pose le « 7 Bleu » (Carte légale, mais il a déjà posé…)
     * 6) Vérifier dans le catch approprié que Charles possède toujours 2 cartes dont le « 2 Bleu »
     * ________________________________________________________________________________
     * Test d’un joueur qui finit son tour sans rien faire
     * <p>
     * 1) Alice finit son tour
     * 2) Vérifier dans le catch approprié qu'Alice possède toujours 3 cartes
     * ________________________________________________________________________________
     * Test d’un joueur qui joue puis pioche
     * <p>
     * 1) Alice joue le « 2 Vert » (RAS, le coup est légal)
     * 2) Alice pioche
     * 3) Vérifier dans le catch approprié qu'Alice possède toujours 2 cartes
     * 4) Vérifier que la carte de la pioche est toujours le « 6 jaune »
     *
     * @return true si succès est à 100% sinon false
     */
    public static boolean executionDuTest() {
        System.out.println("\n\t\t----- Tests coups illégaux avec des cartes simples -----\n");

        /* ***** ***** initialisation des compteurs du test ***** ***** */
        CompteurTest compteurTest = new CompteurTest("Coups illégaux avec des cartes simples");


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


        /* ***** ***** Debut test : Test d’une carte illégale ***** ***** */
        TestCarteCouleurIllégale(compteurTest, partie, jauneSix);


        /* ***** ***** Debut test : Test d’un joueur qui pose deux cartes légales de suite ***** ***** */
        JouerDeuxFoisDAffile(compteurTest, partie, vertDeux, bleuDeux, bleuSix, bleuSept);


        /* ***** ***** Debut test : Test d’un joueur qui finit son tour sans rien faire ***** ***** */
        FinirTourSansJouer(compteurTest, partie);


        /* ***** ***** Debut test : Test d’un joueur qui joue puis pioche ***** ***** */
        JouePuisPioche(compteurTest, partie, vertDeux, jauneSix);


        /* ***** ***** Fin du test, renvoie si test ok et affiche le résultat global ***** ***** */
        return compteurTest.afficheResultatsTest();
    }

    private static void TestCarteCouleurIllégale(CompteurTest compteurTest, Partie partie, Carte jauneSix) {
        System.out.println("\tTest d’une carte illégale");

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


        // 1) Alice	pose le	« 6	jaune »
        try {
            aliceTest.poserCarte(jauneSix);
            compteurTest.testFaux();
        } catch (Exception e) {
            // 2) Vérifier dans	le catch approprié qu'Alice	possède	toujours 3 cartes dont le « 6 Jaune »
            if (aliceTest.getMainDuJoueur().size() == 3) {
                compteurTest.testOK();
            } else {
                System.out.println("Alice a pas 3 carte mais " + aliceTest.getMainDuJoueur().size() + " -_-");
                compteurTest.testFaux();
            }
        }
    }

    private static void JouerDeuxFoisDAffile(CompteurTest compteurTest, Partie partie, Carte vertDeux, Carte bleuDeux, Carte bleuSix, Carte bleuSept) {
        System.out.println("\n\tTest d’un joueur qui pose deux cartes légales de suite");

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


        // 1) Alice	pose le	« 2	Vert » et finit son tour
        try {
            aliceTest.poserCarte(vertDeux);
            aliceTest.finTour();
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 2) Bob pose le « 2 Bleu » et	finit son tour
        try {
            bobTest.poserCarte(bleuDeux);
            bobTest.finTour();
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 3) Charles pose le « 6 Bleu » (RAS, c’est correct, mais Charles ne finit pas le	tour)
        try {
            charlesTest.poserCarte(bleuSix);
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 4) Vérifier que Charles possède 2 cartes
        if (bobTest.nbCarteEnMain() == 2) {
            compteurTest.testOK();
        } else {
            System.out.println("Charles possède " + charlesTest.nbCarteEnMain() + " cartes -_-");
            compteurTest.testFaux();
        }

        // 5) Charles pose le « 7 Bleu » (Carte	légale,	mais il	a déjà posé…)
        try {
            charlesTest.poserCarte(bleuSept);
            compteurTest.testFaux();
        } catch (JoueurJoueMultipleException e) {
            // 6) Vérifier dans	le catch approprié que Charles possède toujours	2 cartes TODO dont le « 7 Bleu »
            if (charlesTest.nbCarteEnMain() == 2 && charlesTest.getMainDuJoueur().get(0).equals(bleuSept)) {
                compteurTest.testOK();
            } else {
                System.out.println("la main de charles:"+charlesTest.getMainDuJoueur());
                System.out.println("Charles possède " +charlesTest.nbCarteEnMain() + " cartes ou n'a pas la carte << 7 Bleu >> -_-");
                compteurTest.testFaux();
            }
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }
    }

    private static void FinirTourSansJouer(CompteurTest compteurTest, Partie partie) {
        System.out.println("\n\tTest d’un joueur qui finit son tour sans rien faire");

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


        // 1) Alice	finit son tour
        try {
            aliceTest.finTour();
            System.out.println("Alice à réussi à faire un \"finTour\" alors qu'elle n'a pas joué -_-");
            compteurTest.testFaux();
        } catch (JoueurJouePasException e) {
            // 2) Vérifier dans	le catch approprié qu'Alice	possède	toujours 3 cartes
            if (aliceTest.nbCarteEnMain() == 3) {
                compteurTest.testOK();
            } else {
                System.out.println("Alice a" + aliceTest.nbCarteEnMain() + " cartes en main -_-");
                compteurTest.testFaux();
            }

        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }
    }

    private static void JouePuisPioche(CompteurTest compteurTest, Partie partie, Carte vertDeux, Carte jauneSix) {
        System.out.println("\n\tTest d’un joueur qui joue puis pioche");

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

        // 1) Alice	joue le	« 2	Vert » (RAS, le	coup est légal)
        try {
            aliceTest.poserCarte(vertDeux);
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 2) Alice	pioche
        try {
            aliceTest.piocherCarte();
            compteurTest.testFaux();
        } catch (JoueurJoueMultipleException e) {
            // 3) Vérifier dans le catch approprié qu'Alice possède toujours 2 cartes
            if (aliceTest.nbCarteEnMain() == 2) {
                Carte cartePioche = partieTest.retirerCartePioche();

                // 4) Vérifier que la carte	de la pioche est toujours le « 6 jaune »
                if (cartePioche.equals(jauneSix)) {
                    compteurTest.testOK();
                } else {
                    System.out.println("La carte au sommet du tas est " + cartePioche + " -_-");
                    compteurTest.testFaux();
                }
            } else {
                System.out.println("Alice possède " + aliceTest.nbCarteEnMain() + " cartes -_-");
                compteurTest.testFaux();
            }
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }
    }
}
