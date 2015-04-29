package domini;

import org.grupwiki.graf.Comunitat;

/**
 * Grup 3: Wikipedia
 * User: eduard.casellas
 * Date: 24/04/15
 */


public class ComunitatWiki extends Comunitat<NodeWiki> {
    /**
     * Constructor per defecte, a partir d'un identificador <tt>id</tt> inicialitza les estructures internes
     *
     */

    public ComunitatWiki() {
        super();
    }

    public void eliminarNode(NodeWiki node){
        nodes.remove(node);
    }





}
