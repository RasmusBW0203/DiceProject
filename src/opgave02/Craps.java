/*
**Feedback til `Craps.java`**

Hej,

Godt gået med implementeringen af Craps! I har fanget spillets kerne-logik korrekt, med en fungerende "come out roll" og "point"-fase. Det er også godt at se, at I har implementeret bonusopgaverne, så man kan spille igen og se statistik.

Hera er et par detaljer, I kan arbejde med for at forbedre koden yderligere:

**Hvad der er godt:**

*   **Korrekt spil-logik:** Spillets grundlæggende regler er implementeret korrekt.
*   **God struktur:** Koden er pænt opdelt i metoder, der hver især har et klart ansvar.
*   **Bonusopgaver:** I har løst bonusopgaverne, så spillet kan køres flere gange, og der vises statistik til sidst.

**Forslag til forbedringer:**

1.  **Navne-typo:** Metoden `playCrabs()` indeholder en lille skrivefejl og burde nok hedde `playCraps()`.
2.  **Håndtering af `Scanner`:** `Scanner`-objektet bliver oprettet og lukket inde i `playCrabs()`-metoden. Det er bedre praksis at styre `Scanner`'en fra `main`-metoden for at undgå potentielle fejl, hvis programmet bliver mere komplekst.
3.  **Tydeligere bruger-prompt:** Når et spil er slut, spørger I brugeren: `"Rul en terning? ('ja/nej') "`. Dette kan være forvirrende. En mere præcis prompt ville være `"Spil igen? ('ja/nej')"`, da brugeren starter et helt nyt spil.
4.  **Output til konsol:** Både `rollDice()` og `rollForPoint()` skriver til konsollen. Overvej at lade metoder som `rollDice()` udelukkende returnere en værdi (summen eller de enkelte terningers øjne), og lad den kaldende metode (f.eks. `playCraps` eller `rollForPoint`) være ansvarlig for at printe output. Det kan give en renere adskillelse af logik og præsentation.

Fortsæt det gode arbejde.
*/
package opgave02;

import java.util.Scanner;

public class Craps {
    private static int winCount = 0;
    private static int lossCount = 0;

    static void main() {
        System.out.println("Velkommen til spillet, Craps");
        printRules();
        System.out.println();
        playCrabs();
        System.out.println();
        System.out.println("Tak for at spille Craps.");
    }

    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Regler for Craps");
        System.out.println("Det første kast kaldes ‘come out roll’. Spilleren vinder med det samme, hvis det første kast er 7\n" +
                "eller 11, og taber med det samme, hvis det første kast er 2, 3 eller 12. Hvis spillerens første kast er\n" +
                "4, 5, 6, 8, 9 eller 10, etableres dette tal som spillerens ‘point’. Spilleren bliver derefter ved med at\n" +
                "kaste, indtil han enten kaster sit ‘point’ igen eller kaster 7. Kaster han 7, har han tabt. Kaster han\n" +
                "sit ’point’, har han vundet");
        System.out.println("=====================================================");
    }

    public static void playCrabs() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Rul to terninger? ('ja/nej') ");
        String answer = scanner.nextLine();
        while (!answer.equals("nej")) {
            System.out.println();
            int sum = rollDice();

            System.out.println("Come out roll er: " + sum);
            if (sum == 7 || sum == 11) {
                System.out.println("Du vinder, da du rullede " + sum);
                winCount++;
            } else if (sum == 2 || sum == 3 || sum == 12) {
                System.out.println("Du har tabt, da du rullede " + sum);
                lossCount++;
            } else {
                System.out.println("Dit point er: " + sum);
                int point = sum;
                boolean gotPoint = rollForPoint(point);

                if (gotPoint) {
                    System.out.println("Du vinder, da du rullede dit point");
                    winCount++;
                } else {
                    System.out.println("Du tabte, da du rullede 7");
                    lossCount++;
                }
            }

            System.out.print("Rul en terning? ('ja/nej') ");
            answer = scanner.nextLine();
        }
        printStatistics();
        scanner.close();
    }


    private static int rollDice() {
        int d1 = (int) (Math.random() * 6 + 1);
        int d2 = (int) (Math.random() * 6 + 1);
        System.out.println("Første terning er: " + d1 + " Anden terning er: " + d2);
        return d1 + d2;
    }


    public static boolean rollForPoint(int point) {
        while (true) {
            int roll = rollDice();
            System.out.println("Du rullede " + roll);
            if (roll == point) {
                return true;
            } else if (roll == 7) {
                return false;
            }
        }
    }

    private static void printStatistics() {
        System.out.println("\nResults:");
        System.out.println("--------");
        System.out.println("Antal sejre: " + winCount);
        System.out.println("Antal tabte: " + lossCount);
    }
}
