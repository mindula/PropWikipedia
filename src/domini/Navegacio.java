package domini;

import graf.GrafWikipedia;
import graf.NodeCategoria;
import graf.NodePagina;
import graf.NodeWiki;
import prop.classescompartides.graf.Arc;
import prop.classescompartides.graf.Graf;

import java.util.HashSet;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 01/05/2015
 */

/**
 * Navegacio per la Wikipedia, amb tots els detalls explicat a l'enunciat
 */

public class Navegacio {

    private GrafWikipedia grafWikipedia;

    /**
     * Constructora per defecte
     * @param graf
     */
    public Navegacio(GrafWikipedia graf){
        grafWikipedia = graf;
    }

    /**
     * Retorna les superpacetories d'un node determinat
     * @param nodeCategoria
     * @return les superpacetories d'un node determinat
     */
    public HashSet<NodeCategoria> getSupercategories(NodeCategoria nodeCategoria){
        HashSet<Arc<NodeWiki>> arcs = grafWikipedia.getNodesAdjacents(nodeCategoria);
        HashSet<NodeCategoria> supercategories = new HashSet<NodeCategoria>();
        for(Arc<NodeWiki> arc : arcs){
            if(arc.getPes() > 0) supercategories.add((NodeCategoria)Graf.getNodeOposat(nodeCategoria, arc));
        }
        return supercategories;
    }

    /**
     * Retorna les subcategories d'un node determinat
     * @param nodeCategoria
     * @return les subcategories d'un node determinat
     */
    public HashSet<NodeCategoria> getSubcategories(NodeCategoria nodeCategoria){
        HashSet<Arc<NodeWiki>> arcs = grafWikipedia.getNodesAdjacents(nodeCategoria);
        HashSet<NodeCategoria> subcategories = new HashSet<NodeCategoria>();
        for(Arc<NodeWiki> arc : arcs){
            if(arc.getPes() < 0) subcategories.add((NodeCategoria)Graf.getNodeOposat(nodeCategoria, arc));
        }
        return subcategories;
    }

    /**
     * Retorna les pagines associades a una categoria
     * @param nodeCategoria
     * @return les pagines associades a una categoria
     */
    public HashSet<NodePagina> getPagines(NodeCategoria nodeCategoria){
        HashSet<Arc<NodeWiki>> arcs = grafWikipedia.getNodesAdjacents(nodeCategoria);
        HashSet<NodePagina> pagines = new HashSet<NodePagina>();
        for(Arc<NodeWiki> arc : arcs){
            if(arc.getPes() == 0) pagines.add((NodePagina)Graf.getNodeOposat(nodeCategoria, arc));
        }
        return pagines;
    }

    /**
     * Retorna les categories associades a una pagina
     * @param nodePagina
     * @return les categories associades a una pagina
     */
    public HashSet<NodeCategoria> getCategories(NodePagina nodePagina){
        HashSet<Arc<NodeWiki>> arcs = grafWikipedia.getNodesAdjacents(nodePagina);
        HashSet<NodeCategoria> categories = new HashSet<NodeCategoria>();
        for(Arc<NodeWiki> arc : arcs){
            categories.add((NodeCategoria)Graf.getNodeOposat(nodePagina, arc)); // no cal comprobar pes
        }
        return categories;
    }

}
