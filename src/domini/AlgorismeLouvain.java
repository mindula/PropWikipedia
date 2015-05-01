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

        int numComunitats = grafOriginal.ordre();

        ConjuntComunitats<Integer> conjuntComunitats = new ConjuntComunitats<Integer>();
        HashMap<Integer, Comunitat<Integer>> nodeToComunitat = new HashMap<Integer, Comunitat<Integer>>();

        HashMap<Integer, T> traduccioGraf = new HashMap<Integer, T>();

        // Traduim el graf a un graf d'enters:
        Graf<Integer> grafLouvain = convertirGraf(grafOriginal, traduccioGraf);

        double m2 = m2(grafLouvain); // només cal calcular m2 un cop

        while(numComunitats > criteriParada){
            //Fase 1
            //Cada node és una comunitat
            for(Integer node : grafLouvain.getNodes()){
                Comunitat<Integer> c = new Comunitat<Integer>(node);
                conjuntComunitats.afegirComunitat(c);
                nodeToComunitat.put(node, c);
            }

            boolean canviQ = false;
            do {
                Set<Integer> nodesGraf = grafLouvain.getNodes();
                for (Integer node : nodesGraf) {
                    double maxModularitat = 0;  //assumim que només ens importen guanys positius
                    Comunitat<Integer> cOriginal = nodeToComunitat.get(node);
                    Pair <Double, Comunitat<Integer>> deltaQMaxComunitat = new Pair<Double, Comunitat<Integer>>();
                    deltaQMaxComunitat.setFirst(maxModularitat);
                    deltaQMaxComunitat.setSecond(cOriginal);
                    Set<Arc<Integer>> arcsAdjacents = grafLouvain.getNodesAdjacents(node);
                    for (Arc<Integer> arc: arcsAdjacents) {
                        Comunitat<Integer> cAdjacent = nodeToComunitat.get(Graf.getNodeOposat(node, arc));
                        //possible millora: comunitats ja visitades no les tornem a visitar
                        if (cAdjacent != cOriginal){
                            double deltaQ = deltaQ(node, cAdjacent, grafLouvain, m2);
                            maxModularitat = max(maxModularitat, deltaQ);
                            if (maxModularitat > deltaQMaxComunitat.getFirst()) {
                                // Movem el node a cAdjacent
                                deltaQMaxComunitat.setFirst(maxModularitat);
                                deltaQMaxComunitat.setSecond(cAdjacent);
                            }
                        }
                    }
                    if (maxModularitat != 0) {
                        canviQ = true;
                        Comunitat<Integer> cAdjacent = deltaQMaxComunitat.getSecond();
                        cOriginal.eliminarNode(node);
                        cAdjacent.afegirNode(node);
                        nodeToComunitat.put(node, cAdjacent);
                        if(cOriginal.estaBuida()) conjuntComunitats.eliminarComunitat(cOriginal);
                    }
                }
            } while(canviQ);

            //Fase 2
            //Hem de comptabilitzar les comunitats que tenim. Per fer-ho hauriem d'eliminar les comunitats que
            //s'han quedat desertes, és a dir, les que no tenen cap node
            ArrayList<Comunitat<T>> comunitats = classificacio.getComunitats();
            Graf<T> grafFase2 = new Graf<T>();
            for(Comunitat<T> comunitat : comunitats){
                if(comunitat.estaBuida()) classificacio.eliminarComunitat(comunitat);
            }
            for(Comunitat<T> comunitat : comunitats){
                grafFase2.afegirNode(new T);
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

        }
        return classificacio;
    }

    private Graf<Integer> convertirGraf(Graf<T> grafOriginal, HashMap<Integer, T> traduccioIntegerT){
        HashMap<T, Integer> traduccioTInteger = new HashMap<T, Integer>();
        HashSet<T> nodesOriginals = grafOriginal.getNodes();
        Graf<Integer> grafFinal = new Graf<Integer>();
        Integer i = 0;
        for(T nodeOriginal : nodesOriginals){
            traduccioIntegerT.put(i, nodeOriginal);
            traduccioTInteger.put(nodeOriginal, i);
            grafFinal.afegirNode(i);
            ++i;
        }

        List<Arc<T>> arcs = grafOriginal.getArcs();
        for(Arc<T> arc : arcs){
            T AT = arc.getNodeA();
            T BT = arc.getNodeB();
            double pes = arc.getPes();
            Integer AInteger = traduccioTInteger.get(AT);
            Integer BInteger = traduccioTInteger.get(BT);
            Arc<Integer> nouArc = new Arc<Integer>(pes, AInteger, BInteger);
            grafFinal.afegirArc(nouArc);
        }
        return grafFinal;
    }

    private double deltaQ (Integer node , Comunitat<Integer> c, Graf<Integer> graf, double m2) {
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
    private double m2(Graf<Integer> graf){
        double sumaArcs = 0;
        List<Arc<Integer>> arcs = graf.getArcs();
        for (Arc<Integer> a : arcs) {
            sumaArcs += a.getPes();
        }
        return sumaArcs*2;
    }

    private double ki(Integer node, Graf<Integer> graf){
        double ki = 0;
        Set<Arc<Integer>> set = graf.getNodesAdjacents(node);
        for(Arc<Integer> a : set) ki += a.getPes();
        return ki;
    }

    private double sigmaIn (Comunitat<Integer> c, Graf<Integer> graf){
        Set<Integer> nodesComunitat = c.getNodes();
        double sigmaIn = 0;
        for (Integer node : nodesComunitat) {
            Set<Arc<Integer>> arcs = graf.getNodesAdjacents(node);
            for (Arc<Integer> arc : arcs) {
                Integer oposat = Graf.getNodeOposat(node, arc);
                if (nodesComunitat.contains(oposat)) {
                    sigmaIn += arc.getPes();
                }
            }
        }
        return sigmaIn/2;
    }

    private double sigmaTot(Comunitat<Integer> c, Graf<Integer> graf){
        double sigmaTot = 0;
        Set<Integer> nodes = graf.getNodes();
        Set<Integer> nodesComunitat = c.getNodes();
        for(Integer node : nodes){
            if(!nodesComunitat.contains(node)){
                HashSet<Arc<Integer>> arcsAdjacents = graf.getNodesAdjacents(node);
                for(Arc<Integer> arc : arcsAdjacents){
                    if(nodesComunitat.contains(Graf.getNodeOposat(node, arc))){
                        sigmaTot += arc.getPes();
                    }
                }
            }
        }
        return sigmaTot;
    }

    private double kiIn(Integer node, Comunitat<Integer> c, Graf<Integer> graf){
        double kiIn = 0;
        Set<Arc<Integer>> arcs = graf.getNodesAdjacents(node);
        Set<Integer> nodesComunitat = c.getNodes();
        for(Arc<Integer> arc : arcs){
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
    /*
    private void convertirGrafComunitat(Graf<T> grafOriginal, Graf<Comunitat<T>> grafComunitat,
                                        HashMap<T, Comunitat<T>> nodeToComunitat) {
        HashSet<T> nodesOriginal = grafOriginal.getNodes();
        List<Arc<T>> arcsOriginal = grafOriginal.getArcs();
        for (T node : nodesOriginal) {
            Comunitat<T> cNode = nodeToComunitat.get(node);
            grafComunitat.afegirNode(cNode);
        }
        for (Arc<T> arc : arcsOriginal) {
            Comunitat<T> cNodeA = nodeToComunitat.get(arc.getNodeA());
            Comunitat<T> cNodeB = nodeToComunitat.get(arc.getNodeB());
            Arc<Comunitat<T>> cArc = new Arc<Comunitat<T>>(arc.getPes(), cNodeA, cNodeB);
            grafComunitat.afegirArc(cArc);
        }
    }*/
}
