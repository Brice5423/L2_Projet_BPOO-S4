package home.metier;

import home.metier.carte.Carte;

import java.util.ArrayList;

public class Joueur {
    private String nom;
    private ArrayList<Carte> mainDuJoueur;
    private int nbVictory;

    public Joueur() {
        this.nom = "anonyme";
        this.mainDuJoueur = new ArrayList<Carte>();
        this.nbVictory = 0;
    }

    public Joueur(String nom) {
        this.setNom(nom);
        this.mainDuJoueur = new ArrayList<Carte>();
        this.nbVictory = 0;
    }

    public String getNom() {
        return nom;
    }

    private void setNom(String nom) {
        if (nom.isBlank())
            throw new IllegalArgumentException("Le nom du joueur est vide");
        this.nom = nom;
    }

    public ArrayList<Carte> getMainDuJoueur() {
        return this.mainDuJoueur;
    }

    public int getNbVictory() {
        return this.nbVictory;
    }

    public void setNbVictory(int nbVictory) {
        this.nbVictory = nbVictory;
    }

    /**
     * Mettre la carte de la pioche dans la main du joueur
     *
     * @param cartePioche Carte récupérer dans la pioche
     */
    public void recupererCarte(Carte cartePioche) {
        this.mainDuJoueur.add(cartePioche);
    }

    /**
     * @return Renvoie une carte du joueur
     */
    public Carte poserCarte(Carte carteSurLaTable) {
        // @TODO poserCarte

        return null;
    }

    /**
     * Range les cartes du joueur par ordre valeur puis par couleur
     * À voir SI on l'a fait
     */
    public void rangerCarte() {
        // @TODO rangerCarte
    }

    public void gagner(){
        // @TODO gagner
    }

    public int nbCarteEnMain() {
        // TODO nbCarteEnMain
        return 0;
    }

    public void afficheCarteEnMain() {
        // TODO afficheCarteEnMain
    }

    public void ditUNO() {
        // TODO ditUNO
    }

    public void finTour(Partie partieEnCours) {
        //TODO finTour
    }
}
