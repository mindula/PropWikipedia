package domini.Controladors;

import domini.ComunitatWiki;
import domini.ConjuntComunitatWiki;
import graf.NodeWiki;
import prop.classescompartides.graf.ConjuntComunitats;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 21/05/15
 */
public class CtrlComunitat {
    public ConjuntComunitatWiki conjunt;

    public CtrlComunitat(){
        this.conjunt = new ConjuntComunitatWiki();
    }

    /**
     * Cas d'us crea tema
     */
    public void creaComunitat(String nom){
        ComunitatWiki c = new ComunitatWiki();
        c.setNom(nom);
        conjunt.afegirComunitat(c);
    }

    public void modNomComunitat(int id, String nomnou) throws  Exception{
        ComunitatWiki c = (ComunitatWiki) conjunt.getComunitats().get(id);
        c.setNom(nomnou);
    }




}
