package home.metier;

import home.metier.carte.Carte;

import java.util.ArrayList;

public class Joueur {
    private String nom;
    private ArrayList<Carte> mainDuJoueur;
    private int nbVictory;

    public Joueur(String nom) {
        this.setNom(nom);
        this.mainDuJoueur = new ArrayList<Carte>();
        this.nbVictory = 0;
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

    public int getNbVictory() {
        return this.nbVictory;
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
                ", nbVictory = " + nbVictory +
                ", mainDuJoueur = \n" + mainDuJoueur +
                '}';
    }

    /**
     * Mettre la carte de la pioche dans la main du joueur
     *
     * @param cartePioche Carte récupérer dans la pioche
     */
    public void recupererCarte(Carte cartePioche) {
        this.mainDuJoueur.add(cartePioche);
    }

    /**
     * @return Renvoie une carte du joueur
     */
    public Carte poserCarte(Carte carteChoisieParJoueur) {
        // @TODO poserCarte : a completé avec exception

        if (!this.mainDuJoueur.contains(carteChoisieParJoueur)) {
            // TODO faire une exception carte ou un truc du style
            throw new IllegalArgumentException("carte choisie par le joueur n'est pas dans sa main");
        }

        this.mainDuJoueur.remove(carteChoisieParJoueur);

        return carteChoisieParJoueur;
    }

    public void avoirGagner() {
        this.nbVictory++;
    }

    public int nbCarteEnMain() {
        return this.mainDuJoueur.size();
    }

    public void afficheCarteEnMain() {
        System.out.println(this.mainDuJoueur);
    }

    public void ditUNO() {
        // TODO ditUNO
    }

    public void finTour(Partie partieEnCours) {
        // TODO finTour
    }
}
