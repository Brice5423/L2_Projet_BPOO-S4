package tests;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.exception.JoueurException;
import home.exception.JoueurJoueMultipleException;
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


        /* ***** ***** Debut test : Test d’une carte illégale ***** ***** */

        /* ***** Bloc des premiers copie pour les tests ***** */
        Partie partieTest = partie.copiePartie();

        Joueur aliceTest = partieTest.getListJoueur().get(0);
        Joueur bobTest = partieTest.getListJoueur().get(1);
        Joueur charlesTest = partieTest.getListJoueur().get(2);
        /* ************************************************** */

        // 1) Vérifier que le joueur courant est bien Alice,
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
           // aliceTest.punition();
            try{
                aliceTest.donnerCarte(partieTest.retirerCartePioche());
            }catch (Exception f){
                System.out.println(f);
            }
            try{
                aliceTest.donnerCarte(partieTest.retirerCartePioche());
            }catch (Exception g){
                System.out.println(g);
            }
            System.out.println(e);
        }

        aliceTest.finTour();
        aliceTest.afficheCarteEnMain();
        if( pioche.size() == 3){
            System.out.println("its ok ^^");
        }else{
            System.out.println("nombre de carte dans la pioche :"+ pioche.size()+"-_-");
        }





        //Punir Alice, la faire piocher 2 cartes et passer son tour



        if (partieTest.joueurCourant().equals(bobTest)) {
            System.out.println("Le joueur courant est bien Bob ^^");
            compteurTest.testOK();

        } else {
            System.out.println("Le joueur courant n'est pas Bob mais " + partieTest.joueurCourant().getNom() + " -_-");
            compteurTest.testFaux();
        }

        bobTest.afficheCarteEnMain();

        Carte cartePioche = partieTest.retirerCartePioche();

        if(cartePioche.equals(vertDeux)){
            System.out.println("La carte de la pioche est le 2 vert ^^");
            compteurTest.testOK();

        }else{
            System.out.println("La carte de la pioche est le " + cartePioche + " -_-");
            compteurTest.testFaux();
        }

        /* ***** ***** Fin du test, renvoie si test ok et affiche le résultat global ***** ***** */
        return compteurTest.afficheResultatsTest();
    }
}


