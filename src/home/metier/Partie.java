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

/**
 * Class qui définit une partie UNO.
 */
public class Partie {
    /** Le numéro du joueur courant de la partie. */
    private int numJoueurCourant;
    /** Pour connaitre le sens de la partie. */
    private boolean etreSensHoraire;
    /** Pour savoir si la carte passer tour est actif. */
    private boolean passerTourActif;
    /** Le nombre de cartes que le joueur devra prendre, à cause de carte "+2", "+4" ou autre "+..." si existe. */
    private int nbCarteAPiocher;
    /** Liste des joueurs se trouvant dans la partie. */
    private ArrayList<Joueur> listeJoueur;
    /** Liste des cartes se trouvant dans la pioche. Liste où le joueur récupère les cartes. */
    private ArrayList<Carte> pioche;
    /** Liste des cartes se trouvent dans le tas. Liste où le joueur dépose les cartes. */
    private ArrayList<Carte> tas;

    /**
     * Constructeur pour une partie avec des valeurs par défaut.
     * Utiliser pour faire une copie d'une partie et pour faire une vraie partie.
     */
    public Partie() {
        this.numJoueurCourant = 0;
        this.etreSensHoraire = true;
        this.passerTourActif = false;
        this.nbCarteAPiocher = 0;
        this.listeJoueur = new ArrayList<Joueur>();
        this.pioche = new ArrayList<Carte>();
        this.tas = new ArrayList<Carte>();
    }

    /**
     * Constructeur qui crée une partie à partir d'une liste de joueur (listeJoueur) et d'une pioche (pioche).
     * Utiliser pour faire les tests.
     * @param listeJoueur liste de 2 à 10 joueurs
     * @param pioche peut-être vide ou pleine (du jeu complet ou non)
     */
    public Partie(ArrayList<Joueur> listeJoueur, ArrayList<Carte> pioche) throws PartieException {
        this.numJoueurCourant = 0;
        this.etreSensHoraire = true;
        this.passerTourActif = false;
        this.nbCarteAPiocher = 0;
        this.initialisationListeJoueur(listeJoueur);
        this.pioche = pioche;
        this.tas = new ArrayList<Carte>();
    }

    /**
     * Setter pour le numéro du joueur courant de la partie.
     * @param numJoueurCourant doit être compris entre 0 et le nombre de joueurs - 1
     */
    private void setNumJoueurCourant(int numJoueurCourant) {
        this.numJoueurCourant = numJoueurCourant;
    }

    /**
     * Setter pour le sens horaire de la partie.
     * @param etreSensHoraire true : sens horaire / false : anti-sens horaire
     */
    private void setEtreSensHoraire(boolean etreSensHoraire) {
        this.etreSensHoraire = etreSensHoraire;
    }

    /**
     * Getter pour savoir si l'effet de la carte passer tour est actif.
     * @return true : actif / false : pas actif
     */
    public boolean isPasserTourActif() {
        return this.passerTourActif;
    }

    /**
     * Setter pour savoir si l'effet de la carte passer tour est actif.
     * @param passerTourActif true : actif / false : non actif
     */
    public void setPasserTourActif(boolean passerTourActif) {
        this.passerTourActif = passerTourActif;
    }

    /**
     * Getter pour le nombre de cartes à piocher à cause d'un "+2", "+4" ou "+...".
     * @return le nombre de cartes à piocher
     */
    public int getNbCarteAPiocher() {
        return this.nbCarteAPiocher;
    }

    /**
     * Getter de la liste de joueur dans la partie.
     * @return liste de joueur
     */
    public ArrayList<Joueur> getListJoueur() {
        return this.listeJoueur;
    }

    /**
     * Getter de la liste de cartes dans la pioche
     * @return liste de carte
     */
    public ArrayList<Carte> getPioche() {
        return this.pioche;
    }

    /**
     * Getter de la liste de cartes dans le tas.
     * @return liste de carte
     */
    public ArrayList<Carte> getTas() {
        return this.tas;
    }

