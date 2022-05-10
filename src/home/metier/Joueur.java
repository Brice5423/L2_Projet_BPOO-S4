package home.metier;

import home.exception.*;
import home.metier.carte.Carte;

import java.util.ArrayList;

public class Joueur {
    private String nom;
    private ArrayList<Carte> mainDuJoueur;
    private Partie dansLaPartie;
    private boolean avoirJoueSonTour;
    private int nbVictoire;

    private Joueur() {
        this.nom = null;
        this.mainDuJoueur = new ArrayList<Carte>();
        this.dansLaPartie = null;
        this.avoirJoueSonTour = false;
        this.nbVictoire = 0;
    }

    public Joueur(String nom) {
        this.setNom(nom);
        this.mainDuJoueur = new ArrayList<Carte>();
        this.dansLaPartie = null;
        this.avoirJoueSonTour = false;
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

    public ArrayList<Carte> getMainDuJoueur() {
        return this.mainDuJoueur;
    }


    private void setMainDuJoueur(ArrayList<Carte> mainDuJoueur) {
        this.mainDuJoueur = mainDuJoueur;
    }

    public Partie getDansLaPartie() {
        return this.dansLaPartie;
    }

    public void setDansPartie(Partie dansLaPartie) {
        this.dansLaPartie = dansLaPartie;
    }

    public int getNbVictoire() {
        return this.nbVictoire;
    }

    private void setNbVictoire(int nbVictoire) {
        this.nbVictoire = nbVictoire;
    }

    public boolean isAvoirJoueSonTour() {
        return avoirJoueSonTour;
    }

    private void setAvoirJoueSonTour(boolean avoirJoueSonTour) {
        this.avoirJoueSonTour = avoirJoueSonTour;
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
        copieJoueur.setDansPartie(this.dansLaPartie);
        copieJoueur.setNbVictoire(this.nbVictoire);
        copieJoueur.setAvoirJoueSonTour(this.avoirJoueSonTour);

        return copieJoueur;
    }

    /**
     * Mettre la carte de la pioche dans la main du joueur
     */
    public void piocherCarte() throws JoueurJoueMultipleException {
        try {
            if (this.dansLaPartie.getPioche().isEmpty()) {
                throw new PartieException("Le joueur " + this.nom + " veut prendre une carte dans la pioche vide");
            }
            if (this.avoirJoueSonTour) {
                throw new JoueurJoueMultipleException("Le joueur " + this.nom + " n'a pas le droit de piocher, il a déjà jouer", this);
            }

           this.mainDuJoueur.add(this.dansLaPartie.retirerCartePioche());
            this.dansLaPartie.retirerCartePioche();

            this.avoirJoueSonTour = true;

        } catch (PartieException e) {
            System.out.println(e);
        }
    }

    public void piocherCarte(Carte carteDonnee) throws JoueurJoueMultipleException {
        if (this.avoirJoueSonTour) {
            throw new JoueurJoueMultipleException("Le joueur " + this.nom + " n'a pas le droit de piocher, il a déjà jouer", this);
        }

        this.mainDuJoueur.add(carteDonnee);
        this.avoirJoueSonTour = true;
    }

    public void donnerCarte() {
        try {
            if (this.dansLaPartie.getPioche().isEmpty()) {
                throw new PartieException("Le joueur " + this.nom + " veut prendre une carte dans la pioche vide");
            }

            this.mainDuJoueur.add(this.dansLaPartie.retirerCartePioche());

        } catch (PartieException e) {
            System.out.println(e);
        }
    }

    public void donnerCarte(Carte carteDonnee) {
        this.mainDuJoueur.add(carteDonnee);
    }

    /**
     * punie le joueur en lui donnant 2 cartes.
     */
    public void punition() {
        // TODO punition : regarder si on fait un finiTour pour les joueur courant
        System.out.println("Le joueur " + this.nom + " est puni(e), " + this.nom + " pioche 2 cartes");
        this.donnerCarte();
        this.donnerCarte();
    }

    public void poserCarte(Carte carteChoisieParJoueur) throws JoueurCarteIllegalException, JoueurJoueMultipleException {
        try {
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

        } catch (JoueurException | JoueurNonCourantException e) {
            System.out.println(e);
        }
    }

    public int nbCarteEnMain() {
        return this.mainDuJoueur.size();
    }

    public void afficheCarteEnMain() {
        System.out.println(this.mainDuJoueur);
    }

    public void ditUNO() {
        // TODO ditUNO
        try {
            if (!this.equals(this.dansLaPartie.joueurCourant())) {
                throw new JoueurNonCourantException("Le joueur " + this.nom + " n'est pas le joueur courant", this);
            }

            System.out.println(this.nom + " dit UNO !!!");

        } catch (JoueurNonCourantException e) {
            this.punition();
            System.out.println(e);
        }
    }

    public void finTour() {
        try {
            if (!this.equals(this.dansLaPartie.joueurCourant())) {
                throw new JoueurNonCourantException("Le joueur " + this.nom + " n'est pas le joueur courant donc pas de fin de tour", this);
            }

            this.dansLaPartie.joueurSuivant();
            this.avoirJoueSonTour = false;

        } catch (JoueurNonCourantException e) {
            this.punition();
            System.out.println(e);
        }
    }

    public void avoirGagner() {
        this.nbVictoire++;
    }
}
