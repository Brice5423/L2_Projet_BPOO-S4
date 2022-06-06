package home.metier.carte.carteAEffetType;

import home.enumeration.ECarteCouleur;
import home.interfaces.carte.IPasserTour;
import home.metier.Partie;
import home.metier.carte.CarteAEffet;

/**
 * Class qui définie une carte passer tour.
 */
public class CartePasserTour extends CarteAEffet implements IPasserTour {

    /**
     * Constructeur pour une carte passer tour.
     * @param couleur couleur de la carte
     */
    public CartePasserTour(ECarteCouleur couleur) {
        super(couleur);
    }

    @Override
    public void passerTour(Partie partieEnCours) {
        partieEnCours.setPasserTourActif(true);
    }

    @Override
    public void appliquerEffet(Partie partieEnCours) {
        this.passerTour(partieEnCours);
    }

    /**
     * Fonction redéfinie (@Override).
     * Renvoie la carte passer tour sous un format de chaine de caractère String.
     * @return la carte passer tour en String
     */
    @Override
    public String toString() {
        return "CartePasserTour{" +
                "couleur = " + this.getCouleur() +
                "}";
    }

    /**
     * Fonction redéfinie (@Override).
     * Vérifie l'égalité entre 2 CartePasserTour.
     * L'égalité entre les deux CartePasserTour est bonne quand ils ont la même couleur
     * @param o l'objet CartePasserTour qui est vérifié par l'égalité
     * @return true : sont égaux / false : ne sont pas égaux
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        CartePasserTour that = (CartePasserTour) o;

        return (this.getCouleur() == that.getCouleur());
    }
}