    /**
     * Setter de la liste de carte dans le tas
     * @param tas liste de carte
     */
    public void setTas(ArrayList<Carte> tas) {
        this.tas = tas;
    }

    /**
     * Ajout un joueur à la partie. Il ne peut pas avoir plus de 10 joueur dans la partie
     * @param nouveauJoueur joueur à ajouter dans la partie
     * @throws PartieException renvoie une exception quand la liste de joueur n'est pas compris entre 2 et 10
     */
    public void ajoutJoueur(Joueur nouveauJoueur) throws PartieException {
        if (listeJoueur.size() >= 10) {
            throw new PartieException("La partie doit avoir 2 à 10 joueurs !");
        }

        nouveauJoueur.setDansLaPartie(this);
        this.listeJoueur.add(nouveauJoueur);
    }

    /**
     * Ajoute un nombre de cartes à piocher dans nbCarteAPiocherAAjouter de la partie, causer par des "+2", "+4" et etc.
     * @param nbCarteAPiocherAAjouter nombre de cartes à ajouter
     */
    public void ajoutNbCarteAPiocher(int nbCarteAPiocherAAjouter) {
        this.nbCarteAPiocher += nbCarteAPiocherAAjouter;
    }

    /**
     * Renvoie la carte se trouvant au-dessus du tas de la partie.
     * @return carte au-dessus du tas
     */
    public Carte carteAuDessusTas() {
        return this.tas.get(this.tas.size() - 1);
    }

    /**
     * Créer une copie d'une partie.
     * @return une copie de la partie
     * @throws PartieException renvoie une exception quand la liste de joueur n'est pas compris entre 2 et 10
     */
    public Partie copiePartie() throws PartieException {
        Partie copiePartie = new Partie();

        copiePartie.setNumJoueurCourant(this.numJoueurCourant);
        copiePartie.setEtreSensHoraire(this.etreSensHoraire);
        copiePartie.setPasserTourActif(this.passerTourActif);
        copiePartie.initialisationListeJoueurEnCopie(this.listeJoueur);
        copiePartie.getPioche().addAll(this.pioche);
        copiePartie.getTas().addAll(this.tas);

        return copiePartie;
    }

    /**
     * Dépose la carte du joueur dans le tas de la partie.
     * @param carteJoueur Carte du joueur à déposer dans le tas
     * @throws ExpertManquantException déclenche une exception si une carte peut-être poser ou pas ou manquant par un expert de vérification
     * @throws JoueurCarteIllegalException déclenche une exception quand le joueur joue un coup illegal
     * @throws PartieException déclenche une exception quand la pioche est vide
     * @throws JoueurOublieDireUnoException déclenche une exception quand le joueur ne dit pas "UNO !"
     * @throws JoueurJouePasException déclenche une exception quand le joueur ne joue pas
     * @throws JoueurNonCourantException déclenche une exception quand le joueur n'est pas courant
     */
    public void deposerCarteTas(Carte carteJoueur) throws ExpertManquantException, JoueurCarteIllegalException, PartieException, JoueurOublieDireUnoException, JoueurJouePasException, JoueurNonCourantException {
        try {
            Expert lesExperts = Expert.initialiseTousLesExperts();

            if (!lesExperts.peutEtrePoser(carteJoueur, this.carteAuDessusTas(), this.nbCarteAPiocher)) {
                if (this.nbCarteAPiocher == 0) {
                    throw new JoueurCarteIllegalException("Le joueur " + this.joueurCourant().getNom() + " depose la carte " + carteJoueur + ", alors qu'elle est illégale.", this.joueurCourant());

                } else {
                    throw new JoueurEncaisserAttaqueException("Le joueur " + this.joueurCourant().getNom() + "  depose la carte " + carteJoueur + ", alors qu'il doit encaisser l'attaque.", this.joueurCourant());
                }
            }

            this.tas.add(carteJoueur);

            if (carteJoueur instanceof CarteAEffet) {
                ((CarteAEffet) carteJoueur).appliquerEffet(this);
            }

        } catch (JoueurEncaisserAttaqueException e) {
            this.joueurCourant().donnerCarte(carteJoueur);
            this.joueurCourant().encaisseAttaque();
        }
    }

