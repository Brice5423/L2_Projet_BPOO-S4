package home.expert;

import home.enumeration.ECarteCouleur;
import home.metier.carte.Carte;
import home.metier.carte.CarteAEffet;

/**
 * Class pour l'expert lorsqu'on a une carte effet sur une autre carte à effet différente.
 */
public class ExpertEffetSurEffetDifferent extends Expert {

    public ExpertEffetSurEffetDifferent() {
        super();
    }

    @Override
    public boolean etreBonExpert(Carte carteJoueur, Carte carteDepot) {
        return ((carteJoueur instanceof CarteAEffet) && (carteDepot instanceof CarteAEffet)
                && (carteJoueur.getClass() != carteDepot.getClass()));
    }

    @Override
    public boolean etreCoupValide(Carte carteJoueur, Carte carteDepot, int nbCarteAPiocher) {
        return (((carteJoueur.getCouleur() == carteDepot.getCouleur()) || (carteJoueur.getCouleur() == ECarteCouleur.NOIR))
                && (nbCarteAPiocher == 0));
    }
}
