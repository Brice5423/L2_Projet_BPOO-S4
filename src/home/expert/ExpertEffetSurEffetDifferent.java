package home.expert;

import home.enumeration.ECarteCouleur;
import home.metier.carte.Carte;
import home.metier.carte.CarteAEffet;

public class ExpertEffetSurEffetDifferent extends Expert {

    public ExpertEffetSurEffetDifferent() {
        super();
    }

    @Override
    public boolean etreBonExpert(Carte carteJoueur, Carte carteDepot) {
        return ((carteJoueur instanceof CarteAEffet) && (carteDepot instanceof CarteAEffet) && (carteJoueur.getClass() != carteDepot.getClass()));
    }

    @Override
    public boolean etreCoupValide(Carte carteJoueur, Carte carteDepot) {
        return ((carteJoueur.getCouleur() == carteDepot.getCouleur()) || (carteJoueur.getCouleur() == ECarteCouleur.NOIR));
    }
}
