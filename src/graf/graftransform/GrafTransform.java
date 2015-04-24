package graf.graftransform;

import domini.NodeWiki;
import graf.GrafWikipedia;
import org.grupwiki.graf.Graf;

/**
 * Created by gus on 16/04/15.
 */
public interface GrafTransform {
    void transform(Graf<NodeWiki> from);
}
