package home.expert;

import home.metier.carte.Carte;
import home.metier.carte.carteAEffetType.CartePasserTour;

public class ExpertPasserTourSurPasserTour extends Expert {

    public ExpertPasserTourSurPasserTour() {
        super();
    }

    @Override
    public boolean etreBonExpert(Carte carteJoueur, Carte carteDepot, int nbCarteAPiocher) {
        return ((carteJoueur instanceof CartePasserTour) && (carteDepot instanceof CartePasserTour) && nbCarteAPiocher == 0);
    }

    @Override
    public boolean etreCoupValide(Carte carteJoueur, Carte carteDepot) {
        return true;
    }
}
