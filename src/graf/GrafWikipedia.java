package graf;


import domini.NodeWiki;
import org.grupwiki.graf.Arc;
import org.grupwiki.graf.Graf;

import javax.xml.soap.Node;
import java.util.HashMap;
import java.util.Map;


public class GrafWikipedia extends Graf<NodeWiki> {

    public static Graf<NodeWiki> convertBidirectional(GrafWikipedia from){
        Graf<NodeWiki> ret = new GrafWikipedia();
        for(NodeWiki n1 : from.adjacencyMap.keySet()){
            Map<NodeWiki, Arc<NodeWiki>> adj = from.adjacencyMap.get(n1);
            ret.afegirNode(n1);
            for(Arc<NodeWiki> arc : adj.values()){
                Arc<NodeWiki> copia = new Arc<NodeWiki>(arc);
                if(n1 == arc.getNodeA() && !ret.existeixNode(arc.getNodeB()))
                    ret.afegirNode(arc.getNodeB());
                if(n1 == arc.getNodeB() && !ret.existeixNode(arc.getNodeA()))
                    ret.afegirNode(arc.getNodeA());

                ret.afegirArc(copia);

            }
        }
        return ret;
    }


    @Override
    public void afegirArc(Arc<NodeWiki> arc) {
        Map<NodeWiki, Arc<NodeWiki>> adj = adjacencyMap.get(arc.getNodeA());

        if(adj == null)
            adj = new HashMap<NodeWiki, Arc<NodeWiki>>();

        adj.put(arc.getNodeA(), arc);
        adjacencyMap.put(arc.getNodeA(), adj);
    }

    @Override
    public void eliminarArc(Arc<NodeWiki> arc) {
        adjacencyMap.remove(arc.getNodeA());
    }


}
