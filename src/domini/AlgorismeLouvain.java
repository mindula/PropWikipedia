package domini;

import org.grupwiki.graf.Algoritme;
import org.grupwiki.graf.Arc;
import org.grupwiki.graf.ConjuntComunitats;
import org.grupwiki.graf.Graf;

import java.util.Set;

public class AlgorismeLouvain extends Algoritme<NodeWiki>{
    @Override
    public ConjuntComunitats<NodeWiki> cercarComunitats(Graf<NodeWiki> graf, int k, int l) {
        int m2 = m2(graf);
        // TODO: implementar l'algoritme
    }

    // Calcula m*2, es a dir, per a tot arc del graf, la suma del seu pes (un arc del node A al node B s'ha de sumar 2 cops)
    private int m2(Graf<NodeWiki> graf){
        int sumaArcs = 0;
        // TODO: falta metode getArcs del graf! O necessitem accedir a l'atribut privat de la classe Graf...
        // TODO: l'alternativa amb l'especificacio actual seria fer un getNodes i un getNodesAdjacents per cada un, pero es poc eficient
        return sumaArcs;
    }

    // Calcula ki: la suma dels pesos dels arcs incidents al NodeWiki node
    private int ki(Graf<NodeWiki> graf, NodeWiki node){
        int ki = 0;
        Set<Arc<NodeWiki>> set = graf.getNodesAdjacents(node);
        for(Arc<NodeWiki> a : set) ki += a.getPes();
        return ki;
    }

    // TODO: metode per calcuar sigma in, sigma tot, ki,in (son operacions sobre comunitats)
}
