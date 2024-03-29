package home.expert;

import home.metier.carte.Carte;
import home.metier.carte.CarteBasique;

/**
 * Class pour l'expert lorsqu'on a une carte basique sur une carte basique.
 */
public class ExpertBasiqueSurBasique extends Expert {

    public ExpertBasiqueSurBasique() {
        super();
    }

    @Override
    public boolean etreBonExpert(Carte carteJoueur, Carte carteDepot) {
        return ((carteJoueur instanceof CarteBasique) && (carteDepot instanceof CarteBasique));
    }

    @Override
    public boolean etreCoupValide(Carte carteJoueur, Carte carteDepot, int nbCarteAPiocher) {
        CarteBasique carteJoueurB = (CarteBasique) carteJoueur;
        CarteBasique carteDepotB = (CarteBasique) carteDepot;

        return ((carteJoueurB.getCouleur() == carteDepotB.getCouleur()) || (carteJoueurB.getValeur() == carteDepotB.getValeur()));
    }
}
