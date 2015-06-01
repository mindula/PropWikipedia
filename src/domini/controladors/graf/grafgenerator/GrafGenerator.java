package domini.controladors.graf.grafgenerator;

import domini.modeldades.graf.GrafWikipedia;
import domini.modeldades.graf.NodeCategoria;
import domini.controladors.graf.grafgenerator.Criteris.Criteri;
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


        for(NodeCategoria n : graf.getCategories())
            newGraf.afegirNode(n);
        int iteracionsLlargues = 0;
        for (int i = 0; i < graf.getCategories().size(); i++) {
            long temps_iteracio_start = System.currentTimeMillis();
            long tempsCriteri = 0;
            long tempsAfegirArc = 0;
            NodeCategoria n1 = graf.getCategories().get(i);

            for (int j = i+1 ; j<graf.getCategories().size(); j++) {
                NodeCategoria n2 = graf.getCategories().get(j);

                double pes = 0;
                long criterisStart = System.currentTimeMillis();
                for(Criteri c  :criteris)
                    pes += c.getPes(n1, n2, graf)/c.getMaxPes(n1, n2, graf) * c.getPonderacio();
                tempsCriteri += (System.currentTimeMillis()-criterisStart);

                if (pes > 0.01) {
                    long arcStart = System.currentTimeMillis();
                    Arc<NodeCategoria> a = new Arc<NodeCategoria>(pes, n1, n2);
                    newGraf.afegirArc(a);
                    tempsAfegirArc += (System.currentTimeMillis() - arcStart);

                }

            }
            long temps_it = System.currentTimeMillis() -temps_iteracio_start;
            if(temps_it > 100) {

                System.out.println("iteracio " + i + "/" + graf.getCategories().size() + ":" + temps_it + "ms");
                System.out.println("Temps en criteri: " + tempsCriteri);
                System.out.println("proporcio: " + (tempsCriteri / (double) temps_it) * 100 + "%");
                System.out.println("Temps en afegirArc: " + tempsAfegirArc);
                System.out.println("proporcio: " + (tempsAfegirArc / (double) temps_it) * 100 + "%");
                System.out.println("Arcs: "+newGraf.mida());
                System.out.println();

                iteracionsLlargues++;
            }


        }
        System.out.println("Iteracions llargues : "+iteracionsLlargues);
        System.out.println("Arcs"+ newGraf.mida());
        return newGraf;
    }
}
