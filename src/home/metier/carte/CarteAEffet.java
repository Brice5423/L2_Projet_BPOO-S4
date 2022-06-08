package home.metier.carte;

import home.enumeration.ECarteCouleur;
import home.metier.Partie;

/**
 * Class abstract qui défini une carte à effet
 */
public abstract class CarteAEffet extends Carte {

    /**
     * Constructeur d'une carte à effet.
     * @param couleur couleur de la carte
     * @param cheminVersImage chemin vers l'image de la carte
     */
    public CarteAEffet(ECarteCouleur couleur, String cheminVersImage) {
        super(couleur, cheminVersImage);
    }

    /**
     * Fonction abstract qui permet de lancer tous les effets d'une carte à effet.
     * @param partieEnCours partie de la carte
     */
    public abstract void appliquerEffet(Partie partieEnCours);
}
