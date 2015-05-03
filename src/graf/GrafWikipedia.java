package graf;

import org.grupwiki.graf.Arc;
import org.grupwiki.graf.Graf;

import java.util.HashSet;
import java.util.List;


public class superpedia extends Graf<NodeWiki> {



    public superpedia (){
        super();
    }

    public void afegirNode(NodeWiki node) {
        super.afegirNode(node);
    }

    public void eliminarNode(NodeWiki node) {
        super.eliminarNode(node);
    }

    public void afegirArcPC(NodePagina pagina, NodeCategoria categoria) {
        Arc<NodeWiki> arcPC = new Arc<NodeWiki>(0.0, pagina, categoria);
        Arc<NodeWiki> arcCP = new Arc<NodeWiki>(0.0, categoria, pagina);
        super.afegirArc(arcPC);
        super.afegirArc(arcCP);
    }

    public void afegirArcCsupC(NodeCategoria categoriaA, NodeCategoria categoriaB) {
        if (categoriaA.getNom().equals(categoriaB.getNom())) {
            throw new RuntimeException("No es pot enllaçar dues categories amb el mateix nom");
        }
        Arc<NodeWiki> arcAsubB = new Arc<NodeWiki>(1, categoriaA, categoriaB);
        Arc<NodeWiki> arcBsubA = new Arc<NodeWiki>(-1, categoriaB, categoriaA);
        super.afegirArc(arcAsubB);
        super.afegirArc(arcBsubA);
    }

    public void afegirArcCsubC(NodeCategoria categoriaA, NodeCategoria categoriaB) {
        if (categoriaA.getNom().equals(categoriaB.getNom())) {
            throw new RuntimeException("No es pot enllaçar dues categories amb el mateix nom");
        }
        Arc<NodeWiki> arcAsubB = new Arc<NodeWiki>(-1, categoriaA, categoriaB);
        Arc<NodeWiki> arcBsubA = new Arc<NodeWiki>(1, categoriaB, categoriaA);
        super.afegirArc(arcAsubB);
        super.afegirArc(arcBsubA);
    }

    public void eliminarArc(Arc<NodeWiki> arc) {
        super.eliminarArc(arc);
    }

    public HashSet<NodeWiki> getNodes() {
        return super.getNodes();
    }

    public List<Arc<NodeWiki>> getArcs() {
        return super.getArcs();
    }

    public HashSet<Arc<NodeWiki>> getNodesAdjacents(NodeWiki node) {
        return super.getNodesAdjacents(node);
    }

    public Arc<NodeWiki> getArcEntre(NodeWiki nodeA, NodeWiki nodeB) {
        return super.getArcEntre(nodeA, nodeB);
    }

    public int getGrau (NodeWiki node) {
        return super.getGrau(node);
    }

    public boolean existeixNode(NodeWiki node) {
        return super.existeixNode(node);
    }

    public boolean existeixNodeCat(String nom) {
        HashSet<NodeWiki> s = super.getNodes();
        for (NodeWiki node : s) {
            if (nom.equals(node.getNom()) && node.esCategoria()) return true;
        }
        return false;
    }

    public boolean existeixNodePag(String nom) {
        HashSet<NodeWiki> s = super.getNodes();
        for (NodeWiki node : s) {
            if (nom.equals(node.getNom()) && !node.esCategoria()) return true;
        }
        return false;
    }

    public boolean existeixArc(NodeWiki nodeA, NodeWiki nodeB) {
        return super.existeixArc(nodeA, nodeB);
    }

    public NodeCategoria getNodeCat (String nom) {
        HashSet<NodeWiki> s = super.getNodes();
        for (NodeWiki node : s) {
            if (nom.equals(node.getNom()) && node.esCategoria()) return (NodeCategoria) node;
        }
        throw new RuntimeException("No existeix una categoria amb aquest nom");
    }

    public NodePagina getNodePag (String nom) {
        HashSet<NodeWiki> s = super.getNodes();
        for (NodeWiki node : s) {
            if (nom.equals(node.getNom()) && !node.esCategoria()) return (NodePagina) node;
        }
        throw new RuntimeException("No existeix una pàgina amb aquest nom");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
