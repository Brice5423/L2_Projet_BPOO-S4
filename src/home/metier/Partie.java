package home.metier;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.metier.carte.Carte;
import home.metier.carte.CarteAEffet;
import home.metier.carte.CarteBasique;
import home.metier.carte.carteAEffetType.CarteChangerCouleur;
import home.metier.carte.carteAEffetType.CarteChangerSens;
import home.metier.carte.carteAEffetType.CartePlusQuatre;

import java.util.ArrayList;
import java.util.Collections;

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

    public int getNumTourJoueur() {
        return this.numTourJoueur;
    }

    public void setNumTourJoueur(int numTourJoueur) {
        this.numTourJoueur = numTourJoueur;
    }

    public boolean getEtreSensHoraire() {
        return this.etreSensHoraire;
    }

    public ArrayList<Joueur> getListJoueur() {
        return this.listeJoueur;
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
     * Nombre de carte noir : 4 par carte (boucle),
     * Nombre de carte différente de noir ET différente de 0 : 2 par carte (boucle),
     * Nombre de carte 0 : 1 par couleur,
     * Melanger, mettre aléatoirement lordre des cartes de la liste
     */
    public void genererPioche() {
        this.pioche = new ArrayList<Carte>();

        this.pioche.add(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.ZERO));
        this.pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.ZERO));
        this.pioche.add(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.ZERO));
        this.pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.ZERO));

        for (int boucleNoire = 0; boucleNoire < 4; boucleNoire++) {
            this.pioche.add(new CartePlusQuatre());
            this.pioche.add(new CarteChangerCouleur());
        }

        for (int boucleCarte = 0; boucleCarte < 2; boucleCarte++) {
            this.pioche.add(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.UN));
            this.pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.UN));
            this.pioche.add(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.UN));
            this.pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.UN));

            this.pioche.add(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.DEUX));
            this.pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.DEUX));
            this.pioche.add(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.DEUX));
            this.pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.DEUX));

            this.pioche.add(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.TROIS));
            this.pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.TROIS));
            this.pioche.add(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.TROIS));
            this.pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.TROIS));

            this.pioche.add(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.QUATRE));
            this.pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.QUATRE));
            this.pioche.add(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.QUATRE));
            this.pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.QUATRE));

            this.pioche.add(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.CINQ));
            this.pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.CINQ));
            this.pioche.add(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.CINQ));
            this.pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.CINQ));

            this.pioche.add(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.SIX));
            this.pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.SIX));
            this.pioche.add(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.SIX));
            this.pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.SIX));

            this.pioche.add(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.SEPT));
            this.pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.SEPT));
            this.pioche.add(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.SEPT));
            this.pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.SEPT));

            this.pioche.add(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.HUIT));
            this.pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.HUIT));
            this.pioche.add(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.HUIT));
            this.pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.HUIT));

            this.pioche.add(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.NEUF));
            this.pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.NEUF));
            this.pioche.add(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.NEUF));
            this.pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.NEUF));

            this.pioche.add(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.PASSER_TOUR));
            this.pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.PASSER_TOUR));
            this.pioche.add(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.PASSER_TOUR));
            this.pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.PASSER_TOUR));

            this.pioche.add(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.CHANGER_SENS));
            this.pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.CHANGER_SENS));
            this.pioche.add(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.CHANGER_SENS));
            this.pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.CHANGER_SENS));

            this.pioche.add(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.PLUS_DEUX));
            this.pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.PLUS_DEUX));
            this.pioche.add(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.PLUS_DEUX));
            this.pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.PLUS_DEUX));

        }
        //Collections.shuffle melange les carte !
        Collections.shuffle(this.pioche);
    }

    public void genererDepotVide() {
        this.depot = new ArrayList<Carte>();
    }

    /**
     * Initialise les cartes des joueurs avec 7 cartes.
     */
    public void initialiserCarteJoueur() {
        for (Joueur joueur : this.listeJoueur) {
            for (int i = 0; i < 7; i++) {
                joueur.recupererCarte(this.retirerCartePioche());
            }
        }
    }

    public void lancerPartie() {
        // @TODO lancerPartie : entamé
        initialiserCarteJoueur();
    }

    /**
     * @return renvoie la carte de la pioche de dessus
     */
    public Carte retirerCartePioche() {
        return this.pioche.remove(this.pioche.size() - 1);
    }

    /**
     * Dépose la carte du joueur dans le depot de la partie
     *
     * @param carteJoueur Carte du joueur à déposer dans le depot
     */
    public void deposerCarteDepot(Carte carteJoueur) {
        this.depot.add(carteJoueur);
    }

    public void inverseSensPartie() {
        this.etreSensHoraire = !this.getEtreSensHoraire();
    }
}
