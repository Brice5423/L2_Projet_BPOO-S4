package home.metier.carte;

import home.enumeration.ECarteCouleur;

/**
 * Class abstract pour définir une carte UNO
 */
public abstract class Carte {
    /** Définit la couleur de la carte */
    private ECarteCouleur couleur;
    /** Définit le chemin pour trouver image de la carte */
    private String cheminVersImage;

    /**
     * Constructeur pour créer une carte.
     * @param couleur couleur de la carte
     * @param cheminVersImage chemin vers l'image de la carte
     */
    public Carte(ECarteCouleur couleur, String cheminVersImage) {
        this.couleur = couleur;
        this.cheminVersImage = cheminVersImage;
    }

    /**
     * Getter pour la couleur de la carte.
     * @return couleur de la carte
     */
    public ECarteCouleur getCouleur() {
        return this.couleur;
    }

    /**
     * Getter pour le chemin de l'image de la carte.
     * @return chemin de l'image
     */
    public String getCheminVersImage() {
        return this.cheminVersImage;
    }
}
