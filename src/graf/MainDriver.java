package graf;

import domini.AlgorismeLouvain;
import domini.NodeWiki;
import graf.graftransform.GrafTransform;
import graf.graftransform.NomTransform;
import graf.graftransform.PlainTransform;
import org.grupwiki.graf.Algoritme;
import org.grupwiki.graf.ConjuntComunitats;
import org.grupwiki.graf.Graf;
import org.grupwiki.graf.GrafExeption;

import javax.xml.soap.Node;

/**
 * Created by gus on 16/04/15.
 */
public class MainDriver {

    public static void main(String[] args) {

        GrafParser parser = new GrafParser();

        // no se com es crea un Path, seria millor que agafes string crec..
        Graf<NodeWiki> grafWikipedia = parser.parse("misc/cats_test");

        System.out.println("Graf Parsejat:");
        System.out.println(grafWikipedia);



        GrafTransform transform = new NomTransform(new PlainTransform()); // primer aplica plainTranform, despres NomTransform


        grafWikipedia = transform.transform(grafWikipedia);

        System.out.println("Graf Transformat:");
        System.out.println(grafWikipedia);


        Algoritme<NodeWiki> algoritme = new AlgorismeLouvain<NodeWiki>();


        try {
            ConjuntComunitats<NodeWiki> nodeWikiConjuntComunitats = algoritme.cercarComunitats(grafWikipedia, 3, 1);
            System.out.println(nodeWikiConjuntComunitats);
        } catch (GrafExeption grafExeption) {
            grafExeption.printStackTrace();
        }


    }

}
