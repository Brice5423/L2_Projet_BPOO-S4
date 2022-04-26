package home.metier.carte.carteAEffetType;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.interfaces.carte.IChangerSens;
import home.metier.Partie;
import home.metier.carte.CarteAEffet;

public class CarteChangerSens extends CarteAEffet implements IChangerSens {

    public CarteChangerSens(ECarteCouleur carteCouleur, ECarteValeur carteValeur) {
        super(carteCouleur, carteValeur);
    }

    @Override
    public void changerSens(Partie partieEnCours) {
        partieEnCours.setEtreSensHoraire(!partieEnCours.getEtreSensHoraire());
    }
}
