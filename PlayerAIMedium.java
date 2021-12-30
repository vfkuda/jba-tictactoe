package tictactoe;

public class PlayerAIMedium extends Player {

    public PlayerAIMedium() {
        super();
    }

    @Override
    public void makeMove(GameField gameField) {
        System.out.println("Making move level \"easy\"");

        FieldPoint point;

        //        1. If it already has two in a row
        //        and can win with one further move, it does so.
        point = gameField.getAnalyzer().findOpportunityFor(this.player);
        if (point != null) {
            gameField.setTile(point, this.player);
            return;
        }

        //        2. If its opponent can win with one move,
        //        it plays the move necessary to block this.
        point = gameField.getAnalyzer().findOpportunityFor(this.getOpponent());
        if (point != null) {
            gameField.setTile(point, this.player);
            return;
        }

        //        3. Otherwise, it makes a random move.
        FieldPoint p = gameField.getRandomFreePoint();
        gameField.setTile(p, this.player);
    }
}
