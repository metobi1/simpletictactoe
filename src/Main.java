import java.util.Scanner;

public class Main {
    final static Scanner scanner = new Scanner(System.in);
    static char[] row1 = new char[3];
    static char[] row2 = new char[3];
    static char[] row3 = new char[3];
    static char[][] gameValues = new char[3][3];
    static boolean xWin = false;
    static boolean oWin = false;
    static boolean win = false;
    static boolean draw = false;
    static boolean inComplete = false;
    static boolean endGame = false;
    static int fir = 0;
    static int sec = 0;
    static char emptyChar = ' ';
    static int playTurn = 0;

    public static void main(String[] args) {
        gamePlay();
    }

    static void gamePlay() {

        // fill-up the 2 dim array with initial empty char
        for (int i = 0; i < gameValues.length; i++) {
            for (int j = 0; j < gameValues[i].length; j++) {
                gameValues[i][j] = emptyChar;
            }
        }
        printGame(gameValues);
        inputPlayAnalysis();
    }

    static void printGame(char [][] gameBody) {
        printUpDownDash();
        printGameBody(gameBody);
        printUpDownDash();
    }

    static void inputPlayAnalysis() {

        while (!endGame) {

            System.out.print("Enter the coordinates: ");
            StringBuilder inCell = new StringBuilder(scanner.nextLine());
            inCell.deleteCharAt(1);
            if (inCell.length() != 2) {
                System.out.println("You should enter two digits");
                continue;
            } if (!onlyDigits(inCell.toString())) {
                System.out.println("You should enter numbers!");
                continue;
            } else if (!onlyOne23(inCell.toString())) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            } else if (checkOccupied(inCell.toString())) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            if (playTurn % 2 == 0) {
                gameValues[fir][sec] = 'X';
            } else {
                gameValues[fir][sec] = 'O';
            }

            printGame(gameValues);
            if (playTurn >= 4) {
                gameStates();
            }
            playTurn++;
        }
    }

    static void printUpDownDash() {
        System.out.println("---------");
    }

    static void printGameBody(char [][] gameValues) {
        for (int i = 0; i < gameValues.length; i++) {
            System.out.print("|");
            for (int j = 0; j < gameValues[i].length; j++) {
                System.out.print(" " + gameValues[i][j]);
            }
            System.out.println(" |");
        }
    }

    static void gameStates () {
        winState();
        if (win) {
            if (xWin) {
                System.out.println("X wins");
                endGame = true;
            } else if (oWin) {
                System.out.println("O wins");
                endGame = true;
            }
        }  else if (draw && !inComplete()) {
            System.out.println("Draw");
            endGame = true;
        } else if (inComplete) {
            inComplete = false;
        }
    }

    static boolean winState() {
        for (int i = 0; i < gameValues.length; i++) {
            for (int j = 0; j < gameValues[i].length; j++) {

                // fill-up the row arrays
                if (i == 0) {
                    row1[j] = gameValues[i][j];
                } else if (i == 1) {
                    row2[j] = gameValues[i][j];
                } else {
                    row3[j] = gameValues[i][j];
                }
            }
        }

        for (int i = 0; i < row1.length; i++) {

            if (i == 0 ) {
                // Checking rows
                if (row1[i] + row1[i + 1] + row1[i + 2] == 264) {
                    xWin = true;
                }
                if (row1[i] + row1[i + 1] + row1[i + 2] == 237) {
                    oWin = true;
                }
                if (row2[i] + row2[i + 1] + row2[i + 2] == 264) {
                    xWin = true;
                }
                if (row2[i] + row2[i + 1] + row2[i + 2] == 237) {
                    oWin = true;
                }
                if (row3[i] + row3[i + 1] + row3[i + 2] == 264) {
                    xWin = true;
                }
                if (row3[i] + row3[i + 1] + row3[i + 2] == 237) {
                    oWin = true;
                }
                if (xWin || oWin) {
                    win = true;
                    return win;
                }
                // Checking columns
                if (row1[i] + row2[i] + row3[i] == 264) {
                    xWin = true;
                }
                if (row1[i] + row2[i] + row3[i] == 237) {
                    oWin = true;
                }
                if (row1[i + 1] + row2[i + 1] + row3[i + 1] == 264) {
                    xWin = true;
                }
                if (row1[i + 1] + row2[i + 1] + row3[i + 1] == 237) {
                    oWin = true;
                }
                if (row1[i + 2] + row2[i + 2] + row3[i + 2] == 264) {
                    xWin = true;
                }
                if (row1[i + 2] + row2[i + 2] + row3[i + 2] == 237) {
                    oWin = true;
                }
                if (xWin || oWin) {
                    win = true;
                    return win;
                }
                // Checking diagonals
                if (row1[i] + row2[i + 1] + row3[i + 2] == 264) {
                    xWin = true;
                }
                if (row1[i] + row2[i + 1] + row3[i + 2] == 237) {
                    oWin = true;
                }
                if (row1[i + 2] + row2[i + 1] + row3[i] == 264) {
                    xWin = true;
                }
                if (row1[i + 2] + row2[i + 1] + row3[i] == 237) {
                    oWin = true;
                }
                if (xWin || oWin) {
                    win = true;
                    return win;
                    // if all fails, then the game is a draw
                }else {
                    draw = true;
                    break;
                }
            }
        }
        return win;
    }

    static boolean inComplete () {
        for (int i = 0; i < gameValues.length; i++) {
            for (int j = 0; j < gameValues[i].length; j++) {
                if (gameValues[i][j] == ' ') {
                    inComplete = true;
                    return inComplete;
                }
            }
        }
        return inComplete;
    }

    // replicating Math.pow returns int
    static int toPos (int number) {
        if (number < 0) {
            number = number * -1;
        }
        return number;
    }
    // Checks for only positive numbers
    static boolean onlyDigits(String str) {
        int len = str.length();
        boolean onlyDigits = false;
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                onlyDigits = true;
            }
        }
        return onlyDigits;
    }
    // Checks for positive 0 - 3
    static boolean onlyOne23(String str) {
        int len = str.length();
        boolean onlyOne23 = false;
        int total = 0;
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '3') {
                total++;
            }
        }
        if (total == 2) {
            onlyOne23 = true;
        }
        return onlyOne23;
    }
    // Check if cell at given coordinate is '_' i.e. unoccupied
    static boolean checkOccupied (String str) {
        boolean occupied = false;
        // convert the character to integer( ASCII ints start at '0' = 48)
        fir = (str.charAt(0) - '0') - 1; // remove one since char array
        sec = (str.charAt(1) - '0') - 1;// starts from 0 and spec from 1
        if (gameValues[fir][sec] != ' ') {
            occupied = true;
        }
        return occupied;
    }
}
