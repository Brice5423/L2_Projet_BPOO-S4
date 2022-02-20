package home.metier;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.metier.carte.Carte;

import java.util.ArrayList;

public abstract class Partie {
    private int nbTour;
    private int numTourJoueur;
    private boolean etreSensHoraire;
    private ECarteCouleur couleurDernierCarte;
    private ECarteValeur valeurDernierCarte;
    private ArrayList<Joueur> listJoueur;
    private ArrayList<Carte> pioche; // -> joueur
    private ArrayList<Carte> depot; // <- joueur

    public Partie(int nbTour, int numTourJoueur, boolean etreSensHoraire, ECarteCouleur couleurDernierCarte, ECarteValeur valeurDernierCarte, ArrayList<Joueur> listJoueur) {
        this.nbTour = nbTour;
        this.numTourJoueur = numTourJoueur;
        this.etreSensHoraire = etreSensHoraire;
        this.couleurDernierCarte = couleurDernierCarte;
        this.valeurDernierCarte = valeurDernierCarte;
        this.listJoueur = listJoueur;
        genererPioche();
        genererDepotVide();
    }

    public int getNbTour() {
        return nbTour;
    }

    public void setNbTour(int nbTour) {
        this.nbTour = nbTour;
    }

    public int getNumTourJoueur() {
        return numTourJoueur;
    }

    public void setNumTourJoueur(int numTourJoueur) {
        this.numTourJoueur = numTourJoueur;
    }

    public boolean isEtreSensHoraire() {
        return etreSensHoraire;
    }

    public void setEtreSensHoraire(boolean etreSensHoraire) {
        this.etreSensHoraire = etreSensHoraire;
    }

    public ECarteCouleur getCouleurDernierCarte() {
        return couleurDernierCarte;
    }

    public void setCouleurDernierCarte(ECarteCouleur couleurDernierCarte) {
        this.couleurDernierCarte = couleurDernierCarte;
    }

    public ECarteValeur getValeurDernierCarte() {
        return valeurDernierCarte;
    }

    public void setValeurDernierCarte(ECarteValeur valeurDernierCarte) {
        this.valeurDernierCarte = valeurDernierCarte;
    }

    public ArrayList<Joueur> getListJoueur() {
        return listJoueur;
    }

    public void setListJoueur(ArrayList<Joueur> listJoueur) {
        this.listJoueur = listJoueur;
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
     * @param carteJoueur Carte du joueur à déposer dans le depot
     */
    public void ajoutCartDepot(Carte carteJoueur) {
        /* @TODO ajoutCartDepot */
    }
}
