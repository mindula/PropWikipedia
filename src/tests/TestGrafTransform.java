package tests;

import graf.NodeCategoria;
import graf.NodePagina;
import graf.NodeWiki;
import graf.graftransform.Criteris.CriteriFillsComuns;
import graf.graftransform.GrafGenerator;
import org.grupwiki.graf.Arc;
import org.grupwiki.graf.Graf;

/**
 * Created by gus on 26/04/15.
 */
public class TestGrafTransform {
    public static void main(String[] args) {

        Graf<NodeWiki> graf = new Graf<NodeWiki>();
        NodeCategoria n1 = new NodeCategoria("Holo");
        NodeCategoria n2 = new NodeCategoria("joder");
        NodeCategoria n3 = new NodeCategoria("jalalalala");
        NodeCategoria n4 = new NodeCategoria("papapapap");
        NodeCategoria n5 = new NodeCategoria("palalala");
        NodeCategoria n6 = new NodeCategoria("agus");


        graf.afegirNode(n1);
        graf.afegirNode(n2);
        graf.afegirNode(n3);
        graf.afegirNode(n4);
        graf.afegirNode(n5);
        graf.afegirNode(n6);




        NodePagina p1 = new NodePagina("hololo.1");
        NodePagina p2 = new NodePagina("hololo.2");
        NodePagina p3 = new NodePagina("hololo.3");


        graf.afegirNode(p1);
        graf.afegirNode(p2);
        graf.afegirNode(p3);

        graf.afegirArc(new Arc<NodeWiki>(n1, p1));
        graf.afegirArc(new Arc<NodeWiki>(n1, p2));
        graf.afegirArc(new Arc<NodeWiki>(n1, p3));

        NodePagina p4 = new NodePagina("joder.1");
        NodePagina p5 = new NodePagina("joder.2");
        NodePagina p6 = new NodePagina("joder.3");

        graf.afegirNode(p4);
        graf.afegirNode(p5);
        graf.afegirNode(p6);

        graf.afegirArc(new Arc<NodeWiki>(n2, p4));
        graf.afegirArc(new Arc<NodeWiki>(n2, p5));
        graf.afegirArc(new Arc<NodeWiki>(n2, p6));







        System.out.println("BEFORE:");
        System.out.println(graf);

        GrafGenerator generator = new GrafGenerator();
        Graf<NodeCategoria> newGraf = generator.generate(graf,new CriteriFillsComuns(1));


        System.out.println("AFTER:");
        System.out.print(newGraf);


    }
}
