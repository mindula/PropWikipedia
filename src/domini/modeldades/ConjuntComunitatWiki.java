package domini.modeldades;

import domini.modeldades.graf.NodeCategoria;
import prop.classescompartides.graf.ConjuntComunitats;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 22/05/15
 */
public class ConjuntComunitatWiki extends ConjuntComunitats<NodeCategoria> {
    private InformacioCjtComunitats informacio;

    public ConjuntComunitatWiki() {
        super();
    }

    public InformacioCjtComunitats getInformacio() {
        return informacio;
    }

    public void setInformacio(InformacioCjtComunitats informacio) {
        this.informacio = informacio;
    }
}
