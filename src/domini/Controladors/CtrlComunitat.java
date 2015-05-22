package domini.Controladors;

import domini.ComunitatWiki;
import graf.NodeWiki;
import prop.classescompartides.graf.ConjuntComunitats;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 21/05/15
 */
public class CtrlComunitat {
    private ConjuntComunitats<NodeWiki> Cjt;

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
