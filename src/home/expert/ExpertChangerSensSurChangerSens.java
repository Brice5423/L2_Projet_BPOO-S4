package home.expert;

import home.metier.carte.Carte;
import home.metier.carte.carteAEffetType.CarteChangerSens;

/**
 * Class pour l'expert lorsqu'on a une carte changer sens sur une carte changer de sens.
 */
public class ExpertChangerSensSurChangerSens extends Expert {

    public ExpertChangerSensSurChangerSens() {
        super();
    }

    @Override
    public boolean etreBonExpert(Carte carteJoueur, Carte carteDepot, int nbCarteAPiocher) {
        return ((carteJoueur instanceof CarteChangerSens) && (carteDepot instanceof CarteChangerSens));
    }

    @Override
    public boolean etreCoupValide(Carte carteJoueur, Carte carteDepot) {
        return true;
    }
}
