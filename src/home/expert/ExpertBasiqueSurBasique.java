package home.expert;

import home.metier.carte.Carte;
import home.metier.carte.CarteBasique;

public class ExpertBasiqueSurBasique extends Expert {

    public ExpertBasiqueSurBasique() {
        super();
    }

    @Override
    public boolean etreBonExpert(Carte carteJoueur, Carte carteDepot, int nbCarteAPiocher) {
        return ((carteJoueur instanceof CarteBasique) && (carteDepot instanceof CarteBasique) && (nbCarteAPiocher == 0));
    }

    @Override
    public boolean etreCoupValide(Carte carteJoueur, Carte carteDepot) {
        CarteBasique carteJoueurB = (CarteBasique) carteJoueur;
        CarteBasique carteDepotB = (CarteBasique) carteDepot;

        return ((carteJoueurB.getCouleur() == carteDepotB.getCouleur()) || (carteJoueurB.getValeur() == carteDepotB.getValeur()));
    }
}
