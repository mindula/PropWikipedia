package graf;

import domini.NodeWiki;
import org.grupwiki.graf.Algoritme;
import org.grupwiki.graf.ConjuntComunitats;
import org.grupwiki.graf.Graf;

/**
 * Created by gus on 16/04/15.
 */
public class MainDriver {

    public static void main(String[] args) {

        GrafParser parser = new GrafParser();


        Graf<NodeWiki> grafPerAlgoritme = parser.parse("fitxer1");





        Algoritme<NodeWiki> algoritme = new Algoritme<NodeWiki>() {
            @Override
            public ConjuntComunitats<NodeWiki> cercarComunitats(Graf<NodeWiki> graf, int k, int l) {
                return null;
            }
        };



    }

}
