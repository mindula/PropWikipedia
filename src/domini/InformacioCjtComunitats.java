package domini;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 29/04/15
 */
public class InformacioCjtComunitats {
        private final long  milisegons;
        private final int nombreComunitats;
        private final String algoritme;
        private final String criteri;
        private final double mitjanaNodesPerComunitat;

    public InformacioCjtComunitats(long milisegons, int nombreComunitats, String algoritme, String criteri, double mitjanaNodesPerComunitat) {
        this.milisegons = milisegons;
        this.nombreComunitats = nombreComunitats;
        this.algoritme = algoritme;
        this.criteri = criteri;
        this.mitjanaNodesPerComunitat = mitjanaNodesPerComunitat;
    }


    public long getMilisegons() {
        return milisegons;
    }


    public int getNombreComunitats() {
        return nombreComunitats;
    }

    public String getAlgoritme() {
        return algoritme;
    }

    public String getCriteri() {
        return criteri;
    }

    public double getMitjanaNodesPerComunitat() {
        return mitjanaNodesPerComunitat;
    }


}
