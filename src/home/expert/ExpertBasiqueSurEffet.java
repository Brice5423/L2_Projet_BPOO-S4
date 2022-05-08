package home.expert;

import home.metier.carte.Carte;
import home.metier.carte.CarteAEffet;
import home.metier.carte.CarteBasique;

public class ExpertBasiqueSurEffet extends Expert {

    public ExpertBasiqueSurEffet() {
        super();
    }

    @Override
    public boolean etreBonExpert(Carte carteJoueur, Carte carteDepot) {
        return ((carteJoueur instanceof CarteBasique) && (carteDepot instanceof CarteAEffet));
    }

    @Override
    public boolean etreCoupValide(Carte carteJoueur, Carte carteDepot) {
        return (carteJoueur.getCouleur() == carteDepot.getCouleur());
    }
}
