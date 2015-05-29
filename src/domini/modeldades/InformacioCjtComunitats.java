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
        private final int nombreComunitats;
        private final TipusAlgorisme algoritme;
        private final String criteri;
        private final double mitjanaNodesPerComunitat;
        private final long tempsgenerar;

    /**
     * Constructora per defecte
     * @param milisegons
     * @param nombreComunitats
     * @param algoritme
     * @param criteri
     * @param mitjanaNodesPerComunitat
     */
    public InformacioCjtComunitats(long tempsgenerar, long milisegons, int nombreComunitats, TipusAlgorisme algoritme, String criteri, double mitjanaNodesPerComunitat) {
        this.tempsgenerar = tempsgenerar;
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

    public TipusAlgorisme getAlgoritme() {
        return algoritme;
    }

    public String getCriteri() {
        return criteri;
    }

    public double getMitjanaNodesPerComunitat() {
        return mitjanaNodesPerComunitat;
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
