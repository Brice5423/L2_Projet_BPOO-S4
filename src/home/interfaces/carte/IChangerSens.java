package home.interfaces.carte;

import home.metier.Partie;

public interface IChangerSens {
    /**
     * Fonction abstract qui permet de changer le sens de la partie.
     *
     * @param partieEnCours partie de la carte
     */
    void changerSens(Partie partieEnCours);
}
