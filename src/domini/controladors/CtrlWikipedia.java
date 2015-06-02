package domini.controladors;

import domini.modeldades.ConjuntComunitatWiki;
import domini.modeldades.InfoCerca;
import domini.modeldades.InformacioCjtComunitats;
import domini.modeldades.graf.GrafWikipedia;
import domini.modeldades.graf.NodeCategoria;
import domini.modeldades.graf.NodePagina;
import persistencia.GrafParser;
import prop.classescompartides.graf.Graf;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 19/5/15
 */

/**
 * CtrlWikipedia de la Wikipedia // TODO: superdefinicion
 */

public class CtrlWikipedia implements Serializable{
    private static CtrlWikipedia INSTANCE;

    private String dataCreacio;
    private GrafWikipedia grafWiki;
    private Graf<NodeCategoria> grafAlgoritme;
    private ConjuntComunitatWiki conjuntsGenerats;
    private ArrayList<InfoCerca> llistatCerques;
    private ArrayList<InformacioCjtComunitats> informacioExecucions;

    /**
     * Creadora per defecte
     */
    private CtrlWikipedia() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dataCreacio = new Date();
        this.dataCreacio = dateFormat.format(dataCreacio);
        grafWiki = new GrafWikipedia();
        conjuntsGenerats = new ConjuntComunitatWiki();
        llistatCerques = new ArrayList<InfoCerca>();
        informacioExecucions = new ArrayList<>();
    }

    /**
     * Es reseteja la informacio de tots els atributs
     */
    public void reset() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dataCreacio = new Date();
        this.dataCreacio = dateFormat.format(dataCreacio);
        grafWiki = new GrafWikipedia();
        conjuntsGenerats = new ConjuntComunitatWiki();
        llistatCerques = new ArrayList<InfoCerca>();
        informacioExecucions = new ArrayList<>();
    }

    /**
     * Retorna una instancia de CtrlWikipedia
     */
    public static CtrlWikipedia getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CtrlWikipedia();
        }
        return INSTANCE;
    }

    /**
     * S'instancia CtrlWikipedia mitjançant la instancia instance
     */
    public static void setInstance(CtrlWikipedia instance) {
        CtrlWikipedia.INSTANCE = instance;
    }

    /**
     * Retorna la data de creació de la sessió
     */
    public String getDataCreacio() {
        return dataCreacio;
    }

    /**
     * Es guarda la informacio d'un conjunt de comunitats informacioCjtComunitats
     */
    public void afegirInfoExecucio (InformacioCjtComunitats informacioCjtComunitats) {
        informacioExecucions.add(informacioCjtComunitats);
    }

    /**
     * Es retorna la informaco del conjunt de comunitats i
     */
    public String getInfoExecucio (int i) {
        InformacioCjtComunitats info = informacioExecucions.get(i);
        String res = String.valueOf(info.getTempsComunitats()+info.getTempsgenerar()) + "-" + info.getNombreComunitats() + "-"
                + info.getAlgoritme() + "-" + info.getCriteri() + "-" +
                info.getMitjanaNodesPerComunitat();
        return res;
    }

    /**
     * Es retorna el nombre total d'informacions dels conjunt de comunitats generats
     */
    public int getNombreExecucions() {
        return informacioExecucions.size();
    }


    /**
     * Es retorna el Graf de la Wikipedia
     */
    public GrafWikipedia getGrafWiki() {
        return grafWiki;
    }

    /**
     * Es el graf del programa passa a ser el GrafWikipedia grafWiki
     */
    public void setGrafWiki(GrafWikipedia grafWiki) {
        this.grafWiki = grafWiki;
    }

    /**
     * Retorna el graf de categories pels algorismes
     */
    //TODO: probablement ja no fa falta aqui, crec que te mes sentit a CtrlAlgorisme
    public Graf<NodeCategoria> getGrafAlgoritme() {
        return grafAlgoritme;
    }

    /**
     * El graf utilitzat pels algoritmes grafAlgoritme
     */
    public void setGrafAlgoritme(Graf<NodeCategoria> grafAlgoritme) {
        this.grafAlgoritme = grafAlgoritme;
    }

    /**
     * Cas d'us Importar Fitxer
     */
    public void getGrafWikiFromFile(String path) throws IOException {
        GrafParser.parse(path, grafWiki);
        System.out.println(grafWiki);
        //TODO: faltaria guardar el nou graf (o subgraf) importat en el nostre format. No se si s'hauria de fer aqui o a CtrlSessio
    }

    /**
     * Cas d'us Afegir Categoria
     */
    public void afegirCat(String nom){
        NodeCategoria nodeC = new NodeCategoria(nom);
        grafWiki.afegirCategoria(nodeC);
    }

    /**
     * Cas d'us Afegir Pagina
     */
    public void afegirPag(String nom){
        NodePagina nodeC = new NodePagina(nom);
        grafWiki.afegirPagina(nodeC);
    }

    /**
     * Cas d'us Eliminar Categoria
     */
    public void elimCat(String nom){
        grafWiki.eliminarCategoria(grafWiki.getNodeCat(nom));
    }

    /**
     * Cas d'us Eliminar Pagina
     */
    public void elimPag(String nom){
        grafWiki.eliminarPagina(grafWiki.getNodePag(nom));
    }

    /**
     * S'obte el conjunt de comunitats del programa
     */
    public ConjuntComunitatWiki getConjuntsGenerats() {
        return conjuntsGenerats;
    }

    /**
     * El conjunt del programa passa a ser el ConjuntComunitatWiki conjuntsGenerats
     */
    public void setConjuntsGenerats(ConjuntComunitatWiki conjuntsGenerats) {
        this.conjuntsGenerats = conjuntsGenerats;
    }

    /**
     * S'obtenen els noms de totes les categories que té el graf del programa
     */
    public ArrayList<String> getNomsCategories(){
        ArrayList<NodeCategoria> cats = grafWiki.getCategories();
        ArrayList<String> nomCats = new ArrayList<>();
        for (NodeCategoria cat : cats) {
            nomCats.add(cat.getNom());
        }
        return nomCats;
    }

    /**
     * S'obtenen els noms de totes les pagines que té el graf del programa
     */
    public ArrayList<String> getNomsPagines(){
        ArrayList<NodePagina> pags = grafWiki.getPagines();
        ArrayList<String> nomPags = new ArrayList<>();
        for (NodePagina pag : pags) {
            nomPags.add(pag.getNom());
        }
        return nomPags;
    }

    /**
     * Es retorna un llistat amb totes les cerques que s'han fet
     */
    public ArrayList<InfoCerca> getLlistatCerques() {
        return llistatCerques;
    }

    @Override
    public String toString() {
        return "CtrlWikipedia{" +'\n' +
                "dataCreacio='" + dataCreacio + '\n' +
                ", grafWiki=" + grafWiki +'\n' +
                ", grafAlgoritme=" + grafAlgoritme +'\n' +
                ", conjuntsGenerats=" + conjuntsGenerats +'\n' +
                '}';
    }
}
