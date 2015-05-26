package domini;

import graf.NodeCategoria;
import prop.classescompartides.graf.Comunitat;
import prop.classescompartides.graf.ConjuntComunitats;

import java.util.ArrayList;

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
