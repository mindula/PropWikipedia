package domini;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard
 * Data: 5/2/15
 */
public class Levenshtein {
    private Levenshtein(){}

    public static int distancia (String s1, String s2) {
        int[][] d = new int[s1.length()+1][s2.length()+1];
        int i, j, cost;

        for (i = 0; i <= s1.length(); ++i) {
            d[i][0] = i;
        }
        for (j = 0; j <= s2.length(); ++j) {
            d[0][j] = j;
        }
        for (i = 1; i <= s1.length(); ++i) {
            for (j = 1; j <= s2.length(); ++j) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) cost = 0;
                else cost = 1;
                d[i][j] = minim(d[i-1][j]+1, d[i][j-1]+1, d[i-1][j-1]+cost);
            }
        }
        return d[s1.length()][s2.length()];
    }

    private static int minim (int i, int j, int k) {
        return Math.min(Math.min(i, j), k);
    }
}
