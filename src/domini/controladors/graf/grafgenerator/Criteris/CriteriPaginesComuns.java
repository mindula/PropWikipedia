package domini.controladors.graf.grafgenerator.Criteris;

import domini.modeldades.graf.GrafWikipedia;
import domini.modeldades.graf.NodeCategoria;
import domini.modeldades.graf.NodePagina;

/**
 * Grup 3: Wikipedia
 * Usuari: agusti.bau
 * Data: 2/5/15
 */

/**
 * Criteri per pagines comunes entre categories
 */
public class CriteriPaginesComuns extends Criteri{
    public CriteriPaginesComuns(double ponderacio) {
        super(ponderacio);
    }

    @Override
    public double getPes(NodeCategoria n1, NodeCategoria n2, GrafWikipedia graf) {


        double fillsComuns = 0;
        for(NodePagina p1 : n1.getPagines()){
            for(NodePagina p2 : n2.getPagines()){
                if(p1 == p2)
                    fillsComuns++;
            }
        }

        return fillsComuns;
    }

    @Override
    public double getMaxPes(NodeCategoria n1, NodeCategoria n2, GrafWikipedia graf) {
        return Math.min(n1.getPagines().size(), n2.getPagines().size());
    }

    public String toString() {
        return "Pagines comunes " + getPonderacio();
    }
}
