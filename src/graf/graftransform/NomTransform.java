package graf.graftransform;

import domini.NodeWiki;
import org.grupwiki.graf.Arc;
import org.grupwiki.graf.Graf;

import java.util.*;

/**
 * Created by gus on 16/04/15.
 */
public class NomTransform extends GrafTransformDecorator {

    public NomTransform(GrafTransform delegate) {
        super(delegate);
    }

    private class NodeWikiSorter implements Comparator<NodeWiki> {

        @Override
        public int compare(NodeWiki o1, NodeWiki o2) {
            return o1.getNom().compareTo(o2.getNom());
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }

    private static int getCommonLetters(String s1, String s2){
        int i = 0;
        while(s1.charAt(i) == s2.charAt(i)){
            i++;
        }
        return i;
    }

    @Override
    public Graf<NodeWiki> transform(Graf<NodeWiki> from) {
        super.transform(from);

        List<NodeWiki> nodes = new ArrayList<NodeWiki>();
        nodes.addAll(from.getNodes());
        Collections.sort(nodes, new NodeWikiSorter());

        Graf<NodeWiki> ret = new Graf<NodeWiki>();

        for(NodeWiki n1: nodes){
            int commonLetters = 999;
            for(int i = 0; i<nodes.size() && commonLetters > 0; i++){
                NodeWiki n2 = nodes.get(i);
                if(n1 != n2) {
                    commonLetters = getCommonLetters(n1.getNom(), n2.getNom());

                    if(!ret.existeixNode(n1))
                        ret.afegirNode(n1);
                    if(!ret.existeixNode(n2))
                        ret.afegirNode(n2);

                    Arc<NodeWiki> arc = new Arc<NodeWiki>(commonLetters, n1, n2);
                    ret.afegirArc(arc);
                }
            }
        }

        return ret;
    }
}
