package domini;

import org.grupwiki.graf.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlgorismeLouvain<T> extends Algoritme<T>{

    @Override
    public ConjuntComunitats<T> cercarComunitats(Graf<T> graf, int criteriParada, int nul){
        double m2 = m2(graf);
        int numComunitats = graf.ordre();
        ConjuntComunitats<T> classificacio = new ConjuntComunitats<T>();
        HashMap<T, Comunitat<T>> nodeComunitat = new HashMap<T, Comunitat<T>>();
        //Cada node és una comunitat
        for(T node : graf.getNodes()){
            classificacio.afegirComunitat(new Comunitat<T>(node));
        }
        while(numComunitats > criteriParada){
            //Fase 1
            boolean canviQ = false;
            do {
                Set<T> nodesGraf = graf.getNodes();
                for (T node : nodesGraf) {
                    double aillarNode = - deltaQ(node);
                }

            } while(canviQ);

        }
        return classificacio;
    }


    private double deltaQ (T node , Comunitat<T> c, Graf<T> graf, double m2) {
        double deltaQ;
        double sigmaIn = sigmaIn(c, graf);
        double sigmaTot = sigmaTot(c, graf);
        double ki = ki(node, graf);
        double kiIn = kiIn(node, c, graf);
        deltaQ = (sigmaIn+kiIn)/m2 - ((sigmaTot+ki)/m2)*((sigmaTot+ki)/m2);
        deltaQ -= sigmaIn/m2 - (sigmaTot/m2)*(sigmaTot/m2) - (ki/m2)*(ki/m2);
        return deltaQ;
    }

    // Calcula m*2, es a dir, per a tot arc del graf, la suma del seu pes (un arc del node A al node B s'ha de sumar 2 cops)
    // Enlaços sumats només un cop
    private double m2(Graf<T> graf){
        double sumaArcs = 0;
        List<Arc<T>> arcs = graf.getArcs();
        for (Arc<T> a : arcs) {
            sumaArcs += a.getPes();
        }
        return sumaArcs*2;
    }

    // Calcula ki: la suma dels pesos dels arcs incidents al NodeWiki node
    // Aqui no tenim el problema de sumar 2 o 1 cops el pes
    private double ki(T node, Graf<T> graf){
        double ki = 0;
        Set<Arc<T>> set = graf.getNodesAdjacents(node);
        for(Arc<T> a : set) ki += a.getPes();
        return ki;
    }

    // Sumem dos cops cada enllaç
    private double sigmaIn (Comunitat<T> c, Graf<T> graf){
        Set<T> nodesComunitat = c.getNodes();
        double sigmaIn = 0;
        for (T node : nodesComunitat) {
            Set<Arc<T>> arcs = graf.getNodesAdjacents(node);
            for (Arc<T> arc : arcs) {
                T oposat = Graf.getNodeOposat(node, arc);
                if (nodesComunitat.contains(oposat)) {
                    sigmaIn += arc.getPes();
                }
            }
        }
        return sigmaIn;
    }

    // Aqui no tenim el problema de sumar 2 o 1 cops el pes
    private double sigmaTot(Comunitat<T> c, Graf<T> graf){
        double sigmaTot = 0;
        Set<T> nodes = graf.getNodes();
        Set<T> nodesComunitat = c.getNodes();
        for(T node : nodes){
            if(!nodesComunitat.contains(node)){
                HashSet<Arc<T>> arcsAdjacents = graf.getNodesAdjacents(node);
                for(Arc<T> arc : arcsAdjacents){
                    if(nodesComunitat.contains(Graf.getNodeOposat(node, arc))){
                        sigmaTot += arc.getPes();
                    }
                }
            }
        }
        return sigmaTot;
    }


    private double kiIn(T node , Comunitat<T> c, Graf<T> graf){
        double kiIn = 0;
        Set<Arc<T>> arcs = graf.getNodesAdjacents(node);
        Set<T> nodesComunitat = c.getNodes();
        for(Arc<T> arc : arcs){
            if(nodesComunitat.contains(Graf.getNodeOposat(node, arc))){
                kiIn += arc.getPes();
            }
        }
        return kiIn;
    }
}
