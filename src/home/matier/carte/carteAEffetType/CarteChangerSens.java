package home.matier.carte.carteAEffetType;

import home.interfaces.carte.IChangerSens;
import home.matier.carte.CarteAEffet;

public class CarteChangerSens extends CarteAEffet implements IChangerSens {

    @Override
    public boolean changerSens(boolean etreSensHoraire) {
        return !etreSensHoraire;
    }
}
