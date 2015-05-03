package domini;

import graf.GrafWikipedia;
import graf.NodeWiki;
import graf.graftransform.Criteris.Criteri;
import graf.graftransform.GrafGenerator;
import org.grupwiki.graf.Algoritme;
import org.grupwiki.graf.ConjuntComunitats;

import java.util.ArrayList;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 02/05/2015
 */

public class CtrlAlgorisme{
    GrafWikipedia grafWikipedia;
    String tipusAlgorisme; // "Louvain", "Girvan", "Clique"
    int par1, par2;
    ArrayList<Criteri> criteris;

    public CtrlAlgorisme(GrafWikipedia grafWikipedia, String tipusAlgorisme, int par1, int par2, ArrayList<Criteri> criteris){
        this.grafWikipedia = grafWikipedia;
        this.tipusAlgorisme = tipusAlgorisme;
        this.par1 = par1;
        this.par2 = par2;
        this.criteris = criteris;
    }

    public ConjuntComunitats<NodeWiki> cercarComunitats(){
        Algoritme<NodeWiki> algorisme;
        ConjuntComunitats<NodeWiki> comunitats;
        if(tipusAlgorisme.equals("Louvain")) {
            algorisme = new AlgorismeLouvain<NodeWiki>();
        }
        else if(tipusAlgorisme.equals("Girvan")){
            algorisme = new AlgorismeGirvan<NodeWiki>();
        }
        else{ // Clique
            algorisme = new AlgorismeClique<NodeWiki>();
        }

        GrafGenerator generator = new GrafGenerator();
        generator.generate(grafWikipedia, criteris);

        return comunitats;
    }

}
