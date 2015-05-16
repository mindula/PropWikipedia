package graf.grafgenerator;

import graf.GrafWikipedia;
import graf.NodeCategoria;
import graf.grafgenerator.Criteris.Criteri;
import prop.classescompartides.graf.Arc;
import prop.classescompartides.graf.Graf;

import java.util.ArrayList;

/**
 * Grup 3: Wikipedia
 * Usuari: agusti.bau
 * Data: 2/5/15
 */

/**
 * Generador del graf pels algorismes a partir del graf de la Wikipedia
 */
public class GrafGenerator  {
    public Graf<NodeCategoria> generate(GrafWikipedia graf, ArrayList<Criteri> criteris) {
        Graf<NodeCategoria> newGraf = new Graf<NodeCategoria>();

        for (int i = 0; i < graf.getCategories().size(); i++) {
            NodeCategoria n1 = graf.getCategories().get(i);
            if(!newGraf.existeixNode(n1))
                newGraf.afegirNode(n1);
            for (int j = i+1 ; j<graf.getCategories().size(); j++) {
                NodeCategoria n2 = graf.getCategories().get(j);
               if (!newGraf.existeixNode(n2))
                    newGraf.afegirNode(n2);

                double pes = 0;
                for(Criteri c  :criteris)
                    pes += c.getPes(n1, n2, graf)/c.getMaxPes(n1, n2, graf) * c.getPonderacio();

                if (pes > 0) {
                    Arc<NodeCategoria> a = new Arc<NodeCategoria>(pes, n1, n2);
                    newGraf.afegirArc(a);
                    //System.out.println(pes);
                }


            }
        }

        //System.out.println(newGraf.mida());
        //System.out.println(newGraf.ordre());
        return newGraf;
    }
}
