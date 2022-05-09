package home.metier.carte.carteAEffetType;

import home.enumeration.ECarteCouleur;
import home.interfaces.carte.IPasserTour;
import home.metier.Partie;
import home.metier.carte.CarteAEffet;

public class CartePasserTour extends CarteAEffet implements IPasserTour {

    public CartePasserTour(ECarteCouleur couleur) {
        super(couleur);
    }

    @Override
    public void passerTour(Partie partieEnCours) {
        // @TODO passerTour
    }

    @Override
    public void appliquerEffet(Partie partieEnCours) {
        // @TODO appliquerEffet
    }

    @Override
    public String toString() {
        return "CartePasserTour{" +
                "couleur=" + this.getCouleur() +
                "}";
    }

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
