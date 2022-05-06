package home.expert;

import home.metier.carte.Carte;
import home.metier.carte.CarteBasique;

public class ExpertBasiqueSurBasique extends Expert {

    public ExpertBasiqueSurBasique() {
        super();
    }

    @Override
    public boolean etreBonExpert(Carte carteAPoser, Carte carteReference) {
        return ((carteAPoser instanceof CarteBasique) && (carteReference instanceof CarteBasique));
    }

    @Override
    public boolean etreCoupValide(Carte carteAPoser, Carte carteReference) {
        // TODO etreCoupValide
        CarteBasique carteAPoserB = (CarteBasique) carteAPoser;
        CarteBasique carteReferenceB = (CarteBasique) carteReference;

        return ((carteAPoserB.getCouleur() == carteReferenceB.getCouleur()) || (carteAPoserB.getValeur() == carteReferenceB.getValeur()));
    }
}
