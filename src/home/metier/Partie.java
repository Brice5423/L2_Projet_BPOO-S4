package home.metier;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.exception.ExpertManquantException;
import home.exception.ParteException;
import home.expert.Expert;
import home.metier.carte.Carte;
import home.metier.carte.CarteBasique;
import home.metier.carte.carteAEffetType.CarteChangerSens;
import home.metier.carte.carteAEffetType.CartePasserTour;
import home.metier.carte.carteAEffetType.CartePlusDeux;

import java.util.ArrayList;
import java.util.Collections;

public class Partie {
    private int niemePartie;
    private int numJoueurCourant;
    private boolean etreSensHoraire;
    private ArrayList<Joueur> listeJoueur;
    private ArrayList<Carte> pioche; // -> joueur
    private ArrayList<Carte> tas; // <- joueur

    private Partie() {
        this.niemePartie = 1;
        this.numJoueurCourant = 0;
        this.etreSensHoraire = true;
        this.listeJoueur = new ArrayList<Joueur>();
        this.pioche = new ArrayList<Carte>();
        this.tas = new ArrayList<Carte>();
    }

    /**
     * Crée une partie avec toutes les cartes du jeu (sauf +4 et changement couleur)
     */
    public Partie(ArrayList<Joueur> listeJoueur) {
        this.niemePartie = 1;
        this.numJoueurCourant = 0;
        this.etreSensHoraire = true;
        this.initialisationListeJoueur(listeJoueur);
        genererPioche(); // la fonction est en commentaire
        this.tas = new ArrayList<Carte>();
    }

    /**
     * Crée une partie en fonction d'une pioche donnée. C'est pour les tests
     *
     * @param pioche liste de carte qu'on veut dans la pioche de la partie
     */
    public Partie(ArrayList<Joueur> listeJoueur, ArrayList<Carte> pioche) {
        this.niemePartie = 1;
        this.numJoueurCourant = 0;
        this.etreSensHoraire = true;
        this.initialisationListeJoueur(listeJoueur);
        this.pioche = pioche;
        this.tas = new ArrayList<Carte>();
    }

    public int getNiemePartie() {
        return this.niemePartie;
    }

    private void setNiemePartie(int niemePartie) {
        this.niemePartie = niemePartie;
    }

    public int getNumJoueurCourant() {
        return this.numJoueurCourant;
    }

    private void setNumJoueurCourant(int numJoueurCourant) {
        this.numJoueurCourant = numJoueurCourant;
    }

    public boolean getEtreSensHoraire() {
        return this.etreSensHoraire;
    }

    private void setEtreSensHoraire(boolean etreSensHoraire) {
        this.etreSensHoraire = etreSensHoraire;
    }

    public ArrayList<Joueur> getListJoueur() {
        return this.listeJoueur;
    }

    private void setListeJoueur(ArrayList<Joueur> listeJoueur) {
        if (listeJoueur.size() < 2 || listeJoueur.size() > 10) {
            try {
                throw new ParteException("La partie doit avoir 2 à 10 dans une partie !");
            } catch (ParteException e) {
                throw new RuntimeException(e);
            }

        } else {
            this.listeJoueur.clear();
            this.listeJoueur.addAll(listeJoueur);
            this.listeJoueur.forEach(joueur -> joueur.setDansPartie(this));
        }
    }

    public ArrayList<Carte> getPioche() {
        return this.pioche;
    }

    private void setPioche(ArrayList<Carte> pioche) {
        this.pioche = pioche;
    }

    public ArrayList<Carte> getTas() {
        return this.tas;
    }

    public void setTas(ArrayList<Carte> tas) {
        this.tas = tas;
    }

    /**
     * Nombre de carte noir : 4 par carte (boucle),
     * Nombre de carte différente de noir ET différente de 0 : 2 par carte (boucle),
     * Nombre de carte 0 : 1 par couleur,
     * Melanger, mettre aléatoirement lordre des cartes de la liste
     */
    private void genererPioche() {
        this.pioche = new ArrayList<Carte>();

        this.pioche.add(new CarteBasique(ECarteCouleur.JAUNE, ECarteValeur.ZERO));
        this.pioche.add(new CarteBasique(ECarteCouleur.ROUGE, ECarteValeur.ZERO));
        this.pioche.add(new CarteBasique(ECarteCouleur.BLEU, ECarteValeur.ZERO));
        this.pioche.add(new CarteBasique(ECarteCouleur.VERT, ECarteValeur.ZERO));

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
    }

    /**
     * Donne 7 cartes à chaque joueur dans la partie.
     */
    public void initialiserCarteJoueur() {
        for (Joueur joueur : this.listeJoueur) {
            for (int i = 0; i < 7; i++) {
                joueur.piocherCarte();
            }
        }
    }

    /**
     * @return renvoie la carte de la pioche de dessus
     */
    public Carte retirerCartePioche() {
        if (!this.pioche.isEmpty()) {
            return this.pioche.remove(this.pioche.size() - 1);

        } else {
            return null;
        }
    }

    /**
     * Dépose la carte du joueur dans le tas de la partie
     *
     * @param carteJoueur Carte du joueur à déposer dans le tas
     */
    public void deposerCarteTas(Carte carteJoueur) throws ParteException {
        Expert lesExperts = Expert.initialiseTousLesExperts();

        try {
            if (!lesExperts.peutEtrePoser(carteJoueur, this.carteAuDessusTas())) {
                throw new ParteException("Le joueur " + this.joueurCourant().getNom() + " ne peut pas poser La carte " + carteJoueur + " dans le tas");
            }

        } catch (ExpertManquantException e) {
            System.out.println(e);
        }

        this.tas.add(carteJoueur);
    }

    public void inverseSensPartie() {
        this.etreSensHoraire = !this.getEtreSensHoraire();
    }

    public Carte carteAuDessusTas() {
        return this.tas.get(this.tas.size() - 1);
    }

    public Joueur joueurCourant() {
        return this.listeJoueur.get(this.numJoueurCourant);
    }

    public void joueurSuivant() {
        if (this.etreSensHoraire) {
            if (this.numJoueurCourant == (this.listeJoueur.size() - 1)) {
                this.numJoueurCourant = 0;

            } else {
                this.numJoueurCourant++;
            }

        } else {
            if (this.numJoueurCourant == 0) {
                this.numJoueurCourant = (this.listeJoueur.size() - 1);

            } else {
                this.numJoueurCourant--;
            }
        }
    }

    public Partie copiePartie() {
        Partie copiePartie = new Partie();

        copiePartie.setNiemePartie(this.niemePartie);
        copiePartie.setNumJoueurCourant(this.numJoueurCourant);
        copiePartie.setEtreSensHoraire(this.etreSensHoraire);
        copiePartie.setListeJoueur(this.listeJoueur);
        copiePartie.setPioche(this.pioche);
        copiePartie.setTas(this.tas);

        return copiePartie;
    }

    private void initialisationListeJoueur(ArrayList<Joueur> listeJoueur) {
        if (listeJoueur.size() < 2 || listeJoueur.size() > 10) {
            try {
                throw new ParteException("La partie doit avoir 2 à 10 dans une partie !");
            } catch (ParteException e) {
                throw new RuntimeException(e);
            }

        } else {
            listeJoueur.forEach(joueur -> joueur.setDansPartie(this));
            this.listeJoueur = listeJoueur;
        }
    }
}
