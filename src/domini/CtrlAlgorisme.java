package domini;

import graf.GrafWikipedia;
import graf.NodeCategoria;
import graf.grafgenerator.Criteris.Criteri;
import graf.grafgenerator.GrafGenerator;
import prop.classescompartides.algorismes.AlgorismeLouvain;
import prop.classescompartides.algorismes.CtrlGirvanBron;
import prop.classescompartides.algorismes.grupclique.AlgoritmoClique;
import prop.classescompartides.graf.Algoritme;
import prop.classescompartides.graf.ConjuntComunitats;
import prop.classescompartides.graf.Graf;

import java.util.ArrayList;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 02/05/2015
 */

/**
 * Controlador d'Algorisme
 */

public class CtrlAlgorisme{
    GrafWikipedia grafWikipedia;
    String tipusAlgorisme; // "Louvain", "Girvan", "Clique"
    int par1, par2;
    ArrayList<Criteri> criteris;

    /**
     * Constructora per defecte de la classe
     * @param grafWikipedia
     * @param tipusAlgorisme
     * @param par1
     * @param par2
     * @param criteris
     */
    public CtrlAlgorisme(GrafWikipedia grafWikipedia, String tipusAlgorisme, int par1, int par2, ArrayList<Criteri> criteris){
        this.grafWikipedia = grafWikipedia;
        this.tipusAlgorisme = tipusAlgorisme;
        this.par1 = par1;
        this.par2 = par2;
        this.criteris = criteris;
    }

    /**
     * Cerca comunitats en un graf seguint un dels 3 algorismes definits
     * @return comunitats en un graf seguint un dels 3 algorismes definits
     */
    public ConjuntComunitats<NodeCategoria> cercarComunitats(){
        Algoritme<NodeCategoria> algorisme;

        if(tipusAlgorisme.toLowerCase().equals("louvain")) {
            algorisme = new AlgorismeLouvain<NodeCategoria>();
        }
        else if(tipusAlgorisme.toLowerCase().equals("girvan")){
            algorisme = new CtrlGirvanBron<>();
        }
        else{ // Clique
            algorisme = new AlgoritmoClique<NodeCategoria>();
        }

        GrafGenerator generator = new GrafGenerator();
        Graf<NodeCategoria> graf = generator.generate(grafWikipedia, criteris);

        long startTime = System.currentTimeMillis();
        ConjuntComunitats<NodeCategoria> comunitats = algorisme.cercarComunitats(graf, par1, par2);
        long elapsedTime = System.currentTimeMillis() - startTime;

        InformacioCjtComunitats informacioCjtComunitats = new InformacioCjtComunitats(elapsedTime,comunitats.getNumComunitats(),tipusAlgorisme,"", 0);
        // TODO: afegir crtieris i mitjana
        // i s'ha de posar en algun lloc
        return comunitats;
    }

}
