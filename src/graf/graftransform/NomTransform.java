package graf.graftransform;

import domini.NodeWiki;
import org.grupwiki.graf.Arc;
import org.grupwiki.graf.Graf;

/**
 * Created by gus on 16/04/15.
 */
public class NomTransform extends GrafTransformDecorator {

    public NomTransform(GrafTransform delegate) {
        super(delegate);
    }
    public NomTransform() {
        super();
    }

    private static int getCommonLetters(String s1, String s2){
        int i = 0;
        int minLetters = Math.min(s1.length(), s2.length());
        while(i < minLetters && s1.charAt(i) == s2.charAt(i)){
            i++;
        }
        return i;
    }

    @Override
    public Graf<NodeWiki> transform(Graf<NodeWiki> from) {
        super.transform(from);
        System.out.println("NomTransform");


        Graf<NodeWiki> ret = new Graf<NodeWiki>();

        for(NodeWiki n1: from.getNodes()){
            for(NodeWiki n2 :  from.getNodes()){

                if(n1 != n2) {
                    int commonLetters = getCommonLetters(n1.getNom(), n2.getNom());

                    if(!ret.existeixNode(n1))
                        ret.afegirNode(n1);
                    if(!ret.existeixNode(n2))
                        ret.afegirNode(n2);

                    if(commonLetters > 0) {
                        Arc<NodeWiki> arc = new Arc<NodeWiki>(commonLetters, n1, n2);
                        ret.afegirArc(arc);
                    }

                }
            }
        }

        return ret;
    }
}
