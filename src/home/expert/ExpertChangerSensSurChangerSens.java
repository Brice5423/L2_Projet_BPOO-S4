package home.expert;

import home.metier.carte.Carte;
import home.metier.carte.carteAEffetType.CarteChangerSens;

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
