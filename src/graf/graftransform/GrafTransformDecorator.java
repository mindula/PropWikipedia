package graf.graftransform;

import domini.NodeWiki;
import org.grupwiki.graf.Graf;

/**
 * Created by gus on 24/04/15.
 */
public class GrafTransformDecorator implements GrafTransform {
    GrafTransform delegate;

    public GrafTransformDecorator(GrafTransform delegate) {
        this.delegate = delegate;
    }

    @Override
    public void transform(Graf<NodeWiki> from) {
        if(delegate != null)
            delegate.transform(from);
    }
}
