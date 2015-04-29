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

    public ComunitatWiki union(ComunitatWiki a, ComunitatWiki b){
            ComunitatWiki c = new ComunitatWiki();
            c.getNodes().addAll(a.getNodes());
            c.getNodes().addAll(b.getNodes());
            return c;
    }

    public ComunitatWiki intersection(ComunitatWiki a, ComunitatWiki b){
            ComunitatWiki c = new ComunitatWiki();
            for (NodeWiki n : a.getNodes()){
                if (b.getNodes().contains(n)){
                    c.getNodes().add(n);
                }
            }
            return c;
    }

    public ComunitatWiki diferencia(ComunitatWiki a, ComunitatWiki b){
        ComunitatWiki c = new ComunitatWiki();
        c.getNodes().addAll(a.getNodes());
        c.getNodes().removeAll(b.getNodes());
        afegirComunitat(c);
        return c;
    }
}
