package home.metier;

import home.exception.*;
import home.metier.carte.Carte;

import java.util.ArrayList;

/**
 * Classe qui définit un joueur UNO
 */
public class Joueur {
    /**
     * Le nom du joueur
     */
    private String nom;
    /**
     * La liste de cartes se trouvant dans la main du joueur
     */
    private ArrayList<Carte> mainDuJoueur;
    /**
     * La partie où le joueur se trouve
     */
    private Partie dansLaPartie;
    /**
     * Pour savoir si le joueur a déjà joué durant son tour
     */
    private boolean avoirJouerSonTour;
    /**
     * Pour savoir si le joueur a dit "UNO !"
     */
    private boolean avoirDitUNO;

    /**
     * Création d'un joueur par défaut.
     * Utiliser pour les copies.
     */
    private Joueur() {
        this.nom = null;
        this.mainDuJoueur = new ArrayList<Carte>();
        this.dansLaPartie = null;
        this.avoirJouerSonTour = false;
        this.avoirDitUNO = false;
    }

    /**
     * Création d'un joueur à partir de son nom.
     * Le reste est initialisé à null (ou arrayList vide).
     *
     * @param nom nom du joueur
     */
    public Joueur(String nom) {
        this.setNom(nom);
        this.mainDuJoueur = new ArrayList<Carte>();
        this.dansLaPartie = null;
        this.avoirJouerSonTour = false;
        this.avoirDitUNO = false;
    }

    /**
     * Getter du nom du joueur.
     *
     * @return nom du joueur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter du nom du joueur.
     *
     * @param nom nouveau nom du joueur
     */
    private void setNom(String nom) {
        if (nom.isBlank()) {
            throw new IllegalArgumentException("Le nom du joueur est vide ou null.");
        }

        this.nom = nom;
    }

    /**
     * Getter de la liste de cartes dans la main du joueur.
     *
     * @return la liste de carte du joueur
     */
    public ArrayList<Carte> getMainDuJoueur() {
        return this.mainDuJoueur;
    }

    /**
     * Setter de la liste de carte dans la main du joueur.
     *
     * @param dansLaPartie nouvelle liste de carte du joueur
     */
    public void setDansLaPartie(Partie dansLaPartie) {
        this.dansLaPartie = dansLaPartie;
    }

    /**
     * Setter pour savoir si le joueur à jouer son tour.
     *
     * @param avoirJouerSonTour true : avoir joué / false : ne pas avoir joué
     */
    public void setAvoirJouerSonTour(boolean avoirJouerSonTour) {
        this.avoirJouerSonTour = avoirJouerSonTour;
    }

    /**
     * Setter pour savoir si le joueur a dit "UNO !".
     *
     * @param avoirDitUNO true : avoir dit "UNO !" / false : ne pas avoir dit "UNO !"
     */
    private void setAvoirDitUNO(boolean avoirDitUNO) {
        this.avoirDitUNO = avoirDitUNO;
    }

    /**
     * Fonction redéfinie (@Override).
     * Vérifie sur l'objet o est égal au joueur this.
     * Deux joueurs sont égaux lorsqu'ils ont le même nom.
     *
     * @param o l'objet Joueur pour lequel on veut vérifier l'égalité
     * @return true : sont égaux / false : ne sont pas égaux
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;

        Joueur joueur = (Joueur) o;

        return this.nom.equals(joueur.getNom());
    }

    /**
     * Fonction redéfinie (@Override).
     * Renvoie le joueur sous un format de chaîne de caractère String.
     *
     * @return le joueur en String
     */
    @Override
    public String toString() {
        return "Joueur {\n" +
                "nom = '" + nom + '\'' +
                ", avoirJouerSonTour = " + this.avoirJouerSonTour +
                ", avoirDitUNO = " + this.avoirDitUNO +
                ", mainDuJoueur = \n" + mainDuJoueur +
                "\n}";
    }

    /**
     * Renvoie la copie du joueur.
     *
     * @return copie du joueur
     */
    public Joueur copieJoueur() {
        Joueur copieJoueur = new Joueur();

        copieJoueur.setNom(this.nom);
        copieJoueur.getMainDuJoueur().addAll(this.mainDuJoueur);
        copieJoueur.setDansLaPartie(this.dansLaPartie);
        copieJoueur.setAvoirJouerSonTour(this.avoirJouerSonTour);
        copieJoueur.setAvoirDitUNO(this.avoirDitUNO);

        return copieJoueur;
    }

    /**
     * Renvoie le nombre de cartes que le joueur a en main.
     *
     * @return nombre cartes du joueur
     */
    public int nbCarteEnMain() {
        return this.mainDuJoueur.size();
    }

