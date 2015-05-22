package domini.modeldades.graf;

import prop.classescompartides.graf.Arc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Grup 3: Wikipedia
 * Autor versio 1: ricard.gascons
 * Autor versio 2: agusti.bau
 * Data: 2/5/15
 */

/**
 * Graf de la Wikipedia
 */
public class GrafWikipedia {

    private GrafDirigit<NodeCategoria> grafWiki;

    private HashMap<String, NodeCategoria> categoriesMap;
    private HashMap<String, NodePagina> paginesMap;
    private ArrayList<NodePagina> pagines;
    private ArrayList<NodeCategoria> categories;
    /**
     * Constructora per defecte
     */
    public GrafWikipedia (){
        grafWiki = new GrafDirigit<NodeCategoria>();
        pagines = new ArrayList<>();
        categories = new ArrayList<>();
        categoriesMap = new HashMap<>();
        paginesMap =  new HashMap<>();

    }

    public void afegirCategoria(NodeCategoria node) {
        grafWiki.afegirNode(node);
        categories.add(node);
        categoriesMap.put(node.getNom(), node);
    }

    public void eliminarCategoria(NodeCategoria node) {
        grafWiki.eliminarNode(node);
        categories.remove(node);
        categoriesMap.remove(node.getNom());
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

    public void eliminarArcPC(String cat, String pag, NodeCategoria nc, NodePagina np) {
        this.getNodeCat(cat).getPagines().remove(np);
        this.getNodePag(pag).getCategories().remove(nc);
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

    public boolean existeixNodeCat(String nom) {
        return categoriesMap.containsKey(nom);
    }

    public boolean existeixNodePag(String nom) {
        return paginesMap.containsKey(nom);
    }

    public boolean existeixArcCC(NodeCategoria nodeA, NodeCategoria nodeB) {
        return grafWiki.existeixArc(nodeA, nodeB);
    }

    public boolean existeixArcCP(NodeCategoria cat, NodePagina pag){
        return pag.getCategories().contains(cat);
    }

    public NodeCategoria getNodeCat (String nom) {
        if(categoriesMap.containsKey(nom))
            return categoriesMap.get(nom);

        throw new RuntimeException("No existeix una categoria amb aquest nom");
    }

    public NodePagina getNodePag (String nom) {
        if(paginesMap.containsKey(nom))
            return paginesMap.get(nom);

        throw new RuntimeException("No existeix una pàgina amb aquest nom");
    }

    public ArrayList<NodeCategoria> getCategories(){
        return categories;
    }

    public ArrayList<NodePagina> getPagines() {
        return pagines;
    }

    @Override
    public String toString() {
        return "Categories: "+categories.size() + "\nPagines: "+pagines.size()+"\nRelacions: "+grafWiki.mida();
    }

    public void afegirPagina(NodePagina pag) {
        pagines.add(pag);
        paginesMap.put(pag.getNom(), pag);
    }

    public void eliminarPagina(NodePagina pag){
        pagines.remove(pag);
        paginesMap.remove(pag.getNom());
    }

    public int getNumCategories(){
       return grafWiki.ordre();
    }
}
