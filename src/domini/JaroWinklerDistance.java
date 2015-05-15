package domini;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 15/05/15
 */
public class JaroWinklerDistance {

    public static double calculate(String s1, String s2){
        if (s1.equals(s2)) return 1.0;

        //s1 ha de ser la paraula m�s curta
        if (s1.length() > s2.length()){
            String aux = s2;
            s2 = s1;
            s1 = aux;
        }


        int dmax = (s2.length()/2)-1;
        int m = 0;
        int t = 0;
        int l = 0;
        boolean fiPrefix = false;
        for (int i = 0; i < s1.length(); ++i){
            char c = s1.charAt(i);
            for (int j = Math.max(0, i - dmax); j < Math.min(s2.length(), i+dmax); ++j){
                if (c == s2.charAt(j)){
                    ++m;
                    if (i != j) ++t;
                    else if (!fiPrefix) ++l;
                }
                else if(i == j && !fiPrefix) fiPrefix = true;
            }
        }
        if (m == 0) return 0.0;
        t /= 2;
        double dj = ((m/(double)s1.length()) + (m/(double)s2.length()) + ((m-t)/(double)m))/3;
        if (dj < 0.7) return dj;
        return dj+(l*0.1*(1-dj));
    }


}
