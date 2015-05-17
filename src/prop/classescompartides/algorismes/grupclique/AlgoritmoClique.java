package prop.classescompartides.algorismes.grupclique;

import prop.classescompartides.graf.Arc;
import prop.classescompartides.graf.Comunitat;
import prop.classescompartides.graf.ConjuntComunitats;
import prop.classescompartides.graf.Graf;

import java.util.*;

/**
 * Created by Bernat
 */

public class AlgoritmoClique<T> {

    private int sumK = 0;
    private int maximalCounter = 0;
    private ArrayList<ArrayList<T>> maximalCliques = new ArrayList<>();

    public AlgoritmoClique() {}

    private double computeK(Graf<T> g){

        maximalCliques.clear();
        sumK = 0;
        maximalCounter = 0;

        ArrayList<T> X = new ArrayList<>();
        ArrayList<T> R = new ArrayList<>();
        ArrayList<T> P = new ArrayList<>(g.getNodes());
        computeBronKerbosch(R,P,X,"",g);
        return (double)sumK/maximalCounter;

    }


    private void computeBronKerbosch(ArrayList<T> R, ArrayList<T> P, ArrayList<T> X,String pre, Graf<T> g){
        if ((P.size() == 0) && (X.size() == 0)) {
            int kAct=R.size();
            maximalCliques.add((ArrayList<T>) R.clone());
            sumK += kAct;
            ++maximalCounter;
            return;
        }

        ArrayList<T> P1 = new ArrayList<T>(P);
        for (T v : P) {
            R.add(v);
            computeBronKerbosch(R, intersect(P1, getNbrs(v,g)), intersect(X, getNbrs(v,g)), pre + "\t",g);
            R.remove(v);
            P1.remove(v);
            X.add(v);
        }
    }



    private ArrayList<T> intersect(ArrayList<T> arlFirst, ArrayList<T> arlSecond) {
        ArrayList<T> arlHold = new ArrayList<T>(arlFirst);
        arlHold.retainAll(arlSecond);
        return arlHold;
    }


    private ArrayList<T> getNbrs(T v, Graf<T> g) {
        HashSet<Arc<T>> nodesAdj = g.getNodesAdjacents(v);
        ArrayList<T> result = new ArrayList<>();
        for (Arc<T> arc: nodesAdj){
            if (arc.getNodeA().equals(v)){
                result.add(arc.getNodeB());
            }
            else if (arc.getNodeB().equals(v)){
                result.add(arc.getNodeA());
            }
        }

        return result;
    }

    private double computeThreshold(Graf<T> gr, double co){
        List<Arc<T>> arcs = gr.getArcs();
        double pMax = 0.0;
        double pMin = 0.0;
        boolean pMinInicialitzat = false;
        for (Arc<T> arc : arcs){
            if (!pMinInicialitzat) {
                pMin = arc.getPes();
                pMinInicialitzat = true;
            }
            if ( arc.getPes()>pMax ) pMax = arc.getPes();
            if ( arc.getPes()<pMin ) pMin = arc.getPes();
        }
        double aux;
        if (pMax == pMin){
            aux=(pMin)*co;
            return aux;
        }else {
            aux=(pMax - pMin) * co;
            return pMax - aux;
        }
    }


    private void cutGraph(Graf<T> graf, double threshold){
        ArrayList<Arc<T>> arcs = new ArrayList<>(graf.getArcs());
        for (Arc<T> arc : arcs){
            if (arc.getPes() < threshold){
                graf.eliminarArc(arc);
            }
        }
    }

    private int round(double d) {
        return (int)Math.round(d);
    }

