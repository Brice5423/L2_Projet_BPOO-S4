package home.expert;

import home.metier.carte.Carte;
import home.metier.carte.carteAEffetType.CartePasserTour;

/**
 * Class pour l'expert lorsqu'on a une carte passer tour sur une carte passer tour.
 */
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
