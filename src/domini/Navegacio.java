package domini;

import graf.GrafWikipedia;
import org.grupwiki.graf.Arc;
import org.grupwiki.graf.Graf;

import java.util.HashSet;
import java.util.Set;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 01/05/2015
 */

public class Navegacio {

    private GrafWikipedia grafWikipedia;

    public Navegacio(GrafWikipedia graf){
        grafWikipedia = graf;
    }

    public HashSet<NodeCategoria> getSupercategories(NodeCategoria nodeCategoria){
        HashSet<Arc<NodeWiki>> arcs = grafWikipedia.getNodesAdjacents(nodeCategoria);
        HashSet<NodeCategoria> supercategories = new HashSet<NodeCategoria>();
        for(Arc<NodeWiki> arc : arcs){
            if(arc.getPes() > 0) supercategories.add(Graf.getNodeOposat(nodeCategoria, arc));
        }
        return supercategories;
    }

    public HashSet<NodeCategoria> getSubcategories(){

    }

    public HashSet<NodePagina> getPagines(){

    }

}
