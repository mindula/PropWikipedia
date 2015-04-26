package domini;

import org.grupwiki.graf.Algoritme;
import org.grupwiki.graf.Arc;
import org.grupwiki.graf.ConjuntComunitats;
import org.grupwiki.graf.Graf;

import java.util.List;
import java.util.Set;

public class AlgorismeLouvain<T> extends Algoritme<T>{

    @Override
    public ConjuntComunitats<T> cercarComunitats(Graf<T> graf, int k, int l) throws Exception{
        double m2 = m2(graf);
        // TODO: implementar l'algoritme
    }

    // Calcula m*2, es a dir, per a tot arc del graf, la suma del seu pes (un arc del node A al node B s'ha de sumar 2 cops)
    private double m2(Graf<T> graf){
        double sumaArcs = 0;
        List<Arc<T>> arcs = graf.getArcs();
        for (Arc<T> a : arcs) {
            sumaArcs += a.getPes();
        }
        return sumaArcs*2;
    }

    // Calcula ki: la suma dels pesos dels arcs incidents al NodeWiki node
    private double ki(Graf<T> graf, T node){
        double ki = 0;
        Set<Arc<T>> set = graf.getNodesAdjacents(node);
        for(Arc<T> a : set) ki += a.getPes();
        return ki;
    }

    // TODO: metode per calcuar sigma in, sigma tot, ki,in (son operacions sobre comunitats)
}
