package tests;

import graf.GrafWikipedia;
import graf.NodeCategoria;
import graf.NodePagina;
import graf.grafgenerator.Criteris.Criteri;
import graf.grafgenerator.Criteris.CriteriParesComuns;
import graf.grafgenerator.GrafGenerator;
import org.grupwiki.graf.Graf;

import java.util.ArrayList;

/**
 * Created by gus on 26/04/15.
 */
public class TestGrafGenerator {
    public static void main(String[] args) {

        GrafWikipedia graf = new GrafWikipedia();
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


        graf.afegirArcCsupC(n1, n3);
        graf.afegirArcCsupC(n1, n4);
        graf.afegirArcCsupC(n1, n5);

        graf.afegirArcCsupC(n2, n3);
        graf.afegirArcCsupC(n2, n4);
        graf.afegirArcCsupC(n2, n5);



        NodePagina p1 = new NodePagina("hololo.1");
        NodePagina p2 = new NodePagina("hololo.2");
        NodePagina p3 = new NodePagina("hololo.3");


        graf.afegirNode(p1);
        graf.afegirNode(p2);
        graf.afegirNode(p3);

        graf.afegirArcPC(p1, n1);
        graf.afegirArcPC(p2, n1);
        graf.afegirArcPC(p3, n1);

        graf.afegirArcPC(p1, n4);
        graf.afegirArcPC(p2, n4);
        graf.afegirArcPC(p3, n4);



        NodePagina p4 = new NodePagina("joder.1");
        NodePagina p5 = new NodePagina("joder.2");
        NodePagina p6 = new NodePagina("joder.3");

        graf.afegirNode(p4);
        graf.afegirNode(p5);
        graf.afegirNode(p6);


        graf.afegirArcPC(p4, n2);
        graf.afegirArcPC(p5, n2);
        graf.afegirArcPC(p6, n2);



        System.out.println("BEFORE:");
        System.out.println(graf);

        ArrayList<Criteri> criteris = new ArrayList<Criteri>();
        criteris.add(new CriteriParesComuns(1));

        GrafGenerator generator = new GrafGenerator();
        Graf<NodeCategoria> newGraf = generator.generate(graf,criteris);


        System.out.println("AFTER:");
        System.out.print(newGraf);


    }
}
