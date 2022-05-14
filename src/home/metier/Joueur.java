package home.metier;

import home.exception.*;
import home.metier.carte.Carte;

import java.util.ArrayList;

public class Joueur {
    private String nom;
    private ArrayList<Carte> mainDuJoueur;
    private Partie dansLaPartie;
    private boolean avoirJoueSonTour;
    private boolean avoirDitUNO;
    private int nbVictoire;

    private Joueur() {
        this.nom = null;
        this.mainDuJoueur = new ArrayList<Carte>();
        this.dansLaPartie = null;
        this.avoirJoueSonTour = false;
        this.avoirDitUNO = false;
        this.nbVictoire = 0;
    }

    public Joueur(String nom) {
        this.setNom(nom);
        this.mainDuJoueur = new ArrayList<Carte>();
        this.dansLaPartie = null;
        this.avoirJoueSonTour = false;
        this.avoirDitUNO = false;
        this.nbVictoire = 0;
    }

    public String getNom() {
        return nom;
    }

    private void setNom(String nom) {
        if (nom.isBlank())
            throw new IllegalArgumentException("Le nom du joueur est vide");

        this.nom = nom;
    }

    public void setAvoirJouerSonTour(boolean a) {
        this.avoirJoueSonTour = a;
    }

    public ArrayList<Carte> getMainDuJoueur() {
        return this.mainDuJoueur;
    }


    private void setMainDuJoueur(ArrayList<Carte> mainDuJoueur) {
        this.mainDuJoueur = mainDuJoueur;
    }

    public Partie getDansLaPartie() {
        return this.dansLaPartie;
    }

    public void setDansLaPartie(Partie dansLaPartie) {
        this.dansLaPartie = dansLaPartie;
    }

    public boolean getAvoirJoueSonTour() {
        return this.avoirJoueSonTour;
    }

    private void setAvoirJoueSonTour(boolean avoirJoueSonTour) {
        this.avoirJoueSonTour = avoirJoueSonTour;
    }

    public boolean getAvoirDitUNO() {
        return this.avoirDitUNO;
    }

    private void setAvoirDitUNO(boolean avoirDitUNO) {
        this.avoirDitUNO = avoirDitUNO;
    }

    public int getNbVictoire() {
        return this.nbVictoire;
    }

    private void setNbVictoire(int nbVictoire) {
        this.nbVictoire = nbVictoire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;

        Joueur joueur = (Joueur) o;

        return this.nom.equals(joueur.getNom());
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "nom = '" + nom + '\'' +
                ", nbVictory = " + nbVictoire +
                ", mainDuJoueur = \n" + mainDuJoueur +
                '}';
    }

    public Joueur copyJoueur() {
        Joueur copieJoueur = new Joueur();

        copieJoueur.setNom(this.nom);
        copieJoueur.getMainDuJoueur().addAll(this.mainDuJoueur);
        copieJoueur.setDansLaPartie(this.dansLaPartie);
        copieJoueur.setAvoirJoueSonTour(this.avoirJoueSonTour);
        copieJoueur.setAvoirDitUNO(this.avoirDitUNO);
        copieJoueur.setNbVictoire(this.nbVictoire);

        return copieJoueur;
    }

    /**
     * Mettre la carte de la pioche dans la main du joueur
     */
    public void piocherCarte() throws JoueurNonCourantException, PartieException, JoueurJoueMultipleException {
        if (!this.equals(this.dansLaPartie.joueurCourant())) {
            throw new JoueurNonCourantException("Le joueur " + this.nom + " n'est pas le joueur courant", this);
        }
        if (this.dansLaPartie.getPioche().isEmpty()) {
            throw new PartieException("Le joueur " + this.nom + " veut prendre une carte dans la pioche vide");
        }
        if (this.avoirJoueSonTour) {
            throw new JoueurJoueMultipleException("Le joueur " + this.nom + " n'a pas le droit de piocher, il a déjà joué", this);
        }

        this.mainDuJoueur.add(this.dansLaPartie.retirerCartePioche());
        this.dansLaPartie.retirerCartePioche();

        this.avoirJoueSonTour = true;
    }

    public void piocherCarte(Carte carteDonnee) throws JoueurJoueMultipleException, JoueurNonCourantException {
        if (!this.equals(this.dansLaPartie.joueurCourant())) {
            throw new JoueurNonCourantException("Le joueur " + this.nom + " n'est pas le joueur courant", this);
        }
        if (this.avoirJoueSonTour) {
            throw new JoueurJoueMultipleException("Le joueur " + this.nom + " n'a pas le droit de piocher, il a déjà joué", this);
        }

        this.mainDuJoueur.add(carteDonnee);
        this.avoirJoueSonTour = true;
    }

