package graf;

import domini.NodeWiki;
import graf.graftransform.GrafTransform;
import graf.graftransform.NomTransform;
import graf.graftransform.PlainTransform;
import org.grupwiki.graf.Algoritme;
import org.grupwiki.graf.Graf;
import org.grupwiki.louvain.AlgoritmeLouvain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gus on 16/04/15.
 */
public class MainDriver {

    public static void main(String[] args) {

        GrafParser parser = new GrafParser();


        Graf<NodeWiki> grafPerAlgoritme = parser.parse("fitxer1");

        List<GrafTransform> transformList = new ArrayList<GrafTransform>();

        transformList.add(new PlainTransform());
        transformList.add(new NomTransform());

        for(GrafTransform transform : transformList)
            grafPerAlgoritme = transform.transform(grafPerAlgoritme);

        Algoritme<NodeWiki> algoritme = new AlgoritmeLouvain();


    }

}
