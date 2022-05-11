package home.metier.carte.carteAEffetType;

import home.enumeration.ECarteCouleur;
import home.metier.carte.CarteAEffet;
import home.interfaces.carte.IChangerSens;
import home.metier.Partie;
import home.metier.carte.CarteBasique;

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
        this.changerSens(partieEnCours);
    }

    @Override
    public String toString() {
        return "CarteChangerSens{" +
                "couleur=" + this.getCouleur() +
                "}";
    }

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
