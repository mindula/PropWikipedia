package domini;

import graf.GrafWikipedia;
import graf.NodeCategoria;
import graf.NodePagina;
import prop.classescompartides.graf.Arc;
import prop.classescompartides.graf.Graf;

import java.util.ArrayList;
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
        HashSet<Arc<NodeCategoria>> arcs = grafWikipedia.getNodesAdjacents(nodeCategoria);
        HashSet<NodeCategoria> supercategories = new HashSet<NodeCategoria>();
        for(Arc<NodeCategoria> arc : arcs){
            if(arc.getPes() > 0) supercategories.add(Graf.getNodeOposat(nodeCategoria, arc));
        }
        return supercategories;
    }

    /**
     * Retorna les subcategories d'un node determinat
     * @param nodeCategoria
     * @return les subcategories d'un node determinat
     */
    public HashSet<NodeCategoria> getSubcategories(NodeCategoria nodeCategoria){
        HashSet<Arc<NodeCategoria>> arcs = grafWikipedia.getNodesAdjacents(nodeCategoria);
        HashSet<NodeCategoria> subcategories = new HashSet<NodeCategoria>();
        for(Arc<NodeCategoria> arc : arcs){
            if(arc.getPes() < 0) subcategories.add((NodeCategoria)Graf.getNodeOposat(nodeCategoria, arc));
        }
        return subcategories;
    }

    /**
     * Retorna les pagines associades a una categoria
     * @param nodeCategoria
     * @return les pagines associades a una categoria
     */
    public ArrayList<NodePagina> getPagines(NodeCategoria nodeCategoria){
        return nodeCategoria.getPagines();
    }

    /**
     * Retorna les categories associades a una pagina
     * @param nodePagina
     * @return les categories associades a una pagina
     */
    public HashSet<NodeCategoria> getCategories(NodePagina nodePagina){
        return nodePagina.getCategories();
    }

}
