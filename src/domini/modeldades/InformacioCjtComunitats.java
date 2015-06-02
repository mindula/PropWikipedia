package domini.modeldades;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 29/04/15
 */

import java.io.Serializable;

/**
 * Informació generada despres de l'execucio d'un algorisme
 */
public class InformacioCjtComunitats implements Serializable {
        private long tempsComunitats;           //Temps en buscar les comunitats d'un graf ja generat
        private int nombreComunitats;
        private final TipusAlgorisme algoritme;
        private final String criteri;
        private double mitjanaNodesPerComunitat;
        private final long tempsgenerar;        //Temps en generar el graf

    /**
     * Constructora per defecte
     * @param tempsgenerar
     * @param algoritme
     * @param criteri
     */
    public InformacioCjtComunitats(long tempsgenerar, TipusAlgorisme algoritme, String criteri) {
        this.tempsgenerar = tempsgenerar;
        this.algoritme = algoritme;
        this.criteri = criteri;
    }


    public long getTempsComunitats() {
        return tempsComunitats;
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

    public void setTempsComunitats(long temps){
        tempsComunitats = temps;
    }

    public long getTempsgenerar() {
        return tempsgenerar;
    }

    @Override
    public String toString() {
        return "InformacioCjtComunitats{\n" +
                "tempsComunitats = " + tempsComunitats + "ms" +
                ",\n nombreComunitats = " + nombreComunitats +
                ",\n algoritme = " + algoritme +
                ",\n criteri = '" + criteri + '\'' +
                ",\n mitjanaNodesPerComunitat=" + mitjanaNodesPerComunitat +
                "\n}";
    }
}
