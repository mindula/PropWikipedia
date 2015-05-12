package prop.classescompartides.algorismes;

/**
 * 
 * @author Arnau Abella Gassol
 *
 */

import prop.classescompartides.graf.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class CtrlGirvanBron<T> extends Algoritme<T> {
	
	/**
	 * Aquesta classe �s l'encarregada de gestionar els algorismes GirvanNewman i BronKerbosch
	 * i de retornar les comunitats trobades dins del graf
	 * 
	 */

	
	/**
	 * Donat un graf simple no dirigit i el criteriDeParada, retorna un <tt>ConjuntComunitats</tt> amb les comunitats trobades mitjan�ant l'algorisme de GirvanNewman.
	 * @param graf Graf on s'aplia l'algorisme.
	 * @param criteriDeParada Nombre m�nim de comunitats diferents que trobar� l'algorisme.
	 * @param noUsada
	 * @return Retorna un <tt>ConjuntComunitats</tt> amb les comunitats trobades mitjan�ant l'algorisme de GirvanNewman.
	 */
	
	@Override
	public ConjuntComunitats<T> cercarComunitats(Graf<T> graf, int criteriDeParada, int noUsada){
		ConjuntComunitats<T> resultat;
		int nComunitats = 0;
		int vertexTotal = graf.ordre();

		while(true){
			resultat = new ConjuntComunitats<T>();
			int indexComunitat = 1;
			BronKerbosch<T> bronkerbosch = new BronKerbosch<T>(graf);
			ArrayList<ArrayList<T>> cliques = bronkerbosch.obtCliques();
			int vertexEnComunitat = 0;
			while(vertexEnComunitat != vertexTotal){
				ArrayList<T> clique = bronkerbosch.obtCliqueMaxim(cliques);
				cliques.remove(clique);
				vertexEnComunitat += clique.size();
				Iterator<T> it2 = clique.iterator();
				Comunitat<T> comunitat = new Comunitat<T>();
				comunitat.setId(indexComunitat);
				++indexComunitat;
				while(it2.hasNext()) {
					T t = it2.next();
					comunitat.afegirNode(t);
					for(ArrayList<T> cli : cliques){
						cli.remove(t);
					}
				}
				resultat.afegirComunitat(comunitat);
			}
			nComunitats = resultat.getNumComunitats();
			if(nComunitats >= criteriDeParada){
				break;
			}
			GirvanNewman<T> gn = new GirvanNewman<T>();
			HashSet<Arc<T>> arcsEliminats = gn.calcularBetweenness(graf);
			Iterator<Arc<T>> it = arcsEliminats.iterator();
			while(it.hasNext()){
				Arc<T> arc = it.next();
				graf.eliminarArc(arc);
			}
		}
		return resultat;
	}

}
