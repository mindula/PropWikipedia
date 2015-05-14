package graf;

import prop.classescompartides.graf.Arc;

import java.util.ArrayList;
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

    private GrafDirigit<NodeCategoria> grafWiki;
    private HashSet<NodePagina> pagines;
    private ArrayList<NodeCategoria> categories;
    /**
     * Constructora per defecte
     */
    public GrafWikipedia (){
        grafWiki = new GrafDirigit<NodeCategoria>();
        pagines = new HashSet<>();
        categories = new ArrayList<>();
    }

    public void afegirCategoria(NodeCategoria node) {
        grafWiki.afegirNode(node);
        categories.add(node);
    }

    public void eliminarNode(NodeCategoria node) {
        grafWiki.eliminarNode(node);
        categories.remove(node);
    }

    /**
     * Afegeix una aresta entre pagina i categoria (i viceversa)
     * @param pagina
     * @param categoria
     */
    public void afegirArcPC(NodePagina pagina, NodeCategoria categoria) {
        categoria.afegirPagina(pagina);
        pagina.afegirCategoria(categoria);
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
        Arc<NodeCategoria> arcAsubB = new Arc<>(1, subCategoria, superCategoria);
        Arc<NodeCategoria> arcBsubA = new Arc<>(-1, superCategoria, subCategoria);
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
        Arc<NodeCategoria> arcAsubB = new Arc<>(-1, superCategoria, subCategoria);
        Arc<NodeCategoria> arcBsubA = new Arc<>(1, subCategoria, superCategoria);
        grafWiki.afegirArc(arcAsubB);
        grafWiki.afegirArc(arcBsubA);
    }

    public void eliminarArc(Arc<NodeCategoria> arc) {
        grafWiki.eliminarArc(arc);
    }

    public List<Arc<NodeCategoria>> getArcs() {
        return grafWiki.getArcs();
    }

    public HashSet<Arc<NodeCategoria>> getNodesAdjacents(NodeCategoria node) {
        return grafWiki.getNodesAdjacents(node);
    }

    public Arc<NodeCategoria> getArcEntre(NodeCategoria nodeA, NodeCategoria nodeB) {
        return grafWiki.getArcEntre(nodeA, nodeB);
    }

    public int getGrau (NodeCategoria node) {
        return grafWiki.getGrau(node);
    }

    public boolean existeixNode(NodeCategoria node) {
        return grafWiki.existeixNode(node);
    }

    public boolean existeixNodeCat(String nom) {
        HashSet<NodeCategoria> s = grafWiki.getNodes();
        for (NodeCategoria node : s) {
            if (nom.equals(node.getNom())) return true;
        }
        return false;
    }

    public boolean existeixNodePag(String nom) {
        for (NodePagina node : pagines) {
            if (nom.equals(node.getNom())) return true;
        }
        return false;
    }

    public boolean existeixArcCC(NodeCategoria nodeA, NodeCategoria nodeB) {
        return grafWiki.existeixArc(nodeA, nodeB);
    }

    public boolean existeixArcCP(NodeCategoria cat, NodePagina pag){
        return pag.getCategories().contains(cat);
    }

    public NodeCategoria getNodeCat (String nom) {
        HashSet<NodeCategoria> s = grafWiki.getNodes();
        for (NodeCategoria node : s) {
            if (nom.equals(node.getNom())) return  node;
        }
        throw new RuntimeException("No existeix una categoria amb aquest nom");
    }

    public NodePagina getNodePag (String nom) {
        for (NodePagina node : pagines) {
            if (nom.equals(node.getNom()) ) return  node;
        }
        throw new RuntimeException("No existeix una pàgina amb aquest nom");
    }

    public ArrayList<NodeCategoria> getCategories(){
        return categories;
    }

    @Override
    public String toString() {
        return grafWiki.toString();
    }

    public void afegirPagina(NodePagina pag) {
        pagines.add(pag);
    }

    public void eliminarPagina(NodePagina pag){
        pagines.remove(pag);
    }
}
