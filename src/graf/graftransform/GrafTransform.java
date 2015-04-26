package graf.graftransform;

import domini.NodeWiki;
import org.grupwiki.graf.Graf;

/**
 * Created by gus on 16/04/15.
 */
public interface GrafTransform {
    Graf<NodeWiki> transform(Graf<NodeWiki> from);
}
