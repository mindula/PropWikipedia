package domini;

import org.grupwiki.graf.*;

import java.util.*;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris, ricard.gascons
 * Data: 23/4/15
 */

public class AlgorismeLouvain<T> extends Algoritme<T>{

    @Override
    public ConjuntComunitats<T> cercarComunitats(Graf<T> grafOriginal, int criteriParada, int nul){
        Graf<T> grafActual = grafOriginal;
        int numComunitats = grafActual.ordre();
        ConjuntComunitats<T> classificacio = new ConjuntComunitats<T>();    //no fa falta ara, ho fem al final
        HashMap<T, Comunitat<T>> nodeToComunitat = new HashMap<T, Comunitat<T>>();
        //Cada node és una comunitat
        for(T node : grafActual.getNodes()){
            Comunitat<T> c = new Comunitat<T>(node);
            classificacio.afegirComunitat(c);
            nodeToComunitat.put(node, c);
        }
        while(numComunitats > criteriParada){
            //Fase 1
            double m2 = m2(grafActual);   //es necessari tenir-ho aqui, ja que anem creant nous grafs
            boolean canviQ = false;
            do {
                Set<T> nodesGraf = grafActual.getNodes();
                for (T node : nodesGraf) {
                    double maxModularitat = 0;  //assumim que només ens importen guanys positius
                    Comunitat<T> cOriginal = nodeToComunitat.get(node);
                    Pair <Double, Comunitat<T>> deltaQMaxComunitat = new Pair<Double, Comunitat<T>>();
                    deltaQMaxComunitat.setFirst(maxModularitat);
                    deltaQMaxComunitat.setSecond(cOriginal);
                    Set<Arc<T>> arcsAdjacents = grafActual.getNodesAdjacents(node);
                    for (Arc<T> arc: arcsAdjacents) {
                        Comunitat<T> cAdjacent = nodeToComunitat.get(Graf.getNodeOposat(node, arc));
                        //possible millora: comunitats ja visitades no les tornem a visitar
                        if (cAdjacent != cOriginal){
                            double deltaQ = deltaQ(node, cAdjacent, grafActual, m2);
                            maxModularitat = max(maxModularitat, deltaQ);
                            if (maxModularitat > deltaQMaxComunitat.getFirst()) {
                                deltaQMaxComunitat.setFirst(maxModularitat);
                                deltaQMaxComunitat.setSecond(cAdjacent);
                            }
                        }
                    }
                    if (maxModularitat != 0) {
                        canviQ = true;
                        Comunitat<T> c = deltaQMaxComunitat.getSecond();
                        c.afegirNode(node);
                        cOriginal.eliminarNode(node);
                        nodeToComunitat.put(node, c);
                    }
                }
            } while(canviQ);

            //Fase 2
            //Hem de comptabilitzar les comunitats que tenim. Per fer-ho hauriem d'eliminar les comunitats que
            //s'han quedat desertes, és a dir, les que no tenen cap node
            ArrayList<Comunitat<T>> comunitats = classificacio.getComunitats();
            for(Comunitat comunitat : comunitats){
                if(comunitat.estaBuida()) classificacio.eliminarComunitat(comunitat);
            }
            for(Comunitat comunitat : comunitats){
                HashSet<T> nodes = comunitat.getNodes();
                double pesComunitat = 0;
                for(T node : nodes){
                    HashSet<Arc<T>> arcs = grafActual.getNodesAdjacents(node);
                    for(Arc<T> arc : arcs){
                        T nodeOposat = Graf.getNodeOposat(node, arc);
                        if(comunitat.teNode(nodeOposat)) pesComunitat += arc.getPes();

                    }
                }




            }

            Graf<Comunitat<T>> nouGraf = new Graf<Comunitat<T>>();

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

    private double max (double x, double y) {
        if (x > y) return x;
        return y;
    }
}
