package domini.controladors;

import domini.modeldades.ComunitatWiki;
import domini.modeldades.graf.NodeWiki;
import prop.classescompartides.graf.ConjuntComunitats;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 21/05/15
 */
public class CtrlComunitat {
    public ConjuntComunitats<NodeWiki> Cjt;

    public CtrlComunitat(){
        this.Cjt = new ConjuntComunitats<NodeWiki>();
    }

    /**
     * Cas d'us crea tema
     */
    public void creaComunitat(String nom){
        ComunitatWiki c = new ComunitatWiki();
        c.setNom(nom);
        Cjt.afegirComunitat(c);
    }
}
