package domini;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard
 * Data: 5/2/15
 */
public class TestLevenshtein {
    public static void main(String args[]) {
        String s1 = "hola";
        String s2 = "jole";
        System.out.println(Levenshtein.distancia(s1, s2));
    }
}
