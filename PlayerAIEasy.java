package tictactoe;

public class PlayerAIEasy extends Player {

    public PlayerAIEasy() {
        super();
    }

    public void makeMove(GameField gameField) {
        System.out.println("Making move level \"easy\"");
        FieldPoint p = gameField.getRandomFreePoint();
        gameField.setTile(p, this.player);
    }

}
