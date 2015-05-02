package graf.graftransform;

import domini.NodeWiki;
import graf.GrafWikipedia;
import org.grupwiki.graf.Arc;
import org.grupwiki.graf.Graf;

/**
 * Created by Agusti on 16/04/15.
 */



public class PlainTransform extends GrafTransformDecorator {

    public Graf<NodeWiki> convertBidirectional(GrafWikipedia from){
        Graf<NodeWiki> ret = new Graf<NodeWiki>();
        for(Arc<NodeWiki> arc: from.getArcs()){

            if(!ret.existeixNode(arc.getNodeA()))
                ret.afegirNode(arc.getNodeA());
            if(!ret.existeixNode(arc.getNodeB()))
                ret.afegirNode(arc.getNodeB());

            Arc<NodeWiki> copia = new Arc<NodeWiki>(arc);
            ret.afegirArc(copia);

        }
        for(NodeWiki node : from.getNodes()) {
            if (!ret.existeixNode(node))
                ret.afegirNode(node);
        }

        return ret;
    }

    public PlainTransform(GrafTransform delegate) {
        super(delegate);
    }

    public PlainTransform(){
        super();
    }


   /* Peta, ho comento per poder fer test

    @Override
    public Graf<NodeWiki> transform(Graf<NodeWiki> from) {
        from = super.transform(from);
        System.out.println("PlainTransform");
        if(from instanceof GrafWikipedia)
            return convertBidirectional((GrafWikipedia)from);
        else
            return from;
    }
    */
}