    public void donnerCarte() throws PartieException {
        if (this.dansLaPartie.getPioche().isEmpty()) {
            throw new PartieException("Le joueur " + this.nom + " veut prendre une carte dans la pioche vide");
        }

        this.mainDuJoueur.add(this.dansLaPartie.retirerCartePioche());
    }

    public void donnerCarte(Carte carteDonnee) {
        this.mainDuJoueur.add(carteDonnee);
    }

    public void poserCarte(Carte carteChoisieParJoueur) throws JoueurNonCourantException, JoueurException, JoueurJoueMultipleException, JoueurCarteIllegalException, JoueurOublieDireUnoException, JoueurJouePasException, ExpertManquantException, PartieException {
        if (!this.equals(this.dansLaPartie.joueurCourant())) {
            throw new JoueurNonCourantException("Le joueur " + this.nom + " joue alors que ce n'est pas son tour", this);
        }
        if (!this.mainDuJoueur.contains(carteChoisieParJoueur)) {
            // TODO poserCarte : JoueurException !!!
            throw new JoueurException("Carte choisie par le joueur n'est pas dans sa main", this);
        }
        if (this.avoirJoueSonTour) {
            throw new JoueurJoueMultipleException("Le joueur " + this.nom + " n'a pas le droit de poser ca carte, il a déjà jouer", this);
        }

        this.dansLaPartie.deposerCarteTas(carteChoisieParJoueur);
        this.mainDuJoueur.remove(carteChoisieParJoueur);
        this.avoirJoueSonTour = true;
    }

    private void joueurCourantRecupererNCarte(int nCarteARecuperer) throws PartieException {
        for (int i = 0; i < nCarteARecuperer; i++) {
            this.donnerCarte();
        }
    }

    /**
     * punie le joueur en lui donnant 2 cartes.
     */
    public void punition() throws PartieException, JoueurOublieDireUnoException, JoueurNonCourantException, JoueurJouePasException {
        this.joueurCourantRecupererNCarte(2);
        this.avoirJoueSonTour = true;

        if (this.equals(this.dansLaPartie.joueurCourant())) {
            this.finTour();
        }
    }

    public void encaisseAttaque() throws PartieException, JoueurOublieDireUnoException, JoueurJouePasException, JoueurNonCourantException {
        this.joueurCourantRecupererNCarte(this.dansLaPartie.getNbCarteAPiocher());
        this.finTour();
    }

    public int nbCarteEnMain() {
        return this.mainDuJoueur.size();
    }

    public void afficheCarteEnMain() {
        System.out.println(this.mainDuJoueur);
    }

    public void ditUNO() throws JoueurNonCourantException, JoueurJouePasException {
        if (!this.equals(this.dansLaPartie.joueurCourant())) {
            throw new JoueurNonCourantException("Le joueur " + this.nom + " n'est pas le joueur courant", this);
        }
        if (!this.avoirJoueSonTour) {
            throw new JoueurJouePasException("Le joueur " + this.nom + " n'a pas joue, elle ne peut pas dire \"UNO !\"", this);
        }

        this.avoirDitUNO = true;
    }

    public void finTour() throws JoueurNonCourantException, JoueurJouePasException, JoueurOublieDireUnoException {
        if (!this.equals(this.dansLaPartie.joueurCourant())) {
            throw new JoueurNonCourantException("Le joueur " + this.nom + " n'est pas le joueur courant donc pas de fin de tour", this);
        }
        if (!this.avoirJoueSonTour) {
            throw new JoueurJouePasException("Le joueur " + this.nom + " n'a pas joue, il ne peut pas mettre fin à son tour", this);
        }
        if (this.nbCarteEnMain() == 1 && !this.avoirDitUNO) {
            throw new JoueurOublieDireUnoException("Le joueur " + this.nom + " à oublie de dire \"UNO !\" avant de mettre fin à son tour", this);
        }

        this.dansLaPartie.joueurCourantSuivant();
        this.avoirJoueSonTour = false;
        this.avoirDitUNO = false;

        if (this.dansLaPartie.isPasserTourActif()) {
            this.dansLaPartie.joueurCourantSuivant();
            this.dansLaPartie.setPasserTourActif(false);
        }
    }

    public void avoirGagner() {
        this.nbVictoire++;
    }
}
