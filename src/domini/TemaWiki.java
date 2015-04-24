package domini;

import org.grupwiki.graf.Comunitat;

/**
 * Created with IntelliJ IDEA.
 * User: eduard.casellas
 * Date: 24/04/15
 * Time: 13:17
 * To change this template use File | Settings | File Templates.
 */


public class TemaWiki extends Comunitat<NodeWiki> {
    /**
     * Constructor per defecte, a partir d'un identificador <tt>id</tt> inicialitza les estructures internes
     *
     * @param id
     */
    public TemaWiki(int id) {
        super(id);
    }

    public eliminarNode(NodeWiki node){
        nodes.remove(node);
    }





}
