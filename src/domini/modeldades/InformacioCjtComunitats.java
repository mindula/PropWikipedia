package domini.modeldades;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 29/04/15
 */

/**
 * Informació generada despres de l'execucio d'un algorisme
 */
public class InformacioCjtComunitats {
        private final long  milisegons;
        private int nombreComunitats;
        private final TipusAlgorisme algoritme;
        private final String criteri;
        private double mitjanaNodesPerComunitat;
        private final long tempsgenerar;

    /**
     * Constructora per defecte
     * @param milisegons
     * @param algoritme
     * @param criteri
     */
    public InformacioCjtComunitats(long tempsgenerar, long milisegons, TipusAlgorisme algoritme, String criteri) {
        this.tempsgenerar = tempsgenerar;
        this.milisegons = milisegons;
        this.algoritme = algoritme;
        this.criteri = criteri;
    }


    public long getMilisegons() {
        return milisegons;
    }


    public int getNombreComunitats() {
        return nombreComunitats;
    }

    public TipusAlgorisme getAlgoritme() {
        return algoritme;
    }

    public String getCriteri() {
        return criteri;
    }

    public double getMitjanaNodesPerComunitat() {
        return mitjanaNodesPerComunitat;
    }

    public void setNombreComunitats(int nombreComunitats) {
        this.nombreComunitats = nombreComunitats;
    }

    public void setMitjanaNodesPerComunitat(double mitjanaNodesPerComunitat) {
        this.mitjanaNodesPerComunitat = mitjanaNodesPerComunitat;
    }

    @Override
    public String toString() {
        return "InformacioCjtComunitats{\n" +
                "milisegons = " + milisegons + "ms" +
                ",\n nombreComunitats = " + nombreComunitats +
                ",\n algoritme = " + algoritme +
                ",\n criteri = '" + criteri + '\'' +
                ",\n mitjanaNodesPerComunitat=" + mitjanaNodesPerComunitat +
                "\n}";
    }
}
