package home.metier;

import home.exception.JoueurException;
import home.exception.PartieException;
import home.metier.carte.Carte;

import java.util.ArrayList;

public class Joueur {
    private String nom;
    private ArrayList<Carte> mainDuJoueur;
    private Partie dansLaPartie;
    private int nbVictoire;

    private Joueur() {
        this.nom = null;
        this.mainDuJoueur = new ArrayList<Carte>();
        this.dansLaPartie = null;
        this.nbVictoire = 0;
    }

    public Joueur(String nom) {
        this.setNom(nom);
        this.mainDuJoueur = new ArrayList<Carte>();
        this.dansLaPartie = null;
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

        return copieJoueur;
    }

    /**
     * Mettre la carte de la pioche dans la main du joueur
     */
    public void piocherCarte() {
        try {
            if (this.dansLaPartie.getPioche().isEmpty()) {
                throw new PartieException("Le joueur " + this.nom + " veut prendre une carte dans la pioche vide");
            }
            this.mainDuJoueur.add(this.dansLaPartie.retirerCartePioche());

        } catch (PartieException e) {
            System.out.println(e);
        }
    }

    public void piocherCarte(Carte carteDonnee) {
        // TODO piocherCarte(Carte carteDonnee) : regardé si on vire les carte de la pioche
        /*try {
            if (!this.dansLaPartie.getPioche().contains(carteDonnee)) {
                throw new ParteException("La carte " + carteDonnee + " n'existe pas dans la pioche");
            }
            this.dansLaPartie.getPioche().remove(carteDonnee);

        } catch (ParteException e) {
            System.out.println(e);
        }*/

        this.mainDuJoueur.add(carteDonnee);
    }

    public void poserCarte(Carte carteChoisieParJoueur) {
        try {
            if (!this.equals(this.dansLaPartie.joueurCourant())) {
                throw new JoueurException("Le joueur " + this.nom + " joue alors que ce n'est pas son tour");
            }
            if (!this.mainDuJoueur.contains(carteChoisieParJoueur)) {
                throw new JoueurException("Carte choisie par le joueur n'est pas dans sa main");
            }

            this.dansLaPartie.deposerCarteTas(carteChoisieParJoueur);
            this.mainDuJoueur.remove(carteChoisieParJoueur);

        } catch (JoueurException | PartieException e) {
            System.out.println(e);
        }
    }

    public int nbCarteEnMain() {
        return this.mainDuJoueur.size();
    }

    public void afficheCarteEnMain() {
        System.out.println(this.mainDuJoueur);
    }

    public void finTour() {
        try {
            if (!this.equals(this.dansLaPartie.joueurCourant())) {
                throw new JoueurException("Le joueur " + this.nom + " n'est pas le joueur courant donc pas de fin de tour");
            }
            this.dansLaPartie.joueurSuivant();

        } catch (JoueurException e) {
            this.piocherCarte();
            this.piocherCarte();
            System.out.println(e);
        }
    }

    public void ditUNO() {
        // TODO ditUNO : voir s'il n'y a pas autre chose à faire
        try {
            if (!this.equals(this.dansLaPartie.joueurCourant())) {
                throw new JoueurException("Le joueur " + this.nom + " n'est pas le joueur courant");
            }
            System.out.println(this.nom + " dit UNO !!!");

        } catch (JoueurException e) {
            this.piocherCarte();
            this.piocherCarte();
            System.out.println(e);
        }
    }

    public void avoirGagner() {
        this.nbVictoire++;
    }
}
