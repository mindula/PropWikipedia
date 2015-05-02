package domini;

import org.grupwiki.graf.ConjuntComunitats;

import java.sql.Struct;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 24/04/15
 */
public class CjtComunitatsWiki extends ConjuntComunitats<NodeWiki> {


    public CjtComunitatsWiki() {
        super();
    }

    public static ComunitatWiki unio(ComunitatWiki a, ComunitatWiki b){
            ComunitatWiki c = new ComunitatWiki();
            c.getNodes().addAll(a.getNodes());
            c.getNodes().addAll(b.getNodes());
            return c;
    }



    public static ComunitatWiki interseccio(ComunitatWiki a, ComunitatWiki b){
            ComunitatWiki c = new ComunitatWiki();
            for (NodeWiki n : a.getNodes()){
                if (b.getNodes().contains(n)){
                    c.getNodes().add(n);
                }
            }
            return c;
    }

    public static ComunitatWiki diferencia(ComunitatWiki a, ComunitatWiki b){
        ComunitatWiki c = new ComunitatWiki();
        c.getNodes().addAll(a.getNodes());
        c.getNodes().removeAll(b.getNodes());
        return c;
    }
}
