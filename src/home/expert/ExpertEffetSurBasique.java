package home.expert;

import home.enumeration.ECarteCouleur;
import home.metier.carte.Carte;
import home.metier.carte.CarteAEffet;
import home.metier.carte.CarteBasique;

/**
 * Class pour l'expert lorsqu'on a une carte à effet sur une carte basique.
 */
public class ExpertEffetSurBasique extends Expert {

    public ExpertEffetSurBasique() {
        super();
    }

    @Override
    public boolean etreBonExpert(Carte carteJoueur, Carte carteDepot) {
        return ((carteJoueur instanceof CarteAEffet) && (carteDepot instanceof CarteBasique));
    }

    @Override
    public boolean etreCoupValide(Carte carteJoueur, Carte carteDepot, int nbCarteAPiocher) {
        return ((carteJoueur.getCouleur() == carteDepot.getCouleur()) || (carteJoueur.getCouleur() == ECarteCouleur.NOIR));
    }
}
