package domini.controladors.graf.grafgenerator.Criteris;

import domini.modeldades.graf.GrafWikipedia;
import domini.modeldades.graf.NodeCategoria;

/**
 * Grup 3: Wikipedia
 * Usuari: agusti.bau
 * Data: 2/5/15
 */

/**
 * Representa els criteris a aplicar al graf per l'algorisme
 */
public  abstract class Criteri {

    /**
     * Constructora per defecte
     * @param ponderacio representa la ponderació aplicada a aquest criteri
     */
    public Criteri(double ponderacio) {
        this.ponderacio = ponderacio;
    }

    private double ponderacio;

    /**
     * Calcula el pes entre dos nodes dins el graf
     * @param n1
     * @param n2
     * @param graf
     * @return el pes entre dos nodes dins el graf
     */
    public abstract double getPes(NodeCategoria n1, NodeCategoria n2, GrafWikipedia graf);

    /**
     * Retorna la ponderació aplicada al criteri
     * @return la ponderació aplicada al criteri
     */
    public double getPonderacio() {
        return ponderacio;
    }

    /**
     * Calcula el maxim pes possible entre dos nodes, utilitzat per normalitzar el valor de getPes
     * @param n1
     * @param n2
     * @param graf
     * @return
     */
    public abstract double getMaxPes(NodeCategoria n1, NodeCategoria n2, GrafWikipedia graf);
}
