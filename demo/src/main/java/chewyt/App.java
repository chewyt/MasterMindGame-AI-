package chewyt;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        // ANSWER - SET AS RANDOM
        String[] permutationList = { "RD", "BU", "YW", "GN", "BK", "WH" };
        List<String> answer = new ArrayList<>();
        // answer.add("BK");
        // answer.add("RD");
        // answer.add("WH");
        // answer.add("GN");
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            answer.add(permutationList[random.nextInt(6)]);
        }

        List<String> tempAnswer = new ArrayList<>();
        tempAnswer.addAll(answer);

        List<String> keyPegs = new ArrayList<>();
        List<String> correctkeyPegs = new ArrayList<>();
        correctkeyPegs.add("BK");
        correctkeyPegs.add("BK");
        correctkeyPegs.add("BK");
        correctkeyPegs.add("BK");

        List<String> check = new ArrayList<>();
        String input;
        String confirmation;
        int tries = 10;

        Scanner sc = new Scanner(System.in);

        do {

            System.out.printf("[Attempt no. %d] Enter the Code Pegs:\n", (11 - tries));
            do {
                check.clear();
                for (int i = 1; i <= 4; i++) {
                    System.out.printf("Peg No. %d (RD, BU, YW, GN, WH, BK): > ", i);
                    input = sc.nextLine().toUpperCase().trim();

                    if (input.equals("RD") || input.equals("BU") || input.equals("YW") || input.equals("GN")
                            || input.equals("WH") || input.equals("BK")) {
                        check.add(input);
                    } else {
                        System.out.println("[ERROR] Wrong input.Please try again");
                        i--;
                    }
                }
                System.out.println("Confirmed Code Peg combination: ");
                for (String string : check) {
                    System.out.print(string + " ");
                }
                System.out.println();
                System.out.print("Y/N? >");
                confirmation = sc.nextLine().toUpperCase().trim();
            } while (!(confirmation.equals("Y") || confirmation.equals("")));

            // check combination against answer
            tempAnswer.clear();
            tempAnswer.addAll(answer);

            // System.out.print("Temp Answer:");
            // for (String string : tempAnswer) {
            // System.out.print(string + " ");
            // }
            // System.out.println();
            System.out.print("Combination:");
            for (String string : check) {
                System.out.print(string + " ");
            }
            System.out.println();

            keyPegs.clear();
            for (int i = 0; i < check.size(); i++) {

                if (tempAnswer.get(i).equals(check.get(i))) {
                    keyPegs.add("BK");
                    // System.out.println("BK added due to "+tempAnswer.get(i)+" is similar to
                    // "+check.get(i));
                    tempAnswer.set(i, "");
                    check.set(i, "-");

                }
            }
            for (int i = 0; i < check.size(); i++) {

                if (tempAnswer.contains(check.get(i))) {
                    keyPegs.add("WH");
                    // System.out.println("WH added due to "+check.get(i)+" inside the right
                    // combination but wrong position");
                    tempAnswer.set(tempAnswer.indexOf(check.get(i)), "");
                }
            }

            // Print key peg results
            System.out.print("Key Peg results: ");
            for (String string : keyPegs) {
                System.out.print(string + " ");
            }
            System.out.println();
            if (!keyPegs.equals(correctkeyPegs)) {
                tries--;
                if (tries == 0) {
                    System.out.printf("[GAME] GAME OVER... :X \n");
                    System.exit(0);
                }
                System.out.printf("[GAME] %d more tries remaining...\n\n", tries);
            }

        } while (!keyPegs.equals(correctkeyPegs));

        System.out.println("You win the game!!");
        System.out.print("Answer:");
        for (String string : answer) {
            System.out.print(string + " ");
        }
        System.out.println();
        sc.close();

    }
}
