package home.metier.carte.carteAEffetType;

import home.interfaces.carte.IChangerSens;
import home.metier.carte.CarteAEffet;

public class CarteChangerSens extends CarteAEffet implements IChangerSens {

    @Override
    public boolean changerSens(boolean etreSensHoraire) {
        return !etreSensHoraire;
    }
}
