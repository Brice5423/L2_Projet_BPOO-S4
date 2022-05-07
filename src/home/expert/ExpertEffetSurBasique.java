package home.expert;

import home.enumeration.ECarteCouleur;
import home.metier.carte.Carte;
import home.metier.carte.CarteAEffet;
import home.metier.carte.CarteBasique;

public class ExpertEffetSurBasique extends Expert {

    public ExpertEffetSurBasique() {
        super();
    }

    @Override
    public boolean etreBonExpert(Carte carteAPoser, Carte carteReference) {
        return ((carteAPoser instanceof CarteAEffet) && (carteReference instanceof CarteBasique));
    }

    @Override
    public boolean etreCoupValide(Carte carteAPoser, Carte carteReference) {
        return ((carteAPoser.getCouleur() == carteReference.getCouleur()) || (carteAPoser.getCouleur() == ECarteCouleur.NOIR));
    }
}
