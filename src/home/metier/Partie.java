package home.metier;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.exception.*;
import home.expert.Expert;
import home.metier.carte.Carte;
import home.metier.carte.CarteAEffet;
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
    private boolean passerTourActif;
    private int nbCarteAPiocher;
    private ArrayList<Joueur> listeJoueur;
    private ArrayList<Carte> pioche; // -> joueur
    private ArrayList<Carte> tas; // <- joueur

    /**
     * Constructeur utiliser pour créer une copie d'une partie
     */
    private Partie() {
        this.niemePartie = 1;
        this.numJoueurCourant = 0;
        this.etreSensHoraire = true;
        this.passerTourActif = false;
        this.nbCarteAPiocher = 0;
        this.listeJoueur = new ArrayList<Joueur>();
        this.pioche = new ArrayList<Carte>();
        this.tas = new ArrayList<Carte>();
    }

    /**
     * Crée une partie avec toutes les cartes du jeu (sauf +4 et changement couleur)
     *
     * @param listeJoueur liste de 2 à 10 joueurs
     */
    public Partie(ArrayList<Joueur> listeJoueur) throws PartieException {
        this.niemePartie = 1;
        this.numJoueurCourant = 0;
        this.etreSensHoraire = true;
        this.passerTourActif = false;
        this.nbCarteAPiocher = 0;
        this.initialisationListeJoueur(listeJoueur);
        this.genererPioche();
        this.initialiserCarteJoueur();
        this.tas = new ArrayList<Carte>();
    }

    /**
     * Crée une partie en fonction d'une liste de joueur et d'une pioche donnée.
     * Utiliser pour les tests.
     *
     * @param listeJoueur liste de 2 à 10 joueurs
     * @param pioche
     */
    public Partie(ArrayList<Joueur> listeJoueur, ArrayList<Carte> pioche) throws PartieException {
        this.niemePartie = 1;
        this.numJoueurCourant = 0;
        this.etreSensHoraire = true;
        this.passerTourActif = false;
        this.nbCarteAPiocher = 0;
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

    public boolean isEtreSensHoraire() {
        return this.etreSensHoraire;
    }

    private void setEtreSensHoraire(boolean etreSensHoraire) {
        this.etreSensHoraire = etreSensHoraire;
    }

    public boolean isPasserTourActif() {
        return this.passerTourActif;
    }

    public void setPasserTourActif(boolean passerTourActif) {
        this.passerTourActif = passerTourActif;
    }

    public int getNbCarteAPiocher() {
        return this.nbCarteAPiocher;
    }

    public void setNbCarteAPiocher(int nbCarteAPiocher) {
        this.nbCarteAPiocher = nbCarteAPiocher;
    }

    public ArrayList<Joueur> getListeJoueur() {
        return this.listeJoueur;
    }

    public void setListeJoueur(ArrayList<Joueur> listeJoueur) {
        this.listeJoueur = listeJoueur;
    }

    public ArrayList<Joueur> getListJoueur() {
        return this.listeJoueur;
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

    private void initialisationListeJoueur(ArrayList<Joueur> listeJoueur) throws PartieException {
        if (listeJoueur.size() < 2 || listeJoueur.size() > 10) {
            throw new PartieException("La partie doit avoir 2 à 10 dans une partie !");
        }

        this.listeJoueur = listeJoueur;
        this.listeJoueur.forEach(joueur -> joueur.setDansLaPartie(this));
    }

    private void setListeJoueurEtCopie(ArrayList<Joueur> listeJoueur) throws PartieException {
        if (listeJoueur.size() < 2 || listeJoueur.size() > 10) {
            throw new PartieException("La partie doit avoir 2 à 10 dans une partie !");
        }

        this.listeJoueur.clear();
        listeJoueur.forEach(joueur -> this.listeJoueur.add(joueur.copyJoueur()));
        this.listeJoueur.forEach(joueur -> joueur.setDansLaPartie(this));
    }

    /**
     * Donne 7 cartes à chaque joueur dans la partie.
     */
    public void initialiserCarteJoueur() throws PartieException {
        for (Joueur joueur : this.listeJoueur) {
            for (int i = 0; i < 7; i++) {
                joueur.donnerCarte();
            }
        }
    }

    public Partie copiePartie() throws PartieException {
        Partie copiePartie = new Partie();

        copiePartie.setNiemePartie(this.niemePartie);
        copiePartie.setNumJoueurCourant(this.numJoueurCourant);
        copiePartie.setEtreSensHoraire(this.etreSensHoraire);
        copiePartie.setPasserTourActif(this.passerTourActif);
        copiePartie.setListeJoueurEtCopie(this.listeJoueur);
        copiePartie.getPioche().addAll(this.pioche);
        copiePartie.getTas().addAll(this.tas);

        return copiePartie;
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
    public void deposerCarteTas(Carte carteJoueur) throws JoueurCarteIllegalException, PartieException, JoueurOublieDireUnoException, JoueurJouePasException, JoueurNonCourantException {
        try {
            Expert lesExperts = Expert.initialiseTousLesExperts();

            if (!lesExperts.peutEtrePoser(carteJoueur, this.carteAuDessusTas(), this.nbCarteAPiocher)) {
                if (this.nbCarteAPiocher == 0) {
                    throw new JoueurCarteIllegalException("Le joueur " + this.joueurCourant().getNom() + " ne peut pas poser La carte " + carteJoueur + " dans le tas", this.joueurCourant());

                } else {
                    throw new JoueurEncaisserAttaqueException("Le joueur " + this.joueurCourant().getNom() + " doit encaisser l'attaque", this.joueurCourant());
                }
            }

            this.tas.add(carteJoueur);

            if (carteJoueur instanceof CarteAEffet) {
                ((CarteAEffet) carteJoueur).appliquerEffet(this);
            }

        } catch (ExpertManquantException e) {
            System.out.println(e);
        } catch (JoueurEncaisserAttaqueException e) {
            this.joueurCourantRecupererNCarte(this.nbCarteAPiocher);
            this.joueurCourant().finTour();
        }
    }

    public Carte carteAuDessusTas() {
        return this.tas.get(this.tas.size() - 1);
    }

    public void inverseSensPartie() {
        this.etreSensHoraire = !this.etreSensHoraire;
    }

    public void ajoutNbCarteAPiocher(int nbCarteAPiocherAAjouter) {
        this.nbCarteAPiocher += nbCarteAPiocherAAjouter;
    }

    private void joueurCourantRecupererNCarte(int nCarteARecuperer) throws PartieException {
        for (int i = 0; i < nbCarteAPiocher; i++) {
            this.joueurCourant().donnerCarte();
        }
    }

    private int numJoueurSuivant() {
        if (this.etreSensHoraire) {
            if (this.numJoueurCourant == (this.listeJoueur.size() - 1)) {
                return 0;

            } else {
                return this.numJoueurCourant + 1;
            }

        } else {
            if (this.numJoueurCourant == 0) {
                return (this.listeJoueur.size() - 1);

            } else {
                return this.numJoueurCourant - 1;
            }
        }
    }

    public Joueur joueurCourant() {
        return this.listeJoueur.get(this.numJoueurCourant);
    }

    public void joueurCourantSuivant() {
        this.numJoueurCourant = this.numJoueurSuivant();
    }
}
