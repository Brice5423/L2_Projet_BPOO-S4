package home.expert;

import home.excepcion.ExpertManquantException;
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

    public void ajoutExpert(Expert newExpert) {
        if (this.avoirUnSuivant()) {
            this.getSuivant().ajoutExpert(newExpert);

        } else {
            this.suivant = newExpert;
        }
    }

    public boolean peutEtrePoser(Carte carteAPoser, Carte carteReference) throws Exception {
        if (this.etreBonExpert(carteAPoser, carteReference)) {
            return etreCoupValide(carteAPoser, carteReference);

        } else if (this.avoirUnSuivant()) {
            return this.getSuivant().peutEtrePoser(carteAPoser, carteReference);

        } else {
            throw new ExpertManquantException(this);
        }
    }

    public abstract boolean etreBonExpert(Carte carteAPoser, Carte carteReference);

    public abstract boolean etreCoupValide(Carte carteAPoser, Carte carteReference);
}