    /**
     * Le joueur pose la carte carteChoisieParJoueur dans le tas de la partie.
     *
     * @param carteChoisieParJoueur carte que le joueur dépose dans le tas
     * @throws JoueurNonCourantException          déclenche une exception quand le joueur n'est pas courant
     * @throws JoueurJoueCarteAbsentMainException déclenche une exception quand le joueur joue une carte qu'il n'a pas
     * @throws JoueurJoueMultipleException        déclenche une exception quand le joueur joue plusieurs fois
     * @throws JoueurCarteIllegalException        déclenche une exception quand le joueur joue un coup illegal
     * @throws JoueurEncaisserAttaqueException    déclenche quand un joueur pose une carte alors qu'il doit encaisser les attaque dû aux "+2", "+4", etc.
     * @throws ExpertManquantException            déclenche une exception si une carte peut-être poser ou pas ou manquant par un expert de vérification
     */
    public void poserCarte(Carte carteChoisieParJoueur) throws JoueurNonCourantException, JoueurJoueCarteAbsentMainException, JoueurJoueMultipleException, JoueurCarteIllegalException, JoueurEncaisserAttaqueException, ExpertManquantException {
        if (!this.equals(this.dansLaPartie.joueurCourant())) {
            throw new JoueurNonCourantException("Le joueur " + this.nom + " pose une carte, alors qu'il n'est pas le joueur courant.", this);
        }
        if (!this.mainDuJoueur.contains(carteChoisieParJoueur)) {
            throw new JoueurJoueCarteAbsentMainException("Le joueur " + this.nom + " choisie une carte qui n'est pas dans sa main.", this);
        }
        if (this.avoirJouerSonTour) {
            throw new JoueurJoueMultipleException("Le joueur " + this.nom + " pose une carte, alors qu'il a déjà joué.", this);
        }

        this.dansLaPartie.deposerCarteTas(carteChoisieParJoueur);
        this.mainDuJoueur.remove(carteChoisieParJoueur);
        this.avoirJouerSonTour = true;
    }

    /**
     * Donne une carte au joueur.
     * La carte est prise dans la pioche.
     * Par cette action le joueur ne sera pas considéré comme avoir joué.
     *
     * @throws PartiePiocheVideException déclenche une exception quand la pioche est vide
     */
    public void donnerCarte() throws PartiePiocheVideException {
        if (this.dansLaPartie.getPioche().isEmpty()) {
            throw new PartiePiocheVideException("On donne une carte au joueur " + this.nom + ", alors que la pioche est vide.");
        }

        this.mainDuJoueur.add(this.dansLaPartie.retirerCartePioche());
    }

    /**
     * Donne au joueur la carte carteDonnee.
     * Par cette action le joueur ne sera pas considéré comme avoir joué.
     *
     * @param carteDonnee carte à donner au joueur
     */
    public void donnerCarte(Carte carteDonnee) {
        this.mainDuJoueur.add(carteDonnee);
    }

    /**
     * Le joueur prend une carte au-dessus de la pioche.
     * Pour cette action le joueur sera considérer comme avoir joué.
     *
     * @throws PartiePiocheVideException   déclenche une exception quand la pioche est vide
     * @throws JoueurNonCourantException   déclenche une exception quand le joueur n'est pas courant
     * @throws JoueurJoueMultipleException déclenche une exception quand le joueur joue plusieurs fois
     */
    public void piocherCarte() throws PartiePiocheVideException, JoueurNonCourantException, JoueurJoueMultipleException {
        if (this.dansLaPartie.getPioche().isEmpty()) {
            throw new PartiePiocheVideException("Le joueur " + this.nom + " pioche, alors que la pioche est vide.");
        }
        if (!this.equals(this.dansLaPartie.joueurCourant())) {
            throw new JoueurNonCourantException("Le joueur " + this.nom + " pioche, alors qu'il n'est pas le joueur courant.", this);
        }
        if (this.avoirJouerSonTour) {
            throw new JoueurJoueMultipleException("Le joueur " + this.nom + " pioche, alors qu'il a déjà joué.", this);
        }

        this.mainDuJoueur.add(this.dansLaPartie.retirerCartePioche());
        this.dansLaPartie.retirerCartePioche(); // TODO il fait quoi lui

        this.avoirJouerSonTour = true;
    }

    /**
     * Le joueur prend la carte carteDonnee.
     * Utiliser principalement pour les tests.
     * Pour cette action, le joueur sera considérer comme avoir joué.
     *
     * @param carteDonnee carte que le joueur pioche
     * @throws JoueurNonCourantException   déclenche une exception quand le joueur n'est pas courant
     * @throws JoueurJoueMultipleException déclenche une exception quand le joueur joue plusieurs fois
     */
    public void piocherCarte(Carte carteDonnee) throws JoueurNonCourantException, JoueurJoueMultipleException {
        if (!this.equals(this.dansLaPartie.joueurCourant())) {
            throw new JoueurNonCourantException("Le joueur " + this.nom + " pioche, alors qu'il n'est pas le joueur courant.", this);
        }
        if (this.avoirJouerSonTour) {
            throw new JoueurJoueMultipleException("Le joueur " + this.nom + " pioche, alors qu'il a déjà joué.", this);
        }

        this.mainDuJoueur.add(carteDonnee);
        this.avoirJouerSonTour = true;
    }

