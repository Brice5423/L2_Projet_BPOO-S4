package home.expert;

import home.exception.ExpertManquantException;
import home.metier.carte.Carte;

public abstract class Expert {
    private Expert suivant;

    Expert() {
        this.suivant = null;
    }

    private Expert getSuivant() {
        return this.suivant;
    }

    private boolean avoirUnSuivant() {
        return this.suivant != null;
    }

    private void ajoutExpert(Expert newExpert) {
        if (this.avoirUnSuivant()) {
            this.getSuivant().ajoutExpert(newExpert);

        } else {
            this.suivant = newExpert;
        }
    }

    public static Expert initialiseTousLesExperts() {
        Expert lesExperts;

        lesExperts = new ExpertBasiqueSurBasique();
        lesExperts.ajoutExpert(new ExpertBasiqueSurEffet());
        lesExperts.ajoutExpert(new ExpertEffetSurBasique());
        lesExperts.ajoutExpert(new ExpertEffetSurEffet());

        return lesExperts;
    }

    public boolean peutEtrePoser(Carte carteJoueur, Carte carteDepot) throws ExpertManquantException {
        if (this.etreBonExpert(carteJoueur, carteDepot)) {
            return etreCoupValide(carteJoueur, carteDepot);

        } else if (this.avoirUnSuivant()) {
            return this.getSuivant().peutEtrePoser(carteJoueur, carteDepot);

        } else {
            throw new ExpertManquantException(carteJoueur, carteDepot);
        }
    }

    public abstract boolean etreBonExpert(Carte carteAPoser, Carte carteReference);

    public abstract boolean etreCoupValide(Carte carteAPoser, Carte carteReference);
}
