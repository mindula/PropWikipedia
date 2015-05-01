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

        HashMap<Integer, Comunitat<Integer>> nodeToComunitat = new HashMap<Integer, Comunitat<Integer>>();

        HashMap<Integer, T> traduccioGraf = new HashMap<Integer, T>();

        ConjuntComunitats<Integer> classificacio = new ConjuntComunitats<Integer>();

        // Traduim el graf a un graf d'enters:
        Graf<Integer> grafLouvain = convertirGraf(grafOriginal, traduccioGraf, classificacio);

        double m2 = m2(grafLouvain); // només cal calcular m2 un cop

        while(numComunitats > criteriParada){
            //Fase 1
            //Cada node és una comunitat
            ConjuntComunitats<Integer> conjuntComunitats = new ConjuntComunitats<Integer>();
            for(Integer node : grafLouvain.getNodes()){
                Comunitat<Integer> c = new Comunitat<Integer>(node, node);
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

            // conjuntComunitats -> Nodes (amb els seus arcs entre nodes i self-loops)
            // classificacio -> afegir per cada comunitat anterior, una comunitat:

            // Primera passada, Fase 1: Comunitat1(1,3,7) Comunitat2(2,4,5) Comunitat3(6)
            // classificacio =  Comunitat1(1,3,7) Comunitat2(2,4,5) Comunitat3(6)
            // Ara a la fase 2 tenim 3 nodes: 1 2 i 3

            // Segona passada, Fase 1: Comunitat1(1,2) Comunitat2(3)
            // classificacio = Comunitat1(1,3,7,2,4,5) Comunitat2(6)


            ArrayList<Comunitat<Integer>> comunitatsLocals = conjuntComunitats.getComunitats();
            ConjuntComunitats<Integer> novaClassificacio = new ConjuntComunitats<Integer>();
            Integer idNova = 0;
            for(Comunitat<Integer> comunitatLocal : comunitatsLocals){
                HashSet<Integer> nodesLocals = comunitatLocal.getNodes();
                Comunitat<Integer> unioComunitats = new Comunitat<Integer>();
                unioComunitats.setId(idNova);
                novaClassificacio.afegirComunitat(unioComunitats);
                for(Integer i : nodesLocals){
                    Comunitat<Integer> comunitatNodesAfegir;
                    try {
                        comunitatNodesAfegir = classificacio.getComunitat(i);
                        HashSet<Integer> nodesAfegir = comunitatNodesAfegir.getNodes();
                        for(Integer nodeAfegir : nodesAfegir){
                            unioComunitats.afegirNode(nodeAfegir);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ++idNova;
            }
            classificacio = novaClassificacio;

            // Ara, crear el nou graf
            Graf<Integer> grafLouvainNou = new Graf<Integer>();
            for(Comunitat<Integer> comunitat : comunitatsLocals){
                Integer node = comunitat.getId();
                grafLouvainNou.afegirNode(node);
                double selfLoop = sigmaIn(comunitat, grafLouvain);
                grafLouvainNou.afegirArc(new Arc<Integer>(selfLoop*2,node,node));

            }


        }
        // Creem el conjunt de comunitats que retornarem i fem la traduccio
        ConjuntComunitats<T> conjuntComunitatsT = new ConjuntComunitats<T>();

        ArrayList<Comunitat<Integer>> comunitats = classificacio.getComunitats();
        for(Comunitat<Integer> comunitat : comunitats){
            Comunitat<T> comunitatT = new Comunitat<T>();
            conjuntComunitatsT.afegirComunitat(comunitatT);
            HashSet<Integer> nodesI = comunitat.getNodes();
            for(Integer node : nodesI){
                T nodeT = traduccioGraf.get(node);
                comunitatT.afegirNode(nodeT);
            }
        }

        return conjuntComunitatsT;
    }

    private Graf<Integer> convertirGraf(Graf<T> grafOriginal, HashMap<Integer, T> traduccioIntegerT,
                                        ConjuntComunitats<Integer> classificacioInicial){
        HashMap<T, Integer> traduccioTInteger = new HashMap<T, Integer>();
        HashSet<T> nodesOriginals = grafOriginal.getNodes();
        Graf<Integer> grafFinal = new Graf<Integer>();
        Integer i = 0;
        for(T nodeOriginal : nodesOriginals){
            traduccioIntegerT.put(i, nodeOriginal);
            traduccioTInteger.put(nodeOriginal, i);
            grafFinal.afegirNode(i);
            classificacioInicial.afegirComunitat(new Comunitat<Integer>(i, i));
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
