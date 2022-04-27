package home.metier.carte;

import home.enumeration.ECarteCouleur;
import home.enumeration.ECarteValeur;
import home.metier.Partie;

public abstract class CarteAEffet extends Carte {

    public CarteAEffet(ECarteCouleur carteCouleur, ECarteValeur carteValeur) {
        super(carteCouleur, carteValeur);
    }

    public abstract void appliquerEffet(Partie partieEnCours);
}
