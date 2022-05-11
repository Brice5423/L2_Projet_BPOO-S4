/* ************************************** *
 *  Projet BPOO | Par Brice.O & Alicia.O  *
 *  Version Java : 17.0.2                 *
 * ************************************** */

import tests.*;
import tests.controleur.CompteurTest;

public class Main {
    public static void main(String[] args) {
        /* ***** ***** initialisation des compteurs du test ***** ***** */
        CompteurTest compteurTest = new CompteurTest("Tous les tests");

        /* ***** ***** Lancement de tous les tests ***** ***** */

        /* ***** Tests coups légaux avec des cartes simples ***** */
        if (CoupsLegauxCartesSimples.executionDuTest()) {
            compteurTest.testOK();
        } else {
            compteurTest.testFaux();
        }

        /* ***** Tests coups illégaux avec des cartes simples ***** */
        if (CoupsIllegauxCartesSimples.executionDuTest()) {
            compteurTest.testOK();
        } else {
            compteurTest.testFaux();
        }

        if (TestPunition.executionDuTest()) {
            compteurTest.testOK();
        } else {
            compteurTest.testFaux();
        }

        if (TestUno.executionDuTest()) {
            compteurTest.testOK();
        } else {
            compteurTest.testFaux();
        }

        if (TestPasseTour.executionDuTest()) {
            compteurTest.testOK();
        } else {
            compteurTest.testFaux();
        }

        // ...


        /* ***** ***** Affiche le résultat de tout les tests ***** ***** */
        System.out.println("\n\n\t\t\t----- Tous les tests -----");
        compteurTest.afficheResultatsTest();
    }
}
