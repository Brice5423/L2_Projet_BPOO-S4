package home.expert;

import home.metier.carte.Carte;
import home.metier.carte.CarteAEffet;
import home.metier.carte.CarteBasique;

/**
 * Class pour l'expert lorsqu'on a une carte basique sur une carte à effet.
 */
public class ExpertBasiqueSurEffet extends Expert {

    public ExpertBasiqueSurEffet() {
        super();
    }

    @Override
    public boolean etreBonExpert(Carte carteJoueur, Carte carteDepot) {
        return ((carteJoueur instanceof CarteBasique) && (carteDepot instanceof CarteAEffet));
    }

    @Override
    public boolean etreCoupValide(Carte carteJoueur, Carte carteDepot, int nbCarteAPiocher) {
        return ((carteJoueur.getCouleur() == carteDepot.getCouleur()) && (nbCarteAPiocher == 0));
    }
}
