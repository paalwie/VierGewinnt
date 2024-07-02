import java.util.Scanner;

public class VierGewinnt {

    static int xZeilen; // Standard: 6
    static int ySpalten; // Standard: 7
    static String spiel = "";
    static int durchlauf = 0;
    static String activePlayer = "";

    // static char game[][] = new char[xZeilen][ySpalten];

    public static void systemBeenden() {
        System.out.println("Programm wurde beendet!");
        System.exit(0);
    }

    private static void handleTheExceptionSomehow(ArrayIndexOutOfBoundsException exception) {
        System.out.println("");
    }

    public static void arrayPrint(char[][] game) {
        System.out.println("Aktuelles Spielbrett:");
        for (int i = 0; i < xZeilen; i++) {
            for (int y = 0; y < ySpalten; y++) {
                System.out.print(game[i][y]);
            }
            System.out.println();

        }
    }

    public static void help() {
        System.out.println("");
        System.out.println("Hier könnte Ihre Werbung stehen!");
        System.out.println("");
    }

    // hier zeichen setzen
    public static char[][] setzteZeichen(String spieler, int number, char[][] game) {

        int endZähler = 0;
        for (int i = 0; i < ySpalten; i++) {
            if (game[0][i] != '-') {
                endZähler++;
            }
            if (endZähler == ySpalten) {
                System.out.println("Feld voll, beide haben verloren.");
                systemBeenden();
            }
        }

        int highNumber = 1;
        char symbolImSpielbrett;

        // setze Spielerzeichen
        if (spieler == "player1") {
            symbolImSpielbrett = 'X';
        } else {
            symbolImSpielbrett = 'O';
        }

        if (game[game.length - highNumber][number - 1] == 'X' || game[game.length - 1][number - 1] == 'O') {
            // System.out.println("Schon belegt mit " + symbolImSpielbrett);

            // hier wird Exception geworfen ArrayIndexoutofBounds

            try {
                while (game[game.length - highNumber][number - 1] != '-') {
                    highNumber++;

                }
            } catch (ArrayIndexOutOfBoundsException exception) {
                handleTheExceptionSomehow(exception);
            }

        }

        try {
            game[game.length - highNumber][number - 1] = symbolImSpielbrett;
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Fehler: Die Spalte " + number + " ist schon voll. Bitte eine andere Spalte wählen!");
            spielerWechselnUndEingabe(spieler, game);
            handleTheExceptionSomehow(exception);
        }

        System.out.println("Nummer " + number + " wurde gespielt!");

        return game;

    }

    public static void spielerWechselnUndEingabe(String player, char[][] game) {

        String input = "";

        Scanner imputVonScanner = new Scanner(System.in);

        imputVonScanner.hasNextLine();
        input = imputVonScanner.nextLine();
        System.out.println(input);

        Integer playedNumber = 0;

        try {
            playedNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            if (input.equals("help")) {
                System.out.println("");
                System.out.println("Hilfefunktion wird aufgerufen");
            } else if (input.equals("exit")) {
                System.out.println("");
                System.out.println("Exitfunktion wird aufgerufen");
            } else {
                System.out.println(input + " ist keine verfügbare Zahl!");
            }
        }

        if (playedNumber > 0 && playedNumber <= ySpalten) {
            System.out.println("Die Nummer " + playedNumber + " wurde gewählt!");
            System.out.println("");

            game = setzteZeichen(player, playedNumber, game);
            // spiel = baueMuster(game, xZeilen, ySpalten);
        } else if (input.equals("exit")) {
            systemBeenden();
        } else if (input.equals("help")) {
            help();
            spielerWechselnUndEingabe(player, game);
        } else {
            System.out.println("Fehler: Die Eingabe ist ungültig! Nur 1 bis " + ySpalten + " ist möglich!");
            spielerWechselnUndEingabe(player, game);
        }

    }

