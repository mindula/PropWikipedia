package prop.classescompartides.algorismes;

/**
 * 
 * @author Arnau Abella y Sergi Tortosa
 *
 */

import prop.classescompartides.graf.Arc;
import prop.classescompartides.graf.Graf;

import java.util.*;


public class GirvanNewman<T> {
	
	/**
	 * Calcula la betweenness de totes les arestes.
	 * @param g Graf
	 * @return Retorna un llistat amb el valor de la betwenness de cada aresta.
	 */
	
	public HashSet<Arc<T>> calcularBetweenness (Graf<T> g){
		HashMap<Arc<T>,Double> betweeness = new HashMap<Arc<T>,Double>();	
		Set<T> aux = g.getNodes();
		Iterator<T> it = aux.iterator();
		
		while(it.hasNext()) {
			T nodoInici = it.next();
			HashMap<T,Double> dists = new HashMap<T,Double>();
			HashMap<T,HashSet<T>> anteriors  = new HashMap<T,HashSet<T>>();
			stage1Dijkstra(g, nodoInici , dists , anteriors );
			stage2Betweeness(g,nodoInici, anteriors , betweeness);
		}
		
		HashSet<Arc<T>> ha = new HashSet<Arc<T>>();
		Arc<T> a;
		Iterator<Arc<T>> itArc = betweeness.keySet().iterator();
		a=null;
		
		if(itArc.hasNext()){
			a=itArc.next();
			ha.add(a);
		}
		
		while(itArc.hasNext()){
			Arc<T> keyToWork = itArc.next();
			if((double)betweeness.get(keyToWork) == (double) betweeness.get(a)){
				ha.add(keyToWork);	
			}
			else if((double)betweeness.get(keyToWork)> (double)betweeness.get(a)){
				a=keyToWork;
				ha=new HashSet<Arc<T>>();
				ha.add(a);				
			}
		}
		return ha;
	}


	/**
	 * Aplica l'algorisme Dijkstra calculant els camins m�nim del <tt>nodeInici</tt> a tots els altres nodes del graf.
	 * @param g Graf on s'aplica l'algorisme.
	 * @param nodeInici 
	 * @param dists Distancia del <tt>nodeInici</tt> als altres nodes.
	 * @param anteriors Node que s'ha usat per arribar fins aquell node dels camins m�nims.
	 */
	
	private void stage1Dijkstra(Graf<T> g,T nodeInici ,HashMap<T,Double> dists, HashMap<T,HashSet<T>> anteriors ){
		HashSet<T> visitat = new HashSet<T>();
		PriorityQueue<AristaGirvan> queue = new PriorityQueue<AristaGirvan>(g.mida(),new GirvanArcComparator());
		queue.add(new AristaGirvan(nodeInici, 0.0));
		dists.put(nodeInici,0.0 );
		while(!queue.isEmpty()){
			AristaGirvan agn = queue.poll();
			T u = agn.nodo;
			if(!visitat.contains(agn.nodo)){
				visitat.add(agn.nodo);
				HashSet<Arc<T>> hashArc = g.getNodesAdjacents(agn.nodo);
				Iterator<Arc<T>> it =  hashArc.iterator();
				while(it.hasNext()){
					Arc<T> ar= it.next();
					T v = ar.getNodeA();
					if(v == u) v= ar.getNodeB();
					
					if(!dists.containsKey(u))dists.put(u, Double.MAX_VALUE);	
					if(!dists.containsKey(v))dists.put(v, Double.MAX_VALUE);	
	
					if(dists.get(v) > (dists.get(u)+ (1/ar.getPes()))){
						dists.put(v, dists.get(u)+ (1/ar.getPes()));
						HashSet<T> hs = new HashSet<T>();
						hs.add(u);
						anteriors.put(v, hs);
						queue.add(new AristaGirvan(v, dists.get(v)));
					}
					else if(dists.get(v)== (dists.get(u)+(1/ar.getPes()))){
						HashSet<T> hs = anteriors.get(v);
						hs.add(u);
						anteriors.put(v, hs);
					}
				}
			}
		}
	}
	
