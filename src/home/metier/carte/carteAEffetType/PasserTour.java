package home.metier.carte.carteAEffetType;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.interfaces.carte.IPasserTour;
import home.metier.Partie;
import home.metier.carte.CarteAEffet;

public class PasserTour extends CarteAEffet implements IPasserTour {

    public PasserTour(ECarteCouleur carteCouleur) {
        super(carteCouleur, ECarteValeur.PASSER_TOUR);
    }

    @Override
    public void passerTour(Partie partieEnCours) {

    }
}
