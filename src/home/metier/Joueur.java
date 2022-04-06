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

    public Joueur() {
        this.username = "anonyme";
        this.listCarte = new ArrayList<Carte>();
        this.nbVictory = 0;
    }

    public Joueur(String username) {

        this.setUsername(username);
        this.listCarte = new ArrayList<Carte>();
        this.nbVictory = 0;
    }

    public ArrayList<Carte> getListCarte() {
        return listCarte;
    }

    public int getNbVictory() {
        return nbVictory;
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        if (username.isBlank()) throw new IllegalArgumentException("Le nom du joueur est vide");
        this.username = username;
    }

    public void setNbVictory(int nbVictory) {
        this.nbVictory = nbVictory;
    }



    /**
     * Mettre la carte de la pioche dans la main du joueur
     *
     * @param cartePioche Carte récupérer dans la pioche
     */
    public void piocherCarte(Carte cartePioche) {

        listCarte.add(cartePioche);

    }

    /**
     * @return Renvoie une carte du joueur
     */
    public Carte deposerCarte() {
        /* @TODO deposerCarte */

        return null;
    }

    public void gagner(){
        /* @TODO gagner */
    }

    /**
     * Range les cartes du joueur par ordre valeur puis par couleur
     * A VOIR SI CEST A FAIRE
     */
    public void rangerCarte() {
        /* @TODO rangerCarte */
    }
}
