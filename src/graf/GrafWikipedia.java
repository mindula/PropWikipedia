package graf;

import org.grupwiki.graf.Arc;
import org.grupwiki.graf.Graf;

import java.util.HashSet;


public class GrafWikipedia extends Graf<NodeWiki> {

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

}
