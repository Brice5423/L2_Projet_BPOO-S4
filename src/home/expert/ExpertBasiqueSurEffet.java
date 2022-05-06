package home.expert;

import home.metier.carte.Carte;
import home.metier.carte.CarteAEffet;
import home.metier.carte.CarteBasique;

public class ExpertBasiqueSurEffet extends Expert {

    public ExpertBasiqueSurEffet() {
        super();
    }

    @Override
    public boolean etreBonExpert(Carte carteAPoser, Carte carteReference) {
        return ((carteAPoser instanceof CarteBasique) && (carteReference instanceof CarteAEffet));
    }

    @Override
    public boolean etreCoupValide(Carte carteAPoser, Carte carteReference) {
        // TODO etreCoupValide
        return false;
    }
}
