package home.metier;

import home.metier.carte.Carte;

import java.util.ArrayList;

public class Partie {
    private int niemePartie;
    private int numJoueurCourant;
    private boolean etreSensHoraire;
    private ArrayList<Joueur> listeJoueur;
    private ArrayList<Carte> pioche; // -> joueur
    private ArrayList<Carte> depot; // <- joueur
    private Carte carteReference;

    /**
     * Crée une partie avec toutes les cartes du jeu
     */
    /*public Partie() {
        this.niemePartie = 1;
        this.numJoueurCourant = 0;
        this.etreSensHoraire = true;
        this.listeJoueur = new ArrayList<Joueur>();
        genererPioche(); // la fonction est en commentaire
        this.depot = new ArrayList<Carte>();
        this.carteReference = null;
    }*/

    /**
     * Crée une partie en fonction d'une pioche donnée. C'est pour les tests
     * @param pioche liste de carte qu'on veut dans la partie
     */
    public Partie(ArrayList<Carte> pioche) {
        this.niemePartie = 1;
        this.numJoueurCourant = 0;
        this.etreSensHoraire = true;
        this.listeJoueur = new ArrayList<Joueur>();
        this.pioche = pioche;
        this.depot = new ArrayList<Carte>();
        this.carteReference = null;
    }

    public int getNiemePartie() {
        return this.niemePartie;
    }

    public int getNumJoueurCourant() {
        return this.numJoueurCourant;
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
    /*public void genererPioche() {
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

            this.pioche.add(new CartePasserTour(ECarteCouleur.JAUNE));
            this.pioche.add(new CartePasserTour(ECarteCouleur.ROUGE));
            this.pioche.add(new CartePasserTour(ECarteCouleur.BLEU));
            this.pioche.add(new CartePasserTour(ECarteCouleur.VERT));

            this.pioche.add(new CarteChangerSens(ECarteCouleur.JAUNE));
            this.pioche.add(new CarteChangerSens(ECarteCouleur.ROUGE));
            this.pioche.add(new CarteChangerSens(ECarteCouleur.BLEU));
            this.pioche.add(new CarteChangerSens(ECarteCouleur.VERT));

            this.pioche.add(new CartePlusDeux(ECarteCouleur.JAUNE));
            this.pioche.add(new CartePlusDeux(ECarteCouleur.ROUGE));
            this.pioche.add(new CartePlusDeux(ECarteCouleur.BLEU));
            this.pioche.add(new CartePlusDeux(ECarteCouleur.VERT));

        }
        //Collections.shuffle melange les carte !
        Collections.shuffle(this.pioche);
    }*/

    /**
     * Initialise les cartes des joueurs avec 7 cartes.
     */
    /*public void initialiserCarteJoueur() {
        for (Joueur joueur : this.listeJoueur) {
            for (int i = 0; i < 7; i++) {
                joueur.recupererCarte(this.retirerCartePioche());
            }
        }
    }*/

    public void lancerPartie() {
        // @TODO lancerPartie
        //initialiserCarteJoueur(); // fonction en commentaire
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

    public void ajoutJoueurPartie(Joueur joueur) {
        this.listeJoueur.add(joueur);
    }

    public Carte carteDepot() {
        // TODO carteDepot
        return null;
    }

    public Joueur joueurCourant() {
        // TODO joueurCourant
        return null;
    }
}
