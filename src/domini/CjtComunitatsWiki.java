package domini;

import org.grupwiki.graf.ConjuntComunitats;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 24/04/15
 */
public class CjtComunitatsWiki extends ConjuntComunitats<NodeWiki> {
    public CjtComunitatsWiki() {
        super();
    }

    public void eliminarTema(int id){
        cjtComunitats.remove(id);
    }

    private void interseccio(ComunitatWiki a, ComunitatWiki b){


    }

}