    /**
     * Génère toutes les cartes de la partie et les range de manière aléatoire.
     * Carte dans la partie sont :
     * jaune 0, rouge 0, bleu 0, vert 0 en 1 fois;
     * jaune 1 à 9, rouge 1 à 9, bleu 1 à 9, vert 1 à 9 en 2 fois;
     * passer tour de 4 couleurs, changer sens de 4 couleurs, plus deux en 4 couleurs en 2 fois.
     */
    public void genererPioche() {
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

        // Collections.shuffle melange les éléments d'une array liste
        Collections.shuffle(this.pioche);
    }

    /**
     * Met en place une nouvelle liste de joueur dans la partie.
     * @param listeJoueur nouvelle liste de joueur
     * @throws PartieException renvoie une exception quand la liste de joueur n'est pas compris entre 2 et 10.
     */
    private void initialisationListeJoueur(ArrayList<Joueur> listeJoueur) throws PartieException {
        if (listeJoueur.size() < 2 || listeJoueur.size() >= 10) {
            throw new PartieException("La partie doit avoir 2 à 10 joueurs !");
        }

        this.listeJoueur = listeJoueur;
        this.listeJoueur.forEach(joueur -> joueur.setDansLaPartie(this));
    }

    /**
     * Met en place une nouvelle liste de joueur à partir d'une copie.
     * @param listeJoueur liste de joueur qui sera copié
     * @throws PartieException renvoie une exception quand la liste de joueur n'est pas compris entre 2 et 10
     */
    private void initialisationListeJoueurEnCopie(ArrayList<Joueur> listeJoueur) throws PartieException {
        if (listeJoueur.size() < 2 || listeJoueur.size() >= 10) {
            throw new PartieException("La partie doit avoir 2 à 10 joueurs !");
        }

        this.listeJoueur.clear();
        listeJoueur.forEach(joueur -> this.listeJoueur.add(joueur.copieJoueur()));
        this.listeJoueur.forEach(joueur -> joueur.setDansLaPartie(this));
    }

    /**
     * Inverse le sens de la partie.
     */
    public void inverseSensPartie() {
        this.etreSensHoraire = !this.etreSensHoraire;
    }

    /**
     * Renvoie le joueur courant de la partie.
     * @return joueur courant
     */
    public Joueur joueurCourant() {
        return this.listeJoueur.get(this.numJoueurCourant);
    }

    /**
     * Met en place le prochain joueur courant.
     */
    public void joueurCourantSuivant() {
        this.numJoueurCourant = this.numJoueurSuivant();
    }

    /**
     * Remet à zero le nombre de cartes à piocher, causer par des "+2", "+4" et etc.
     */
    public void nbCarteAPiocherAZero() {
        this.nbCarteAPiocher = 0;
    }

    /**
     * Renvoie le numéro du prochain joueur courant dans la partie.
     * @return numéro du joueur courant suivant
     */
    private int numJoueurSuivant() {
        if (this.etreSensHoraire) {
            return ((this.numJoueurCourant + 1) % this.listeJoueur.size());

        } else {
            if (this.numJoueurCourant == 0) {
                return (this.listeJoueur.size() - 1);
            } else {
                return (this.numJoueurCourant - 1);
            }
        }
    }

    public void poserPremiereCarteDuTas() throws PartieException {
        if (!this.tas.isEmpty()) {
            throw new PartieException("Le tas de la partie n'est pas vide, on ne peut pas poser la 1er carte du tas.");
        }

        this.tas.add(this.retirerCartePioche());
    }

    /**
     * Retire et renvoie la carte au-dessus de la pioche de la partie.
     * @return renvoie la carte retirer de la pioche
     */
    public Carte retirerCartePioche() {
        if (!this.pioche.isEmpty()) {
            return this.pioche.remove(this.pioche.size() - 1);

        } else {
            return null;
        }
    }
}
