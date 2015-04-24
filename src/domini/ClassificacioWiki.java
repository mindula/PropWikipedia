package domini;

import org.grupwiki.graf.ConjuntComunitats;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 24/04/15
 */
public class ClassificacioWiki extends ConjuntComunitats<NodeWiki> {
    public ClassificacioWiki() {
        super();
    }

    public void eliminarTema(int id){
        cjtComunitats.remove(id);
    }


}
