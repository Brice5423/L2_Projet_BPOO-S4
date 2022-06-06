package tests.controleur;

/**
 * Class pour le contrôleur de réussite du programme.
 */
public class CompteurTest {
    /** Le nom du test */
    private final String nomTest;
    /** Le nombre de tests total */
    private int nbTestTotal;
    /** Le nombre de tests réussi */
    private int nbTestOk;

    /**
     * Constructeur pour faire un suivi d'un test.
     * @param nomTest nom du test
     */
    public CompteurTest(String nomTest) {
        this.nomTest = nomTest;
        this.nbTestTotal = 0;
        this.nbTestOk = 0;
    }

    /**
     * Test valide.
     */
    public void testOK() {
        this.nbTestTotal++;
        this.nbTestOk++;
    }

    /**
     * Test faux.
     */
    public void testFaux() {
        this.nbTestTotal++;
    }

    /**
     * Affiche le nombre de tests total, le nombre de tests OK avec le taux de réussite des tests.
     * @return true : 100% OK / false : < 100% OK
     */
    public boolean afficheResultatsTest() {
        System.out.println("\n\tRésultats du test : " + this.nomTest);
        System.out.println("Nb test total : " + this.nbTestTotal);
        System.out.println("Nb test Ok : " + this.nbTestOk);
        //System.out.println("Nb test faux : " + (this.nbTestTotal - this.nbTestOk));
        if (this.nbTestTotal == 0) {
            this.nbTestTotal = 1;
        }
        System.out.println("Taux de réussite : " + ((double) this.nbTestOk / this.nbTestTotal * 100) + " %");

        return (this.nbTestOk == this.nbTestTotal);
    }
}
