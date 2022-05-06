package home.metier.carte;

import home.enumeration.ECarteCouleur;
import home.metier.Partie;

public abstract class CarteAEffet extends Carte {

    public CarteAEffet(ECarteCouleur carteCouleur) {
        super(carteCouleur);
    }

    public abstract void appliquerEffet(Partie partieEnCours);
}
