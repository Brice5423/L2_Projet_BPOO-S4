package home.metier.carte.carteAEffetType;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.interfaces.carte.IPasserTour;
import home.metier.Partie;
import home.metier.carte.CarteAEffet;

public class PasserTour extends CarteAEffet implements IPasserTour {

    public PasserTour(ECarteCouleur carteCouleur, ECarteValeur carteValeur) {
        super(carteCouleur, carteValeur);
    }

    @Override
    public void passerTour(Partie partieEnCours) {

    }
}
