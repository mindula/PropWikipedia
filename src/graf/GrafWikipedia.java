package graf;

import prop.classescompartides.graf.Arc;

import java.util.HashSet;
import java.util.List;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 2/5/15
 */

/**
 * Graf de la Wikipedia
 */
public class GrafWikipedia {

    private GrafDirigit<NodeWiki> grafWiki;

    /**
     * Constructora per defecte
     */
    public GrafWikipedia (){
        grafWiki = new GrafDirigit<NodeWiki>();
    }

    public void afegirNode(NodeWiki node) {
        grafWiki.afegirNode(node);
    }

    public void eliminarNode(NodeWiki node) {
        grafWiki.eliminarNode(node);
    }

    /**
     * Afegeix una aresta entre pagina i categoria (i viceversa)
     * @param pagina
     * @param categoria
     */
    public void afegirArcPC(NodePagina pagina, NodeCategoria categoria) {
        Arc<NodeWiki> arcPC = new Arc<NodeWiki>(0.0, pagina, categoria);
        Arc<NodeWiki> arcCP = new Arc<NodeWiki>(0.0, categoria, pagina);
        grafWiki.afegirArc(arcPC);
        grafWiki.afegirArc(arcCP);
    }

    /**
     * Afegeix una aresta entre subcategoria i supercategoria
     * @param subCategoria
     * @param superCategoria
     */
    public void afegirArcCsupC(NodeCategoria subCategoria, NodeCategoria superCategoria) {
        if (subCategoria.getNom().equals(superCategoria.getNom())) {
            throw new RuntimeException("No es pot enllaçar dues categories amb el mateix nom");
        }
        Arc<NodeWiki> arcAsubB = new Arc<NodeWiki>(1, subCategoria, superCategoria);
        Arc<NodeWiki> arcBsubA = new Arc<NodeWiki>(-1, superCategoria, subCategoria);
        grafWiki.afegirArc(arcAsubB);
        grafWiki.afegirArc(arcBsubA);
    }

    /**
     * Afegeix una aresta entre supercategoria i subcategoria
     * @param superCategoria
     * @param subCategoria
     */
    public void afegirArcCsubC(NodeCategoria superCategoria, NodeCategoria subCategoria) {
        if (superCategoria.getNom().equals(subCategoria.getNom())) {
            throw new RuntimeException("No es pot enllaçar dues categories amb el mateix nom");
        }
        Arc<NodeWiki> arcAsubB = new Arc<NodeWiki>(-1, superCategoria, subCategoria);
        Arc<NodeWiki> arcBsubA = new Arc<NodeWiki>(1, subCategoria, superCategoria);
        grafWiki.afegirArc(arcAsubB);
        grafWiki.afegirArc(arcBsubA);
    }

    public void eliminarArc(Arc<NodeWiki> arc) {
        grafWiki.eliminarArc(arc);
    }

    public HashSet<NodeWiki> getNodes() {
        return grafWiki.getNodes();
    }

    public List<Arc<NodeWiki>> getArcs() {
        return grafWiki.getArcs();
    }

    public HashSet<Arc<NodeWiki>> getNodesAdjacents(NodeWiki node) {
        return grafWiki.getNodesAdjacents(node);
    }

    public Arc<NodeWiki> getArcEntre(NodeWiki nodeA, NodeWiki nodeB) {
        return grafWiki.getArcEntre(nodeA, nodeB);
    }

    public int getGrau (NodeWiki node) {
        return grafWiki.getGrau(node);
    }

    public boolean existeixNode(NodeWiki node) {
        return grafWiki.existeixNode(node);
    }

    public boolean existeixNodeCat(String nom) {
        HashSet<NodeWiki> s = grafWiki.getNodes();
        for (NodeWiki node : s) {
            if (nom.equals(node.getNom()) && node.esCategoria()) return true;
        }
        return false;
    }

    public boolean existeixNodePag(String nom) {
        HashSet<NodeWiki> s = grafWiki.getNodes();
        for (NodeWiki node : s) {
            if (nom.equals(node.getNom()) && !node.esCategoria()) return true;
        }
        return false;
    }

    public boolean existeixArc(NodeWiki nodeA, NodeWiki nodeB) {
        return grafWiki.existeixArc(nodeA, nodeB);
    }

    public NodeCategoria getNodeCat (String nom) {
        HashSet<NodeWiki> s = grafWiki.getNodes();
        for (NodeWiki node : s) {
            if (nom.equals(node.getNom()) && node.esCategoria()) return (NodeCategoria) node;
        }
        throw new RuntimeException("No existeix una categoria amb aquest nom");
    }

    public NodePagina getNodePag (String nom) {
        HashSet<NodeWiki> s = grafWiki.getNodes();
        for (NodeWiki node : s) {
            if (nom.equals(node.getNom()) && !node.esCategoria()) return (NodePagina) node;
        }
        throw new RuntimeException("No existeix una pàgina amb aquest nom");
    }

    @Override
    public String toString() {
        return grafWiki.toString();
    }
}
