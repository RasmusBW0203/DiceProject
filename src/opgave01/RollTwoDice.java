package opgave01;

import java.util.Scanner;

public class RollTwoDice {
    private static int rollCount = 0;
    private static int rollSum = 0;
    private static int identical = 0;
    private static int highestThrow = 0;
    private static int[] counterRoll = new int[6];

    static void main() {
        System.out.println("Velkommen til spillet, rul to terninger.");
        printRules();
        System.out.println();
        playTwoDice();
        System.out.println();
        System.out.println("Tak for at spille, rul to terninger.");
    }

    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Regler for rul en terning");
        System.out.println("Spilleren ruller to terninger, så længe man lyster.");
        System.out.println("=====================================================");
    }

    private static void playTwoDice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Rul to terninger? ('ja/nej') ");
        String answer = scanner.nextLine();
        while (!answer.equals("nej")) {
            int[] faces = new int[2];
            rollDice(faces);
            System.out.println("Du rullede: " + faces[0] + " og " + faces[1]);
            System.out.println();

            updateStatistics(faces);
            System.out.print("Rul en terning? ('ja/nej') ");
            answer = scanner.nextLine();
        }
        printStatistics();
        scanner.close();
    }

    private static int[] rollDice(int[] faces) {
        for (int i = 0; i < faces.length; i++) {
            faces[i] = (int) (Math.random() * 6 + 1);
        }
        return faces;


    }

    private static void updateStatistics(int[] faces) {
        rollCount++;
        rollSum += faces[0] + faces[1];
        if (faces[0] == faces[1]) {
            identical++;
        }
        int sum = faces[0] + faces[1];
        if (sum > highestThrow) {
            highestThrow = sum;
        }
        for (int i = 0; i < faces.length; i++) {
            int face = faces[i];
            counterRoll[face - 1]++;
        }
    }

    private static void printStatistics() {
        System.out.println("\nResults:");
        System.out.println("--------");
        System.out.println("Antal rul: " + rollCount);
        System.out.println("Summen af alle terninger var: " + rollSum);
        System.out.println("Der var " + identical + " kast, hvor terningerne var ens");
        System.out.println("Det største antal øjne i et kast var: " + highestThrow);

        for (int i = 0; i < counterRoll.length; i++) {
            System.out.println("Øjnene " + (i + 1) + " er rullet " + counterRoll[i] + " gange");
        }
    }
}