package domini.Controladors;

import domini.InformacioCjtComunitats;
import domini.TipusAlgorisme;
import graf.GrafWikipedia;
import graf.NodeCategoria;
import graf.grafgenerator.Criteris.Criteri;
import graf.grafgenerator.GrafGenerator;
import prop.classescompartides.algorismes.AlgorismeLouvain;
import prop.classescompartides.algorismes.CtrlGirvanBron;
import prop.classescompartides.algorismes.grupclique.CtrlAlgoritmoClique;
import prop.classescompartides.graf.Algoritme;
import prop.classescompartides.graf.ConjuntComunitats;
import prop.classescompartides.graf.Graf;
import prop.classescompartides.utils.Pair;

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
    private GrafWikipedia grafWikipedia;
    private TipusAlgorisme tipusAlgorisme;
    private double par1;
    private ArrayList<Criteri> criteris;

    /**
     * Constructora per defecte de la classe
     * @param grafWikipedia
     * @param tipusAlgorisme
     * @param par1
     * @param criteris
     */
    public CtrlAlgorisme(GrafWikipedia grafWikipedia, TipusAlgorisme tipusAlgorisme, double par1, ArrayList<Criteri> criteris){
        this.grafWikipedia = grafWikipedia;
        this.tipusAlgorisme = tipusAlgorisme;
        this.par1 = par1;
        this.criteris = criteris;
    }

    /**
     * Cerca comunitats en un graf seguint un dels 3 algorismes definits
     * @return comunitats en un graf seguint un dels 3 algorismes definits
     */
    public Pair<ConjuntComunitats<NodeCategoria>, InformacioCjtComunitats> cercarComunitats(){
        Algoritme<NodeCategoria> algorisme;


        if(tipusAlgorisme == TipusAlgorisme.LOUVAIN) {
            algorisme = new AlgorismeLouvain<NodeCategoria>();
        }
        else if(tipusAlgorisme == TipusAlgorisme.GIRVAN){
            algorisme = new CtrlGirvanBron<>();
        }
        else{ // Clique
            algorisme = new CtrlAlgoritmoClique<>();
        }

        long startTime = System.currentTimeMillis();
        GrafGenerator generator = new GrafGenerator();
        Graf<NodeCategoria> graf = generator.generate(grafWikipedia, criteris);
        long generatorTime = System.currentTimeMillis() - startTime;

        System.out.println("Temps en generar: "+generatorTime+"ms");
        System.out.println("Graf generat: "+graf.toString());
        ConjuntComunitats<NodeCategoria> comunitats = algorisme.cercarComunitats(graf, par1);

        long elapsedTime = System.currentTimeMillis() - startTime - generatorTime;
        System.out.println("Temps en cercar comunitats: " +elapsedTime+"ms");


        InformacioCjtComunitats informacioCjtComunitats = new InformacioCjtComunitats(elapsedTime,comunitats.getNumComunitats(),tipusAlgorisme,"", 0);
        // TODO: afegir criteris i mitjana i s'ha de posar en algun lloc

        return new Pair<> (comunitats, informacioCjtComunitats);
    }

}
