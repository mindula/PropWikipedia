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
        this.conjunt = CtrlWikipedia.getInstance().getConjuntsGenerats();
        this.graf = CtrlWikipedia.getInstance().getGrafWiki();
    }

    public static CtrlComunitat getInstance() {
        if (INSTANCE == null) INSTANCE = new CtrlComunitat();
        return INSTANCE;
    }

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
        Comunitat<NodeCategoria> c = new Comunitat<NodeCategoria>(conjunt.getCjtComunitats().getNumComunitats()+1);
        conjunt.getCjtComunitats().afegirComunitat(c);
        conjunt.setNom(conjunt.getCjtComunitats().getNumComunitats(), nom);
        conjunt.setId(conjunt.getCjtComunitats().getNumComunitats(), nom);
    }

    public Integer getId(String nom) {
        return conjunt.getId(nom);
    }

    /**
     * Cas d'us Modificar tema. Canviar nom.
     */
    public void modNomComunitat(int id, String nomnou){
        conjunt.setNom(id, nomnou);
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
        c = OperacionsConjunts.unio(conjunt.getCjtComunitats().getComunitat(idComunitat1), conjunt.getCjtComunitats().getComunitat(idComunitat2));
        c.setId(conjunt.getCjtComunitats().getNumComunitats());
        conjunt.getCjtComunitats().afegirComunitat(c);
        conjunt.setNom(c.getId(), "Unio entre " + idComunitat1 + " i " + idComunitat2);
        conjunt.setId(c.getId(), "Unio entre " + idComunitat1 + " i " + idComunitat2);
        conjunt.setDescripcio(c.getId(), "No hi ha cap Descripcio");
    }

    /**
     * Cas d'us Operacio entre temes. Interseccio.
     */
    public void interseccio(String nomComunitat1, String nomComunitat2) throws Exception{
        int idComunitat1 = conjunt.getId(nomComunitat1);
        int idComunitat2 = conjunt.getId(nomComunitat2);
        Comunitat<NodeCategoria> c = new Comunitat<>();
        c = OperacionsConjunts.interseccio(conjunt.getCjtComunitats().getComunitat(idComunitat1), conjunt.getCjtComunitats().getComunitat(idComunitat2));
        c.setId(conjunt.getCjtComunitats().getNumComunitats());
        conjunt.getCjtComunitats().afegirComunitat(c);
        conjunt.setNom(c.getId(), "Interseccio entre " + idComunitat1 + " i " + idComunitat2);
        conjunt.setId(c.getId(), "Interseccio entre " + idComunitat1 + " i " + idComunitat2);
        conjunt.setDescripcio(c.getId(), "No hi ha cap Descripcio");
    }

    /**
     * Cas d'us Operacio entre temes. Diferencia.
     */
    public void diferencia (String nomComunitat1, String nomComunitat2) throws Exception{
        int idComunitat1 = conjunt.getId(nomComunitat1);
        int idComunitat2 = conjunt.getId(nomComunitat2);
        Comunitat<NodeCategoria> c = new Comunitat<>();
        c = OperacionsConjunts.diferencia(conjunt.getCjtComunitats().getComunitat(idComunitat1), conjunt.getCjtComunitats().getComunitat(idComunitat2));
        c.setId(conjunt.getCjtComunitats().getNumComunitats());
        conjunt.getCjtComunitats().afegirComunitat(c);
        conjunt.setNom(c.getId(), "Diferencia entre " + idComunitat1 + " i " + idComunitat2);
        conjunt.setId(c.getId(), "Diferencia entre " + idComunitat1 + " i " + idComunitat2);
        conjunt.setDescripcio(c.getId(), "No hi ha cap Descripcio");

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
