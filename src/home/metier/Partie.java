package home.metier;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.metier.carte.Carte;
import home.metier.carte.CarteAEffet;
import home.metier.carte.CarteBasique;
import home.metier.carte.carteAEffetType.CartePlusQuatre;

import java.util.ArrayList;

public abstract class Partie {
    private int niemePartie;
    private int numTourJoueur;
    private boolean etreSensHoraire;
    private ArrayList<Joueur> listeJoueur;
    private ArrayList<Carte> pioche; // -> joueur
    private ArrayList<Carte> depot; // <- joueur
    private Carte carteReference;

    public Partie(ArrayList<Joueur> listeJoueur) {
        this.niemePartie = 1;
        this.numTourJoueur = 0;
        this.etreSensHoraire = true;
        this.listeJoueur = listeJoueur;
        genererPioche();
        genererDepotVide();
        this.carteReference = null;
    }

    public int getNiemePartie() {
        return this.niemePartie;
    }

    public void setNiemePartie(int niemePartie) {
        this.niemePartie = niemePartie;
    }

    public int getNumTourJoueur() {
        return this.numTourJoueur;
    }

    public void setNumTourJoueur(int numTourJoueur) {
        this.numTourJoueur = numTourJoueur;
    }

    public boolean getEtreSensHoraire() {
        return this.etreSensHoraire;
    }

    public void setEtreSensHoraire(boolean etreSensHoraire) {
        this.etreSensHoraire = etreSensHoraire;
    }

    public ArrayList<Joueur> getListJoueur() {
        return this.listeJoueur;
    }

    public void setListJoueur(ArrayList<Joueur> listeJoueur) {
        this.listeJoueur = listeJoueur;
    }

    public ArrayList<Carte> getPioche() {
        return this.pioche;
    }

    public void setPioche(ArrayList<Carte> pioche) {
        this.pioche = pioche;
    }

    public ArrayList<Carte> getDepot() {
        return this.depot;
    }

    public void setDepot(ArrayList<Carte> depot) {
        this.depot = depot;
    }

    public Carte getCarteReference() {
        return this.carteReference;
    }

    public void setCarteReference(Carte carteReference) {
        this.carteReference = carteReference;
    }

    /**
     * Créer une ArrayList de Carte vide, remplit la liste de toutes les cartes et les met dans le désordre
     */
    public void genererPioche() {
        // @TODO genererPioche : Alicia
        this.pioche = new ArrayList<Carte>();

        // Générer tout les carte à main, je te remercie pour ce sacrifice Alicia ^^
        // Voici un exemple de comment faire avec un "+ 4"
        this.pioche.add(new CartePlusQuatre());
        // ...
    }

    public void genererDepotVide() {
        this.depot = new ArrayList<Carte>();
    }

    /**
     * Initialise les cartes des joueurs avec 7 cartes.
     */
    public void initialiserCarteJoueur() {
        // @TODO initialiserCarteJoueur : fini (alicia)

        for (Joueur joueur : this.listeJoueur) {
            for (int i = 0; i < 7; i++) {
                joueur.recupererCarte(this.retirerCartePioche());
            }
        }
    }

    public void lancerPartie() {
        // @TODO lancerPartie
    }

    /**
     * @return renvoie la carte de la pioche de dessus
     */
    public Carte retirerCartePioche() {
        // @TODO prendreCartePioche : fini (alicia)

        return this.pioche.remove(0);
    }

    /**
     * Dépose la carte du joueur dans le depot de la partie
     *
     * @param carteJoueur Carte du joueur à déposer dans le depot
     */
    public void deposerCarteDepot(Carte carteJoueur) {
        // @TODO ajoutCarteDepot : fini (alicia)

        this.depot.add(carteJoueur);
    }
}
