package home.metier.carte.carteAEffetType;

import home.enumeration.ECarteCouleur;
import home.metier.carte.CarteAEffet;
import home.interfaces.carte.IChangerSens;
import home.metier.Partie;

public class CarteChangerSens extends CarteAEffet implements IChangerSens {

    public CarteChangerSens(ECarteCouleur couleur) {
        super(couleur);
    }

    @Override
    public void changerSens(Partie partieEnCours) {
        partieEnCours.inverseSensPartie();
    }

    @Override
    public void appliquerEffet(Partie partieEnCours) {
        // @TODO appliquerEffet
    }

    @Override
    public String toString() {
        return "CarteChangerSens{" +
                "couleur=" + this.getCouleur() +
                "}";
    }
}
