/*
Denne fil er en implementering af terningspillet "Gris" (Pig).

Efter at have gennemgået koden ser det ud til, at der er betydelige problemer med spillets kerne-logik. Den nuværende implementering følger ikke reglerne for Gris korrekt, hvad angår tur-baseret spil, pointgivning og vinderbetingelser.

Her er en opsummering af problemerne:

1.  **Fejlbehæftet spil-løkke:** Logikken for at skifte mellem Spiller 1 og Spiller 2 virker ikke efter hensigten. De indlejrede `while`-løkker og afhængigheden af, at brugeren skriver "nej", forhindrer et korrekt skift af tur.
2.  **Forkert pointgivning:** Koden nulstiller en spillers *samlede score* til nul, når de slår en 1'er. Ifølge reglerne mister man kun de point, man har samlet *i den aktuelle runde*, ikke hele sin score. Konceptet om en midlertidig "runde-score" mangler.
3.  **Manglende "hold"-funktion:** En central del af Gris er spillerens valg om at "holde" (stoppe med at rulle) for at gemme sine point fra runden. Dette er ikke implementeret tydeligt; spillet er afhængigt af, at brugeren skriver "nej", hvilket også bruges til at skifte spiller og afslutte spillet.
4.  **Ingen vinderbetingelse:** Spillet tjekker ikke, om en spiller har nået 100 point for at kåre en vinder.
5.  **Forvirrende import:** Der er en ubrugt og unødvendig import af `rollDie` fra en anden pakke.

Kort sagt, spillets logik trænger til en fundamental omstrukturering for at virke korrekt.
*/
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
