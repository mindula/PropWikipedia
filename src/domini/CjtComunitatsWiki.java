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
            c.getComunitat().addAll(a.getComunitat());
            c.getComunitat().addAll(b.getComunitat());
            return c;
    }

    public ComunitatWiki intersection(ComunitatWiki a, ComunitatWiki b){
            ComunitatWiki c = new ComunitatWiki();
            for (NodeWiki n : a.getComunitat()){
                if (b.getComunitat().contains(n)){
                    c.getComunitat().add(n);
                }
            }
            return c;
    }

    public ComunitatWiki diferencia(ComunitatWiki a, ComunitatWiki b){
        ComunitatWiki c = new ComunitatWiki();
        c.getComunitat().addAll(a.getComunitat());
        c.getComunitat().removeAll(b.getComunitat());
        afegirComunitat(c);
        return c;
    }
}
