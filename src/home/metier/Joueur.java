package home.metier;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.metier.carte.Carte;
import home.metier.carte.CarteBasique;

import java.util.ArrayList;

public class Joueur {
    private String username;
    private ArrayList<Carte> listCarte;
    private int nbVictory;

    /*
    @TODO Alicia : Tâche à faire dans Joueur :
     - le constructeur
     - les getters
     - les setters
     - la fonction piocherCarte
     */

    /**
     * Mette la carte de la pioche dans la main du joueur
     *
     * @param cartePioche Carte récupérer dans la pioche
     */
    public void piocherCarte(Carte cartePioche) {
        /* @TODO Alicia */
    }

    /**
     * @return Renvoie une carte du joueur
     */
    public Carte deposerCarte() {
        /* @TODO deposerCarte */

        return null;
    }

    /**
     * Range les cartes du joueur par ordre valeur puis par couleur
     */
    public void rangerCarte() {
        /* @TODO rangerCarte */
    }
}
