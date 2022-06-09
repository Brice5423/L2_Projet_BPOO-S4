package home.expert;

import home.enumeration.ECarteCouleur;
import home.metier.carte.Carte;
import home.metier.carte.CarteAEffet;

public class ExpertEffetSurEffetIdentique extends Expert {

    public ExpertEffetSurEffetIdentique() {
        super();
    }

    @Override
    public boolean etreBonExpert(Carte carteJoueur, Carte carteDepot) {
        return ((carteJoueur instanceof CarteAEffet) && (carteJoueur.getClass() == carteDepot.getClass()));
    }

    @Override
    public boolean etreCoupValide(Carte carteJoueur, Carte carteDepot, int nbCarteAPiocher) {
        return true;
    }
}
