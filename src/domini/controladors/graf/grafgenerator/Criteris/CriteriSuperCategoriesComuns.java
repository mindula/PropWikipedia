package domini.controladors.graf.grafgenerator.Criteris;

import domini.modeldades.graf.GrafWikipedia;
import domini.modeldades.graf.NodeCategoria;
import prop.classescompartides.graf.Arc;
import prop.classescompartides.graf.Graf;

import java.util.HashSet;

/**
 * Grup 3: Wikipedia
 * Usuari: agusti.bau
 * Data: 2/5/15
 */

/**
 * Criter per super categories comunes comunes entre categories
 */
public class CriteriSuperCategoriesComuns extends Criteri{

    public CriteriSuperCategoriesComuns(double ponderacio) {
        super(ponderacio);
    }

    @Override
    public double getPes(NodeCategoria n1, NodeCategoria n2, GrafWikipedia graf) {
        HashSet<Arc<NodeCategoria>> arcs1 = graf.getNodesAdjacents(n1);
        double paresComuns = 0;
        for(Arc<NodeCategoria> arc : arcs1){

                NodeCategoria pare = Graf.getNodeOposat(n1, arc);
                if(arc.getPes()> 0){
                    Arc<NodeCategoria> arcn2pare = graf.getArcEntre(n2, pare);
                    if(arcn2pare != null && arcn2pare.getPes() > 0)
                        paresComuns++;
                }

        }
        return paresComuns;
    }

    @Override
    public double getMaxPes(NodeCategoria n1, NodeCategoria n2, GrafWikipedia graf) {
        int paresN1 = 0;
        int paresN2 =0;
        for(Arc<NodeCategoria> paren1 : graf.getNodesAdjacents(n1)){
            if (paren1.getPes() > 0)
                paresN1++;
        }

        for(Arc<NodeCategoria> paren2 : graf.getNodesAdjacents(n2)){
            if (paren2.getPes() > 0)
                paresN1++;
        }

        return Math.min(paresN1, paresN2);
    }

    public String toString() {
        return "SupercategoriesComunes " + getPonderacio();
    }

}
