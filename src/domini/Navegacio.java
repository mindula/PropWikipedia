package domini;

import graf.GrafWikipedia;
import graf.NodeCategoria;
import graf.NodePagina;
import graf.NodeWiki;
import org.grupwiki.graf.Arc;
import org.grupwiki.graf.Graf;

import java.util.HashSet;

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
            if(arc.getPes() > 0) supercategories.add((NodeCategoria)Graf.getNodeOposat(nodeCategoria, arc));
        }
        return supercategories;
    }

    public HashSet<NodeCategoria> getSubcategories(NodeCategoria nodeCategoria){
        HashSet<Arc<NodeWiki>> arcs = grafWikipedia.getNodesAdjacents(nodeCategoria);
        HashSet<NodeCategoria> subcategories = new HashSet<NodeCategoria>();
        for(Arc<NodeWiki> arc : arcs){
            if(arc.getPes() < 0) subcategories.add((NodeCategoria)Graf.getNodeOposat(nodeCategoria, arc));
        }
        return subcategories;
    }

    public HashSet<NodePagina> getPagines(NodeCategoria nodeCategoria){
        HashSet<Arc<NodeWiki>> arcs = grafWikipedia.getNodesAdjacents(nodeCategoria);
        HashSet<NodePagina> pagines = new HashSet<NodePagina>();
        for(Arc<NodeWiki> arc : arcs){
            if(arc.getPes() == 0) pagines.add((NodePagina)Graf.getNodeOposat(nodeCategoria, arc));
        }
        return pagines;
    }

    public HashSet<NodeCategoria> getCategories(NodePagina nodePagina){
        HashSet<Arc<NodeWiki>> arcs = grafWikipedia.getNodesAdjacents(nodePagina);
        HashSet<NodeCategoria> categories = new HashSet<NodeCategoria>();
        for(Arc<NodeWiki> arc : arcs){
            categories.add((NodeCategoria)Graf.getNodeOposat(nodePagina, arc)); // no cal comprobar pes
        }
        return categories;
    }

}
