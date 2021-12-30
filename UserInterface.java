package tictactoe;

import java.util.Scanner;

public class UserInterface {
    private static UserInterface us = new UserInterface();
    Scanner scanner = null;

    public static UserInterface get() {
        return us;
    }

    public static void initialize(Scanner scanner) {
        us.scanner = scanner;
    }

    public boolean isStartParamsValid(String[] cnp) {
        if (cnp.length != 3) {
            return false;
        }
        for (int i = 1; i < cnp.length; i++) {
            String p = cnp[i];
            if (!PlayerFactory.isValidPlayer(p)) {
                return false;
            }
//            if (!p.equals("easy") && !p.equals("user") && !p.equals("medium")) {
//                return false;
//            }
        }
        return true;
    }

    public String[] readMenuCommandWithParams() {
        boolean isInputFine = false;
        String[] words;
        do {
            System.out.print("Input command: ");
            String input = this.scanner.nextLine();
            words = input.toLowerCase().split("\\s");
            switch (words[0]) {
                case "start":
                    isInputFine = isStartParamsValid(words);
                    break;
                case "exit":
                    isInputFine = true;
                default:
            }
            if (!isInputFine) {
                System.out.println("Bad parameters!");
            }
        } while (!isInputFine);
        return words;
    }


    public String[] readStartParams() {
        String[] retVal = new String[2];
        boolean isInputFine = false;
        do {
            try {
                retVal[0] = this.scanner.next().toLowerCase();
                retVal[1] = this.scanner.next().toLowerCase();
                if (this.isStartParamsValid(retVal)) {
                    isInputFine = true;
                }
            } catch (Exception e) {
                //
            }
            if (!isInputFine) {
                System.out.println("Bad parameters!");
            }
        } while (!isInputFine);
        return retVal;
    }

    public FieldPoint readCoords() {

        int posY = 0;
        int posX = 0;

        boolean isInputFine = false;
        do {
            System.out.print("Enter the coordinates: ");
            String input = this.scanner.nextLine();
            String[] coords = input.split("\\s");

            if (coords.length == 2) {
                try {
                    posY = Integer.parseInt(coords[0]);
                    posX = Integer.parseInt(coords[1]);
                    isInputFine = true;
                } catch (Exception e) {
                    //
                }
            }
            if (!isInputFine) {
                System.out.println("You should enter numbers!");
            }
        } while (!isInputFine);
        return new FieldPoint(posY, posX);
    }

}
