package tests;

import domini.JaroWinklerDistance;

import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: eduard.casellas
 * Data: 15/05/15
 */
public class TestJaroWinkler {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Comença a introduir strings");
        String s1 = sc.next();
        String s2 = sc.next();
        System.out.println("\nRESULTATS:");
        System.out.println(s1+":"+s2+" = "+ JaroWinklerDistance.calculate(s1, s2));
    }
}
