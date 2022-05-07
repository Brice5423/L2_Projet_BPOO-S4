package home.expert;

import home.enumeration.ECarteCouleur;
import home.metier.carte.Carte;
import home.metier.carte.CarteAEffet;

public class ExpertEffetSurEffet extends Expert {

    public ExpertEffetSurEffet() {
        super();
    }

    @Override
    public boolean etreBonExpert(Carte carteAPoser, Carte carteReference) {
        return ((carteAPoser instanceof CarteAEffet) && (carteReference instanceof CarteAEffet));
    }

    @Override
    public boolean etreCoupValide(Carte carteAPoser, Carte carteReference) {
        // TODO etreCoupValide : voir comment faire pour que 2 même cartes puisse  être poser
        return ((carteAPoser.getCouleur() == carteReference.getCouleur()) || (carteAPoser.getCouleur() == ECarteCouleur.NOIR));
    }
}
