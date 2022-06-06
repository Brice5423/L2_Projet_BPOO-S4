package home.expert;

import home.exception.ExpertManquantException;
import home.metier.carte.Carte;

/**
 * Class abstract qui définie les différentes règles des Experts pour pouvoir poser une carte ou non.
 */
public abstract class Expert {
    /** Contient l'expert suivant */
    private Expert suivant;

    /**
     * Constructeur pour faire un expert.
     */
    Expert() {
        this.suivant = null;
    }

    /**
     * Getter pour avoir l'expert suivant.
     * @return
     */
    private Expert getSuivant() {
        return this.suivant;
    }

    /**
     * Renvoie l'expert suivant.
     * @return l'expert suivant
     */
    private boolean avoirUnSuivant() {
        return this.suivant != null;
    }

    /**
     * Ajout un expert à la fin de la liste d'expert.
     * @param newExpert nouvel expert a ajouté à la chaine.
     */
    private void ajoutExpert(Expert newExpert) {
        if (this.avoirUnSuivant()) {
            this.getSuivant().ajoutExpert(newExpert);

        } else {
            this.suivant = newExpert;
        }
    }

    /**
     * Fonction static qui initialise tous les experts qu'on a besoin pour le UNO.
     * @return l'expert final
     */
    public static Expert initialiseTousLesExperts() {
        Expert lesExperts;

        lesExperts = new ExpertBasiqueSurBasique();
        lesExperts.ajoutExpert(new ExpertBasiqueSurEffet());
        lesExperts.ajoutExpert(new ExpertEffetSurBasique());
        lesExperts.ajoutExpert(new ExpertEffetSurEffetDifferent());
        lesExperts.ajoutExpert(new ExpertChangerSensSurChangerSens());
        lesExperts.ajoutExpert(new ExpertPasserTourSurPasserTour());
        lesExperts.ajoutExpert(new ExpertPlusDeuxSurPlusDeux());

        return lesExperts;
    }

    /**
     * Parcourt tous les experts pour savoir si on peut poser une carte.
     * @param carteJoueur carte du joueur a posé
     * @param carteDepot carte au-dessus du depot
     * @param nbCarteAPiocher nombre de cartes a pioché à cause d'un "+2", "+4" et etc.
     * @return true : peut être posé / false : ne peut pas être posé
     * @throws ExpertManquantException déclenche une exception si une carte peut-être poser ou pas ou manquant selon un expert de vérification
     */
    public boolean peutEtrePoser(Carte carteJoueur, Carte carteDepot, int nbCarteAPiocher) throws ExpertManquantException {
        if (this.etreBonExpert(carteJoueur, carteDepot, nbCarteAPiocher)) {
            return this.etreCoupValide(carteJoueur, carteDepot);

        } else if (this.avoirUnSuivant()) {
            return this.getSuivant().peutEtrePoser(carteJoueur, carteDepot, nbCarteAPiocher);

        } else {
            throw new ExpertManquantException(carteJoueur, carteDepot);
        }
    }

    /**
     * Fonction abstract qui verifier si c'est le bon expert à utiliser.
     * @param carteJoueur carte du joueur à poser
     * @param carteDepot carte au-dessus du depot
     * @param nbCarteAPiocher nombre de cartes a pioché à cause d'un "+2", "+4" et etc.
     * @return true : bon expert / false : mauvais expert
     */
    public abstract boolean etreBonExpert(Carte carteJoueur, Carte carteDepot, int nbCarteAPiocher);

    /**
     * Fonction abstract qui vérifie si la carte du joueur n'est pas un coup illegal.
     * @param carteJoueur carte du joueur a posé
     * @param carteReference carte au-dessus du depot
     * @return true : carte légale / false : carte illégale
     */
    public abstract boolean etreCoupValide(Carte carteJoueur, Carte carteReference);
}