	/**
	 * Recomposa els camins m�nims calculats pr�viament en el Dijkstra.
	 * @param g Graf on s'aplica l'algorisme.
	 * @param anteriors Node que s'ha usat per arribar fins aquell node dels camins m�nims.
	 * @param camins Conjunt d'arcs que representen els camins per arribar a cada node.
	 * @param nodeInici
	 * @param nodeFi
	 * @param nodeActual
	 * @param aristes Conjunt d'arcs del cam� per arribar fins el <tt>nodeActual</tt>.
	 */
	
	private  void recomposarCamins(Graf<T> g,HashMap<T,HashSet<T>> anteriors, HashMap<T,HashSet<HashSet<Arc<T>>>> camins ,T nodeInici, T nodeFi, T nodeActual , HashSet<Arc<T>> aristes ){
			if(nodeActual == nodeFi){
				camins.get(nodeInici).add(new HashSet<Arc<T>>(aristes));
			}
			else {
				Iterator<T> itAnt = anteriors.get(nodeActual).iterator();
				while(itAnt.hasNext()){
					T properNode = itAnt.next();
					Arc<T> ar = g.getArcEntre(nodeActual, properNode);
					aristes.add(ar);
					recomposarCamins(g,anteriors,camins,nodeInici,nodeFi,properNode,aristes);
					aristes.remove(ar);
				}
			}		
	}
	
	/**
	 * Calcula la betwenness de les aristes.
	 * @param g Graf on s'aplica l'algorisme.
	 * @param nodeInici
	 * @param anteriors Node que s'ha usat per arribar fins aquell node dels camins m�nims.
	 * @param betweeness Valor de betweeness de cada arc.
	 */
	
	private  void stage2Betweeness(Graf<T> g,T nodeInici, HashMap<T,HashSet<T>> anteriors, HashMap<Arc<T>,Double> betweeness) {
			
		HashMap<T, HashSet<HashSet<Arc<T>>>> camins = new HashMap<T, HashSet<HashSet<Arc<T>>>>();
		Iterator<T> itArc2 = anteriors.keySet().iterator();
		
		while(itArc2.hasNext()){
			T nodoTrabajo= itArc2.next();
			HashSet<Arc<T>> aristas = new HashSet<Arc<T>>();
			camins.put(nodoTrabajo, new HashSet<HashSet<Arc<T>>>());
			recomposarCamins(g, anteriors, camins,nodoTrabajo, nodeInici ,nodoTrabajo, aristas);
			HashSet<HashSet<Arc<T>>> r =camins.get(nodoTrabajo);
			Iterator<HashSet<Arc<T>>> itr = r.iterator();
			Integer siz = r.size();
			while(itr.hasNext()){
				HashSet<Arc<T>> c = itr.next();
				Iterator<Arc<T>> itDip = c.iterator();
				while(itDip.hasNext()){
					Arc<T> ardip = itDip.next();
					if(!betweeness.containsKey(ardip))betweeness.put(ardip,0.0);
					betweeness.put(ardip, betweeness.get(ardip)+ 1.0/((double) siz));
				}
			}
		}	
	}
	
	
	//Clase privada auxiliar usada por priorityQueue.
	private class AristaGirvan{
		public T nodo;
		public Double peso;
		public AristaGirvan(T nodo, Double peso) {
			super();
			this.nodo = nodo;
			this.peso = peso;
		}		
	}
	
	//Comparador de la priorityQueue
	private	class GirvanArcComparator implements Comparator<AristaGirvan>
	{
	    @Override
	    public int compare(AristaGirvan x, AristaGirvan y)
	    {
	        if (x.peso < y.peso)
	        {
	            return -1;
	        }
	        if (x.peso > y.peso)
	        {
	            return 1;
	        }
	        return 0;
	    }
	}

}


