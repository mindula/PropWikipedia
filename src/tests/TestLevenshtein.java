package tests;

import domini.LevenshteinDistance;

/**
 * Grup 3: Wikipedia
 * Usuari: ricard
 * Data: 5/2/15
 */
public class TestLevenshtein {
    public static void main(String args[]) {
        String[] testValues = {
                "hola", "hallo", "hello", "agus", "caca", "barquito", "pequeñito"};

        for(String s1 : testValues){
            for (String s2 :testValues){
                System.out.println(s1+":"+s2+"="+ ":"+LevenshteinDistance.calculate(s1, s2));

            }
        }
    }
}
