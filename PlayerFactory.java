package tictactoe;

import java.util.Map;
import java.util.function.Supplier;

public class PlayerFactory {
    //    private static String[] validPlayers = new String[]{"user", "easy"};
    private static Map<String, Supplier<Player>> players = Map.of(
            "user", PlayerHuman::new,
            "easy", PlayerAIEasy::new,
            "medium", PlayerAIMedium::new,
            "hard", PlayerAIHard::new
    );

    public static boolean isValidPlayer(String strPlayer) {
        return players.containsKey(strPlayer);
    }

    public static Player getPlayer(String strPlayer) {
//
//        switch (playerType) {
//            case "user":
//                return new HumanPlayer();
//            case "easy":
//            case "medium":
//                return new AIPlayer(playerType);
//        }
//        return null;

        return players.get(strPlayer).get();
    }
}
