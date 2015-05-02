package domini;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 29/04/15
 */
public class InformacioCjtComunitats {
        private int milisegons;
        private int nombreComunitats;
        private String algoritme;
        private String criteri;
        private double mitjanaNodesPerComunitat;

    public void setMilisegons(int milisegons) {
        this.milisegons = milisegons;
    }

    public int getMilisegons() {
        return milisegons;
    }

    public void setNombreComunitats(int nombreComunitats) {
        this.nombreComunitats = nombreComunitats;
    }

    public int getNombreComunitats() {
        return nombreComunitats;
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

    public double getMitjanaNodesPerComunitat() {
        return mitjanaNodesPerComunitat;
    }

    public void setMitjanaNodesPerComunitat(double mitjanaNodesPerComunitat) {
        this.mitjanaNodesPerComunitat = mitjanaNodesPerComunitat;
    }
}
