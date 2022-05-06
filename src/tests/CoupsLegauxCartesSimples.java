package tests;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.expert.*;
import home.metier.Joueur;
import home.metier.Partie;
import home.metier.carte.Carte;
import home.metier.carte.CarteBasique;

import java.util.ArrayList;

public class CoupsLegauxCartesSimples {
    /**
     * Alice joue une carte de la bonne couleur.
     *
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
     *
     *  1) Vérifier	que	Bob	possède	bien 3 cartes,
     *  2) Bob pose le « 2 bleu »,
     *  3) Vérifier que	Bob	possède	bien 2 cartes,
     *  4) Vérifier que	les cartes de Bob sont le « 4 jaune » et le	« 9	rouge »,
     *  5) Vérifier que la carte au sommet du tas est le « 2 Bleu »,
     *  6) Vérifier	que le nombre de cartes du tas est 3,
     *  7) Bob finit le tour,
     *  8) Vérifier que le joueur courant est Charles.
     */
    public static void executionDuTest() {
        /* ***** ***** Initialisation des experts ***** ***** */

        Expert lesExperts = Expert.initialiseTousLesExperts();

        /* ***** ***** Initialiser le fichier test ***** ***** */

        ArrayList<Carte> pioche = new ArrayList<Carte>();

        pioche.add(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.SIX));
        pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.QUATRE));
        pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.DEUX));
        pioche.add(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.CINQ));
        pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.ZERO));

        Partie partie = new Partie(pioche);

        Joueur Alice = new Joueur("Alice");
        Joueur Bob = new Joueur("Bob");
        Joueur Charles = new Joueur("Charles");

        partie.ajoutJoueurPartie(Alice);
        partie.ajoutJoueurPartie(Bob);
        partie.ajoutJoueurPartie(Charles);

        ArrayList<Carte> Tas = new ArrayList<Carte>();

        Tas.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.HUIT));

        partie.setDepot(Tas);

        Alice.recupererCarte(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.DEUX));
        Alice.recupererCarte(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.SIX));
        Alice.recupererCarte(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.UN));

        Bob.recupererCarte(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.DEUX));
        Bob.recupererCarte(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.QUATRE));
        Bob.recupererCarte(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.NEUF));

        Charles.recupererCarte(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.NEUF));
        Charles.recupererCarte(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.SEPT));
        Charles.recupererCarte(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.ZERO));


        /* ***** ***** Debut test : Alice joue une carte de la bonne couleur. ***** ***** */

        // 1) Vérifier que le joueur courant est bien Alice,
        if (partie.joueurCourant().equals(Alice)) {
            System.out.println("Le joueur courant est bien Alice");
        }

        // 2) Vérifier qu'Alice possède bien 3 cartes,
        if (Alice.nbCarteEnMain() == 3) {
            System.out.println("Alice possède bien 3 cartes");
        }

        // 3) Alice joue le « 2 Vert »,
        partie.deposerCarteDepot(Alice.poserCarte(new CarteBasique(ECarteCouleur.VERT,ECarteValeur.DEUX)));

        // 4) Vérifier qu'Alice possède bien 2 cartes,
        if (Alice.nbCarteEnMain() == 2) {
            System.out.println("Alice possède bien 2 cartes");
        }

        // TODO Alicia : à finir...


        /* ***** ***** Debut test : Bob joue une carte de couleur différente, mais de même valeur. ***** ***** */
        // TODO Alicia : à finir...
    }
}