    /**
     * Donne au joueur n cartes.
     *
     * @param nCarteARecuperer nombre de cartes à donner
     * @throws PartiePiocheVideException déclenche une exception quand la pioche est vide
     */
    public void recupererNCarte(int nCarteARecuperer) throws PartiePiocheVideException {
        for (int i = 0; i < nCarteARecuperer; i++) {
            this.donnerCarte();
        }
    }

    /**
     * Puni le joueur en lui donnant 2 cartes.
     *
     * @throws PartiePiocheVideException déclenche une exception quand la pioche est vide
     */
    public void punition() throws PartiePiocheVideException {
        this.recupererNCarte(2);

        // Renvoie true si joueur courant sinon false
        this.avoirJouerSonTour = this.equals(this.dansLaPartie.joueurCourant());
    }

    /**
     * Le joueur encaisse les attaques causées par les "+2", "+4" et etc.
     *
     * @throws PartiePiocheVideException déclenche une exception quand la pioche est vide
     */
    public void encaisseAttaque() throws PartiePiocheVideException {
        this.recupererNCarte(this.dansLaPartie.getNbCarteAPiocher());
        this.dansLaPartie.nbCarteAPiocherAZero();
        this.setAvoirJouerSonTour(true);
    }

    /**
     * Le joueur dit "UNO !".
     *
     * @throws JoueurNonCourantException     déclenche une exception quand le joueur n'est pas courant
     * @throws JoueurJouePasException        déclenche une exception quand le joueur ne joue pas
     * @throws JoueurDireUnoTropTotException déclenche une exception quand le joueur à plus d'une carte dans sa main
     */
    public void ditUNO() throws JoueurNonCourantException, JoueurJouePasException, JoueurDireUnoTropTotException {
        if (!this.equals(this.dansLaPartie.joueurCourant())) {
            throw new JoueurNonCourantException("Le joueur " + this.nom + " dit \"UNO !\", alors qu'il n'est pas le joueur courant.", this);
        }
        if (!this.avoirJouerSonTour) {
            throw new JoueurJouePasException("Le joueur " + this.nom + " dit \"UNO !\", alors qu'il n'a pas joué.", this);
        }
        if (this.mainDuJoueur.size() > 1) {
            throw new JoueurDireUnoTropTotException("Le joueur " + this.nom + " dit \"UNO !\", alors qu'il a plus d'une carte en main.", this);
        }

        this.avoirDitUNO = true;
    }

    /**
     * Le joueur met fin à son tour.
     *
     * @throws JoueurNonCourantException       déclenche une exception quand le joueur n'est pas courant
     * @throws JoueurJouePasException          déclenche une exception quand le joueur ne joue pas
     * @throws JoueurOublieDireUnoException    déclenche une exception quand le joueur ne dit pas "UNO !"
     * @throws JoueurEncaisserAttaqueException déclenche quand un joueur pose une carte alors qu'il doit encaisser les attaque dû aux "+2", "+4", etc.
     */
    public void finTour() throws JoueurNonCourantException, JoueurJouePasException, JoueurOublieDireUnoException, JoueurEncaisserAttaqueException {
        if (!this.equals(this.dansLaPartie.joueurCourant())) {
            throw new JoueurNonCourantException("Le joueur " + this.nom + " fini son tour, alors qu'il n'est pas le joueur courant.", this);
        }
        if (!this.avoirJouerSonTour) {
            if (this.dansLaPartie.getNbCarteAPiocher() == 0) {
                // Pour que le joueur puisse finir son tour quand la pioche est vide
                if (!this.dansLaPartie.getPioche().isEmpty()) {
                    throw new JoueurJouePasException("Le joueur " + this.nom + " fini son tour, alors qu'il n'a pas joué.", this);
                }
            } else {
                throw new JoueurEncaisserAttaqueException("Le joueur " + this.getNom() + " fini son tour, alors qu'il doit encaisser l'attaque.", this);
            }
        }
        if (this.nbCarteEnMain() == 1 && !this.avoirDitUNO) {
            throw new JoueurOublieDireUnoException("Le joueur " + this.nom + " fini son tour, alors qu'il n'a pas dit \"UNO !\".", this);
        }

        this.dansLaPartie.joueurCourantSuivant();
        this.avoirJouerSonTour = false;
        this.avoirDitUNO = false;

        if (this.dansLaPartie.isPasserTourActif()) {
            this.dansLaPartie.joueurCourantSuivant();
            this.dansLaPartie.setPasserTourActif(false);
        }
    }
}
