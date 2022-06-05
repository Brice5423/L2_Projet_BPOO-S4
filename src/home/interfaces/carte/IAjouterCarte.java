package home.interfaces.carte;

import home.metier.Partie;

public interface IAjouterCarte {
    /**
     * Fonction abstract qui permet d'augmenter le nombre de cartes à récupérer à cause d'un "+2", "+4" et etc.
     * @param partieEnCours partie de la carte
     */
    void ajouterCarte(Partie partieEnCours);
}
