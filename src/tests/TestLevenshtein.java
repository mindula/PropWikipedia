package tests;

import domini.LevenshteinDistance;

import java.util.Scanner;

/**
 * Grup 3: Wikipedia
 * Usuari: agusti.bau
 * Data: 5/2/15
 */
public class TestLevenshtein {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el nombre de strings que vols comparar entre elles");
        int nStrings = sc.nextInt();
        String[] strings = new String[nStrings];
        System.out.println("Comença a introduir strings");
        sc.nextLine();
        for(int i = 0; i < nStrings; i++){
            strings[i] = sc.nextLine();
        }
        System.out.println("\nRESULTATS:");
        for(String s1 : strings){
            for (String s2 : strings){
                System.out.println(s1+":"+s2+" = "+ LevenshteinDistance.calculate(s1,s2));
            }
        }

    }
}
