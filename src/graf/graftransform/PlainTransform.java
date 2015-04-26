package graf.graftransform;

import domini.NodeWiki;
import graf.GrafWikipedia;
import org.grupwiki.graf.Graf;

/**
 * Created by gus on 16/04/15.
 */
public class PlainTransform extends GrafTransformDecorator {
    public PlainTransform(GrafTransform delegate) {
        super(delegate);
    }

    @Override
    public Graf<NodeWiki> transform(Graf<NodeWiki> from) {
        super.transform(from);

        Graf<NodeWiki> graf = GrafWikipedia.convertBidirectional((GrafWikipedia)from);


        return graf;
    }
}
