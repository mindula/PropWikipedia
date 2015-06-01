package domini.modeldades.graf;

import prop.classescompartides.graf.Arc;
import prop.classescompartides.graf.Graf;

import java.util.Collection;
import java.util.Set;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard.gascons
 * Data: 2/5/15
 */

/**
 * Graf dirigit per la Wikipedia. Exten la majoria de funcions de Graf
 * @param <T>
 */
public class GrafDirigit<T> extends Graf<T> {

    @Override
    public void eliminarNode(T node) {
        if(!adjacencyMap.containsKey(node))
            throw new RuntimeException("No es pot eliminar un node que no està dins el graf");

        Set<T> s = adjacencyMap.keySet();
        for (T t : s) {
            if (adjacencyMap.get(t).containsKey(node)) {
                Arc<T> aux = adjacencyMap.get(t).get(node);
                adjacencyMap.get(t).remove(node);
                cjtArcs.remove(aux);
                --E;
            }
        }
        E -= adjacencyMap.get(node).size();
        Collection<Arc<T>> c = adjacencyMap.get(node).values();
        cjtArcs.removeAll(c);
        adjacencyMap.remove(node);
        cjtNodes.remove(node);
        --V;
    }

    @Override
    public void afegirArc(Arc<T> arc) {
        if (!adjacencyMap.containsKey(arc.getNodeA()))
            throw new  RuntimeException("El node A ha d'estar previament al graf");
        if (!adjacencyMap.containsKey(arc.getNodeB()))
            throw new  RuntimeException("El node B ha d'estar previament al graf");
        if(adjacencyMap.get(arc.getNodeA()).containsKey(arc.getNodeB()))
            return;
        adjacencyMap.get(arc.getNodeA()).put(arc.getNodeB(), arc);
        cjtArcs.add(arc);
        ++E;
    }

    @Override
    public void eliminarArc(Arc<T> arc) {
        Collection<Arc<T>> aAdjacents = adjacencyMap.get(arc.getNodeA()).values();
        Collection<Arc<T>> bAdjacents = adjacencyMap.get(arc.getNodeB()).values();

        if(! aAdjacents.remove(arc) && !bAdjacents.remove(arc))
            throw new RuntimeException("L'arc no existeix");
        cjtArcs.remove(arc);
        --E;
    }

    @Override
    public boolean existeixArc(T origen, T desti) {
        return super.existeixArc(origen, desti);
    }
}
