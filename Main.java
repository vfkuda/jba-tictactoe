package tictactoe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserInterface.initialize(sc);

        Game game = new Game();

        //menu look
        String[] cnp;
        do {
            cnp = UserInterface.get().readMenuCommandWithParams();
            switch (cnp[0]) {
                case "start":
                    Player px = PlayerFactory.getPlayer(cnp[1]);
                    px.playAs(Player.PLAYER_X);
                    Player po = PlayerFactory.getPlayer(cnp[2]);
                    po.playAs(Player.PLAYER_O);

                    game.setPlayers(px, po);
                    game.play();
                    break;
                case "exit":
                    break;
                default:
                    assert (false);

            }
        } while (!"exit".equals(cnp[0]));


    }


}
