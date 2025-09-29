package opgave03;

import java.util.Scanner;

import static examples.RollOneDie.rollDie;

public class Pigs {
    static int sumPlayer1 = 0;
    static int sumPlayer2 = 0;

    static void main() {
        System.out.println("Velkommen til spillet, Pigs.");
        printRules();
        System.out.println();

        playPigs();

        System.out.println();
        System.out.println("Tak for at spille, pigs.");
    }

    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Regler for rul en terning");
        System.out.println("Første spiller kaster en terning, indtil han enten kaster 1, eller beslutter sig for at stoppe. Hvis han\n" +
                "slår en 1’er, får han ingen point i denne runde. Hvis han beslutter sig for at stoppe, inden har slår\n" +
                "en 1’er, lægges summen af alle hans kast i denne runde sammen med hans samlede antal point,\n" +
                "og turen går videre til næste spiller. Derefter skiftes spillerne til at kaste. Den første spiller, der\n" +
                "samlet når 100 point, har vundet.");
        System.out.println("=====================================================");
    }

    public static void playPigs() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Rul en terning? ('ja/nej') ");
        String answer = scanner.nextLine();
        while (!answer.equals("nej")) {
            int face = rollDie();
            if (face != 1) {
                System.out.println("Spiller 1 rullede: " + face);
                System.out.println();

                sumPlayer1 += face;
            } else {
                sumPlayer1 = 0;
                System.out.println("Skriv \"Nej\", så den anden kan spille, da du slog 1: ");
            }

            System.out.print("Rul en terning? ('ja/nej') ");
            answer = scanner.nextLine();
            if (answer.equals("nej")) {
                while (!answer.equals("nej")) {
                    int face2 = rollDie();
                    if (face2 != 1) {
                        System.out.println("Spiller 2 rullede: " + face2);
                        System.out.println();

                        sumPlayer2 += face2;
                    } else {
                        sumPlayer2 = 0;
                        System.out.println("Skriv \"Nej\", så den anden kan spille, da du slog 1: ");
                    }

                    System.out.print("Rul en terning? ('ja/nej') ");
                    answer = scanner.nextLine();
                }
            }
        }
        printStatistics();
        scanner.close();
    }

    private static int rollDie() {
        return (int) (Math.random() * 6 + 1);
    }

    private static void printStatistics() {
        System.out.println("\nResults:");
        System.out.println("-------");
        System.out.println("Antal point for spiller 1: " + sumPlayer1);
        System.out.println("Antal point for spiller 2: " + sumPlayer2);
    }
}