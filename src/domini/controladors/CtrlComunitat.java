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

    /**
     * Creadora per defecte
     */
    private CtrlComunitat(){
        this.conjunt = CtrlWikipedia.getInstance().getConjuntsGenerats();
        this.graf = CtrlWikipedia.getInstance().getGrafWiki();
    }

    /**
     * Es reseteja la informacio de tots els atributs
     */
    public void reset() {
        INSTANCE = null;
    }

    /**
     * Retorna una instancia de CtrlComunitat
     */
    public static CtrlComunitat getInstance() {
        if (INSTANCE == null) INSTANCE = new CtrlComunitat();
        return INSTANCE;
    }


    /**
     * Afegeix el conjunt de comunitats c al conjunt de comunitats del programa amb un nou id que
     * s'assigna de forma correlativa
     */
    public void afegirComunitatsGenerades(ConjuntComunitatWiki c) throws Exception {
        for (Comunitat<NodeCategoria> com: c.getCjtComunitats().getComunitats()) {
            conjunt.getCjtComunitats().afegirComunitat(com);
            String nom = "Tema: " + String.valueOf(conjunt.getCjtComunitats().getNumComunitats());
            com.setId(conjunt.getCjtComunitats().getNumComunitats());
            conjunt.setNom(com.getId(), nom);
            conjunt.setId(com.getId(), nom);
            conjunt.setDescripcio(com.getId(), "No hi ha cap descripcio");
        }
    }


    /**
     * Cas d'us Crear tema.
     */
    public void creaComunitat(String nom) throws Exception {
        int id = conjunt.getCjtComunitats().getNumComunitats() + 1;
        Comunitat<NodeCategoria> c = new Comunitat<>(id);
        conjunt.getCjtComunitats().afegirComunitat(c);
        conjunt.setNom(id, nom);
        conjunt.setId(id, nom);
        conjunt.setDescripcio(id, "No hi ha cap descripci�");
    }

    public Integer getId(String nom) {
        return conjunt.getId(nom);
    }

    /**
     * Cas d'us Modificar tema. Canviar nom.
     */
    public void modNomComunitat(int id, String nomnou){
        conjunt.setNom(id, nomnou);
        conjunt.setId(id, nomnou);
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
    public void unio(String nomComunitat1, String nomComunitat2) throws Exception{
        int idComunitat1 = conjunt.getId(nomComunitat1);
        int idComunitat2 = conjunt.getId(nomComunitat2);
        Comunitat<NodeCategoria> c = new Comunitat<>();
        //conjunt = CtrlWikipedia.getInstance().getConjuntsGenerats();
        c = OperacionsConjunts.unio(conjunt.getCjtComunitats().getComunitat(idComunitat1), conjunt.getCjtComunitats().getComunitat(idComunitat2));
        int id = conjunt.getCjtComunitats().getNumComunitats()+1;
        c.setId(id);
        conjunt.getCjtComunitats().afegirComunitat(c);
        conjunt.setNom(id, "Unio entre " + idComunitat1 + " i " + idComunitat2);
        conjunt.setId(id, "Unio entre " + idComunitat1 + " i " + idComunitat2);
        conjunt.setDescripcio(id, "No hi ha cap Descripcio");
        //CtrlWikipedia.getInstance().setConjuntsGenerats(conjunt);
    }

    /**
     * Cas d'us Operacio entre temes. Interseccio.
     */
    public void interseccio(String nomComunitat1, String nomComunitat2) throws Exception{
        int idComunitat1 = conjunt.getId(nomComunitat1);
        int idComunitat2 = conjunt.getId(nomComunitat2);
        Comunitat<NodeCategoria> c = new Comunitat<>();
        c = OperacionsConjunts.interseccio(conjunt.getCjtComunitats().getComunitat(idComunitat1), conjunt.getCjtComunitats().getComunitat(idComunitat2));
        int id = conjunt.getCjtComunitats().getNumComunitats()+1;
        c.setId(id);
        conjunt.getCjtComunitats().afegirComunitat(c);
        conjunt.setNom(id, "Interseccio entre " + idComunitat1 + " i " + idComunitat2);
        conjunt.setId(id, "Interseccio entre " + idComunitat1 + " i " + idComunitat2);
        conjunt.setDescripcio(id, "No hi ha cap Descripcio");
    }

    /**
     * Cas d'us Operacio entre temes. Diferencia.
     */
    public void diferencia (String nomComunitat1, String nomComunitat2) throws Exception{
        int idComunitat1 = conjunt.getId(nomComunitat1);
        int idComunitat2 = conjunt.getId(nomComunitat2);
        Comunitat<NodeCategoria> c = new Comunitat<>();
        c = OperacionsConjunts.diferencia(conjunt.getCjtComunitats().getComunitat(idComunitat1), conjunt.getCjtComunitats().getComunitat(idComunitat2));
        int id = conjunt.getCjtComunitats().getNumComunitats()+1;
        c.setId(id);
        conjunt.getCjtComunitats().afegirComunitat(c);
        conjunt.setNom(id, "Diferencia entre " + idComunitat1 + " i " + idComunitat2);
        conjunt.setId(id, "Diferencia entre " + idComunitat1 + " i " + idComunitat2);
        conjunt.setDescripcio(id, "No hi ha cap Descripcio");

    }

    /**
     * Cas d'us Eliminar comunitat.
     */
    public void eliminarComunitat (int idComunitat) throws Exception {
        conjunt.eliminarInfoComunitat(idComunitat);
        conjunt.getCjtComunitats().eliminarComunitat(conjunt.getCjtComunitats().getComunitat(idComunitat));
    }

    //TODO: Metode OPCIONAL Assignar color a un tema
}
