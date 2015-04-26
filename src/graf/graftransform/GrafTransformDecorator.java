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

    public GrafTransformDecorator(){

    }
    @Override
    public Graf<NodeWiki> transform(Graf<NodeWiki> from) {
        if(delegate != null)
            return delegate.transform(from);
        else
            return null;
    }
}