    public ConjuntComunitats<T> executeClique(Graf<T> graf, double granularidad){

        double threshold = computeThreshold(graf, granularidad);
        cutGraph(graf, threshold);
        double kAverage = computeK(graf);
        int k = round(kAverage);

        ArrayList<ArrayList<Integer>> cliqueMatrix = new ArrayList<>();
        ConjuntComunitats<T> cc = new ConjuntComunitats<>();

        for (int i = 0; i < maximalCliques.size(); ++i){
            ArrayList<T> clique = maximalCliques.get(i);
            ArrayList<Integer> cliqueMatrixRow = new ArrayList<>();
            for (int j = 0; j < maximalCliques.size(); ++j){
                if (j == i){
                    cliqueMatrixRow.add(clique.size());
                }
                else {
                    int n = intersect(maximalCliques.get(i),maximalCliques.get(j)).size();;
                    cliqueMatrixRow.add(n);
                }
            }
            cliqueMatrix.add(cliqueMatrixRow);
        }

        if (k > 1) {

            for (int i = 0; i < cliqueMatrix.size(); ++i) {
                ArrayList<Integer> row = new ArrayList<>(cliqueMatrix.get(i));
                for (int j = 0; j < cliqueMatrix.size(); ++j) {
                    if (j == i) {
                        if (row.get(j) < k) {
                            row.set(j, 0);
                        }
                    } else {
                        if (row.get(j) < (k - 1)) {
                            row.set(j, 0);
                        }
                    }
                }
                cliqueMatrix.set(i, row);
            }

            for (int i = 0; i < cliqueMatrix.size(); ++i) {
                ArrayList<Integer> row = new ArrayList<>(cliqueMatrix.get(i));
                for (int j = 0; j < cliqueMatrix.size(); ++j) {
                    if (row.get(j) > 0) {
                        row.set(j, 1);
                    }
                }
                cliqueMatrix.set(i, row);
            }

            ArrayList<ArrayList<T>> comunidades = new ArrayList<>();
            int ncom = maximalCliques.size();

            ArrayList<Boolean> v = new ArrayList<>();
            for(int i=0;i<ncom; i++)
                v.add(false);

            for(int i = 0; i < ncom; ++i){
                if(!v.get(i)) {
                    boolean a = false;
                    ArrayList<T> aux = new ArrayList<>();
                    Queue<Integer> q = new LinkedList<>();
                    q.add(cliqueMatrix.get(i).get(0));
                    Boolean first = true;
                    while (!q.isEmpty()) {
                        int p = q.peek();
                        q.poll();
                        if(first){
                            first = false;
                            p=i;
                        }
                        for (int j = 0; j < ncom; ++j) {
                            if (!v.get(j) && cliqueMatrix.get(p).get(j) == 1) {
                                q.add(j);
                                a = true;
                                v.set(j, true);
                                for(int w = 0; w<maximalCliques.get(j).size();++w){
                                    if(!aux.contains(maximalCliques.get(j).get(w)))
                                        aux.add(maximalCliques.get(j).get(w));
                                }
                            }
                        }
                    }
                    if (a) {
                        comunidades.add(aux);
                    }
                }
            }

            int idcom = 0;
            for (int x = 0; x < comunidades.size(); ++x){
                Comunitat<T> com = new Comunitat<>();
                com.setId(idcom);
                ++idcom;
                for (T node : comunidades.get(x)){
                    com.afegirNode(node);
                }
                cc.afegirComunitat(com);
            }

            cc = computeFreeNodes(cc,graf);
        }
        else {

            HashSet<T> nodes = graf.getNodes();
            int idCom = 0;
            for (T aux : nodes) {
                Comunitat<T> comAux = new Comunitat<>();
                comAux.setId(idCom);
                comAux.afegirNode(aux);
                cc.afegirComunitat(comAux);
                ++idCom;
            }
        }
        return cc;
    }


    private ConjuntComunitats<T> computeFreeNodes(ConjuntComunitats<T> conjCom, Graf<T> gr) {
        HashSet<T> n = gr.getNodes();
        ArrayList<Comunitat<T>> arrayCom = conjCom.getComunitats();
        for (Comunitat<T> com : arrayCom ) {
            HashSet<T> gn = com.getNodes();
            for (T gnode : gn) {
                if (n.contains(gnode)) {
                    n.remove(gnode);
                }
            }
        }
        int idCom = conjCom.getNumComunitats();
        for (T aux : n) {
            Comunitat<T> comAux = new Comunitat<>();
            comAux.setId(idCom);
            comAux.afegirNode(aux);
            conjCom.afegirComunitat(comAux);
            ++idCom;
        }
        return conjCom;
    }

}

