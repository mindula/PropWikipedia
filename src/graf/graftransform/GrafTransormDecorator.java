package graf.graftransform;

import domini.NodeWiki;
import org.grupwiki.graf.Graf;

/**
 * Created by gus on 24/04/15.
 */
public class GrafTransormDecorator implements GrafTransform {
    GrafTransform delegate;

    public GrafTransormDecorator(GrafTransform delegate) {
        this.delegate = delegate;
    }

    @Override
    public void transform(Graf<NodeWiki> from) {
        delegate.transform(from);
    }
}
