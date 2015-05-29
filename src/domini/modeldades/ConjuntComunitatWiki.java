package domini.modeldades;

import domini.modeldades.graf.NodeCategoria;
import prop.classescompartides.graf.ConjuntComunitats;

import java.util.HashMap;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 22/05/15
 */
public class ConjuntComunitatWiki {
    private InformacioCjtComunitats informacio;
    private ConjuntComunitats<NodeCategoria> cjtComunitats;

    private HashMap<Integer,String> noms;
    private HashMap<Integer,String> descripcions;


    public ConjuntComunitatWiki(){
        cjtComunitats = new ConjuntComunitats<NodeCategoria>();
        noms = new HashMap<Integer,String>();
        descripcions = new HashMap<Integer,String>();
    }


    public InformacioCjtComunitats getInformacio() {
        return informacio;
    }

    public void setInformacio(InformacioCjtComunitats informacio) {
        this.informacio = informacio;
    }

    public ConjuntComunitats<NodeCategoria> getCjtComunitats() {
        return cjtComunitats;
    }

    public void setCjtComunitats(ConjuntComunitats<NodeCategoria> cjtComunitats) {
        this.cjtComunitats = cjtComunitats;
    }

    public HashMap<Integer, String> getNoms() {
        return noms;
    }

    public String getNom(Integer id){
        return noms.get(id);
    }

    public void setNom(Integer id, String nounom){
        noms.put(id, nounom);
    }

    public HashMap<Integer, String> getDescripcions() {
        return descripcions;
    }

    public String getDescripcio(Integer id){
        return descripcions.get(id);
    }

    public void setDescripcio(Integer id, String descripcio){
        descripcions.put(id,descripcio);
    }
}
