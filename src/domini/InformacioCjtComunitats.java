package domini;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 29/04/15
 */
public class InformacioCjtComunitats {
        private int milisegons;
        private int nComunitats;
        private String algoritme;
        private String criteri;

    public void setMilisegons(int milisegons) {
        this.milisegons = milisegons;
    }

    public int getMilisegons() {
        return milisegons;
    }

    public void setnComunitats(int nComunitats) {
        this.nComunitats = nComunitats;
    }

    public int getnComunitats() {
        return nComunitats;
    }

    public void setAlgoritme(String algoritme) {
        this.algoritme = algoritme;
    }

    public String getAlgoritme() {
        return algoritme;
    }

    public void setCriteri(String criteri) {
        this.criteri = criteri;
    }

    public String getCriteri() {
        return criteri;
    }
}
