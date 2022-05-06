/* ************************************** *
 *  Projet BPOO | Par Brice.O & Alicia.O  *
 *  Version Java : 17.0.2                 *
 * ************************************** */

package home;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.metier.Joueur;
import home.metier.Partie;
import home.metier.carte.Carte;
import home.metier.carte.CarteBasique;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //Initialiser le fichier test
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

        if (partie.joueurCourant().equals(Alice)) {
            System.out.println("Le joueur courant est bien Alice");
        }

        if (Alice.nbCarteEnMain() == 3) {
            System.out.println("Alice possède bien 3 cartes");
        }


        partie.deposerCarteDepot(Alice.poserCarte(new CarteBasique(ECarteCouleur.VERT,ECarteValeur.DEUX)));

        if (Alice.nbCarteEnMain() == 2) {
            System.out.println("Alice possède bien 2 cartes");
        }


    }
}
