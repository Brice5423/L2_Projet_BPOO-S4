package tests;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.exception.*;
import home.metier.Joueur;
import home.metier.Partie;
import home.metier.carte.Carte;
import home.metier.carte.CarteBasique;
import home.metier.carte.carteAEffetType.CarteChangerSens;
import home.metier.carte.carteAEffetType.CartePasserTour;
import tests.controleur.CompteurTest;

import java.util.ArrayList;

public class TestChangerSens {

    public static boolean executionDuTest() {
        System.out.println("\n\t\t\t----- Tests Changer Sens -----");

        /* ***** ***** initialisation des compteurs du test ***** ***** */
        CompteurTest compteurTest = new CompteurTest("Test Changer Sens");


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

        Carte rougeSens = new CarteChangerSens(ECarteCouleur.ROUGE);
        Carte bleuNeuf = new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.NEUF);
        Carte jauneQuatre = new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.QUATRE);

        alice.donnerCarte(rougeSens);
        alice.donnerCarte(bleuNeuf);
        alice.donnerCarte(jauneQuatre);

        Carte vertSens = new CarteChangerSens(ECarteCouleur.VERT);
        Carte bleuSept = new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.SEPT);

        bob.donnerCarte(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.SIX));
        bob.donnerCarte(vertSens);
        bob.donnerCarte(bleuSept);

        Carte jauneSens = new CarteChangerSens(ECarteCouleur.JAUNE);
        Carte bleuUn = new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.UN);

        charles.donnerCarte(bleuUn);
        charles.donnerCarte(jauneSens);
        charles.donnerCarte(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.UN));


        /* ***** ***** 1er test : Test de coups légaux avec des cartes « Changer de sens » ***** ***** */
        changerSensLegaux(compteurTest, partie, rougeSens, vertSens, jauneSens,jauneQuatre);

        /* ***** ***** 2e test : Test d’une carte simple illégale sur un « Changer de Sens » ***** ***** */
        carteSimpleSurChangerSensIllegale(compteurTest, charles, partie, rougeSens, bleuUn);

        /* ***** ***** 3e test : Test Changer de sens illégale sur carte simple ***** ***** */
        changerSensSurCarteSimpleIllegale(compteurTest, charles, partie, bleuNeuf, bleuSept, jauneSens);


        /* ***** ***** Fin du test, renvoie si test ok et affiche le résultat global ***** ***** */
        return compteurTest.afficheResultatsTest();
    }

    private static void changerSensLegaux(CompteurTest compteurTest, Partie partie, Carte rougeSens, Carte vertSens, Carte jauneSens, Carte jauneQuatre) {
        System.out.println("\tTest de coups légaux avec des cartes « Changer de Sens »");

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
            System.out.println("Le joueur courant n'est pas Alice mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        // 2) Alice pose le "changer Sens rouge"
        try {
            aliceTest.poserCarte(rougeSens);
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

        // 5) Vérifier que la carte	au sommet du tas est le	« rouge changer de sens »
        if (partieTest.carteAuDessusTas().equals(rougeSens)) {
            compteurTest.testOK();
        } else {
            System.out.println("La carte de la pioche est le " + partieTest.carteAuDessusTas() + " -_-");
            compteurTest.testFaux();
        }

        // 6) Charles pose le jaune changer de sens
        try {
            charlesTest.poserCarte(jauneSens);
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

        // 8) verifier que le joueur courant = Alice
        if (partieTest.joueurCourant().equals(aliceTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Alice mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        // 9) Vérifier que la carte	au sommet du tas est le	« jaune changer de sens »
        if (partieTest.carteAuDessusTas().equals(jauneSens)) {
            compteurTest.testOK();
        } else {
            System.out.println("La carte de la pioche est le " + partieTest.carteAuDessusTas() + " -_-");
            compteurTest.testFaux();
        }

        // 10) Alice pose le 4 jaune
        try {
            bobTest.poserCarte(jauneQuatre);
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 11)Alice finit son tour
        try {
            aliceTest.finTour();
            compteurTest.testOK();
        } catch (Exception e) {
            System.out.println(e);
            compteurTest.testFaux();
        }

        // 12) verifier que le joueur courant = Bob
        if (partieTest.joueurCourant().equals(bobTest)) {
            compteurTest.testOK();
        } else {
            System.out.println("Le joueur courant n'est pas Charles mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        // 13) Vérifier que la carte	au sommet du tas est le	« 4 Jaune »
        if (partieTest.carteAuDessusTas().equals(jauneQuatre)) {
            compteurTest.testOK();
        } else {
            System.out.println("La carte de la pioche est le " + partieTest.carteAuDessusTas() + " -_-");
            compteurTest.testFaux();
        }
    }

    private static void carteSimpleSurChangerSensIllegale(CompteurTest compteurTest, Joueur charles, Partie partie, Carte rougeSens, Carte bleuUn) {
        System.out.println("\tTest d’une carte simple sur un « changer Sens» illégale");

        /* ***** Bloc des premiers copie pour les tests ***** */
        Partie partieTest = null;
        try {
            partieTest = partie.copiePartie();
            compteurTest.testOK();
        } catch (PartieException e) {
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
            aliceTest.poserCarte(rougeSens);
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
            System.out.println("Charles à réussi à mettre un « 1 Bleu » sur « changer sens rouge »");
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

    private static void changerSensSurCarteSimpleIllegale(CompteurTest compteurTest, Joueur charles, Partie partie, Carte bleuNeuf, Carte bleuSept, Carte jauneSens) {
        System.out.println("\tTest Changer Sens illégale sur carte simple");

        /* ***** Bloc des premiers copie pour les tests ***** */
        Partie partieTest = null;
        try {
            partieTest = partie.copiePartie();
            compteurTest.testOK();
        } catch (PartieException e) {
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

        // 2) Alice pose le 9 bleu
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

        // 8) Charles pose le changer Sens jaune
        try {
            charlesTest.poserCarte(jauneSens);
            System.out.println("Charles à réussi à poser un changer sens jaune sur un « 7 Bleu » -_-");
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

