package home.expert;

import home.metier.carte.Carte;
import home.metier.carte.carteAEffetType.CartePlusDeux;

/**
 * Class pour l'expert de quand on a une carte plus deux sur une carte plus deux.
 */
public class ExpertPlusDeuxSurPlusDeux extends Expert {

    public ExpertPlusDeuxSurPlusDeux() {
        super();
    }

    @Override
    public boolean etreBonExpert(Carte carteJoueur, Carte carteDepot, int nbCarteAPiocher) {
        // pas besoin de regarder nbCarteAPiocher == ou > à 0, car on l'utilise dans tous les cas
        return ((carteJoueur instanceof CartePlusDeux) && (carteDepot instanceof CartePlusDeux));
    }

    @Override
    public boolean etreCoupValide(Carte carteJoueur, Carte carteDepot) {
        return true;
    }
}
