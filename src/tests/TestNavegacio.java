package tests;

import domini.Navegacio;
import domini.Sessio;

/**
 * Grup 3: Wikipedia
 * Usuari: aleix.paris
 * Data: 03/05/2015
 */
public class TestNavegacio {
    public static void main(String[] args){
        Navegacio navegacio = new Navegacio(Sessio.getInstance().getGrafWiki());
        System.out.println("Escriu 0 per buscar una cateogria o 1 per pagina:");

    }

}
