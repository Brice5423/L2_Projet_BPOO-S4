package home.expert;

import home.enumeration.ECarteCouleur;
import home.metier.carte.Carte;
import home.metier.carte.CarteAEffet;
import home.metier.carte.carteAEffetType.CarteChangerSens;
import home.metier.carte.carteAEffetType.CartePasserTour;
import home.metier.carte.carteAEffetType.CartePlusDeux;

public class ExpertEffetSurEffet extends Expert {
    // TODO : On change tout !!!! (carteAEffet sur même carteAEffet

    public ExpertEffetSurEffet() {
        super();
    }

    @Override
    public boolean etreBonExpert(Carte carteJoueur, Carte carteDepot) {
        return ((carteJoueur instanceof CarteAEffet) && (carteDepot instanceof CarteAEffet));
    }

    @Override
    public boolean etreCoupValide(Carte carteJoueur, Carte carteDepot) {
        // TODO etreCoupValide : voir comment faire pour que 2 même cartes puisse  être poser
        boolean etreMemeCouleur;
        boolean etreCouleurNoir;
        boolean etreDeuxChangerSens;
        boolean etreDeuxPasserTour;
        boolean etreDeuxPlusDeux;

        etreMemeCouleur = (carteJoueur.getCouleur() == carteDepot.getCouleur());
        etreCouleurNoir = (carteJoueur.getCouleur() == ECarteCouleur.NOIR);

        etreDeuxChangerSens = ((carteJoueur instanceof CarteChangerSens) && (carteDepot instanceof CarteChangerSens));
        etreDeuxPasserTour = ((carteJoueur instanceof CartePasserTour) && (carteDepot instanceof CartePasserTour));
        etreDeuxPlusDeux = ((carteJoueur instanceof CartePlusDeux) && (carteDepot instanceof CartePlusDeux));

        return (etreMemeCouleur || etreCouleurNoir || etreDeuxChangerSens || etreDeuxPasserTour || etreDeuxPlusDeux);
    }
}
