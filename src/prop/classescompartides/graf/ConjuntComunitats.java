package prop.classescompartides.graf;

import java.util.ArrayList;

/**
 * Aquesta classe representa un conjunt de Comunitats, amb un seguit d'operacions bàsiques
 * @param <T>
 */

public class ConjuntComunitats<T> {
    protected ArrayList<Comunitat<T>> cjtComunitats;

    /**
     * Constructor per defecte, inicialitza les estructures internes de la classe
     */
    public ConjuntComunitats() {
        cjtComunitats = new ArrayList<Comunitat<T>>();
    }

    /**
     * Afegeix una comunitat al conjunt
     * @param c
     */
    public void afegirComunitat(Comunitat<T> c) {
        this.cjtComunitats.add(c);
    }

    /**
     * Retorna el nombre de comunitats que pertanyen al conjunt
     * @return el nombre de comunitats que pertanyen al conjunt
     */
    public int getNumComunitats() {
        return cjtComunitats.size();
    }

    /**
     * Retorna una comunitat localitzada a la posició i
     * @param id
     * @return una comunitat localitzada a la posició i
     */
    public Comunitat<T> getComunitat(int id) throws Exception{
        for (Comunitat<T> c : cjtComunitats) {
            if (c.getId() == id) {
                return c;
            }
        }
        throw new Exception("No hi ha una comunitat amb aquesta id");
    }

    public void eliminarComunitat(Comunitat<T> c) {
        cjtComunitats.remove(c);
    }

    public ArrayList<Comunitat<T>> getComunitats() {
        return cjtComunitats;
    }

    @Override
    public String toString() {
        String s = "{";
        for(int i = 0; i<cjtComunitats.size(); i++){
            if(cjtComunitats.get(i).getMida() >= 1) {
                s += cjtComunitats.get(i).toString();
                if (i != cjtComunitats.size() - 1)
                    s += "\n";
            }
        }

        s+="}";
        return s;
    }
}
