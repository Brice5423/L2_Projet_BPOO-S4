package home.interfaces.carte;

import home.metier.Partie;

public interface IPasserTour {
    /**
     * Fonction abstract qui permet de faire passer son tour au prochain joueur.
     * @param partieEnCours partie de la carte
     */
    void passerTour(Partie partieEnCours);
}
