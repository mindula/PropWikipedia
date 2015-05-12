package prop.classescompartides.algorismes;

import prop.classescompartides.graf.Graf;

import java.util.*;

/**
 * 
 * @author Arnau Abella Gassol
 *
 */


/**
 * L'algorisme de Bron-Kerbosch troba els cliques maximals d'un graf no dirigit.
 * @param <T>
 */

public class BronKerbosch<T> {
	
	private Graf<T> graf;
	private ArrayList<ArrayList<T>> cliques;
	
	/**
	 * Constructor: crea un nou buscador de cliques maximals. El par�metre graf ha de ser un graf simple no dirigit.
	 * @param graf Graf en el qual buscarem els cliques maximals.
	 */
	public BronKerbosch(Graf<T> graf){
		super();
		this.graf = graf; 
	}
	
	//~M�todes ------------------------------------------------------
	
	/**
	 * Troba el cliqu� maximal m�s gran de tot el graf.
	 * @return Retorna un conjunt de diputats els quals perteneixen al cliqu� maximal major del graf.
	 */
	
	public ArrayList<T> obtCliqueMaxim(ArrayList<ArrayList<T>> cliques){
		int maxim = 0;
		ArrayList<T> cliqueMaxim = null;
		for(ArrayList<T> clique: cliques){
			if(maxim < clique.size()) {
				maxim = clique.size();
				cliqueMaxim = clique;
			}
		}
		
		return cliqueMaxim;
	}
	
	/**
	 * Troba els cliques m�xims del graf. Un clique �s maximal quan no es pot agrandar afegint v�rtex.
	 * @return Retorna un conjunt que representa els cliques maximals.
	 */
	
	public ArrayList<ArrayList<T>> obtCliques(){
		cliques = new ArrayList<ArrayList<T>>();
		ArrayList<T> cliquePotencial = new ArrayList<T>();
		ArrayList<T> candidats = new ArrayList<T>();
		ArrayList<T> trobats = new ArrayList<T>();
		Set<T> vertex = graf.getNodes();
		Iterator<T> it = vertex.iterator();
		while (it.hasNext()){
			candidats.add(it.next());
		}
		trobarCliques(cliquePotencial, candidats, trobats);
		return cliques;
	}
	
	/**
	 * Troba els cliques maximals del graf aplicant l'algorisme de Bron-Kerbosch.
	 * 
	 * @param cliquePotencial V�rtexs que poden donar lloc a un cliqu�.
	 * @param candidats V�rtexs que s�n candidats a cliques potencials.
	 * @param trobats V�rtexs que ja estan continguts en un cliqu� maximal. 
	 */
	
	private void trobarCliques(
			ArrayList<T> cliquePotencial,
			ArrayList<T> candidats,
			ArrayList<T> trobats)
	{
		ArrayList<T> vectorCandidats = new ArrayList<T>(candidats);
		if(!fin(candidats,trobats)){
			for(T candidat : vectorCandidats){
				ArrayList<T> nousCandidats = new ArrayList<T>();
				ArrayList<T> nousTrobats = new ArrayList<T>();
				cliquePotencial.add(candidat);
				candidats.remove(candidat);
				
				for(T nouCandidat : candidats){
					if(graf.existeixArc(candidat, nouCandidat)) nousCandidats.add(nouCandidat);	
				}
				
				for(T nouTrobat : trobats){
					if(graf.existeixArc(candidat, nouTrobat)) nousTrobats.add(nouTrobat);
				}	
				
				if(nousCandidats.isEmpty() && nousTrobats.isEmpty()){
					cliques.add(new ArrayList<T>(cliquePotencial));	
				}
				else{
					trobarCliques(cliquePotencial, nousCandidats, nousTrobats);
					
				}
				trobats.add(candidat);
				cliquePotencial.remove(candidat);	
			}	
		}
	}
	
	/**
	 * Retorna cert si un node trobat �s adjacent a tots els candidats, altrament retorna fals.
	 * 
	 * @param candidats Candidats a possible cliqu�.
	 * @param trobats V�rtex que pertanyen a un cliqu� maximal.
	 * @return Retorna cert si un node trobat �s adjacent a tots els candidats, altrament retorna fals.
	 */
	private boolean fin(
			ArrayList<T> candidats,
			ArrayList<T> trobats)
	{
		boolean fin = false;
		int contadorAristas;
		for(T trobat : trobats){
			contadorAristas = 0;
			for(T candidato : candidats){
				if(graf.existeixArc(trobat, candidato)) ++contadorAristas;	
			}
			if(contadorAristas == candidats.size()) fin = true;	
		}
		return fin;
	}
}
