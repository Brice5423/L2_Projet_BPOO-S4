package home.metier.carte.carteAEffetType;

import home.enumeration.ECarteCouleur;
import home.interfaces.carte.IPasserTour;
import home.metier.Partie;
import home.metier.carte.CarteAEffet;

public class CartePasserTour extends CarteAEffet implements IPasserTour {

    public CartePasserTour(ECarteCouleur carteCouleur) {
        super(carteCouleur);
    }

    @Override
    public void passerTour(Partie partieEnCours) {
        // @TODO passerTour
    }

    @Override
    public void appliquerEffet(Partie partieEnCours) {
        // @TODO appliquerEffet
    }
}
