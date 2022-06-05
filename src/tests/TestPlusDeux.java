package tests;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.exception.*;
import home.metier.Joueur;
import home.metier.Partie;
import home.metier.carte.Carte;
import home.metier.carte.CarteBasique;
import home.metier.carte.carteAEffetType.CartePasserTour;
import home.metier.carte.carteAEffetType.CartePlusDeux;
import tests.controleur.CompteurTest;

import java.util.ArrayList;

public class TestPlusDeux {

    public static boolean executionDuTest() {
        System.out.println("\n\t\t\t----- Tests Plus Deux -----");

        /* ***** ***** initialisation des compteurs du test ***** ***** */
        CompteurTest compteurTest = new CompteurTest("Test Plus Deux");


        /* ***** ***** Initialiser le fichier test ***** ***** */

        ArrayList<Carte> pioche = new ArrayList<Carte>();

        pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.SIX));
        pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.CINQ));
        pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.TROIS));
        pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.DEUX));
        pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.UN));
        pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.ZERO));
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

        Carte neufVert = new CarteBasique(ECarteCouleur.VERT, ECarteValeur.NEUF);
        tas.add(neufVert);
        partie.setTas(tas);

        Carte plusDeuxVert = new CartePlusDeux(ECarteCouleur.VERT);


        alice.donnerCarte(plusDeuxVert);
        alice.donnerCarte(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.NEUF));
        alice.donnerCarte(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.QUATRE));


        bob.donnerCarte(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.SIX));
        bob.donnerCarte(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.SIX));
        bob.donnerCarte(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.SEPT));

        Carte plusDeuxVertC = new CartePlusDeux(ECarteCouleur.VERT);
        Carte unVert = new CarteBasique(ECarteCouleur.VERT, ECarteValeur.UN);

        charles.donnerCarte(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.UN));
        charles.donnerCarte(plusDeuxVertC);
        charles.donnerCarte(unVert);


        legalPlusDeux(compteurTest, partie, plusDeuxVert, unVert);
        Partie partieTest;
        Joueur aliceTest;
        Joueur charlesTest;
        Joueur bobTest;


        legalCumulPlusDeux(compteurTest, partie, plusDeuxVert, plusDeuxVertC);



        /* ***** ***** Fin du test, renvoie si test ok et affiche le résultat global ***** ***** */
        return compteurTest.afficheResultatsTest();
    }

    private static void legalCumulPlusDeux(CompteurTest compteurTest, Partie partie, Carte plusDeuxVert, Carte plusDeuxVertC) {
        Joueur charlesTest;
        Joueur bobTest;
        Partie partieTest;
        Joueur aliceTest;
        /* ***** ***** 2e test : Test de coups legaux + cumul sur un « Plus Deux » ***** ***** */
        System.out.println("\tTest de coups légaux + Cumul avec des cartes « Plus Deux »");
        /* ***** Bloc des premiers copie pour les tests ***** */
        partieTest = null;
        try {
            partieTest = partie.copiePartie();
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        aliceTest = partieTest.getListJoueur().get(0);
        bobTest = partieTest.getListJoueur().get(1);
        charlesTest = partieTest.getListJoueur().get(2);
        /* ************************************************** */
        //1)Verifier qu'alice est le joueur courant :
        if (partieTest.joueurCourant().equals(aliceTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Alice mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        //2)Alice Pioche une carte :
        try {
            aliceTest.piocherCarte();
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("2) Alice ne peut pas piocher -_-");
            compteurTest.testFaux();
        }

        //3) Alice finit son tour :
        try {
            aliceTest.finTour();
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("3) Alice n'a pas reussi a finir son tour -_-");
            compteurTest.testFaux();
        }

        //4) Verifier que Bob est bien le joueur courant :
        if (partieTest.joueurCourant().equals(bobTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Bob mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        //5) Bob pioche une carte :
        try {
            bobTest.piocherCarte();
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("5) Bob ne peut pas piocher -_-");
            compteurTest.testFaux();
        }

        //6) Bob finit son tour
        try {
            bobTest.finTour();
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("6) Bob n'a pas reussi a finir son tour -_-");
            compteurTest.testFaux();
        }

        //7) Verifier que Charles est le joueur courant :
        if (partieTest.joueurCourant().equals(charlesTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("7)Le joueur courant n'est pas Charles mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        //8) Charles pose +2
        try {
            charlesTest.poserCarte(plusDeuxVertC);
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("8)Charles na pas posé son +2 -_-");
            compteurTest.testFaux();
        }

        //9) Charles finit son tour
        try {
            charlesTest.finTour();
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("9) Charles n'a pas reussi a finir son tour -_-");
            compteurTest.testFaux();
        }

        //10) Alice joueur courant
        if (partieTest.joueurCourant().equals(aliceTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("10)Le joueur courant n'est pas Alice mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        //11) Alice pose le +2 vert
        try {
            aliceTest.poserCarte(plusDeuxVert);
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("11)Alice na pas posé son +2 -_-");
            compteurTest.testFaux();
        }

        //12) Alice finit son tour
        try {
            aliceTest.finTour();
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("12) Alice n'a pas reussi a finir son tour -_-");
            compteurTest.testFaux();
        }

        //13) Bob joueur courant
        if (partieTest.joueurCourant().equals(bobTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("13)Le joueur courant n'est pas Bob mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        //14) Bob a 4 cartes ?
        if (bobTest.nbCarteEnMain() == 4) {
            compteurTest.testOK();
        } else {
            System.out.println("14)Bob n'a pas 4 cartes mais"+bobTest.nbCarteEnMain()+"-_-");
            compteurTest.testFaux();
        }

        //15) Bob encaisse ?
        try {
            bobTest.encaisseAttaque();
            compteurTest.testOK();
        } catch (Exception e){
            System.out.println(e);
            System.out.println("Bob na pas encaissé -_-");
            compteurTest.testFaux();
        }


        //16) bob a 8 cartes
        if (bobTest.nbCarteEnMain() == 8) {
            compteurTest.testOK();
        } else {
            System.out.println("16)Bob n'a pas 8 cartes mais"+bobTest.nbCarteEnMain()+"-_-");
            compteurTest.testFaux();
        }

        //17) Charles joueur courant :
        if (partieTest.joueurCourant().equals(charlesTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("17)Le joueur courant n'est pas Charles mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }
    }

    private static void legalPlusDeux(CompteurTest compteurTest, Partie partie, Carte plusDeuxVert, Carte unVert) {
        /* ***** ***** 1er test : Test de coups légaux avec des cartes « Plus Deux » ***** ***** */

        System.out.println("\tTest de coups légaux avec des cartes « Plus Deux »");

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
        //1)Verifier qu'alice est le joueur courant :
        if (partieTest.joueurCourant().equals(aliceTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Alice mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        //2) Alice pose le +2 vert
        try {
            aliceTest.poserCarte(plusDeuxVert);
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("11)Alice na pas posé son +2 -_-");
            compteurTest.testFaux();
        }

        //3) Alice finit son tour :
        try {
            aliceTest.finTour();
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("3) Alice n'a pas reussi a finir son tour -_-");
            compteurTest.testFaux();
        }
        //4) Bob joueur courant
        if (partieTest.joueurCourant().equals(bobTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("13)Le joueur courant n'est pas Bob mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        //5) Bob a 3 cartes ?
        if (bobTest.nbCarteEnMain() == 3) {
            compteurTest.testOK();
        } else {
            System.out.println("Bob n'a pas 3 cartes mais"+bobTest.nbCarteEnMain()+"-_-");
            compteurTest.testFaux();
        }

        //6 Bob encaisse
        try {
            bobTest.encaisseAttaque();
            compteurTest.testOK();
        } catch (Exception e){
            System.out.println(e);
            System.out.println("Bob na pas encaissé -_-");
            compteurTest.testFaux();
        }
        //7) Bob a 5 cartes ?
        if (bobTest.nbCarteEnMain() == 5) {
            compteurTest.testOK();
        } else {
            System.out.println("Bob n'a pas 5 cartes mais"+bobTest.nbCarteEnMain()+"-_-");
            compteurTest.testFaux();
        }
        //8) Verifier que Charles est le joueur courant :
        if (partieTest.joueurCourant().equals(charlesTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("7)Le joueur courant n'est pas Charles mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        //9) Charles pose le 1 vert
        try {
            charlesTest.poserCarte(unVert);
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Charles na pas posé son 1VERT -_-");
            compteurTest.testFaux();
        }

        //10) Charles finit son tour
        try {
            charlesTest.finTour();
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("9) Charles n'a pas reussi a finir son tour -_-");
            compteurTest.testFaux();
        }

        //11) Charles a 2 cartes
        if (charlesTest.nbCarteEnMain() == 2) {
            compteurTest.testOK();
        } else {
            System.out.println("Charles n'a pas 2 cartes mais"+charlesTest.nbCarteEnMain()+"-_-");
            compteurTest.testFaux();
        }
    }
}
