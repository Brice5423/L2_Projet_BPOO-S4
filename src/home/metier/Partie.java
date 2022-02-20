package home.metier;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.metier.carte.Carte;

import java.util.ArrayList;

public abstract class Partie {
    private int nbTour;
    private ECarteCouleur couleurDernierCarte;
    private ECarteValeur valeurDernierCarte;
    private boolean etreSensHoraire;
    private ArrayList<Joueur> listJoueur;
    private ArrayList<Carte> pioche; // -> joueur
    private ArrayList<Carte> depot; // <- joueur

    /*
    privet genererPioche : mettre les carte dans le désordre
    public initialiserCarteJoueur : donne les 7 cartes au joueurs
    public prendCartePioche : renvoie la carte de la pioche de dessus
    public ajoutCarteDepot(Carte carte) : prend la carte du joueur et la dépose dans le depot
     */
}
