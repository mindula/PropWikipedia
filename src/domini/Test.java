package domini;

import graf.GrafWikipedia;
import org.grupwiki.graf.Arc;

public class Test {
    public static void main(String[] args) {
        GrafWikipedia gw = new GrafWikipedia();
        NodeWiki n1 = new NodeCategoria("1");
        NodeWiki n2 = new NodeCategoria("2");
        NodeWiki n3 = new NodeCategoria("3");
        NodeWiki n4 = new NodeCategoria("4");
        NodeWiki n5 = new NodeCategoria("5");
        NodeWiki n6 = new NodeCategoria("6");
        NodeWiki n66 = new NodeCategoria("66");
        NodeWiki n678 = new NodeCategoria("678");
        gw.afegirNode(n1);
        gw.afegirNode(n2);
        gw.afegirNode(n3);
        gw.afegirNode(n4);
        gw.afegirNode(n5);
        gw.afegirNode(n6);
        gw.afegirNode(n66);
        gw.afegirNode(n678);
        gw.afegirArc(new Arc(0, n1, n2));
        gw.afegirArc(new Arc(0, n2, n3));
        gw.afegirArc(new Arc(0, n4, n1));
        gw.afegirArc(new Arc(0, n3, n6));
        // System.out.print(Cerca.cercarCategoria(gw, "6"));
    }
}
