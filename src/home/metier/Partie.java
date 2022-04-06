package home.metier;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.metier.carte.Carte;

import java.util.ArrayList;

public abstract class Partie {
    private int nIemePartie;
    private int numTourJoueur;
    private boolean etreSensHoraire;
    private Carte carteReference;
    private ArrayList<Joueur> listeJoueur;
    private ArrayList<Carte> pioche; // -> joueur
    private ArrayList<Carte> depot; // <- joueur

    public Partie(ArrayList<Joueur> listeJoueur) {
        this.nIemePartie = 1;
        this.numTourJoueur = 0;
        this.etreSensHoraire = true;
        this.carteReference = null;
        this.listeJoueur = listeJoueur;
        genererPioche();
        genererDepotVide();
    }

    public int getNiemePartie() {
        return nIemePartie;
    }

    public void setNiemePartie(int niemePartie) {
        this.nIemePartie = niemePartie;
    }

    public int getNumTourJoueur() {
        return numTourJoueur;
    }

    public void setNumTourJoueur(int numTourJoueur) {
        this.numTourJoueur = numTourJoueur;
    }

    public boolean getEtreSensHoraire() {
        return etreSensHoraire;
    }

    public void setEtreSensHoraire(boolean etreSensHoraire) {
        this.etreSensHoraire = etreSensHoraire;
    }

    public Carte getCarteReference() {
        return carteReference;
    }

    public void setCarteReference(Carte carteReference) {
        this.carteReference = carteReference;
    }

    public ArrayList<Joueur> getListJoueur() {
        return listeJoueur;
    }

    public void setListJoueur(ArrayList<Joueur> listeJoueur) {
        this.listeJoueur = listeJoueur;
    }

    public ArrayList<Carte> getPioche() {
        return pioche;
    }

    public void setPioche(ArrayList<Carte> pioche) {
        this.pioche = pioche;
    }

    public ArrayList<Carte> getDepot() {
        return depot;
    }

    public void setDepot(ArrayList<Carte> depot) {
        this.depot = depot;
    }

    /**
     * Créer une ArrayList de Carte vide, remplit la liste de toutes les cartes et les met dans le désordre
     */
    public void genererPioche() {
        /* @TODO genererPioche */
    }

    public void genererDepotVide() {
        this.depot = new ArrayList<Carte>();
    }

    /**
     * Initialise les carte des joueurs avec 7 cartes en mains.
     */
    public void initialiserCarteJoueur() {
        /* @TODO initialiserCarteJoueur */
    }

    /**
     * @return renvoie la carte de la pioche de dessus
     */
    public Carte prendreCartePioche() {
        /* @TODO prendreCartePioche */
        return null;
    }

    /**
     * Dépose la carte du joueur dans le depot de la partie
     *
     * @param carteJoueur Carte du joueur à déposer dans le depot
     */
    public void ajoutCartDepot(Carte carteJoueur) {
        /* @TODO ajoutCartDepot */
    }
}
