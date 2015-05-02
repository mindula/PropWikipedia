package graf;

import domini.NodeWiki;
import org.grupwiki.graf.Arc;
import org.grupwiki.graf.Graf;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class GrafWikipedia extends Graf<NodeWiki> {


    //TODO: cal que eliminarNode tingui en compte que el graf és dirigit

    @Override
    public void eliminarNode(NodeWiki node) {
        if(!adjacencyMap.containsKey(node))
            throw new RuntimeException("No es pot eliminar un node que no està dins el graf");

        Set<NodeWiki> s = adjacencyMap.keySet();
        for (NodeWiki t : s) {
            if (adjacencyMap.get(t).containsKey(node)) {
                Arc<NodeWiki> aux = adjacencyMap.get(t).get(node);
                adjacencyMap.get(t).remove(node);
                cjtArcs.remove(aux);
                --E;
            }
        }
        adjacencyMap.remove(node);
        cjtNodes.remove(node);
        --V;
    }

    @Override
    public void afegirArc(Arc<NodeWiki> arc) {
        Map<NodeWiki, Arc<NodeWiki>> adj = adjacencyMap.get(arc.getNodeA());

        if(adj == null)
            adj = new HashMap<NodeWiki, Arc<NodeWiki>>();

        adj.put(arc.getNodeA(), arc);
        adjacencyMap.put(arc.getNodeA(), adj);
        cjtArcs.add(arc);
    }

    @Override
    public void eliminarArc(Arc<NodeWiki> arc) {
        adjacencyMap.remove(arc.getNodeA());
    }
}
