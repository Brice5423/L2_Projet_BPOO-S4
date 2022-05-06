package home.excepcion;

import home.expert.Expert;

public class ExpertManquantException extends Exception {
    private Expert expert;

    public ExpertManquantException(Expert expert) {
        System.out.println("Il y a un problème avec l'expert");
        this.expert = expert;
    }
}
