package tests;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.excepcion.ExpertManquantException;
import home.expert.*;
import home.metier.Joueur;
import home.metier.Partie;
import home.metier.carte.Carte;
import home.metier.carte.CarteBasique;

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
    public static void executionDuTest() {
        /* ***** ***** Initialiser le fichier test ***** ***** */

        ArrayList<Carte> pioche = new ArrayList<Carte>();

        pioche.add(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.SIX));
        pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.QUATRE));
        pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.DEUX));
        pioche.add(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.CINQ));
        pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.ZERO));

        Partie partie = new Partie(pioche);

        Joueur alice = new Joueur("Alice");
        Joueur bob = new Joueur("Bob");
        Joueur charles = new Joueur("Charles");

        partie.ajoutJoueurPartie(alice);
        partie.ajoutJoueurPartie(bob);
        partie.ajoutJoueurPartie(charles);

        ArrayList<Carte> Tas = new ArrayList<Carte>();

        Tas.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.HUIT));
        partie.setDepot(Tas);
        partie.setCarteReference(partie.carteDepot());

        Carte vertDeux = new CarteBasique(ECarteCouleur.VERT, ECarteValeur.DEUX);
        Carte jauneSix = new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.SIX);

        alice.recupererCarte(vertDeux);
        alice.recupererCarte(jauneSix);
        alice.recupererCarte(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.UN));

        Carte bleuDeux = new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.DEUX);

        bob.recupererCarte(bleuDeux);
        bob.recupererCarte(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.QUATRE));
        bob.recupererCarte(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.NEUF));

        charles.recupererCarte(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.NEUF));
        charles.recupererCarte(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.SEPT));
        charles.recupererCarte(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.ZERO));


        /* ***** ***** Debut test : Alice joue une carte de la bonne couleur. ***** ***** */

        // 1) Vérifier que le joueur courant est bien Alice,
        if (partie.joueurCourant().equals(alice)) {
            System.out.println("Le joueur courant est bien Alice");
        }

        // 2) Vérifier qu'Alice possède bien 3 cartes,
        if (alice.nbCarteEnMain() == 3) {
            System.out.println("Alice possède bien 3 cartes");
        }

        // 3) Alice joue le « 2 Vert »,
        try {
            partie.deposerCarteDepot(alice.poserCarte(vertDeux));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 4) Vérifier qu'Alice possède bien 2 cartes,
        if (alice.nbCarteEnMain() == 2) {
            System.out.println("Alice possède bien 2 cartes");
        }


        // 5) Vérifier que	les	cartes d’Alice	sont le	« 6	jaune »	et	le	« 1	rouge »
        alice.afficheCarteEnMain();

        //6) Vérifier que la carte au sommet du tas est le « 2 Vert »
        if (partie.carteDepot() == vertDeux){
            System.out.println("La carte au sommet du tas est bien le 2 vert");
        }

        //7) Vérifier que le nombre de cartes du tas est 2,
        if( Tas.size() == 2){
            System.out.println("Le nombre de carte dans le tas est de 2");
        }

        // 8) Alice finit le tour
        alice.finTour(partie);


        // 9) Vérifier que le joueur courant est Bob.
        if(partie.joueurCourant().equals(bob)) {
            System.out.println("Le joueur courant est Bob");
        }

        /* ***** ***** Debut test : Bob joue une carte de couleur différente, mais de même valeur. ***** ***** */

     //1) Vérifier	que	Bob	possède	bien 3 cartes
        if (bob.nbCarteEnMain() == 3) {
            System.out.println("Bob possède bien 3 cartes");
        }

     //2) Bob pose le « 2 bleu »,
        try {
            partie.deposerCarteDepot(bob.poserCarte(bleuDeux));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
     //3) Vérifier que	Bob	possède	bien 2 cartes,
        if (bob.nbCarteEnMain() == 2) {
            System.out.println("Bob possède bien 2 cartes");
        }
     // 4) Vérifier que	les cartes de Bob sont le « 4 jaune » et le	« 9	rouge »,
        //TODO : Alicia : J'attend d'avoir la fonction afficheCarteEnMain()
        System.out.println(alice);

     //5) Vérifier que la carte au sommet du tas est le « 2 Bleu »,
        if (partie.carteDepot() == bleuDeux){
            System.out.println("La carte au sommet du tas est bien le 2 Bleu");
        }
     //6) Vérifier	que le nombre de cartes du tas est 3,
        if( Tas.size() == 3){
            System.out.println("Le nombre de carte dans le tas est de 3");
        }
     //7) Bob finit le tour,
        bob.finTour(partie);

     // 8) Vérifier que le joueur courant est Charles.
        if(partie.joueurCourant().equals(charles)) {
            System.out.println("Le joueur courant est Charles");
        }
    }
}