    public static boolean checkWin(char[][] game) {

        durchlauf++;
        System.out.println("Durchlauf: " + durchlauf);
        int count = 0;
        boolean result = false;

        // für X
        for (int i = game.length - 1; i >= 0; i--) {

            count = 0;

            for (int j = ySpalten - 1; j >= 0; j--) {
                if (game[i][j] == 'X') {
                    int iStandard = i;
                    int jStandard = j;
                    count = 1;
                    while (j != 0 && game[i][j - 1] == 'X') {
                        count++;
                        ;
                        j--;
                        System.out.println("X - Siegcounter Links/Rechts: " + count);

                        if (count == 4) {
                            result = true;
                            return result;
                        }
                    }

                    count = 1;

                    // nach oben
                    while ((iStandard - 1) >= 0 && game[iStandard - 1][jStandard] == 'X') {
                        System.out.println("iStandard = " + iStandard + "; jStandard = " + jStandard);
                        count++;
                        System.out.println("X - Siegcounter Oben/Unten: " + count);
                        iStandard--;
                        // Code hier: Checke ob links, rechts und oben davon ein selbes Zeichen ist

                        // prüft oben vom Zeichen
                        if (count == 4) {
                            result = true;
                            return result;
                        }

                    }

                }

            }

        }

        // selbe für O
        for (int i = game.length - 1; i >= 0; i--) {

            count = 0;

            for (int j = ySpalten - 1; j >= 0; j--) {
                if (game[i][j] == 'O') {
                    int iStandard = i;
                    int jStandard = j;
                    count = 1;

                    // j ist das Problem, weil das bei Eingabe 1 bei 0 steht und dann ins Minus geht
                    // und Error wirft
                    // nach links
                    while (j != 0 && game[i][j - 1] == 'O') {
                        count++;
                        ;
                        j--;
                        System.out.println("O - Siegcounter Links/Rechts: " + count);

                        if (count == 4) {
                            result = true;
                            return result;
                        }
                    }

                    count = 1;

                    // nach oben
                    while ((iStandard - 1) >= 0 && game[iStandard - 1][jStandard] == 'O') {
                        count++;
                        System.out.println("O - Siegcounter Oben/Unten: " + count);
                        iStandard--;
                        // Code hier: Checke ob links, rechts und oben davon ein selbes Zeichen ist

                        // prüft oben vom Zeichen
                        if (count == 4) {
                            result = true;
                            return result;
                        }

                    }

                }

            }

        }

        return result;

    }

    public static void main(String[] args) {

        Scanner konsolenEingabe = new Scanner(System.in);
        System.out.println("Bitte gebe die Größe des Spielfeldes an.");
        System.out.println("Anzahl Zeilen:");
        xZeilen = konsolenEingabe.nextInt();
        System.out.println("Anzahl Spalten:");
        ySpalten = konsolenEingabe.nextInt();
        konsolenEingabe.nextLine();

        System.out.println("Bitte gebe den Namen für Spieler 1 ein:");
        String player1 = konsolenEingabe.nextLine();
        System.out.println("\nHallo " + player1 + "! Du bist Spieler 1. Dein Zeichen ist:\n X \n");

        System.out.println("Bitte gebe den Namen für Spieler 2 ein:");
        String player2 = konsolenEingabe.nextLine();
        System.out.println("\nHallo " + player2 + "! Du bist Spieler 2. Dein Zeichen ist:\n O \n");

        char game[][] = new char[xZeilen][ySpalten];

        for (int i = 0; i < xZeilen; i++) {
            for (int y = 0; y < ySpalten; y++) {
                game[i][y] = '-';
            }

        }

        int i = 2;
        int y = 0;

        while (checkWin(game) == false) {
            {
                if (i % 2 == 0) {
                    activePlayer = player1;
                    arrayPrint(game);
                    System.out.println(player1 + " (Spieler 1) ist dran!");
                    spielerWechselnUndEingabe("player1", game);
                    i++;
                    y++;

                } else {
                    activePlayer = player2;
                    arrayPrint(game);
                    System.out.println(player2 + " (Spieler 2) ist dran!");
                    spielerWechselnUndEingabe("player2", game);
                    i++;
                    y++;
                }
            }
        }

        if (checkWin(game) == true) {
            arrayPrint(game);
            System.out.println("Spiel vorbei, " + activePlayer + " hat GEWONNEN!");
            System.out.println(spiel);
        }

    }

}