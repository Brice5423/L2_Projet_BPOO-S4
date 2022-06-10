package home.metier.carte.carteAEffetType;

import home.enumeration.ECarteCouleur;
import home.metier.carte.CarteAEffet;
import home.interfaces.carte.IChangerSens;
import home.metier.Partie;

/**
 * Class qui définie une carte changement de sens.
 */
public class CarteChangerSens extends CarteAEffet implements IChangerSens {

    /**
     * Constructeur pour une carte changement de sens.
     *
     * @param couleur couleur de la carte.
     */
    public CarteChangerSens(ECarteCouleur couleur) {
        super(couleur, ("carte_Change_" + couleur.getCouleur() + ".png"));
    }

    @Override
    public void changerSens(Partie partieEnCours) {
        partieEnCours.inverseSensPartie();
    }

    @Override
    public void appliquerEffet(Partie partieEnCours) {
        this.changerSens(partieEnCours);
    }

    /**
     * Fonction redéfinie (@Override).
     * Renvoie la carte changement de sens sous un format de chaine de caractère String.
     *
     * @return la carte basique en String
     */
    @Override
    public String toString() {
        return "CarteChangerSens{" +
                "couleur = " + this.getCouleur() +
                "}";
    }

    /**
     * Fonction redéfinie (@Override).
     * Vérifie l'égalité entre 2 CarteChangerSens.
     * L'égalité entre les deux CarteChangerSens est bonne s'ils ont la même couleur
     *
     * @param o l'objet CarteChangerSens qui est vérifié par l'égalité
     * @return true : sont égaux / false : ne sont pas égaux
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        CarteChangerSens that = (CarteChangerSens) o;

        return (this.getCouleur() == that.getCouleur());
    }
}
