package graf;

import domini.NodeWiki;
import org.grupwiki.graf.Arc;
import org.grupwiki.graf.Graf;

import java.util.HashMap;
import java.util.Map;


public class GrafWikipedia extends Graf<NodeWiki> {




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
