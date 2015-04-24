package graf.graftransform;

import domini.NodeWiki;
import graf.GrafWikipedia;
import org.grupwiki.graf.Graf;

/**
 * Created by gus on 16/04/15.
 */
public class PlainTransform extends GrafTransform {
    @Override
    public Graf<NodeWiki> transform(Graf<NodeWiki> from) {
        if(from instanceof GrafWikipedia)
            return GrafWikipedia.convertBidirectional((GrafWikipedia)from);
        else
            return from;
    }
}
