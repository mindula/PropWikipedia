package domini.controladors;

import domini.controladors.graf.OperacionsConjunts;
import domini.modeldades.ConjuntComunitatWiki;
import domini.modeldades.graf.GrafWikipedia;
import domini.modeldades.graf.NodeCategoria;
import prop.classescompartides.graf.Comunitat;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 21/05/15
 */
public class CtrlComunitat {

    private static CtrlComunitat INSTANCE;

    private ConjuntComunitatWiki conjunt;
    private GrafWikipedia graf;

    private CtrlComunitat(){
        this.conjunt = new ConjuntComunitatWiki();
        this.graf = CtrlWikipedia.getInstance().getGrafWiki();
    }

    public static CtrlComunitat getInstance() {
        if (INSTANCE == null) INSTANCE = new CtrlComunitat();
        return INSTANCE;
    }

    public void afegirConjuntsGenerats(ConjuntComunitatWiki c) throws Exception {
        for (Comunitat<NodeCategoria> com: c.getCjtComunitats().getComunitats()){
            conjunt.getCjtComunitats().afegirComunitat(com);
            String nom = "Tema: " + String.valueOf(com.getId());
            System.out.println(com.getId());
            conjunt.setNom(com.getId(), nom);
            conjunt.setId(com.getId(), nom);
            conjunt.setDescripcio(com.getId(), "No hi ha cap descripcio");
        }
    }


    /**
     * Cas d'us Crear tema.
     */
    public void creaComunitat(String nom, int id){
        Comunitat<NodeCategoria> c = new Comunitat<NodeCategoria>(id);
        conjunt.setNom(id,nom);
        conjunt.setId(id, nom);
        conjunt.getCjtComunitats().afegirComunitat(c);
    }

    public Integer getId(String nom) {
        return conjunt.getId(nom);
    }

    /**
     * Cas d'us Modificar tema. Canviar nom.
     */
    public void modNomComunitat(int id, String nomnou){
        conjunt.setNom(id,nomnou);
    }

    public void modDescripcioComunitat(int idComunitat, String descripcio){
        conjunt.setDescripcio(idComunitat, descripcio);
    }

    /**
     * Cas d'us Modificar tema. Afegir Categoria.
     */
    public void afegirCatComunitat (int idComunitat, String nomCategoria) throws Exception {
        NodeCategoria categoria = graf.getNodeCat(nomCategoria);
        conjunt.getCjtComunitats().getComunitat(idComunitat).afegirNode(categoria);
    }

    /**
     * Cas d'us Modificar tema. Eliminar Categoria.
     */
    public void eliminarCatComunitat(int idComunitat, String nomCategoria) throws Exception {
        NodeCategoria categoria = graf.getNodeCat(nomCategoria);
        conjunt.getCjtComunitats().getComunitat(idComunitat).eliminarNode(categoria);
    }

    /**
     * Cas d'us Consultar tema.
     */
    public ConjuntComunitatWiki getConjunt() {
        return conjunt;
    }

    /**
     * Cas d'us Operacio entre temes. Unio.
     */
    public Comunitat<NodeCategoria> unio(int idComunitat1, int idComunitat2) throws Exception{
        return OperacionsConjunts.unio(conjunt.getCjtComunitats().getComunitat(idComunitat1), conjunt.getCjtComunitats().getComunitat(idComunitat2));
    }

    /**
     * Cas d'us Operacio entre temes. Interseccio.
     */
    public Comunitat<NodeCategoria> interseccio(int idComunitat1, int idComunitat2) throws Exception{
        return OperacionsConjunts.interseccio(conjunt.getCjtComunitats().getComunitat(idComunitat1), conjunt.getCjtComunitats().getComunitat(idComunitat2));
    }

    /**
     * Cas d'us Operacio entre temes. Diferencia.
     */
    public Comunitat<NodeCategoria> diferencia (int idComunitat1, int idComunitat2) throws Exception{
        return OperacionsConjunts.diferencia(conjunt.getCjtComunitats().getComunitat(idComunitat1), conjunt.getCjtComunitats().getComunitat(idComunitat2));
    }

    /**
     * Cas d'us Eliminar comunitat.
     */
    public void eliminarComunitat (int idComunitat) throws Exception {
        conjunt.getCjtComunitats().eliminarComunitat(conjunt.getCjtComunitats().getComunitat(idComunitat));
    }

    //TODO: Metode OPCIONAL Assignar color a un tema
}
